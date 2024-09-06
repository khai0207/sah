package com.netease.nimlib.biz.d.h;

import u.aly.df;

/* compiled from: UpdateUserStateRequest.java */
/* loaded from: classes.dex */
public class d extends com.netease.nimlib.biz.d.a {
    private int a;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 3;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return df.l;
    }

    public d(int i) {
        this.a = i;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.a);
        return bVar;
    }
}
