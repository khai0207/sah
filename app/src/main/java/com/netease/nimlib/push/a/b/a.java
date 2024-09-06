package com.netease.nimlib.push.a.b;

/* compiled from: HandshakeRequest.java */
/* loaded from: classes.dex */
public class a extends com.netease.nimlib.biz.d.a {
    private int a;
    private byte[] b;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 1;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 1;
    }

    public a(int i, byte[] bArr) {
        this.a = i;
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
