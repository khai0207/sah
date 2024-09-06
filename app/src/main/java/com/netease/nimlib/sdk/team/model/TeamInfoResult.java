package com.netease.nimlib.sdk.team.model;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes.dex */
public interface TeamInfoResult extends Serializable {
    int getCode();

    List<Long> getFailedTeamIdList();

    List<Team> getTeamInfoList();
}
