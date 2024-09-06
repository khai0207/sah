package com.netease.nimlib.biz.c.l;

import com.netease.nimlib.biz.c.i;
import com.netease.nimlib.biz.d.l.f;
import com.netease.nimlib.biz.d.l.g;
import com.netease.nimlib.biz.d.l.h;
import com.netease.nimlib.biz.e;
import com.netease.nimlib.biz.e.m.j;
import com.netease.nimlib.biz.e.m.k;
import com.netease.nimlib.biz.e.m.l;
import com.netease.nimlib.session.t;
import com.netease.nimlib.user.UserInfoDBHelper;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: UserResponseHandler.java */
/* loaded from: classes.dex */
public class d extends i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        byte q = aVar.q();
        if (q == 3) {
            a((l) aVar);
            return;
        }
        if (q == 5) {
            b((l) aVar);
            return;
        }
        if (q == 7) {
            a((com.netease.nimlib.biz.e.m.b) aVar);
            return;
        }
        if (q == 10) {
            a((k) aVar);
        } else if (q == 13) {
            c((l) aVar);
        } else {
            if (q != 15) {
                return;
            }
            a((j) aVar);
        }
    }

    private void a(l lVar) {
        if (lVar.r() == 200) {
            com.netease.nimlib.biz.d.l.c cVar = (com.netease.nimlib.biz.d.l.c) b((com.netease.nimlib.biz.e.a) lVar);
            com.netease.nimlib.user.c.a(cVar.e(), cVar.d());
        }
        a(lVar, null);
    }

    private void b(l lVar) {
        if (lVar.r() == 200) {
            com.netease.nimlib.biz.d.l.d dVar = (com.netease.nimlib.biz.d.l.d) b((com.netease.nimlib.biz.e.a) lVar);
            com.netease.nimlib.user.c.b(dVar.e(), dVar.d());
        }
        a(lVar, null);
    }

    private void a(com.netease.nimlib.biz.e.m.b bVar) {
        ArrayList arrayList = new ArrayList();
        if (bVar.r() == 200) {
            Iterator<com.netease.nimlib.push.packet.b.c> it = bVar.a().iterator();
            while (it.hasNext()) {
                arrayList.add(com.netease.nimlib.user.b.a(it.next()));
            }
        }
        if (arrayList.size() > 0) {
            com.netease.nimlib.user.c.b(arrayList);
        }
        a(bVar, arrayList);
    }

    private void a(k kVar) {
        h hVar;
        if (kVar.n() && (hVar = (h) b(kVar)) != null) {
            com.netease.nimlib.user.c.a(hVar.d());
            if (kVar.a() > 0) {
                t.c().a(kVar.a());
                UserInfoDBHelper.updateTimeTag(com.netease.nimlib.c.n(), kVar.a());
            }
        }
        a(kVar, null);
    }

    private void a(j jVar) {
        g gVar;
        if (jVar.n() && (gVar = (g) b(jVar)) != null) {
            com.netease.nimlib.biz.l.j(jVar.a());
            com.netease.nimlib.user.c.b(gVar.d());
        }
        a(jVar, null);
    }

    private void c(l lVar) {
        if (lVar.r() == 200) {
            e d = ((f) b((com.netease.nimlib.biz.e.a) lVar)).d();
            d.a(true);
            com.netease.nimlib.biz.l.a(d);
        }
        a(lVar, null);
    }
}
