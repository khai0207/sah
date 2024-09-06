package com.netease.nimlib.biz.d;

import com.netease.nimlib.push.packet.b.c;

/* compiled from: UpdateMySessionRequest.java */
/* loaded from: classes.dex */
public class b extends a {
    private String a;
    private String b;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 7;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 21;
    }

    public b(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        c cVar = new c();
        cVar.a(1, this.a);
        cVar.a(3, this.b);
        return new com.netease.nimlib.push.packet.c.b().a(cVar);
    }
}
