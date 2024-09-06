package com.netease.nimlib.sdk.chatroom.constant;

/* loaded from: classes.dex */
public enum ChatRoomModeEnum {
    ALL(0),
    DEPENDENT(1),
    INDEPENDENT(2);

    private int value;

    ChatRoomModeEnum(int i) {
        this.value = i;
    }

    public final int getValue() {
        return this.value;
    }
}
