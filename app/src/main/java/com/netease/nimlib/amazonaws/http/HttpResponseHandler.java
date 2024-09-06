package com.netease.nimlib.amazonaws.http;

/* loaded from: classes.dex */
public interface HttpResponseHandler<T> {
    T handle(HttpResponse httpResponse) throws Exception;

    boolean needsConnectionLeftOpen();
}
