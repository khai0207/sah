package com.netease.nimlib.sdk.team.constant;

import java.io.Serializable;

/* loaded from: classes.dex */
public enum TeamFieldEnum {
    undefined(-1, null),
    Name(3, String.class),
    ICON(20, String.class),
    Introduce(14, String.class),
    Announcement(15, String.class),
    Extension(18, String.class),
    Ext_Server_Only(19, String.class),
    VerifyType(16, VerifyTypeEnum.class),
    InviteMode(22, TeamInviteModeEnum.class),
    BeInviteMode(21, TeamBeInviteModeEnum.class),
    TeamUpdateMode(23, TeamUpdateModeEnum.class),
    TeamExtensionUpdateMode(24, TeamExtensionUpdateModeEnum.class),
    AllMute(101, TeamAllMuteModeEnum.class),
    MaxMemberCount(6, Integer.class);

    private Class<? extends Serializable> fieldType;
    private int value;

    TeamFieldEnum(int i, Class cls) {
        this.value = i;
        this.fieldType = cls;
    }

    public static TeamFieldEnum typeOfValue(int i) {
        for (TeamFieldEnum teamFieldEnum : values()) {
            if (teamFieldEnum.value == i) {
                return teamFieldEnum;
            }
        }
        return undefined;
    }

    public int getValue() {
        return this.value;
    }

    public Class<? extends Serializable> getFieldType() {
        return this.fieldType;
    }
}
