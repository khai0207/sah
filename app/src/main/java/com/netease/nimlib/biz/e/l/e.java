package com.netease.nimlib.biz.e.l;

import java.util.ArrayList;
import java.util.List;

/* compiled from: GetTeamInfoBatchResponse.java */
@com.netease.nimlib.biz.e.b(a = 8, b = {"10"})
/* loaded from: classes.dex */
public class e extends com.netease.nimlib.biz.e.a {
    private List<com.netease.nimlib.push.packet.b.c> c;
    private long d;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        int g = fVar.g();
        this.c = new ArrayList(g);
        for (int i = 0; i < g; i++) {
            this.c.add(com.netease.nimlib.push.packet.c.d.a(fVar));
        }
        this.d = fVar.h();
        return null;
    }
}
