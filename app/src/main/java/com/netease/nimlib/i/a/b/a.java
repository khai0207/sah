package com.netease.nimlib.i.a.b;

import com.netease.nimlib.sdk.RequestCallbackWrapper;

/* compiled from: RequestCallbackDelegate.java */
/* loaded from: classes.dex */
public class a<T> extends RequestCallbackWrapper<T> {
    private b<T> a;

    public a(b<T> bVar) {
        this.a = bVar;
    }

    @Override // com.netease.nimlib.sdk.RequestCallbackWrapper
    public void onResult(int i, T t, Throwable th) {
        this.a.a(i, t, th);
    }
}
