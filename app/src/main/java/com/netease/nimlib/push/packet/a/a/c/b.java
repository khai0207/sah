package com.netease.nimlib.push.packet.a.a.c;

import java.math.BigInteger;

/* compiled from: DHParameters.java */
/* loaded from: classes.dex */
public class b implements com.netease.nimlib.push.packet.a.a.a {
    private BigInteger a;
    private BigInteger b;
    private BigInteger c;
    private BigInteger d;
    private int e;
    private int f;
    private c g;

    public b(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, int i, int i2, BigInteger bigInteger4, c cVar) {
        if (i2 != 0) {
            if (i2 > bigInteger.bitLength()) {
                throw new IllegalArgumentException("when l value specified, it must satisfy 2^(l-1) <= p");
            }
            if (i2 < i) {
                throw new IllegalArgumentException("when l value specified, it may not be less than m value");
            }
        }
        if (i > bigInteger.bitLength()) {
            throw new IllegalArgumentException("unsafe p value so small specific l required");
        }
        this.a = bigInteger2;
        this.b = bigInteger;
        this.c = bigInteger3;
        this.e = i;
        this.f = i2;
        this.d = bigInteger4;
        this.g = cVar;
    }

    public BigInteger a() {
        return this.b;
    }

    public BigInteger b() {
        return this.a;
    }

    public BigInteger c() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        if (c() != null) {
            if (!c().equals(bVar.c())) {
                return false;
            }
        } else if (bVar.c() != null) {
            return false;
        }
        return bVar.a().equals(this.b) && bVar.b().equals(this.a);
    }

    public int hashCode() {
        return (a().hashCode() ^ b().hashCode()) ^ (c() != null ? c().hashCode() : 0);
    }
}
