package com.netease.nimlib.biz.d.d;

/* compiled from: GetOriginalUrlRequest.java */
/* loaded from: classes.dex */
public class k extends com.netease.nimlib.biz.d.a {
    private String a;
    private byte[] b;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 6;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 22;
    }

    public k(String str) {
        this.a = str;
    }

    public k(byte[] bArr) {
        this.b = bArr;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        byte[] bArr = this.b;
        if (bArr == null) {
            com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
            cVar.a(0, this.a);
            bVar.a(cVar);
        } else {
            bVar.a(bArr);
        }
        return bVar;
    }
}
