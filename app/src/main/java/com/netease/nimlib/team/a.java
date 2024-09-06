package com.netease.nimlib.team;

import com.netease.nimlib.sdk.team.model.NIMTeamMemberSearchResult;
import com.netease.nimlib.sdk.team.model.TeamMember;
import java.util.List;

/* compiled from: NIMTeamMemberSearchResultImpl.java */
/* loaded from: classes.dex */
public class a implements NIMTeamMemberSearchResult {
    private List<TeamMember> a;
    private long b;
    private boolean c;

    @Override // com.netease.nimlib.sdk.team.model.NIMTeamMemberSearchResult
    public List<TeamMember> getTeamMemberList() {
        return this.a;
    }

    @Override // com.netease.nimlib.sdk.team.model.NIMTeamMemberSearchResult
    public long getOffset() {
        return this.b;
    }

    @Override // com.netease.nimlib.sdk.team.model.NIMTeamMemberSearchResult
    public boolean isFinished() {
        return this.c;
    }

    public void a(List<TeamMember> list) {
        this.a = list;
    }

    public void a(long j) {
        this.b = j;
    }

    public void a(boolean z) {
        this.c = z;
    }
}
