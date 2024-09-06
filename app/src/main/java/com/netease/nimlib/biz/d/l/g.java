package com.netease.nimlib.biz.d.l;

import u.aly.df;

/* compiled from: UpdateDonopConfigRequest.java */
/* loaded from: classes.dex */
public class g extends com.netease.nimlib.biz.d.a {
    com.netease.nimlib.push.packet.b.c a;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 3;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return df.m;
    }

    public g(com.netease.nimlib.push.packet.b.c cVar) {
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
