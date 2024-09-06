package com.netease.nimlib.chatroom.b;

import android.text.TextUtils;

/* compiled from: RoomCdnNotifyHandler.java */
/* loaded from: classes.dex */
public class k extends e {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        if (aVar instanceof com.netease.nimlib.chatroom.d.b) {
            String o = aVar.j().o();
            if (TextUtils.isEmpty(o)) {
                com.netease.nimlib.log.b.d("RoomCdnNotifyHandler", "cancel update CdnHandler, roomId=" + o);
                return;
            }
            com.netease.nimlib.chatroom.a.b.d().a(o, ((com.netease.nimlib.chatroom.d.b) aVar).a());
        }
    }
}
