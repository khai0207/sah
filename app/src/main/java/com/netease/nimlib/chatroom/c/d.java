package com.netease.nimlib.chatroom.c;

import u.aly.df;

/* compiled from: EnterRoomRequest.java */
/* loaded from: classes.dex */
public class d extends com.netease.nimlib.biz.d.a {
    private byte a;
    private com.netease.nimlib.push.packet.b.c b;
    private com.netease.nimlib.push.packet.b.c c;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return df.k;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 2;
    }

    public d(int i, com.netease.nimlib.push.packet.b.c cVar, com.netease.nimlib.push.packet.b.c cVar2) {
        this.a = (byte) i;
        this.b = cVar;
        this.c = cVar2;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.a);
        bVar.a(this.b);
        bVar.a(this.c);
        com.netease.nimlib.log.b.J("************ enter room request begin ****************");
        com.netease.nimlib.log.b.a(b(), c(), "type = " + ((int) this.a));
        com.netease.nimlib.log.b.a(b(), c(), "chatRoomLoginTag", this.b);
        com.netease.nimlib.log.b.a(b(), c(), "loginReqTag", this.c);
        com.netease.nimlib.log.b.J("************ enter room request end ****************");
        return bVar;
    }
}
