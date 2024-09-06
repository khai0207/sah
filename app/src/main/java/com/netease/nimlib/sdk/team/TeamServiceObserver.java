package com.netease.nimlib.sdk.team;

import com.netease.nimlib.i.d;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.team.model.Team;
import com.netease.nimlib.sdk.team.model.TeamMember;
import java.util.List;

@d
/* loaded from: classes.dex */
public interface TeamServiceObserver {
    void observeMemberRemove(Observer<List<TeamMember>> observer, boolean z);

    void observeMemberUpdate(Observer<List<TeamMember>> observer, boolean z);

    void observeTeamRemove(Observer<Team> observer, boolean z);

    void observeTeamUpdate(Observer<List<Team>> observer, boolean z);
}
