package com.netease.nimlib.sdk.team.model;

import com.netease.nimlib.sdk.team.constant.TeamAllMuteModeEnum;
import com.netease.nimlib.sdk.team.constant.TeamBeInviteModeEnum;
import com.netease.nimlib.sdk.team.constant.TeamExtensionUpdateModeEnum;
import com.netease.nimlib.sdk.team.constant.TeamInviteModeEnum;
import com.netease.nimlib.sdk.team.constant.TeamMessageNotifyTypeEnum;
import com.netease.nimlib.sdk.team.constant.TeamTypeEnum;
import com.netease.nimlib.sdk.team.constant.TeamUpdateModeEnum;
import com.netease.nimlib.sdk.team.constant.VerifyTypeEnum;
import java.io.Serializable;

/* loaded from: classes.dex */
public interface Team extends Serializable {
    String getAnnouncement();

    long getCreateTime();

    String getCreator();

    String getExtServer();

    String getExtension();

    String getIcon();

    String getId();

    String getIntroduce();

    int getMemberCount();

    int getMemberLimit();

    TeamMessageNotifyTypeEnum getMessageNotifyType();

    TeamAllMuteModeEnum getMuteMode();

    String getName();

    TeamBeInviteModeEnum getTeamBeInviteMode();

    TeamExtensionUpdateModeEnum getTeamExtensionUpdateMode();

    TeamInviteModeEnum getTeamInviteMode();

    TeamUpdateModeEnum getTeamUpdateMode();

    TeamTypeEnum getType();

    VerifyTypeEnum getVerifyType();

    boolean isAllMute();

    boolean isMyTeam();

    @Deprecated
    boolean mute();

    void setExtension(String str);
}
