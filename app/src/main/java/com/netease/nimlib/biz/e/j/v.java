package com.netease.nimlib.biz.e.j;

import u.aly.cs;

/* compiled from: SendMessageReceiptResponse.java */
@com.netease.nimlib.biz.e.b(a = cs.b.g, b = {"11"})
/* loaded from: classes.dex */
public class v extends com.netease.nimlib.biz.e.a {
    private long c;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        this.c = com.netease.nimlib.push.packet.c.d.a(fVar).e(7);
        return null;
    }

    public long a() {
        return this.c;
    }
}
