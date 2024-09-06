package com.netease.nimlib.amazonaws.services.s3.internal;

import com.netease.nimlib.amazonaws.http.HttpResponse;
import com.netease.nimlib.amazonaws.services.s3.Headers;
import com.netease.nimlib.amazonaws.services.s3.model.DeleteObjectTaggingResult;

/* loaded from: classes.dex */
public class DeleteObjectTaggingHeaderHandler implements HeaderHandler<DeleteObjectTaggingResult> {
    @Override // com.netease.nimlib.amazonaws.services.s3.internal.HeaderHandler
    public void handle(DeleteObjectTaggingResult deleteObjectTaggingResult, HttpResponse httpResponse) {
        deleteObjectTaggingResult.setVersionId(httpResponse.getHeaders().get(Headers.S3_VERSION_ID));
    }
}
