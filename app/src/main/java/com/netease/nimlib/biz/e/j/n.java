package com.netease.nimlib.biz.e.j;

import java.util.ArrayList;
import java.util.List;
import u.aly.cs;

/* compiled from: GetSessionReliableInfoResponse.java */
@com.netease.nimlib.biz.e.b(a = cs.b.g, b = {"28"})
/* loaded from: classes.dex */
public class n extends com.netease.nimlib.biz.e.a {
    private List<com.netease.nimlib.push.packet.b.c> c;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        int g = fVar.g();
        this.c = new ArrayList(g);
        for (int i = 0; i < g; i++) {
            com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
            fVar.a(cVar);
            this.c.add(cVar);
        }
        return null;
    }

    public List<com.netease.nimlib.push.packet.b.c> a() {
        return this.c;
    }
}
