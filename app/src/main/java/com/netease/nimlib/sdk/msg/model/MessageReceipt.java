package com.netease.nimlib.sdk.msg.model;

/* loaded from: classes.dex */
public class MessageReceipt {
    private String sessionId;
    private long time;

    public MessageReceipt(String str, long j) {
        this.sessionId = str;
        this.time = j;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public long getTime() {
        return this.time;
    }
}
