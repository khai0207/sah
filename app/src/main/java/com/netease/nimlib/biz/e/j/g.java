package com.netease.nimlib.biz.e.j;

import u.aly.cs;

/* compiled from: DeleteMsgSelfResponse.java */
@com.netease.nimlib.biz.e.b(a = cs.b.g, b = {"23"})
/* loaded from: classes.dex */
public class g extends com.netease.nimlib.biz.e.a {
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
