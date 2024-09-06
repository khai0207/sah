package com.netease.nimlib.push.a.c;

import java.util.ArrayList;

/* compiled from: LoginStatusNotify.java */
@com.netease.nimlib.biz.e.b(a = 2, b = {"7"})
/* loaded from: classes.dex */
public class c extends com.netease.nimlib.biz.e.a {
    private byte c;
    private ArrayList<com.netease.nimlib.biz.f> d;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        this.c = fVar.c();
        int g = fVar.g();
        this.d = new ArrayList<>(g);
        for (int i = 0; i < g; i++) {
            this.d.add(com.netease.nimlib.biz.f.a(com.netease.nimlib.push.packet.c.d.a(fVar)));
        }
        return null;
    }

    public byte a() {
        return this.c;
    }

    public ArrayList<com.netease.nimlib.biz.f> b() {
        return this.d;
    }
}
