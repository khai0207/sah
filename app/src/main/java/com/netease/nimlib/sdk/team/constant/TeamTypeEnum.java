package com.netease.nimlib.sdk.team.constant;

/* loaded from: classes.dex */
public enum TeamTypeEnum {
    Normal(0),
    Advanced(1);

    private int value;

    TeamTypeEnum(int i) {
        this.value = i;
    }

    public static TeamTypeEnum typeOfValue(int i) {
        for (TeamTypeEnum teamTypeEnum : values()) {
            if (teamTypeEnum.value == i) {
                return teamTypeEnum;
            }
        }
        return Normal;
    }

    public int getValue() {
        return this.value;
    }
}
