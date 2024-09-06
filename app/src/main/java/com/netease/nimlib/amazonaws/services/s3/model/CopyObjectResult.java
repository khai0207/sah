package com.netease.nimlib.amazonaws.services.s3.model;

import com.netease.nimlib.amazonaws.services.s3.internal.ObjectExpirationResult;
import com.netease.nimlib.amazonaws.services.s3.internal.S3RequesterChargedResult;
import com.netease.nimlib.amazonaws.services.s3.internal.S3VersionResult;
import com.netease.nimlib.amazonaws.services.s3.internal.SSEResultBase;
import java.io.Serializable;
import java.util.Date;

/* loaded from: classes.dex */
public class CopyObjectResult extends SSEResultBase implements ObjectExpirationResult, S3RequesterChargedResult, S3VersionResult, Serializable {
    private String etag;
    private Date expirationTime;
    private String expirationTimeRuleId;
    private boolean isRequesterCharged;
    private Date lastModifiedDate;
    private String versionId;

    public String getETag() {
        return this.etag;
    }

    public void setETag(String str) {
        this.etag = str;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.S3VersionResult
    public String getVersionId() {
        return this.versionId;
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.S3VersionResult
    public void setVersionId(String str) {
        this.versionId = str;
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

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.S3RequesterChargedResult
    public boolean isRequesterCharged() {
        return this.isRequesterCharged;
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.S3RequesterChargedResult
    public void setRequesterCharged(boolean z) {
        this.isRequesterCharged = z;
    }
}
