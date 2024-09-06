package com.netease.nimlib.sdk.uinfo.constant;

/* loaded from: classes.dex */
public enum GenderEnum {
    UNKNOWN(0),
    MALE(1),
    FEMALE(2);

    private Integer value;

    GenderEnum(int i) {
        this.value = Integer.valueOf(i);
    }

    public static GenderEnum genderOfValue(int i) {
        for (GenderEnum genderEnum : values()) {
            if (genderEnum.getValue().intValue() == i) {
                return genderEnum;
            }
        }
        return UNKNOWN;
    }

    public Integer getValue() {
        return this.value;
    }
}
