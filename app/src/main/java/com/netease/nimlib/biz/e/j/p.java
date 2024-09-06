package com.netease.nimlib.biz.e.j;

import java.util.ArrayList;
import java.util.List;
import u.aly.cs;

/* compiled from: MsgFullKeywordSearchResponse.java */
@com.netease.nimlib.biz.e.b(a = cs.b.g, b = {"26"})
/* loaded from: classes.dex */
public class p extends com.netease.nimlib.biz.e.a {
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
