package com.netease.nimlib.amazonaws.handlers;

import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.util.TimingInfo;

@Deprecated
/* loaded from: classes.dex */
public interface RequestHandler {
    void afterError(Request<?> request, Exception exc);

    void afterResponse(Request<?> request, Object obj, TimingInfo timingInfo);

    void beforeRequest(Request<?> request);
}
