package com.netease.nimlib.amazonaws.services.s3.model;

import com.netease.nimlib.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* loaded from: classes.dex */
public class GetBucketLocationRequest extends AmazonWebServiceRequest implements Serializable {
    private String bucketName;

    public GetBucketLocationRequest(String str) {
        this.bucketName = str;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public GetBucketLocationRequest withBucketName(String str) {
        setBucketName(str);
        return this;
    }
}
