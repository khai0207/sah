package com.netease.nimlib.user;

/* compiled from: UserSpecialTag.java */
/* loaded from: classes.dex */
public class d {
    private String a;
    private int b;
    private int c;
    private long d;
    private long e;

    public d() {
    }

    public d(String str, int i, int i2, long j, long j2) {
        this.a = str;
        this.b = i;
        this.c = i2;
        this.e = j;
        this.d = j2;
    }

    public static final d a(com.netease.nimlib.push.packet.b.c cVar) {
        return new d(cVar.c(0), cVar.d(1), cVar.d(2), cVar.e(3), cVar.e(4));
    }

    public String a() {
        return this.a;
    }

    public boolean b() {
        return this.b == 1;
    }

    public boolean c() {
        return this.c == 1;
    }

    public long d() {
        return this.d;
    }

    public long e() {
        return this.e;
    }

    public void a(String str) {
        this.a = str;
    }

    public void a(int i) {
        this.b = i;
    }

    public void b(int i) {
        this.c = i;
    }

    public void a(long j) {
        this.d = j;
    }

    public void b(long j) {
        this.e = j;
    }
}
