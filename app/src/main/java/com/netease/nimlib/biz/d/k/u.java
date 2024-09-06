package com.netease.nimlib.biz.d.k;

/* compiled from: UpdateMemberInfoRequest.java */
/* loaded from: classes.dex */
public class u extends com.netease.nimlib.biz.d.a {
    private final com.netease.nimlib.push.packet.b.c a;
    private final com.netease.nimlib.push.packet.b.c b;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 8;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 19;
    }

    public u(com.netease.nimlib.push.packet.b.c cVar, com.netease.nimlib.push.packet.b.c cVar2) {
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
        com.netease.nimlib.log.b.J("************ UpdateMemberInfoRequest begin ****************");
        com.netease.nimlib.log.b.a(b(), c(), "memberInfo", this.a);
        com.netease.nimlib.log.b.a(b(), c(), "followAccountIdsUpdateInfo", this.b);
        com.netease.nimlib.log.b.J("************ UpdateMemberInfoRequest end ****************");
        return bVar;
    }
}
