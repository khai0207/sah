package com.netease.nimlib.sdk.msg.model;

/* loaded from: classes.dex */
public enum GetMessageDirectionEnum {
    FORWARD(0),
    BACKWARD(1);

    private int value;

    GetMessageDirectionEnum(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static GetMessageDirectionEnum typeOfValue(int i) {
        for (GetMessageDirectionEnum getMessageDirectionEnum : values()) {
            if (getMessageDirectionEnum.getValue() == i) {
                return getMessageDirectionEnum;
            }
        }
        return FORWARD;
    }
}
