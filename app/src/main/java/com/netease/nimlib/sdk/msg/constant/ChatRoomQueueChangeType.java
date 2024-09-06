package com.netease.nimlib.sdk.msg.constant;

/* loaded from: classes.dex */
public enum ChatRoomQueueChangeType {
    undefined(-1),
    OFFER(1),
    POLL(2),
    DROP(3),
    PARTCLEAR(4),
    BATCH_UPDATE(5),
    BATCH_OFFER(6);

    private int value;

    ChatRoomQueueChangeType(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static ChatRoomQueueChangeType typeOfValue(int i) {
        for (ChatRoomQueueChangeType chatRoomQueueChangeType : values()) {
            if (chatRoomQueueChangeType.getValue() == i) {
                return chatRoomQueueChangeType;
            }
        }
        return undefined;
    }
}
