package com.netease.nimlib.biz.d.d;

/* compiled from: GetBackSourceTokenRequest.java */
/* loaded from: classes.dex */
public class d extends com.netease.nimlib.biz.d.a {
    private byte[] a;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 6;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 30;
    }

    public d(byte[] bArr) {
        this.a = bArr;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.a);
        return bVar;
    }
}
