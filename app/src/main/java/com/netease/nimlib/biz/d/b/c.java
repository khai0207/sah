package com.netease.nimlib.biz.d.b;

/* compiled from: UpdateFriendRequest.java */
/* loaded from: classes.dex */
public class c extends com.netease.nimlib.biz.d.a {
    private com.netease.nimlib.push.packet.b.c a;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 12;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 3;
    }

    public c(com.netease.nimlib.push.packet.b.c cVar) {
        this.a = cVar;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.a);
        return bVar;
    }

    public com.netease.nimlib.push.packet.b.c d() {
        return this.a;
    }
}
