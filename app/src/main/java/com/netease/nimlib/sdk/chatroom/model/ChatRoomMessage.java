package com.netease.nimlib.sdk.chatroom.model;

import com.netease.nimlib.sdk.msg.model.IMMessage;
import java.util.List;

/* loaded from: classes.dex */
public interface ChatRoomMessage extends IMMessage {
    CustomChatRoomMessageConfig getChatRoomConfig();

    ChatRoomMessageExtension getChatRoomMessageExtension();

    String getNotifyTargetTags();

    boolean isHighPriorityMessage();

    void setChatRoomConfig(CustomChatRoomMessageConfig customChatRoomMessageConfig);

    void setLocX(Double d);

    void setLocY(Double d);

    void setLocZ(Double d);

    void setNotifyTargetTags(String str);

    void setToAccounts(List<String> list);
}
