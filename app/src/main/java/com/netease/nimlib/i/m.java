package com.netease.nimlib.i;

import com.netease.nimlib.sdk.AbortableFuture;
import com.netease.nimlib.sdk.RequestCallback;

/* compiled from: TransactionFuture.java */
/* loaded from: classes.dex */
class m implements AbortableFuture {
    private RequestCallback a;
    private k b;

    m(k kVar) {
        this.b = kVar;
    }

    @Override // com.netease.nimlib.sdk.InvocationFuture
    public final void setCallback(RequestCallback requestCallback) {
        this.a = requestCallback;
    }

    @Override // com.netease.nimlib.sdk.AbortableFuture
    public boolean abort() {
        return a.b(this.b);
    }

    public final void a(int i, Object obj) {
        this.b.a(i);
        this.b.a(obj);
    }

    public final void a() {
        com.netease.nimlib.n.a.a().c(this.b);
        if (this.a == null) {
            return;
        }
        int i = this.b.i();
        Object j = this.b.j();
        if (i == 200) {
            this.a.onSuccess(j);
        } else if (j instanceof Throwable) {
            this.a.onException((Throwable) j);
        } else {
            this.a.onFailed(i);
        }
    }
}
