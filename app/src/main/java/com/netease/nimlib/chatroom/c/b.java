package com.netease.nimlib.chatroom.c;

import u.aly.df;

/* compiled from: ChatRoomMsgAckRequest.java */
/* loaded from: classes.dex */
public class b extends com.netease.nimlib.biz.d.a {
    private com.netease.nimlib.push.packet.b.c a;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return df.k;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 35;
    }

    public b(com.netease.nimlib.push.packet.b.c cVar) {
        this.a = cVar;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.a);
        com.netease.nimlib.log.b.J("************ ChatRoomMsgAckRequest begin ****************");
        com.netease.nimlib.log.b.a(b(), c(), "chat room msg ack", this.a);
        com.netease.nimlib.log.b.J("************ ChatRoomMsgAckRequest end ****************");
        return bVar;
    }
}
