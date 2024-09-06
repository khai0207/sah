package com.netease.nimlib.push.packet.a.b.a;

import java.math.BigInteger;

/* compiled from: GLVMultiplier.java */
/* loaded from: classes.dex */
public class m extends a {
    protected final d a;
    protected final com.netease.nimlib.push.packet.a.b.a.b.b b;

    public m(d dVar, com.netease.nimlib.push.packet.a.b.a.b.b bVar) {
        if (dVar == null || dVar.g() == null) {
            throw new IllegalArgumentException("Need curve with known group order");
        }
        this.a = dVar;
        this.b = bVar;
    }

    @Override // com.netease.nimlib.push.packet.a.b.a.a
    protected h b(h hVar, BigInteger bigInteger) {
        if (!this.a.a(hVar.c())) {
            throw new IllegalStateException();
        }
        BigInteger[] a = this.b.a(bigInteger.mod(hVar.c().g()));
        BigInteger bigInteger2 = a[0];
        BigInteger bigInteger3 = a[1];
        i a2 = this.b.a();
        if (this.b.b()) {
            return b.a(hVar, bigInteger2, a2, bigInteger3);
        }
        return b.a(hVar, bigInteger2, a2.a(hVar), bigInteger3);
    }
}
