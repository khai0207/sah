package com.netease.nimlib.biz.d.k;

import java.util.Collection;
import java.util.List;

/* compiled from: GetMemberInvitorRequest.java */
/* loaded from: classes.dex */
public class g extends com.netease.nimlib.biz.d.a {
    private String a;
    private List<String> b;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 8;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 33;
    }

    public g(String str, List<String> list) {
        this.a = str;
        this.b = list;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.b(this.a);
        com.netease.nimlib.push.packet.c.d.a(bVar, (Collection<?>) this.b);
        return bVar;
    }

    public String d() {
        return this.a;
    }
}
