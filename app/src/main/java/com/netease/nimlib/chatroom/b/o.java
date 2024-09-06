package com.netease.nimlib.chatroom.b;

import com.netease.nimlib.chatroom.d.s;
import com.netease.nimlib.chatroom.model.ChatRoomMessageImpl;
import com.netease.nimlib.sdk.msg.constant.MsgStatusEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import java.util.ArrayList;

/* compiled from: RoomTalkResponseHandler.java */
/* loaded from: classes.dex */
public class o extends e {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        ChatRoomMessageImpl chatRoomMessageImpl;
        s sVar = (s) aVar;
        com.netease.nimlib.biz.d.a b = b(aVar);
        short r = aVar.r();
        if (b == null || b.j() == null || !(b.j() instanceof com.netease.nimlib.i.k)) {
            chatRoomMessageImpl = null;
        } else {
            com.netease.nimlib.i.k kVar = (com.netease.nimlib.i.k) b.j();
            chatRoomMessageImpl = (ChatRoomMessageImpl) kVar.g()[0];
            com.netease.nimlib.chatroom.e.a(chatRoomMessageImpl, r);
            kVar.a(r).b();
        }
        if (chatRoomMessageImpl != null) {
            chatRoomMessageImpl.setStatus(MsgStatusEnum.statusOfValue((aVar.n() ? MsgStatusEnum.success : MsgStatusEnum.fail).getValue()));
            if (aVar.n()) {
                ArrayList arrayList = new ArrayList(1);
                arrayList.add(chatRoomMessageImpl);
                com.netease.nimlib.chatroom.f u2 = com.netease.nimlib.chatroom.c.a().u(aVar.j().o());
                if (u2 != null) {
                    u2.a(arrayList);
                }
                com.netease.nimlib.push.packet.b.c a = sVar.a();
                chatRoomMessageImpl.setTime(a.e(20));
                chatRoomMessageImpl.setFromAccount(a.c(21));
                chatRoomMessageImpl.setSessionId(a.c(22));
                chatRoomMessageImpl.setCallbackExtension(a.c(27));
                chatRoomMessageImpl.setYidunAntiSpamRes(a.c(33));
            }
            com.netease.nimlib.n.g.a().c(chatRoomMessageImpl);
            com.netease.nimlib.chatroom.l.a(chatRoomMessageImpl);
            com.netease.nimlib.session.d.a().b(chatRoomMessageImpl.getUuid());
            com.netease.nimlib.n.g.a().a((IMMessage) chatRoomMessageImpl, (int) r);
        }
    }
}
