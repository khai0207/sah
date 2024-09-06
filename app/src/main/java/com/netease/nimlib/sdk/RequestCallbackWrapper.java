package com.netease.nimlib.sdk;

/* loaded from: classes.dex */
public abstract class RequestCallbackWrapper<T> implements RequestCallback<T> {
    public abstract void onResult(int i, T t, Throwable th);

    @Override // com.netease.nimlib.sdk.RequestCallback
    public void onSuccess(T t) {
        onResult(200, t, null);
    }

    @Override // com.netease.nimlib.sdk.RequestCallback
    public void onFailed(int i) {
        onResult(i, null, null);
    }

    @Override // com.netease.nimlib.sdk.RequestCallback
    public void onException(Throwable th) {
        th.printStackTrace();
        onResult(1000, null, th);
    }
}
