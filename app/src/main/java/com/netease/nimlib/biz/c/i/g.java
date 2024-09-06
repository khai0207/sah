package com.netease.nimlib.biz.c.i;

import com.netease.nimlib.biz.e.j.u;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: MessageSearchResponseHandler.java */
/* loaded from: classes.dex */
public class g extends com.netease.nimlib.biz.c.i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        if (aVar instanceof u) {
            a((u) aVar);
        }
    }

    private void a(u uVar) {
        ArrayList<com.netease.nimlib.push.packet.b.c> a = uVar.a();
        if (a == null) {
            a(uVar, null);
            return;
        }
        ArrayList arrayList = new ArrayList(a.size());
        Iterator<com.netease.nimlib.push.packet.b.c> it = a.iterator();
        while (it.hasNext()) {
            arrayList.add(com.netease.nimlib.session.g.a(it.next(), false, false));
        }
        a(uVar, arrayList);
    }
}
