package com.netease.nimlib.biz.e.l;

import java.util.HashSet;
import java.util.Set;

/* compiled from: AckTeamMsgResponse.java */
@com.netease.nimlib.biz.e.b(a = 8, b = {"28"})
/* loaded from: classes.dex */
public class a extends com.netease.nimlib.biz.e.a {
    private Set<String> c;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        int g = fVar.g();
        this.c = new HashSet(g);
        for (int i = 0; i < g; i++) {
            this.c.add(com.netease.nimlib.push.packet.c.d.a(fVar).c(1));
        }
        return null;
    }

    public Set<String> a() {
        return this.c;
    }
}
