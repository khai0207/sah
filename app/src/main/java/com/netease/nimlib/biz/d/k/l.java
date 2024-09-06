package com.netease.nimlib.biz.d.k;

import u.aly.df;

/* compiled from: JoinApplyRequest.java */
/* loaded from: classes.dex */
public class l extends com.netease.nimlib.biz.d.a {
    private String a;
    private String b;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 8;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return df.k;
    }

    public l(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.b(this.a);
        bVar.a(this.b);
        return bVar;
    }
}
