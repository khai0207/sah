package com.netease.nimlib.biz.d.k;

/* compiled from: MuteTeamRequest.java */
/* loaded from: classes.dex */
public class p extends com.netease.nimlib.biz.d.a {
    private String a;
    private int b;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 8;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 32;
    }

    public p(String str, int i) {
        this.a = str;
        this.b = i;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.b(this.a);
        bVar.a(this.b);
        return bVar;
    }
}
