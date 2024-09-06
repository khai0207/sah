package com.netease.nimlib.biz.e.l;

import java.util.ArrayList;
import java.util.List;

/* compiled from: GetMemberListResponse.java */
@com.netease.nimlib.biz.e.b(a = 8, b = {"11"})
/* loaded from: classes.dex */
public class d extends com.netease.nimlib.biz.e.a {
    private String c;
    private List<com.netease.nimlib.push.packet.b.c> d;
    private long e;
    private boolean f = false;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        this.c = fVar.i();
        int g = fVar.g();
        this.d = new ArrayList(g);
        for (int i = 0; i < g; i++) {
            this.d.add(com.netease.nimlib.push.packet.c.d.a(fVar));
        }
        this.e = fVar.h();
        this.f = fVar.k();
        com.netease.nimlib.push.packet.a j = j();
        if (j == null) {
            return null;
        }
        com.netease.nimlib.log.b.J("************ GetMemberListResponse begin ****************");
        com.netease.nimlib.log.b.a(j.i(), j.j(), "code = " + ((int) r()));
        com.netease.nimlib.log.b.a(j.i(), j.j(), "tid = " + this.c);
        com.netease.nimlib.log.b.a(j.i(), j.j(), "members", this.d);
        com.netease.nimlib.log.b.a(j.i(), j.j(), "refresh = " + this.f);
        com.netease.nimlib.log.b.a(j.i(), j.j(), "timeTag = " + this.e);
        com.netease.nimlib.log.b.J("************ GetMemberListResponse end ****************");
        return null;
    }

    public String a() {
        return this.c;
    }

    public List<com.netease.nimlib.push.packet.b.c> b() {
        return this.d;
    }

    public long c() {
        return this.e;
    }

    public boolean d() {
        return this.f;
    }
}
