package com.netease.nimlib.biz.c.k;

import com.netease.nimlib.team.TeamDBHelper;
import java.util.ArrayList;

/* compiled from: SyncTInfoResponseHandler.java */
/* loaded from: classes.dex */
public class h extends com.netease.nimlib.biz.c.i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        if (aVar.n()) {
            com.netease.nimlib.biz.e.l.n nVar = (com.netease.nimlib.biz.e.l.n) aVar;
            ArrayList arrayList = new ArrayList();
            for (com.netease.nimlib.push.packet.b.c cVar : nVar.a()) {
                if (cVar.d(8) == 0 || cVar.d(13) == 0) {
                    String c = cVar.c(1);
                    if (TeamDBHelper.queryTeam(c) == null) {
                        com.netease.nimlib.team.c.a(com.netease.nimlib.team.d.a(cVar));
                    }
                    com.netease.nimlib.team.c.a(c, cVar.d(8) == 0);
                } else {
                    arrayList.add(com.netease.nimlib.team.d.a(cVar));
                }
            }
            if (arrayList.size() > 0) {
                TeamDBHelper.saveTeams(arrayList);
                com.netease.nimlib.log.b.N("save team info and size is " + arrayList.size());
            }
            if (arrayList.size() > 0) {
                com.netease.nimlib.i.b.j(arrayList);
            }
            com.netease.nimlib.biz.l.m(nVar.b());
        }
    }
}
