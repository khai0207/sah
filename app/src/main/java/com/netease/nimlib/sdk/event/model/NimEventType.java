package com.netease.nimlib.sdk.event.model;

/* loaded from: classes.dex */
public enum NimEventType {
    ONLINE_STATE(1);

    private int eventType;

    public int getValue() {
        return this.eventType;
    }

    NimEventType(int i) {
        this.eventType = i;
    }
}
