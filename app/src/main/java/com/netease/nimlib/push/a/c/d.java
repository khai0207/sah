package com.netease.nimlib.push.a.c;

import com.netease.nimlib.push.packet.asymmetric.AsymmetricType;

/* compiled from: NegotiateTransportResponse.java */
@com.netease.nimlib.biz.e.b(a = 1, b = {"5"})
/* loaded from: classes.dex */
public class d extends com.netease.nimlib.biz.e.a {
    private int c;
    private String d;
    private String e;
    private long f;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        return null;
    }

    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar, AsymmetricType asymmetricType) throws Exception {
        com.netease.nimlib.push.packet.b.c a = com.netease.nimlib.push.packet.c.d.a(fVar);
        this.c = a.d(2);
        if (asymmetricType == AsymmetricType.RSA) {
            this.d = a.c(101);
            this.e = a.c(102);
        } else if (asymmetricType == AsymmetricType.SM2) {
            this.d = a.c(103);
            this.e = a.c(104);
        }
        this.f = a.e(100);
        return null;
    }

    public int a() {
        return this.c;
    }

    public String b() {
        return this.d;
    }

    public String c() {
        return this.e;
    }

    public long d() {
        return this.f;
    }
}
