package com.netease.nimlib.sdk.team.constant;

/* loaded from: classes.dex */
public enum TeamExtensionUpdateModeEnum {
    Manager(0),
    All(1);

    private int value;

    TeamExtensionUpdateModeEnum(int i) {
        this.value = i;
    }

    public static TeamExtensionUpdateModeEnum typeOfValue(int i) {
        for (TeamExtensionUpdateModeEnum teamExtensionUpdateModeEnum : values()) {
            if (teamExtensionUpdateModeEnum.value == i) {
                return teamExtensionUpdateModeEnum;
            }
        }
        return Manager;
    }

    public int getValue() {
        return this.value;
    }
}
