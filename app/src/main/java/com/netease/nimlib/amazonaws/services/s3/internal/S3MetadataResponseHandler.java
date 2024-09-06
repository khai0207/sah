package com.netease.nimlib.amazonaws.services.s3.internal;

import com.netease.nimlib.amazonaws.AmazonWebServiceResponse;
import com.netease.nimlib.amazonaws.http.HttpResponse;
import com.netease.nimlib.amazonaws.services.s3.model.ObjectMetadata;

/* loaded from: classes.dex */
public class S3MetadataResponseHandler extends AbstractS3ResponseHandler<ObjectMetadata> {
    @Override // com.netease.nimlib.amazonaws.http.HttpResponseHandler
    public AmazonWebServiceResponse<ObjectMetadata> handle(HttpResponse httpResponse) throws Exception {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        populateObjectMetadata(httpResponse, objectMetadata);
        AmazonWebServiceResponse<ObjectMetadata> parseResponseMetadata = parseResponseMetadata(httpResponse);
        parseResponseMetadata.setResult(objectMetadata);
        return parseResponseMetadata;
    }
}
