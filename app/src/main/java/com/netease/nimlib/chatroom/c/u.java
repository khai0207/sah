package com.netease.nimlib.chatroom.c;

import android.text.TextUtils;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomTagsInfo;
import u.aly.df;

/* compiled from: UpdateChatRoomTagRequest.java */
/* loaded from: classes.dex */
public class u extends com.netease.nimlib.biz.d.a {
    private com.netease.nimlib.push.packet.b.c a;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return df.k;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 34;
    }

    public u(ChatRoomTagsInfo chatRoomTagsInfo) {
        this.a = a(chatRoomTagsInfo);
    }

    private com.netease.nimlib.push.packet.b.c a(ChatRoomTagsInfo chatRoomTagsInfo) {
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        String tags = chatRoomTagsInfo.getTags();
        if (!TextUtils.isEmpty(tags)) {
            cVar.a(1, tags);
        }
        String notifyTargetTags = chatRoomTagsInfo.getNotifyTargetTags();
        if (!TextUtils.isEmpty(notifyTargetTags)) {
            cVar.a(2, notifyTargetTags);
        }
        Boolean needNotify = chatRoomTagsInfo.getNeedNotify();
        if (needNotify != null) {
            cVar.a(3, needNotify.booleanValue() ? 1 : 0);
        }
        String ext = chatRoomTagsInfo.getExt();
        if (!TextUtils.isEmpty(ext)) {
            cVar.a(4, ext);
        }
        return cVar;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.a);
        com.netease.nimlib.log.b.J("************ update chatroom tag request begin ****************");
        com.netease.nimlib.log.b.a(b(), c(), "chatroom update tag", this.a);
        com.netease.nimlib.log.b.J("************ update chatroom tag request end ****************");
        return bVar;
    }
}
