package com.netease.nimlib.sdk.chatroom.model;

import java.io.Serializable;
import java.util.Map;

/* loaded from: classes.dex */
public class MemberOption implements Serializable {
    private String account;
    private Map<String, Object> notifyExtension;
    private String roomId;

    public MemberOption(String str, String str2) {
        this.roomId = str;
        this.account = str2;
    }

    public String getRoomId() {
        return this.roomId;
    }

    public void setRoomId(String str) {
        this.roomId = str;
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String str) {
        this.account = str;
    }

    public Map<String, Object> getNotifyExtension() {
        return this.notifyExtension;
    }

    public void setNotifyExtension(Map<String, Object> map) {
        this.notifyExtension = map;
    }
}
