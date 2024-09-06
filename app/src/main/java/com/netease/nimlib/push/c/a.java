package com.netease.nimlib.push.c;

/* compiled from: QuickConnectABTest.java */
/* loaded from: classes.dex */
public class a {
    private static a a;
    private final int g = com.netease.nimlib.abtest.b.a;
    private final boolean b = com.netease.nimlib.abtest.b.k();
    private final boolean c = com.netease.nimlib.abtest.b.i();
    private final int d = com.netease.nimlib.abtest.b.h();
    private final int e = com.netease.nimlib.abtest.b.g();
    private final int f = com.netease.nimlib.abtest.b.j();

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (a == null) {
                a = new a();
            }
            aVar = a;
        }
        return aVar;
    }

    private a() {
        com.netease.nimlib.log.b.d("QuickConnectABTest", String.format("constructor: %s", this));
    }

    public boolean b() {
        return this.b;
    }

    public boolean c() {
        return this.c;
    }

    public long d() {
        return this.g * 1000;
    }

    public long e() {
        return this.d * 1000;
    }

    public long f() {
        return this.e * 1000;
    }

    public long g() {
        return this.f * 1000;
    }

    public String toString() {
        return "QuickConnectABTest{enabled=" + this.b + ", isRegularAsFirst=" + this.c + ", timeoutQuick=" + this.d + ", timeoutTotal=" + this.e + ", ttlSucceedLink=" + this.f + ", timeoutRegular=" + this.g + '}';
    }
}
