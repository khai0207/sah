package com.netease.nimlib.chatroom.plugin;

import android.text.TextUtils;
import com.netease.nimlib.biz.d.a;
import com.netease.nimlib.chatroom.c;
import com.netease.nimlib.chatroom.d;
import com.netease.nimlib.chatroom.m;
import com.netease.nimlib.chatroom.o;
import com.netease.nimlib.plugin.interact.IChatRoomInteract;

/* loaded from: classes.dex */
public class ChatRoomInteract implements IChatRoomInteract {
    public String getRoomLinkAddress(String str) {
        return m.a().b(str);
    }

    public void sendRequest(a aVar) {
        d.e().a(aVar);
    }

    @Override // com.netease.nimlib.plugin.interact.IChatRoomInteract
    public void sendRequest(String str, a aVar) {
        d.e().a(str, aVar);
    }

    @Override // com.netease.nimlib.plugin.interact.IChatRoomInteract
    public boolean independent(String str) {
        return c.a().l(str);
    }

    @Override // com.netease.nimlib.plugin.interact.IChatRoomInteract
    public void addSendTask(final com.netease.nimlib.biz.g.c cVar, String str) {
        d.e().a(new o(str, cVar.b()) { // from class: com.netease.nimlib.chatroom.plugin.ChatRoomInteract.1
            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
            public void a(com.netease.nimlib.biz.e.a aVar) {
                super.a(aVar);
                cVar.a(aVar);
            }
        }, str);
    }

    @Override // com.netease.nimlib.plugin.interact.IChatRoomInteract
    public String getAppKeyByRoomId(String str) {
        if (independent(str)) {
            return c.a().m(str);
        }
        return null;
    }

    @Override // com.netease.nimlib.plugin.interact.IChatRoomInteract
    public String getRoomIdByAppKey(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return c.a().n(str);
    }
}
