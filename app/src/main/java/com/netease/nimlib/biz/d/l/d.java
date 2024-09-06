package com.netease.nimlib.biz.d.l;

/* compiled from: MarkMuteListRequest.java */
/* loaded from: classes.dex */
public class d extends com.netease.nimlib.biz.d.a {
    private boolean a;
    private String b;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 3;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 5;
    }

    public d(boolean z, String str) {
        this.a = z;
        this.b = str;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.b);
        bVar.a(this.a);
        return bVar;
    }

    public boolean d() {
        return this.a;
    }

    public String e() {
        return this.b;
    }
}
