package com.netease.nimlib.team;

import android.text.TextUtils;
import com.netease.nimlib.biz.l;
import com.netease.nimlib.sdk.team.constant.TeamMessageNotifyTypeEnum;
import com.netease.nimlib.sdk.team.model.Team;
import defpackage.C$r8$backportedMethods$utility$Objects$2$equals;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* compiled from: TeamHelper.java */
/* loaded from: classes.dex */
public class c {
    public static void a(String str, boolean z) {
        a(str, z, true);
    }

    public static void a(String str, boolean z, boolean z2) {
        l.a(str, 0L);
        if (z) {
            TeamDBHelper.deleteTeam(str);
        } else {
            TeamDBHelper.quitTeam(str);
        }
        TeamDBHelper.clearTeamMembers(str);
        if (z2) {
            com.netease.nimlib.i.b.b(TeamDBHelper.queryTeam(str));
        }
    }

    public static void a(String str, com.netease.nimlib.push.packet.b.c cVar) {
        d queryTeam = TeamDBHelper.queryTeam(str);
        if (queryTeam == null) {
            return;
        }
        for (int i = 0; i < cVar.a(); i++) {
            int a = cVar.a(i);
            if (a == 3) {
                queryTeam.b(cVar.b(i));
            } else if (a == 12) {
                queryTeam.a(cVar.e(a));
            } else if (a != 101) {
                switch (a) {
                    case 14:
                        queryTeam.d(cVar.b(i));
                        break;
                    case 15:
                        queryTeam.e(cVar.b(i));
                        break;
                    case 16:
                        queryTeam.e(cVar.d(a));
                        break;
                    default:
                        switch (a) {
                            case 18:
                                queryTeam.setExtension(cVar.b(i));
                                break;
                            case 19:
                                queryTeam.g(cVar.b(i));
                                break;
                            case 20:
                                queryTeam.h(cVar.b(i));
                                break;
                            case 21:
                                queryTeam.h(cVar.d(a));
                                break;
                            case 22:
                                queryTeam.g(cVar.d(a));
                                break;
                            case 23:
                                queryTeam.i(cVar.d(a));
                                break;
                            case 24:
                                queryTeam.j(cVar.d(a));
                                break;
                        }
                }
            } else {
                queryTeam.k(cVar.d(a));
            }
        }
        a(queryTeam);
    }

    public static void a(d dVar) {
        d.a(dVar, TeamDBHelper.getMemberBits(dVar.getId()));
        TeamDBHelper.saveTeam(dVar);
        com.netease.nimlib.i.b.a(dVar);
    }

    public static TeamMessageNotifyTypeEnum a(String str) {
        long memberBits = TeamDBHelper.getMemberBits(str);
        boolean a = b.a(memberBits);
        boolean b = b.b(memberBits);
        if (a) {
            return TeamMessageNotifyTypeEnum.Mute;
        }
        if (b) {
            return TeamMessageNotifyTypeEnum.Manager;
        }
        return TeamMessageNotifyTypeEnum.All;
    }

    public static void a(com.netease.nimlib.push.packet.b.c cVar) {
        g queryTeamMember;
        com.netease.nimlib.log.b.d("TeamHelper", String.format("updateMember, member property: %s", cVar));
        if (cVar == null || cVar.a() == 0 || (queryTeamMember = TeamDBHelper.queryTeamMember(cVar.c(1), cVar.c(3))) == null) {
            return;
        }
        if (cVar.f(5)) {
            queryTeamMember.c(cVar.c(5));
        }
        if (cVar.f(7)) {
            queryTeamMember.a(cVar.e(7));
        }
        if (cVar.f(12)) {
            queryTeamMember.f(cVar.c(12));
        }
        if (cVar.f(13)) {
            queryTeamMember.c(cVar.d(13));
        }
        if (cVar.f(16)) {
            queryTeamMember.e(cVar.c(16));
        }
        a(queryTeamMember);
    }

    public static void a(g gVar) {
        b(gVar);
        TeamDBHelper.saveTeamMember(gVar);
        com.netease.nimlib.i.b.a(gVar);
    }

    public static void a(ArrayList<g> arrayList) {
        a((List<g>) arrayList);
        TeamDBHelper.saveTeamMembers(arrayList);
        com.netease.nimlib.i.b.k(arrayList);
    }

    public static void a(String str, String str2) {
        TeamDBHelper.deleteTeamMember(str, str2);
        com.netease.nimlib.i.b.a(str, str2);
        if (C$r8$backportedMethods$utility$Objects$2$equals.equals(str2, com.netease.nimlib.c.n())) {
            return;
        }
        b(str, str2);
    }

    private static void b(String str, String str2) {
        List<String> followAccountIds;
        g queryTeamMember = TeamDBHelper.queryTeamMember(str, com.netease.nimlib.c.n());
        if (queryTeamMember == null || (followAccountIds = queryTeamMember.getFollowAccountIds()) == null || !followAccountIds.contains(str2)) {
            return;
        }
        com.netease.nimlib.log.b.d("TeamHelper", String.format("removeFollowAccountId, tid: %s, account: %s", str, str2));
        followAccountIds.remove(str2);
        queryTeamMember.a(followAccountIds);
        TeamDBHelper.saveTeamMember(queryTeamMember);
        com.netease.nimlib.i.b.a(queryTeamMember);
    }

    public static void b(g gVar) {
        d queryTeam;
        if (!gVar.getAccount().equals(com.netease.nimlib.c.n()) || TeamDBHelper.getMemberBits(gVar.getTid()) == gVar.a() || (queryTeam = TeamDBHelper.queryTeam(gVar.getTid())) == null) {
            return;
        }
        d.a(queryTeam, gVar.a());
        com.netease.nimlib.i.b.a(queryTeam);
    }

    public static void a(List<g> list) {
        List<g> queryMemberListByServerTeamMembers = TeamDBHelper.queryMemberListByServerTeamMembers(list);
        HashMap hashMap = new HashMap();
        for (g gVar : list) {
            if (gVar.getAccount().equals(com.netease.nimlib.c.n())) {
                g gVar2 = null;
                Iterator<g> it = queryMemberListByServerTeamMembers.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    g next = it.next();
                    if (C$r8$backportedMethods$utility$Objects$2$equals.equals(gVar.getTid(), next.getTid()) && C$r8$backportedMethods$utility$Objects$2$equals.equals(gVar.getAccount(), next.getAccount())) {
                        gVar2 = next;
                        break;
                    }
                }
                if (gVar2 != null && gVar2.a() != gVar.a()) {
                    com.netease.nimlib.log.b.a("notifyTeamAsMemberBitsUpdated need update member.tid = %s,member.account = %s", gVar.getTid(), gVar.getAccount());
                    hashMap.put(gVar.getTid(), gVar);
                }
            }
        }
        Iterator<Team> it2 = TeamDBHelper.queryTeamListById(new ArrayList(hashMap.keySet())).iterator();
        while (it2.hasNext()) {
            Team next2 = it2.next();
            if (next2 != null) {
                d dVar = (d) next2;
                g gVar3 = (g) hashMap.get(dVar.getId());
                if (gVar3 != null) {
                    d.a(dVar, gVar3.a());
                    com.netease.nimlib.i.b.a(dVar);
                }
            }
        }
    }

    public static void a(String str, List<String> list) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            a(str, it.next());
        }
    }

    public static void a(String str, String str2, boolean z) {
        TeamDBHelper.muteTeamMember(str, str2, z);
        com.netease.nimlib.i.b.a(TeamDBHelper.queryTeamMember(str, str2));
    }

    public static d b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return d.a(b(new JSONObject(str).getJSONObject("tinfo")));
        } catch (Exception unused) {
            return null;
        }
    }

    public static d a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            return d.a(b(jSONObject));
        } catch (Exception unused) {
            return null;
        }
    }

    public static com.netease.nimlib.push.packet.b.c b(JSONObject jSONObject) {
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            cVar.a(Integer.parseInt(next), com.netease.nimlib.o.k.e(jSONObject, next));
        }
        return cVar;
    }
}
