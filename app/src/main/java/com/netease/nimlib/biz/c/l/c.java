package com.netease.nimlib.biz.c.l;

import com.netease.nimlib.biz.c.i;
import com.netease.nimlib.biz.e.m.e;
import com.netease.nimlib.biz.e.m.f;
import com.netease.nimlib.biz.e.m.h;
import com.netease.nimlib.biz.l;
import com.netease.nimlib.sdk.friend.model.BlackListChangedNotify;
import com.netease.nimlib.session.t;
import com.netease.nimlib.session.u;
import com.netease.nimlib.user.UserDBHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: SyncUserResponseHandler.java */
/* loaded from: classes.dex */
public class c extends i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        byte q = aVar.q();
        if (q == 7) {
            a((com.netease.nimlib.biz.e.f.c) aVar);
            return;
        }
        if (q == 8) {
            a((e) aVar);
            return;
        }
        if (q == 13) {
            a((com.netease.nimlib.biz.e.f.d) aVar);
            return;
        }
        if (q == 103) {
            a((com.netease.nimlib.biz.e.m.c) aVar);
            return;
        }
        if (q == 105) {
            a((com.netease.nimlib.biz.e.m.d) aVar);
            return;
        }
        if (q == 115) {
            a((h) aVar);
        } else if (q == 109) {
            a((f) aVar);
        } else {
            if (q != 110) {
                return;
            }
            a((com.netease.nimlib.biz.e.m.i) aVar);
        }
    }

    private void a(e eVar) {
        List<com.netease.nimlib.push.packet.b.c> a = eVar.a();
        ArrayList<com.netease.nimlib.user.d> arrayList = new ArrayList(a.size());
        Iterator<com.netease.nimlib.push.packet.b.c> it = a.iterator();
        while (it.hasNext()) {
            arrayList.add(com.netease.nimlib.user.d.a(it.next()));
        }
        if (!arrayList.isEmpty()) {
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            ArrayList<String> blackList = UserDBHelper.getBlackList();
            com.netease.nimlib.log.b.L("onSyncMuteAndBlackList blackList = " + blackList);
            for (com.netease.nimlib.user.d dVar : arrayList) {
                if (dVar.c() != blackList.contains(dVar.a())) {
                    if (dVar.c()) {
                        arrayList2.add(dVar.a());
                    } else {
                        arrayList3.add(dVar.a());
                    }
                }
            }
            UserDBHelper.saveUserSpecialTags(arrayList);
            if (!arrayList2.isEmpty() || !arrayList3.isEmpty()) {
                com.netease.nimlib.i.b.a(new BlackListChangedNotify(arrayList2, arrayList3));
            }
        }
        l.q(eVar.b());
    }

    private void a(f fVar) {
        if (fVar.a() != null) {
            com.netease.nimlib.user.c.a(com.netease.nimlib.user.b.a(fVar.a()));
            com.netease.nimlib.c.y();
            u.c().b();
            t.c().a();
        }
        l.k(fVar.b());
    }

    private void a(com.netease.nimlib.biz.e.m.c cVar) {
        com.netease.nimlib.user.c.a(cVar.a(), cVar.b());
    }

    private void a(com.netease.nimlib.biz.e.m.d dVar) {
        com.netease.nimlib.user.c.b(dVar.a(), dVar.b());
    }

    private void a(com.netease.nimlib.biz.e.m.i iVar) {
        if (iVar.a() == null) {
            return;
        }
        com.netease.nimlib.user.c.a(iVar.a());
    }

    private void a(h hVar) {
        if (hVar.a() != null) {
            com.netease.nimlib.user.a a = com.netease.nimlib.user.a.a(hVar.a());
            l.e(a.a());
            com.netease.nimlib.i.b.c(a.a());
        }
        l.j(hVar.b());
    }

    private void a(com.netease.nimlib.biz.e.f.d dVar) {
        if (dVar.a() != null) {
            com.netease.nimlib.user.a a = com.netease.nimlib.user.a.a(dVar.a());
            l.e(a.a());
            com.netease.nimlib.i.b.c(a.a());
        }
        l.j(dVar.b());
    }

    private void a(com.netease.nimlib.biz.e.f.c cVar) {
        l.g(cVar.b());
        com.netease.nimlib.biz.e a = cVar.a();
        if (l.o() == 0 && a.g()) {
            return;
        }
        a.a(true);
        l.a(a);
    }
}
