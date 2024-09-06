package com.netease.nimlib.amazonaws.handlers;

import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.Response;

/* loaded from: classes.dex */
public abstract class RequestHandler2 {
    public abstract void afterError(Request<?> request, Response<?> response, Exception exc);

    public abstract void afterResponse(Request<?> request, Response<?> response);

    public abstract void beforeRequest(Request<?> request);

    public static RequestHandler2 adapt(RequestHandler requestHandler) {
        return new RequestHandler2Adaptor(requestHandler);
    }
}
