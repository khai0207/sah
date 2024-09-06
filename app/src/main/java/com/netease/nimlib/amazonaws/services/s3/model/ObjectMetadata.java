package com.netease.nimlib.amazonaws.services.s3.model;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.services.s3.Headers;
import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import com.netease.nimlib.amazonaws.services.s3.internal.ObjectExpirationResult;
import com.netease.nimlib.amazonaws.services.s3.internal.ObjectRestoreResult;
import com.netease.nimlib.amazonaws.services.s3.internal.S3RequesterChargedResult;
import com.netease.nimlib.amazonaws.services.s3.internal.ServerSideEncryptionResult;
import com.netease.nimlib.amazonaws.util.DateUtils;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes.dex */
public class ObjectMetadata implements ObjectExpirationResult, ObjectRestoreResult, S3RequesterChargedResult, ServerSideEncryptionResult, Serializable, Cloneable {
    public static final String AES_256_SERVER_SIDE_ENCRYPTION = SSEAlgorithm.AES256.getAlgorithm();
    public static final String KMS_SERVER_SIDE_ENCRYPTION = SSEAlgorithm.KMS.getAlgorithm();
    private Date expirationTime;
    private String expirationTimeRuleId;
    private Date httpExpiresDate;
    private Map<String, Object> metadata;
    private Boolean ongoingRestore;
    private Date restoreExpirationTime;
    private Map<String, String> userMetadata;

    public ObjectMetadata() {
        this.userMetadata = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        this.metadata = new TreeMap(String.CASE_INSENSITIVE_ORDER);
    }

    private ObjectMetadata(ObjectMetadata objectMetadata) {
        this.userMetadata = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        this.metadata = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        this.userMetadata = objectMetadata.userMetadata == null ? null : new TreeMap(objectMetadata.userMetadata);
        this.metadata = objectMetadata.metadata != null ? new TreeMap(objectMetadata.metadata) : null;
        this.expirationTime = DateUtils.cloneDate(objectMetadata.expirationTime);
        this.expirationTimeRuleId = objectMetadata.expirationTimeRuleId;
        this.httpExpiresDate = DateUtils.cloneDate(objectMetadata.httpExpiresDate);
        this.ongoingRestore = objectMetadata.ongoingRestore;
        this.restoreExpirationTime = DateUtils.cloneDate(objectMetadata.restoreExpirationTime);
    }

    public Map<String, String> getUserMetadata() {
        return this.userMetadata;
    }

    public void setUserMetadata(Map<String, String> map) {
        this.userMetadata = map;
    }

    public void setHeader(String str, Object obj) {
        this.metadata.put(str, obj);
    }

    public void addUserMetadata(String str, String str2) {
        this.userMetadata.put(str, str2);
    }

    public Map<String, Object> getRawMetadata() {
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        treeMap.putAll(this.metadata);
        return Collections.unmodifiableMap(treeMap);
    }

    public Object getRawMetadataValue(String str) {
        return this.metadata.get(str);
    }

    public Date getLastModified() {
        return DateUtils.cloneDate((Date) this.metadata.get(Headers.LAST_MODIFIED));
    }

    public void setLastModified(Date date) {
        this.metadata.put(Headers.LAST_MODIFIED, date);
    }

    public long getContentLength() {
        Long l = (Long) this.metadata.get("Content-Length");
        if (l == null) {
            return 0L;
        }
        return l.longValue();
    }

    public long getInstanceLength() {
        int lastIndexOf;
        String str = (String) this.metadata.get(Headers.CONTENT_RANGE);
        if (str != null && (lastIndexOf = str.lastIndexOf("/")) >= 0) {
            return Long.parseLong(str.substring(lastIndexOf + 1));
        }
        return getContentLength();
    }

    public void setContentLength(long j) {
        this.metadata.put("Content-Length", Long.valueOf(j));
    }

    public String getContentType() {
        return (String) this.metadata.get("Content-Type");
    }

    public void setContentType(String str) {
        this.metadata.put("Content-Type", str);
    }

    public String getContentLanguage() {
        return (String) this.metadata.get(Headers.CONTENT_LANGUAGE);
    }

    public void setContentLanguage(String str) {
        this.metadata.put(Headers.CONTENT_LANGUAGE, str);
    }

    public String getContentEncoding() {
        return (String) this.metadata.get("Content-Encoding");
    }

    public void setContentEncoding(String str) {
        this.metadata.put("Content-Encoding", str);
    }

    public String getCacheControl() {
        return (String) this.metadata.get(Headers.CACHE_CONTROL);
    }

    public void setCacheControl(String str) {
        this.metadata.put(Headers.CACHE_CONTROL, str);
    }

    public void setContentMD5(String str) {
        if (str == null) {
            this.metadata.remove(Headers.CONTENT_MD5);
        } else {
            this.metadata.put(Headers.CONTENT_MD5, str);
        }
    }

    public String getContentMD5() {
        return (String) this.metadata.get(Headers.CONTENT_MD5);
    }

    public void setContentDisposition(String str) {
        this.metadata.put(Headers.CONTENT_DISPOSITION, str);
    }

    public String getContentDisposition() {
        return (String) this.metadata.get(Headers.CONTENT_DISPOSITION);
    }

    public String getETag() {
        return (String) this.metadata.get("ETag");
    }

    public String getVersionId() {
        return (String) this.metadata.get(Headers.S3_VERSION_ID);
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.ServerSideEncryptionResult
    public String getSSEAlgorithm() {
        return (String) this.metadata.get(Headers.SERVER_SIDE_ENCRYPTION);
    }

    @Deprecated
    public String getServerSideEncryption() {
        return (String) this.metadata.get(Headers.SERVER_SIDE_ENCRYPTION);
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.ServerSideEncryptionResult
    public void setSSEAlgorithm(String str) {
        this.metadata.put(Headers.SERVER_SIDE_ENCRYPTION, str);
    }

    @Deprecated
    public void setServerSideEncryption(String str) {
        this.metadata.put(Headers.SERVER_SIDE_ENCRYPTION, str);
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.ServerSideEncryptionResult
    public String getSSECustomerAlgorithm() {
        return (String) this.metadata.get(Headers.SERVER_SIDE_ENCRYPTION_CUSTOMER_ALGORITHM);
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.ServerSideEncryptionResult
    public void setSSECustomerAlgorithm(String str) {
        this.metadata.put(Headers.SERVER_SIDE_ENCRYPTION_CUSTOMER_ALGORITHM, str);
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.ServerSideEncryptionResult
    public String getSSECustomerKeyMd5() {
        return (String) this.metadata.get(Headers.SERVER_SIDE_ENCRYPTION_CUSTOMER_KEY_MD5);
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.ServerSideEncryptionResult
    public void setSSECustomerKeyMd5(String str) {
        this.metadata.put(Headers.SERVER_SIDE_ENCRYPTION_CUSTOMER_KEY_MD5, str);
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.ObjectExpirationResult
    public Date getExpirationTime() {
        return DateUtils.cloneDate(this.expirationTime);
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

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.ObjectRestoreResult
    public Date getRestoreExpirationTime() {
        return DateUtils.cloneDate(this.restoreExpirationTime);
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.ObjectRestoreResult
    public void setRestoreExpirationTime(Date date) {
        this.restoreExpirationTime = date;
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.ObjectRestoreResult
    public void setOngoingRestore(boolean z) {
        this.ongoingRestore = Boolean.valueOf(z);
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.ObjectRestoreResult
    public Boolean getOngoingRestore() {
        return this.ongoingRestore;
    }

    public void setHttpExpiresDate(Date date) {
        this.httpExpiresDate = date;
    }

    public Date getHttpExpiresDate() {
        return DateUtils.cloneDate(this.httpExpiresDate);
    }

    public void setStorageClass(StorageClass storageClass) {
        this.metadata.put(Headers.STORAGE_CLASS, storageClass);
    }

    public String getStorageClass() {
        Object obj = this.metadata.get(Headers.STORAGE_CLASS);
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public String getUserMetaDataOf(String str) {
        Map<String, String> map = this.userMetadata;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public ObjectMetadata m9clone() {
        return new ObjectMetadata(this);
    }

    public String getSSEAwsKmsKeyId() {
        return (String) this.metadata.get(Headers.SERVER_SIDE_ENCRYPTION_KMS_KEY_ID);
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.S3RequesterChargedResult
    public boolean isRequesterCharged() {
        return this.metadata.get(Headers.REQUESTER_CHARGED_HEADER) != null;
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.S3RequesterChargedResult
    public void setRequesterCharged(boolean z) {
        if (z) {
            this.metadata.put(Headers.REQUESTER_CHARGED_HEADER, Constants.REQUESTER_PAYS);
        }
    }

    public Integer getPartCount() {
        return (Integer) this.metadata.get(Headers.S3_PARTS_COUNT);
    }

    public Long[] getContentRange() {
        String str = (String) this.metadata.get(Headers.CONTENT_RANGE);
        if (str == null) {
            return null;
        }
        String[] split = str.split("[ -/]+");
        try {
            return new Long[]{Long.valueOf(Long.parseLong(split[1])), Long.valueOf(Long.parseLong(split[2]))};
        } catch (NumberFormatException e) {
            throw new AmazonClientException("Unable to parse content range. Header 'Content-Range' has corrupted data" + e.getMessage(), e);
        }
    }

    public String getReplicationStatus() {
        return (String) this.metadata.get(Headers.OBJECT_REPLICATION_STATUS);
    }
}
