package com.netease.nimlib.biz.d.l;

/* compiled from: UpdateAppStatusRequest.java */
/* loaded from: classes.dex */
public class e extends com.netease.nimlib.biz.d.a {
    private boolean a;
    private int b = 0;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 3;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 2;
    }

    public e(boolean z) {
        this.a = z;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.a);
        bVar.a(this.b);
        return bVar;
    }
}
