package com.netease.nimlib.sdk.chatroom.model;

import java.io.Serializable;
import java.util.Map;

/* loaded from: classes.dex */
public class ChatRoomMemberUpdate implements Serializable {
    private String avatar;
    private Map<String, Object> extension;
    private boolean needSave = false;
    private String nick;

    public String getNick() {
        return this.nick;
    }

    public void setNick(String str) {
        this.nick = str;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public Map<String, Object> getExtension() {
        return this.extension;
    }

    public void setExtension(Map<String, Object> map) {
        this.extension = map;
    }

    public boolean isNeedSave() {
        return this.needSave;
    }

    public void setNeedSave(boolean z) {
        this.needSave = z;
    }
}
