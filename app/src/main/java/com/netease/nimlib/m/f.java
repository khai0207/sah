package com.netease.nimlib.m;

/* compiled from: NtpRecord.java */
/* loaded from: classes.dex */
public class f {
    private long a;
    private long b;
    private long c;
    private long d;
    private boolean e;

    public void a(long j) {
        this.a = j;
    }

    public void b(long j) {
        this.b = j;
    }

    public void a(long j, long j2) {
        this.c = j;
        this.d = j2;
    }

    public boolean a() {
        long j = this.a;
        if (j > 0) {
            long j2 = this.b;
            if (j2 > 0 && j2 > j && this.c > 0) {
                return true;
            }
        }
        return false;
    }

    public long b() {
        return this.b - this.a;
    }

    public h c() {
        return new h(this.b, new g(this.c, b()));
    }

    public String toString() {
        return "NtpRecord{requestSentTimestamp=" + this.a + ", responseReceivedTimestamp=" + this.b + ", serverTime=" + this.c + ", localTime=" + this.d + ", selected=" + this.e + '}';
    }
}
