package com.netease.nimlib.m;

/* compiled from: NtpConfig.java */
/* loaded from: classes.dex */
public class a {
    private final long a;
    private final long b;
    private final long c;
    private final long d;

    public a(long j, long j2, long j3, long j4) {
        this.a = j;
        this.b = j2;
        this.c = j3;
        this.d = j4;
    }

    public long a() {
        return this.a;
    }

    public long b() {
        return this.b;
    }

    public long c() {
        return this.c;
    }

    public long d() {
        return this.d;
    }

    public String toString() {
        return "NtpConfig{rttMin=" + this.a + ", rttMax=" + this.b + ", rttCount=" + this.c + ", rttTTL=" + this.d + '}';
    }
}
