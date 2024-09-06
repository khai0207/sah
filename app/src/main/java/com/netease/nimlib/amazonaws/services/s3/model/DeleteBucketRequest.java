package com.netease.nimlib.amazonaws.services.s3.model;

import com.netease.nimlib.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* loaded from: classes.dex */
public class DeleteBucketRequest extends AmazonWebServiceRequest implements S3AccelerateUnsupported, Serializable {
    private String bucketName;

    public DeleteBucketRequest(String str) {
        setBucketName(str);
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public String getBucketName() {
        return this.bucketName;
    }
}
