package com.netease.nimlib.biz.d.k;

/* compiled from: DismissTeamRequest.java */
/* loaded from: classes.dex */
public class d extends com.netease.nimlib.biz.d.a {
    private String a;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 8;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 12;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.b(this.a);
        return bVar;
    }

    public void a(String str) {
        this.a = str;
    }
}
