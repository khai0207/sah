package com.netease.nimlib.push.a.c;

import u.aly.cs;

/* compiled from: SyncResponse.java */
@com.netease.nimlib.biz.e.b(a = cs.b.e, b = {"1"})
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
