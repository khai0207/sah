package com.netease.nimlib.i.a.b;

import com.netease.nimlib.sdk.util.api.RequestResult;

/* compiled from: RequestResultWrapper.java */
/* loaded from: classes.dex */
public class b<T> {
    private com.netease.nimlib.i.a.c.b a;
    private RequestResult<T> b;
    private boolean c = false;

    public void a(com.netease.nimlib.i.a.c.b bVar) {
        this.a = bVar;
    }

    public void a(int i) {
        if (this.b != null) {
            return;
        }
        this.b = new RequestResult<>(i);
    }

    void a(int i, T t, Throwable th) {
        if (this.b != null) {
            return;
        }
        this.b = new RequestResult<>(i, t, th);
        this.c = true;
        com.netease.nimlib.i.a.c.b bVar = this.a;
        if (bVar != null) {
            bVar.b();
        }
    }

    public boolean a() {
        return this.c;
    }

    public RequestResult<T> b() {
        return this.b;
    }
}
