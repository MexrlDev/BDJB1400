# hen.iso — Complete Reverse-Engineering Analysis

**A deep dive into the Bonk HEN 14.00 Blu-ray ISO.**

| | |
|---|---|
| Fork | Bonk HEN 14.00 (Kat, Slopp, Pigeon) |
| Upstream | PS4-14.00-BD-JB (Scene-Collective) |
| Payload | Scene-Collective HEN 2.2.0 BETA |
| Target | PS4 firmware **14.00** |
| ISO size | 6,225,920 bytes |
| ISO SHA-256 | `39DA48EA6711795850D3062B4BA85A650D4B6289E6682350843DB8E0F0D141C3` |
| Format | UDF (BEA01 signature at offset 0x8001) |
| Verdict | **DEMO BUILD** — complete exploit chain, broken payload delivery |

Every claim in this document is traceable to a raw artifact in `analysis/`.
The chain has been reconstructed from bytecode, disassembly, and string
analysis — not from the original `.java` sources (which were never public).

---

## 1. Executive Summary

The `hen.iso` image is a **Blu-ray Disc Java (BD-J) homebrew enabler** targeting
PS4 firmware 14.00. Inserted into a PS4, it achieves **kernel code execution**
through a complete three-tier exploit chain:

1. **BD-J JVM escape** (userspace) — three primitives bypass the BD-J sandbox
2. **Kernel exploit** (kernel) — heap grooming + `/dev/dce` kqueue UAF + ROP
3. **Payload delivery** (post-exploit) — copies `hen.bin` into RWX memory and
   runs it via `pthread_create`

The exploit **does not deliver a working payload**. `HENURL` is set to a
sentinel path (`file:/__bonk___offline_missing__/hen.bin`) that cannot be
opened. The kernel is patched (`uid=0`, `sysent[11]` = kexec) before the
failure point, leaving the console in a **half-jailbroken state** requiring
cold reboot.

The `NOTICE.txt` warnings ("may write unknown kernel objects on failed runs",
"cold reboot after failure") are accurate — this is a deliberate demo build.

---

## 2. Container Structure

### ISO layout

```
hen.iso (6,225,920 bytes, UDF filesystem)
├── BDMV/
│   ├── index.bdmv             (526 B) — BD-J auto-launch entry
│   ├── MovieObject.bdmv       (7402 B) — movie object table
│   ├── BDJO/00000.bdjo        (164 B) — Xlet launch descriptor
│   ├── JAR/00000.jar          (192,403 B) — the Xlet (signed)
│   ├── STREAM/00000.m2ts      (3,975,168 B) — H.264 intro video (not payload)
│   ├── PLAYLIST/00000.mpls    (170 B)
│   ├── CLIPINF/00000.clpi     (1136 B)
│   └── AUXDATA/               (fonts for UI, ~312 KB)
├── CERTIFICATE/
│   ├── app.discroot.crt       (665 B) — signed root cert
│   ├── bu.discroot.crt        (665 B)
│   └── id.bdmv                (89 B) — disc ID binding
├── x/offline/                 ← FOUR classes loaded at runtime (hash-verified)
│   ├── J196rR.class           (2931 B) — AWT info leak
│   ├── AXZXkk.class           (1626 B) — JNI bridge + /dev/dce race
│   ├── EkIKZR.class           (813 B)  — Unsafe R/W wrapper
│   └── Hx1400.class           (56,859 B) — THE KERNEL EXPLOIT
├── bonk/
│   ├── hen.bin                (562,240 B) — Scene-Collective HEN payload
│   └── local/
│       ├── rules.bin          (5338 B)
│       ├── shapes.bin         (794 B)
│       ├── guards.bin         (184 B)
│       ├── schedule.bin       (168 B)
│       ├── marker-sites.bin   (40 B)
│       ├── template-hashes.bin (1528 B)
│       ├── write-sites.bin    (656 B)
│       └── templates/         (41 cmd-*.bin files, 23,340 B total)
└── NOTICE.txt                 (564 B)
```

### Signing

The JAR is signed by the `CERTIFICATE/*.discroot.crt` chain. BD-J player
verifies this before launching the Xlet. Any modification to `00000.jar`
invalidates the signature — this is why the fork authors **preserve original
class hashes** even for dead code (see §12).

### Hash verification chain

`InitXlet.CLASSES[][]` (line 40) hardcodes SHA-256 of the four offline classes.
Before loading each one, the Xlet verifies the hash. Any modification fails at
that point. Verified:

| Class | Expected SHA-256 | Verified |
|---|---|---|
| J196rR | `f59953027867d15d1434179991feeeb94bf274422f742e739067fd65400a264d` | ✅ |
| AXZXkk | `2362afc9efa07a688169b9121d9e5f8bdacbead58f87cafd23fdddffea58b429` | ✅ |
| EkIKZR | `df30bfbd25c3cdbe67965d907d575efb562738cf29c258895a698ff2ef525137` | ✅ |
| Hx1400 | `e597710078e9afab5b24f4c22ea31fe7e5a5643b602b06c2866273b61c9c57e9` | ✅ |

The `P[]` table (see §11) is stored **outside** these classes — in the VM
scripts — so firmware-specific offsets can be updated without changing hashed
bytecode. That's the key architectural insight of this fork.

---

## 3. Three-Tier Architecture

```
┌─────────────────────────────────────────────────────────────────┐
│  TIER 1: BD-J JVM (limited sandbox)                             │
│                                                                 │
│  InitXlet.startXlet()                                           │
│    ├─ Waits for X button press                                  │
│    ├─ Verifies four offline classes by SHA-256                  │
│    ├─ Runs Boot.exec(cmd-0002.bin ... cmd-0046.bin)             │
│    └─ Displays progress via Screen + OfflineRenderer            │
│                                                                 │
│  Boot.exec = 30-opcode bytecode VM                              │
│    (SLEEP, SPIN, loadClass, getMethod, invoke, spawnThread)     │
└─────────────────────────────────────────────────────────────────┘
                                ↓
         ┌──────────────────────────────────────────┐
         │  TIER 2: JVM Userspace (native access)   │
         │                                          │
         │  J196rR  → AWT OOB info leak             │
         │  EkIKZR  → sun.misc.Unsafe R/W wrapper   │
         │  AXZXkk  → JNI native bridge + race      │
         └──────────────────────────────────────────┘
                                ↓
         ┌──────────────────────────────────────────┐
         │  TIER 3: PS4 Kernel (FreeBSD 11-derived) │
         │                                          │
         │  Hx1400.run() → kernel exploit           │
         │    ├─ kqueue UAF → fake ops → ROP        │
         │    ├─ uid=0 via ucred write              │
         │    ├─ sysent[11] = kexec                 │
         │    └─ hen.bin fetch ← FAILS              │
         └──────────────────────────────────────────┘
                                ↓
         ┌──────────────────────────────────────────┐
         │  PAYLOAD: hen.bin (562 KB, x86-64)       │
         │  Scene-Collective HEN 2.2.0 BETA         │
         │  (never reached — demo build)            │
         └──────────────────────────────────────────┘
```

---

## 4. Tier 1 — The BD-J Xlet

### 4.1 InitXlet.java — entry point

Wired by `BDMV/BDJO/00000.bdjo` to auto-launch when the disc is opened.

Flow:
1. `initXlet()` — sets up state, no-op
2. `startXlet()` — displays "Insert X to launch" screen, waits for X press
3. On X press: sets `Boot.EP = discRoot`, `Boot.SID = "offline"`,
   `Boot.PAR = InitXlet.class.getClassLoader()`
4. Calls `Boot.exec(cmd-0002.bin)` then `Boot.exec(cmd-0043.bin)`
5. Waits for return, displays result

**Line 580** — `Boot.EP = discRoot` — this sets the base URL for all subsequent
`Boot.io()` operations to the local disc root (`file:` scheme). This is
**critical for security analysis** — see §12.

**Lines 619 and 635** — the two `Boot.exec()` calls. First loads the
primitives, second fires the exploit.

### 4.2 Boot.java — the VM interpreter + upstream launcher

Contains the **30-opcode bytecode VM** (see §6) plus:
- `Boot.run()` — dead upstream code (line 966, unreachable — see §12)
- `Boot.io()` — dead HTTP client (line 162)
- Static state: `O[]` (objects), `R[]` (numbers), `S[]` (strings), `Z[]` (bytes),
  `M[]` (methods/fields)

**The VM is the key architectural innovation.** Instead of loading classes
directly, `InitXlet` runs scripts that:
1. Load classes via `op 7/8` (with DVBClassLoader fallback)
2. Call methods via `op 10/14` (reflection)
3. Spawn threads via `op 29` (background execution)
4. Manipulate arrays via `op 15/17` (build P[] dynamically)

This lets the fork authors **change exploit behavior without changing class
hashes** — only the script files need updating.

### 4.3 Cfg.java — hardcoded constants (dead)

```java
SERVER = "tester.f5rvoice.com";
PORT   = 1992;
BUILD  = "6";
CID    = "c6";
CKEY   = "942a76d6946a34ce476bdabb09989f4c";
```

All dead code. See §12 for the 4-layer proof.

### 4.4 UI classes

- `Screen.java` — HAVi screen container (AWT)
- `OfflineRenderer.java` — draws progress from metadata files
- `LocalResultLedger.java` — records success/failure (no network)

---

## 5. Tier 2 — Three Sandbox Escape Primitives

### 5.1 J196rR — AWT OOB info leak

```java
public int[] fFFjF2(int[] marker) {
    BufferedImage img = (BufferedImage) vNjgvC;
    int[] buf = new int[16];
    img.getRGB(0, 0, 1, 2, buf, 0, -2);   // ← NEGATIVE scansize
    // reads backward past the pixel buffer → heap pointers
}
```

**Why it works:** Java's AWT `getRGB()` does not fully validate `scansize`. A
negative value causes the internal memcpy to walk backward through the image
data, exposing adjacent heap content. The leaked pointers identify the JVM's
ASLR base for subsequent `sun.misc.Unsafe` operations.

**Purpose:** Bootstrap the memory R/W primitive by leaking JVM heap addresses.

### 5.2 EkIKZR — Unsafe R/W wrapper

```java
public long[] HF6fzp(long addr, int n, int stride)   // read n longs
public void   JtAXmm(long addr, long[] arr, int s)   // write longs
public byte[] OPitzi(long addr, int n)               // read bytes
public void   R0Bep2(long addr, byte[] arr)          // write bytes
```

Backed by `sun.misc.Unsafe` (obtained via reflection from
`Class.forName("sun.misc.Unsafe").getDeclaredField("theUnsafe")`).

**Limitation:** Only works on **userspace** addresses (JVM heap). Cannot touch
kernel memory directly. This is a property of PS4's BD-J JVM sandbox.

**Purpose:** Manipulate JVM internal state (class loader caches, object
layout) after the AWT leak reveals addresses.

### 5.3 AXZXkk — JNI bridge + TOCTOU race

```java
private native long vrzs27(long var1, int[] var3);

long QEBB8A(long l) { return vrzs27(l, new int[]{1}); }
long Cn90dc(long l) { return vrzs27(l, new int[]{1}); }

long DX9qSL(long l, long l2, ..., long l7) {
    for (int i = 0; i < 2; i++) {
        unsafe.putLong(kernel_addr + OFF_10, value_9);
        unsafe.putLong(value_9 + OFF_12, i==0 ? 11 : 12);   // ← RACE
        unsafe.putLong(kernel_addr + OFF_13, saved_14);
        long result = QEBB8A(kernel_addr);                   // ← window open
        unsafe.putLong(kernel_addr + OFF_13, saved_15);
        if (result && result > OFF_14) {
            unsafe.putLong(result + OFF_15, saved_16);       // patch
            unsafe.putLong(result + OFF_16, 0L);             // zero
        }
    }
}
```

**The `native` keyword is the escape.** `vrzs27` runs **inside the BD-J JVM's
native layer**, which has direct libc/syscall access. That's what lets the
payload call `open("/dev/dce")`, `kqueue()`, and `kevent()` from Java.

`DX9qSL` implements a **2-iteration TOCTOU race** on `/dev/dce` file
descriptors. This is the trigger for the kqueue UAF that `Hx1400` exploits.

**Purpose:** The actual escape primitive. Everything else supports this.

---

## 6. The Boot.exec VM — 30 Opcodes

`Boot.exec(byte[])` interprets a custom bytecode. Wire format:

```
u16  command_count
repeat command_count times:
    u8   opcode
    (opcode-dependent args, big-endian)
```

Full opcode table:

| # | Name | Semantics |
|---|---|---|
| 1 | HALT | `readLong()` then halt with return value |
| 2 | PRINT_HEX | `Screen.println(Long.toHexString(readLong()))` |
| 3 | SLEEP | `Thread.sleep(readLong())`, ignore second `readLong()` |
| 4 | SPIN | busy-loop N iterations |
| 5 | setString | `S[dst] = new String(utf8_bytes)` |
| 6 | setBytes | `Z[dst] = bytes` |
| 7 | loadClass | `O[dst] = Class.forName(S[name], true, PAR)` |
| 8 | loadViaLoader | `O[dst] = L(Z[loader], S[name]).loadClass(...)` with DVBClassLoader fallback |
| 9 | newInstance | `O[dst] = constructor(argtypes).newInstance(args)` |
| 10 | getMethod | `M[dst] = cls.getDeclaredMethod(S[name], argtypes)` |
| 11 | getField | `M[dst] = cls.getDeclaredField(S[name])` |
| 12 | getFieldValue | `R[dst] = M[field].get(obj)` |
| 13 | setFieldValue | `M[field].set(obj, arg)` |
| 14 | invokeMethod | `R/O[dst] = M[method].invoke(obj, args)` |
| 15 | newArray | `O[dst] = Array.newInstance(cls, size)` |
| 16 | arrayGet | `R[dst] = Array.get(O[arr], elem)` |
| 17 | arraySet | `Array.set(O[arr], elem, arg)` |
| 18 | arrayLength | `R[dst] = Array.getLength(O[arr])` |
| 19 | numFromArg | `R[dst] = num(arg)` |
| 20 | arith | `R[dst] = a op b` (12 subops: +, -, &, \|, ^, <<, >>>, ×, ÷, ==, <, >) |
| 21 | outputByte | `dataOutputStream.writeLong(num(arg))` |
| 22 | appendObject | `vector.addElement(...)` (byte[]/int[]/long[]) |
| 23 | appendBytes | `vector.addElement(Z[idx])` |
| 24 | skipIfZero | if `R[check] == 0`: skip N commands |
| 25 | System.gc | force GC (heap layout) |
| 26 | alias | `O[dst] = O[src]` |
| 27 | setObj | `O[dst] = arg` |
| 28 | wrap | `O[dst] = new Object[]{O[src]}` |
| 29 | spawnThread | `new Thread(() -> M[m].invoke(...)).start()` |
| 30 | getMethodRef | `O[dst] = M[src]` |

**Design notes:**
- Opcodes 3, 4 (`SLEEP`, `SPIN`) are race-window timing controls — critical for
  TOCTOU exploitation
- Opcodes 7–14, 26–30 are the reflection bridge (the "sandbox escape" surface)
- Opcode 8 (`loadViaLoader`) has a `DVBClassLoader` fallback — loads `Hx1400`
  from the disc root when normal classloading fails
- Opcode 25 (`System.gc`) forces heap layout before the AWT leak

---

## 7. The 41 Command Scripts

Each `cmd-NNNN.bin` is a VM script. `NNNN` matches the shape number in
`schedule.bin`. The 40-step progression:

```
step  0: shape  2 →  4      cmd-0002   load J196rR (AWT leak)
step  1: shape  4 →  5      cmd-0004   noop
step  2: shape  5 →  6      cmd-0005   noop
step  3: shape  6 →  7      cmd-0006   noop
step  4: shape  7 →  8      cmd-0007   noop
step  5: shape  8 →  9      cmd-0008   noop
step  6: shape  9 → 10      cmd-0009   noop
step  7: shape 10 → 11      cmd-0010   get sun.misc.Unsafe
step  8: shape 11 → 12      cmd-0011   load Boot, ClassLoader$NativeLibrary
step  9: shape 12 → 14      cmd-0012   load AXZXkk (JNI race)
step 10: shape 14 → 15      cmd-0014   noop
step 11: shape 15 → 16      cmd-0015   noop
step 12: shape 16 → 17      cmd-0016   noop
step 13: shape 17 → 18      cmd-0017   noop
step 14: shape 18 → 19      cmd-0018   noop
step 15: shape 19 → 20      cmd-0019   noop
step 16: shape 20 → 21      cmd-0020   noop
step 17: shape 21 → 22      cmd-0021   noop
step 18: shape 22 → 24      cmd-0022   load EkIKZR (Unsafe wrapper)
step 19: shape 24 → 25      cmd-0024   sysctlbyname lookup
step 20: shape 25 → 26      cmd-0025   noop
step 21: shape 26 → 27      cmd-0026   noop
step 22: shape 27 → 28      cmd-0027   noop
step 23: shape 28 → 29      cmd-0028   noop (largest, 3242 B — likely large patch)
step 24: shape 29 → 30      cmd-0029   zero Boot.CKB, Boot.SK (crypto disabled)
step 25: shape 30 → 31      cmd-0030   get Boot.KSB
step 26: shape 31 → 32      cmd-0031   zero Boot.KSB
step 27: shape 32 → 34      cmd-0032   load Hx1400, set HENURL = SENTINEL (★demo)
step 28: shape 34 → 35      cmd-0034   newArray P[31] PG[24] PGL[24] PGN[24] PGW[24]
step 29: shape 35 → 36      cmd-0035   populate P[] + kernel patch #1
step 30: shape 36 → 37      cmd-0036   populate P[] + kernel patch #2
step 31: shape 37 → 38      cmd-0037   kernel patch #3
step 32: shape 38 → 39      cmd-0038   kernel patch #4
step 33: shape 39 → 40      cmd-0039   kernel patch #5
step 34: shape 40 → 41      cmd-0040   kernel patch #6
step 35: shape 41 → 42      cmd-0041   kernel patch #7
step 36: shape 42 → 43      cmd-0042   assign P[]/PG/PGL/PGN/PGW/PKB to Hx1400 (★)
step 37: shape 43 → 44      cmd-0043   spawnThread(Hx1400.run()) (★ EXPLOIT FIRES)
step 38: shape 44 → 46      cmd-0044   sleep (wait for Hx1400 return)
step 39: shape 46 → 65535   cmd-0046   print, halt
```

**5 phantom shapes:** 03, 13, 23, 33, 45 — no command file. These are
"absent" states the schedule advances past (like `cd .` — a no-op transition).

**Note:** Scripts marked "noop" have zero commands but non-zero length — the
first two bytes are `00 00` (command count = 0). They exist so the schedule
never has to jump over a missing shape.

### The critical scripts

**`cmd-0032.bin`** — sets HENURL to the sentinel (the demo marker):
```
op 5:  setString(S[44], "HENURL")
op 11: getField(M[40], O[30], "HENURL")     ← class = Hx1400
op 13: setFieldValue(M[40], null, S[45])    ← S[45] = "file:/__bonk___offline_missing__/hen.bin"
```

**`cmd-0042.bin`** — the P[] assigner:
```
op 5:  setString(S[38], "P")
op 11: getField(M[50], O[30], "P")
op 13: setFieldValue(M[50], null, O[40])    ← O[40] is long[31]
... same for PG, PGL, PGN, PGW, PKB
```

**`cmd-0043.bin`** — the trigger:
```
op 5:  setString(S[46], "run")
op 10: getMethod(M[41], cls=30, "run", [])
op 5:  setString(S[43], "LOGS")
op 10: getMethod(M[44], cls=30, "LOGS", [])
op 29: spawnThread(M[41], args=2)
```

**Key detail:** `cls=30` is `O[30]` — the `Hx1400` instance created by
`cmd-0032`. This is how we know `cmd-0043` targets `Hx1400.run()` and **not**
`Boot.run()`.

---

## 8. The 7 Metadata Files

All share a common header:

```
u32  magic      ("FMx1" — 4 ASCII chars)
u16  version
u16  count
...  entries
```

| File | Magic | Size | Count | Content |
|---|---|---|---|---|
| `template-hashes.bin` | FMT1 | 1528 B | 22 sections | hex strings (PKB, kexec stub) |
| `marker-sites.bin` | FMM1 | 40 B | 4 | `(shape_id, marker_id, offset)` |
| `write-sites.bin` | FMW1 | 656 B | 81 | `(shape_id, marker_id, offset)` |
| `shapes.bin` | FMS1 | 794 B | 39 | UI shape definitions |
| `guards.bin` | FMG1 | 184 B | 10 | kernel syscall-stub sigs + 5 hashes |
| `rules.bin` | FMR1 | 5338 B | 205 | UI rendering rules |
| `schedule.bin` | FMC1 | 168 B | 40 | `(u16 from, u16 to)` shape transitions |

**`guards.bin` decoded:** Each 16-byte entry is a kernel code signature:

```
48 C7 C0 XX XX XX XX 49 89 CA 0F 05
└─────────┬──────────┘ └──┬──┘ └──┬─┘
  mov rax, syscall_N   mov r10,rcx  syscall
```

`Hx1400.chk()` (line 1881) verifies these against live kernel memory before
applying patches. This is how the exploit refuses to run if the kernel is
already HEN-patched or has been modified.

**`schedule.bin` decoded:** 40 entries of `(u16 from, u16 to)` — the shape
progression shown in §7.

**These are `OfflineRenderer`'s inputs** (for progress display) **and
`Hx1400`'s pre-exploit verification inputs** (guards). They do **not** contain
the `P[]` table — that's built in the VM scripts.

---

## 9. The `P[]` Table — 31 FW-14.00 Values

`Hx1400.P` is a `long[31]` populated by the VM scripts before the exploit
fires. Layout:

| Slot | Field | Meaning | Value |
|---|---|---|---|
| 0 | SE11 | sysent[11] kernel address | runtime (kbase + off) |
| 1 | KEXECG | kexec gadget address | runtime |
| 2 | HEN_SIZE | payload size | **562,240** |
| 3 | HEN_ENTRY | payload entry | **0x4AAB** |
| 4 | OFF_FIXES | config byte offset in hen.bin | 0-562239 |
| 5 | OFF_MMAP | config byte offset | 0-562239 |
| 6 | OFF_BLOCKUPD | config byte offset | 0-562239 |
| 7 | OFF_ASLR | config byte offset | 0-562239 |
| 8 | OFF_NOBD | config byte offset | 0-562239 |
| 9 | OFF_SKIP | config byte offset | 0-562239 |
| 10 | CFG_UPLOAD | upload_prx config val | **52,845** (0xCE6D) |
| 11 | CFG_PLUGINS | enable_plugins config val | **53,310** (0xD03E) |
| 12 | ANCHOR | fd anchor marker | runtime |
| 13 | R_SLOT | sysfilt_ops registry slot | runtime |
| 14 | R_OPS | sysfilt_ops value | runtime |
| 15 | R_RET0 | ROP gadget: `xor eax,eax; ret` | kbase + off |
| 16 | R_TOUCH | ROP gadget: page-touch | kbase + off |
| 17 | R_POPRSP | ROP gadget: `pop rsp; ret` (pivot) | kbase + off |
| 18 | R_SAVE | ROP gadget: save regs | kbase + off |
| 19 | R_REST | ROP gadget: restore regs | kbase + off |
| 20 | R_CONT | ROP gadget: continuation | kbase + off |
| 21 | R_PRAX | ROP gadget: `pop rax; ret` | kbase + **117,253** (0x1C9C5) |
| 22 | R_PRDX | ROP gadget: `pop rdx; ret` | kbase + **1,591,986** (0x1855F2) |
| 23 | R_PRDI | ROP gadget: `pop rdi; ret` | kbase + off |
| 24 | R_UCRED | get thread's ucred | kbase + off |
| 25 | R_RAXRCX | ROP gadget: `mov rax, rcx; ret` | kbase + off |
| 26 | R_LDRAX | ROP gadget: `mov rax, [rax]; ret` | kbase + off |
| 27 | R_ADDRAX | ROP gadget: `add rax, rdx; ret` | kbase + off |
| 28 | R_STRAXRDX | ROP gadget: `mov [rax], rdx; ret` | kbase + off |
| 29 | R_STRDIRAX | ROP gadget: `mov [rax], rdi; ret` | kbase + off |
| 30 | R_UIDPAIR | write uid pair | kbase + off |

**Verified values** from `cmd-0035`/`cmd-0036` partial array decode:
`HEN_SIZE = 562240`, `CFG_UPLOAD = 52845`, `CFG_PLUGINS = 53310`,
`R_PRAX = 117253`, `R_PRDX = 1591986`.

### Source chain

```
cmd-0032.bin  → O[30] = new Hx1400(), HENURL = sentinel
cmd-0034.bin  → O[40]=long[31], O[41]=long[24], O[42..44]=int[24]
cmd-0035..41  → populate O[40..44] slots + kernel patch hex strings
cmd-0042.bin  → assign O[40..44] to Hx1400.P/PG/PGL/PGN/PGW, O[39] to PKB
cmd-0043.bin  → spawnThread(Hx1400.run())
```

`PG[]` (24) holds guard addresses. `PGL[]` (24) holds guard lengths. `PGN[]`
(24) holds guard "now-bytes". `PGW[]` (24) holds guard "want-bytes".

**The `P[]` design is the fork's core innovation:** the hash-frozen class
references firmware-specific offsets that live in unhashed script files.
Update the scripts → the same class targets a different firmware.

---

## 10. Tier 3 — The Kernel Exploit (Hx1400.run)

`Hx1400.run()` at line 305 is a one-line wrapper:

```java
public void run() {
    result = this.probe();
}
```

Everything is inside `probe()` — a ~1,170-line method that orchestrates the
entire chain.

### Phase-by-phase breakdown

| Phase | Line | Action |
|---|---|---|
| **A** | ~340 | Leak kernel base via `sysctl(KERN_PROC)` + `J196rR` heap leak |
| **B0** | ~380 | Groom `/dev/dce` file descriptors (open hundreds) |
| **B1** | ~405 | `fstat` anchor fd → leak `st_ino` → close matched fds |
| **C** | ~521 | **6-round kqueue UAF sweep** — the exploit trigger |
| **D** | ~552 | `mmap(0x940900000)` — create fake kqueue |
| **E** | ~980 | Build fake `rb_node` tree |
| **F** | ~1000 | Hijack `kq->kq_ops` → fake filterops → `f_touch = R_POPRSP` |
| **F1** | ~1017 | Build ROP chain in `gSTACK` |
| **G** | ~1035 | Install `safeDown()` teardown |
| **H** | ~1058 | Fire `kevent(-23, EV_ADD)` → **ROP executes → uid=0** |
| **H** | ~1086 | Patch `sysent[11].sy_call` with decoded `BLOB1400` (kexec) |
| **H3** | ~1121 | `jitshm(589824, PROT_RWX)` → map at `0x950000000` |
| **H4** | ~1317 | **Fetch hen.bin — FAILS at sentinel URL** |
| **H5** | ~1329 | Verify head=0xE9, entry=0x4AAB (never reached) |
| **H5b** | ~1353 | Patch 8 config bytes in-image (never reached) |
| **H6** | ~1374 | Resolve `pthread_create` (never reached) |
| **H7** | ~1384 | `pthread_create(hen.bin_entry)` (never reached) |
| **H8** | ~1430 | Teardown (never reached) |

### Phase C detail — the UAF trigger

```java
for (n12 = 1; n12 <= 6 && n32 < 0 && n35 < 32; ++n12) {
    // 1. Close all spray fds
    for (n37 = 0; n37 < n30; ++n37) {
        this.ac(unsafe, l47, nArray7[n37], 41L, 51L, 0L, 0L, 0L);
    }
    // 2. Reopen with kqueue spray
    for (n37 = 0; n37 < n30; ++n37) {
        this.ac(unsafe, l47, nArray7[n37], 41L, 51L, l74, 248L, 0L);
    }
    // 3. Fire kevent on target kqueue (triggers UAF)
    long l105 = Hx1400.sx(this.ac(unsafe, l52, l8, l77, 1L, 0L, 0L, 0L));
    // 4. Check for marker 0x43434343/0x54545454 in reused slot
    if (unsafe.getInt(l75 + 8L) == n39 && unsafe.getInt(l75 + 12L) == 0) {
        // realloc'd slot — exploit has reclaimed the freed kqueue
    }
}
```

**The race window** between step 2 (reopen) and step 3 (kevent fire) is where
the UAF happens. If the kernel frees the kqueue between those calls, and our
`/dev/dce` fd reclaims the memory, we control the `kq_ops` pointer.

### Phase H detail — the privesc

```java
boolean bl8 = this.kgo(unsafe, l52, l77, l6, n41);
// ...
long l123 = l122 != 0L ? Hx1400.sx(this.ac(unsafe, l122, 0L, 0L, 0L, 0L, 0L, 0L)) : -1L;
this.lg(unsafe, "F entry1 mark=" + bl8 + "  *** uid=" + l123 + " ***");
if (!bl8 || l123 != 0L) {
    return this.fin(unsafe, "entry1 mark=" + bl8 + " uid=" + l123);
}
```

`kgo()` fires `kevent(-23, EV_ADD)` which invokes the hijacked `f_touch`
pointer. That pivots to `R_POPRSP`, which starts the ROP chain. The chain
writes 0 to `ucred->cr_uid`, `cr_ruid`, `cr_svuid` via `R_UIDPAIR`.

After this, `l123 == 0` means `uid == 0` — **the process is now root**.

### Phase H4 detail — the demo build failure

```java
String string4 = HENURL;
if (string4 == null) {
    string4 = HEN_URL;
}
inputStream = new URL(string4).openStream();   // ← FileNotFoundException
// ...
if (l150 != (long)HEN_SIZE) {
    return this.fin(unsafe, "hen.bin is " + l150 + " B, expected " + HEN_SIZE);
}
```

Both `HENURL` (set by `cmd-0032`) and `HEN_URL` (static fallback) point to
`file:/__bonk___offline_missing__/hen.bin`. That path doesn't exist.
`openStream()` throws. `l150` stays 0. `0 != 562240` → abort.

**The kernel is now half-patched.** `sysent[11]` is kexec, `uid=0` was achieved,
but no payload is loaded. The `NOTICE.txt` warning about cold-reboot applies.

### The ROP gadgets

All 16 gadgets are documented in `P[]`. The critical ones:

- **`R_POPRSP`** (slot 17) — `pop rsp; ret` — the pivot that starts the chain
- **`R_PRAX`** (slot 21) — `pop rax; ret` — loads addresses
- **`R_PRDX`** (slot 22) — `pop rdx; ret` — loads values
- **`R_PRDI`** (slot 23) — `pop rdi; ret` — loads arguments
- **`R_UCRED`** (slot 24) — get current thread's ucred pointer
- **`R_UIDPAIR`** (slot 30) — write uid pair (the actual privesc)
- **`R_STRDIRAX`** (slot 29) — `mov [rax], rdi; ret` — kernel write
- **`R_STRAXRDX`** (slot 28) — `mov [rax], rdx; ret` — kernel write

The ROP chain is built via `w8()`:

```java
int w8(Unsafe unsafe, long l, int n, long l2) {
    unsafe.putLong(l + (long)n * 8L, l2);
    return n + 1;
}
```

Each `w8` call appends one qword. The chain is structured:

```
kb0()                          — prologue (R_PRDI, gCTX, R_SAVE)
[user ROP setup]
kb1()                          — epilogue (marker write, context restore)
```

`kb1()` writes a magic value (`5570198814713989681L = 0x4D4D524F4F544A31 =
"MMROOTJ1"` in ASCII) to `gMARK`. After the ROP chain runs, `kgo()` verifies:

```java
return unsafe.getLong(this.gMARK) == 5570198814713989681L;
```

If the marker matches, ROP executed successfully. If not, the ROP chain was
broken somewhere (wrong gadget offset, wrong stack setup, etc.).

---

## 11. The hen.bin Payload

**Out of scope for deep analysis** — this is a Scene-Collective HEN 2.2.0
release, openly available on GitHub. But characteristics:

| Field | Value |
|---|---|
| Size | 562,240 bytes |
| SHA-256 | `29EC095FBEDE651E29954DE43411599093138791E62105AAEE6FDCED58EDEC06` |
| Format | Raw x86-64, **no ELF header**, **no encryption** |
| Header | `E9 A6 4A 00 00` — 5-byte `jmp rel32` to entry at `0x4AAB` |
| Entry | `0x4AAB` — normal `endbr64; push r15..rbx; sub rsp, 0xF98` prologue |
| Data region | `0x05`–`0x4AAB` (19,110 bytes), entropy 6.38 (plaintext tables) |
| Code region | `0x4AAB`–end (543,125 bytes) |

### Strings found

Via `capstone` disassembly + `Select-String`:

- `libkernel`, `libSceLibcInternal`
- `sceKernelSendNotificationRequest`, `sceKernelLoadStartModule`, `sceKernelDlsym`
- `syscall`, `mmap`, `jailbreak`, `setuid`, `proc`, `error`, `fail`

### Config keys (from hen.ini template)

14 keys: `config_version`, `exploit_fixes`, `mmap_patches`, `block_updates`,
`unmount_update`, `disable_aslr`, `nobd_patches`, `skip_patches`, `genuine_cex`,
`np_patch_check`, `np_regmgr_hook`, `upload_prx`, `enable_plugins`, `target_id`.

### Build metadata

- Version: **2.2.0 BETA**
- Self-identifier: **"519072 defer-shellui build"**
- Source files referenced: `source/common.c`, `source/config.c`,
  `source/kpayloads.c`, `source/main.c`

**The payload is intentionally not analyzed further** — it's a complete,
open-source project at `github.com/Scene-Collective/ps4-hen`. Reversing it
would produce a worse version of what's already published.

---

## 12. Security Analysis — The Phone-Home Question

The strings `tester.f5rvoice.com` and `1992` appear in `Boot.java:947` and
`Cfg.java:7-8`. `NOTICE.txt` claims "no HTTP server is needed." **Both are
true.** Here's the 4-layer proof:

### Layer 1 — unreachable from InitXlet

`InitXlet` calls only `Boot.exec(cmd-NNNN.bin)` at lines **619** and **635**.
Never `Boot.run()`. Grep of the full JAR:

```
InitXlet:580  Boot.EP = discRoot
InitXlet:581  Boot.SID = "offline"
InitXlet:591  Boot.PAR = clazz.getClassLoader()
InitXlet:592  Boot.SK = null
InitXlet:619  Boot.exec(byArray4)
InitXlet:635  Boot.exec(byArray4)
```

**No `Boot.run()` call.**

### Layer 2 — unreachable from any command script

`cmd-0043.bin` is the only script referencing `"run"` as a method name. Full
decode:

```
op 5:  setString(S[46], "run")
op 10: getMethod(M[41], cls=30, "run", [])
op 5:  setString(S[43], "LOGS")
op 10: getMethod(M[44], cls=30, "LOGS", [])
op 29: spawnThread(M[41], args=2)
```

`cls=30` is `O[30]` — the `Hx1400` instance created by `cmd-0032`. So this
calls `Hx1400.run()`, **not** `Boot.run()`.

### Layer 3 — malformed if somehow reached

`Boot.io()` at line 169:

```java
httpURLConnection = (HttpURLConnection) new URL(EP + string).openConnection();
```

`InitXlet:580` sets `Boot.EP = discRoot`. `discRoot` is a `file:` URL.
`new URL("file:...").openConnection()` returns a `FileURLConnection`, not an
`HttpURLConnection`. **`ClassCastException` before any network call.**

### Layer 4 — BD-J permission denies

`bluray.InitXlet.perm`:

```xml
<permissionrequestfile orgid="0x56789abc" appid="0x4000">
  <applifecyclecontrol value="true"/>
  <bd-bindingunitarea value="true"/>
  <bd-vfs value="true"/>
  <file value="true"/>
  <servicesel value="true"/>
  <userpreferences read="true" write="true"/>
</permissionrequestfile>
```

**No `Connector.http`. No `Connector.socket`. No `Connector.ssl`.**
Any `http://` `openConnection()` throws `SecurityException`.

### Conclusion

`tester.f5rvoice.com:1992` is **vestigial upstream BD-JB code** preserved in
this fork for one specific reason: **removing it would change `Boot.class`'s
SHA-256**, which would break the JAR signature and prevent the exploit from
loading.

The exploit authors left dead code in place to preserve the hash. **No network
traffic occurs during normal operation. No network traffic CAN occur even if
the dead code were reached.**

---

## 13. Reproducibility and Artifact Index

Every claim traces to a raw artifact in `analysis/`.

### Primary artifacts

| Artifact | Proves |
|---|---|
| `analysis/10-hen-header.txt` | hen.bin size, jmp target, entry point |
| `analysis/13-hen-stats.txt` | entropy + region sizes |
| `analysis/36-bootexec-opcodes.txt` | 30-opcode VM list |
| `analysis/41-template-strings.txt` | string scan of all cmd-*.bin |
| `analysis/62-hen-strings-all.txt` | full hen.bin strings dump |
| `analysis/63-hen-rodata-zone.txt` | hen.bin rodata section |
| `analysis/95-cmd-decoder.txt` | full VM decode of every command script |
| `analysis/96-cmd43-and-array-dump.txt` | P[] partial values |
| `analysis/98-FINAL-COMPLETE.txt` | executive summary |

### Verification tools

```bash
# Verify all hashes
powershell tools/verify_hashes.ps1

# Decode a command script
python tools/decode_cmd.py --file bd-j-hen/assets/bonk/local/templates/cmd-0043.bin

# Disassemble hen.bin
python tools/disasm_hen.py
```

### Rebuilding the P[] table

Run `decode_cmd.py` on `cmd-0035.bin` and `cmd-0036.bin`. The `arraySet`
opcodes reveal every populated P[] value. Cross-check against the table in §9.

---

## 14. Conclusion

`hen.iso` is a **complete, working kernel exploit demo** that proves the
Bonk HEN 14.00 chain can achieve uid=0 on PS4 firmware 14.00 via Blu-ray
disc delivery.

It is **not a working HEN**. The payload delivery is deliberately broken at
phase H4 — `HENURL` points to a sentinel path that cannot be opened. The
kernel is patched before the failure, requiring cold reboot.

### What makes this analysis useful

1. **Complete chain documentation** — every phase, every line, every constant
2. **Architectural innovation captured** — the P[] design (hash-frozen class +
   unhashed script) is a novel technique
3. **Byte-level verification** — every claim traceable to a raw artifact
4. **Demo-build pattern** — teaches how to spot fake "jailbreak" images
5. **Transferable technique** — the BD-J VM approach could be reimplemented
   for other targets if the kernel bug existed

### What's genuinely new here

- The full `P[]` mapping with 5 verified values
- The complete `cmd-*.bin` script decode
- The 4-layer phone-home proof
- The demo-build evidence chain (line-by-line)
- The VM opcode semantics from decompiled bytecode

### What's not new

- The exploit technique itself (public via BD-JB, GoldHEN, etc.)
- The Hen payload (open source at Scene-Collective)
- The kqueue UAF class (documented in PS4 homebrew community)

**This document is a research artifact, not a discovery.** Its value is in the
depth and verifiability — not in inventing anything new.

---

## Appendix A — File hashes (verify your extraction)

```
39DA48EA6711795850D3062B4BA85A650D4B6289E6682350843DB8E0F0D141C3  hen.iso
29EC095FBEDE651E29954DE43411599093138791E62105AAEE6FDCED58EDEC06  hen.bin
f59953027867d15d1434179991feeeb94bf274422f742e739067fd65400a264d  J196rR.class
2362afc9efa07a688169b9121d9e5f8bdacbead58f87cafd23fdddffea58b429  AXZXkk.class
df30bfbd25c3cdbe67965d907d575efb562738cf29c258895a698ff2ef525137  EkIKZR.class
e597710078e9afab5b24f4c22ea31fe7e5a5643b602b06c2866273b61c9c57e9  Hx1400.class
```

## Appendix B — Critical line numbers (in reconstructed .java files)

Note: these include the 20-line "RECONSTRUCTED SOURCE" header added by us.
Original bytecode offsets may differ.

| Location | Line | Content |
|---|---|---|
| `bd-j/InitXlet.java:580` | 580 | `Boot.EP = discRoot` |
| `bd-j/InitXlet.java:619` | 619 | `Boot.exec(cmd-0002.bin)` |
| `bd-j/InitXlet.java:635` | 635 | `Boot.exec(cmd-0043.bin)` |
| `bd-j/Boot.java:381` | 381 | `byte[] exec(...)` — the VM |
| `bd-j/Boot.java:966` | 966 | `public static String run()` — dead |
| `bd-j/Boot.java:967` | 967 | `EP = "http://..."` — dead assignment |
| `offline/Hx1400.java:305` | 305 | `public void run()` — entry |
| `offline/Hx1400.java:1566` | 1566 | `kgo()` — ROP fire |
| `offline/Hx1400.java:1581` | 1581 | `kread()` — kernel read |
| `offline/Hx1400.java:1599` | 1599 | `kwrite()` — kernel write |
| `offline/Hx1400.java:1317` | 1317 | H4 fetch — `String string4 = HENURL` |
| `offline/Hx1400.java:1346` | 1346 | H4 abort — `if (l150 != HEN_SIZE)` |

## Appendix C — Glossary

| Term | Meaning |
|---|---|
| **BD-J** | Blu-ray Disc Java — JVM-based app platform on Blu-ray players |
| **Xlet** | A Java app on BD-J (analogous to Applet) |
| **DVB** | Digital Video Broadcasting — the standard BD-J is built on |
| **HEN** | Homebrew ENabler — kernel payload enabling unsigned code on PS4 |
| **kqueue / kevent** | FreeBSD's event notification subsystem |
| **ucred** | FreeBSD per-process credentials |
| **sysent** | FreeBSD syscall table |
| **jitshm** | PS4-specific syscall (533) — create executable memory |
| **kexec** | Kernel exec — replace running kernel |
| **Unsafe** | `sun.misc.Unsafe` — JVM internal for direct memory access |
| **TOCTOU** | Time-of-check-to-time-of-use race condition |
| **UAF** | Use-after-free |
| **ROP** | Return-oriented programming |
| **KVA** | Kernel Virtual Address |
| **KASLR** | Kernel address space layout randomization |
