package com.netease.nimlib.biz.e.l;

import java.util.ArrayList;
import java.util.List;

/* compiled from: SyncTInfoResponse.java */
@com.netease.nimlib.biz.e.b(a = 8, b = {"109"})
/* loaded from: classes.dex */
public class n extends com.netease.nimlib.biz.e.a {
    private long c;
    private List<com.netease.nimlib.push.packet.b.c> d;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        this.c = fVar.h();
        int g = fVar.g();
        this.d = new ArrayList(g);
        for (int i = 0; i < g; i++) {
            this.d.add(com.netease.nimlib.push.packet.c.d.a(fVar));
        }
        return null;
    }

    public List<com.netease.nimlib.push.packet.b.c> a() {
        return this.d;
    }

    public long b() {
        return this.c;
    }
}
