#!/usr/bin/env python3
"""tools/decode_cmd.py - decode a cmd-NNNN.bin VM script."""
import sys, os, struct

def decode(data):
    if len(data) < 2: return []
    count = struct.unpack('>H', data[0:2])[0]
    pos, ops = 2, []
    for i in range(count):
        if pos >= len(data): break
        op = data[pos]; pos += 1
        rec = {'i': i, 'op': op}
        try:
            if op in (1,2,4):
                rec['u64'] = struct.unpack('>Q', data[pos:pos+8])[0]; pos += 8
            elif op == 3:
                rec['ms'] = struct.unpack('>Q', data[pos:pos+8])[0]; pos += 8
                pos += 8
            elif op in (5,6):
                rec['idx'] = struct.unpack('>Q', data[pos:pos+8])[0]; pos += 8
                ln = struct.unpack('>I', data[pos:pos+4])[0]; pos += 4
                if op == 5:
                    rec['str'] = data[pos:pos+ln].decode('utf-8','replace')
                else:
                    rec['bytes'] = data[pos:pos+ln].hex()[:48]
                pos += ln
            elif op == 7:
                rec['dst'] = struct.unpack('>Q', data[pos:pos+8])[0]; pos += 8
                rec['str_idx'] = struct.unpack('>Q', data[pos:pos+8])[0]; pos += 8
            elif op == 8:
                pos += 24
            elif op == 9:
                pos += 16
                n_at = data[pos]; pos += 1
                pos += n_at * 8 + 1
                n_a = data[pos]; pos += 1
                pos += n_a * 9
            elif op == 10:
                rec['dst'] = struct.unpack('>Q', data[pos:pos+8])[0]; pos += 8
                rec['cls'] = struct.unpack('>Q', data[pos:pos+8])[0]; pos += 8
                rec['str_idx'] = struct.unpack('>Q', data[pos:pos+8])[0]; pos += 8
                n_at = data[pos]; pos += 1
                pos += n_at * 8
            elif op == 11:
                rec['dst'] = struct.unpack('>Q', data[pos:pos+8])[0]; pos += 8
                rec['cls'] = struct.unpack('>Q', data[pos:pos+8])[0]; pos += 8
                rec['str_idx'] = struct.unpack('>Q', data[pos:pos+8])[0]; pos += 8
            elif op == 13:
                rec['field'] = struct.unpack('>Q', data[pos:pos+8])[0]; pos += 8
                pos += 18
            elif op == 15:
                rec['dst'] = struct.unpack('>Q', data[pos:pos+8])[0]; pos += 8
                rec['cls'] = struct.unpack('>Q', data[pos:pos+8])[0]; pos += 8
                rec['size'] = struct.unpack('>Q', data[pos:pos+8])[0]; pos += 8
            elif op == 17:
                rec['arr'] = struct.unpack('>Q', data[pos:pos+8])[0]; pos += 8
                rec['elem'] = struct.unpack('>Q', data[pos:pos+8])[0]; pos += 8
                pos += 9
            elif op == 29:
                rec['method'] = struct.unpack('>Q', data[pos:pos+8])[0]; pos += 8
                n_a = data[pos]; pos += 1
                pos += n_a * 9
            else:
                rec['unknown'] = True; ops.append(rec); break
            ops.append(rec)
        except: break
    return ops

if __name__ == '__main__':
    if len(sys.argv) < 3:
        print("Usage: decode_cmd.py --file <path>"); sys.exit(1)
    path = sys.argv[-1]
    with open(path,'rb') as f: data = f.read()
    print(f"File: {path} ({len(data)} B)")
    for r in decode(data): print(r)