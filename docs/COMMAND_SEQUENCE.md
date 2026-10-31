# COMMAND_SEQUENCE

The 40 cmd-NNNN.bin scripts and the 40-step shape schedule.
Full detail: README.md section 9.

Each cmd-NNNN.bin is a VM script. NNNN = shape number.

Steps 0-9 (early setup):
  02->04 cmd-0002 load J196rR
  04->09 cmd-0004..0008 noop
  09->10 cmd-0009 noop
  10->11 cmd-0010 get Unsafe
  11->12 cmd-0011 load Boot + NativeLibrary

Steps 9-19 (loader loading):
  12->14 cmd-0012 load AXZXkk
  14->22 cmd-0014..0021 noop
  22->24 cmd-0022 load EkIKZR
  24->25 cmd-0024 sysctlbyname

Steps 20-29 (config + Hx1400 init):
  25->29 cmd-0025..0028 noop (0028 largest, 3242 B)
  29->32 cmd-0029..0031 zero Boot.CKB/SK/KSB
  32->34 cmd-0032 load Hx1400, HENURL=sentinel (DEMO)
  34->35 cmd-0034 newArray P[31] PG[24] PGL/PGN/PGW[24]

Steps 29-38 (populate P[] + patches):
  35->36 cmd-0035 populate + patch #1
  36->37 cmd-0036 populate + patch #2
  37->42 cmd-0037..0041 patch #3-7

Steps 36-39 (fire):
  42->43 cmd-0042 assign P[] to Hx1400 (P SETTER)
  43->44 cmd-0043 spawn Hx1400.run() (EXPLOIT FIRES)
  44->46 cmd-0044 sleep
  46->end cmd-0046 print, halt

Phantom shapes: 03, 13, 23, 33, 45 have no cmd file.
