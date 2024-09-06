package com.netease.nimlib.sdk.chatroom.model;

import java.util.Map;

/* loaded from: classes.dex */
public class ChatRoomKickOutEvent {
    private Map<String, Object> extension;
    private ChatRoomKickOutReason reason;
    private String roomId;

    /* loaded from: classes.dex */
    public enum ChatRoomKickOutReason {
        UNKNOWN(-1),
        CHAT_ROOM_INVALID(1),
        KICK_OUT_BY_MANAGER(2),
        KICK_OUT_BY_CONFLICT_LOGIN(3),
        ILLEGAL_STAT(4),
        BE_BLACKLISTED(5);

        private int value;

        ChatRoomKickOutReason(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static ChatRoomKickOutReason typeOfValue(int i) {
            for (ChatRoomKickOutReason chatRoomKickOutReason : values()) {
                if (chatRoomKickOutReason.getValue() == i) {
                    return chatRoomKickOutReason;
                }
            }
            return UNKNOWN;
        }
    }

    public ChatRoomKickOutEvent(String str, int i, Map<String, Object> map) {
        this.roomId = str;
        this.reason = ChatRoomKickOutReason.typeOfValue(i);
        this.extension = map;
    }

    public String getRoomId() {
        return this.roomId;
    }

    public ChatRoomKickOutReason getReason() {
        return this.reason;
    }

    public Map<String, Object> getExtension() {
        return this.extension;
    }
}
