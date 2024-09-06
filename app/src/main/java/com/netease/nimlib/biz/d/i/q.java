package com.netease.nimlib.biz.d.i;

/* compiled from: SendMessageReceiptRequest.java */
/* loaded from: classes.dex */
public class q extends com.netease.nimlib.biz.d.a {
    private String a;
    private String b;
    private long c;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 7;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 11;
    }

    public q(String str, String str2, long j) {
        this.a = str;
        this.b = str2;
        this.c = j;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(1, this.a);
        cVar.a(11, this.b);
        return new com.netease.nimlib.push.packet.c.b().a(cVar);
    }

    public String d() {
        return this.a;
    }

    public long e() {
        return this.c;
    }
}
