package com.netease.nimlib.apm.event.c;

/* compiled from: Event.java */
/* loaded from: classes.dex */
public class a {
    private long a;
    private String b;
    private long c;
    private String d;
    private long e;

    public a(String str) {
        this.a = -1L;
        this.e = 0L;
        this.b = str;
        this.c = System.currentTimeMillis();
    }

    public a(String str, String str2, long j) {
        this(str);
        this.d = str2;
        this.e = j;
    }

    public a(String str, long j, String str2) {
        this.a = -1L;
        this.e = 0L;
        this.b = str;
        this.d = str2;
        this.c = j;
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return this.d;
    }

    public long c() {
        return this.c;
    }

    public long d() {
        return this.a;
    }

    public void a(long j) {
        this.a = j;
    }

    public long e() {
        return this.e;
    }
}
