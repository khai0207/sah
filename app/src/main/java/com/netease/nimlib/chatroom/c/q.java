package com.netease.nimlib.chatroom.c;

/* compiled from: SendChatRoomMessageTask.java */
/* loaded from: classes.dex */
public class q extends com.netease.nimlib.chatroom.o {
    private com.netease.nimlib.push.packet.b.c b;

    public q(String str, p pVar, com.netease.nimlib.biz.g.a aVar) {
        super(str, pVar, aVar);
        this.b = pVar.d();
    }

    @Override // com.netease.nimlib.chatroom.o, com.netease.nimlib.biz.g.c
    public boolean a() {
        this.b.a(5, 1);
        this.h.i().b();
        com.netease.nimlib.chatroom.d.e().a(this, this.a);
        return true;
    }
}
