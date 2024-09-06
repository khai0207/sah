package com.netease.nimlib.biz.d.k;

/* compiled from: ProcessInviteRequest.java */
/* loaded from: classes.dex */
public class r extends com.netease.nimlib.biz.d.a {
    protected boolean a;
    private String b;
    private String c;
    private String d;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 8;
    }

    public r(String str, String str2, String str3, boolean z) {
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.a = z;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.b(this.b);
        bVar.a(this.c);
        if (!this.a) {
            bVar.a(this.d);
        }
        return bVar;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return this.a ? (byte) 21 : (byte) 22;
    }
}
