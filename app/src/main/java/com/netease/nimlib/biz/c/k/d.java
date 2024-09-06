package com.netease.nimlib.biz.c.k;

import com.netease.nimlib.o.f;
import com.netease.nimlib.team.TeamDBHelper;
import java.util.List;

/* compiled from: GetTInfoResponseHandler.java */
/* loaded from: classes.dex */
public class d extends com.netease.nimlib.biz.c.i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        if ((aVar instanceof com.netease.nimlib.biz.e.l.f) && (aVar.n() || aVar.r() == 816)) {
            a((com.netease.nimlib.biz.e.l.f) aVar);
        } else if (aVar instanceof com.netease.nimlib.biz.e.l.g) {
            a((com.netease.nimlib.biz.e.l.g) aVar);
        }
    }

    private void a(com.netease.nimlib.biz.e.l.f fVar) {
        List<com.netease.nimlib.push.packet.b.c> a = fVar.a();
        List<Long> b = fVar.b();
        a(fVar, new com.netease.nimlib.team.e(fVar.r(), com.netease.nimlib.o.f.c(a, new f.a() { // from class: com.netease.nimlib.biz.c.k.-$$Lambda$9yYdoI6xsC5aZG_u-lwBSQ6UxKs
            @Override // com.netease.nimlib.o.f.a
            public final Object transform(Object obj) {
                return com.netease.nimlib.team.d.a((com.netease.nimlib.push.packet.b.c) obj);
            }
        }), b), 200);
    }

    private void a(com.netease.nimlib.biz.e.l.g gVar) {
        if (gVar.n()) {
            com.netease.nimlib.team.d a = com.netease.nimlib.team.d.a(gVar.a());
            com.netease.nimlib.team.d queryTeam = TeamDBHelper.queryTeam(a.getId());
            if (queryTeam != null && queryTeam.isMyTeam()) {
                a.f(1);
            }
            a(gVar, a, gVar.r());
            com.netease.nimlib.team.c.a(a);
            return;
        }
        a(gVar, null, gVar.r());
    }
}
