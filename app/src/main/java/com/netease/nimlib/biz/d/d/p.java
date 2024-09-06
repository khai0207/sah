package com.netease.nimlib.biz.d.d;

/* compiled from: SyncClientAntiSpamRequest.java */
/* loaded from: classes.dex */
public class p extends com.netease.nimlib.biz.d.a {
    private com.netease.nimlib.push.packet.b.c a;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 6;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 17;
    }

    public p(int i, String str, String str2) {
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        this.a = cVar;
        cVar.a(1, i);
        this.a.a(2, str);
        this.a.a(3, str2);
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.a);
        return bVar;
    }
}
