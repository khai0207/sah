package com.netease.nimlib.biz.d.b;

/* compiled from: DeleteFriendRequest.java */
/* loaded from: classes.dex */
public class b extends com.netease.nimlib.biz.d.a {
    private String a;
    private boolean b;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 12;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 2;
    }

    public b(String str, boolean z) {
        this.a = str;
        this.b = z;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.a);
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(1, this.b ? 1 : 0);
        bVar.a(cVar);
        return bVar;
    }

    public String d() {
        return this.a;
    }

    public boolean e() {
        return this.b;
    }
}
