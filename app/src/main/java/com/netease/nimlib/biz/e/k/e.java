package com.netease.nimlib.biz.e.k;

/* compiled from: AddQuickCommentNotify.java */
@com.netease.nimlib.biz.e.b(a = 23, b = {"5", "103"})
/* loaded from: classes.dex */
public class e extends com.netease.nimlib.biz.e.a {
    private com.netease.nimlib.push.packet.b.c c;
    private com.netease.nimlib.push.packet.b.c d;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        this.c = com.netease.nimlib.push.packet.c.d.a(fVar);
        this.d = com.netease.nimlib.push.packet.c.d.a(fVar);
        return null;
    }

    public com.netease.nimlib.push.packet.b.c a() {
        return this.c;
    }

    public com.netease.nimlib.push.packet.b.c b() {
        return this.d;
    }
}
