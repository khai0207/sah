package com.netease.nimlib.biz.c;

import com.netease.nimlib.i.k;

/* compiled from: UIResponseHandler.java */
/* loaded from: classes.dex */
public abstract class i extends a {
    protected com.netease.nimlib.biz.d.a b(com.netease.nimlib.biz.e.a aVar) {
        return com.netease.nimlib.biz.i.a().a(aVar);
    }

    protected <T extends com.netease.nimlib.biz.d.a> T c(com.netease.nimlib.biz.e.a aVar) {
        return (T) b(aVar);
    }

    protected void a(com.netease.nimlib.biz.e.a aVar, Object obj) {
        a(aVar, obj, aVar.r());
    }

    protected void a(com.netease.nimlib.biz.e.a aVar, Object obj, int i) {
        com.netease.nimlib.biz.d.a b = b(aVar);
        if (b == null || b.j() == null || !(b.j() instanceof k)) {
            return;
        }
        ((k) b.j()).a(i).a(obj).b();
    }
}
