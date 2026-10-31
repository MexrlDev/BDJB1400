#!/usr/bin/env python3
"""tools/disasm_hen.py - disassemble hen.bin (needs: pip install capstone)."""
import sys, os
from capstone import Cs, CS_ARCH_X86, CS_MODE_64

def main():
    hen = os.path.join(os.path.dirname(os.path.abspath(__file__)),
                       '..', 'assets', 'bonk', 'hen.bin')
    if not os.path.exists(hen):
        print(f"hen.bin not found at {hen}")
        return 1
    with open(hen, 'rb') as f:
        data = f.read()

    jmp_rel = int.from_bytes(data[1:5], 'little', signed=True)
    entry = 5 + jmp_rel
    print(f"File: {hen} ({len(data)} bytes)")
    print(f"jmp stub: E9 {data[1]:02X} {data[2]:02X} {data[3]:02X} {data[4]:02X}")
    print(f"entry: 0x{entry:X}")
    print(f"data region: 5..0x{entry:X} ({entry-5} bytes)")
    print(f"code region: 0x{entry:X}..{len(data)} ({len(data)-entry} bytes)")
    print()

    md = Cs(CS_ARCH_X86, CS_MODE_64)
    print(f"=== First 40 instructions from entry 0x{entry:X} ===")
    for i, ins in enumerate(md.disasm(data[entry:entry+0x400], entry)):
        if i >= 40:
            break
        print(f"  0x{ins.address:08X}  {ins.bytes.hex():<20}  {ins.mnemonic:<8} {ins.op_str}")
    return 0

if __name__ == '__main__':
    sys.exit(main())