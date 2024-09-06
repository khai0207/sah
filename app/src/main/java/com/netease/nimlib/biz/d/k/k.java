package com.netease.nimlib.biz.d.k;

import java.util.Collection;
import java.util.List;

/* compiled from: InviteMemberRequest.java */
/* loaded from: classes.dex */
public class k extends com.netease.nimlib.biz.d.a {
    private String a;
    private List<String> b;
    private String c;
    private String d;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 8;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 5;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.b(this.a);
        com.netease.nimlib.push.packet.c.d.a(bVar, (Collection<?>) this.b);
        bVar.a(this.c);
        bVar.a(this.d);
        return bVar;
    }

    public String d() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public void a(List<String> list) {
        this.b = list;
    }

    public void b(String str) {
        this.c = str;
    }

    public void c(String str) {
        this.d = str;
    }
}
