package com.netease.nimlib.sdk.friend.constant;

/* loaded from: classes.dex */
public enum FriendSource {
    DEFAULT((byte) 0);

    private byte value;

    FriendSource(byte b) {
        this.value = b;
    }

    public static FriendSource friendSourceOfValue(int i) {
        byte b = (byte) i;
        for (FriendSource friendSource : values()) {
            if (friendSource.getValue() == b) {
                return friendSource;
            }
        }
        return null;
    }

    public byte getValue() {
        return this.value;
    }
}
