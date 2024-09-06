package com.netease.nimlib.sdk.team.constant;

/* loaded from: classes.dex */
public enum TeamAllMuteModeEnum {
    Cancel(0),
    MuteNormal(1),
    MuteALL(3);

    private int value;

    TeamAllMuteModeEnum(int i) {
        this.value = i;
    }

    public static TeamAllMuteModeEnum typeOfValue(int i) {
        for (TeamAllMuteModeEnum teamAllMuteModeEnum : values()) {
            if (teamAllMuteModeEnum.value == i) {
                return teamAllMuteModeEnum;
            }
        }
        return Cancel;
    }

    public int getValue() {
        return this.value;
    }
}
