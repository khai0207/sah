package com.netease.nimlib.biz.f;

import android.text.TextUtils;
import android.util.Pair;
import com.netease.nimlib.biz.d.k.p;
import com.netease.nimlib.biz.d.k.q;
import com.netease.nimlib.biz.d.k.r;
import com.netease.nimlib.biz.d.k.s;
import com.netease.nimlib.biz.d.k.t;
import com.netease.nimlib.biz.d.k.u;
import com.netease.nimlib.biz.d.k.v;
import com.netease.nimlib.biz.d.k.w;
import com.netease.nimlib.sdk.InvocationFuture;
import com.netease.nimlib.sdk.RequestCallbackWrapper;
import com.netease.nimlib.sdk.ResponseCode;
import com.netease.nimlib.sdk.antispam.model.AntiSpamConfig;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.SearchOrderEnum;
import com.netease.nimlib.sdk.msg.model.TeamMessageReceipt;
import com.netease.nimlib.sdk.msg.model.TeamMsgAckInfo;
import com.netease.nimlib.sdk.team.TeamService;
import com.netease.nimlib.sdk.team.constant.TeamAllMuteModeEnum;
import com.netease.nimlib.sdk.team.constant.TeamBeInviteModeEnum;
import com.netease.nimlib.sdk.team.constant.TeamExtensionUpdateModeEnum;
import com.netease.nimlib.sdk.team.constant.TeamFieldEnum;
import com.netease.nimlib.sdk.team.constant.TeamInviteModeEnum;
import com.netease.nimlib.sdk.team.constant.TeamMemberType;
import com.netease.nimlib.sdk.team.constant.TeamMessageNotifyTypeEnum;
import com.netease.nimlib.sdk.team.constant.TeamTypeEnum;
import com.netease.nimlib.sdk.team.constant.TeamUpdateModeEnum;
import com.netease.nimlib.sdk.team.constant.VerifyTypeEnum;
import com.netease.nimlib.sdk.team.model.CreateTeamResult;
import com.netease.nimlib.sdk.team.model.NIMTeamMemberRoleTypeSearchOption;
import com.netease.nimlib.sdk.team.model.NIMTeamMemberSearchResult;
import com.netease.nimlib.sdk.team.model.Team;
import com.netease.nimlib.sdk.team.model.TeamInfoResult;
import com.netease.nimlib.sdk.team.model.TeamMember;
import com.netease.nimlib.session.IMMessageImpl;
import com.netease.nimlib.session.MsgDBHelper;
import com.netease.nimlib.team.TeamDBHelper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;

/* compiled from: TeamServiceRemote.java */
/* loaded from: classes.dex */
public class n extends com.netease.nimlib.i.j implements TeamService {
    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<CreateTeamResult> createTeam(Map<TeamFieldEnum, Serializable> map, TeamTypeEnum teamTypeEnum, String str, List<String> list) {
        return createTeam(map, teamTypeEnum, str, list, null);
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<CreateTeamResult> createTeam(Map<TeamFieldEnum, Serializable> map, TeamTypeEnum teamTypeEnum, String str, List<String> list, AntiSpamConfig antiSpamConfig) {
        com.netease.nimlib.biz.i.a().a(a(map, teamTypeEnum, str, list, antiSpamConfig));
        return null;
    }

    private com.netease.nimlib.push.packet.b.c a(Map<TeamFieldEnum, Serializable> map) {
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        if (map != null && map.size() > 0) {
            for (Map.Entry<TeamFieldEnum, Serializable> entry : map.entrySet()) {
                if (entry.getKey().getFieldType() == String.class) {
                    cVar.a(entry.getKey().getValue(), (String) entry.getValue());
                } else if (entry.getKey().getFieldType() == VerifyTypeEnum.class) {
                    cVar.a(entry.getKey().getValue(), ((VerifyTypeEnum) entry.getValue()).getValue());
                } else if (entry.getKey().getFieldType() == TeamInviteModeEnum.class) {
                    cVar.a(entry.getKey().getValue(), ((TeamInviteModeEnum) entry.getValue()).getValue());
                } else if (entry.getKey().getFieldType() == TeamBeInviteModeEnum.class) {
                    cVar.a(entry.getKey().getValue(), ((TeamBeInviteModeEnum) entry.getValue()).getValue());
                } else if (entry.getKey().getFieldType() == TeamUpdateModeEnum.class) {
                    cVar.a(entry.getKey().getValue(), ((TeamUpdateModeEnum) entry.getValue()).getValue());
                } else if (entry.getKey().getFieldType() == TeamExtensionUpdateModeEnum.class) {
                    cVar.a(entry.getKey().getValue(), ((TeamExtensionUpdateModeEnum) entry.getValue()).getValue());
                } else if (entry.getKey().getFieldType() == Integer.class) {
                    cVar.a(entry.getKey().getValue(), ((Integer) entry.getValue()).intValue());
                }
            }
        }
        return cVar;
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<List<String>> addMembersEx(String str, List<String> list, String str2, String str3) {
        a(str, list, str2, str3);
        return null;
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<List<String>> addMembers(String str, List<String> list) {
        a(str, list, (String) null, (String) null);
        return null;
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<Void> removeMember(String str, String str2) {
        com.netease.nimlib.biz.d.k.m mVar = new com.netease.nimlib.biz.d.k.m();
        mVar.a(str);
        ArrayList arrayList = new ArrayList();
        arrayList.add(str2);
        mVar.a((List<String>) arrayList);
        mVar.a(b());
        com.netease.nimlib.biz.i.a().a(mVar);
        return null;
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<Void> removeMembers(String str, List<String> list) {
        com.netease.nimlib.biz.d.k.m mVar = new com.netease.nimlib.biz.d.k.m();
        mVar.a(str);
        mVar.a(list);
        mVar.a(b());
        com.netease.nimlib.biz.i.a().a(mVar);
        return null;
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<Void> updateName(String str, String str2) {
        return updateTeam(str, TeamFieldEnum.Name, str2);
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<Void> updateTeam(String str, TeamFieldEnum teamFieldEnum, Serializable serializable) {
        HashMap hashMap = new HashMap(1);
        hashMap.put(teamFieldEnum, serializable);
        return updateTeamFields(str, hashMap);
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<Void> updateTeamFields(String str, Map<TeamFieldEnum, Serializable> map) {
        return updateTeamFields(str, map, null);
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<Void> updateTeamFields(String str, Map<TeamFieldEnum, Serializable> map, AntiSpamConfig antiSpamConfig) {
        if (map.containsKey(TeamFieldEnum.AllMute)) {
            throw new IllegalArgumentException("unsupported team field：AllMute");
        }
        if (map.containsKey(TeamFieldEnum.Ext_Server_Only)) {
            throw new IllegalArgumentException("unsupported team field：ext server only");
        }
        b(map);
        com.netease.nimlib.push.packet.b.c a = a(map);
        a.a(1, str);
        w wVar = new w();
        wVar.a(a);
        wVar.a(b());
        if (antiSpamConfig != null) {
            com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
            String antiSpamBusinessId = antiSpamConfig.getAntiSpamBusinessId();
            if (!TextUtils.isEmpty(antiSpamBusinessId)) {
                cVar.a(1, antiSpamBusinessId);
            }
            wVar.b(cVar);
        }
        com.netease.nimlib.biz.i.a().a(wVar);
        return null;
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<Void> dismissTeam(final String str) {
        com.netease.nimlib.biz.d.k.d dVar = new com.netease.nimlib.biz.d.k.d();
        dVar.a(str);
        final com.netease.nimlib.i.k b = b();
        com.netease.nimlib.biz.i.a().a(new com.netease.nimlib.biz.g.b(dVar) { // from class: com.netease.nimlib.biz.f.n.1
            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
            public void a(com.netease.nimlib.biz.e.a aVar) {
                if (aVar.r() == 803) {
                    aVar.j().b(ResponseCode.RES_SUCCESS);
                }
                if (aVar.n()) {
                    com.netease.nimlib.team.c.a(str, true);
                }
                b.a(aVar.r()).b();
            }
        });
        return null;
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<Void> quitTeam(final String str) {
        com.netease.nimlib.biz.d.k.n nVar = new com.netease.nimlib.biz.d.k.n();
        nVar.a(str);
        final com.netease.nimlib.i.k b = b();
        com.netease.nimlib.biz.i.a().a(new com.netease.nimlib.biz.g.b(nVar) { // from class: com.netease.nimlib.biz.f.n.8
            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
            public void a(com.netease.nimlib.biz.e.a aVar) {
                if (aVar.r() == 804) {
                    aVar.j().b(ResponseCode.RES_SUCCESS);
                }
                if (aVar.n()) {
                    com.netease.nimlib.team.c.a(str, false);
                }
                b.a(aVar.r()).b();
            }
        });
        return null;
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<Team> queryTeam(String str) {
        com.netease.nimlib.i.k b = b();
        com.netease.nimlib.team.d queryTeam = TeamDBHelper.queryTeam(str);
        if (queryTeam == null) {
            searchTeam(str);
            return null;
        }
        a(b, 200, queryTeam);
        return null;
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public Team queryTeamBlock(String str) {
        return TeamDBHelper.queryTeam(str);
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<Team> searchTeam(String str) {
        com.netease.nimlib.biz.d.k.j jVar = new com.netease.nimlib.biz.d.k.j();
        jVar.a(str);
        jVar.a(b());
        com.netease.nimlib.biz.i.a().a(new com.netease.nimlib.biz.g.b(jVar, com.netease.nimlib.biz.g.a.c));
        return null;
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<TeamInfoResult> searchTeam(List<Long> list) {
        com.netease.nimlib.i.k b = b();
        if (com.netease.nimlib.o.f.c((Collection) list)) {
            b.b((Object) null).b();
            return null;
        }
        if (list.size() > 10) {
            list = list.subList(0, 10);
        }
        com.netease.nimlib.biz.d.k.i iVar = new com.netease.nimlib.biz.d.k.i(list);
        iVar.a(b);
        com.netease.nimlib.biz.i.a().a(iVar);
        return null;
    }

    private static void a(com.netease.nimlib.i.k kVar, int i, Team team) {
        kVar.a(i).a(team).b();
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<Team> applyJoinTeam(String str, String str2) {
        com.netease.nimlib.biz.d.k.l lVar = new com.netease.nimlib.biz.d.k.l(str, str2);
        final com.netease.nimlib.i.k b = b();
        com.netease.nimlib.biz.i.a().a(new com.netease.nimlib.biz.g.b(lVar) { // from class: com.netease.nimlib.biz.f.n.9
            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
            public void a(com.netease.nimlib.biz.e.a aVar) {
                if (aVar.n() || aVar.r() == 808) {
                    com.netease.nimlib.team.d a = com.netease.nimlib.team.d.a(((com.netease.nimlib.biz.e.l.j) aVar).a());
                    a.f(aVar.n() ? 1 : 0);
                    com.netease.nimlib.team.c.a(a);
                    b.b(a);
                }
                b.a(aVar.r()).b();
            }
        });
        return null;
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<Void> passApply(String str, String str2) {
        return a(new q(str, str2, null, true));
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<Void> rejectApply(String str, String str2, String str3) {
        return a(new q(str, str2, str3, false));
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<List<TeamMember>> addManagers(final String str, final List<String> list) {
        com.netease.nimlib.biz.d.k.b bVar = new com.netease.nimlib.biz.d.k.b(str, list, true);
        final com.netease.nimlib.i.k b = b();
        com.netease.nimlib.biz.i.a().a(new com.netease.nimlib.biz.g.b(bVar) { // from class: com.netease.nimlib.biz.f.n.10
            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
            public void a(com.netease.nimlib.biz.e.a aVar) {
                b.a(aVar.r());
                if (aVar.n()) {
                    ArrayList arrayList = new ArrayList();
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        com.netease.nimlib.team.g queryTeamMember = TeamDBHelper.queryTeamMember(str, (String) it.next());
                        if (queryTeamMember != null) {
                            queryTeamMember.a(TeamMemberType.Manager);
                            arrayList.add(queryTeamMember);
                        }
                    }
                    TeamDBHelper.saveTeamMembers(arrayList);
                    b.a(arrayList);
                }
                b.b();
            }
        });
        return null;
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<List<TeamMember>> removeManagers(final String str, final List<String> list) {
        com.netease.nimlib.biz.d.k.b bVar = new com.netease.nimlib.biz.d.k.b(str, list, false);
        final com.netease.nimlib.i.k b = b();
        com.netease.nimlib.biz.i.a().a(new com.netease.nimlib.biz.g.b(bVar) { // from class: com.netease.nimlib.biz.f.n.11
            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
            public void a(com.netease.nimlib.biz.e.a aVar) {
                b.a(aVar.r());
                if (aVar.n()) {
                    ArrayList arrayList = new ArrayList();
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        com.netease.nimlib.team.g queryTeamMember = TeamDBHelper.queryTeamMember(str, (String) it.next());
                        if (queryTeamMember != null) {
                            queryTeamMember.a(TeamMemberType.Normal);
                            arrayList.add(queryTeamMember);
                        }
                    }
                    TeamDBHelper.saveTeamMembers(arrayList);
                    b.a(arrayList);
                }
                b.b();
            }
        });
        return null;
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<List<TeamMember>> transferTeam(final String str, final String str2, final boolean z) {
        t tVar = new t(str, str2, z);
        final com.netease.nimlib.i.k b = b();
        com.netease.nimlib.biz.i.a().a(new com.netease.nimlib.biz.g.b(tVar) { // from class: com.netease.nimlib.biz.f.n.12
            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
            public void a(com.netease.nimlib.biz.e.a aVar) {
                b.a(aVar.r());
                if (aVar.n()) {
                    if (!z) {
                        ArrayList arrayList = new ArrayList();
                        com.netease.nimlib.team.g queryTeamMember = TeamDBHelper.queryTeamMember(str, str2);
                        if (queryTeamMember != null) {
                            queryTeamMember.a(TeamMemberType.Owner);
                            arrayList.add(queryTeamMember);
                        }
                        com.netease.nimlib.team.g queryTeamMember2 = TeamDBHelper.queryTeamMember(str, com.netease.nimlib.c.n());
                        if (queryTeamMember2 != null) {
                            queryTeamMember2.a(TeamMemberType.Normal);
                            arrayList.add(queryTeamMember2);
                        }
                        TeamDBHelper.saveTeamMembers(arrayList);
                        b.a(arrayList);
                        com.netease.nimlib.team.d queryTeam = TeamDBHelper.queryTeam(str);
                        if (queryTeam != null) {
                            queryTeam.c(str2);
                            com.netease.nimlib.team.c.a(queryTeam);
                        }
                    } else {
                        com.netease.nimlib.biz.l.a(str, 0L);
                        TeamDBHelper.quitTeam(str);
                        com.netease.nimlib.i.b.b(TeamDBHelper.queryTeam(str));
                    }
                }
                b.b();
            }
        });
        return null;
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<Void> acceptInvite(String str, String str2) {
        return a(new r(str, str2, null, true));
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<Void> declineInvite(String str, String str2, String str3) {
        return a(new r(str, str2, str3, false));
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public int queryTeamCountBlock() {
        return TeamDBHelper.queryTeamCount();
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public int queryTeamCountByTypeBlock(TeamTypeEnum teamTypeEnum) {
        return TeamDBHelper.queryTeamCountByType(teamTypeEnum);
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<List<Team>> queryTeamList() {
        b().b(TeamDBHelper.queryAllTeams()).b();
        return null;
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public List<Team> queryTeamListBlock() {
        return TeamDBHelper.queryAllTeams();
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<List<Team>> queryTeamListById(List<String> list) {
        b().b(TeamDBHelper.queryTeamListById(list)).b();
        return null;
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public List<Team> queryTeamListByIdBlock(List<String> list) {
        return TeamDBHelper.queryTeamListById(list);
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<List<Team>> queryTeamListByType(TeamTypeEnum teamTypeEnum) {
        b().b(TeamDBHelper.queryTeamListByType(teamTypeEnum)).b();
        return null;
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public List<Team> queryTeamListByTypeBlock(TeamTypeEnum teamTypeEnum) {
        return TeamDBHelper.queryTeamListByType(teamTypeEnum);
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<List<TeamMember>> queryMemberList(final String str) {
        boolean z;
        final com.netease.nimlib.i.k b = b();
        if (b(str)) {
            z = false;
        } else {
            z = a(str);
            if (!z) {
                a(str, b);
                return null;
            }
        }
        com.netease.nimlib.biz.d.k.h hVar = new com.netease.nimlib.biz.d.k.h();
        hVar.a(str);
        hVar.a(z ? 0L : com.netease.nimlib.biz.l.d(str));
        com.netease.nimlib.biz.i.a().a(new com.netease.nimlib.biz.g.b(hVar) { // from class: com.netease.nimlib.biz.f.n.13
            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
            public void a(com.netease.nimlib.biz.e.a aVar) {
                try {
                    n.this.a(str, b);
                } catch (Throwable th) {
                    b.a(th).b();
                }
            }
        });
        return null;
    }

    private boolean a(String str) {
        com.netease.nimlib.team.d queryTeam = TeamDBHelper.queryTeam(str);
        return (queryTeam == null || TeamDBHelper.queryMemberCount(str) == queryTeam.getMemberCount()) ? false : true;
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<TeamMember> queryTeamMember(final String str, final String str2) {
        final com.netease.nimlib.i.k b = b();
        if (b(str)) {
            com.netease.nimlib.biz.d.k.h hVar = new com.netease.nimlib.biz.d.k.h();
            hVar.a(str);
            hVar.a(com.netease.nimlib.biz.l.d(str));
            com.netease.nimlib.biz.i.a().a(new com.netease.nimlib.biz.g.b(hVar) { // from class: com.netease.nimlib.biz.f.n.14
                @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
                public void a(com.netease.nimlib.biz.e.a aVar) {
                    try {
                        n.this.a(str, str2, b);
                    } catch (Throwable th) {
                        b.a(th).b();
                    }
                }
            });
            return null;
        }
        a(str, str2, b);
        return null;
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public TeamMember queryTeamMemberBlock(String str, String str2) {
        return TeamDBHelper.queryTeamMember(str, str2);
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public List<TeamMember> queryMutedTeamMembers(String str) {
        return TeamDBHelper.queryMutedMemberList(str);
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<Void> updateMyTeamNick(String str, String str2) {
        return updateMemberNick(str, com.netease.nimlib.c.n(), str2);
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<Void> updateMemberNick(String str, String str2, String str3) {
        boolean equals = str2.equals(com.netease.nimlib.c.n());
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(1, str);
        cVar.a(5, str3);
        if (!equals) {
            cVar.a(3, str2);
        }
        return a(cVar, equals);
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<Void> updateMyMemberExtension(String str, Map<String, Object> map) {
        return a(str, com.netease.nimlib.c.n(), map);
    }

    public InvocationFuture<Void> a(String str, String str2, Map<String, Object> map) {
        boolean equals = str2.equals(com.netease.nimlib.c.n());
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(1, str);
        cVar.a(12, com.netease.nimlib.session.j.a(map));
        if (!equals) {
            cVar.a(3, str2);
        }
        return a(cVar, equals);
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<Void> muteTeam(String str, TeamMessageNotifyTypeEnum teamMessageNotifyTypeEnum) {
        long memberBits = TeamDBHelper.getMemberBits(str);
        com.netease.nimlib.log.b.d("TeamService", String.format("muteTeam, teamId=%s, notifyType=%s, bits=%s", str, teamMessageNotifyTypeEnum, Long.valueOf(memberBits)));
        if (teamMessageNotifyTypeEnum == TeamMessageNotifyTypeEnum.All) {
            memberBits = com.netease.nimlib.team.b.b(com.netease.nimlib.team.b.a(memberBits, false), false);
        } else if (teamMessageNotifyTypeEnum == TeamMessageNotifyTypeEnum.Manager) {
            memberBits = com.netease.nimlib.team.b.b(com.netease.nimlib.team.b.a(memberBits, false), true);
        } else if (teamMessageNotifyTypeEnum == TeamMessageNotifyTypeEnum.Mute) {
            memberBits = com.netease.nimlib.team.b.b(com.netease.nimlib.team.b.a(memberBits, true), false);
        }
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(1, str);
        cVar.a(7, memberBits);
        return a(cVar, true);
    }

    private void b(Map<TeamFieldEnum, Serializable> map) {
        for (Map.Entry<TeamFieldEnum, Serializable> entry : map.entrySet()) {
            if (!entry.getKey().getFieldType().isInstance(entry.getValue())) {
                throw new IllegalArgumentException("type of TeamFieldEnum." + entry.getKey().name() + " wrong, should be " + entry.getKey().getFieldType().getName());
            }
            if (entry.getKey() == TeamFieldEnum.undefined) {
                throw new IllegalArgumentException("undefined team field");
            }
        }
    }

    private InvocationFuture<Void> a(final com.netease.nimlib.push.packet.b.c cVar, final boolean z) {
        com.netease.nimlib.biz.d.a uVar = z ? new u(cVar, null) : new v(cVar);
        uVar.a(b());
        com.netease.nimlib.biz.i.a().a(new com.netease.nimlib.biz.g.b(uVar) { // from class: com.netease.nimlib.biz.f.n.15
            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
            public void a(com.netease.nimlib.biz.e.a aVar) {
                if (aVar.n()) {
                    if (z) {
                        cVar.a(3, com.netease.nimlib.c.n());
                    }
                    com.netease.nimlib.team.c.a(cVar);
                }
                ((com.netease.nimlib.i.k) this.h.j()).a(aVar.r()).b();
            }
        });
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final String str, final com.netease.nimlib.i.k kVar) {
        Set<String> queryMemberAccountList = TeamDBHelper.queryMemberAccountList(str);
        ArrayList arrayList = new ArrayList();
        for (String str2 : queryMemberAccountList) {
            if (com.netease.nimlib.user.c.d(str2)) {
                arrayList.add(str2);
            }
        }
        if (!arrayList.isEmpty()) {
            com.netease.nimlib.user.c.a(arrayList, new RequestCallbackWrapper<ArrayList<com.netease.nimlib.user.b>>() { // from class: com.netease.nimlib.biz.f.n.2
                @Override // com.netease.nimlib.sdk.RequestCallbackWrapper
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onResult(int i, ArrayList<com.netease.nimlib.user.b> arrayList2, Throwable th) {
                    n.this.b(str, kVar);
                }
            });
        } else {
            b(str, kVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, com.netease.nimlib.i.k kVar) {
        kVar.b(TeamDBHelper.queryMemberList(str)).b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, com.netease.nimlib.i.k kVar) {
        com.netease.nimlib.team.g queryTeamMember = TeamDBHelper.queryTeamMember(str, str2);
        if (queryTeamMember != null) {
            kVar.b(queryTeamMember);
        } else {
            kVar.a(404);
        }
        kVar.b();
    }

    private boolean b(String str) {
        long queryMemberTimetag = TeamDBHelper.queryMemberTimetag(str);
        long d = com.netease.nimlib.biz.l.d(str);
        return d == 0 || d < queryMemberTimetag;
    }

    private InvocationFuture<Void> a(com.netease.nimlib.biz.d.a aVar) {
        aVar.a(b());
        com.netease.nimlib.biz.i.a().a(new com.netease.nimlib.biz.g.b(aVar) { // from class: com.netease.nimlib.biz.f.n.3
            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
            public void a(com.netease.nimlib.biz.e.a aVar2) {
                ((com.netease.nimlib.i.k) this.h.j()).a(aVar2.r()).b();
            }
        });
        return null;
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<Void> muteTeamMember(String str, String str2, boolean z) {
        com.netease.nimlib.biz.d.k.o oVar = new com.netease.nimlib.biz.d.k.o(str, str2, z);
        oVar.a(b());
        com.netease.nimlib.biz.i.a().a(oVar);
        return null;
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<Void> muteAllTeamMember(String str, boolean z) {
        p pVar = new p(str, (z ? TeamAllMuteModeEnum.MuteNormal : TeamAllMuteModeEnum.Cancel).getValue());
        final com.netease.nimlib.i.k b = b();
        com.netease.nimlib.biz.i.a().a(new com.netease.nimlib.biz.g.b(pVar) { // from class: com.netease.nimlib.biz.f.n.4
            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
            public void a(com.netease.nimlib.biz.e.a aVar) {
                b.a(aVar.r());
                if (aVar.n()) {
                    b.a((Object) null);
                }
                b.b();
            }
        });
        return null;
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<Void> sendTeamMessageReceipt(IMMessage iMMessage) {
        com.netease.nimlib.i.k b = b();
        if (!com.netease.nimlib.c.i().enableTeamMsgAck) {
            com.netease.nimlib.log.b.N("team msg ack is disabled");
            b.a(1000).b();
            return null;
        }
        if (!iMMessage.needMsgAck()) {
            com.netease.nimlib.log.b.N("msg not need ack");
            b.a(200).b();
            return null;
        }
        if (iMMessage.hasSendAck()) {
            com.netease.nimlib.log.b.N("msg has send ack");
            b.a(200).b();
            return null;
        }
        com.netease.nimlib.team.h.c().a(iMMessage.getUuid(), b);
        com.netease.nimlib.team.k.b().a(iMMessage);
        return null;
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public void refreshTeamMessageReceipt(List<IMMessage> list) {
        if (!com.netease.nimlib.c.i().enableTeamMsgAck) {
            com.netease.nimlib.log.b.N("team msg ack is disabled");
            return;
        }
        final List<IMMessage> c = com.netease.nimlib.team.h.c().c(list);
        List<Pair<String, Long>> a = a(c);
        if (a == null || a.isEmpty()) {
            return;
        }
        com.netease.nimlib.log.b.N("refresh team message receipts, size=" + a.size());
        com.netease.nimlib.biz.i.a().a(new com.netease.nimlib.biz.g.b(new com.netease.nimlib.biz.d.k.e(a)) { // from class: com.netease.nimlib.biz.f.n.5
            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
            public void a(com.netease.nimlib.biz.e.a aVar) {
                if (aVar.n()) {
                    com.netease.nimlib.biz.c.i.q.a(((com.netease.nimlib.biz.e.l.q) aVar).a());
                    return;
                }
                com.netease.nimlib.team.h.c().d(c);
                com.netease.nimlib.log.b.N("refresh team mag ack info failed, code=" + ((int) aVar.r()));
            }
        });
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<TeamMsgAckInfo> fetchTeamMessageReceiptDetail(IMMessage iMMessage) {
        return b(iMMessage, true, (Set<String>) null);
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<TeamMsgAckInfo> fetchTeamMessageReceiptDetail(IMMessage iMMessage, Set<String> set) {
        return b(iMMessage, false, set);
    }

    private InvocationFuture<TeamMsgAckInfo> b(IMMessage iMMessage, final boolean z, final Set<String> set) {
        final com.netease.nimlib.i.k b = b();
        if (!com.netease.nimlib.c.i().enableTeamMsgAck) {
            com.netease.nimlib.log.b.N("team msg ack is disabled");
            b.a(1000).b();
            return null;
        }
        if (iMMessage == null || !iMMessage.needMsgAck() || iMMessage.getSessionType() != SessionTypeEnum.Team) {
            com.netease.nimlib.log.b.N("msg is null or ack not needed or session type is not team");
            b.a(414).b();
            return null;
        }
        if (!z && com.netease.nimlib.o.f.c((Collection) set)) {
            com.netease.nimlib.log.b.N("fetch receipts from part of members with empty account set");
            b.b(new TeamMsgAckInfo(iMMessage.getSessionId(), iMMessage.getUuid(), new ArrayList(0), new ArrayList(0))).b();
            return null;
        }
        TeamMsgAckInfo queryTeamMsgAckDetail = MsgDBHelper.queryTeamMsgAckDetail(iMMessage.getUuid());
        Pair<Integer, Integer> a = com.netease.nimlib.team.h.c().a(iMMessage.getUuid());
        if (queryTeamMsgAckDetail != null && a != null && queryTeamMsgAckDetail.getAckCount() == ((Integer) a.first).intValue() && queryTeamMsgAckDetail.getUnAckCount() == ((Integer) a.second).intValue()) {
            com.netease.nimlib.log.b.N("no need to fetch team message ack detail, as current is the newest data, reply directly");
            if (!z) {
                queryTeamMsgAckDetail = queryTeamMsgAckDetail.newInstanceFromPartOfAccount(set);
            }
            b.b(queryTeamMsgAckDetail).b();
            return null;
        }
        final boolean z2 = queryTeamMsgAckDetail == null || ((queryTeamMsgAckDetail.getAckAccountList() == null || queryTeamMsgAckDetail.getAckAccountList().isEmpty()) && (queryTeamMsgAckDetail.getUnAckAccountList() == null || queryTeamMsgAckDetail.getUnAckAccountList().isEmpty()));
        com.netease.nimlib.log.b.N("fetch team message receipt detail, msgId=" + iMMessage.getUuid() + ", snapshot=" + z2);
        com.netease.nimlib.biz.i.a().a(new com.netease.nimlib.biz.g.b(new com.netease.nimlib.biz.d.k.f(iMMessage.getSessionId(), Long.valueOf(((IMMessageImpl) iMMessage).getServerId()), z2)) { // from class: com.netease.nimlib.biz.f.n.6
            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
            public void a(com.netease.nimlib.biz.e.a aVar) {
                if (aVar.n()) {
                    com.netease.nimlib.biz.e.l.r rVar = (com.netease.nimlib.biz.e.l.r) aVar;
                    TeamMsgAckInfo a2 = rVar.a();
                    if (!z2) {
                        MsgDBHelper.updateTeamMsgAckDetail(a2.getMsgId(), rVar.b());
                        a2 = MsgDBHelper.queryTeamMsgAckDetail(a2.getMsgId());
                    } else {
                        MsgDBHelper.saveTeamMsgAckDetail(a2, rVar.b());
                    }
                    if (a2 != null) {
                        com.netease.nimlib.team.h.c().e(com.netease.nimlib.o.f.a((Object[]) new TeamMessageReceipt[]{new TeamMessageReceipt(a2)}));
                    }
                    com.netease.nimlib.i.k kVar = b;
                    if (!z && a2 != null) {
                        a2 = a2.newInstanceFromPartOfAccount(set);
                    }
                    kVar.b(a2).b();
                    return;
                }
                b.a(aVar.r()).b();
            }
        });
        return null;
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public TeamMsgAckInfo queryTeamMessageReceiptDetailBlock(IMMessage iMMessage) {
        return a(iMMessage, true, (Set<String>) null);
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public TeamMsgAckInfo queryTeamMessageReceiptDetailBlock(IMMessage iMMessage, Set<String> set) {
        return a(iMMessage, false, set);
    }

    public TeamMsgAckInfo a(IMMessage iMMessage, boolean z, Set<String> set) {
        if (!com.netease.nimlib.c.i().enableTeamMsgAck) {
            com.netease.nimlib.log.b.N("team msg ack is disabled");
            return null;
        }
        if (iMMessage == null || !iMMessage.needMsgAck() || iMMessage.getSessionType() != SessionTypeEnum.Team) {
            com.netease.nimlib.log.b.N("msg is null or ack not needed or session type is not team");
            return null;
        }
        if (!z && com.netease.nimlib.o.f.c((Collection) set)) {
            com.netease.nimlib.log.b.N("query receipts from part of members with empty account set");
            return new TeamMsgAckInfo(iMMessage.getSessionId(), iMMessage.getUuid(), new ArrayList(0), new ArrayList(0));
        }
        TeamMsgAckInfo queryTeamMsgAckDetail = MsgDBHelper.queryTeamMsgAckDetail(iMMessage.getUuid());
        return (set == null || queryTeamMsgAckDetail == null) ? queryTeamMsgAckDetail : queryTeamMsgAckDetail.newInstanceFromPartOfAccount(set);
    }

    private List<Pair<String, Long>> a(List<IMMessage> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (IMMessage iMMessage : list) {
            arrayList.add(new Pair(iMMessage.getSessionId(), Long.valueOf(((IMMessageImpl) iMMessage).getServerId())));
        }
        return arrayList;
    }

    private com.netease.nimlib.biz.d.k.c a(Map<TeamFieldEnum, Serializable> map, TeamTypeEnum teamTypeEnum, String str, List<String> list, AntiSpamConfig antiSpamConfig) {
        b(map);
        com.netease.nimlib.biz.d.k.c cVar = new com.netease.nimlib.biz.d.k.c();
        if (list == null) {
            list = new ArrayList<>();
        }
        cVar.a(list);
        cVar.a(str);
        com.netease.nimlib.push.packet.b.c a = a(map);
        a.a(4, teamTypeEnum.getValue());
        cVar.a(a);
        cVar.a(b());
        if (antiSpamConfig != null) {
            com.netease.nimlib.push.packet.b.c cVar2 = new com.netease.nimlib.push.packet.b.c();
            String antiSpamBusinessId = antiSpamConfig.getAntiSpamBusinessId();
            if (!TextUtils.isEmpty(antiSpamBusinessId)) {
                cVar2.a(1, antiSpamBusinessId);
            }
            cVar.b(cVar2);
        }
        return cVar;
    }

    private void a(String str, List<String> list, String str2, String str3) {
        if (str2 == null) {
            str2 = "";
        }
        if (str3 == null) {
            str3 = "";
        }
        com.netease.nimlib.biz.d.k.k kVar = new com.netease.nimlib.biz.d.k.k();
        kVar.a(str);
        kVar.a(list);
        kVar.b(str2);
        kVar.c(str3);
        kVar.a(b());
        com.netease.nimlib.biz.i.a().a(kVar);
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<Map<String, String>> getMemberInvitor(String str, List<String> list) {
        com.netease.nimlib.i.k b = b();
        if (TextUtils.isEmpty(str) || list == null || list.isEmpty() || list.size() > 200) {
            b.a(414).b();
            return null;
        }
        ArrayList<com.netease.nimlib.team.g> queryMemberListByAccids = TeamDBHelper.queryMemberListByAccids(str, list);
        boolean z = queryMemberListByAccids == null || queryMemberListByAccids.size() == 0 || queryMemberListByAccids.size() != list.size();
        HashMap hashMap = new HashMap(list.size());
        if (!z) {
            for (String str2 : list) {
                Iterator<com.netease.nimlib.team.g> it = queryMemberListByAccids.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    com.netease.nimlib.team.g next = it.next();
                    if (TextUtils.equals(str2, next.getAccount())) {
                        if (next.getInvitorAccid() == null) {
                            z = true;
                            break;
                        }
                        hashMap.put(str2, next.getInvitorAccid());
                    }
                }
                if (z) {
                    break;
                }
            }
        }
        if (!z) {
            z = hashMap.size() != list.size();
        }
        if (!z) {
            b.b(hashMap).b();
        } else {
            com.netease.nimlib.biz.d.k.g gVar = new com.netease.nimlib.biz.d.k.g(str, list);
            gVar.a(b);
            com.netease.nimlib.biz.i.a().a(gVar);
        }
        return null;
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<List<Team>> searchTeamsByKeyword(String str) {
        b().b(TeamDBHelper.searchTeamsByKeyword(str)).b();
        return null;
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<List<IMMessage>> searchTeamMsgByKeyword(long j, long j2, long j3, String str, int i, boolean z) {
        s sVar = new s(j, j2, j3, str, i, z);
        sVar.a(b());
        com.netease.nimlib.biz.i.a().a(sVar);
        return null;
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<List<String>> searchTeamIdByName(String str) {
        b().b(TeamDBHelper.queryTeamIdByName(str)).b();
        return null;
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<NIMTeamMemberSearchResult> getTeamMemberList(String str, NIMTeamMemberRoleTypeSearchOption nIMTeamMemberRoleTypeSearchOption) {
        com.netease.nimlib.i.k b = b();
        if (TextUtils.isEmpty(str)) {
            b.a(414).b();
            return null;
        }
        if (nIMTeamMemberRoleTypeSearchOption == null || !nIMTeamMemberRoleTypeSearchOption.isValid()) {
            b.a(414).b();
            return null;
        }
        com.netease.nimlib.team.a aVar = new com.netease.nimlib.team.a();
        if (!TeamDBHelper.isMyTeam(str)) {
            aVar.a(new ArrayList());
            aVar.a(true);
            aVar.a(0L);
            b.b(aVar).b();
            return null;
        }
        HashSet hashSet = new HashSet();
        if (nIMTeamMemberRoleTypeSearchOption.getRoleTypes() != null) {
            hashSet.addAll(nIMTeamMemberRoleTypeSearchOption.getRoleTypes());
        }
        List<TeamMember> queryMemberListByTypes = TeamDBHelper.queryMemberListByTypes(str, hashSet, nIMTeamMemberRoleTypeSearchOption.getOffset().intValue(), nIMTeamMemberRoleTypeSearchOption.getLimit().intValue() + 1, SearchOrderEnum.DESC.equals(nIMTeamMemberRoleTypeSearchOption.getOrder()));
        boolean z = queryMemberListByTypes.size() <= nIMTeamMemberRoleTypeSearchOption.getLimit().intValue();
        if (!z) {
            queryMemberListByTypes.remove(queryMemberListByTypes.size() - 1);
        }
        aVar.a(queryMemberListByTypes);
        aVar.a(z);
        aVar.a(z ? 0L : nIMTeamMemberRoleTypeSearchOption.getOffset().intValue() + queryMemberListByTypes.size());
        b.b(aVar).b();
        return null;
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<Void> addTeamMembersFollow(String str, List<String> list) {
        a(b(), str, list, true);
        return null;
    }

    @Override // com.netease.nimlib.sdk.team.TeamService
    public InvocationFuture<Void> removeTeamMembersFollow(String str, List<String> list) {
        a(b(), str, list, false);
        return null;
    }

    private void a(final com.netease.nimlib.i.k kVar, String str, List<String> list, boolean z) {
        if (TextUtils.isEmpty(str) || com.netease.nimlib.o.f.c((Collection) list)) {
            kVar.a(414).b();
            return;
        }
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(1, str);
        com.netease.nimlib.push.packet.b.c cVar2 = new com.netease.nimlib.push.packet.b.c();
        cVar2.a(1, new JSONArray((Collection) list).toString());
        cVar2.a(2, z ? 1 : 0);
        u uVar = new u(cVar, cVar2);
        uVar.a(kVar);
        com.netease.nimlib.biz.i.a().a(new com.netease.nimlib.biz.g.b(uVar) { // from class: com.netease.nimlib.biz.f.n.7
            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
            public void a(com.netease.nimlib.biz.e.a aVar) {
                if (aVar.n()) {
                    com.netease.nimlib.team.c.a(((com.netease.nimlib.biz.e.l.w) aVar).a());
                }
                kVar.a(aVar.r()).b();
            }
        });
    }
}
