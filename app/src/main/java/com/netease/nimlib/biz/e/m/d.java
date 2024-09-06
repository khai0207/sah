package com.netease.nimlib.biz.e.m;

/* compiled from: SyncMarkMuteListResponse.java */
@com.netease.nimlib.biz.e.b(a = 3, b = {"105"})
/* loaded from: classes.dex */
public class d extends com.netease.nimlib.biz.e.a {
    private String c;
    private boolean d;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        this.c = fVar.e();
        this.d = fVar.k();
        return null;
    }

    public String a() {
        return this.c;
    }

    public boolean b() {
        return this.d;
    }
}
