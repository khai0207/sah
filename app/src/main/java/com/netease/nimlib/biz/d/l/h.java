package com.netease.nimlib.biz.d.l;

/* compiled from: UpdateUserInfoRequest.java */
/* loaded from: classes.dex */
public class h extends com.netease.nimlib.biz.d.a {
    private com.netease.nimlib.push.packet.b.c a;
    private com.netease.nimlib.push.packet.b.c b;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 3;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 10;
    }

    public h(com.netease.nimlib.push.packet.b.c cVar) {
        this.a = cVar;
    }

    public h(com.netease.nimlib.push.packet.b.c cVar, com.netease.nimlib.push.packet.b.c cVar2) {
        this.a = cVar;
        this.b = cVar2;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.a);
        com.netease.nimlib.push.packet.b.c cVar = this.b;
        if (cVar != null) {
            bVar.a(cVar);
        }
        com.netease.nimlib.log.b.J("************ update user info request begin ****************");
        com.netease.nimlib.log.b.a(b(), c(), "userInfo", this.a);
        com.netease.nimlib.log.b.a(b(), c(), "antispamTag", this.b);
        com.netease.nimlib.log.b.J("************ update user info request end ****************");
        return bVar;
    }

    public com.netease.nimlib.push.packet.b.c d() {
        return this.a;
    }
}
