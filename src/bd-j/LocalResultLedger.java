/*
 * ═══════════════════════════════════════════════════════════════════════
 *  RECONSTRUCTED SOURCE — NOT ORIGINAL AUTHORED CODE
 * ═══════════════════════════════════════════════════════════════════════
 *
 *  Class:         LocalResultLedger
 *  SHA-256:       d2e4c6a8b1f0e9c5...
 *  Source:        BDMV/JAR/00000.jar → org/bdj/LocalResultLedger.class
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

import java.io.IOException;
import org.bdj.OfflineRenderer;

final class LocalResultLedger {
    private final OfflineRenderer.ScheduleEntry[] schedule;
    private final OfflineRenderer.Rule[] rules;
    private final OfflineRenderer.Shape[] shapes;
    private final OfflineRenderer.Guards guards;
    private final OfflineRenderer.TemplatePin[] pins;
    private final OfflineRenderer.MarkerPlan markerPlan;
    private final int[] markerWords;
    private final OfflineRenderer.WriteSite[] writeSites;
    private final OfflineRenderer.Frame[] frames = new OfflineRenderer.Frame[48];
    private int at;
    private boolean awaitingResult;
    private boolean failed;
    private boolean successWindowConfirmed;
    private int pendingInstructionCount;

    LocalResultLedger(OfflineRenderer.ScheduleEntry[] scheduleEntryArray, OfflineRenderer.Rule[] ruleArray, OfflineRenderer.Shape[] shapeArray, OfflineRenderer.Guards guards, OfflineRenderer.TemplatePin[] templatePinArray, OfflineRenderer.MarkerPlan markerPlan, int[] nArray, OfflineRenderer.WriteSite[] writeSiteArray) {
        this.schedule = scheduleEntryArray;
        this.rules = ruleArray;
        this.shapes = shapeArray;
        this.guards = guards;
        this.pins = templatePinArray;
        this.markerPlan = markerPlan;
        this.markerWords = (int[])nArray.clone();
        this.writeSites = writeSiteArray;
    }

    int currentCommand() throws IOException {
        if (this.failed || this.at >= this.schedule.length) {
            throw new IOException("ledger stopped");
        }
        return this.schedule[this.at].command;
    }

    byte[] renderCurrent(byte[] byArray) throws Exception {
        if (this.failed || this.awaitingResult || this.at >= this.schedule.length) {
            throw new IOException("ledger out of order");
        }
        int n = this.schedule[this.at].command;
        if (n >= 28 && !this.successWindowConfirmed) {
            this.failed = true;
            throw new IOException("successful code-window branch unconfirmed");
        }
        try {
            byte[] byArray2 = OfflineRenderer.renderCandidate(n, byArray, this.frames, this.rules, this.shapes, this.guards, this.pins, this.markerPlan, this.markerWords, this.writeSites);
            this.pendingInstructionCount = (byArray2[0] & 0xFF) << 8 | byArray2[1] & 0xFF;
            this.awaitingResult = true;
            return byArray2;
        }
        catch (Exception exception) {
            this.failed = true;
            throw exception;
        }
    }

    void acceptResult(byte[] byArray) throws Exception {
        if (this.failed || !this.awaitingResult || this.at >= this.schedule.length) {
            throw new IOException("ledger result out of order");
        }
        int n = this.schedule[this.at].nextResultRequest;
        if (n == 65535) {
            this.failed = true;
            throw new IOException("terminal command must not return a result");
        }
        try {
            OfflineRenderer.Frame frame = OfflineRenderer.frame(byArray);
            OfflineRenderer.verifyNewResult(n, frame, this.shapes, this.guards);
            OfflineRenderer.verifyMarkerNewResult(n, frame, this.markerWords);
            if (this.frames[n] != null) {
                throw new IOException("duplicate result request");
            }
            this.frames[n] = frame;
            if (n == 27) {
                this.successWindowConfirmed = true;
            }
            ++this.at;
            this.awaitingResult = false;
        }
        catch (Exception exception) {
            this.failed = true;
            throw exception;
        }
    }

    void finishTerminal(byte[] byArray) throws Exception {
        if (this.failed || !this.awaitingResult || this.at != this.schedule.length - 1 || this.schedule[this.at].nextResultRequest != 65535) {
            this.failed = true;
            throw new IOException("terminal finish out of order");
        }
        try {
            OfflineRenderer.Frame frame = OfflineRenderer.frame(byArray);
            if (frame.status != 0L || frame.instruction != this.pendingInstructionCount || frame.scalars.length != 0 || frame.vectors.length != 0) {
                throw new IOException("terminal Boot result changed");
            }
        }
        catch (Exception exception) {
            this.failed = true;
            throw exception;
        }
        ++this.at;
        this.awaitingResult = false;
    }

    boolean complete() {
        return !this.failed && !this.awaitingResult && this.at == this.schedule.length;
    }

    boolean successWindowConfirmed() {
        return this.successWindowConfirmed;
    }
}

