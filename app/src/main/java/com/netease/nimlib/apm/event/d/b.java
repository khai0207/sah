package com.netease.nimlib.apm.event.d;

/* compiled from: EventReportTask.java */
/* loaded from: classes.dex */
public class b implements Runnable {
    private volatile long a;
    private volatile long b = 0;
    private volatile boolean c = false;

    public b() {
        this.a = 0L;
        this.a = System.currentTimeMillis();
    }

    @Override // java.lang.Runnable
    public void run() {
        this.b = System.currentTimeMillis();
    }

    public long d() {
        return this.b;
    }

    public void a(boolean z) {
        this.c = z;
    }
}
