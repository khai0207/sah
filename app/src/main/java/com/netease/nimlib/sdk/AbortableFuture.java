package com.netease.nimlib.sdk;

/* loaded from: classes.dex */
public interface AbortableFuture<T> extends InvocationFuture<T> {
    boolean abort();
}
