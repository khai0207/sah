package com.netease.nimlib.amazonaws.services.s3.internal;

import com.netease.nimlib.amazonaws.http.HttpResponse;
import com.netease.nimlib.amazonaws.services.s3.Headers;
import com.netease.nimlib.amazonaws.services.s3.model.SetObjectTaggingResult;

/* loaded from: classes.dex */
public class SetObjectTaggingResponseHeaderHandler implements HeaderHandler<SetObjectTaggingResult> {
    @Override // com.netease.nimlib.amazonaws.services.s3.internal.HeaderHandler
    public void handle(SetObjectTaggingResult setObjectTaggingResult, HttpResponse httpResponse) {
        setObjectTaggingResult.setVersionId(httpResponse.getHeaders().get(Headers.S3_VERSION_ID));
    }
}
