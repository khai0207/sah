package com.netease.nimlib.sdk.msg.constant;

import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import okhttp3.internal.http.StatusLine;

/* loaded from: classes.dex */
public enum NotificationType {
    undefined(-1),
    InviteMember(0),
    KickMember(1),
    LeaveTeam(2),
    UpdateTeam(3),
    DismissTeam(4),
    PassTeamApply(5),
    TransferOwner(6),
    AddTeamManager(7),
    RemoveTeamManager(8),
    AcceptInvite(9),
    MuteTeamMember(10),
    NetCallMiss(101),
    NetCallBill(102),
    NetCallReject(103),
    DataTunnelFin(201),
    DataTunnelMiss(202),
    ChatRoomMemberIn(Constants.BUCKET_REDIRECT_STATUS_CODE),
    ChatRoomMemberExit(302),
    ChatRoomMemberBlackAdd(303),
    ChatRoomMemberBlackRemove(304),
    ChatRoomMemberMuteAdd(305),
    ChatRoomMemberMuteRemove(306),
    ChatRoomManagerAdd(StatusLine.HTTP_TEMP_REDIRECT),
    ChatRoomManagerRemove(StatusLine.HTTP_PERM_REDIRECT),
    ChatRoomCommonAdd(309),
    ChatRoomCommonRemove(310),
    ChatRoomClose(311),
    ChatRoomInfoUpdated(312),
    ChatRoomMemberKicked(313),
    ChatRoomMemberTempMuteAdd(314),
    ChatRoomMemberTempMuteRemove(315),
    ChatRoomMyRoomRoleUpdated(316),
    ChatRoomQueueChange(317),
    ChatRoomRoomMuted(318),
    ChatRoomRoomDeMuted(319),
    ChatRoomQueueBatchChange(320),
    ChatRoomRecall(323),
    ChatRoomQueueBatchAdd(324),
    ChatRoomTagsUpdate(325),
    SUPER_TEAM_INVITE(401),
    SUPER_TEAM_KICK(402),
    SUPER_TEAM_LEAVE(Constants.BUCKET_ACCESS_FORBIDDEN_STATUS_CODE),
    SUPER_TEAM_UPDATE_T_INFO(404),
    SUPER_TEAM_DISMISS(405),
    SUPER_TEAM_CHANGE_OWNER(406),
    SUPER_TEAM_ADD_MANAGER(407),
    SUPER_TEAM_REMOVE_MANAGER(408),
    SUPER_TEAM_MUTE_TLIST(409),
    SUPER_TEAM_APPLY_PASS(410),
    SUPER_TEAM_INVITE_ACCEPT(411);

    private int value;

    NotificationType(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static NotificationType typeOfValue(int i) {
        for (NotificationType notificationType : values()) {
            if (notificationType.getValue() == i) {
                return notificationType;
            }
        }
        return undefined;
    }
}
