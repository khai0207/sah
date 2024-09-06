package com.netease.nimlib.sdk.friend.constant;

import java.util.Map;

/* loaded from: classes.dex */
public enum FriendFieldEnum {
    undefined(-1, null),
    ALIAS(8, String.class),
    EXTENSION(10, Map.class);

    private Class fieldType;
    private int value;

    FriendFieldEnum(int i, Class cls) {
        this.value = i;
        this.fieldType = cls;
    }

    public static FriendFieldEnum typeOfValue(int i) {
        for (FriendFieldEnum friendFieldEnum : values()) {
            if (friendFieldEnum.value == i) {
                return friendFieldEnum;
            }
        }
        return undefined;
    }

    public int getValue() {
        return this.value;
    }

    public Class getFieldType() {
        return this.fieldType;
    }
}
