package com.netease.nimlib.biz.c.k;

import android.text.TextUtils;
import com.netease.nimlib.team.TeamDBHelper;
import com.netease.nimlib.user.UserInfoDBHelper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/* compiled from: GetMemberListResponseHandler.java */
/* loaded from: classes.dex */
public class c extends com.netease.nimlib.biz.c.i {
    protected String a() {
        return "GetMemberListResponseHandler";
    }

    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        if (aVar.n()) {
            boolean z = aVar.j().j() == 111;
            com.netease.nimlib.biz.e.l.d dVar = (com.netease.nimlib.biz.e.l.d) aVar;
            String a = dVar.a();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            if (dVar.d()) {
                HashSet hashSet = new HashSet();
                Iterator<com.netease.nimlib.push.packet.b.c> it = dVar.b().iterator();
                while (it.hasNext()) {
                    com.netease.nimlib.team.g a2 = com.netease.nimlib.team.g.a(it.next());
                    arrayList.add(a2);
                    hashSet.add(a2.getAccount());
                    if (a2.getAccount().equals(com.netease.nimlib.c.n())) {
                        com.netease.nimlib.team.c.b(a2);
                    }
                }
                if (z) {
                    com.netease.nimlib.log.b.L(a() + " isRefresh getUpdateTimeTags account = " + hashSet);
                    Map<String, Long> updateTimeTags = UserInfoDBHelper.getUpdateTimeTags(hashSet);
                    com.netease.nimlib.log.b.L(a() + " isRefresh getUpdateTimeTags = " + updateTimeTags);
                    Iterator it2 = hashSet.iterator();
                    while (it2.hasNext()) {
                        String str = (String) it2.next();
                        Long l = updateTimeTags.get(str);
                        if (!TextUtils.isEmpty(str) && !str.equals(com.netease.nimlib.c.n()) && (l == null || l.longValue() <= 0)) {
                            arrayList3.add(str);
                        }
                    }
                }
                Iterator<com.netease.nimlib.team.g> it3 = TeamDBHelper.queryMemberList(a).iterator();
                while (it3.hasNext()) {
                    com.netease.nimlib.team.g next = it3.next();
                    if (!hashSet.contains(next.getAccount())) {
                        next.b(0);
                        arrayList2.add(next);
                    }
                }
                TeamDBHelper.refreshAllTeamMembers(a, arrayList, arrayList2);
                com.netease.nimlib.i.b.k(arrayList);
                com.netease.nimlib.i.b.l(arrayList2);
            } else {
                HashSet hashSet2 = new HashSet();
                Iterator<com.netease.nimlib.push.packet.b.c> it4 = dVar.b().iterator();
                while (it4.hasNext()) {
                    com.netease.nimlib.team.g a3 = com.netease.nimlib.team.g.a(it4.next());
                    if (a3.b() != 1) {
                        if (TeamDBHelper.queryTeamMember(a, a3.getAccount()) != null) {
                            TeamDBHelper.deleteTeamMember(a, a3.getAccount());
                            arrayList2.add(a3);
                        }
                    } else {
                        a3.a(a);
                        arrayList.add(a3);
                        hashSet2.add(a3.getAccount());
                    }
                }
                if (z) {
                    com.netease.nimlib.log.b.L(a() + " getUpdateTimeTags account = " + hashSet2);
                    Map<String, Long> updateTimeTags2 = UserInfoDBHelper.getUpdateTimeTags(hashSet2);
                    com.netease.nimlib.log.b.L(a() + " getUpdateTimeTags = " + updateTimeTags2);
                    Iterator it5 = hashSet2.iterator();
                    while (it5.hasNext()) {
                        String str2 = (String) it5.next();
                        Long l2 = updateTimeTags2.get(str2);
                        if (!TextUtils.isEmpty(str2) && !str2.equals(com.netease.nimlib.c.n()) && (l2 == null || l2.longValue() <= 0)) {
                            arrayList3.add(str2);
                        }
                    }
                }
                if (arrayList.size() > 0) {
                    com.netease.nimlib.team.c.a((ArrayList<com.netease.nimlib.team.g>) arrayList);
                }
                com.netease.nimlib.i.b.l(arrayList2);
            }
            com.netease.nimlib.biz.l.a(a, dVar.c());
            if (z && !arrayList3.isEmpty() && com.netease.nimlib.c.M()) {
                com.netease.nimlib.log.b.L(a() + " fetchUserInfo account = " + arrayList3);
                com.netease.nimlib.user.c.a(arrayList3);
            }
            com.netease.nimlib.log.b.d(a(), "refresh =  " + dVar.d() + " , sync = " + z + " , tid = " + a + " , member size = " + arrayList.size() + " , time = " + dVar.c());
        }
    }
}
