package com.netease.nimlib.chatroom.c;

import u.aly.df;

/* compiled from: GetMessagesByTagsRequest.java */
/* loaded from: classes.dex */
public class i extends com.netease.nimlib.biz.d.a {
    private final com.netease.nimlib.push.packet.b.c a;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return df.k;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 36;
    }

    public i(com.netease.nimlib.push.packet.b.c cVar) {
        this.a = cVar;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.a);
        com.netease.nimlib.log.b.J("************ get messages by tag request begin ****************");
        com.netease.nimlib.log.b.a(b(), c(), "ChatRoomTagHistoryMsgRequestTag", this.a);
        com.netease.nimlib.log.b.J("************ get messages by tag request end ****************");
        return bVar;
    }
}
