# METADATA_FORMATS

Reference for the 7 metadata files in bonk/local/.
Full detail: README.md section 10.

Common header: u32 magic (FMx1), u16 version, u16 count, entries.

template-hashes.bin  FMT1  1528 B  22 hex sections, PKB (kexec stub)
marker-sites.bin     FMM1    40 B  4 entries (shape, marker, offset)
write-sites.bin      FMW1   656 B  81 entries (shape, marker, offset)
shapes.bin           FMS1   794 B  39 shape definitions
guards.bin           FMG1   184 B  10 syscall-stub sigs + 5 hashes
rules.bin            FMR1  5338 B  205 rules
schedule.bin         FMC1   168 B  40 shape transitions

guards.bin entries are 16-byte kernel syscall stubs:
  48 C7 C0 XX XX XX XX 49 89 CA 0F 05
  = mov rax, syscall_N ; syscall
Verified by Hx1400.chk() against live kernel memory.

schedule.bin entries are (u16 from, u16 to) shape transitions.
