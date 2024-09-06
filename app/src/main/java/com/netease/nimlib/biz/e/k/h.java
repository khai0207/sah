package com.netease.nimlib.biz.e.k;

import java.util.ArrayList;

/* compiled from: QueryCollectResponse.java */
@com.netease.nimlib.biz.e.b(a = 23, b = {"11"})
/* loaded from: classes.dex */
public class h extends com.netease.nimlib.biz.e.a {
    private long c;
    private ArrayList<com.netease.nimlib.session.a> d;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        if (fVar == null) {
            this.d = new ArrayList<>(0);
            return null;
        }
        this.c = fVar.h();
        int g = fVar.g();
        this.d = new ArrayList<>(g);
        for (int i = 0; i < g; i++) {
            this.d.add(new com.netease.nimlib.session.a(com.netease.nimlib.push.packet.c.d.a(fVar)));
        }
        return null;
    }

    public long a() {
        return this.c;
    }

    public ArrayList<com.netease.nimlib.session.a> b() {
        return this.d;
    }
}
