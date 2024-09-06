package com.netease.nimlib.amazonaws.services.s3.model;

import com.netease.nimlib.amazonaws.AmazonWebServiceRequest;

/* loaded from: classes.dex */
public class SetBucketAccelerateConfigurationRequest extends AmazonWebServiceRequest implements S3AccelerateUnsupported {
    private BucketAccelerateConfiguration accelerateConfiguration;
    private String bucketName;

    public SetBucketAccelerateConfigurationRequest(String str, BucketAccelerateConfiguration bucketAccelerateConfiguration) {
        this.bucketName = str;
        this.accelerateConfiguration = bucketAccelerateConfiguration;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public SetBucketAccelerateConfigurationRequest withBucketName(String str) {
        setBucketName(str);
        return this;
    }

    public BucketAccelerateConfiguration getAccelerateConfiguration() {
        return this.accelerateConfiguration;
    }

    public void setAccelerateConfiguration(BucketAccelerateConfiguration bucketAccelerateConfiguration) {
        this.accelerateConfiguration = bucketAccelerateConfiguration;
    }

    public SetBucketAccelerateConfigurationRequest withAccelerateConfiguration(BucketAccelerateConfiguration bucketAccelerateConfiguration) {
        setAccelerateConfiguration(bucketAccelerateConfiguration);
        return this;
    }
}
