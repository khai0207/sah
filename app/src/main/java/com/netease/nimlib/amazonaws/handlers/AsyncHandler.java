package com.netease.nimlib.amazonaws.handlers;

import com.netease.nimlib.amazonaws.AmazonWebServiceRequest;

/* loaded from: classes.dex */
public interface AsyncHandler<REQUEST extends AmazonWebServiceRequest, RESULT> {
    void onError(Exception exc);

    void onSuccess(REQUEST request, RESULT result);
}
