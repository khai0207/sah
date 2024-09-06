package com.netease.nimlib.sdk.friend.model;

import java.io.Serializable;

/* loaded from: classes.dex */
public class AddFriendNotify implements Serializable {
    private String account;
    private Event event;
    private String msg;
    private String serverExt;

    /* loaded from: classes.dex */
    public enum Event {
        RECV_ADD_FRIEND_DIRECT((byte) 1),
        RECV_ADD_FRIEND_VERIFY_REQUEST((byte) 2),
        RECV_AGREE_ADD_FRIEND((byte) 3),
        RECV_REJECT_ADD_FRIEND((byte) 4);

        private byte value;

        Event(byte b) {
            this.value = b;
        }

        public static Event eventOfValue(byte b) {
            for (Event event : values()) {
                if (event.getValue() == b) {
                    return event;
                }
            }
            return null;
        }

        public byte getValue() {
            return this.value;
        }
    }

    public AddFriendNotify(String str, Event event) {
        this.account = str;
        this.event = event;
    }

    public AddFriendNotify(String str, Event event, String str2) {
        this.account = str;
        this.event = event;
        this.msg = str2;
    }

    public AddFriendNotify(String str, Event event, String str2, String str3) {
        this.account = str;
        this.event = event;
        this.msg = str2;
        this.serverExt = str3;
    }

    public String getAccount() {
        return this.account;
    }

    public Event getEvent() {
        return this.event;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getServerExt() {
        return this.serverExt;
    }
}
