package com.netease.nimlib.push.packet.a.a.c;

import java.math.BigInteger;

/* compiled from: DSAParameters.java */
/* loaded from: classes.dex */
public class d implements com.netease.nimlib.push.packet.a.a.a {
    private BigInteger a;
    private BigInteger b;
    private BigInteger c;
    private e d;

    public d(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, e eVar) {
        this.a = bigInteger3;
        this.c = bigInteger;
        this.b = bigInteger2;
        this.d = eVar;
    }

    public BigInteger a() {
        return this.c;
    }

    public BigInteger b() {
        return this.b;
    }

    public BigInteger c() {
        return this.a;
    }

    public e d() {
        return this.d;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return dVar.a().equals(this.c) && dVar.b().equals(this.b) && dVar.c().equals(this.a);
    }

    public int hashCode() {
        return (a().hashCode() ^ b().hashCode()) ^ c().hashCode();
    }
}
