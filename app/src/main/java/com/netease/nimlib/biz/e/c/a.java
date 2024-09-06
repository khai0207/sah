package com.netease.nimlib.biz.e.c;

import com.netease.nimlib.biz.e.b;
import com.netease.nimlib.push.packet.b.c;
import com.netease.nimlib.push.packet.c.f;
import java.util.ArrayList;
import java.util.List;
import u.aly.cs;

/* compiled from: AckSessionBatchRoutePassThroughResponse.java */
@b(a = cs.b.g, b = {"29"})
/* loaded from: classes.dex */
public class a extends com.netease.nimlib.biz.e.a {
    List<com.netease.nimlib.g.a.a> c;

    @Override // com.netease.nimlib.biz.e.a
    public f a(f fVar) throws Exception {
        int g = fVar.g();
        this.c = new ArrayList(g);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < g; i++) {
            c cVar = new c();
            fVar.a(cVar);
            arrayList.add(cVar);
            this.c.add(com.netease.nimlib.g.a.a.a(cVar));
        }
        com.netease.nimlib.push.packet.a j = j();
        if (j == null) {
            return null;
        }
        com.netease.nimlib.log.b.J("************ AckSessionBatchRoutePassThroughResponse begin ****************");
        com.netease.nimlib.log.b.a(j.i(), j.j(), "code = " + ((int) r()));
        com.netease.nimlib.log.b.a(j.i(), j.j(), "propertyList", arrayList);
        com.netease.nimlib.log.b.J("************ AckSessionBatchRoutePassThroughResponse end ****************");
        return null;
    }

    public List<com.netease.nimlib.g.a.a> a() {
        return this.c;
    }
}
