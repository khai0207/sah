package com.netease.nimlib.amazonaws.services.s3.model;

import com.netease.nimlib.amazonaws.AmazonWebServiceRequest;

/* loaded from: classes.dex */
public class GetBucketAclRequest extends AmazonWebServiceRequest {
    private String bucketName;

    public GetBucketAclRequest(String str) {
        this.bucketName = str;
    }

    public String getBucketName() {
        return this.bucketName;
    }
}
