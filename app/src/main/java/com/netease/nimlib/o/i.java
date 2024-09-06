package com.netease.nimlib.o;

/* compiled from: FrequencyControl.java */
/* loaded from: classes.dex */
public class i {
    private final long a;
    private final int b;
    private int c = 0;
    private int d = 0;
    private long e = 0;

    public i(long j, int i) {
        this.a = j < 0 ? 0L : j;
        this.b = i < 0 ? 0 : i;
        c();
    }

    public void a() {
        this.d = this.c;
        this.e = System.currentTimeMillis();
    }

    public boolean b() {
        int i = this.c + 1;
        this.c = i;
        return i - this.d >= this.b && System.currentTimeMillis() - this.e >= this.a;
    }

    public void c() {
        this.c = 0;
        this.d = 0;
        this.e = 0L;
    }
}
