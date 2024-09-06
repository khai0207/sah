package com.netease.nimlib.biz.d.b;

/* compiled from: AddFriendRequest.java */
/* loaded from: classes.dex */
public class a extends com.netease.nimlib.biz.d.a {
    private String a;
    private byte b;
    private String c;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 12;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 1;
    }

    public a(String str, byte b, String str2) {
        this.a = str;
        this.b = b;
        this.c = str2;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.a);
        bVar.a(this.b);
        bVar.a(this.c);
        return bVar;
    }

    public String d() {
        return this.a;
    }

    public byte e() {
        return this.b;
    }
}
