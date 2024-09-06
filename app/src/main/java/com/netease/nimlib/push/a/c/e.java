package com.netease.nimlib.push.a.c;

/* compiled from: NotifyResponse.java */
@com.netease.nimlib.biz.e.b(a = 4, b = {"1", "2", "10", "11"})
/* loaded from: classes.dex */
public class e extends com.netease.nimlib.biz.e.a {
    private long c;
    private com.netease.nimlib.push.packet.a d;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        this.c = fVar.h();
        com.netease.nimlib.push.packet.a aVar = new com.netease.nimlib.push.packet.a();
        this.d = aVar;
        aVar.a(fVar);
        if (this.a.j() == 2) {
            this.d.a((short) 0);
        } else {
            this.d.a((short) 1);
        }
        if (this.d.i() == 13) {
            com.netease.nimlib.log.b.j("embedded packet: " + this.d);
        } else {
            com.netease.nimlib.log.b.f("embedded packet: " + this.d);
        }
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.d);
        bVar.a(fVar.b());
        return new com.netease.nimlib.push.packet.c.f(bVar.b());
    }

    public long a() {
        return this.c;
    }

    public com.netease.nimlib.push.packet.a b() {
        return this.d;
    }
}
