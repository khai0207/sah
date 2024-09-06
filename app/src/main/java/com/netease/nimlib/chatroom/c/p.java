package com.netease.nimlib.chatroom.c;

import u.aly.df;

/* compiled from: RoomTalkRequest.java */
/* loaded from: classes.dex */
public class p extends com.netease.nimlib.biz.d.a {
    private com.netease.nimlib.push.packet.b.c a;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return df.k;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 6;
    }

    public p(com.netease.nimlib.push.packet.b.c cVar) {
        this.a = cVar;
        a(true);
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.a);
        com.netease.nimlib.log.b.J("************ sent chatroom message request begin ****************");
        com.netease.nimlib.log.b.a(b(), c(), "chatroom send message", this.a);
        com.netease.nimlib.log.b.J("************ sent chatroom message request end ****************");
        return bVar;
    }

    public com.netease.nimlib.push.packet.b.c d() {
        return this.a;
    }
}
