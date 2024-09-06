package com.netease.nimlib.biz.e.j;

import java.util.ArrayList;
import java.util.List;

/* compiled from: SyncSessionReliableInfoNotify.java */
@com.netease.nimlib.biz.e.b(a = 4, b = {"25"})
/* loaded from: classes.dex */
public class z extends com.netease.nimlib.biz.e.a {
    com.netease.nimlib.push.packet.b.c c;
    private List<com.netease.nimlib.push.packet.b.c> d;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        this.c = com.netease.nimlib.push.packet.c.d.a(fVar);
        int g = fVar.g();
        this.d = new ArrayList(g);
        for (int i = 0; i < g; i++) {
            com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
            fVar.a(cVar);
            this.d.add(cVar);
        }
        return null;
    }

    public com.netease.nimlib.push.packet.b.c a() {
        return this.c;
    }

    public List<com.netease.nimlib.push.packet.b.c> b() {
        return this.d;
    }
}
