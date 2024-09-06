package com.netease.nimlib.sdk.team.model;

import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class CreateTeamResult implements Serializable {
    private ArrayList<String> failedInviteAccounts;
    private Team team;

    public CreateTeamResult(Team team, ArrayList<String> arrayList) {
        this.team = team;
        this.failedInviteAccounts = arrayList;
    }

    public Team getTeam() {
        return this.team;
    }

    public ArrayList<String> getFailedInviteAccounts() {
        return this.failedInviteAccounts;
    }

    public void setFailedInviteAccounts(ArrayList<String> arrayList) {
        this.failedInviteAccounts = arrayList;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
