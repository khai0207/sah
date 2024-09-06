package com.netease.nimlib.biz.c.c;

import com.netease.nimlib.biz.d.d.j;
import java.util.ArrayList;

/* compiled from: GetNosTokenResponseHandler.java */
/* loaded from: classes.dex */
public class b extends com.netease.nimlib.biz.c.i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        ArrayList arrayList = new ArrayList();
        String d = ((j) b(aVar)).d();
        if (aVar.n()) {
            com.netease.nimlib.biz.e.d.g gVar = (com.netease.nimlib.biz.e.d.g) aVar;
            String d2 = com.netease.nimlib.c.d(gVar.j().o());
            for (com.netease.nimlib.push.packet.b.c cVar : gVar.a()) {
                com.netease.nimlib.net.a.b.c.d dVar = new com.netease.nimlib.net.a.b.c.d();
                dVar.c(cVar.c(3));
                dVar.b(cVar.c(2));
                dVar.d(cVar.c(1));
                dVar.a(cVar.d(4));
                dVar.a(cVar.e(7));
                dVar.a(cVar.c(8));
                dVar.e(cVar.c(9));
                dVar.f(d2);
                arrayList.add(dVar);
            }
        }
        com.netease.nimlib.net.a.b.a.a().a(d, arrayList);
    }
}
