# BD-J HEN 14.00 — Reverse Engineering Notes

Complete analysis of `hen.iso`, a Blu-ray HEN ISO for PS4 firmware 14.00.

## Identity

| | |
|---|---|
| Fork | Bonk HEN 14.00 (Kat, Slopp, Pigeon) |
| Upstream | PS4-14.00-BD-JB (Scene-Collective) |
| Payload | Scene-Collective HEN 2.2.0 BETA |
| Target | FW 14.00 |
| ISO size | 6,225,920 bytes |
| ISO SHA-256 | `39DA48EA6711795850D3062B4BA85A650D4B6289E6682350843DB8E0F0D141C3` |

## What it does

1. Inserts as Blu-ray disc → BD-J Xlet runs automatically
2. User presses X → exploit chain begins
3. Loads four classes from disc root: `J196rR`, `AXZXkk`, `EkIKZR`, `Hx1400`
4. Sandbox escape via AWT OOB + Unsafe + JNI bridge
5. Kernel exploit via `/dev/dce` kqueue UAF + ROP-over-kevent
6. hen.bin load — `HENURL` points to sentinel
   `__bonk___offline_missing__`

## Where to start

- **[ANALYSIS.md](ANALYSIS.md)** — full 20-page deep dive
- **[src/offline/Hx1400.java](src/offline/Hx1400.java)** — the actual kernel exploit
- **[analysis/98-FINAL-COMPLETE.txt](analysis/98-FINAL-COMPLETE.txt)** — the executive summary
- **[docs/](docs/)** — VM opcodes, metadata formats, command sequence, P[] table

## Key facts

- **Entry point:** `Hx1400.java:305` — `public void run()`
- **Kernel write:** `Hx1400.java:1599` — `kwrite()`
- **ROP fire:** `Hx1400.java:1566` — `kgo()` (fires kevent -23)
- **Failure point:** `Hx1400.java:1346` — hen.bin fetch aborts
- **Payload:** `assets/bonk/hen.bin` — 562,240 B, entry `0x4AAB`

## Tools

- `tools/decode_cmd.py` — decode VM bytecode (tested on cmd-0043.bin)
- `tools/disasm_hen.py` — disassemble hen.bin (needs `pip install capstone`)
- `tools/verify_hashes.ps1` — verify all files against known hashes
- `tools/extract_iso.ps1` — extract ISO to folder (Windows)
