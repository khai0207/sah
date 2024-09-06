package com.netease.nimlib.sdk.uinfo.constant;

/* loaded from: classes.dex */
public enum UserInfoFieldEnum {
    undefined(-1, null),
    Name(3, String.class),
    AVATAR(4, String.class),
    SIGNATURE(5, String.class),
    GENDER(6, Integer.class),
    EMAIL(7, String.class),
    BIRTHDAY(8, String.class),
    MOBILE(9, String.class),
    EXTEND(10, String.class);

    private Class<? extends Object> fieldType;
    private int value;

    UserInfoFieldEnum(int i, Class cls) {
        this.value = i;
        this.fieldType = cls;
    }

    public static UserInfoFieldEnum typeOfValue(int i) {
        for (UserInfoFieldEnum userInfoFieldEnum : values()) {
            if (userInfoFieldEnum.value == i) {
                return userInfoFieldEnum;
            }
        }
        return undefined;
    }

    public int getValue() {
        return this.value;
    }

    public Class<? extends Object> getFieldType() {
        return this.fieldType;
    }
}
