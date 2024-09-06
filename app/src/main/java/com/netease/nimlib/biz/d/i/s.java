package com.netease.nimlib.biz.d.i;

/* compiled from: TalkRequest.java */
/* loaded from: classes.dex */
public class s extends com.netease.nimlib.biz.d.a {
    private com.netease.nimlib.push.packet.b.c a;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 7;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 1;
    }

    public s() {
        a(true);
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        return new com.netease.nimlib.push.packet.c.b().a(this.a);
    }

    public com.netease.nimlib.push.packet.b.c d() {
        return this.a;
    }

    public void a(com.netease.nimlib.push.packet.b.c cVar) {
        this.a = cVar;
    }
}
