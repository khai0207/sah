package com.netease.nimlib.push.a.b;

/* compiled from: LoginRequest.java */
/* loaded from: classes.dex */
public class c extends com.netease.nimlib.biz.d.a {
    private com.netease.nimlib.push.packet.b.c a;
    private final boolean b;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 2;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 2;
    }

    public c(boolean z) {
        this.b = z;
    }

    public boolean d() {
        return this.b;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        com.netease.nimlib.log.b.J("************ LoginRequest begin ****************");
        com.netease.nimlib.log.b.a(b(), c(), "property", this.a);
        com.netease.nimlib.log.b.a(b(), c(), "LoginRequest authType = " + this.a.c(115));
        com.netease.nimlib.log.b.J("************ LoginRequest end ****************");
        bVar.a(this.a);
        return bVar;
    }

    public void a(com.netease.nimlib.push.packet.b.c cVar) {
        this.a = cVar;
    }
}
