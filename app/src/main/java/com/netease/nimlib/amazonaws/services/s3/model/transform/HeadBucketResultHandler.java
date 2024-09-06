package com.netease.nimlib.amazonaws.services.s3.model.transform;

import com.netease.nimlib.amazonaws.AmazonWebServiceResponse;
import com.netease.nimlib.amazonaws.http.HttpResponse;
import com.netease.nimlib.amazonaws.services.s3.Headers;
import com.netease.nimlib.amazonaws.services.s3.internal.AbstractS3ResponseHandler;
import com.netease.nimlib.amazonaws.services.s3.model.HeadBucketResult;

/* loaded from: classes.dex */
public class HeadBucketResultHandler extends AbstractS3ResponseHandler<HeadBucketResult> {
    @Override // com.netease.nimlib.amazonaws.http.HttpResponseHandler
    public AmazonWebServiceResponse<HeadBucketResult> handle(HttpResponse httpResponse) throws Exception {
        AmazonWebServiceResponse<HeadBucketResult> amazonWebServiceResponse = new AmazonWebServiceResponse<>();
        HeadBucketResult headBucketResult = new HeadBucketResult();
        headBucketResult.setBucketRegion(httpResponse.getHeaders().get(Headers.S3_BUCKET_REGION));
        amazonWebServiceResponse.setResult(headBucketResult);
        return amazonWebServiceResponse;
    }
}
