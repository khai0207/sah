package com.netease.nimlib.biz.e.l;

/* compiled from: UpdateTeamResponse.java */
@com.netease.nimlib.biz.e.b(a = 8, b = {"7"})
/* loaded from: classes.dex */
public class y extends com.netease.nimlib.biz.e.a {
    private String c;
    private long d;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        this.c = fVar.i();
        this.d = fVar.h();
        return null;
    }

    public String a() {
        return this.c;
    }
}
