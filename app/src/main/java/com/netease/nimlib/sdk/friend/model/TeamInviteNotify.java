package com.netease.nimlib.sdk.friend.model;

import com.netease.nimlib.sdk.team.model.Team;
import java.io.Serializable;
import java.util.Map;

/* loaded from: classes.dex */
public class TeamInviteNotify implements Serializable {
    private Map<String, Object> extension;
    private Team team;

    public TeamInviteNotify(Team team, Map<String, Object> map) {
        this.team = team;
        this.extension = map;
    }

    public Map<String, Object> getExtension() {
        return this.extension;
    }

    public Team getTeam() {
        return this.team;
    }
}
