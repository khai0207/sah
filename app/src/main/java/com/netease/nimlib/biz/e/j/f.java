package com.netease.nimlib.biz.e.j;

import u.aly.cs;

/* compiled from: DeleteMsgSelfNotify.java */
@com.netease.nimlib.biz.e.b(a = cs.b.g, b = {"123"})
/* loaded from: classes.dex */
public class f extends com.netease.nimlib.biz.e.a {
    private com.netease.nimlib.push.packet.b.c c;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        fVar.a(cVar);
        this.c = cVar;
        return null;
    }

    public com.netease.nimlib.push.packet.b.c a() {
        return this.c;
    }
}
