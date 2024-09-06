package com.netease.nimlib.biz.e.k;

import com.netease.nimlib.session.z;

/* compiled from: RemoveStickTopSessionNotify.java */
@com.netease.nimlib.biz.e.b(a = 23, b = {"113"})
/* loaded from: classes.dex */
public class q extends com.netease.nimlib.biz.e.a {
    private long c;
    private z d;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        this.c = fVar.h();
        this.d = new z(com.netease.nimlib.push.packet.c.d.a(fVar));
        return null;
    }

    public long a() {
        return this.c;
    }

    public z b() {
        return this.d;
    }
}
