package com.netease.nimlib.sdk.chatroom.constant;

/* loaded from: classes.dex */
public enum MemberType {
    UNKNOWN(-1000),
    GUEST(-2),
    LIMITED(-1),
    NORMAL(0),
    CREATOR(1),
    ADMIN(2),
    ANONYMOUS(4);

    private int value;

    MemberType(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static MemberType typeOfValue(int i) {
        for (MemberType memberType : values()) {
            if (memberType.getValue() == i) {
                return memberType;
            }
        }
        return UNKNOWN;
    }
}
