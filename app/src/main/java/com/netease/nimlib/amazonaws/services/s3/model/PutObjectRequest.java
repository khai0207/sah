package com.netease.nimlib.amazonaws.services.s3.model;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;

/* loaded from: classes.dex */
public class PutObjectRequest extends AbstractPutObjectRequest implements Serializable {
    private boolean isRequesterPays;

    public PutObjectRequest(String str, String str2, File file) {
        super(str, str2, file);
    }

    public PutObjectRequest(String str, String str2, String str3) {
        super(str, str2, str3);
    }

    public PutObjectRequest(String str, String str2, InputStream inputStream, ObjectMetadata objectMetadata) {
        super(str, str2, inputStream, objectMetadata);
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.model.AbstractPutObjectRequest, com.netease.nimlib.amazonaws.AmazonWebServiceRequest
    /* renamed from: clone */
    public PutObjectRequest mo6clone() {
        return (PutObjectRequest) copyPutObjectBaseTo((PutObjectRequest) super.mo6clone());
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.model.AbstractPutObjectRequest
    public PutObjectRequest withBucketName(String str) {
        return (PutObjectRequest) super.withBucketName(str);
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.model.AbstractPutObjectRequest
    public PutObjectRequest withKey(String str) {
        return (PutObjectRequest) super.withKey(str);
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.model.AbstractPutObjectRequest
    public PutObjectRequest withStorageClass(String str) {
        return (PutObjectRequest) super.withStorageClass(str);
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.model.AbstractPutObjectRequest
    public PutObjectRequest withStorageClass(StorageClass storageClass) {
        return (PutObjectRequest) super.withStorageClass(storageClass);
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.model.AbstractPutObjectRequest
    public PutObjectRequest withFile(File file) {
        return (PutObjectRequest) super.withFile(file);
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.model.AbstractPutObjectRequest
    public PutObjectRequest withMetadata(ObjectMetadata objectMetadata) {
        return (PutObjectRequest) super.withMetadata(objectMetadata);
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.model.AbstractPutObjectRequest
    public PutObjectRequest withCannedAcl(CannedAccessControlList cannedAccessControlList) {
        return (PutObjectRequest) super.withCannedAcl(cannedAccessControlList);
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.model.AbstractPutObjectRequest
    public PutObjectRequest withAccessControlList(AccessControlList accessControlList) {
        return (PutObjectRequest) super.withAccessControlList(accessControlList);
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.model.AbstractPutObjectRequest
    public PutObjectRequest withInputStream(InputStream inputStream) {
        return (PutObjectRequest) super.withInputStream(inputStream);
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.model.AbstractPutObjectRequest
    public PutObjectRequest withRedirectLocation(String str) {
        return (PutObjectRequest) super.withRedirectLocation(str);
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.model.AbstractPutObjectRequest
    public PutObjectRequest withSSECustomerKey(SSECustomerKey sSECustomerKey) {
        return (PutObjectRequest) super.withSSECustomerKey(sSECustomerKey);
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.model.AbstractPutObjectRequest
    public PutObjectRequest withTagging(ObjectTagging objectTagging) {
        super.setTagging(objectTagging);
        return this;
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.model.AbstractPutObjectRequest
    @Deprecated
    public PutObjectRequest withProgressListener(ProgressListener progressListener) {
        return (PutObjectRequest) super.withProgressListener(progressListener);
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.model.AbstractPutObjectRequest
    public PutObjectRequest withSSEAwsKeyManagementParams(SSEAwsKeyManagementParams sSEAwsKeyManagementParams) {
        return (PutObjectRequest) super.withSSEAwsKeyManagementParams(sSEAwsKeyManagementParams);
    }

    @Override // com.netease.nimlib.amazonaws.AmazonWebServiceRequest
    public PutObjectRequest withGeneralProgressListener(com.netease.nimlib.amazonaws.event.ProgressListener progressListener) {
        return (PutObjectRequest) super.withGeneralProgressListener(progressListener);
    }

    public boolean isRequesterPays() {
        return this.isRequesterPays;
    }

    public void setRequesterPays(boolean z) {
        this.isRequesterPays = z;
    }

    public PutObjectRequest withRequesterPays(boolean z) {
        setRequesterPays(z);
        return this;
    }
}
