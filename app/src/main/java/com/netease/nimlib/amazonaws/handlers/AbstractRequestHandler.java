package com.netease.nimlib.amazonaws.handlers;

import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.util.TimingInfo;

@Deprecated
/* loaded from: classes.dex */
public abstract class AbstractRequestHandler implements RequestHandler {
    @Override // com.netease.nimlib.amazonaws.handlers.RequestHandler
    public void afterError(Request<?> request, Exception exc) {
    }

    @Override // com.netease.nimlib.amazonaws.handlers.RequestHandler
    public void afterResponse(Request<?> request, Object obj, TimingInfo timingInfo) {
    }

    @Override // com.netease.nimlib.amazonaws.handlers.RequestHandler
    public void beforeRequest(Request<?> request) {
    }
}
