package com.netease.nimlib.biz.d.k;

/* compiled from: MuteMemberRequest.java */
/* loaded from: classes.dex */
public class o extends com.netease.nimlib.biz.d.a {
    private String a;
    private String b;
    private boolean c;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 8;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 25;
    }

    public o(String str, String str2, boolean z) {
        this.a = str;
        this.b = str2;
        this.c = z;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.b(this.a);
        bVar.a(this.b);
        bVar.a(this.c ? 1 : 0);
        return bVar;
    }

    public String d() {
        return this.a;
    }

    public String e() {
        return this.b;
    }

    public boolean f() {
        return this.c;
    }
}
