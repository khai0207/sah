package com.netease.nimlib.biz.g;

import com.netease.nimlib.biz.e.a;
import com.netease.nimlib.biz.i;
import com.netease.nimlib.sdk.ResponseCode;

/* compiled from: SendTask.java */
/* loaded from: classes.dex */
public abstract class c {
    private final a a;
    private boolean b = false;

    public abstract void a(com.netease.nimlib.biz.e.a aVar);

    public abstract com.netease.nimlib.biz.d.a b();

    public c(a aVar) {
        this.a = aVar == null ? null : new a(aVar);
    }

    public boolean c() {
        a aVar = this.a;
        return aVar != null && aVar.a() > 0;
    }

    public boolean d() {
        return b() != null;
    }

    public int e() {
        a aVar = this.a;
        if (aVar != null) {
            return aVar.b();
        }
        return 0;
    }

    public boolean f() {
        a aVar = this.a;
        if (aVar == null) {
            return false;
        }
        int c = aVar.c();
        if (c == -1 || this.b) {
            a(ResponseCode.RES_ETIMEOUT);
            return false;
        }
        if (c < -1) {
            return false;
        }
        return a();
    }

    public boolean g() {
        a aVar = this.a;
        if (aVar == null || aVar.c() < -1) {
            return false;
        }
        this.b = this.a.a() == -1;
        return a();
    }

    protected boolean a() {
        a(b());
        return true;
    }

    public void a(short s) {
        i.a().a(a.C0029a.a(b().i(), s));
    }

    public void a(short s, boolean z) {
        i.a().a(a.C0029a.a(b().i(), s), z);
    }

    final void a(com.netease.nimlib.biz.d.a aVar) {
        aVar.i().b();
        i.a().a(this);
    }
}
