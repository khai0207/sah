package com.netease.nimlib.sdk.msg.constant;

/* loaded from: classes.dex */
public enum SessionTypeEnum {
    None(-1),
    P2P(0),
    Team(1),
    SUPER_TEAM(5),
    System(10001),
    Ysf(2),
    ChatRoom(10002),
    QChat(10003);

    private int value;

    SessionTypeEnum(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static SessionTypeEnum typeOfValue(int i) {
        for (SessionTypeEnum sessionTypeEnum : values()) {
            if (sessionTypeEnum.getValue() == i) {
                return sessionTypeEnum;
            }
        }
        return P2P;
    }
}
