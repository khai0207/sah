package com.netease.nimlib.team;

import com.netease.nimlib.sdk.team.model.Team;
import com.netease.nimlib.sdk.team.model.TeamInfoResult;
import java.util.List;

/* compiled from: TeamInfoResultImpl.java */
/* loaded from: classes.dex */
public class e implements TeamInfoResult {
    private final int a;
    private final List<Team> b;
    private final List<Long> c;

    public e(int i, List<Team> list, List<Long> list2) {
        this.a = i;
        this.b = list;
        this.c = list2;
    }

    @Override // com.netease.nimlib.sdk.team.model.TeamInfoResult
    public int getCode() {
        return this.a;
    }

    @Override // com.netease.nimlib.sdk.team.model.TeamInfoResult
    public List<Team> getTeamInfoList() {
        return this.b;
    }

    @Override // com.netease.nimlib.sdk.team.model.TeamInfoResult
    public List<Long> getFailedTeamIdList() {
        return this.c;
    }
}
