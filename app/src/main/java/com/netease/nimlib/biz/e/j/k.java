package com.netease.nimlib.biz.e.j;

import java.util.ArrayList;
import u.aly.cs;

/* compiled from: GetMySessionListResponse.java */
@com.netease.nimlib.biz.e.b(a = cs.b.g, b = {"19"})
/* loaded from: classes.dex */
public class k extends com.netease.nimlib.biz.e.a {
    private ArrayList<com.netease.nimlib.push.packet.b.c> c;
    private com.netease.nimlib.push.packet.b.c d;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        fVar.a(cVar);
        this.d = cVar;
        int g = fVar.g();
        this.c = new ArrayList<>(g);
        for (int i = 0; i < g; i++) {
            com.netease.nimlib.push.packet.b.c cVar2 = new com.netease.nimlib.push.packet.b.c();
            fVar.a(cVar2);
            this.c.add(cVar2);
        }
        return null;
    }

    public com.netease.nimlib.push.packet.b.c a() {
        return this.d;
    }

    public ArrayList<com.netease.nimlib.push.packet.b.c> b() {
        return this.c;
    }
}
