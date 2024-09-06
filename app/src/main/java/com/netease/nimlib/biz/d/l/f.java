package com.netease.nimlib.biz.d.l;

import u.aly.df;

/* compiled from: UpdateDndConfigRequest.java */
/* loaded from: classes.dex */
public class f extends com.netease.nimlib.biz.d.a {
    private com.netease.nimlib.biz.e a;
    private com.netease.nimlib.push.packet.b.c b;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 3;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return df.k;
    }

    public f(com.netease.nimlib.biz.e eVar) {
        this.a = eVar;
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        this.b = cVar;
        cVar.a(1, eVar.f() ? 2 : 1);
        this.b.a(2, eVar.isOpen() ? 1 : 2);
        this.b.a(3, eVar.b());
        this.b.a(4, eVar.c());
        this.b.a(5, eVar.d());
        this.b.a(6, eVar.e());
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.b);
        return bVar;
    }

    public com.netease.nimlib.biz.e d() {
        return this.a;
    }
}
