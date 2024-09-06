package com.netease.nimlib.team;

import com.netease.nimlib.sdk.team.constant.TeamMemberType;
import com.netease.nimlib.sdk.team.model.TeamMember;
import java.util.List;
import java.util.Map;

/* compiled from: TeamMemberImpl.java */
/* loaded from: classes.dex */
public class g implements TeamMember {
    private String a;
    private String b;
    private TeamMemberType c;
    private String d;
    private long e;
    private int f;
    private long g;
    private String h;
    private boolean i;
    private String j;
    private List<String> k;

    @Override // com.netease.nimlib.sdk.team.model.TeamMember
    public String getTid() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    @Override // com.netease.nimlib.sdk.team.model.TeamMember
    public String getAccount() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    @Override // com.netease.nimlib.sdk.team.model.TeamMember
    public TeamMemberType getType() {
        return this.c;
    }

    public void a(int i) {
        this.c = TeamMemberType.typeOfValue(i);
    }

    public void a(TeamMemberType teamMemberType) {
        this.c = teamMemberType;
    }

    @Override // com.netease.nimlib.sdk.team.model.TeamMember
    public String getTeamNick() {
        return this.d;
    }

    public void c(String str) {
        this.d = str;
    }

    public long a() {
        return this.e;
    }

    public void a(long j) {
        this.e = j;
    }

    public int b() {
        return this.f;
    }

    @Override // com.netease.nimlib.sdk.team.model.TeamMember
    public boolean isInTeam() {
        return this.f == 1;
    }

    public void b(int i) {
        this.f = i;
    }

    @Override // com.netease.nimlib.sdk.team.model.TeamMember
    public long getJoinTime() {
        return this.g;
    }

    public void b(long j) {
        this.g = j;
    }

    @Override // com.netease.nimlib.sdk.team.model.TeamMember
    public String getInvitorAccid() {
        return this.j;
    }

    public void d(String str) {
        this.j = str;
    }

    public void a(List<String> list) {
        this.k = list;
    }

    public void e(String str) {
        this.k = com.netease.nimlib.session.j.b(str);
    }

    @Override // com.netease.nimlib.sdk.team.model.TeamMember
    public List<String> getFollowAccountIds() {
        return this.k;
    }

    public String c() {
        return com.netease.nimlib.session.j.e(this.k);
    }

    @Override // com.netease.nimlib.sdk.team.model.TeamMember
    public Map<String, Object> getExtension() {
        return com.netease.nimlib.session.j.c(this.h);
    }

    public String d() {
        return this.h;
    }

    public void f(String str) {
        this.h = str;
    }

    public void c(int i) {
        this.i = i == 1;
    }

    @Override // com.netease.nimlib.sdk.team.model.TeamMember
    public boolean isMute() {
        return this.i;
    }

    public static final g a(com.netease.nimlib.push.packet.b.c cVar) {
        g gVar = new g();
        gVar.a(cVar.c(1));
        gVar.b(cVar.c(3));
        gVar.c(cVar.c(5));
        gVar.a(cVar.d(4));
        gVar.b(cVar.d(9));
        gVar.a(cVar.e(7));
        gVar.b(cVar.e(10));
        gVar.f(cVar.c(12));
        gVar.c(cVar.d(13));
        gVar.d(cVar.c(14));
        gVar.e(cVar.c(16));
        return gVar;
    }

    public String toString() {
        return "TeamMemberImpl{tid='" + this.a + "', account='" + this.b + "', type=" + this.c + ", teamNick='" + com.netease.nimlib.log.b.a.a((CharSequence) this.d) + "', bits=" + this.e + ", validFlag=" + this.f + ", joinTime=" + this.g + ", extension='" + this.h + "', mute=" + this.i + ", invitorAccid='" + this.j + "', followAccountIds=" + this.k + '}';
    }
}
