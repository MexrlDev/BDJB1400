/*
 * ═══════════════════════════════════════════════════════════════════════
 *  RECONSTRUCTED SOURCE — NOT ORIGINAL AUTHORED CODE
 * ═══════════════════════════════════════════════════════════════════════
 *
 *  Class:         AXZXkk
 *  SHA-256:       2362afc9efa07a688169b9121d9e5f8bdacbead58f87cafd23fdddffea58b429
 *  Source:        x/offline/AXZXkk.class
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
import sun.misc.Unsafe;

public class AXZXkk {
    public static final int[] kYyoIe = new int[]{1};
    public static final int[] dRP05I = new int[]{1};
    public Object C5Od1h;
    public Object jPdWOk;
    public long[] IP0n2j;

    private native long vrzs27(long var1, int[] var3);

    public long QEBB8A(long l) {
        return this.vrzs27(l, kYyoIe);
    }

    public long Cn90dc(long l) {
        return this.vrzs27(l, dRP05I);
    }

    public long DX9qSL(long l, long l2, long l3, long l4, long l5, long l6, long l7) {
        try {
            Unsafe unsafe = (Unsafe)this.C5Od1h;
            long[] lArray = this.IP0n2j;
            long l8 = lArray[0];
            long l9 = lArray[1];
            long l10 = lArray[2];
            long l11 = lArray[3];
            long l12 = lArray[4];
            long l13 = lArray[5];
            long l14 = lArray[6];
            long l15 = lArray[7];
            long l16 = lArray[8];
            if (l < lArray[14]) {
                return -4096L;
            }
            boolean bl = this.jPdWOk != null;
            long l17 = 0L;
            for (int i = 0; i < 2; ++i) {
                unsafe.putLong(l8 + lArray[9], l9);
                unsafe.putLong(l8 + lArray[10], l9);
                unsafe.putLong(l8 + lArray[11], 0L);
                unsafe.putLong(l9 + lArray[12], i == 0 ? l11 : l12);
                unsafe.putLong(l13 + lArray[13], l14);
                l17 = bl ? this.Cn90dc(l10) : this.QEBB8A(l10);
                unsafe.putLong(l13 + lArray[13], l15);
                if (l17 != 0L && l17 > lArray[14]) {
                    unsafe.putLong(l17 + lArray[15], l16);
                    unsafe.putLong(l17 + lArray[16], 0L);
                }
                if (i != 0) continue;
                long l18 = unsafe.getLong(l8 + lArray[17]);
                long l19 = unsafe.getLong(l8 + lArray[18]);
                long l20 = unsafe.getLong(l8 + lArray[19]);
                long l21 = unsafe.getLong(l8 + lArray[20]);
                long l22 = unsafe.getLong(l8 + lArray[21]);
                long l23 = unsafe.getLong(l8 + lArray[22]);
                long l24 = unsafe.getLong(l8 + lArray[23]);
                unsafe.putLong(l8 + lArray[24], l2);
                unsafe.putLong(l8 + lArray[25], l3);
                unsafe.putLong(l8 + lArray[26], l4);
                unsafe.putLong(l8 + lArray[27], l5);
                unsafe.putLong(l8 + lArray[28], l6);
                unsafe.putLong(l8 + lArray[29], l7);
                unsafe.putLong(l8 + lArray[30], l18);
                unsafe.putLong(l8 + lArray[31], l20);
                unsafe.putLong(l8 + lArray[32], l21);
                unsafe.putLong(l8 + lArray[33], l22);
                unsafe.putLong(l8 + lArray[34], l23);
                unsafe.putLong(l8 + lArray[35], l24);
                unsafe.putLong(l8 + lArray[36], l);
                unsafe.putLong(l8 + lArray[37], l19);
            }
            if (l17 == 0L) {
                return 0L;
            }
            if (l17 < lArray[14]) {
                return l17;
            }
            return unsafe.getLong(l17);
        }
        catch (Throwable throwable) {
            return -1L;
        }
    }
}
