package com.netease.nimlib.sdk.msg.model;

import java.io.Serializable;

/* loaded from: classes.dex */
public class MessageRobotInfo implements Serializable {
    private final String account;
    private final String customContent;
    private final String function;
    private final String topic;

    public MessageRobotInfo(String str, String str2, String str3, String str4) {
        this.function = str;
        this.topic = str2;
        this.customContent = str3;
        this.account = str4;
    }

    public String getFunction() {
        return this.function;
    }

    public String getTopic() {
        return this.topic;
    }

    public String getCustomContent() {
        return this.customContent;
    }

    public String getAccount() {
        return this.account;
    }

    public String toString() {
        return "MessageRobotInfo{function='" + this.function + "', topic='" + this.topic + "', customContent='" + this.customContent + "', account='" + this.account + "'}";
    }
}
