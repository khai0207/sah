package com.netease.nimlib.amazonaws.handlers;

import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.Response;
import com.netease.nimlib.amazonaws.util.AWSRequestMetrics;

/* loaded from: classes.dex */
final class RequestHandler2Adaptor extends RequestHandler2 {
    private final RequestHandler old;

    RequestHandler2Adaptor(RequestHandler requestHandler) {
        if (requestHandler == null) {
            throw new IllegalArgumentException();
        }
        this.old = requestHandler;
    }

    @Override // com.netease.nimlib.amazonaws.handlers.RequestHandler2
    public void beforeRequest(Request<?> request) {
        this.old.beforeRequest(request);
    }

    @Override // com.netease.nimlib.amazonaws.handlers.RequestHandler2
    public void afterResponse(Request<?> request, Response<?> response) {
        AWSRequestMetrics aWSRequestMetrics = request == null ? null : request.getAWSRequestMetrics();
        this.old.afterResponse(request, response == null ? null : response.getAwsResponse(), aWSRequestMetrics != null ? aWSRequestMetrics.getTimingInfo() : null);
    }

    @Override // com.netease.nimlib.amazonaws.handlers.RequestHandler2
    public void afterError(Request<?> request, Response<?> response, Exception exc) {
        this.old.afterError(request, exc);
    }

    public int hashCode() {
        return this.old.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof RequestHandler2Adaptor) {
            return this.old.equals(((RequestHandler2Adaptor) obj).old);
        }
        return false;
    }
}
