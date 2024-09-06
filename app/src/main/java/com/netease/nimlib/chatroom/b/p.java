package com.netease.nimlib.chatroom.b;

import android.text.TextUtils;
import com.netease.nimlib.chatroom.d.v;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomTagsUpdateEventImpl;

/* compiled from: UpdateChatRoomTagNotifyHandler.java */
/* loaded from: classes.dex */
public class p extends e {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        if (aVar instanceof v) {
            String o = aVar.j().o();
            if (TextUtils.isEmpty(o)) {
                return;
            }
            com.netease.nimlib.chatroom.l.a(new ChatRoomTagsUpdateEventImpl(o, ((v) aVar).a()));
        }
    }
}
