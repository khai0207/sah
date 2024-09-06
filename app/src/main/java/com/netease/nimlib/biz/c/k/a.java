package com.netease.nimlib.biz.c.k;

import com.netease.nimlib.sdk.ResponseCode;
import com.netease.nimlib.sdk.team.constant.TeamMemberType;
import com.netease.nimlib.sdk.team.model.CreateTeamResult;
import com.netease.nimlib.team.TeamDBHelper;
import java.util.ArrayList;
import java.util.List;

/* compiled from: CreateTeamResponseHandler.java */
/* loaded from: classes.dex */
public class a extends com.netease.nimlib.biz.c.i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        ArrayList<String> b;
        com.netease.nimlib.team.d dVar = null;
        if (aVar.n() || aVar.r() == 810) {
            com.netease.nimlib.biz.e.l.b bVar = (com.netease.nimlib.biz.e.l.b) aVar;
            dVar = com.netease.nimlib.team.d.a(bVar.a());
            dVar.c(com.netease.nimlib.c.n());
            dVar.c(1);
            dVar.f(1);
            b = bVar.b();
            com.netease.nimlib.biz.d.k.c cVar = (com.netease.nimlib.biz.d.k.c) b(aVar);
            if (cVar != null) {
                a(dVar, cVar, b, aVar.n());
                com.netease.nimlib.biz.l.a(dVar.getId(), dVar.c());
            } else {
                com.netease.nimlib.biz.d.k.h hVar = new com.netease.nimlib.biz.d.k.h();
                hVar.a(dVar.getId());
                hVar.a(0L);
                com.netease.nimlib.biz.i.a().a(hVar);
            }
            com.netease.nimlib.team.c.a(dVar);
            aVar.j().b(ResponseCode.RES_SUCCESS);
        } else {
            b = null;
        }
        a(aVar, new CreateTeamResult(dVar, b));
    }

    private void a(com.netease.nimlib.team.d dVar, com.netease.nimlib.biz.d.k.c cVar, List<String> list, boolean z) {
        if (cVar.d() == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        long c = dVar.c();
        if (c == 0) {
            c = dVar.getCreateTime();
        }
        if (z) {
            for (String str : cVar.d()) {
                if (!str.equals(com.netease.nimlib.c.n()) && (list == null || !list.contains(str))) {
                    com.netease.nimlib.team.g gVar = new com.netease.nimlib.team.g();
                    gVar.a(dVar.getId());
                    gVar.b(1);
                    gVar.b(str);
                    gVar.a(TeamMemberType.Normal);
                    gVar.d(com.netease.nimlib.c.n());
                    gVar.b(c);
                    arrayList.add(gVar);
                }
            }
        }
        com.netease.nimlib.team.g gVar2 = new com.netease.nimlib.team.g();
        gVar2.a(dVar.getId());
        gVar2.b(1);
        gVar2.b(com.netease.nimlib.c.n());
        gVar2.a(TeamMemberType.Owner);
        gVar2.d("");
        gVar2.b(c);
        arrayList.add(gVar2);
        TeamDBHelper.saveTeamMembers(arrayList);
    }
}
