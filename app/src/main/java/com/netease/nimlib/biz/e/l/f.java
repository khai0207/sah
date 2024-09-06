package com.netease.nimlib.biz.e.l;

import java.util.ArrayList;
import java.util.List;

/* compiled from: GetTeamInfoListResponse.java */
@com.netease.nimlib.biz.e.b(a = 8, b = {"34"})
/* loaded from: classes.dex */
public class f extends com.netease.nimlib.biz.e.a {
    private List<com.netease.nimlib.push.packet.b.c> c;
    private List<Long> d;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        int g = fVar.g();
        this.c = new ArrayList(g);
        for (int i = 0; i < g; i++) {
            this.c.add(com.netease.nimlib.push.packet.c.d.a(fVar));
        }
        int g2 = fVar.g();
        this.d = new ArrayList(g2);
        for (int i2 = 0; i2 < g2; i2++) {
            this.d.add(Long.valueOf(fVar.h()));
        }
        return null;
    }

    @Override // com.netease.nimlib.biz.e.a
    protected boolean m() {
        return q() == 34 && r() == 816;
    }

    public List<com.netease.nimlib.push.packet.b.c> a() {
        return this.c;
    }

    public List<Long> b() {
        return this.d;
    }
}
