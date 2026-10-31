# P_ARRAY

The Hx1400.P table: 31-slot long[] with FW-14.00 kernel offsets.
Full detail: README.md section 11.

Populated by cmd-0032 through cmd-0042 (see COMMAND_SEQUENCE.md).

Slots 0-14 (kernel offsets):
  0  SE11         sysent[11] kva
  1  KEXECG       kexec gadget kva
  2  HEN_SIZE     562240
  3  HEN_ENTRY    0x4AAB
  4  OFF_FIXES    config byte offset
  5  OFF_MMAP     config byte offset
  6  OFF_BLOCKUPD config byte offset
  7  OFF_ASLR     config byte offset
  8  OFF_NOBD     config byte offset
  9  OFF_SKIP     config byte offset
  10 CFG_UPLOAD   52845
  11 CFG_PLUGINS  53310
  12 ANCHOR       fd marker
  13 R_SLOT       sysfilt_ops slot
  14 R_OPS        sysfilt_ops value

Slots 15-30 (ROP gadgets):
  15 R_RET0, 16 R_TOUCH, 17 R_POPRSP (pivot), 18 R_SAVE, 19 R_REST, 20 R_CONT
  21 R_PRAX (117253), 22 R_PRDX (1591986), 23 R_PRDI, 24 R_UCRED
  25 R_RAXRCX, 26 R_LDRAX, 27 R_ADDRAX
  28 R_STRAXRDX (write), 29 R_STRDIRAX (write), 30 R_UIDPAIR

Runtime slots = kbase + offset, resolved at runtime.
