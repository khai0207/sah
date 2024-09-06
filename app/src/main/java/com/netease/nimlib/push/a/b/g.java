package com.netease.nimlib.push.a.b;

/* compiled from: SyncRequest.java */
/* loaded from: classes.dex */
public class g extends com.netease.nimlib.biz.d.a {
    private com.netease.nimlib.push.packet.b.c a;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 5;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 1;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b a = new com.netease.nimlib.push.packet.c.b().a(this.a);
        com.netease.nimlib.log.b.J("************ SyncRequest begin ****************");
        com.netease.nimlib.log.b.a(b(), c(), "Sync tags = ", this.a);
        com.netease.nimlib.log.b.J("************ SyncRequest end ****************");
        return a;
    }

    public void a(com.netease.nimlib.push.packet.b.c cVar) {
        this.a = cVar;
    }
}
