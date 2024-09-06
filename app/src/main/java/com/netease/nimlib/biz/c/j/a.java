package com.netease.nimlib.biz.c.j;

import android.util.Pair;
import com.netease.nimlib.biz.c.i;
import com.netease.nimlib.biz.d.j.g;
import com.netease.nimlib.biz.d.j.k;
import com.netease.nimlib.biz.e.k.h;
import com.netease.nimlib.biz.e.k.l;
import com.netease.nimlib.biz.e.k.s;
import com.netease.nimlib.session.MsgDBHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: CollectResponseHandler.java */
/* loaded from: classes.dex */
public class a extends i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        if (!aVar.n()) {
            a(aVar, null);
            return;
        }
        if (aVar instanceof com.netease.nimlib.biz.e.k.a) {
            a((com.netease.nimlib.biz.e.k.a) aVar);
            return;
        }
        if (aVar instanceof l) {
            a((l) aVar);
        } else if (aVar instanceof s) {
            a((s) aVar);
        } else if (aVar instanceof h) {
            a((h) aVar);
        }
    }

    private void a(com.netease.nimlib.biz.e.k.a aVar) {
        com.netease.nimlib.session.a a = aVar.a();
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(a);
        MsgDBHelper.saveCollectInfo(arrayList);
        a(aVar, a);
    }

    private void a(l lVar) {
        List<Pair<Long, Long>> d = ((k) b(lVar)).d();
        if (d != null && !d.isEmpty()) {
            ArrayList arrayList = new ArrayList(d.size());
            Iterator<Pair<Long, Long>> it = d.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().first);
            }
            MsgDBHelper.deleteCollectInfo(arrayList);
        }
        a(lVar, Integer.valueOf(lVar.a()));
    }

    private void a(s sVar) {
        MsgDBHelper.updateCollectInfo(sVar.a());
        a(sVar, sVar.a());
    }

    private void a(h hVar) {
        ArrayList<com.netease.nimlib.session.a> b = hVar.b();
        if (((g) b(hVar)).d()) {
            MsgDBHelper.saveCollectInfo(b);
        }
        a(hVar, new com.netease.nimlib.session.b(hVar.a(), b));
    }
}
