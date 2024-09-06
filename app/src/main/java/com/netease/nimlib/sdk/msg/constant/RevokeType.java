package com.netease.nimlib.sdk.msg.constant;

/* loaded from: classes.dex */
public enum RevokeType {
    undefined(-1),
    P2P_DELETE_MSG(7),
    TEAM_DELETE_MSG(8),
    SUPER_TEAM_DELETE_MSG(12),
    P2P_ONE_WAY_DELETE_MSG(13),
    TEAM_ONE_WAY_DELETE_MSG(14);

    private int value;

    RevokeType(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static RevokeType typeOfValue(int i) {
        for (RevokeType revokeType : values()) {
            if (revokeType.getValue() == i) {
                return revokeType;
            }
        }
        return undefined;
    }
}
