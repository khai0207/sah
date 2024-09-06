package com.netease.nimlib.sdk.chatroom.model;

import java.io.Serializable;
import java.util.Map;

/* loaded from: classes.dex */
public class ChatRoomMessageExtension implements Serializable {
    private String avatar;
    private String nick;
    private long roleInfoTimeTag = -1;
    private Map<String, Object> senderExtension;

    public long getRoleInfoTimeTag() {
        return this.roleInfoTimeTag;
    }

    public void setRoleInfoTimeTag(long j) {
        this.roleInfoTimeTag = j;
    }

    public String getSenderNick() {
        return this.nick;
    }

    public void setSenderNick(String str) {
        this.nick = str;
    }

    public String getSenderAvatar() {
        return this.avatar;
    }

    public void setSenderAvatar(String str) {
        this.avatar = str;
    }

    public Map<String, Object> getSenderExtension() {
        return this.senderExtension;
    }

    public void setSenderExtension(Map<String, Object> map) {
        this.senderExtension = map;
    }
}
