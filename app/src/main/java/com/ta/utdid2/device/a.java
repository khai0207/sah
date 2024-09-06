package com.ta.utdid2.device;

/* loaded from: classes.dex */
public class a {

    /* renamed from: b, reason: collision with other field name */
    public String f7b = "";
    public String c = "";
    public String d = "";
    public String e = "";
    public long a = 0;
    public long b = 0;

    public void a(long j) {
        this.b = j;
    }

    public void b(long j) {
        this.a = j;
    }

    public void c(String str) {
        this.d = str;
    }

    public String d() {
        return this.f7b;
    }

    public String e() {
        return this.e;
    }

    public String getDeviceId() {
        return this.d;
    }

    public String getImsi() {
        return this.c;
    }

    public long a() {
        return this.a;
    }

    public void b(String str) {
        this.c = str;
    }

    public void d(String str) {
        this.e = str;
    }

    public void a(String str) {
        this.f7b = str;
    }
}
