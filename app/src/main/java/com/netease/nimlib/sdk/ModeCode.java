package com.netease.nimlib.sdk;

/* loaded from: classes.dex */
public enum ModeCode {
    INIT(0),
    IM(1),
    CHAT_ROOM_INDEPENDENT(2);

    private int value;

    ModeCode(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
