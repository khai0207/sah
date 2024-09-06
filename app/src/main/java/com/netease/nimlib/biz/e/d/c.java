package com.netease.nimlib.biz.e.d;

/* compiled from: GetBackSourceTokenResponse.java */
@com.netease.nimlib.biz.e.b(a = 6, b = {"30"})
/* loaded from: classes.dex */
public class c extends com.netease.nimlib.biz.e.a {
    private byte[] c;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        this.c = fVar.b().array();
        return null;
    }

    public byte[] a() {
        return this.c;
    }
}
