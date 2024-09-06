package com.netease.nimlib.biz.e.j;

import java.util.ArrayList;
import java.util.List;

/* compiled from: SyncMessageReceiptResponse.java */
@com.netease.nimlib.biz.e.b(a = 4, b = {"12"})
/* loaded from: classes.dex */
public class x extends com.netease.nimlib.biz.e.a {
    private List<com.netease.nimlib.session.f> c;
    private long d;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        int g = fVar.g();
        this.c = new ArrayList(g);
        for (int i = 0; i < g; i++) {
            com.netease.nimlib.push.packet.b.c a = com.netease.nimlib.push.packet.c.d.a(fVar);
            this.c.add(new com.netease.nimlib.session.f(a.c(2), 0L, a.e(7)));
        }
        this.d = fVar.h();
        return null;
    }

    public List<com.netease.nimlib.session.f> a() {
        return this.c;
    }

    public long b() {
        return this.d;
    }
}
