package com.netease.nimlib.biz.e.k;

/* compiled from: AddMsgPinResponse.java */
@com.netease.nimlib.biz.e.b(a = 23, b = {"15"})
/* loaded from: classes.dex */
public class c extends com.netease.nimlib.biz.e.a {
    private long c;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        this.c = fVar.h();
        return null;
    }

    public long a() {
        return this.c;
    }
}
