package com.netease.nimlib.amazonaws.services.s3.internal;

import com.netease.nimlib.amazonaws.http.HttpResponse;
import com.netease.nimlib.amazonaws.services.s3.Headers;
import com.netease.nimlib.amazonaws.services.s3.internal.S3VersionResult;

/* loaded from: classes.dex */
public class S3VersionHeaderHandler<T extends S3VersionResult> implements HeaderHandler<T> {
    @Override // com.netease.nimlib.amazonaws.services.s3.internal.HeaderHandler
    public void handle(T t, HttpResponse httpResponse) {
        t.setVersionId(httpResponse.getHeaders().get(Headers.S3_VERSION_ID));
    }
}
