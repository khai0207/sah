package com.netease.nimlib.biz.c.k;

import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: TeamMessageSearchResponseHandler.java */
/* loaded from: classes.dex */
public class k extends com.netease.nimlib.biz.c.i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        if (aVar instanceof com.netease.nimlib.biz.e.l.l) {
            a((com.netease.nimlib.biz.e.l.l) aVar);
        }
    }

    private void a(com.netease.nimlib.biz.e.l.l lVar) {
        ArrayList<com.netease.nimlib.push.packet.b.c> a = lVar.a();
        if (a == null || a.isEmpty()) {
            a(lVar, new ArrayList());
            return;
        }
        ArrayList arrayList = new ArrayList(a.size());
        Iterator<com.netease.nimlib.push.packet.b.c> it = a.iterator();
        while (it.hasNext()) {
            arrayList.add(com.netease.nimlib.session.g.a(it.next(), false, false));
        }
        a(lVar, arrayList);
    }
}
