package com.netease.nimlib.biz.e.l;

import java.util.ArrayList;
import java.util.List;

/* compiled from: UpdateMemberResponse.java */
@com.netease.nimlib.biz.e.b(a = 8, b = {"5", "6"})
/* loaded from: classes.dex */
public class x extends com.netease.nimlib.biz.e.a {
    private long c;
    private List<String> d;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        this.c = fVar.h();
        if (q() != 5) {
            return null;
        }
        this.d = com.netease.nimlib.push.packet.c.d.b(fVar);
        return null;
    }

    public long a() {
        return this.c;
    }

    public ArrayList<String> b() {
        if (this.d == null) {
            return null;
        }
        return new ArrayList<>(this.d);
    }

    @Override // com.netease.nimlib.biz.e.a
    protected boolean m() {
        return q() == 5 && r() == 813;
    }
}
