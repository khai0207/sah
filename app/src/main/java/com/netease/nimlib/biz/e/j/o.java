package com.netease.nimlib.biz.e.j;

import u.aly.cs;

/* compiled from: MessageReceiptNotify.java */
@com.netease.nimlib.biz.e.b(a = cs.b.g, b = {"12"})
/* loaded from: classes.dex */
public class o extends com.netease.nimlib.biz.e.a {
    private String c;
    private String d;
    private long e;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        com.netease.nimlib.push.packet.b.c a = com.netease.nimlib.push.packet.c.d.a(fVar);
        this.c = a.c(2);
        this.d = a.c(11);
        this.e = a.e(7);
        com.netease.nimlib.push.packet.a j = j();
        if (j == null) {
            return null;
        }
        com.netease.nimlib.log.b.J("************ MessageReceiptNotify begin ****************");
        com.netease.nimlib.log.b.a(j.i(), j.j(), "code = " + ((int) r()));
        com.netease.nimlib.log.b.a(j.i(), j.j(), "msg", a);
        com.netease.nimlib.log.b.J("************ MessageReceiptNotify end ****************");
        return null;
    }

    public String a() {
        return this.c;
    }

    public String b() {
        return this.d;
    }

    public long c() {
        return this.e;
    }
}
