package com.netease.nimlib.biz.e.l;

import java.util.ArrayList;
import java.util.List;

/* compiled from: CreateTeamResponse.java */
@com.netease.nimlib.biz.e.b(a = 8, b = {"1", "101"})
/* loaded from: classes.dex */
public class b extends com.netease.nimlib.biz.e.a {
    private com.netease.nimlib.push.packet.b.c c;
    private List<String> d;

    @Override // com.netease.nimlib.biz.e.a
    protected boolean m() {
        return true;
    }

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        if (!n() && r() != 810) {
            return null;
        }
        this.c = com.netease.nimlib.push.packet.c.d.a(fVar);
        if (q() != 1) {
            return null;
        }
        this.d = com.netease.nimlib.push.packet.c.d.b(fVar);
        return null;
    }

    public com.netease.nimlib.push.packet.b.c a() {
        return this.c;
    }

    public ArrayList<String> b() {
        if (this.d == null) {
            return null;
        }
        return new ArrayList<>(this.d);
    }
}
