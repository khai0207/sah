package com.netease.nimlib.push.a.a;

import android.text.TextUtils;
import android.util.Pair;
import com.netease.nimlib.biz.i;
import com.netease.nimlib.biz.l;
import com.netease.nimlib.n.p;
import com.netease.nimlib.plugin.interact.IMixPushInteract;
import com.netease.nimlib.push.a.c.g;
import com.netease.nimlib.push.f;
import com.netease.nimlib.sdk.auth.constant.LoginSyncStatus;
import com.netease.nimlib.sdk.superteam.SuperTeam;
import com.netease.nimlib.sdk.team.model.Team;
import com.netease.nimlib.superteam.SuperTeamDBHelper;
import com.netease.nimlib.team.TeamDBHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* compiled from: SyncResponseHandler.java */
/* loaded from: classes.dex */
public class e extends com.netease.nimlib.biz.c.a {
    private final boolean a;

    public e(boolean z) {
        this.a = z;
    }

    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        p.a(aVar.n(), aVar.r());
        if (!this.a) {
            a();
            long a = ((g) aVar).a();
            if (a == 0) {
                com.netease.nimlib.log.b.N("this is fake sync response in ui process");
                return;
            }
            com.netease.nimlib.c.c(false);
            if (aVar.n()) {
                l.o(a);
                com.netease.nimlib.session.a.c.a().a(a);
            }
            com.netease.nimlib.log.b.N("SDK login sync data completed");
            com.netease.nimlib.c.y();
            com.netease.nimlib.i.b.a(LoginSyncStatus.SYNC_COMPLETED);
            c();
            b();
            return;
        }
        f.l().i();
        if (!aVar.n()) {
            com.netease.nimlib.log.b.O("SDK login sync data failed, disconnect link! code=" + ((int) aVar.r()));
            f.l().h();
            return;
        }
        com.netease.nimlib.log.b.O("SDK login sync data succeed");
    }

    private void a() {
        IMixPushInteract iMixPushInteract = (IMixPushInteract) com.netease.nimlib.plugin.interact.b.a().a(IMixPushInteract.class);
        if (iMixPushInteract != null) {
            iMixPushInteract.a();
        }
    }

    private void b() {
        if (!com.netease.nimlib.c.L()) {
            com.netease.nimlib.log.b.N("sync superTeam member disable");
            return;
        }
        ArrayList<SuperTeam> queryAllSuperTeams = SuperTeamDBHelper.queryAllSuperTeams();
        ArrayList<Pair<String, Long>> arrayList = new ArrayList<>();
        ArrayList<Pair<String, Long>> arrayList2 = new ArrayList<>();
        ArrayList arrayList3 = new ArrayList();
        if (TextUtils.isEmpty(com.netease.nimlib.c.n())) {
            return;
        }
        for (SuperTeam superTeam : queryAllSuperTeams) {
            long e = l.e(superTeam.getId());
            if (e == 0) {
                arrayList3.add(superTeam.getId());
            }
            if (e == 0 || ((com.netease.nimlib.superteam.b) superTeam).d() > e) {
                if (superTeam.getMemberLimit() > 2000) {
                    arrayList.add(new Pair<>(superTeam.getId(), Long.valueOf(e)));
                } else {
                    arrayList2.add(new Pair<>(superTeam.getId(), Long.valueOf(e)));
                }
            }
        }
        if (arrayList3.size() > 0 && SuperTeamDBHelper.clearTeamMembers(false, arrayList3) < 0) {
            com.netease.nimlib.log.b.N("clear super team member dirty data failed");
            com.netease.nimlib.i.b.b(false);
            return;
        }
        com.netease.nimlib.log.b.N("clear super team member dirty data, size =" + arrayList3.size() + " , data = " + arrayList3.toString());
        List<Pair<String, Long>> a = a(arrayList, arrayList2);
        if (a != null && !a.isEmpty()) {
            a(a, 0, 500);
            com.netease.nimlib.log.b.N("sync super team member info , request amount = " + a.size() + " , data = " + a.toString());
            return;
        }
        com.netease.nimlib.i.b.b(true);
        com.netease.nimlib.log.b.N("no need to sync super team member info ");
    }

    private List<Pair<String, Long>> a(ArrayList<Pair<String, Long>> arrayList, ArrayList<Pair<String, Long>> arrayList2) {
        int size = arrayList.size();
        int size2 = arrayList2.size();
        ArrayList arrayList3 = new ArrayList();
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i >= size && i2 >= size2) {
                return arrayList3;
            }
            int min = Math.min(size - i, 10);
            if (i < size) {
                int i3 = i + min;
                arrayList3.addAll(arrayList.subList(i, i3));
                i = i3;
            }
            int min2 = Math.min(size2 - i2, 500 - min);
            if (i2 < size2) {
                int i4 = i2 + min2;
                arrayList3.addAll(arrayList2.subList(i2, i4));
                i2 = i4;
            }
            int i5 = (500 - min2) - min;
            for (int i6 = 0; i6 < i5; i6++) {
                arrayList3.add(null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final List<Pair<String, Long>> list, final int i, final int i2) {
        int size;
        if (list == null || i >= (size = list.size()) || i < 0 || i2 <= 0) {
            return;
        }
        final int min = Math.min(size, i + i2);
        List<Pair<String, Long>> subList = list.subList(i, min);
        int indexOf = subList.indexOf(null);
        if (indexOf != -1) {
            subList = subList.subList(0, indexOf);
        }
        i.a().a(new com.netease.nimlib.biz.g.b(new com.netease.nimlib.biz.d.h.b(subList)) { // from class: com.netease.nimlib.push.a.a.e.1
            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
            public void a(com.netease.nimlib.biz.e.a aVar) {
                com.netease.nimlib.log.b.N("sync super team, startIndex=" + i + ", stopIndex=" + min + ", code=" + ((int) aVar.r()));
                e.this.a(list, min, i2);
            }
        });
    }

    private void c() {
        if (!com.netease.nimlib.c.K()) {
            com.netease.nimlib.log.b.N("sync team member disable");
            return;
        }
        ArrayList<Team> queryAllTeams = TeamDBHelper.queryAllTeams();
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(com.netease.nimlib.c.n())) {
            return;
        }
        for (Team team : queryAllTeams) {
            long d = l.d(team.getId());
            if (d == 0) {
                arrayList.add(team.getId());
            }
            if (d == 0 || ((com.netease.nimlib.team.d) team).d() > d) {
                hashMap.put(team.getId(), Long.valueOf(d));
            }
        }
        if (arrayList.size() > 0 && TeamDBHelper.clearTeamMembers(false, arrayList) < 0) {
            com.netease.nimlib.log.b.N("clear team member dirty data failed");
            com.netease.nimlib.i.b.a(false);
            return;
        }
        com.netease.nimlib.log.b.N("clear team member dirty data, size =" + arrayList.size() + " , data = " + arrayList.toString());
        if (hashMap.size() > 0) {
            i.a().a(new com.netease.nimlib.biz.d.h.c(hashMap), com.netease.nimlib.biz.g.a.d);
            com.netease.nimlib.log.b.N("sync team member info , size = " + hashMap.size() + " , data = " + hashMap.toString());
            return;
        }
        com.netease.nimlib.i.b.a(true);
        com.netease.nimlib.log.b.N("no need to sync team member info ");
    }
}
