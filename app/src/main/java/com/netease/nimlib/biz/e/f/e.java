package com.netease.nimlib.biz.e.f;

import java.util.ArrayList;
import java.util.List;

/* compiled from: SyncRoamMsgHasMoreResponse.java */
@com.netease.nimlib.biz.e.b(a = 4, b = {"22"})
/* loaded from: classes.dex */
public class e extends com.netease.nimlib.biz.e.a {
    private List<com.netease.nimlib.push.packet.b.c> c;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        int g = fVar.g();
        this.c = new ArrayList(g);
        for (int i = 0; i < g; i++) {
            this.c.add(com.netease.nimlib.push.packet.c.d.a(fVar));
        }
        return null;
    }

    public List<com.netease.nimlib.push.packet.b.c> a() {
        return this.c;
    }
}
