package com.netease.nimlib.net.b;

import android.util.SparseArray;
import com.netease.nimlib.net.b.a.c;
import com.netease.nimlib.net.b.a.e;
import com.netease.nimlib.net.b.a.k;
import com.netease.nimlib.net.b.c.f;
import com.netease.nimlib.push.net.lbs.b;

/* compiled from: Bootstrap.java */
/* loaded from: classes.dex */
public final class a {
    private long b;
    private e c;
    private final SparseArray<Object> a = new SparseArray<>();
    private f d = new f();

    public <T> a a(int i, T t) {
        if (t == null) {
            synchronized (this.a) {
                this.a.remove(i);
            }
        } else {
            synchronized (this.a) {
                this.a.put(i, t);
            }
        }
        return this;
    }

    public a a(e eVar) {
        this.c = eVar;
        return this;
    }

    public a a(long j) {
        this.b = j;
        return this;
    }

    public c a(b bVar) {
        return b().a().a(bVar, this.a, this.c, this.b);
    }

    public void a() {
        f fVar = this.d;
        if (fVar != null) {
            fVar.i();
        }
    }

    private com.netease.nimlib.net.b.a.a b() {
        return new k(this.d);
    }
}
