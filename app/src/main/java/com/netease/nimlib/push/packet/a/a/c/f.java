package com.netease.nimlib.push.packet.a.a.c;

import java.math.BigInteger;

/* compiled from: ECDomainParameters.java */
/* loaded from: classes.dex */
public class f implements com.netease.nimlib.push.packet.a.b.a.c {
    private com.netease.nimlib.push.packet.a.b.a.d g;
    private byte[] h;
    private com.netease.nimlib.push.packet.a.b.a.h i;
    private BigInteger j;
    private BigInteger k;
    private BigInteger l;

    public f(com.netease.nimlib.push.packet.a.b.a.d dVar, com.netease.nimlib.push.packet.a.b.a.h hVar, BigInteger bigInteger, BigInteger bigInteger2) {
        this(dVar, hVar, bigInteger, bigInteger2, null);
    }

    public f(com.netease.nimlib.push.packet.a.b.a.d dVar, com.netease.nimlib.push.packet.a.b.a.h hVar, BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        this.l = null;
        if (dVar == null) {
            throw new NullPointerException("curve");
        }
        if (bigInteger == null) {
            throw new NullPointerException("n");
        }
        this.g = dVar;
        this.i = a(dVar, hVar);
        this.j = bigInteger;
        this.k = bigInteger2;
        this.h = com.netease.nimlib.push.packet.a.c.a.b(bArr);
    }

    public com.netease.nimlib.push.packet.a.b.a.d a() {
        return this.g;
    }

    public com.netease.nimlib.push.packet.a.b.a.h b() {
        return this.i;
    }

    public BigInteger c() {
        return this.j;
    }

    public BigInteger d() {
        return this.k;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        return this.g.a(fVar.g) && this.i.a(fVar.i) && this.j.equals(fVar.j) && this.k.equals(fVar.k);
    }

    public int hashCode() {
        return (((((this.g.hashCode() * 37) ^ this.i.hashCode()) * 37) ^ this.j.hashCode()) * 37) ^ this.k.hashCode();
    }

    static com.netease.nimlib.push.packet.a.b.a.h a(com.netease.nimlib.push.packet.a.b.a.d dVar, com.netease.nimlib.push.packet.a.b.a.h hVar) {
        if (hVar == null) {
            throw new IllegalArgumentException("Point has null value");
        }
        com.netease.nimlib.push.packet.a.b.a.h n = com.netease.nimlib.push.packet.a.b.a.b.a(dVar, hVar).n();
        if (n.o()) {
            throw new IllegalArgumentException("Point at infinity");
        }
        if (n.p()) {
            return n;
        }
        throw new IllegalArgumentException("Point not on curve");
    }
}
