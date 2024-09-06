package com.netease.nimlib.amazonaws.services.s3.model;

import com.netease.nimlib.amazonaws.AmazonWebServiceRequest;

/* loaded from: classes.dex */
public class GetBucketWebsiteConfigurationRequest extends AmazonWebServiceRequest {
    private String bucketName;

    public GetBucketWebsiteConfigurationRequest(String str) {
        this.bucketName = str;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public GetBucketWebsiteConfigurationRequest withBucketName(String str) {
        setBucketName(str);
        return this;
    }
}
