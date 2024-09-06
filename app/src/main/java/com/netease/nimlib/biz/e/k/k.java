package com.netease.nimlib.biz.e.k;

import java.util.ArrayList;
import java.util.List;

/* compiled from: QueryThreadTalkHistoryResponse.java */
@com.netease.nimlib.biz.e.b(a = 23, b = {"1"})
/* loaded from: classes.dex */
public class k extends com.netease.nimlib.biz.e.a {
    private com.netease.nimlib.push.packet.b.c c;
    private int d;
    private long e;
    private List<com.netease.nimlib.push.packet.b.c> f;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        this.c = com.netease.nimlib.push.packet.c.d.a(fVar);
        com.netease.nimlib.push.packet.b.c a = com.netease.nimlib.push.packet.c.d.a(fVar);
        this.d = a.d(1);
        this.e = a.e(2);
        int g = fVar.g();
        this.f = new ArrayList(g);
        for (int i = 0; i < g; i++) {
            this.f.add(com.netease.nimlib.push.packet.c.d.a(fVar));
        }
        return null;
    }

    public com.netease.nimlib.push.packet.b.c a() {
        return this.c;
    }

    public int b() {
        return this.d;
    }

    public long c() {
        return this.e;
    }

    public List<com.netease.nimlib.push.packet.b.c> d() {
        return this.f;
    }
}
