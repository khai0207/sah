package com.netease.nimlib.biz.d.d;

/* compiled from: GetNosSecurityTokenRequest.java */
/* loaded from: classes.dex */
public class i extends com.netease.nimlib.biz.d.a {
    private String a;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 6;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 24;
    }

    public i(String str) {
        this.a = str;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(1, this.a);
        bVar.a(cVar);
        return bVar;
    }
}
