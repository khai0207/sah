package com.netease.nimlib.push.a.b;

/* compiled from: NegotiateTransportRequest.java */
/* loaded from: classes.dex */
public class e extends com.netease.nimlib.biz.d.a {
    private com.netease.nimlib.push.packet.b.c a;
    private byte[] b;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 1;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 5;
    }

    public e(com.netease.nimlib.push.packet.b.c cVar, byte[] bArr) {
        this.a = cVar;
        this.b = bArr;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.a);
        bVar.a(this.b);
        return bVar;
    }
}
