/*
 * ═══════════════════════════════════════════════════════════════════════
 *  RECONSTRUCTED SOURCE — NOT ORIGINAL AUTHORED CODE
 * ═══════════════════════════════════════════════════════════════════════
 *
 *  Class:         OfflineRenderer
 *  SHA-256:       f8b3e91d3f8c5a76...
 *  Source:        BDMV/JAR/00000.jar → org/bdj/OfflineRenderer.class
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
package org.bdj;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.SecureRandom;

final class OfflineRenderer {
    static final int FRAME_LIMIT = 0x1000000;

    OfflineRenderer() {
    }

    static Frame frame(byte[] byArray) throws Exception {
        if (byArray.length < 20 || byArray.length > 0x1000000) {
            throw new IOException("result frame size invalid");
        }
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(byArray));
        long l = dataInputStream.readLong();
        int n = dataInputStream.readInt();
        int n2 = dataInputStream.readInt();
        if (n2 < 0 || n2 > 4096) {
            throw new IOException("scalar count invalid");
        }
        long[] lArray = new long[n2];
        int n3 = 0;
        while (n3 < n2) {
            lArray[n3] = dataInputStream.readLong();
            ++n3;
        }
        n3 = dataInputStream.readInt();
        if (n3 < 0 || n3 > 4096) {
            throw new IOException("vector count invalid");
        }
        byte[][] byArrayArray = new byte[n3][];
        int n4 = 0;
        while (n4 < n3) {
            int n5 = dataInputStream.readInt();
            if (n5 < 0 || n5 > 0x1000000 || n5 > dataInputStream.available()) {
                throw new IOException("vector length invalid");
            }
            byArrayArray[n4] = new byte[n5];
            dataInputStream.readFully(byArrayArray[n4]);
            ++n4;
        }
        if (dataInputStream.available() != 0) {
            throw new IOException("result frame trailing bytes");
        }
        return new Frame(l, n, lArray, byArrayArray);
    }

    static Rule[] rules(byte[] byArray) throws Exception {
        DataInputStream dataInputStream = OfflineRenderer.metadata(byArray, "FMR1");
        int n = dataInputStream.readUnsignedShort();
        if (n != 205) {
            throw new IOException("rule count changed");
        }
        Rule[] ruleArray = new Rule[n];
        int n2 = 0;
        while (n2 < n) {
            ruleArray[n2] = new Rule(dataInputStream.readUnsignedShort(), dataInputStream.readUnsignedShort(), dataInputStream.readInt(), dataInputStream.readUnsignedShort(), dataInputStream.readUnsignedShort(), dataInputStream.readUnsignedShort(), dataInputStream.readUnsignedShort(), dataInputStream.readUnsignedShort(), dataInputStream.readLong());
            ++n2;
        }
        if (dataInputStream.available() != 0) {
            throw new IOException("rule trailing bytes");
        }
        return ruleArray;
    }

    static Shape[] shapes(byte[] byArray) throws Exception {
        DataInputStream dataInputStream = OfflineRenderer.metadata(byArray, "FMS1");
        int n = dataInputStream.readUnsignedShort();
        if (n < 1 || n > 47) {
            throw new IOException("shape count invalid");
        }
        Shape[] shapeArray = new Shape[n];
        int n2 = 0;
        while (n2 < n) {
            int n3 = dataInputStream.readUnsignedShort();
            long l = dataInputStream.readLong();
            int n4 = dataInputStream.readInt();
            int n5 = dataInputStream.readUnsignedShort();
            int n6 = dataInputStream.readUnsignedShort();
            if (n5 > 4096 || n6 > 4096) {
                throw new IOException("shape count invalid");
            }
            int[] nArray = new int[n6];
            int n7 = 0;
            while (n7 < n6) {
                nArray[n7] = dataInputStream.readInt();
                ++n7;
            }
            shapeArray[n2] = new Shape(n3, l, n4, n5, nArray);
            ++n2;
        }
        if (dataInputStream.available() != 0) {
            throw new IOException("shape trailing bytes");
        }
        return shapeArray;
    }

    static Guards guards(byte[] byArray) throws Exception {
        DataInputStream dataInputStream = OfflineRenderer.metadata(byArray, "FMG1");
        int n = dataInputStream.readUnsignedShort();
        if (n != 10) {
            throw new IOException("native guard count changed");
        }
        long[] lArray = new long[n];
        int n2 = 0;
        while (n2 < n) {
            lArray[n2] = dataInputStream.readLong();
            ++n2;
        }
        byte[][] byArray2 = new byte[3][32];
        int n3 = 0;
        while (n3 < byArray2.length) {
            dataInputStream.readFully(byArray2[n3]);
            ++n3;
        }
        if (dataInputStream.available() != 0) {
            throw new IOException("guard trailing bytes");
        }
        return new Guards(lArray, byArray2);
    }

    static TemplatePin[] templatePins(byte[] byArray) throws Exception {
        DataInputStream dataInputStream = OfflineRenderer.metadata(byArray, "FMT1");
        int n = dataInputStream.readUnsignedShort();
        if (n != 40) {
            throw new IOException("template count changed");
        }
        TemplatePin[] templatePinArray = new TemplatePin[n];
        int n2 = 0;
        while (n2 < n) {
            int n3 = dataInputStream.readUnsignedShort();
            int n4 = dataInputStream.readInt();
            if (n4 < 2 || n4 > 65535) {
                throw new IOException("template length invalid");
            }
            byte[] byArray2 = new byte[32];
            dataInputStream.readFully(byArray2);
            templatePinArray[n2] = new TemplatePin(n3, n4, byArray2);
            ++n2;
        }
        if (dataInputStream.available() != 0) {
            throw new IOException("template trailing bytes");
        }
        return templatePinArray;
    }

    static ScheduleEntry[] schedule(byte[] byArray) throws Exception {
        DataInputStream dataInputStream = OfflineRenderer.metadata(byArray, "FMC1");
        int n = dataInputStream.readUnsignedShort();
        if (n != 40) {
            throw new IOException("schedule count changed");
        }
        ScheduleEntry[] scheduleEntryArray = new ScheduleEntry[n];
        int n2 = 0;
        int n3 = 0;
        while (n3 < n) {
            int n4 = dataInputStream.readUnsignedShort();
            int n5 = dataInputStream.readUnsignedShort();
            if (n4 <= n2 || n5 <= n4 && n5 != 65535 || n5 == 65535 && n3 != n - 1) {
                throw new IOException("schedule ordering invalid");
            }
            scheduleEntryArray[n3] = new ScheduleEntry(n4, n5);
            n2 = n4;
            ++n3;
        }
        if (scheduleEntryArray[n - 1].command != 46 || scheduleEntryArray[n - 1].nextResultRequest != 65535 || dataInputStream.available() != 0) {
            throw new IOException("schedule terminal changed");
        }
        return scheduleEntryArray;
    }

    static MarkerPlan markerPlan(byte[] byArray) throws Exception {
        DataInputStream dataInputStream = OfflineRenderer.metadata(byArray, "FMM1");
        int n = dataInputStream.readUnsignedShort();
        if (n != 4) {
            throw new IOException("marker site count changed");
        }
        int[] nArray = new int[n];
        int n2 = 0;
        while (n2 < n) {
            int n3 = dataInputStream.readUnsignedShort();
            int n4 = dataInputStream.readUnsignedShort();
            nArray[n2] = dataInputStream.readInt();
            if (n3 != 2 || n4 != 21 + n2 || nArray[n2] < 18 || n2 > 0 && nArray[n2] <= nArray[n2 - 1]) {
                throw new IOException("marker site identity changed");
            }
            ++n2;
        }
        if (dataInputStream.available() != 0) {
            throw new IOException("marker site trailing bytes");
        }
        return new MarkerPlan(nArray);
    }

    static WriteSite[] writeSites(byte[] byArray, Rule[] ruleArray) throws Exception {
        DataInputStream dataInputStream = OfflineRenderer.metadata(byArray, "FMW1");
        int n = dataInputStream.readUnsignedShort();
        if (n != 81) {
            throw new IOException("direct-write site count changed");
        }
        WriteSite[] writeSiteArray = new WriteSite[n];
        int n2 = 0;
        while (n2 < n) {
            int n3 = dataInputStream.readUnsignedShort();
            int n4 = dataInputStream.readUnsignedShort();
            int n5 = dataInputStream.readInt();
            if (n3 < 1 || n3 > 28 || n5 < 2 || n2 > 0 && (n3 < writeSiteArray[n2 - 1].command || n3 == writeSiteArray[n2 - 1].command && n5 <= writeSiteArray[n2 - 1].offset)) {
                throw new IOException("direct-write site order changed");
            }
            int n6 = 0;
            int n7 = 0;
            while (n7 < ruleArray.length) {
                if (ruleArray[n7].command == n3 && ruleArray[n7].instruction == n4 && ruleArray[n7].offset == n5) {
                    ++n6;
                }
                ++n7;
            }
            if (n6 != 1) {
                throw new IOException("direct-write site not a relocation");
            }
            writeSiteArray[n2] = new WriteSite(n3, n4, n5);
            ++n2;
        }
        if (dataInputStream.available() != 0) {
            throw new IOException("direct-write site trailing bytes");
        }
        return writeSiteArray;
    }

    static int[] freshMarkerWords() throws IOException {
        SecureRandom secureRandom = new SecureRandom();
        int[] nArray = new int[4];
        int n = 0;
        while (n < nArray.length) {
            boolean bl;
            do {
                nArray[n] = secureRandom.nextInt();
                bl = nArray[n] != 0 && nArray[n] != -1 && nArray[n] != -2;
                int n2 = 0;
                while (n2 < n) {
                    if (nArray[n] == nArray[n2]) {
                        bl = false;
                    }
                    ++n2;
                }
            } while (!bl);
            ++n;
        }
        OfflineRenderer.validateMarkerWords(nArray);
        return nArray;
    }

    private static DataInputStream metadata(byte[] byArray, String string) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(byArray));
        int n = 0;
        while (n < 4) {
            if (dataInputStream.readUnsignedByte() != string.charAt(n)) {
                throw new IOException("metadata magic mismatch");
            }
            ++n;
        }
        if (dataInputStream.readUnsignedShort() != 1) {
            throw new IOException("metadata version unsupported");
        }
        return dataInputStream;
    }

    static byte[] render(int n, byte[] byArray, Frame[] frameArray, Rule[] ruleArray, Shape[] shapeArray, Guards guards, TemplatePin[] templatePinArray) throws Exception {
        return OfflineRenderer.renderInternal(n, byArray, frameArray, ruleArray, shapeArray, guards, templatePinArray, null, null, null, false);
    }

    static byte[] renderCandidate(int n, byte[] byArray, Frame[] frameArray, Rule[] ruleArray, Shape[] shapeArray, Guards guards, TemplatePin[] templatePinArray, MarkerPlan markerPlan, int[] nArray, WriteSite[] writeSiteArray) throws Exception {
        return OfflineRenderer.renderInternal(n, byArray, frameArray, ruleArray, shapeArray, guards, templatePinArray, markerPlan, nArray, writeSiteArray, true);
    }

    private static byte[] renderInternal(int n, byte[] byArray, Frame[] frameArray, Rule[] ruleArray, Shape[] shapeArray, Guards guards, TemplatePin[] templatePinArray, MarkerPlan markerPlan, int[] nArray, WriteSite[] writeSiteArray, boolean bl) throws Exception {
        TemplatePin templatePin = null;
        int n2 = 0;
        while (n2 < templatePinArray.length) {
            if (templatePinArray[n2].command == n) {
                templatePin = templatePinArray[n2];
            }
            ++n2;
        }
        if (templatePin == null || byArray.length != templatePin.length || !OfflineRenderer.same(OfflineRenderer.hash(byArray), templatePin.sha256)) {
            throw new IOException("template identity changed");
        }
        if (bl) {
            if (markerPlan == null) {
                throw new IOException("marker plan missing");
            }
            OfflineRenderer.validateMarkerWords(nArray);
            OfflineRenderer.verifyPriorMarkerResults(frameArray, n, nArray);
        }
        OfflineRenderer.verifyShapes(frameArray, shapeArray, n);
        if (n >= 12) {
            OfflineRenderer.verifyNativeLayout(frameArray[12], guards);
        }
        if (n >= 17) {
            OfflineRenderer.verifyAliases(frameArray[16]);
        }
        if (n >= 27) {
            OfflineRenderer.verifyCodeWindows(frameArray[27], guards);
        }
        byte[] byArray2 = (byte[])byArray.clone();
        int n3 = 0;
        while (n3 < ruleArray.length) {
            Rule rule = ruleArray[n3];
            if (rule.command == n) {
                Frame frame;
                if (rule.offset < 2 || rule.offset + 8 > byArray2.length || rule.request > n || rule.request >= frameArray.length) {
                    throw new IOException("relocation site invalid");
                }
                if (bl) {
                    int n4 = 0;
                    while (n4 < 8) {
                        if (byArray2[rule.offset + n4] != 0) {
                            throw new IOException("captured relocation operand retained");
                        }
                        ++n4;
                    }
                }
                if ((frame = frameArray[rule.request]) == null) {
                    throw new IOException("relocation source missing");
                }
                long l = OfflineRenderer.value(rule, frame, frameArray);
                int n5 = 7;
                while (n5 >= 0) {
                    byArray2[rule.offset + n5] = (byte)l;
                    l >>>= 8;
                    --n5;
                }
            }
            ++n3;
        }
        if (bl && n == 2) {
            OfflineRenderer.patchMarkerWords(byArray2, markerPlan, nArray);
        }
        if (bl) {
            OfflineRenderer.verifyWriteDestinations(n, byArray2, writeSiteArray);
        }
        return byArray2;
    }

    static void verifyWriteDestinations(int n, byte[] byArray, WriteSite[] writeSiteArray) throws IOException {
        if (writeSiteArray == null || writeSiteArray.length != 81) {
            throw new IOException("direct-write guard missing");
        }
        int n2 = 0;
        while (n2 < writeSiteArray.length) {
            WriteSite writeSite = writeSiteArray[n2];
            if (writeSite.command == n) {
                if (writeSite.offset < 2 || writeSite.offset + 8 > byArray.length) {
                    throw new IOException("direct-write destination site invalid");
                }
                long l = OfflineRenderer.readLong(byArray, writeSite.offset);
                if (l < 0x100000000L || l >= 0x7FFFFFFFFFFFFFF7L) {
                    throw new IOException("direct-write destination outside pointer range");
                }
            }
            ++n2;
        }
    }

    static boolean isPendingHenResult(byte[] byArray) throws Exception {
        Frame frame = OfflineRenderer.frame(byArray);
        if (frame.status != 0L || frame.instruction != 7 || frame.scalars.length != 0 || frame.vectors.length != 2) {
            throw new IOException("Hx status frame shape changed");
        }
        byte[] byArray2 = frame.vectors[0];
        byte[] byArray3 = "not run".getBytes("US-ASCII");
        if (byArray2.length != byArray3.length) {
            return false;
        }
        int n = 0;
        while (n < byArray3.length) {
            if (byArray2[n] != byArray3[n]) {
                return false;
            }
            ++n;
        }
        if (frame.vectors[1].length > 131072) {
            throw new IOException("Hx pending log too large");
        }
        return true;
    }

    private static void validateMarkerWords(int[] nArray) throws IOException {
        if (nArray == null || nArray.length != 4) {
            throw new IOException("marker words missing");
        }
        int n = 0;
        while (n < nArray.length) {
            if (nArray[n] == 0 || nArray[n] == -1 || nArray[n] == -2) {
                throw new IOException("reserved marker value");
            }
            int n2 = 0;
            while (n2 < n) {
                if (nArray[n] == nArray[n2]) {
                    throw new IOException("duplicate marker value");
                }
                ++n2;
            }
            ++n;
        }
    }

    private static void patchMarkerWords(byte[] byArray, MarkerPlan markerPlan, int[] nArray) throws IOException {
        int n = 0;
        while (n < 4) {
            int n2 = markerPlan.offsets[n];
            if (n2 + 8 > byArray.length || byArray[n2 - 18] != 17 || byArray[n2 - 1] != 1 || OfflineRenderer.readLong(byArray, n2 - 17) != 3L || OfflineRenderer.readLong(byArray, n2 - 9) != (long)n) {
                throw new IOException("marker assignment structure changed");
            }
            int n3 = 0;
            while (n3 < 8) {
                if (byArray[n2 + n3] != 0) {
                    throw new IOException("captured marker retained");
                }
                ++n3;
            }
            long l = nArray[n];
            int n4 = 7;
            while (n4 >= 0) {
                byArray[n2 + n4] = (byte)l;
                l >>>= 8;
                --n4;
            }
            ++n;
        }
    }

    private static long readLong(byte[] byArray, int n) {
        long l = 0L;
        int n2 = 0;
        while (n2 < 8) {
            l = l << 8 | (long)byArray[n + n2] & 0xFFL;
            ++n2;
        }
        return l;
    }

    private static int readInt(byte[] byArray, int n) {
        int n2 = 0;
        int n3 = 0;
        while (n3 < 4) {
            n2 = n2 << 8 | byArray[n + n3] & 0xFF;
            ++n3;
        }
        return n2;
    }

    private static void verifyPriorMarkerResults(Frame[] frameArray, int n, int[] nArray) throws IOException {
        if (n >= 7) {
            if (frameArray.length <= 7 || frameArray[7] == null) {
                throw new IOException("marker scan result missing");
            }
            OfflineRenderer.verifyMarkerNewResult(7, frameArray[7], nArray);
        }
        if (n >= 8) {
            if (frameArray.length <= 8 || frameArray[8] == null) {
                throw new IOException("marker echo result missing");
            }
            OfflineRenderer.verifyMarkerNewResult(8, frameArray[8], nArray);
        }
    }

    static void verifyMarkerNewResult(int n, Frame frame, int[] nArray) throws IOException {
        OfflineRenderer.validateMarkerWords(nArray);
        if (n == 7) {
            if (frame.vectors.length != 1 || frame.vectors[0].length != 4) {
                throw new IOException("marker scan failed");
            }
            int n2 = OfflineRenderer.readInt(frame.vectors[0], 0);
            if (n2 < 4 || n2 > 16383) {
                throw new IOException("marker scan outside valid search range");
            }
        } else if (n == 8) {
            if (frame.vectors.length != 1 || frame.vectors[0].length != 1024) {
                throw new IOException("marker echo vector changed");
            }
            int n3 = 0;
            while (n3 < 4) {
                if (OfflineRenderer.readInt(frame.vectors[0], n3 * 4) != nArray[n3]) {
                    throw new IOException("marker echo mismatch");
                }
                ++n3;
            }
        }
    }

    private static long value(Rule rule, Frame frame, Frame[] frameArray) throws IOException {
        long l;
        if (rule.kind == 0) {
            if (rule.slot >= frame.scalars.length) {
                throw new IOException("scalar source missing");
            }
            l = frame.scalars[rule.slot];
        } else if (rule.kind == 1) {
            if (rule.slot >= frame.vectors.length) {
                throw new IOException("vector source missing");
            }
            l = OfflineRenderer.qword(frame.vectors[rule.slot], rule.high, rule.low);
        } else if (rule.kind == 2) {
            if (rule.request != 8 || frameArray[8] == null || frameArray[8].vectors.length == 0 || rule.slot > 3) {
                throw new IOException("early index source missing");
            }
            long[] lArray = OfflineRenderer.earlyIndexes(frameArray[8].vectors[0]);
            l = lArray[rule.slot];
        } else {
            throw new IOException("unknown relocation source kind");
        }
        if (rule.addend > 0L && l > Long.MAX_VALUE - rule.addend || rule.addend < 0L && l < Long.MIN_VALUE - rule.addend) {
            throw new IOException("relocation arithmetic overflow");
        }
        return l + rule.addend;
    }

    private static long[] earlyIndexes(byte[] byArray) throws IOException {
        if (byArray.length != 1024) {
            throw new IOException("early vector changed");
        }
        long l = OfflineRenderer.qword(byArray, 13, 14);
        long l2 = OfflineRenderer.qword(byArray, 11, 12) - l - 24L;
        long l3 = OfflineRenderer.qword(byArray, 29, 28) - l - 8L;
        if (l2 % 4L != 0L || l3 % 4L != 0L) {
            throw new IOException("early index not word aligned");
        }
        if ((l2 /= 4L) < 0L || (l3 /= 4L) < 0L || l2 >= 0x8000000L || l3 >= 0x8000000L) {
            throw new IOException("early index outside range");
        }
        return new long[]{l2, l2 + 40L, l2 + 41L, l3};
    }

    private static long qword(byte[] byArray, int n, int n2) throws IOException {
        if ((byArray.length & 3) != 0 || n < 0 || n2 < 0 || n > byArray.length / 4 - 1 || n2 > byArray.length / 4 - 1) {
            throw new IOException("vector word missing");
        }
        long l = OfflineRenderer.word(byArray, n);
        long l2 = OfflineRenderer.word(byArray, n2);
        return l << 32 | l2;
    }

    private static long word(byte[] byArray, int n) {
        int n2 = n * 4;
        return ((long)byArray[n2] & 0xFFL) << 24 | ((long)byArray[n2 + 1] & 0xFFL) << 16 | ((long)byArray[n2 + 2] & 0xFFL) << 8 | (long)byArray[n2 + 3] & 0xFFL;
    }

    private static void verifyShapes(Frame[] frameArray, Shape[] shapeArray, int n) throws IOException {
        int n2 = 0;
        while (n2 < shapeArray.length) {
            Shape shape = shapeArray[n2];
            if (shape.request <= n) {
                if (shape.request >= frameArray.length || frameArray[shape.request] == null) {
                    throw new IOException("prior result missing");
                }
                Frame frame = frameArray[shape.request];
                if (frame.status != shape.status || frame.instruction != shape.instruction || frame.scalars.length != shape.scalarCount || frame.vectors.length != shape.vectorLengths.length) {
                    throw new IOException("prior result shape changed");
                }
                int n3 = 0;
                while (n3 < frame.vectors.length) {
                    if (shape.request != 46 && frame.vectors[n3].length != shape.vectorLengths[n3]) {
                        throw new IOException("prior vector shape changed");
                    }
                    ++n3;
                }
                OfflineRenderer.verifyEarlyStatus(shape.request, frame);
                if (shape.request == 46) {
                    OfflineRenderer.verifyHenReport(frame);
                }
            }
            ++n2;
        }
    }

    static void verifyNewResult(int n, Frame frame, Shape[] shapeArray, Guards guards) throws Exception {
        Shape shape = null;
        int n2 = 0;
        while (n2 < shapeArray.length) {
            if (shapeArray[n2].request == n) {
                shape = shapeArray[n2];
            }
            ++n2;
        }
        if (shape == null || frame.status != shape.status || frame.instruction != shape.instruction || frame.scalars.length != shape.scalarCount || frame.vectors.length != shape.vectorLengths.length) {
            throw new IOException("new result shape changed");
        }
        n2 = 0;
        while (n2 < frame.vectors.length) {
            if (n != 46 && frame.vectors[n2].length != shape.vectorLengths[n2]) {
                throw new IOException("new result vector shape changed");
            }
            ++n2;
        }
        OfflineRenderer.verifyEarlyStatus(n, frame);
        if (n == 46) {
            OfflineRenderer.verifyHenReport(frame);
        }
        if (n == 12) {
            OfflineRenderer.verifyNativeLayout(frame, guards);
        }
        if (n == 16) {
            OfflineRenderer.verifyAliases(frame);
        }
        if (n == 27) {
            OfflineRenderer.verifyCodeWindows(frame, guards);
        }
    }

    private static void verifyEarlyStatus(int n, Frame frame) throws IOException {
        if (n == 4) {
            if (frame.vectors.length != 2 || frame.vectors[0].length != 4 || OfflineRenderer.readInt(frame.vectors[0], 0) != 0) {
                throw new IOException("helper setup failed");
            }
        } else if (n == 5 || n == 6) {
            int n2;
            int n3 = n2 = n == 5 ? 4 : Integer.MAX_VALUE;
            if (frame.vectors.length != 1 || frame.vectors[0].length != 8 || OfflineRenderer.readInt(frame.vectors[0], 0) != 0 || OfflineRenderer.readInt(frame.vectors[0], 4) != n2) {
                throw new IOException(n == 5 ? "helper image setup failed" : "getRGB array-length corruption failed");
            }
        }
    }

    private static void verifyNativeLayout(Frame frame, Guards guards) throws IOException {
        if (frame == null || frame.scalars.length != 13) {
            throw new IOException("native symbol results missing");
        }
        long l = frame.scalars[0];
        int n = 0;
        while (n < 10) {
            long l2 = frame.scalars[n];
            if (l2 < 0x100000000L || l2 < 0L || l2 - l != guards.nativeDeltas[n]) {
                throw new IOException("native relative layout changed");
            }
            ++n;
        }
    }

    private static void verifyAliases(Frame frame) throws IOException {
        if (frame == null || frame.scalars.length < 12) {
            throw new IOException("shared base result missing");
        }
        long l = frame.scalars[1];
        int n = 3;
        while (n < 12) {
            if (frame.scalars[n] != l) {
                throw new IOException("shared base aliases diverged");
            }
            n += 2;
        }
    }

    private static void verifyCodeWindows(Frame frame, Guards guards) throws Exception {
        if (frame == null || frame.vectors.length != 3) {
            throw new IOException("code window results missing");
        }
        int n = 0;
        while (n < 3) {
            if (frame.vectors[n].length != 16 || !OfflineRenderer.same(OfflineRenderer.hash(frame.vectors[n]), guards.windowHashes[n])) {
                throw new IOException("code window signature changed");
            }
            ++n;
        }
    }

    private static void verifyHenReport(Frame frame) throws IOException {
        if (frame.vectors.length != 2 || frame.vectors[0].length < 64 || frame.vectors[0].length > 4096 || frame.vectors[1].length < 128 || frame.vectors[1].length > 131072) {
            throw new IOException("Hx report vector bounds changed");
        }
        byte[] byArray = frame.vectors[0];
        byte[] byArray2 = frame.vectors[1];
        String[] stringArray = new String[]{"HEN14V", "APPLIED=1", "UID=0 uid2=0", "*** ROOT ***"};
        String[] stringArray2 = new String[]{"K 6/6 sites now hold the PATCHED byte", "K sysent[11].sy_narg=2", "K *** 1400.bin APPLIED - syscall 11 IS NOW kexec ***", "G2 sysfilt_ops restore=true", "H4 fetched 562240/562240 B", "H5c launching", "H7 pthread_create rc=0 handle=", "H7 *** hen.bin RUNNING"};
        int n = 0;
        while (n < stringArray.length) {
            if (!OfflineRenderer.contains(byArray, stringArray[n])) {
                throw new IOException("Hx success summary incomplete");
            }
            ++n;
        }
        n = 0;
        while (n < stringArray2.length) {
            if (!OfflineRenderer.contains(byArray2, stringArray2[n])) {
                throw new IOException("Hx HEN or patch log incomplete");
            }
            ++n;
        }
        if (!OfflineRenderer.lineContainsBoth(byArray2, "K sysent[11].sy_call=", "APPLIED=true") || !OfflineRenderer.lineContainsBoth(byArray2, "L set to ", " ok=true")) {
            throw new IOException("Hx kernel or ShellCore readback missing");
        }
        String[] stringArray3 = new String[]{"STOP", "no pair", "blind writes", "APPLIED=0"};
        int n2 = 0;
        while (n2 < stringArray3.length) {
            if (OfflineRenderer.contains(byArray, stringArray3[n2])) {
                throw new IOException("Hx reported failed pairing");
            }
            ++n2;
        }
        if (OfflineRenderer.contains(byArray2, "K NOT fully applied") || OfflineRenderer.contains(byArray2, "safeDown threw")) {
            throw new IOException("Hx reported incomplete kexec patch");
        }
    }

    private static boolean lineContainsBoth(byte[] byArray, String string, String string2) throws IOException {
        byte[] byArray2 = string.getBytes("US-ASCII");
        byte[] byArray3 = string2.getBytes("US-ASCII");
        int n = 0;
        while (n <= byArray.length - byArray2.length) {
            boolean bl = true;
            int n2 = 0;
            while (n2 < byArray2.length) {
                if (byArray[n + n2] != byArray2[n2]) {
                    bl = false;
                    break;
                }
                ++n2;
            }
            if (bl) {
                n2 = n;
                while (n2 < byArray.length && byArray[n2] != 10 && n2 - n < 512) {
                    ++n2;
                }
                int n3 = n + byArray2.length;
                while (n3 <= n2 - byArray3.length) {
                    boolean bl2 = true;
                    int n4 = 0;
                    while (n4 < byArray3.length) {
                        if (byArray[n3 + n4] != byArray3[n4]) {
                            bl2 = false;
                            break;
                        }
                        ++n4;
                    }
                    if (bl2) {
                        return true;
                    }
                    ++n3;
                }
            }
            ++n;
        }
        return false;
    }

    private static boolean contains(byte[] byArray, String string) throws IOException {
        byte[] byArray2 = string.getBytes("US-ASCII");
        int n = 0;
        while (n <= byArray.length - byArray2.length) {
            boolean bl = true;
            int n2 = 0;
            while (n2 < byArray2.length) {
                if (byArray[n + n2] != byArray2[n2]) {
                    bl = false;
                    break;
                }
                ++n2;
            }
            if (bl) {
                return true;
            }
            ++n;
        }
        return false;
    }

    private static byte[] hash(byte[] byArray) throws Exception {
        return MessageDigest.getInstance("SHA-256").digest(byArray);
    }

    private static boolean same(byte[] byArray, byte[] byArray2) {
        if (byArray.length != byArray2.length) {
            return false;
        }
        int n = 0;
        int n2 = 0;
        while (n2 < byArray.length) {
            n |= byArray[n2] ^ byArray2[n2];
            ++n2;
        }
        return n == 0;
    }

    static final class Frame {
        final long status;
        final int instruction;
        final long[] scalars;
        final byte[][] vectors;

        Frame(long l, int n, long[] lArray, byte[][] byArray) {
            this.status = l;
            this.instruction = n;
            this.scalars = lArray;
            this.vectors = byArray;
        }
    }

    static final class Guards {
        final long[] nativeDeltas;
        final byte[][] windowHashes;

        Guards(long[] lArray, byte[][] byArray) {
            this.nativeDeltas = lArray;
            this.windowHashes = byArray;
        }
    }

    static final class MarkerPlan {
        final int[] offsets;

        MarkerPlan(int[] nArray) {
            this.offsets = nArray;
        }
    }

    static final class Rule {
        final int command;
        final int instruction;
        final int offset;
        final int request;
        final int kind;
        final int slot;
        final int high;
        final int low;
        final long addend;

        Rule(int n, int n2, int n3, int n4, int n5, int n6, int n7, int n8, long l) {
            this.command = n;
            this.instruction = n2;
            this.offset = n3;
            this.request = n4;
            this.kind = n5;
            this.slot = n6;
            this.high = n7;
            this.low = n8;
            this.addend = l;
        }
    }

    static final class ScheduleEntry {
        final int command;
        final int nextResultRequest;

        ScheduleEntry(int n, int n2) {
            this.command = n;
            this.nextResultRequest = n2;
        }
    }

    static final class Shape {
        final int request;
        final int instruction;
        final int scalarCount;
        final long status;
        final int[] vectorLengths;

        Shape(int n, long l, int n2, int n3, int[] nArray) {
            this.request = n;
            this.status = l;
            this.instruction = n2;
            this.scalarCount = n3;
            this.vectorLengths = nArray;
        }
    }

    static final class TemplatePin {
        final int command;
        final int length;
        final byte[] sha256;

        TemplatePin(int n, int n2, byte[] byArray) {
            this.command = n;
            this.length = n2;
            this.sha256 = byArray;
        }
    }

    static final class WriteSite {
        final int command;
        final int instruction;
        final int offset;

        WriteSite(int n, int n2, int n3) {
            this.command = n;
            this.instruction = n2;
            this.offset = n3;
        }
    }
}

