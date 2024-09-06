package com.netease.nimlib.biz.d.i;

/* compiled from: GetMySessionRequest.java */
/* loaded from: classes.dex */
public class j extends com.netease.nimlib.biz.d.a {
    String a;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 7;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 20;
    }

    public j(String str) {
        this.a = str;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(1, this.a);
        return new com.netease.nimlib.push.packet.c.b().a(cVar);
    }
}
