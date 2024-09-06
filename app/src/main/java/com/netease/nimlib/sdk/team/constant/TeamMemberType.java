package com.netease.nimlib.sdk.team.constant;

/* loaded from: classes.dex */
public enum TeamMemberType {
    Normal(0),
    Owner(1),
    Manager(2),
    Apply(3);

    private int value;

    TeamMemberType(int i) {
        this.value = i;
    }

    public static TeamMemberType typeOfValue(int i) {
        for (TeamMemberType teamMemberType : values()) {
            if (teamMemberType.value == i) {
                return teamMemberType;
            }
        }
        return Normal;
    }

    public int getValue() {
        return this.value;
    }
}
