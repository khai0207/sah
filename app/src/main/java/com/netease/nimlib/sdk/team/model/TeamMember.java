package com.netease.nimlib.sdk.team.model;

import com.netease.nimlib.sdk.team.constant.TeamMemberType;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public interface TeamMember extends Serializable {
    String getAccount();

    Map<String, Object> getExtension();

    List<String> getFollowAccountIds();

    String getInvitorAccid();

    long getJoinTime();

    String getTeamNick();

    String getTid();

    TeamMemberType getType();

    boolean isInTeam();

    boolean isMute();
}
