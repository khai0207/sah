package com.netease.nimlib.biz.e.l;

import java.util.ArrayList;
import java.util.List;

/* compiled from: SyncMyTeamMemberResponse.java */
@com.netease.nimlib.biz.e.b(a = 8, b = {"126"})
/* loaded from: classes.dex */
public class m extends com.netease.nimlib.biz.e.a {
    private long c;
    private List<com.netease.nimlib.push.packet.b.c> d;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        int g = fVar.g();
        this.d = new ArrayList(g);
        for (int i = 0; i < g; i++) {
            this.d.add(com.netease.nimlib.push.packet.c.d.a(fVar));
        }
        this.c = fVar.h();
        com.netease.nimlib.push.packet.a j = j();
        if (j == null) {
            return null;
        }
        com.netease.nimlib.log.b.J("************ SyncMyTeamMemberResponse begin ****************");
        com.netease.nimlib.log.b.a(j.i(), j.j(), "code = " + ((int) r()));
        com.netease.nimlib.log.b.a(j.i(), j.j(), "members", this.d);
        com.netease.nimlib.log.b.a(j.i(), j.j(), "timetag = " + this.c);
        com.netease.nimlib.log.b.J("************ SyncMyTeamMemberResponse end ****************");
        return null;
    }

    public List<com.netease.nimlib.push.packet.b.c> a() {
        return this.d;
    }

    public long b() {
        return this.c;
    }
}
