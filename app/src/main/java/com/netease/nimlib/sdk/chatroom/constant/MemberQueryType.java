package com.netease.nimlib.sdk.chatroom.constant;

/* loaded from: classes.dex */
public enum MemberQueryType {
    UNKNOWN(-1000),
    NORMAL(0),
    GUEST(1),
    ONLINE_NORMAL(2),
    GUEST_DESC(1),
    GUEST_ASC(3);

    private int value;

    MemberQueryType(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static MemberQueryType typeOfValue(int i) {
        for (MemberQueryType memberQueryType : values()) {
            if (memberQueryType.getValue() == i) {
                return memberQueryType;
            }
        }
        return UNKNOWN;
    }
}
