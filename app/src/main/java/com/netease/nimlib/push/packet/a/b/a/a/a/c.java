package com.netease.nimlib.push.packet.a.b.a.a.a;

import com.netease.nimlib.push.packet.a.b.a.e;
import java.math.BigInteger;

/* compiled from: SM2P256V1FieldElement.java */
/* loaded from: classes.dex */
public class c extends e.a {
    public static final BigInteger g = a.i;
    protected int[] h;

    public c(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(g) >= 0) {
            throw new IllegalArgumentException("x value invalid for SM2P256V1FieldElement");
        }
        this.h = b.a(bigInteger);
    }

    public c() {
        this.h = com.netease.nimlib.push.packet.a.b.c.c.a();
    }

    protected c(int[] iArr) {
        this.h = iArr;
    }

    @Override // com.netease.nimlib.push.packet.a.b.a.e
    public boolean i() {
        return com.netease.nimlib.push.packet.a.b.c.c.b(this.h);
    }

    @Override // com.netease.nimlib.push.packet.a.b.a.e
    public boolean h() {
        return com.netease.nimlib.push.packet.a.b.c.c.a(this.h);
    }

    @Override // com.netease.nimlib.push.packet.a.b.a.e
    public boolean j() {
        return com.netease.nimlib.push.packet.a.b.c.c.a(this.h, 0) == 1;
    }

    @Override // com.netease.nimlib.push.packet.a.b.a.e
    public BigInteger a() {
        return com.netease.nimlib.push.packet.a.b.c.c.c(this.h);
    }

    @Override // com.netease.nimlib.push.packet.a.b.a.e
    public int b() {
        return g.bitLength();
    }

    @Override // com.netease.nimlib.push.packet.a.b.a.e
    public e a(e eVar) {
        int[] a = com.netease.nimlib.push.packet.a.b.c.c.a();
        b.a(this.h, ((c) eVar).h, a);
        return new c(a);
    }

    @Override // com.netease.nimlib.push.packet.a.b.a.e
    public e b(e eVar) {
        int[] a = com.netease.nimlib.push.packet.a.b.c.c.a();
        b.b(this.h, ((c) eVar).h, a);
        return new c(a);
    }

    @Override // com.netease.nimlib.push.packet.a.b.a.e
    public e c() {
        int[] a = com.netease.nimlib.push.packet.a.b.c.c.a();
        b.a(this.h, a);
        return new c(a);
    }

    @Override // com.netease.nimlib.push.packet.a.b.a.e
    public e d() {
        int[] a = com.netease.nimlib.push.packet.a.b.c.c.a();
        b.c(this.h, a);
        return new c(a);
    }

    @Override // com.netease.nimlib.push.packet.a.b.a.e
    public e e() {
        int[] a = com.netease.nimlib.push.packet.a.b.c.c.a();
        com.netease.nimlib.push.packet.a.b.c.a.a(b.a, this.h, a);
        return new c(a);
    }

    @Override // com.netease.nimlib.push.packet.a.b.a.e
    public e f() {
        int[] iArr = this.h;
        if (com.netease.nimlib.push.packet.a.b.c.c.b(iArr) || com.netease.nimlib.push.packet.a.b.c.c.a(iArr)) {
            return this;
        }
        int[] a = com.netease.nimlib.push.packet.a.b.c.c.a();
        b.c(iArr, a);
        b.b(a, iArr, a);
        int[] a2 = com.netease.nimlib.push.packet.a.b.c.c.a();
        b.a(a, 2, a2);
        b.b(a2, a, a2);
        int[] a3 = com.netease.nimlib.push.packet.a.b.c.c.a();
        b.a(a2, 2, a3);
        b.b(a3, a, a3);
        b.a(a3, 6, a);
        b.b(a, a3, a);
        int[] a4 = com.netease.nimlib.push.packet.a.b.c.c.a();
        b.a(a, 12, a4);
        b.b(a4, a, a4);
        b.a(a4, 6, a);
        b.b(a, a3, a);
        b.c(a, a3);
        b.b(a3, iArr, a3);
        b.a(a3, 31, a4);
        b.b(a4, a3, a);
        b.a(a4, 32, a4);
        b.b(a4, a, a4);
        b.a(a4, 62, a4);
        b.b(a4, a, a4);
        b.a(a4, 4, a4);
        b.b(a4, a2, a4);
        b.a(a4, 32, a4);
        b.b(a4, iArr, a4);
        b.a(a4, 62, a4);
        b.c(a4, a2);
        if (com.netease.nimlib.push.packet.a.b.c.c.a(iArr, a2)) {
            return new c(a4);
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof c) {
            return com.netease.nimlib.push.packet.a.b.c.c.a(this.h, ((c) obj).h);
        }
        return false;
    }

    public int hashCode() {
        return g.hashCode() ^ com.netease.nimlib.push.packet.a.c.a.a(this.h, 0, 8);
    }
}
