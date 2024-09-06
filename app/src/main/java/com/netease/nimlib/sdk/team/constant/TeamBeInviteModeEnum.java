package com.netease.nimlib.sdk.team.constant;

/* loaded from: classes.dex */
public enum TeamBeInviteModeEnum {
    NeedAuth(0),
    NoAuth(1);

    private int value;

    TeamBeInviteModeEnum(int i) {
        this.value = i;
    }

    public static TeamBeInviteModeEnum typeOfValue(int i) {
        for (TeamBeInviteModeEnum teamBeInviteModeEnum : values()) {
            if (teamBeInviteModeEnum.value == i) {
                return teamBeInviteModeEnum;
            }
        }
        return NeedAuth;
    }

    public int getValue() {
        return this.value;
    }
}
