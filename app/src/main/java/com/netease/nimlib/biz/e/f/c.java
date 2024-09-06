package com.netease.nimlib.biz.e.f;

/* compiled from: SyncDndConfigResponse.java */
@com.netease.nimlib.biz.e.b(a = 4, b = {"7"})
/* loaded from: classes.dex */
public class c extends com.netease.nimlib.biz.e.a {
    private com.netease.nimlib.biz.e c;
    private long d;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        this.c = new com.netease.nimlib.biz.e();
        com.netease.nimlib.push.packet.b.c a = com.netease.nimlib.push.packet.c.d.a(fVar);
        this.c.b(a.d(1) == 2);
        this.c.setOpen(a.d(2) == 1);
        this.c.a(a.d(3));
        this.c.b(a.d(4));
        this.c.c(a.d(5));
        this.c.d(a.d(6));
        this.d = fVar.h();
        return null;
    }

    public com.netease.nimlib.biz.e a() {
        return this.c;
    }

    public long b() {
        return this.d;
    }
}
