package com.netease.nimlib.biz.e.j;

import android.text.TextUtils;
import u.aly.cs;

/* compiled from: GetMySessionResponse.java */
@com.netease.nimlib.biz.e.b(a = cs.b.g, b = {"20"})
/* loaded from: classes.dex */
public class l extends com.netease.nimlib.biz.e.a {
    private String c;
    private long d;
    private String e;
    private String f;
    private int g;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        fVar.a(cVar);
        this.c = cVar.c(1);
        String c = cVar.c(2);
        this.d = TextUtils.isEmpty(c) ? 0L : Long.parseLong(c);
        this.e = cVar.c(3);
        this.f = cVar.c(4);
        this.g = cVar.d(5);
        return null;
    }

    public String a() {
        return this.c;
    }

    public long b() {
        return this.d;
    }

    public String c() {
        return this.e;
    }

    public String d() {
        return this.f;
    }

    public int e() {
        return this.g;
    }
}
