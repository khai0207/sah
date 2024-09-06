package com.netease.nimlib.chatroom.d;

import u.aly.df;

/* compiled from: RoomTalkNotify.java */
@com.netease.nimlib.biz.e.b(a = df.k, b = {"7#-1"})
/* loaded from: classes.dex */
public class r extends com.netease.nimlib.biz.e.a {
    private com.netease.nimlib.push.packet.b.c c;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        this.c = com.netease.nimlib.push.packet.c.d.a(fVar);
        com.netease.nimlib.log.b.J("************ chatroom receive notify begin ****************");
        com.netease.nimlib.log.b.a(13, 7, "chatroom receive notify", this.c);
        com.netease.nimlib.log.b.J("************ chatroom receive notify end ****************");
        return null;
    }

    public com.netease.nimlib.push.packet.b.c a() {
        return this.c;
    }
}
