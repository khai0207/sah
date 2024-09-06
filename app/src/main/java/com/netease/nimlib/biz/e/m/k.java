package com.netease.nimlib.biz.e.m;

/* compiled from: UpdateUserInfoResponse.java */
@com.netease.nimlib.biz.e.b(a = 3, b = {"10"})
/* loaded from: classes.dex */
public class k extends com.netease.nimlib.biz.e.a {
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
