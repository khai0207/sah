package com.netease.nimlib.biz.c.k;

import com.netease.nimlib.biz.d.k.w;
import com.netease.nimlib.biz.e.l.y;

/* compiled from: UpdateTeamResponseHandler.java */
/* loaded from: classes.dex */
public class n extends com.netease.nimlib.biz.c.i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        if (aVar.n()) {
            y yVar = (y) aVar;
            w wVar = (w) b(aVar);
            if (wVar != null) {
                com.netease.nimlib.team.c.a(yVar.a(), wVar.d());
            }
        }
        a(aVar, null);
    }
}
