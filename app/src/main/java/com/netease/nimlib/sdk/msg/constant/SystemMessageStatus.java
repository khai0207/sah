package com.netease.nimlib.sdk.msg.constant;

/* loaded from: classes.dex */
public enum SystemMessageStatus {
    init(0),
    passed(1),
    declined(2),
    ignored(3),
    expired(4),
    extension1(100),
    extension2(101),
    extension3(102),
    extension4(103),
    extension5(104);

    private int value;

    SystemMessageStatus(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static SystemMessageStatus statusOfValue(int i) {
        for (SystemMessageStatus systemMessageStatus : values()) {
            if (systemMessageStatus.getValue() == i) {
                return systemMessageStatus;
            }
        }
        return init;
    }
}
