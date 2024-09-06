package com.netease.nimlib.biz.e.b;

import com.netease.nimlib.push.packet.c.f;
import java.util.ArrayList;
import java.util.List;

/* compiled from: SyncFriendListResponse.java */
@com.netease.nimlib.biz.e.b(a = 12, b = {"5", "6"})
/* loaded from: classes.dex */
public class d extends com.netease.nimlib.biz.e.a {
    private List<com.netease.nimlib.push.packet.b.c> c;
    private long d;

    @Override // com.netease.nimlib.biz.e.a
    public f a(f fVar) throws Exception {
        int g = fVar.g();
        this.c = new ArrayList(g);
        for (int i = 0; i < g; i++) {
            this.c.add(com.netease.nimlib.push.packet.c.d.a(fVar));
        }
        this.d = fVar.h();
        return null;
    }

    public List<com.netease.nimlib.push.packet.b.c> a() {
        return this.c;
    }

    public long b() {
        return this.d;
    }
}
