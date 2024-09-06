package com.netease.nimlib.biz.d.d;

/* compiled from: FileQuickTransferRequest.java */
/* loaded from: classes.dex */
public class b extends com.netease.nimlib.biz.d.a {
    private String a;
    private com.netease.nimlib.push.packet.b.c b;
    private byte[] c;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 6;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 18;
    }

    public b(String str, long j) {
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        this.b = cVar;
        cVar.a(1, str);
        if (j > 0) {
            this.b.a(3, j);
        }
        this.a = str;
    }

    public b(byte[] bArr) {
        this.c = bArr;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        if (this.c == null) {
            return new com.netease.nimlib.push.packet.c.b().a(this.b);
        }
        return new com.netease.nimlib.push.packet.c.b().a(this.c);
    }

    public String d() {
        return this.a;
    }
}
