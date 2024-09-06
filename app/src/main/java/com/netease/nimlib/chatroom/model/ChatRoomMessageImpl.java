package com.netease.nimlib.chatroom.model;

import com.netease.nimlib.sdk.chatroom.model.ChatRoomMessage;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomMessageExtension;
import com.netease.nimlib.sdk.chatroom.model.CustomChatRoomMessageConfig;
import com.netease.nimlib.session.IMMessageImpl;
import java.util.Collection;
import java.util.List;
import org.json.JSONArray;

/* loaded from: classes.dex */
public class ChatRoomMessageImpl extends IMMessageImpl implements ChatRoomMessage {
    private CustomChatRoomMessageConfig chatRoomMessageConfig;
    private ChatRoomMessageExtension chatRoomMessageExtension;
    private boolean isHighPriorityMessage = false;
    private Double locX;
    private Double locY;
    private Double locZ;
    private Boolean needHighPriorityMsgAck;
    private String notifyTargetTags;
    private String toAccounts;

    @Override // com.netease.nimlib.sdk.chatroom.model.ChatRoomMessage
    public ChatRoomMessageExtension getChatRoomMessageExtension() {
        return this.chatRoomMessageExtension;
    }

    public void setChatRoomMessageExtension(ChatRoomMessageExtension chatRoomMessageExtension) {
        this.chatRoomMessageExtension = chatRoomMessageExtension;
    }

    @Override // com.netease.nimlib.sdk.chatroom.model.ChatRoomMessage
    public CustomChatRoomMessageConfig getChatRoomConfig() {
        return this.chatRoomMessageConfig;
    }

    @Override // com.netease.nimlib.sdk.chatroom.model.ChatRoomMessage
    public void setChatRoomConfig(CustomChatRoomMessageConfig customChatRoomMessageConfig) {
        this.chatRoomMessageConfig = customChatRoomMessageConfig;
    }

    @Override // com.netease.nimlib.sdk.chatroom.model.ChatRoomMessage
    public boolean isHighPriorityMessage() {
        return this.isHighPriorityMessage;
    }

    public void setHighPriorityMessage() {
        this.isHighPriorityMessage = true;
    }

    @Override // com.netease.nimlib.sdk.chatroom.model.ChatRoomMessage
    public String getNotifyTargetTags() {
        return this.notifyTargetTags;
    }

    @Override // com.netease.nimlib.sdk.chatroom.model.ChatRoomMessage
    public void setNotifyTargetTags(String str) {
        this.notifyTargetTags = str;
    }

    public Double getLocX() {
        return this.locX;
    }

    @Override // com.netease.nimlib.sdk.chatroom.model.ChatRoomMessage
    public void setLocX(Double d) {
        this.locX = d;
    }

    public Double getLocY() {
        return this.locY;
    }

    @Override // com.netease.nimlib.sdk.chatroom.model.ChatRoomMessage
    public void setLocY(Double d) {
        this.locY = d;
    }

    public Double getLocZ() {
        return this.locZ;
    }

    @Override // com.netease.nimlib.sdk.chatroom.model.ChatRoomMessage
    public void setLocZ(Double d) {
        this.locZ = d;
    }

    @Override // com.netease.nimlib.sdk.chatroom.model.ChatRoomMessage
    public void setToAccounts(List<String> list) {
        if (list != null) {
            try {
                this.toAccounts = new JSONArray((Collection) list).toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getToAccounts() {
        return this.toAccounts;
    }

    public Boolean isNeedHighPriorityMsgAck() {
        return this.needHighPriorityMsgAck;
    }

    public void setNeedHighPriorityMsgAck(Boolean bool) {
        this.needHighPriorityMsgAck = bool;
    }
}
