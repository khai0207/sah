package com.netease.nimlib.biz.d.d;

/* compiled from: GetMixStoreAuthorizationRequest.java */
/* loaded from: classes.dex */
public class e extends com.netease.nimlib.biz.d.a {
    private byte[] a;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 6;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 29;
    }

    public e(byte[] bArr) {
        this.a = bArr;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.a);
        return bVar;
    }
}
