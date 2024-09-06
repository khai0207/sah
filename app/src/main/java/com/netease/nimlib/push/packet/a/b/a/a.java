package com.netease.nimlib.push.packet.a.b.a;

import java.math.BigInteger;

/* compiled from: AbstractECMultiplier.java */
/* loaded from: classes.dex */
public abstract class a implements g {
    protected abstract h b(h hVar, BigInteger bigInteger);

    @Override // com.netease.nimlib.push.packet.a.b.a.g
    public h a(h hVar, BigInteger bigInteger) {
        int signum = bigInteger.signum();
        if (signum == 0 || hVar.o()) {
            return hVar.c().c();
        }
        h b = b(hVar, bigInteger.abs());
        if (signum <= 0) {
            b = b.s();
        }
        return a(b);
    }

    protected h a(h hVar) {
        return b.a(hVar);
    }
}
