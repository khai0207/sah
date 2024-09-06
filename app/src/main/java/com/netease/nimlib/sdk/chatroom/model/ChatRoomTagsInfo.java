package com.netease.nimlib.sdk.chatroom.model;

import java.io.Serializable;

/* loaded from: classes.dex */
public class ChatRoomTagsInfo implements Serializable {
    private String ext;
    private Boolean needNotify;
    private String notifyTargetTags;
    private String tags;

    public ChatRoomTagsInfo() {
    }

    public ChatRoomTagsInfo(String str, String str2, Boolean bool, String str3) {
        this.tags = str;
        this.notifyTargetTags = str2;
        this.needNotify = bool;
        this.ext = str3;
    }

    public String getTags() {
        return this.tags;
    }

    public void setTags(String str) {
        this.tags = str;
    }

    public String getNotifyTargetTags() {
        return this.notifyTargetTags;
    }

    public void setNotifyTargetTags(String str) {
        this.notifyTargetTags = str;
    }

    public Boolean getNeedNotify() {
        return this.needNotify;
    }

    public void setNeedNotify(Boolean bool) {
        this.needNotify = bool;
    }

    public String getExt() {
        return this.ext;
    }

    public void setExt(String str) {
        this.ext = str;
    }
}
