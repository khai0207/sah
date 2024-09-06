package com.netease.nimlib.biz.d.k;

/* compiled from: UpdateTeamRequest.java */
/* loaded from: classes.dex */
public class w extends com.netease.nimlib.biz.d.a {
    private com.netease.nimlib.push.packet.b.c a;
    private com.netease.nimlib.push.packet.b.c b;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 8;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 7;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.a);
        com.netease.nimlib.push.packet.b.c cVar = this.b;
        if (cVar != null) {
            bVar.a(cVar);
        }
        com.netease.nimlib.log.b.J("************ update team request begin ****************");
        com.netease.nimlib.log.b.a(b(), c(), "tinfo", this.a);
        com.netease.nimlib.log.b.a(b(), c(), "antispamTag", this.b);
        com.netease.nimlib.log.b.J("************ update team request end ****************");
        return bVar;
    }

    public com.netease.nimlib.push.packet.b.c d() {
        return this.a;
    }

    public void a(com.netease.nimlib.push.packet.b.c cVar) {
        this.a = cVar;
    }

    public void b(com.netease.nimlib.push.packet.b.c cVar) {
        this.b = cVar;
    }
}
