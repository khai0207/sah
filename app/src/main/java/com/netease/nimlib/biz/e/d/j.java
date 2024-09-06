package com.netease.nimlib.biz.e.d;

import com.netease.nimlib.biz.d.d.r;

/* compiled from: IMDetectNotify.java */
@com.netease.nimlib.biz.e.b(a = 6, b = {"31"})
/* loaded from: classes.dex */
public class j extends com.netease.nimlib.biz.e.a {
    private com.netease.nimlib.k.b.a c;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        long currentTimeMillis = System.currentTimeMillis();
        com.netease.nimlib.push.packet.b.c a = com.netease.nimlib.push.packet.c.d.a(fVar);
        com.netease.nimlib.push.packet.a j = j();
        if (j != null) {
            com.netease.nimlib.log.b.J("************ IMDetectNotify begin ****************");
            com.netease.nimlib.log.b.a(j.i(), j.j(), "code = " + ((int) r()));
            com.netease.nimlib.log.b.a(j.i(), j.j(), "property", a);
            com.netease.nimlib.log.b.J("************ IMDetectNotify end ****************");
        }
        com.netease.nimlib.k.b.a a2 = com.netease.nimlib.k.b.a.a(a);
        this.c = a2;
        a2.a(Long.valueOf(currentTimeMillis));
        com.netease.nimlib.push.f.l().a(new com.netease.nimlib.ipc.a.d(new r(this.c)));
        return null;
    }
}
