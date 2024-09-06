package com.netease.nimlib.biz.e.k;

/* compiled from: UpdateCollectResponse.java */
@com.netease.nimlib.biz.e.b(a = 23, b = {"10"})
/* loaded from: classes.dex */
public class s extends com.netease.nimlib.biz.e.a {
    private com.netease.nimlib.session.a c;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        this.c = new com.netease.nimlib.session.a(com.netease.nimlib.push.packet.c.d.a(fVar));
        return null;
    }

    public com.netease.nimlib.session.a a() {
        return this.c;
    }
}
