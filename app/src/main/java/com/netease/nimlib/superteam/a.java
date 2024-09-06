package com.netease.nimlib.superteam;

import com.netease.nimlib.biz.l;
import com.netease.nimlib.o.k;
import com.netease.nimlib.sdk.superteam.SuperTeam;
import com.netease.nimlib.sdk.team.constant.TeamMessageNotifyTypeEnum;
import defpackage.C$r8$backportedMethods$utility$Objects$2$equals;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* compiled from: SuperTeamHelper.java */
/* loaded from: classes.dex */
public class a {
    private static int a;
    private static long b;

    public static void a(String str, String str2, boolean z, boolean z2) {
        l.b(str, 0L);
        if (z) {
            SuperTeamDBHelper.deleteSuperTeam(str);
        } else {
            SuperTeamDBHelper.quitSuperTeam(str);
        }
        SuperTeamDBHelper.clearTeamMembers(str);
        if (z2) {
            com.netease.nimlib.i.b.b(SuperTeamDBHelper.querySuperTeam(str), str2);
        }
    }

    public static void a(String str, com.netease.nimlib.push.packet.b.c cVar, String str2) {
        b querySuperTeam = SuperTeamDBHelper.querySuperTeam(str);
        if (querySuperTeam == null) {
            return;
        }
        for (int i = 0; i < cVar.a(); i++) {
            int a2 = cVar.a(i);
            if (a2 == 3) {
                querySuperTeam.b(cVar.b(i));
            } else if (a2 == 12) {
                querySuperTeam.a(cVar.e(a2));
            } else if (a2 != 101) {
                switch (a2) {
                    case 14:
                        querySuperTeam.d(cVar.b(i));
                        break;
                    case 15:
                        querySuperTeam.e(cVar.b(i));
                        break;
                    case 16:
                        querySuperTeam.e(cVar.d(a2));
                        break;
                    default:
                        switch (a2) {
                            case 18:
                                querySuperTeam.setExtension(cVar.b(i));
                                break;
                            case 19:
                                querySuperTeam.g(cVar.b(i));
                                break;
                            case 20:
                                querySuperTeam.h(cVar.b(i));
                                break;
                            case 21:
                                querySuperTeam.h(cVar.d(a2));
                                break;
                            case 22:
                                querySuperTeam.g(cVar.d(a2));
                                break;
                            case 23:
                                querySuperTeam.i(cVar.d(a2));
                                break;
                            case 24:
                                querySuperTeam.j(cVar.d(a2));
                                break;
                        }
                }
            } else {
                querySuperTeam.k(cVar.d(a2));
            }
        }
        a(querySuperTeam, str2);
    }

    public static void a(b bVar, String str) {
        b.a(bVar, SuperTeamDBHelper.getMemberBits(bVar.getId()));
        SuperTeamDBHelper.saveSuperTeam(bVar);
        com.netease.nimlib.i.b.a(bVar, str);
    }

    public static TeamMessageNotifyTypeEnum a(String str) {
        long memberBits = SuperTeamDBHelper.getMemberBits(str);
        boolean a2 = com.netease.nimlib.team.b.a(memberBits);
        boolean b2 = com.netease.nimlib.team.b.b(memberBits);
        if (a2) {
            return TeamMessageNotifyTypeEnum.Mute;
        }
        if (b2) {
            return TeamMessageNotifyTypeEnum.Manager;
        }
        return TeamMessageNotifyTypeEnum.All;
    }

    public static void a(com.netease.nimlib.push.packet.b.c cVar, String str) {
        c queryTeamMember;
        com.netease.nimlib.log.b.d("SuperTeamHelper", String.format("updateMember, member property: %s", cVar));
        if (cVar == null || cVar.a() == 0 || (queryTeamMember = SuperTeamDBHelper.queryTeamMember(cVar.c(1), cVar.c(3))) == null) {
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
        if (cVar.f(17)) {
            queryTeamMember.e(cVar.c(17));
        }
        a(queryTeamMember, str);
    }

    public static void a(c cVar, String str) {
        b(cVar, str);
        SuperTeamDBHelper.saveTeamMember(cVar);
        com.netease.nimlib.i.b.a(cVar, str);
    }

    public static void a(ArrayList<c> arrayList, String str) {
        a((List<c>) arrayList, str);
        SuperTeamDBHelper.saveTeamMembers(arrayList);
        com.netease.nimlib.i.b.b(arrayList, str);
    }

    public static void a(String str, String str2, String str3) {
        SuperTeamDBHelper.deleteTeamMember(str, str2);
        com.netease.nimlib.i.b.a(str, str2, str3);
        if (C$r8$backportedMethods$utility$Objects$2$equals.equals(str2, com.netease.nimlib.c.n())) {
            return;
        }
        b(str, str2, str3);
    }

    private static void b(String str, String str2, String str3) {
        List<String> followAccountIds;
        c queryTeamMember = SuperTeamDBHelper.queryTeamMember(str, com.netease.nimlib.c.n());
        if (queryTeamMember == null || (followAccountIds = queryTeamMember.getFollowAccountIds()) == null || !followAccountIds.contains(str2)) {
            return;
        }
        com.netease.nimlib.log.b.d("SuperTeamHelper", String.format("removeFollowAccountId, tid: %s, account: %s", str, str2));
        followAccountIds.remove(str2);
        queryTeamMember.a(followAccountIds);
        SuperTeamDBHelper.saveTeamMember(queryTeamMember);
        com.netease.nimlib.i.b.a(queryTeamMember, str3);
    }

    public static void b(c cVar, String str) {
        b querySuperTeam;
        if (!cVar.getAccount().equals(com.netease.nimlib.c.n()) || SuperTeamDBHelper.getMemberBits(cVar.getTid()) == cVar.a() || (querySuperTeam = SuperTeamDBHelper.querySuperTeam(cVar.getTid())) == null) {
            return;
        }
        b.a(querySuperTeam, cVar.a());
        com.netease.nimlib.i.b.a(querySuperTeam, str);
    }

    public static void a(List<c> list, String str) {
        List<c> queryMemberListByServerTeamMembers = SuperTeamDBHelper.queryMemberListByServerTeamMembers(list);
        HashMap hashMap = new HashMap();
        for (c cVar : list) {
            if (cVar.getAccount().equals(com.netease.nimlib.c.n())) {
                c cVar2 = null;
                Iterator<c> it = queryMemberListByServerTeamMembers.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    c next = it.next();
                    if (C$r8$backportedMethods$utility$Objects$2$equals.equals(cVar.getTid(), next.getTid()) && C$r8$backportedMethods$utility$Objects$2$equals.equals(cVar.getAccount(), next.getAccount())) {
                        cVar2 = next;
                        break;
                    }
                }
                if (cVar2 != null && cVar2.a() != cVar.a()) {
                    com.netease.nimlib.log.b.a("superTeam notifyTeamAsMemberBitsUpdated need update member.tid = %s,member.account = %s", cVar.getTid(), cVar.getAccount());
                    hashMap.put(cVar.getTid(), cVar);
                }
            }
        }
        Iterator<SuperTeam> it2 = SuperTeamDBHelper.querySuperTeamListById(new ArrayList(hashMap.keySet())).iterator();
        while (it2.hasNext()) {
            SuperTeam next2 = it2.next();
            if (next2 != null) {
                b bVar = (b) next2;
                c cVar3 = (c) hashMap.get(next2.getId());
                if (cVar3 != null) {
                    b.a(bVar, cVar3.a());
                    com.netease.nimlib.i.b.a(bVar, str);
                }
            }
        }
    }

    public static void a(String str, List<String> list, String str2) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            a(str, it.next(), str2);
        }
    }

    public static com.netease.nimlib.push.packet.b.c a(JSONObject jSONObject) {
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            cVar.a(Integer.parseInt(next), k.e(jSONObject, next));
        }
        return cVar;
    }

    public static void a() {
        SuperTeamDBHelper.clearAllSuperTeams();
    }

    public static void a(String str, ArrayList<String> arrayList, boolean z, String str2) {
        SuperTeamDBHelper.muteTeamMembers(str, arrayList, z);
        com.netease.nimlib.i.b.b(SuperTeamDBHelper.queryTeamMembers(str, arrayList), str2);
    }

    public static boolean b() {
        long currentTimeMillis = System.currentTimeMillis();
        int i = a + 1;
        a = i;
        if (i <= 1 && b == 0) {
            a = 1;
            b = currentTimeMillis;
            return true;
        }
        if (a < 5 && currentTimeMillis - b < 5000) {
            return false;
        }
        a = 0;
        b = System.currentTimeMillis();
        return true;
    }
}
