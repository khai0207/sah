package com.netease.nimlib.sdk.team.constant;

/* loaded from: classes.dex */
public enum TeamUpdateModeEnum {
    Manager(0),
    All(1);

    private int value;

    TeamUpdateModeEnum(int i) {
        this.value = i;
    }

    public static TeamUpdateModeEnum typeOfValue(int i) {
        for (TeamUpdateModeEnum teamUpdateModeEnum : values()) {
            if (teamUpdateModeEnum.value == i) {
                return teamUpdateModeEnum;
            }
        }
        return Manager;
    }

    public int getValue() {
        return this.value;
    }
}
