package com.netease.nimlib.sdk.chatroom.model;

import java.io.Serializable;
import java.util.Map;

/* loaded from: classes.dex */
public class ChatRoomUpdateInfo implements Serializable {
    private String announcement;
    private String broadcastUrl;
    private Map<String, Object> extension;
    private String name;
    private int queueLevel;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getAnnouncement() {
        return this.announcement;
    }

    public void setAnnouncement(String str) {
        this.announcement = str;
    }

    public String getBroadcastUrl() {
        return this.broadcastUrl;
    }

    public void setBroadcastUrl(String str) {
        this.broadcastUrl = str;
    }

    public Map<String, Object> getExtension() {
        return this.extension;
    }

    public void setExtension(Map<String, Object> map) {
        this.extension = map;
    }

    public void setQueueLevel(int i) {
        this.queueLevel = i;
    }

    public int getQueueLevel() {
        return this.queueLevel;
    }
}
