package com.netease.nimlib.fusionstorage.crossplatform.defines;

import defpackage.C$r8$backportedMethods$utility$Objects$2$equals;
import java.util.Arrays;

/* loaded from: classes.dex */
public class Credential {
    private String accessKeyId;
    private String bucketName;
    private long expiredAt;
    private long fileExpiredAt;
    private String objectName;
    private int provider;
    private String region;
    private String scene;
    private String secretAccessKey;
    private String sessionToken;
    private String shortUrl;
    private String token;

    public Credential(int i, String str, String str2, String str3, String str4, String str5, String str6, long j, long j2, String str7, String str8, String str9) {
        this.provider = i;
        this.accessKeyId = str;
        this.secretAccessKey = str2;
        this.sessionToken = str3;
        this.token = str4;
        this.bucketName = str5;
        this.objectName = str6;
        this.fileExpiredAt = j;
        this.expiredAt = j2;
        this.scene = str7;
        this.shortUrl = str8;
        this.region = str9;
    }

    public int getProvider() {
        return this.provider;
    }

    public void setProvider(int i) {
        this.provider = i;
    }

    public String getAccessKeyId() {
        return this.accessKeyId;
    }

    public void setAccessKeyId(String str) {
        this.accessKeyId = str;
    }

    public String getSecretAccessKey() {
        return this.secretAccessKey;
    }

    public void setSecretAccessKey(String str) {
        this.secretAccessKey = str;
    }

    public String getSessionToken() {
        return this.sessionToken;
    }

    public void setSessionToken(String str) {
        this.sessionToken = str;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public String getObjectName() {
        return this.objectName;
    }

    public void setObjectName(String str) {
        this.objectName = str;
    }

    public long getFileExpiredAt() {
        return this.fileExpiredAt;
    }

    public void setFileExpiredAt(long j) {
        this.fileExpiredAt = j;
    }

    public long getExpiredAt() {
        return this.expiredAt;
    }

    public void setExpiredAt(long j) {
        this.expiredAt = j;
    }

    public String getScene() {
        return this.scene;
    }

    public void setScene(String str) {
        this.scene = str;
    }

    public String getShortUrl() {
        return this.shortUrl;
    }

    public void setShortUrl(String str) {
        this.shortUrl = str;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String str) {
        this.region = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Credential credential = (Credential) obj;
        return this.provider == credential.provider && this.fileExpiredAt == credential.fileExpiredAt && this.expiredAt == credential.expiredAt && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.accessKeyId, credential.accessKeyId) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.secretAccessKey, credential.secretAccessKey) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.sessionToken, credential.sessionToken) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.token, credential.token) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.bucketName, credential.bucketName) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.objectName, credential.objectName) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.scene, credential.scene) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.shortUrl, credential.shortUrl) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.region, credential.region);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.provider), this.accessKeyId, this.secretAccessKey, this.sessionToken, this.token, this.bucketName, this.objectName, Long.valueOf(this.fileExpiredAt), Long.valueOf(this.expiredAt), this.scene, this.shortUrl, this.region});
    }

    public String toString() {
        return "Credential{provider=" + this.provider + ", accessKeyId='" + this.accessKeyId + "', secretAccessKey='" + this.secretAccessKey + "', sessionToken='" + this.sessionToken + "', token='" + this.token + "', bucketName='" + this.bucketName + "', objectName='" + this.objectName + "', fileExpiredAt=" + this.fileExpiredAt + ", expiredAt=" + this.expiredAt + ", scene='" + this.scene + "', shortUrl='" + this.shortUrl + "', region='" + this.region + "'}";
    }
}
