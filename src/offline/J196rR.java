/*
 * ═══════════════════════════════════════════════════════════════════════
 *  RECONSTRUCTED SOURCE — NOT ORIGINAL AUTHORED CODE
 * ═══════════════════════════════════════════════════════════════════════
 *
 *  Class:         J196rR
 *  SHA-256:       f59953027867d15d1434179991feeeb94bf274422f742e739067fd65400a264d
 *  Source:        x/offline/J196rR.class
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
import java.awt.Container;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;

public class J196rR {
    public int[] siVK7a;
    public Object[] CLdAPN;
    public Object vNjgvC;
    public int[] ryc3I7;
    public Object U5nZbm;

    public int[] p9NZZB() {
        try {
            Container container = null;
            try {
                Class<?> clazz = Class.forName("org.havi.ui.HSceneFactory");
                Object object = clazz.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
                container = (Container)clazz.getMethod("getDefaultHScene", new Class[0]).invoke(object, new Object[0]);
            }
            catch (Throwable throwable) {
                return new int[]{-1};
            }
            if (container == null) {
                return new int[]{-2};
            }
            container.setVisible(true);
            return new int[]{0};
        }
        catch (Throwable throwable) {
            return new int[]{-9};
        }
    }

    public int[] ApYgGE() {
        try {
            GraphicsConfiguration graphicsConfiguration = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
            if (graphicsConfiguration == null) {
                return new int[]{-1, 0};
            }
            this.U5nZbm = graphicsConfiguration;
            BufferedImage bufferedImage = graphicsConfiguration.createCompatibleImage(4, 4);
            if (bufferedImage == null) {
                return new int[]{-2, 0};
            }
            this.vNjgvC = bufferedImage;
            bufferedImage.setRGB(0, 0, -16777216);
            bufferedImage.setRGB(0, 1, Integer.MAX_VALUE);
            return new int[]{0, bufferedImage.getWidth()};
        }
        catch (Throwable throwable) {
            return new int[]{-9, 0};
        }
    }

    public int[] fFFjF2(int[] nArray) {
        try {
            Object[] objectArray;
            BufferedImage bufferedImage = (BufferedImage)this.vNjgvC;
            int[] nArray2 = new int[16];
            bufferedImage.getRGB(0, 0, 1, 2, nArray2, 0, -2);
            int n = nArray2.length;
            if (n != Integer.MAX_VALUE) {
                return new int[]{-2, n};
            }
            this.siVK7a = nArray2;
            SecurityManager securityManager = System.getSecurityManager();
            int[] nArray3 = new int[]{nArray[0], nArray[1], nArray[2], nArray[3]};
            this.ryc3I7 = nArray3;
            objectArray[5] = objectArray = new Object[]{securityManager, System.class, nArray2, nArray3, bufferedImage, null, this.U5nZbm, null};
            this.CLdAPN = objectArray;
            return new int[]{0, n};
        }
        catch (Throwable throwable) {
            return new int[]{-9, 0};
        }
    }

    public int[] TI8JyY(int n, int n2) {
        try {
            int[] nArray = this.siVK7a;
            int[] nArray2 = this.ryc3I7;
            int n3 = n + n2;
            for (int i = n; i < n3; ++i) {
                if (nArray[i] != nArray2[0] || nArray[i + 1] != nArray2[1] || nArray[i + 2] != nArray2[2]) continue;
                return new int[]{i};
            }
            return new int[]{-1};
        }
        catch (Throwable throwable) {
            return new int[]{-2};
        }
    }

    public int pJVqoX(int n) {
        return this.siVK7a[n];
    }

    public void dkzW4P(int n, int n2) {
        this.siVK7a[n] = n2;
    }

    public int[] ofas1Z(int n, int n2) {
        int[] nArray = new int[n2];
        for (int i = 0; i < n2; ++i) {
            nArray[i] = this.siVK7a[n + i];
        }
        return nArray;
    }
}
