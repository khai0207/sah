package com.netease.nimlib.biz.e.f;

import java.util.Map;

/* compiled from: SyncSessionAckResponse.java */
@com.netease.nimlib.biz.e.b(a = 4, b = {"14"})
/* loaded from: classes.dex */
public class g extends com.netease.nimlib.biz.e.a {
    private Map<String, Long> c;
    private Map<String, Long> d;
    private long e;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        com.netease.nimlib.push.packet.b.d dVar = new com.netease.nimlib.push.packet.b.d();
        fVar.a(dVar);
        this.c = dVar.a;
        com.netease.nimlib.push.packet.b.a aVar = new com.netease.nimlib.push.packet.b.a();
        fVar.a(aVar);
        this.d = aVar.a();
        this.e = fVar.h();
        return null;
    }

    public Map<String, Long> a() {
        return this.c;
    }

    public Map<String, Long> b() {
        return this.d;
    }

    public long c() {
        return this.e;
    }
}
