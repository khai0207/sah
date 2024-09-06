package com.netease.nimlib.net.b.a;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: ChannelFuture.java */
/* loaded from: classes.dex */
public class c {
    private static final a a = new a(com.alipay.sdk.m.a0.c.p);
    private static final a b = new a("CANCELED");
    private List<f> c;
    private com.netease.nimlib.net.b.c.f d;
    private com.netease.nimlib.net.b.a.a e;
    private Object f;
    private Throwable g;

    public c(com.netease.nimlib.net.b.a.a aVar) {
        this.e = aVar;
        this.d = aVar.a().d();
    }

    public com.netease.nimlib.net.b.a.a a() {
        return this.e;
    }

    public void a(f fVar) {
        if (this.c == null) {
            this.c = new ArrayList();
        }
        if (this.c.contains(fVar)) {
            return;
        }
        synchronized (this) {
            this.c.add(fVar);
        }
        c(fVar);
    }

    public void b(f fVar) {
        if (this.c != null) {
            synchronized (this) {
                this.c.remove(fVar);
            }
        }
    }

    public void a(Object obj) {
        if (obj == null) {
            obj = a;
        }
        this.f = obj;
        this.g = null;
        g();
    }

    public void a(Throwable th) {
        this.g = th;
        g();
    }

    public Throwable b() {
        return this.g;
    }

    public boolean c() {
        return (this.f == null && this.g == null) ? false : true;
    }

    public boolean d() {
        return this.g == null && this.f != b;
    }

    public boolean e() {
        return this.f == b;
    }

    public boolean f() {
        if (c()) {
            return false;
        }
        synchronized (this) {
            if (c()) {
                return false;
            }
            this.f = b;
            g();
            return true;
        }
    }

    private void g() {
        if (this.d.h()) {
            h();
        } else {
            com.netease.nimlib.net.b.e.a.a(this.d, new Runnable() { // from class: com.netease.nimlib.net.b.a.c.1
                @Override // java.lang.Runnable
                public void run() {
                    c.this.h();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        List<f> list = this.c;
        if (list == null) {
            return;
        }
        Iterator<f> it = list.iterator();
        while (it.hasNext()) {
            it.next().onComplete(this);
        }
    }

    private void c(final f fVar) {
        if (c()) {
            if (this.d.h()) {
                fVar.onComplete(this);
            } else {
                com.netease.nimlib.net.b.e.a.a(this.d, new Runnable() { // from class: com.netease.nimlib.net.b.a.c.2
                    @Override // java.lang.Runnable
                    public void run() {
                        fVar.onComplete(c.this);
                    }
                });
            }
        }
    }

    /* compiled from: ChannelFuture.java */
    /* loaded from: classes.dex */
    private static final class a {
        String a;

        a(String str) {
            this.a = str;
        }

        public String toString() {
            return "ChannelFutureResult " + this.a;
        }
    }
}
