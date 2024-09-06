package com.netease.nimlib.push.a.c;

import java.util.ArrayList;

/* compiled from: LoginResponse.java */
@com.netease.nimlib.biz.e.b(a = 2, b = {"2"})
/* loaded from: classes.dex */
public class b extends com.netease.nimlib.biz.e.a {
    private com.netease.nimlib.push.packet.b.c c;
    private ArrayList<com.netease.nimlib.biz.f> d;
    private com.netease.nimlib.push.d e;
    private com.netease.nimlib.push.packet.c.f f;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        this.f = fVar;
        this.c = com.netease.nimlib.push.packet.c.d.a(fVar);
        com.netease.nimlib.push.packet.a j = j();
        if (j != null) {
            com.netease.nimlib.log.b.J("************ LoginResponse begin ****************");
            com.netease.nimlib.log.b.a(j.i(), j.j(), "code = " + ((int) r()));
            com.netease.nimlib.log.b.a(j.i(), j.j(), "loginRep", this.c);
        }
        this.e = com.netease.nimlib.push.d.a(this.c);
        if (fVar.a() > 0) {
            int g = fVar.g();
            ArrayList arrayList = new ArrayList();
            if (g > 0) {
                this.d = new ArrayList<>(g);
                for (int i = 0; i < g; i++) {
                    com.netease.nimlib.push.packet.b.c a = com.netease.nimlib.push.packet.c.d.a(fVar);
                    arrayList.add(a);
                    this.d.add(com.netease.nimlib.biz.f.a(a));
                }
                if (j != null) {
                    com.netease.nimlib.log.b.a(j.i(), j.j(), "clients", arrayList);
                }
            }
            if (fVar.a() > 0) {
                com.netease.nimlib.push.packet.b.c a2 = com.netease.nimlib.push.packet.c.d.a(fVar);
                if (j != null) {
                    com.netease.nimlib.log.b.a(j.i(), j.j(), "push", a2);
                }
                this.e.b(a2.d(110));
                this.e.a(a2.d(111));
            }
        }
        if (j == null) {
            return null;
        }
        com.netease.nimlib.log.b.J("************ LoginResponse end ****************");
        return null;
    }

    public com.netease.nimlib.push.packet.c.f a() {
        return this.f;
    }

    public com.netease.nimlib.push.packet.b.c b() {
        return this.c;
    }

    public ArrayList<com.netease.nimlib.biz.f> c() {
        return this.d;
    }

    public com.netease.nimlib.push.d d() {
        return this.e;
    }
}
