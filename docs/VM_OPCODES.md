# VM_OPCODES

Reference for the 30-opcode Boot.exec bytecode VM.
Full detail: README.md section 8.

Wire format: u16 count, then count x (u8 opcode + args). Big-endian.

1  HALT         readLong then halt
2  PRINT_HEX    print hex of readLong
3  SLEEP        Thread.sleep(ms), ignore second arg
4  SPIN         busy-loop N iterations
5  setString    S[dst] = new String(utf8)
6  setBytes     Z[dst] = bytes
7  loadClass    O[dst] = Class.forName(S[name])
8  loadViaLoader DVBClassLoader fallback
9  newInstance  O[dst] = ctor(args).newInstance()
10 getMethod    M[dst] = getDeclaredMethod
11 getField     M[dst] = getDeclaredField
12 getFieldValue M[field].get(obj)
13 setFieldValue M[field].set(obj, arg)
14 invokeMethod M[method].invoke(obj, args)
15 newArray     Array.newInstance(cls, size)
16 arrayGet     Array.get(arr, idx)
17 arraySet     Array.set(arr, idx, val)
18 arrayLength  Array.getLength(arr)
19 numFromArg   R[dst] = num(arg)
20 arith        R[dst] = a op b (12 subops)
21 outputByte   writeLong to response buffer
22 appendObject append byte[]/int[]/long[]
23 appendBytes  append Z[idx]
24 skipIfZero   skip N commands if zero
25 System.gc    gc twice (heap layout)
26 alias        O[dst] = O[src]
27 setObj       O[dst] = arg
28 wrap         O[dst] = Object[]{O[src]}
29 spawnThread  Thread.start()
30 getMethodRef O[dst] = M[src]
