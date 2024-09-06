package com.netease.nimlib.sdk.friend.constant;

/* loaded from: classes.dex */
public enum FriendRelationship {
    NOT_FRIEND(0),
    NORMAL_FRIEND(1);

    private int value;

    FriendRelationship(int i) {
        this.value = i;
    }

    public static FriendRelationship RelationshipOfValue(int i) {
        for (FriendRelationship friendRelationship : values()) {
            if (friendRelationship.getValue() == i) {
                return friendRelationship;
            }
        }
        return NOT_FRIEND;
    }

    public int getValue() {
        return this.value;
    }
}
