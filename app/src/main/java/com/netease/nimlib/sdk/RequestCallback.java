package com.netease.nimlib.sdk;

/* loaded from: classes.dex */
public interface RequestCallback<T> {
    void onException(Throwable th);

    void onFailed(int i);

    void onSuccess(T t);
}
