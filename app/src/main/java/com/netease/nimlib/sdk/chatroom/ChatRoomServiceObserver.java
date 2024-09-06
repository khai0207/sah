package com.netease.nimlib.sdk.chatroom;

import com.netease.nimlib.i.d;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.chatroom.model.CdnRequestData;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomKickOutEvent;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomMessage;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomStatusChangeData;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomTagsUpdateEvent;
import com.netease.nimlib.sdk.msg.model.AttachmentProgress;
import java.util.List;

@d
/* loaded from: classes.dex */
public interface ChatRoomServiceObserver {
    void observeAttachmentProgress(Observer<AttachmentProgress> observer, boolean z);

    void observeCdnRequestData(Observer<CdnRequestData> observer, boolean z);

    void observeKickOutEvent(Observer<ChatRoomKickOutEvent> observer, boolean z);

    void observeMsgStatus(Observer<ChatRoomMessage> observer, boolean z);

    void observeOnlineStatus(Observer<ChatRoomStatusChangeData> observer, boolean z);

    void observeReceiveMessage(Observer<List<ChatRoomMessage>> observer, boolean z);

    void observeTagsUpdate(Observer<ChatRoomTagsUpdateEvent> observer, boolean z);
}
