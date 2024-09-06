package com.netease.nimlib.i;

import com.netease.nimlib.sdk.AbortableFuture;
import com.netease.nimlib.sdk.RequestCallback;

/* compiled from: RemoteAbortAction.java */
/* loaded from: classes.dex */
public abstract class h<T> implements AbortableFuture {
    protected T c;

    @Override // com.netease.nimlib.sdk.InvocationFuture
    public void setCallback(RequestCallback requestCallback) {
    }

    public h(T t) {
        this.c = t;
    }
}
