package com.netease.nimlib.sdk.team.constant;

/* loaded from: classes.dex */
public enum TeamMessageNotifyTypeEnum {
    All(0),
    Manager(1),
    Mute(2);

    private int value;

    TeamMessageNotifyTypeEnum(int i) {
        this.value = i;
    }

    public static TeamMessageNotifyTypeEnum typeOfValue(int i) {
        for (TeamMessageNotifyTypeEnum teamMessageNotifyTypeEnum : values()) {
            if (teamMessageNotifyTypeEnum.value == i) {
                return teamMessageNotifyTypeEnum;
            }
        }
        return All;
    }

    public int getValue() {
        return this.value;
    }
}
