package com.netease.nimlib.chatroom.d;

import u.aly.df;

/* compiled from: QueryTagMembersCountResponse.java */
@com.netease.nimlib.biz.e.b(a = df.k, b = {"32"})
/* loaded from: classes.dex */
public class q extends com.netease.nimlib.biz.e.a {
    private long c;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        this.c = fVar.f();
        return null;
    }

    public long a() {
        return this.c;
    }
}
