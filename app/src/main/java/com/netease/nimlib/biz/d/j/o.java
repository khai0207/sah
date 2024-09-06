package com.netease.nimlib.biz.d.j;

/* compiled from: UpdateCollectRequest.java */
/* loaded from: classes.dex */
public class o extends com.netease.nimlib.biz.d.a {
    private long a;
    private long b;
    private String c;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 23;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 10;
    }

    public o(long j, long j2, String str) {
        this.a = j;
        this.b = j2;
        this.c = str;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(1, this.a);
        cVar.a(6, this.b);
        cVar.a(4, this.c);
        bVar.a(cVar);
        return bVar;
    }
}
