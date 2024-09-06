package com.netease.nimlib.biz.d.i;

/* compiled from: SendMessageTask.java */
/* loaded from: classes.dex */
public class r extends com.netease.nimlib.biz.g.b {
    private com.netease.nimlib.push.packet.b.c a;

    @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
    public void a(com.netease.nimlib.biz.e.a aVar) {
    }

    public r(s sVar, com.netease.nimlib.biz.g.a aVar) {
        super(sVar, aVar);
        this.a = sVar.d();
    }

    @Override // com.netease.nimlib.biz.g.c
    public boolean a() {
        this.a.a(13, 1);
        return super.a();
    }
}
