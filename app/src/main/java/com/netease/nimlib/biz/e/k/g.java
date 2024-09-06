package com.netease.nimlib.biz.e.k;

import java.util.ArrayList;

/* compiled from: MsgPinSyncResponse.java */
@com.netease.nimlib.biz.e.b(a = 23, b = {"21"})
/* loaded from: classes.dex */
public class g extends com.netease.nimlib.biz.e.a {
    private long c;
    private boolean d;
    private ArrayList<com.netease.nimlib.session.o> e;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        this.c = fVar.h();
        boolean k = fVar.k();
        this.d = k;
        if (!k) {
            this.e = new ArrayList<>(0);
            return null;
        }
        int d = com.netease.nimlib.push.packet.c.d.d(fVar);
        this.e = new ArrayList<>(d);
        for (int i = 0; i < d; i++) {
            this.e.add(com.netease.nimlib.session.o.a(com.netease.nimlib.push.packet.c.d.a(fVar)));
        }
        return null;
    }

    public long a() {
        return this.c;
    }

    public boolean b() {
        return this.d;
    }

    public ArrayList<com.netease.nimlib.session.o> c() {
        return this.e;
    }
}
