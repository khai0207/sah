package com.netease.nimlib.biz.c;

import com.netease.nimlib.biz.e.a;

/* compiled from: ResponseDispatcherPush.java */
/* loaded from: classes.dex */
public class c extends b {
    @Override // com.netease.nimlib.biz.c.b
    protected boolean a(com.netease.nimlib.biz.e.a aVar) {
        return true;
    }

    public c(com.netease.nimlib.c.b.b bVar, h hVar) {
        super(e.a(true), bVar, hVar);
    }

    @Override // com.netease.nimlib.biz.c.b
    protected void a(com.netease.nimlib.push.packet.a aVar, com.netease.nimlib.push.packet.c.f fVar, int i) {
        if (this.a.c(aVar) == null) {
            return;
        }
        super.a(aVar, fVar, i);
    }

    @Override // com.netease.nimlib.biz.c.b
    protected com.netease.nimlib.biz.e.a a(com.netease.nimlib.push.packet.a aVar, com.netease.nimlib.push.packet.c.f fVar) {
        com.netease.nimlib.biz.e.a d = this.a.d(aVar);
        short l = aVar.l();
        if ((com.netease.nimlib.h.b() || ((f) this.a).f(aVar)) && ((l == 200 || aVar.j() != 2 || aVar.i() != 2 || !com.netease.nimlib.push.f.l().a()) && (!aVar.d() || (l != 398 && l != 399)))) {
            b(aVar, fVar);
        }
        return d;
    }

    private void b(com.netease.nimlib.push.packet.a aVar, com.netease.nimlib.push.packet.c.f fVar) {
        a.C0029a c0029a = new a.C0029a();
        c0029a.a = aVar;
        c0029a.b = fVar;
        if (com.netease.nimlib.ipc.a.b.a(aVar, fVar)) {
            c0029a.c = com.netease.nimlib.ipc.a.b.c().a(aVar);
        }
        com.netease.nimlib.ipc.e.a(c0029a);
    }
}
