package com.netease.nimlib.push.a.b;

import u.aly.df;

/* compiled from: RoomTokenRequest.java */
/* loaded from: classes.dex */
public class f extends com.netease.nimlib.biz.d.a {
    private String a;
    private boolean b = false;
    private int c = com.netease.nimlib.push.e.f().getValue();

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return df.k;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 1;
    }

    public f(String str) {
        this.a = str;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.b(this.a);
        bVar.a(this.b);
        bVar.a(this.c);
        return bVar;
    }
}
