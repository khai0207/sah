package com.netease.nimlib.chatroom;

import com.netease.nimlib.sdk.chatroom.ChatRoomServiceObserver;
import com.netease.nimlib.sdk.chatroom.model.CdnRequestData;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomKickOutEvent;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomStatusChangeData;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomTagsUpdateEvent;
import com.netease.nimlib.sdk.msg.model.AttachmentProgress;
import com.netease.nimlib.session.IMMessageImpl;
import java.util.Iterator;
import java.util.List;

/* compiled from: NotificationCenterCR.java */
/* loaded from: classes.dex */
public class l {
    public static void a(ChatRoomStatusChangeData chatRoomStatusChangeData) {
        com.netease.nimlib.i.a.a(ChatRoomServiceObserver.class.getSimpleName() + "/observeOnlineStatus", chatRoomStatusChangeData);
    }

    public static void a(ChatRoomKickOutEvent chatRoomKickOutEvent) {
        com.netease.nimlib.i.a.a(ChatRoomServiceObserver.class.getSimpleName() + "/observeKickOutEvent", chatRoomKickOutEvent);
    }

    public static void a(List<IMMessageImpl> list) {
        Iterator<IMMessageImpl> it = list.iterator();
        while (it.hasNext()) {
            com.netease.nimlib.n.f.a().a(it.next(), 200);
        }
        com.netease.nimlib.i.a.a(ChatRoomServiceObserver.class.getSimpleName() + "/observeReceiveMessage", list);
    }

    public static void a(IMMessageImpl iMMessageImpl) {
        com.netease.nimlib.i.a.a(ChatRoomServiceObserver.class.getSimpleName() + "/observeMsgStatus", iMMessageImpl);
    }

    public static void a(String str, long j, long j2) {
        com.netease.nimlib.i.a.a(ChatRoomServiceObserver.class.getSimpleName() + "/observeAttachmentProgress", new AttachmentProgress(str, j, j2));
    }

    public static void a(CdnRequestData cdnRequestData) {
        com.netease.nimlib.i.a.a(ChatRoomServiceObserver.class.getSimpleName() + "/observeCdnRequestData", cdnRequestData);
    }

    public static void a(ChatRoomTagsUpdateEvent chatRoomTagsUpdateEvent) {
        com.netease.nimlib.i.a.a(ChatRoomServiceObserver.class.getSimpleName() + "/observeTagsUpdate", chatRoomTagsUpdateEvent);
    }
}
