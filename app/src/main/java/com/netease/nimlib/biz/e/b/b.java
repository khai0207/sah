package com.netease.nimlib.biz.e.b;

import com.netease.nimlib.push.packet.c.f;

/* compiled from: SyncAddFriendResponse.java */
@com.netease.nimlib.biz.e.b(a = 12, b = {"101"})
/* loaded from: classes.dex */
public class b extends com.netease.nimlib.biz.e.a {
    private String c;
    private byte d;
    private String e;
    private com.netease.nimlib.push.packet.b.c f;

    @Override // com.netease.nimlib.biz.e.a
    public f a(f fVar) throws Exception {
        this.c = fVar.e();
        this.d = fVar.c();
        this.e = fVar.e();
        this.f = com.netease.nimlib.push.packet.c.d.a(fVar);
        return null;
    }

    public String a() {
        return this.c;
    }

    public byte b() {
        return this.d;
    }

    public com.netease.nimlib.push.packet.b.c c() {
        return this.f;
    }
}
