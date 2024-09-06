package com.netease.nimlib.sdk.msg.constant;

/* loaded from: classes.dex */
public enum NotificationExtraTypeEnum {
    MESSAGE(0),
    JSON_ARR_STR(1);

    private final int value;

    NotificationExtraTypeEnum(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
