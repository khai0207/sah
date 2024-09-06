package com.netease.nimlib.n;

/* compiled from: BaseExceptionEventManager.java */
/* loaded from: classes.dex */
public abstract class b {
    public abstract void a(com.netease.nimlib.n.b.f fVar, com.netease.nimlib.n.c.d dVar, com.netease.nimlib.n.b.h hVar);

    public abstract void a(com.netease.nimlib.n.b.f fVar, String str, com.netease.nimlib.n.b.g gVar);

    public abstract void a(com.netease.nimlib.n.b.f fVar, String str, com.netease.nimlib.n.b.g gVar, com.netease.nimlib.n.c.d dVar);

    public abstract void a(com.netease.nimlib.n.b.f fVar, String str, com.netease.nimlib.n.c.d dVar, com.netease.nimlib.n.b.h hVar);

    public abstract void a(com.netease.nimlib.n.b.f fVar, String str, String str2, com.netease.nimlib.n.c.d dVar, com.netease.nimlib.n.b.h hVar);

    public void a(com.netease.nimlib.push.net.lbs.b bVar, com.netease.nimlib.n.b.g gVar) {
        a(com.netease.nimlib.n.b.f.kTCP, bVar.toString(), gVar);
    }

    public void a(com.netease.nimlib.push.net.lbs.b bVar, com.netease.nimlib.n.c.n nVar, com.netease.nimlib.n.b.h hVar) {
        a(com.netease.nimlib.n.b.f.kTCP, bVar.toString(), nVar, hVar);
    }

    public void a(String str, com.netease.nimlib.n.b.g gVar) {
        a(com.netease.nimlib.n.b.f.kHTTP, str, gVar);
    }

    public void a(String str, String str2, com.netease.nimlib.n.c.f fVar, com.netease.nimlib.n.b.h hVar) {
        a(com.netease.nimlib.n.b.f.kHTTP, str, str2, fVar, hVar);
    }

    public void a(com.netease.nimlib.push.packet.a aVar, com.netease.nimlib.n.b.g gVar) {
        a(com.netease.nimlib.n.b.f.kBusiness, ((int) aVar.k()) + "", gVar);
    }

    public void a(com.netease.nimlib.push.packet.a aVar, com.netease.nimlib.n.c.a aVar2, com.netease.nimlib.n.b.h hVar) {
        a(com.netease.nimlib.n.b.f.kBusiness, ((int) aVar.k()) + "", aVar2, hVar);
    }

    public void a(com.netease.nimlib.n.c.a aVar, com.netease.nimlib.n.b.h hVar) {
        a(com.netease.nimlib.n.b.f.kBusiness, aVar, hVar);
    }

    public void a(com.netease.nimlib.n.c.g gVar, com.netease.nimlib.n.b.h hVar) {
        a(com.netease.nimlib.n.b.f.kLibrary, gVar, hVar);
    }

    public void a(com.netease.nimlib.n.c.c cVar, com.netease.nimlib.n.b.h hVar) {
        a(com.netease.nimlib.n.b.f.kDatabase, cVar, hVar);
    }

    public void a(com.netease.nimlib.n.c.e eVar, com.netease.nimlib.n.b.h hVar) {
        a(com.netease.nimlib.n.b.f.kFile, eVar, hVar);
    }

    public void a(com.netease.nimlib.n.c.k kVar, com.netease.nimlib.n.b.h hVar) {
        a(com.netease.nimlib.n.b.f.kRuntime, kVar, hVar);
    }

    public void a(com.netease.nimlib.push.net.lbs.b bVar, com.netease.nimlib.n.b.g gVar, com.netease.nimlib.n.c.h hVar) {
        a(com.netease.nimlib.n.b.f.kLinkKeep, bVar.toString(), gVar, hVar);
    }

    public void a(com.netease.nimlib.push.net.lbs.b bVar, com.netease.nimlib.n.c.h hVar, com.netease.nimlib.n.b.h hVar2) {
        a(com.netease.nimlib.n.b.f.kLinkKeep, bVar.toString(), hVar, hVar2);
    }
}
