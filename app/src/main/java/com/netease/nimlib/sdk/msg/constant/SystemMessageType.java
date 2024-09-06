package com.netease.nimlib.sdk.msg.constant;

/* loaded from: classes.dex */
public enum SystemMessageType {
    undefined(-1),
    ApplyJoinTeam(0),
    RejectTeamApply(1),
    TeamInvite(2),
    DeclineTeamInvite(3),
    AddFriend(5),
    SuperTeamApply(15),
    SuperTeamApplyReject(16),
    SuperTeamInvite(17),
    SuperTeamInviteReject(18);

    private int value;

    SystemMessageType(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static SystemMessageType typeOfValue(int i) {
        for (SystemMessageType systemMessageType : values()) {
            if (systemMessageType.getValue() == i) {
                return systemMessageType;
            }
        }
        return undefined;
    }
}
