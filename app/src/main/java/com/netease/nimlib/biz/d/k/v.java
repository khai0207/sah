package com.netease.nimlib.biz.d.k;

/* compiled from: UpdateOtherMemberInfoRequest.java */
/* loaded from: classes.dex */
public class v extends com.netease.nimlib.biz.d.a {
    private final com.netease.nimlib.push.packet.b.c a;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 8;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 20;
    }

    public v(com.netease.nimlib.push.packet.b.c cVar) {
        this.a = cVar;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.a);
        com.netease.nimlib.log.b.J("************ UpdateOtherMemberInfoRequest begin ****************");
        com.netease.nimlib.log.b.a(b(), c(), "memberInfo", this.a);
        com.netease.nimlib.log.b.J("************ UpdateOtherMemberInfoRequest end ****************");
        return bVar;
    }
}
