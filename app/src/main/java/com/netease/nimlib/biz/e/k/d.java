package com.netease.nimlib.biz.e.k;

import com.netease.nimlib.session.z;

/* compiled from: AddOrUpdateStickTopSessionResponse.java */
@com.netease.nimlib.biz.e.b(a = 23, b = {"12", "112", "14", "114"})
/* loaded from: classes.dex */
public class d extends com.netease.nimlib.biz.e.a {
    private z c;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        this.c = new z(com.netease.nimlib.push.packet.c.d.a(fVar));
        return null;
    }

    public z a() {
        return this.c;
    }
}
