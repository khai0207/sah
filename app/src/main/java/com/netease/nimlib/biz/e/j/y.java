package com.netease.nimlib.biz.e.j;

import java.util.ArrayList;
import java.util.List;
import u.aly.cs;

/* compiled from: SyncMessageRevokeResponse.java */
@com.netease.nimlib.biz.e.b(a = cs.b.g, b = {"15"})
/* loaded from: classes.dex */
public class y extends com.netease.nimlib.biz.e.a {
    private List<com.netease.nimlib.push.packet.b.c> c;
    private long d;
    private byte e;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        int g = fVar.g();
        this.c = new ArrayList(g);
        for (int i = 0; i < g; i++) {
            this.c.add(com.netease.nimlib.push.packet.c.d.a(fVar));
        }
        this.d = fVar.h();
        this.e = fVar.c();
        return null;
    }

    public long a() {
        return this.d;
    }

    public List<com.netease.nimlib.push.packet.b.c> b() {
        return this.c;
    }

    public byte c() {
        return this.e;
    }
}
