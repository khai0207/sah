package com.netease.nimlib.amazonaws.services.s3.model;

import com.netease.nimlib.amazonaws.services.s3.internal.ObjectExpirationResult;
import com.netease.nimlib.amazonaws.services.s3.internal.S3RequesterChargedResult;
import com.netease.nimlib.amazonaws.services.s3.internal.S3VersionResult;
import com.netease.nimlib.amazonaws.services.s3.internal.SSEResultBase;
import java.util.Date;

/* loaded from: classes.dex */
public class PutObjectResult extends SSEResultBase implements ObjectExpirationResult, S3RequesterChargedResult, S3VersionResult {
    private String contentMd5;
    private String eTag;
    private Date expirationTime;
    private String expirationTimeRuleId;
    private boolean isRequesterCharged;
    private ObjectMetadata metadata;
    private String versionId;

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.S3VersionResult
    public String getVersionId() {
        return this.versionId;
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.S3VersionResult
    public void setVersionId(String str) {
        this.versionId = str;
    }

    public String getETag() {
        return this.eTag;
    }

    public void setETag(String str) {
        this.eTag = str;
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.ObjectExpirationResult
    public Date getExpirationTime() {
        return this.expirationTime;
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.ObjectExpirationResult
    public void setExpirationTime(Date date) {
        this.expirationTime = date;
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.ObjectExpirationResult
    public String getExpirationTimeRuleId() {
        return this.expirationTimeRuleId;
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.ObjectExpirationResult
    public void setExpirationTimeRuleId(String str) {
        this.expirationTimeRuleId = str;
    }

    public void setContentMd5(String str) {
        this.contentMd5 = str;
    }

    public String getContentMd5() {
        return this.contentMd5;
    }

    public ObjectMetadata getMetadata() {
        return this.metadata;
    }

    public void setMetadata(ObjectMetadata objectMetadata) {
        this.metadata = objectMetadata;
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.S3RequesterChargedResult
    public boolean isRequesterCharged() {
        return this.isRequesterCharged;
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.S3RequesterChargedResult
    public void setRequesterCharged(boolean z) {
        this.isRequesterCharged = z;
    }
}
