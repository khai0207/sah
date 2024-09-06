package com.netease.nimlib.biz.e.f;

/* compiled from: SyncDonopPushResponse.java */
@com.netease.nimlib.biz.e.b(a = 4, b = {"13"})
/* loaded from: classes.dex */
public class d extends com.netease.nimlib.biz.e.a {
    com.netease.nimlib.push.packet.b.c c;
    long d;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        this.c = com.netease.nimlib.push.packet.c.d.a(fVar);
        this.d = fVar.h();
        return null;
    }

    public com.netease.nimlib.push.packet.b.c a() {
        return this.c;
    }

    public long b() {
        return this.d;
    }
}
