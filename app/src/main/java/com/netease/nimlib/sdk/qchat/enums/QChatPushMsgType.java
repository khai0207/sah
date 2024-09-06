package com.netease.nimlib.sdk.qchat.enums;

/* loaded from: classes.dex */
public enum QChatPushMsgType {
    ALL(1),
    HIGH_MID_LEVEL(2),
    HIGH_LEVEL(3),
    NONE(4),
    INHERIT(5);

    private int value;

    QChatPushMsgType(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static QChatPushMsgType typeOfValue(int i) {
        for (QChatPushMsgType qChatPushMsgType : values()) {
            if (qChatPushMsgType.getValue() == i) {
                return qChatPushMsgType;
            }
        }
        return null;
    }
}
