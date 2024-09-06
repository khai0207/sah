package com.netease.nimlib.team;

import com.netease.nimlib.sdk.team.constant.TeamAllMuteModeEnum;
import com.netease.nimlib.sdk.team.constant.TeamBeInviteModeEnum;
import com.netease.nimlib.sdk.team.constant.TeamExtensionUpdateModeEnum;
import com.netease.nimlib.sdk.team.constant.TeamInviteModeEnum;
import com.netease.nimlib.sdk.team.constant.TeamMessageNotifyTypeEnum;
import com.netease.nimlib.sdk.team.constant.TeamTypeEnum;
import com.netease.nimlib.sdk.team.constant.TeamUpdateModeEnum;
import com.netease.nimlib.sdk.team.constant.VerifyTypeEnum;
import com.netease.nimlib.sdk.team.model.Team;

/* compiled from: TeamImpl.java */
/* loaded from: classes.dex */
public class d implements Team {
    private boolean A;
    private TeamMessageNotifyTypeEnum B;
    private String a;
    private String b;
    private String c;
    private TeamTypeEnum d;
    private String e;
    private int f;
    private String g;
    private String h;
    private String i;
    private VerifyTypeEnum j;
    private int k;
    private long l;
    private int m;
    private int n;
    private long o;
    private long p;
    private String q;
    private String r;
    private long s;
    private boolean t;

    /* renamed from: u, reason: collision with root package name */
    private TeamInviteModeEnum f29u;
    private TeamBeInviteModeEnum v;
    private TeamUpdateModeEnum w;
    private TeamExtensionUpdateModeEnum x;
    private TeamAllMuteModeEnum y;
    private boolean z;

    @Override // com.netease.nimlib.sdk.team.model.Team
    public String getId() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    @Override // com.netease.nimlib.sdk.team.model.Team
    public String getName() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    @Override // com.netease.nimlib.sdk.team.model.Team
    public String getCreator() {
        return this.e;
    }

    public void c(String str) {
        this.e = str;
    }

    @Override // com.netease.nimlib.sdk.team.model.Team
    public TeamTypeEnum getType() {
        return this.d;
    }

    public void a(int i) {
        this.d = TeamTypeEnum.typeOfValue(i);
    }

    public int a() {
        return this.f;
    }

    public void b(int i) {
        this.f = i;
    }

    @Override // com.netease.nimlib.sdk.team.model.Team
    public String getExtension() {
        return this.q;
    }

    @Override // com.netease.nimlib.sdk.team.model.Team
    public void setExtension(String str) {
        this.q = str;
    }

    @Override // com.netease.nimlib.sdk.team.model.Team
    public String getIntroduce() {
        return this.g;
    }

    public void d(String str) {
        this.g = str;
    }

    @Override // com.netease.nimlib.sdk.team.model.Team
    public String getAnnouncement() {
        return this.h;
    }

    public void e(String str) {
        this.h = str;
    }

    public int b() {
        return this.m;
    }

    public void c(int i) {
        this.m = i;
    }

    public long c() {
        return this.o;
    }

    public void a(long j) {
        this.o = j;
    }

    @Override // com.netease.nimlib.sdk.team.model.Team
    public int getMemberCount() {
        return this.k;
    }

    @Override // com.netease.nimlib.sdk.team.model.Team
    public int getMemberLimit() {
        return this.f;
    }

    public void d(int i) {
        this.k = i;
    }

    public long d() {
        return this.l;
    }

    public void b(long j) {
        this.l = j;
    }

    @Override // com.netease.nimlib.sdk.team.model.Team
    public VerifyTypeEnum getVerifyType() {
        return this.j;
    }

    public void e(int i) {
        this.j = VerifyTypeEnum.typeOfValue(i);
    }

    public int e() {
        return this.n;
    }

    public void f(int i) {
        this.n = i;
    }

    @Override // com.netease.nimlib.sdk.team.model.Team
    public long getCreateTime() {
        return this.p;
    }

    @Override // com.netease.nimlib.sdk.team.model.Team
    public boolean isMyTeam() {
        return this.n == 1 && this.m == 1;
    }

    public void c(long j) {
        this.p = j;
    }

    public String f() {
        return this.i;
    }

    public void f(String str) {
        this.i = str;
    }

    @Override // com.netease.nimlib.sdk.team.model.Team
    public String getExtServer() {
        return this.r;
    }

    public void g(String str) {
        this.r = str;
    }

    public long g() {
        return this.s;
    }

    public void d(long j) {
        this.s = j;
    }

    @Override // com.netease.nimlib.sdk.team.model.Team
    public boolean mute() {
        return this.t;
    }

    @Override // com.netease.nimlib.sdk.team.model.Team
    public TeamMessageNotifyTypeEnum getMessageNotifyType() {
        return this.B;
    }

    public void a(TeamMessageNotifyTypeEnum teamMessageNotifyTypeEnum) {
        this.B = teamMessageNotifyTypeEnum;
    }

    @Override // com.netease.nimlib.sdk.team.model.Team
    public String getIcon() {
        return this.c;
    }

    public void h(String str) {
        this.c = str;
    }

    public void g(int i) {
        this.f29u = TeamInviteModeEnum.typeOfValue(i);
    }

    @Override // com.netease.nimlib.sdk.team.model.Team
    public TeamInviteModeEnum getTeamInviteMode() {
        return this.f29u;
    }

    public void h(int i) {
        this.v = TeamBeInviteModeEnum.typeOfValue(i);
    }

    @Override // com.netease.nimlib.sdk.team.model.Team
    public TeamBeInviteModeEnum getTeamBeInviteMode() {
        return this.v;
    }

    public void i(int i) {
        this.w = TeamUpdateModeEnum.typeOfValue(i);
    }

    @Override // com.netease.nimlib.sdk.team.model.Team
    public TeamUpdateModeEnum getTeamUpdateMode() {
        return this.w;
    }

    public void j(int i) {
        this.x = TeamExtensionUpdateModeEnum.typeOfValue(i);
    }

    @Override // com.netease.nimlib.sdk.team.model.Team
    public TeamExtensionUpdateModeEnum getTeamExtensionUpdateMode() {
        return this.x;
    }

    public void k(int i) {
        this.y = TeamAllMuteModeEnum.typeOfValue(i);
        a(i >= TeamAllMuteModeEnum.MuteNormal.getValue());
    }

    private void a(boolean z) {
        this.z = z;
    }

    public static void a(d dVar, long j) {
        TeamMessageNotifyTypeEnum teamMessageNotifyTypeEnum;
        dVar.t = b.a(j);
        boolean b = b.b(j);
        dVar.A = b;
        if (dVar.t) {
            teamMessageNotifyTypeEnum = TeamMessageNotifyTypeEnum.Mute;
        } else if (b) {
            teamMessageNotifyTypeEnum = TeamMessageNotifyTypeEnum.Manager;
        } else {
            teamMessageNotifyTypeEnum = TeamMessageNotifyTypeEnum.All;
        }
        dVar.a(teamMessageNotifyTypeEnum);
    }

    @Override // com.netease.nimlib.sdk.team.model.Team
    public boolean isAllMute() {
        return this.z;
    }

    @Override // com.netease.nimlib.sdk.team.model.Team
    public TeamAllMuteModeEnum getMuteMode() {
        return this.y;
    }

    public static final d a(com.netease.nimlib.push.packet.b.c cVar) {
        d dVar = new d();
        dVar.a(cVar.c(1));
        dVar.d(cVar.d(9));
        dVar.c(cVar.d(8));
        dVar.b(cVar.c(3));
        dVar.c(cVar.c(5));
        dVar.f(cVar.c(7));
        dVar.b(cVar.d(6));
        dVar.b(cVar.e(10));
        dVar.a(cVar.d(4));
        dVar.a(cVar.e(12));
        dVar.d(cVar.c(14));
        dVar.e(cVar.c(15));
        dVar.c(cVar.e(11));
        dVar.f(cVar.d(13));
        dVar.e(cVar.d(16));
        dVar.setExtension(cVar.c(18));
        dVar.g(cVar.c(19));
        dVar.d(cVar.e(17));
        dVar.h(cVar.c(20));
        dVar.g(cVar.d(22));
        dVar.h(cVar.d(21));
        dVar.i(cVar.d(23));
        dVar.j(cVar.d(24));
        dVar.k(cVar.d(101));
        a(dVar, TeamDBHelper.getMemberBits(dVar.getId()));
        return dVar;
    }
}
