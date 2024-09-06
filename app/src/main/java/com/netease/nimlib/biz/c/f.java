package com.netease.nimlib.biz.c;

import android.util.SparseArray;
import com.netease.nimlib.biz.e.d.j;
import com.netease.nimlib.biz.e.d.p;

/* compiled from: ResponseFactoryPush.java */
/* loaded from: classes.dex */
class f extends e {
    private SparseArray<Class<? extends com.netease.nimlib.biz.e.a>> a;

    /* compiled from: ResponseFactoryPush.java */
    /* loaded from: classes.dex */
    public static class a {
        static f a = new f();
    }

    /* synthetic */ f(AnonymousClass1 anonymousClass1) {
        this();
    }

    private f() {
        this.a = new SparseArray<>();
        b();
    }

    @Override // com.netease.nimlib.biz.c.e
    protected void a() {
        a(com.netease.nimlib.push.a.c.b.class, new com.netease.nimlib.push.a.a.b(true));
        a(com.netease.nimlib.biz.e.i.a.class, new com.netease.nimlib.push.a.a.a());
        a(com.netease.nimlib.push.a.c.e.class, new com.netease.nimlib.push.a.a.d(true));
        a(com.netease.nimlib.push.a.c.c.class, new com.netease.nimlib.push.a.a.c());
        a(com.netease.nimlib.push.a.c.g.class, new com.netease.nimlib.push.a.a.e(true));
        a(j.class, (com.netease.nimlib.biz.c.a) null);
        a(p.class, (com.netease.nimlib.biz.c.a) null);
    }

    private void b() {
        this.a.append(a((byte) 4, (byte) 1), com.netease.nimlib.push.a.c.e.class);
        this.a.append(a((byte) 4, (byte) 2), com.netease.nimlib.push.a.c.e.class);
    }

    boolean f(com.netease.nimlib.push.packet.a aVar) {
        return (a(aVar) && this.a.get(e(aVar)) == null) ? false : true;
    }
}
