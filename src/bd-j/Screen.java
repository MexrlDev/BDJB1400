/*
 * ═══════════════════════════════════════════════════════════════════════
 *  RECONSTRUCTED SOURCE — NOT ORIGINAL AUTHORED CODE
 * ═══════════════════════════════════════════════════════════════════════
 *
 *  Class:         Screen
 *  SHA-256:       e3f0a7d1b9c8e5f6...
 *  Source:        BDMV/JAR/00000.jar → org/bdj/Screen.class
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

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.StringTokenizer;
import java.util.Vector;

public class Screen
extends Container {
    private static final long serialVersionUID = 0x4141414141414141L;
    private final Font FONT = new Font(null, 0, 17);
    private final Vector messages = new Vector();
    private static final Screen instance = new Screen();
    private volatile boolean isPainting = false;
    private volatile boolean isDirty = false;
    private volatile boolean isVisible = true;

    private Screen() {
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
        this.addComponentListener(new ComponentAdapter(){

            public void componentShown(ComponentEvent componentEvent) {
                Screen.this.isVisible = true;
                Screen.this.safeRepaint();
            }

            public void componentHidden(ComponentEvent componentEvent) {
                Screen.this.isVisible = false;
            }
        });
    }

    public static Screen getInstance() {
        return instance;
    }

    public static void println(String string) {
        Screen.println(string, true, false);
    }

    public static void println(String string, boolean bl, boolean bl2) {
        Screen.getInstance().print(string, bl, bl2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void print(String string, boolean bl, boolean bl2) {
        if (string == null) {
            string = "null";
        }
        Vector vector = this.messages;
        synchronized (vector) {
            if (bl2 && this.messages.size() > 0) {
                this.messages.removeElementAt(this.messages.size() - 1);
            }
            this.messages.addElement(string);
            while (this.messages.size() > 46) {
                this.messages.removeElementAt(0);
            }
            this.isDirty = true;
        }
        if (bl) {
            this.safeRepaint();
        }
    }

    private void safeRepaint() {
        if (EventQueue.isDispatchThread()) {
            if (this.isDisplayable()) {
                this.repaint();
            }
        } else {
            EventQueue.invokeLater(new Runnable(){

                public void run() {
                    if (Screen.this.isDisplayable()) {
                        Screen.this.repaint();
                    }
                }
            });
        }
    }

    public void update(Graphics graphics) {
        this.paint(graphics);
    }

    public static void printStackTrace(String string, Throwable throwable) {
        Screen.println(string, true, false);
        Screen.getInstance().printStackTrace(throwable);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void printStackTrace(Throwable throwable) {
        if (throwable == null) {
            this.print("null exception", true, false);
            return;
        }
        try {
            int n;
            StringBuffer stringBuffer;
            StringTokenizer stringTokenizer;
            Object object;
            Object object2;
            StringWriter stringWriter = new StringWriter();
            try {
                object2 = new PrintWriter(stringWriter);
                try {
                    throwable.printStackTrace((PrintWriter)object2);
                    ((PrintWriter)object2).flush();
                }
                finally {
                    ((PrintWriter)object2).close();
                }
                object = stringWriter.toString();
                stringTokenizer = new StringTokenizer((String)object, "\n", false);
                stringBuffer = new StringBuffer(((String)object).length());
            }
            finally {
                stringWriter.close();
            }
            object2 = new Vector();
            while (stringTokenizer.hasMoreTokens()) {
                object = stringTokenizer.nextToken();
                stringBuffer.setLength(0);
                for (n = 0; n < ((String)object).length(); ++n) {
                    char c = ((String)object).charAt(n);
                    if (c == '\t') {
                        stringBuffer.append("   ");
                        continue;
                    }
                    if (c == '\r') continue;
                    stringBuffer.append(c);
                }
                ((Vector)object2).addElement(stringBuffer.toString());
            }
            object = this.messages;
            synchronized (object) {
                for (n = 0; n < ((Vector)object2).size(); ++n) {
                    String string = (String)((Vector)object2).elementAt(n);
                    this.print(string, false, false);
                }
            }
            this.safeRepaint();
        }
        catch (IOException iOException) {
            this.printThrowable(throwable);
            throw new RuntimeException("Another exception occurred while printing stacktrace. " + iOException.getClass().getName() + ": " + iOException.getMessage());
        }
    }

    public void printThrowable(Throwable throwable) {
        if (throwable == null) {
            this.print("null throwable", true, false);
            return;
        }
        String string = throwable.getMessage();
        if (string == null) {
            string = "(no message)";
        }
        this.print(throwable.getClass().getName() + ": " + string, true, false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void paint(Graphics graphics) {
        Vector vector;
        if (graphics == null) {
            return;
        }
        boolean bl = false;
        Vector vector2 = this.messages;
        synchronized (vector2) {
            if (this.isPainting) {
                return;
            }
            this.isPainting = true;
            this.isDirty = false;
            vector = new Vector(this.messages);
        }
        try {
            graphics.setColor(this.getBackground());
            graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
            graphics.setFont(this.FONT);
            graphics.setColor(this.getForeground());
            int n = graphics.getFontMetrics().getHeight();
            int n2 = 8;
            int n3 = 23;
            for (int i = 0; i < vector.size(); ++i) {
                String string = (String)vector.elementAt(i);
                if (string == null || n3 >= this.getHeight()) continue;
                graphics.drawString(string, n2, n3);
                n3 += n;
            }
        }
        finally {
            Vector vector3 = this.messages;
            synchronized (vector3) {
                this.isPainting = false;
                if (this.isDirty) {
                    bl = true;
                }
            }
            if (bl) {
                this.safeRepaint();
            }
        }
    }

    public static void clear() {
        Screen.getInstance().clearMessages();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void clearMessages() {
        Vector vector = this.messages;
        synchronized (vector) {
            this.messages.removeAllElements();
            this.isDirty = true;
        }
        this.safeRepaint();
    }
}

