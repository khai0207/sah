package com.netease.nimlib.sdk.team.model;

import java.util.List;

/* loaded from: classes.dex */
public interface NIMTeamMemberSearchResult {
    long getOffset();

    List<TeamMember> getTeamMemberList();

    boolean isFinished();
}
