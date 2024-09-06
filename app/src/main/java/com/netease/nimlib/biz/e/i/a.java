package com.netease.nimlib.biz.e.i;

import com.netease.nimlib.push.packet.c.f;

/* compiled from: KickOutNotify.java */
@com.netease.nimlib.biz.e.b(a = 2, b = {"5"})
/* loaded from: classes.dex */
public class a extends com.netease.nimlib.biz.e.a {
    private int c;
    private int d;
    private String e;
    private int f;

    @Override // com.netease.nimlib.biz.e.a
    public f a(f fVar) throws Exception {
        this.c = fVar.f();
        this.d = fVar.f();
        this.e = fVar.e();
        this.f = fVar.f();
        return null;
    }

    public int a() {
        return this.d;
    }

    public int b() {
        return this.c;
    }

    public String c() {
        return this.e;
    }

    public int d() {
        return this.f;
    }
}
