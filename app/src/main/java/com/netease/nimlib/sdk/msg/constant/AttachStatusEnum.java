package com.netease.nimlib.sdk.msg.constant;

/* loaded from: classes.dex */
public enum AttachStatusEnum {
    def(0),
    transferring(1),
    transferred(2),
    fail(3),
    cancel(4);

    private int value;

    AttachStatusEnum(int i) {
        this.value = i;
    }

    public static AttachStatusEnum statusOfValue(int i) {
        for (AttachStatusEnum attachStatusEnum : values()) {
            if (attachStatusEnum.getValue() == i) {
                return attachStatusEnum;
            }
        }
        return def;
    }

    public int getValue() {
        return this.value;
    }
}
