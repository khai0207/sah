package com.netease.nimlib.sdk.msg.model;

import java.io.Serializable;

/* loaded from: classes.dex */
public class BroadcastMessage implements Serializable {
    private String content;
    private String fromAccount;
    private long id;
    private long time;

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public String getFromAccount() {
        return this.fromAccount;
    }

    public void setFromAccount(String str) {
        this.fromAccount = str;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long j) {
        this.time = j;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }
}
