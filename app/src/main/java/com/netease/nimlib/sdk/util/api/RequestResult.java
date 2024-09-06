package com.netease.nimlib.sdk.util.api;

/* loaded from: classes.dex */
public class RequestResult<T> {
    public int code;
    public T data;
    public Throwable exception;

    public RequestResult(int i, T t, Throwable th) {
        this.code = i;
        this.data = t;
        this.exception = th;
    }

    public RequestResult(int i) {
        this.code = i;
    }

    public String toString() {
        return "RequestResult{code=" + this.code + ", exception=" + this.exception + ", data=" + this.data + '}';
    }
}
