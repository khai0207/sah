package com.netease.nimlib.biz.d.j;

/* compiled from: AddCollectRequest.java */
/* loaded from: classes.dex */
public class a extends com.netease.nimlib.biz.d.a {
    private int a;
    private String b;
    private String c;
    private String d;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 23;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 8;
    }

    public a(int i, String str, String str2, String str3) {
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = str3;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(2, this.a);
        cVar.a(3, this.b);
        cVar.a(4, this.c);
        cVar.a(5, this.d);
        bVar.a(cVar);
        return bVar;
    }
}
