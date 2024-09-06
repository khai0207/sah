package com.netease.nimlib.biz.e.l;

/* compiled from: JoinApplyResponse.java */
@com.netease.nimlib.biz.e.b(a = 8, b = {"13"})
/* loaded from: classes.dex */
public class j extends com.netease.nimlib.biz.e.a {
    private com.netease.nimlib.push.packet.b.c c;

    @Override // com.netease.nimlib.biz.e.a
    protected boolean m() {
        return true;
    }

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        if (r() != 808 && !n()) {
            return null;
        }
        this.c = com.netease.nimlib.push.packet.c.d.a(fVar);
        return null;
    }

    public com.netease.nimlib.push.packet.b.c a() {
        return this.c;
    }
}
