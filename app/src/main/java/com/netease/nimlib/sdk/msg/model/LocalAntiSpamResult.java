package com.netease.nimlib.sdk.msg.model;

/* loaded from: classes.dex */
public class LocalAntiSpamResult {
    private String content;
    private int operator;

    public LocalAntiSpamResult(int i, String str) {
        this.operator = i;
        this.content = str;
    }

    public int getOperator() {
        return this.operator;
    }

    public String getContent() {
        return this.content;
    }
}
