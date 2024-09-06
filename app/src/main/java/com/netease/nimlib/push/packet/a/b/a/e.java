package com.netease.nimlib.push.packet.a.b.a;

import java.math.BigInteger;

/* compiled from: ECFieldElement.java */
/* loaded from: classes.dex */
public abstract class e implements c {

    /* compiled from: ECFieldElement.java */
    /* loaded from: classes.dex */
    public static abstract class a extends e {
    }

    public abstract e a(e eVar);

    public abstract BigInteger a();

    public abstract int b();

    public abstract e b(e eVar);

    public abstract e c();

    public abstract e d();

    public abstract e e();

    public abstract e f();

    public int g() {
        return a().bitLength();
    }

    public boolean h() {
        return g() == 1;
    }

    public boolean i() {
        return a().signum() == 0;
    }

    public boolean j() {
        return a().testBit(0);
    }

    public String toString() {
        return a().toString(16);
    }

    public byte[] k() {
        return com.netease.nimlib.push.packet.a.c.b.a((b() + 7) / 8, a());
    }
}
