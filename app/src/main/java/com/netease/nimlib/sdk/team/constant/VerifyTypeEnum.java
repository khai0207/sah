package com.netease.nimlib.sdk.team.constant;

/* loaded from: classes.dex */
public enum VerifyTypeEnum {
    Free(0),
    Apply(1),
    Private(2);

    private int value;

    VerifyTypeEnum(int i) {
        this.value = i;
    }

    public static VerifyTypeEnum typeOfValue(int i) {
        for (VerifyTypeEnum verifyTypeEnum : values()) {
            if (verifyTypeEnum.value == i) {
                return verifyTypeEnum;
            }
        }
        return Apply;
    }

    public int getValue() {
        return this.value;
    }
}
