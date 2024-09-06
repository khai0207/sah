package com.netease.nimlib.sdk.msg.constant;

/* loaded from: classes.dex */
public enum MsgStatusEnum {
    draft(-1),
    sending(0),
    success(1),
    fail(2),
    read(3),
    unread(4);

    private int value;

    MsgStatusEnum(int i) {
        this.value = i;
    }

    public static MsgStatusEnum statusOfValue(int i) {
        for (MsgStatusEnum msgStatusEnum : values()) {
            if (msgStatusEnum.getValue() == i) {
                return msgStatusEnum;
            }
        }
        return sending;
    }

    public int getValue() {
        return this.value;
    }
}
