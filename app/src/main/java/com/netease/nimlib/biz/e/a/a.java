package com.netease.nimlib.biz.e.a;

import com.netease.nimlib.sdk.event.model.Event;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import u.aly.df;

/* compiled from: BatchPushEventResponse.java */
@com.netease.nimlib.biz.e.b(a = df.l, b = {"9"})
/* loaded from: classes.dex */
public class a extends com.netease.nimlib.biz.e.a {
    private List<com.netease.nimlib.f.a> c;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        int g;
        ArrayList arrayList = new ArrayList();
        if (fVar.a() > 0 && (g = fVar.g()) > 0) {
            this.c = new ArrayList(g);
            for (int i = 0; i < g; i++) {
                com.netease.nimlib.push.packet.b.c a = com.netease.nimlib.push.packet.c.d.a(fVar);
                arrayList.add(a);
                this.c.add(new com.netease.nimlib.f.a(a));
            }
        }
        com.netease.nimlib.push.packet.a j = j();
        if (j == null) {
            return null;
        }
        com.netease.nimlib.log.b.J("************ BatchPushEventResponse begin ****************");
        com.netease.nimlib.log.b.a(j.i(), j.j(), "code = " + ((int) r()));
        com.netease.nimlib.log.b.a(j.i(), j.j(), "events", arrayList);
        com.netease.nimlib.log.b.J("************ BatchPushEventResponse end ****************");
        return null;
    }

    public ArrayList<Event> a() {
        if (this.c == null) {
            return null;
        }
        ArrayList<Event> arrayList = new ArrayList<>(this.c.size());
        Iterator<com.netease.nimlib.f.a> it = this.c.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }
}
