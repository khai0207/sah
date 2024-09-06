package com.netease.nimlib.biz.c.i;

import com.netease.nimlib.biz.e.j.z;

/* compiled from: SyncSessionReliableInfoResponseHandler.java */
/* loaded from: classes.dex */
public class m extends com.netease.nimlib.biz.c.i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        if (!aVar.n()) {
            a(aVar, null);
        } else if (aVar instanceof z) {
            a((z) aVar);
        }
    }

    private void a(z zVar) {
        com.netease.nimlib.session.a.c.a().a(com.netease.nimlib.session.a.e.a(zVar.a(), zVar.b()));
    }
}
