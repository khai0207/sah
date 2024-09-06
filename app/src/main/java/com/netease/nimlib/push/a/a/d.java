package com.netease.nimlib.push.a.a;

import com.netease.nimlib.biz.i;
import com.netease.nimlib.push.f;

/* compiled from: NotifyResponseHandler.java */
/* loaded from: classes.dex */
public class d extends com.netease.nimlib.biz.c.a {
    private final boolean a;

    public d(boolean z) {
        this.a = z;
    }

    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        com.netease.nimlib.push.a.c.e eVar = (com.netease.nimlib.push.a.c.e) aVar;
        com.netease.nimlib.push.packet.a b = eVar.b();
        if (eVar.a() != 0 && com.netease.nimlib.biz.c.e.a(this.a).a(b) && a(b, eVar.a())) {
            com.netease.nimlib.biz.d.e.b bVar = new com.netease.nimlib.biz.d.e.b();
            bVar.a(eVar.a());
            bVar.a(eVar.b());
            if (this.a) {
                f.l().a(bVar);
                return;
            }
            if (eVar.l() > 0) {
                bVar.a(eVar.l());
            }
            i.a().a(bVar, com.netease.nimlib.biz.g.a.d);
        }
    }

    private boolean a(com.netease.nimlib.push.packet.a aVar, long j) {
        if (j <= 0) {
            return false;
        }
        return (aVar.i() == 7 && aVar.j() == 101) ? false : true;
    }
}
