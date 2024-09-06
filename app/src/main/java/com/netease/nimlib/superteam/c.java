package com.netease.nimlib.superteam;

import com.netease.nimlib.sdk.superteam.SuperTeamMember;
import com.netease.nimlib.sdk.team.constant.TeamMemberType;
import com.netease.nimlib.session.j;
import java.util.List;

/* compiled from: SuperTeamMemberImpl.java */
/* loaded from: classes.dex */
public class c implements SuperTeamMember {
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

    @Override // com.netease.nimlib.sdk.superteam.SuperTeamMember
    public String getTid() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    @Override // com.netease.nimlib.sdk.superteam.SuperTeamMember
    public String getAccount() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    @Override // com.netease.nimlib.sdk.superteam.SuperTeamMember
    public TeamMemberType getType() {
        return this.c;
    }

    public void a(int i) {
        this.c = TeamMemberType.typeOfValue(i);
    }

    public void a(TeamMemberType teamMemberType) {
        this.c = teamMemberType;
    }

    @Override // com.netease.nimlib.sdk.superteam.SuperTeamMember
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

    @Override // com.netease.nimlib.sdk.superteam.SuperTeamMember
    public boolean isInTeam() {
        return this.f == 1;
    }

    public void b(int i) {
        this.f = i;
    }

    @Override // com.netease.nimlib.sdk.superteam.SuperTeamMember
    public long getJoinTime() {
        return this.g;
    }

    public void b(long j) {
        this.g = j;
    }

    @Override // com.netease.nimlib.sdk.superteam.SuperTeamMember
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
        this.k = j.b(str);
    }

    @Override // com.netease.nimlib.sdk.superteam.SuperTeamMember
    public List<String> getFollowAccountIds() {
        return this.k;
    }

    public String c() {
        return j.e(this.k);
    }

    @Override // com.netease.nimlib.sdk.superteam.SuperTeamMember
    public String getExtension() {
        return this.h;
    }

    public void f(String str) {
        this.h = str;
    }

    public void c(int i) {
        this.i = i == 1;
    }

    @Override // com.netease.nimlib.sdk.superteam.SuperTeamMember
    public boolean isMute() {
        return this.i;
    }

    public static final c a(com.netease.nimlib.push.packet.b.c cVar) {
        c cVar2 = new c();
        if (cVar == null) {
            return cVar2;
        }
        cVar2.a(cVar.c(1));
        cVar2.b(cVar.c(3));
        cVar2.c(cVar.c(5));
        cVar2.a(cVar.d(4));
        cVar2.b(cVar.d(9));
        cVar2.a(cVar.e(7));
        cVar2.b(cVar.e(10));
        cVar2.f(cVar.c(12));
        cVar2.c(cVar.d(13));
        cVar2.d(cVar.c(14));
        cVar2.e(cVar.c(17));
        return cVar2;
    }

    public String toString() {
        return "SuperTeamMemberImpl{tid='" + this.a + "', account='" + this.b + "', type=" + this.c + ", teamNick='" + com.netease.nimlib.log.b.a.a((CharSequence) this.d) + "', bits=" + this.e + ", validFlag=" + this.f + ", joinTime=" + this.g + ", extension='" + this.h + "', mute=" + this.i + ", invitorAccid='" + this.j + "', followAccountIds=" + this.k + '}';
    }
}
