package com.netease.nimlib.biz.d.k;

/* compiled from: TransferTeamRequest.java */
/* loaded from: classes.dex */
public class t extends com.netease.nimlib.biz.d.a {
    private String a;
    private String b;
    private boolean c;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 8;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 18;
    }

    public t(String str, String str2, boolean z) {
        this.a = str;
        this.b = str2;
        this.c = z;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.b(this.a);
        bVar.a(this.b);
        bVar.a(this.c);
        return bVar;
    }
}
