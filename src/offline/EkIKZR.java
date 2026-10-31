/*
 * ═══════════════════════════════════════════════════════════════════════
 *  RECONSTRUCTED SOURCE — NOT ORIGINAL AUTHORED CODE
 * ═══════════════════════════════════════════════════════════════════════
 *
 *  Class:         EkIKZR
 *  SHA-256:       df30bfbd25c3cdbe67965d907d575efb562738cf29c258895a698ff2ef525137
 *  Source:        x/offline/EkIKZR.class
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

public class EkIKZR {
    public Object jYjOsX;

    public long[] HF6fzp(long l, int n, int n2) {
        Unsafe unsafe = (Unsafe)this.jYjOsX;
        long[] lArray = new long[n];
        for (int i = 0; i < n; ++i) {
            lArray[i] = unsafe.getLong(l + (long)i * (long)n2);
        }
        return lArray;
    }

    public void JtAXmm(long l, long[] lArray, int n) {
        Unsafe unsafe = (Unsafe)this.jYjOsX;
        for (int i = 0; i < lArray.length; ++i) {
            unsafe.putLong(l + (long)i * (long)n, lArray[i]);
        }
    }

    public byte[] OPitzi(long l, int n) {
        Unsafe unsafe = (Unsafe)this.jYjOsX;
        byte[] byArray = new byte[n];
        for (int i = 0; i < n; ++i) {
            byArray[i] = unsafe.getByte(l + (long)i);
        }
        return byArray;
    }

    public void R0Bep2(long l, byte[] byArray) {
        Unsafe unsafe = (Unsafe)this.jYjOsX;
        for (int i = 0; i < byArray.length; ++i) {
            unsafe.putByte(l + (long)i, byArray[i]);
        }
    }
}
