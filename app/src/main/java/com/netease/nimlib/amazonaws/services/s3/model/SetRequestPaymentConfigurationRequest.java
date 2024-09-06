package com.netease.nimlib.amazonaws.services.s3.model;

import com.netease.nimlib.amazonaws.AmazonWebServiceRequest;

/* loaded from: classes.dex */
public class SetRequestPaymentConfigurationRequest extends AmazonWebServiceRequest {
    private String bucketName;
    private RequestPaymentConfiguration configuration;

    public SetRequestPaymentConfigurationRequest(String str, RequestPaymentConfiguration requestPaymentConfiguration) {
        setBucketName(str);
        this.configuration = requestPaymentConfiguration;
    }

    public RequestPaymentConfiguration getConfiguration() {
        return this.configuration;
    }

    public void setConfiguration(RequestPaymentConfiguration requestPaymentConfiguration) {
        this.configuration = requestPaymentConfiguration;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }
}
