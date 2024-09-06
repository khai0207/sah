package com.netease.nimlib.sdk.superteam;

import com.netease.nimlib.sdk.team.constant.TeamMemberType;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes.dex */
public interface SuperTeamMember extends Serializable {
    String getAccount();

    String getExtension();

    List<String> getFollowAccountIds();

    String getInvitorAccid();

    long getJoinTime();

    String getTeamNick();

    String getTid();

    TeamMemberType getType();

    boolean isInTeam();

    boolean isMute();
}
