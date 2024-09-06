package com.netease.nimlib.biz.c.a;

import com.netease.nimlib.biz.c.i;
import com.netease.nimlib.biz.e.a.b;
import com.netease.nimlib.biz.e.a.c;
import com.netease.nimlib.biz.e.a.d;
import com.netease.nimlib.biz.e.a.e;
import com.netease.nimlib.biz.e.a.f;
import com.netease.nimlib.sdk.event.model.Event;
import java.util.ArrayList;

/* compiled from: EventSubscribeResponseHandler.java */
/* loaded from: classes.dex */
public class a extends i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        switch (aVar.q()) {
            case 1:
                a((c) aVar);
                return;
            case 2:
                a((d) aVar);
                return;
            case 3:
            case 4:
                a((f) aVar);
                return;
            case 5:
                a((b) aVar);
                return;
            case 6:
            case 7:
                a((e) aVar);
                return;
            case 8:
            default:
                return;
            case 9:
                a((com.netease.nimlib.biz.e.a.a) aVar);
                return;
        }
    }

    private void a(c cVar) {
        if (cVar.n()) {
            com.netease.nimlib.f.a d = ((com.netease.nimlib.biz.d.a.b) b(cVar)).d();
            d.a(cVar.a());
            a(cVar, d);
            return;
        }
        a(cVar, null);
    }

    private void a(d dVar) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new com.netease.nimlib.f.a(dVar.a()));
        com.netease.nimlib.i.b.a((ArrayList<Event>) arrayList);
    }

    private void a(com.netease.nimlib.biz.e.a.a aVar) {
        com.netease.nimlib.i.b.a(aVar.a());
    }

    private void a(f fVar) {
        a(fVar, fVar.a());
    }

    private void a(b bVar) {
        a(bVar, null);
    }

    private void a(e eVar) {
        a(eVar, eVar.a());
    }
}
