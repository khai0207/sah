package com.netease.nimlib.sdk.msg.model;

import java.io.Serializable;

/* loaded from: classes.dex */
public class MsgThreadOption implements Serializable {
    private long replyMsgIdServer;
    private long replyMsgTime;
    private long threadMsgIdServer;
    private long threadMsgTime;
    private String replyMsgFromAccount = "";
    private String replyMsgToAccount = "";
    private String replyMsgIdClient = "";
    private String threadMsgFromAccount = "";
    private String threadMsgToAccount = "";
    private String threadMsgIdClient = "";

    public String getReplyMsgFromAccount() {
        return this.replyMsgFromAccount;
    }

    public void setReplyMsgFromAccount(String str) {
        this.replyMsgFromAccount = str;
    }

    public String getReplyMsgToAccount() {
        return this.replyMsgToAccount;
    }

    public void setReplyMsgToAccount(String str) {
        this.replyMsgToAccount = str;
    }

    public long getReplyMsgTime() {
        return this.replyMsgTime;
    }

    public void setReplyMsgTime(long j) {
        this.replyMsgTime = j;
    }

    public long getReplyMsgIdServer() {
        return this.replyMsgIdServer;
    }

    public void setReplyMsgIdServer(long j) {
        this.replyMsgIdServer = j;
    }

    public String getReplyMsgIdClient() {
        return this.replyMsgIdClient;
    }

    public void setReplyMsgIdClient(String str) {
        this.replyMsgIdClient = str;
    }

    public String getThreadMsgFromAccount() {
        return this.threadMsgFromAccount;
    }

    public void setThreadMsgFromAccount(String str) {
        this.threadMsgFromAccount = str;
    }

    public String getThreadMsgToAccount() {
        return this.threadMsgToAccount;
    }

    public void setThreadMsgToAccount(String str) {
        this.threadMsgToAccount = str;
    }

    public long getThreadMsgTime() {
        return this.threadMsgTime;
    }

    public void setThreadMsgTime(long j) {
        this.threadMsgTime = j;
    }

    public long getThreadMsgIdServer() {
        return this.threadMsgIdServer;
    }

    public void setThreadMsgIdServer(long j) {
        this.threadMsgIdServer = j;
    }

    public String getThreadMsgIdClient() {
        return this.threadMsgIdClient;
    }

    public void setThreadMsgIdClient(String str) {
        this.threadMsgIdClient = str;
    }
}
