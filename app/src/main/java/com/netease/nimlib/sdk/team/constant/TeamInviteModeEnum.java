package com.netease.nimlib.sdk.team.constant;

/* loaded from: classes.dex */
public enum TeamInviteModeEnum {
    Manager(0),
    All(1);

    private int value;

    TeamInviteModeEnum(int i) {
        this.value = i;
    }

    public static TeamInviteModeEnum typeOfValue(int i) {
        for (TeamInviteModeEnum teamInviteModeEnum : values()) {
            if (teamInviteModeEnum.value == i) {
                return teamInviteModeEnum;
            }
        }
        return Manager;
    }

    public int getValue() {
        return this.value;
    }
}
