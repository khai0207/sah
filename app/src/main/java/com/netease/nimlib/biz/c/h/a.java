package com.netease.nimlib.biz.c.h;

import com.netease.nimlib.biz.c.i;
import com.netease.nimlib.biz.e.i.b;
import com.netease.nimlib.biz.f;
import com.netease.nimlib.push.h;
import java.util.ArrayList;
import java.util.List;

/* compiled from: KickSelfResponseHandler.java */
/* loaded from: classes.dex */
public class a extends i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        b bVar = (b) aVar;
        if (bVar.n() && bVar.a().size() > 0) {
            ArrayList arrayList = new ArrayList(bVar.a().size());
            for (String str : bVar.a()) {
                f fVar = new f();
                fVar.e(str);
                arrayList.add(fVar);
            }
            h.a((List<f>) arrayList);
        }
        a(aVar, null);
    }
}
