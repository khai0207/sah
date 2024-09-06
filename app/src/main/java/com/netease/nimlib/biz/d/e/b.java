package com.netease.nimlib.biz.d.e;

/* compiled from: MarkReadRequest.java */
/* loaded from: classes.dex */
public class b extends com.netease.nimlib.biz.d.a {
    private long a;
    private com.netease.nimlib.push.packet.a b;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 4;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 3;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.a);
        bVar.a(this.b);
        return bVar;
    }

    public void a(long j) {
        this.a = j;
    }

    public void a(com.netease.nimlib.push.packet.a aVar) {
        this.b = aVar;
    }
}
