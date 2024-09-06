package com.netease.nimlib.biz.e.d;

import java.util.ArrayList;
import java.util.List;

/* compiled from: GetNosTokenResponse.java */
@com.netease.nimlib.biz.e.b(a = 6, b = {"1"})
/* loaded from: classes.dex */
public class g extends com.netease.nimlib.biz.e.a {
    private List<com.netease.nimlib.push.packet.b.c> c;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        this.c = new ArrayList();
        int g = fVar.g();
        for (int i = 0; i < g; i++) {
            this.c.add(com.netease.nimlib.push.packet.c.d.a(fVar));
        }
        com.netease.nimlib.push.packet.a j = j();
        if (j == null) {
            return null;
        }
        com.netease.nimlib.log.b.J("************ GetNosTokenResponse begin ****************");
        com.netease.nimlib.log.b.a(j);
        com.netease.nimlib.log.b.a(j.i(), j.j(), "code = " + ((int) r()));
        com.netease.nimlib.log.b.a(j.i(), j.j(), "tokens", this.c);
        com.netease.nimlib.log.b.J("************ GetNosTokenResponse end ****************");
        return null;
    }

    public List<com.netease.nimlib.push.packet.b.c> a() {
        return this.c;
    }
}
