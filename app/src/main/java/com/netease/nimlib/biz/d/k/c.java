package com.netease.nimlib.biz.d.k;

import java.util.Collection;
import java.util.List;

/* compiled from: CreateTeamRequest.java */
/* loaded from: classes.dex */
public class c extends com.netease.nimlib.biz.d.a {
    private com.netease.nimlib.push.packet.b.c a;
    private List<String> b;
    private String c;
    private com.netease.nimlib.push.packet.b.c d;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 8;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 1;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(this.a);
        com.netease.nimlib.push.packet.c.d.a(bVar, (Collection<?>) this.b);
        bVar.a(this.c);
        com.netease.nimlib.push.packet.b.c cVar = this.d;
        if (cVar != null) {
            bVar.a(cVar);
        }
        com.netease.nimlib.log.b.J("************ create team request begin ****************");
        com.netease.nimlib.log.b.a(b(), c(), "tinfo", this.a);
        com.netease.nimlib.log.b.a(b(), c(), "members = " + this.b);
        com.netease.nimlib.log.b.a(b(), c(), "postscript = " + this.c);
        com.netease.nimlib.log.b.a(b(), c(), "antispamTag", this.d);
        com.netease.nimlib.log.b.J("************ create team request end ******************");
        return bVar;
    }

    public void a(com.netease.nimlib.push.packet.b.c cVar) {
        this.a = cVar;
    }

    public List<String> d() {
        return this.b;
    }

    public void a(List<String> list) {
        this.b = list;
    }

    public void a(String str) {
        this.c = str;
    }

    public void b(com.netease.nimlib.push.packet.b.c cVar) {
        this.d = cVar;
    }
}
