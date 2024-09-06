package com.netease.nimlib.biz.e.j;

import u.aly.cs;

/* compiled from: DeleteSessionHistoryMsgNotify.java */
@com.netease.nimlib.biz.e.b(a = cs.b.g, b = {"118"})
/* loaded from: classes.dex */
public class j extends com.netease.nimlib.biz.e.a {
    private com.netease.nimlib.push.packet.b.c c;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        this.c = cVar;
        fVar.a(cVar);
        return null;
    }

    public com.netease.nimlib.push.packet.b.c a() {
        return this.c;
    }
}
