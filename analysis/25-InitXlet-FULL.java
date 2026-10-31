/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.tv.xlet.Xlet
 *  javax.tv.xlet.XletContext
 *  javax.tv.xlet.XletStateChangeException
 *  org.havi.ui.HComponent
 *  org.havi.ui.HScene
 *  org.havi.ui.HSceneFactory
 */
package org.bdj;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;
import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;
import org.bdj.Boot;
import org.bdj.LocalResultLedger;
import org.bdj.OfflineRenderer;
import org.havi.ui.HComponent;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;

public final class InitXlet
implements Xlet,
Runnable,
KeyListener {
    private static final String[][] CLASSES = new String[][]{{"J196rR", "f59953027867d15d1434179991feeeb94bf274422f742e739067fd65400a264d"}, {"AXZXkk", "2362afc9efa07a688169b9121d9e5f8bdacbead58f87cafd23fdddffea58b429"}, {"EkIKZR", "df30bfbd25c3cdbe67965d907d575efb562738cf29c258895a698ff2ef525137"}, {"Hx1400", "e597710078e9afab5b24f4c22ea31fe7e5a5643b602b06c2866273b61c9c57e9"}};
    private static final String HEN_SHA256 = "29ec095fbede651e29954de43411599093138791e62105aaee6fdced58edec06";
    private static final String[][] META = new String[][]{{"rules.bin", "9c2f599212bfa71e1026dcc25b2f78826245992f958d898f7d0a1b6a2c2cb66e", "5338"}, {"shapes.bin", "a88a4eec4b49119c5e197b83b6c11724116f1c4f0057039b388874277c258050", "794"}, {"guards.bin", "64026f18135fe03397e0a2eb9c2586ce9218b1ed7b8cb6b13789ca8a2e367f50", "184"}, {"schedule.bin", "63b7a01ee102194aedd6329e750c6000752d076e1ebc5c18b7a3d0020ef49463", "168"}, {"template-hashes.bin", "9b60da9a7ce6a46327aed2091bafd7aaf182ed3a3e64aa5508fb39c02d8f5abc", "1528"}, {"marker-sites.bin", "816859ef11826205f0459b5246eff9dac3e02ccc4c54e4496eacc8bf9c3863bf", "40"}, {"write-sites.bin", "4bae0f1ad26b53efb4c47a12b8923191f0d99b52f7e4b792b82df2494f97276f", "656"}};
    private static final String HEN_SENTINEL = "file:/__bonk___offline_missing__/hen.bin";
    private Container scene;
    private Lines lines;
    private volatile boolean stopped;
    private volatile boolean ready;
    private boolean preflightStarted;
    private boolean attempted;
    private String discRoot;
    private String henUrl;
    private OfflineRenderer.Rule[] rules;
    private OfflineRenderer.Shape[] shapes;
    private OfflineRenderer.Guards guards;
    private OfflineRenderer.ScheduleEntry[] schedule;
    private OfflineRenderer.TemplatePin[] pins;
    private OfflineRenderer.MarkerPlan markerPlan;
    private OfflineRenderer.WriteSite[] writeSites;
    static /* synthetic */ Class class$0;

    public void initXlet(XletContext xletContext) throws XletStateChangeException {
        this.stopped = false;
    }

    public synchronized void startXlet() throws XletStateChangeException {
        this.stopped = false;
        if (this.scene == null) {
            this.openDisplay();
        } else {
            this.scene.setVisible(true);
        }
        if (!(this.preflightStarted || this.ready || this.attempted)) {
            this.preflightStarted = true;
            Thread thread = new Thread(this, "Bonk HEN local asset preflight");
            thread.setDaemon(true);
            thread.start();
        }
    }

    public void pauseXlet() {
        this.stopped = true;
        if (this.scene != null) {
            this.scene.setVisible(false);
        }
    }

    public void destroyXlet(boolean bl) throws XletStateChangeException {
        this.stopped = true;
        if (this.scene != null) {
            this.scene.setVisible(false);
        }
        this.scene = null;
        this.lines = null;
    }

    public void run() {
        block27: {
            try {
                Object object;
                URL uRL;
                Object object2;
                URL uRL2;
                this.line("Credits: Kat (@avieah), Slopp (@slopp), and Pigeon (@daburningpigeon) on Discord");
                Class<?> clazz = class$0;
                if (clazz == null) {
                    try {
                        clazz = class$0 = Class.forName("org.bdj.InitXlet");
                    }
                    catch (ClassNotFoundException classNotFoundException) {
                        throw new NoClassDefFoundError(classNotFoundException.getMessage());
                    }
                }
                if ((uRL2 = clazz.getResource("InitXlet.class")) == null) {
                    throw new IOException("signed Xlet URL missing");
                }
                String string = uRL2.toString();
                int n = string.indexOf("/BDMV/JAR/");
                if (!string.startsWith("jar:file:") || n < 0) {
                    throw new IOException("Xlet is not in a disc JAR");
                }
                this.discRoot = string.substring(4, n);
                if (!this.discRoot.startsWith("file:")) {
                    throw new IOException("disc root is not local file URL");
                }
                int n2 = 0;
                while (n2 < CLASSES.length) {
                    object2 = new URL(String.valueOf(this.discRoot) + "/x/offline/" + CLASSES[n2][0] + ".class");
                    InitXlet.checkHash((URL)object2, CLASSES[n2][1], 1, 100000);
                    ++n2;
                }
                this.line("Four local helper classes verified");
                Class<?> clazz2 = class$0;
                if (clazz2 == null) {
                    try {
                        clazz2 = class$0 = Class.forName("org.bdj.InitXlet");
                    }
                    catch (ClassNotFoundException classNotFoundException) {
                        throw new NoClassDefFoundError(classNotFoundException.getMessage());
                    }
                }
                if ((uRL = clazz2.getResource("/bonk/hen.bin")) == null || !uRL.toString().startsWith("jar:file:")) {
                    throw new IOException("signed local HEN URL missing");
                }
                InitXlet.checkHash(uRL, HEN_SHA256, 562240, 562240);
                this.henUrl = uRL.toString();
                this.line("Exact HEN image verified in signed JAR");
                object2 = new byte[META.length][];
                int n3 = 0;
                while (n3 < META.length) {
                    Class<?> clazz3 = class$0;
                    if (clazz3 == null) {
                        try {
                            clazz3 = Class.forName("org.bdj.InitXlet");
                        }
                        catch (ClassNotFoundException classNotFoundException) {
                            throw new NoClassDefFoundError(classNotFoundException.getMessage());
                        }
                    }
                    if ((object = clazz3.getResource("/bonk/local/" + META[n3][0])) == null || !((URL)object).toString().startsWith("jar:file:")) {
                        throw new IOException("local metadata resource missing");
                    }
                    object2[n3] = InitXlet.readBounded((URL)object, 8192);
                    if (((Object)object2[n3]).length != Integer.parseInt(META[n3][2]) || !InitXlet.hexHash((byte[])object2[n3]).equals(META[n3][1])) {
                        throw new IOException("local metadata identity changed");
                    }
                    ++n3;
                }
                this.rules = OfflineRenderer.rules((byte[])object2[0]);
                this.shapes = OfflineRenderer.shapes((byte[])object2[1]);
                this.guards = OfflineRenderer.guards((byte[])object2[2]);
                this.schedule = OfflineRenderer.schedule((byte[])object2[3]);
                this.pins = OfflineRenderer.templatePins((byte[])object2[4]);
                this.markerPlan = OfflineRenderer.markerPlan((byte[])object2[5]);
                this.writeSites = OfflineRenderer.writeSites((byte[])object2[6], this.rules);
                if (this.pins.length != this.schedule.length) {
                    throw new IOException("command schedule count changed");
                }
                n3 = 0;
                while (n3 < this.pins.length) {
                    if (this.pins[n3].command != this.schedule[n3].command) {
                        throw new IOException("command schedule identity changed");
                    }
                    ++n3;
                }
                n3 = 0;
                while (n3 < this.pins.length) {
                    object = this.loadTemplate(this.pins[n3].command, this.pins[n3].length);
                    byte[] byArray = MessageDigest.getInstance("SHA-256").digest((byte[])object);
                    if (!InitXlet.same(byArray, this.pins[n3].sha256)) {
                        throw new IOException("command template hash changed");
                    }
                    ++n3;
                }
                this.line("All 40 local command templates verified");
                if (this.stopped) {
                    return;
                }
                this.ready = true;
                this.line("LOCAL PREFLIGHT PASS. Press X once to run.");
                this.line("One attempt only; restart after any failure.");
            }
            catch (Throwable throwable) {
                this.line("PREFLIGHT FAIL: " + throwable.getClass().getName());
                if (throwable.getMessage() == null) break block27;
                this.line(throwable.getMessage());
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void keyPressed(KeyEvent keyEvent) {
        int n = keyEvent.getKeyCode();
        if (n != 10 && n != 32 && n != 88) {
            return;
        }
        Runnable runnable = this;
        synchronized (runnable) {
            if (this.stopped || !this.ready || this.attempted) {
                return;
            }
            this.attempted = true;
        }
        this.line("Attempt 1 started: local interpreter commands");
        runnable = new Thread(new Attempt(), "Bonk HEN one-shot local run");
        ((Thread)runnable).setDaemon(true);
        ((Thread)runnable).start();
        keyEvent.consume();
    }

    public void keyReleased(KeyEvent keyEvent) {
    }

    public void keyTyped(KeyEvent keyEvent) {
    }

    private void showEarlyStatus(int n, byte[] byArray) {
        try {
            OfflineRenderer.Frame frame = OfflineRenderer.frame(byArray);
            if (frame.vectors.length == 0 || frame.vectors[0].length < 4) {
                throw new IOException("early status vector missing");
            }
            int n2 = InitXlet.readInt(frame.vectors[0], 0);
            if (n == 6) {
                if (n2 == -1) {
                    this.line("Marker scan: no match (-1)");
                } else if (n2 == -2) {
                    this.line("Marker scan: helper exception (-2)");
                } else {
                    this.line("Marker scan index: " + n2);
                }
            } else if (n == 4 || n == 5) {
                if (frame.vectors[0].length < 8) {
                    throw new IOException("early status pair missing");
                }
                int n3 = InitXlet.readInt(frame.vectors[0], 4);
                this.line("Command " + n + " helper status: " + n2 + ", " + n3);
            } else {
                this.line("Command 2 helper status: " + n2);
            }
        }
        catch (Throwable throwable) {
            this.line("Command " + n + " early status unreadable");
        }
    }

    private void showHxDiagnostic(byte[] byArray) {
        try {
            int n;
            OfflineRenderer.Frame frame = OfflineRenderer.frame(byArray);
            if (frame.vectors.length != 2 || frame.vectors[0].length > 4096 || frame.vectors[1].length > 131072) {
                throw new IOException("Hx diagnostic shape changed");
            }
            String string = new String(frame.vectors[0], "UTF-8");
            this.line("Hx summary: " + InitXlet.printable(string, 96));
            String string2 = new String(frame.vectors[1], "UTF-8");
            String[] stringArray = new String[3];
            int n2 = 0;
            int n3 = 0;
            while (n3 < string2.length()) {
                String string3;
                n = string2.indexOf(10, n3);
                if (n < 0) {
                    n = string2.length();
                }
                if ((string3 = string2.substring(n3, n)).indexOf("STOP") >= 0 || string3.indexOf("no pair") >= 0 || string3.indexOf("K NOT fully applied") >= 0 || string3.indexOf("G2 sysfilt_ops") >= 0 || string3.indexOf("safeDown threw") >= 0 || string3.indexOf("H7 pthread_create") >= 0 || string3.indexOf("H7 ***") >= 0 || string3.indexOf("L set ") >= 0) {
                    stringArray[n2 % stringArray.length] = InitXlet.printable(string3, 96);
                    ++n2;
                }
                n3 = n + 1;
            }
            n = n2 < stringArray.length ? n2 : stringArray.length;
            int n4 = 0;
            while (n4 < n) {
                this.line("Hx log: " + stringArray[(n2 - n + n4) % stringArray.length]);
                ++n4;
            }
        }
        catch (Throwable throwable) {
            this.line("Hx report could not be decoded for display");
        }
    }

    private static String printable(String string, int n) {
        StringBuffer stringBuffer = new StringBuffer();
        int n2 = 0;
        while (n2 < string.length() && stringBuffer.length() < n) {
            char c = string.charAt(n2);
            stringBuffer.append(c >= ' ' && c <= '~' ? c : (char)' ');
            ++n2;
        }
        return stringBuffer.toString();
    }

    private byte[] loadTemplate(int n, int n2) throws Exception {
        URL uRL;
        String string = Integer.toString(n);
        while (string.length() < 4) {
            string = "0" + string;
        }
        Class<?> clazz = class$0;
        if (clazz == null) {
            try {
                clazz = class$0 = Class.forName("org.bdj.InitXlet");
            }
            catch (ClassNotFoundException classNotFoundException) {
                throw new NoClassDefFoundError(classNotFoundException.getMessage());
            }
        }
        if ((uRL = clazz.getResource("/bonk/local/templates/cmd-" + string + ".bin")) == null || !uRL.toString().startsWith("jar:file:")) {
            throw new IOException("command template resource missing");
        }
        byte[] byArray = InitXlet.readBounded(uRL, n2);
        if (byArray.length != n2) {
            throw new IOException("command template length changed");
        }
        return byArray;
    }

    static byte[] patchLocalHenUrl(byte[] byArray, String string) throws Exception {
        int n;
        if (string == null || !string.startsWith("jar:file:") || string.indexOf("!/bonk/hen.bin") < 0) {
            throw new IOException("HEN URL is not verified local JAR resource");
        }
        byte[] byArray2 = HEN_SENTINEL.getBytes("UTF-8");
        byte[] byArray3 = string.getBytes("UTF-8");
        int n2 = -1;
        int n3 = 13;
        while (n3 <= byArray.length - byArray2.length) {
            boolean bl = true;
            n = 0;
            while (n < byArray2.length) {
                if (byArray[n3 + n] != byArray2[n]) {
                    bl = false;
                    break;
                }
                ++n;
            }
            if (bl) {
                if (n2 >= 0) {
                    throw new IOException("HEN sentinel not unique");
                }
                n2 = n3;
            }
            ++n3;
        }
        if (n2 < 13 || byArray[n2 - 13] != 5 || InitXlet.readLong(byArray, n2 - 12) != 45L || InitXlet.readInt(byArray, n2 - 4) != byArray2.length) {
            throw new IOException("HEN assignment framing changed");
        }
        n3 = byArray.length - byArray2.length + byArray3.length;
        if (n3 > 65535) {
            throw new IOException("local HEN command too long");
        }
        byte[] byArray4 = new byte[n3];
        System.arraycopy(byArray, 0, byArray4, 0, n2);
        System.arraycopy(byArray3, 0, byArray4, n2, byArray3.length);
        System.arraycopy(byArray, n2 + byArray2.length, byArray4, n2 + byArray3.length, byArray.length - n2 - byArray2.length);
        n = n2 - 4;
        int n4 = byArray3.length;
        byArray4[n] = (byte)(n4 >>> 24);
        byArray4[n + 1] = (byte)(n4 >>> 16);
        byArray4[n + 2] = (byte)(n4 >>> 8);
        byArray4[n + 3] = (byte)n4;
        return byArray4;
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

    /*
     * WARNING - Removed back jump from a try to a catch block - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static byte[] readBounded(URL uRL, int n) throws Exception {
        InputStream inputStream = uRL.openStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            int n2;
            byte[] byArray = new byte[8192];
            while ((n2 = inputStream.read(byArray)) >= 0) {
                if (n2 == 0) continue;
                if (byteArrayOutputStream.size() > n - n2) {
                    throw new IOException("asset exceeds bound");
                }
                byteArrayOutputStream.write(byArray, 0, n2);
            }
        }
        catch (Throwable throwable) {
            Object var6_7 = null;
            inputStream.close();
            throw throwable;
        }
        {
            Object var6_8 = null;
        }
        inputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    /*
     * WARNING - Removed back jump from a try to a catch block - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    static void checkHash(URL uRL, String string, int n, int n2) throws Exception {
        if (n < 0 || n2 < n) {
            throw new IOException("asset size bounds invalid");
        }
        InputStream inputStream = uRL.openStream();
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] byArray = new byte[8192];
        int n3 = 0;
        int n4 = 0;
        try {
            int n5;
            while ((n5 = inputStream.read(byArray)) >= 0) {
                if (n5 == 0) {
                    if (++n4 <= 16) continue;
                    throw new IOException("asset stream did not advance");
                }
                n4 = 0;
                if (n3 > n2 - n5) {
                    throw new IOException("asset exceeds bound");
                }
                messageDigest.update(byArray, 0, n5);
                n3 += n5;
            }
        }
        catch (Throwable throwable) {
            Object var10_11 = null;
            inputStream.close();
            throw throwable;
        }
        {
            Object var10_12 = null;
        }
        inputStream.close();
        if (n3 >= n && InitXlet.hexDigest(messageDigest.digest()).equals(string)) {
            return;
        }
        throw new IOException("asset hash or size changed");
    }

    private static String hexHash(byte[] byArray) throws Exception {
        return InitXlet.hexDigest(MessageDigest.getInstance("SHA-256").digest(byArray));
    }

    private static String hexDigest(byte[] byArray) {
        StringBuffer stringBuffer = new StringBuffer(64);
        int n = 0;
        while (n < byArray.length) {
            int n2 = byArray[n] & 0xFF;
            if (n2 < 16) {
                stringBuffer.append('0');
            }
            stringBuffer.append(Integer.toHexString(n2));
            ++n;
        }
        return stringBuffer.toString();
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

    private void openDisplay() {
        HScene hScene = HSceneFactory.getInstance().getDefaultHScene();
        if (hScene == null) {
            return;
        }
        hScene.setLayout(null);
        hScene.setBackground(Color.black);
        Lines lines = new Lines();
        lines.setBounds(0, 0, hScene.getWidth() > 0 ? hScene.getWidth() : 1280, hScene.getHeight() > 0 ? hScene.getHeight() : 720);
        lines.addKeyListener(this);
        hScene.addKeyListener(this);
        hScene.add((Component)((Object)lines));
        hScene.setVisible(true);
        hScene.validate();
        lines.requestFocus();
        this.scene = hScene;
        this.lines = lines;
    }

    private void line(String string) {
        if (this.stopped) {
            return;
        }
        System.out.println("Bonk HEN local: " + string);
        Lines lines = this.lines;
        if (lines != null) {
            lines.addLine(string);
        }
    }

    private final class Attempt
    implements Runnable {
        private Attempt() {
        }

        public void run() {
            int n = 0;
            byte[] byArray = null;
            byte[] byArray2 = null;
            int n2 = 0;
            try {
                if (InitXlet.this.stopped) {
                    throw new IOException("Xlet stopped");
                }
                Boot.EP = InitXlet.this.discRoot;
                Boot.SID = "offline";
                Class<?> clazz = class$0;
                if (clazz == null) {
                    try {
                        clazz = class$0 = Class.forName("org.bdj.InitXlet");
                    }
                    catch (ClassNotFoundException classNotFoundException) {
                        throw new NoClassDefFoundError(classNotFoundException.getMessage());
                    }
                }
                Boot.PAR = clazz.getClassLoader();
                Boot.SK = null;
                int[] nArray = OfflineRenderer.freshMarkerWords();
                LocalResultLedger localResultLedger = new LocalResultLedger(InitXlet.this.schedule, InitXlet.this.rules, InitXlet.this.shapes, InitXlet.this.guards, InitXlet.this.pins, InitXlet.this.markerPlan, nArray, InitXlet.this.writeSites);
                int n3 = 0;
                while (n3 < InitXlet.this.schedule.length) {
                    if (InitXlet.this.stopped) {
                        throw new IOException("Xlet stopped");
                    }
                    n = localResultLedger.currentCommand();
                    byte[] byArray3 = InitXlet.this.loadTemplate(n, ((InitXlet)InitXlet.this).pins[n3].length);
                    byte[] byArray4 = localResultLedger.renderCurrent(byArray3);
                    if (n == 32) {
                        byArray4 = InitXlet.patchLocalHenUrl(byArray4, InitXlet.this.henUrl);
                        InitXlet.this.line("Command 32 uses verified local HEN URL");
                    }
                    if (n == 28) {
                        InitXlet.this.line("Native code-window branch verified");
                    }
                    if (n == 43) {
                        InitXlet.this.line("Entering one-shot Hx/HEN stage");
                    }
                    if (n == 44) {
                        long l = System.currentTimeMillis() + 45000L;
                        boolean bl = false;
                        int n4 = 1;
                        while (n4 <= 10) {
                            byte[] byArray5;
                            byArray = byArray5 = Boot.exec(byArray4);
                            if (!OfflineRenderer.isPendingHenResult(byArray5)) {
                                localResultLedger.acceptResult(byArray5);
                                bl = true;
                                break;
                            }
                            InitXlet.this.line("Hx still running; read-only status " + n4);
                            if (n4 == 10 || System.currentTimeMillis() > l) {
                                throw new IOException("Hx status remained pending");
                            }
                            ++n4;
                        }
                        if (!bl) {
                            throw new IOException("Hx final report missing");
                        }
                    } else {
                        byte[] byArray6 = Boot.exec(byArray4);
                        if (n == 2 || n == 4 || n == 5 || n == 6) {
                            byArray2 = byArray6;
                            n2 = n;
                        }
                        if (((InitXlet)InitXlet.this).schedule[n3].nextResultRequest == 65535) {
                            localResultLedger.finishTerminal(byArray6);
                        } else {
                            localResultLedger.acceptResult(byArray6);
                        }
                    }
                    if (n == 9 || n == 27 || n == 32 || n == 44 || n == 46) {
                        InitXlet.this.line("Command " + n + " result accepted");
                    }
                    ++n3;
                }
                if (!localResultLedger.complete()) {
                    throw new IOException("local result ledger incomplete");
                }
                InitXlet.this.line("Hx reports HEN start. Verify menu and kernel R/W separately.");
            }
            catch (Throwable throwable) {
                InitXlet.this.line("STOP at command " + n + ": " + throwable.getClass().getName());
                if (throwable.getMessage() != null) {
                    InitXlet.this.line(throwable.getMessage());
                }
                if (n == n2 && byArray2 != null) {
                    InitXlet.this.showEarlyStatus(n, byArray2);
                }
                if (n == 44 && byArray != null) {
                    InitXlet.this.showHxDiagnostic(byArray);
                }
                InitXlet.this.line("No retry in this boot. Cold reboot before another attempt.");
            }
        }
    }

    private static final class Lines
    extends HComponent {
        private static final long serialVersionUID = 1L;
        private final String[] values = new String[15];
        private int count;

        private Lines() {
        }

        synchronized void addLine(String string) {
            if (this.count < this.values.length) {
                this.values[this.count++] = string;
            } else {
                int n = 1;
                while (n < this.values.length) {
                    this.values[n - 1] = this.values[n];
                    ++n;
                }
                this.values[this.values.length - 1] = string;
            }
            this.repaint();
        }

        public synchronized void paint(Graphics graphics) {
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
            graphics.setColor(new Color(255, 127, 0));
            graphics.setFont(new Font("SansSerif", 1, 28));
            graphics.drawString("BONK HEN 14.00", 40, 55);
            graphics.setColor(Color.white);
            graphics.setFont(new Font("SansSerif", 0, 21));
            int n = 0;
            while (n < this.count) {
                graphics.drawString(this.values[n], 40, 105 + n * 34);
                ++n;
            }
        }
    }
}

