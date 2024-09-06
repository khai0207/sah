package com.netease.nimlib.push.a.c;

/* compiled from: HandshakeResponse.java */
@com.netease.nimlib.biz.e.b(a = 1, b = {"1"})
/* loaded from: classes.dex */
public class a extends com.netease.nimlib.biz.e.a {
    private int c;
    private byte[] d;
    private long e;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        this.c = fVar.f();
        this.d = fVar.d();
        this.e = fVar.h();
        return null;
    }

    public int a() {
        return this.c;
    }

    public byte[] b() {
        return this.d;
    }

    public long c() {
        return this.e;
    }
}
