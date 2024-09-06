package com.netease.nimlib.amazonaws.auth;

/* loaded from: classes.dex */
public class BasicAWSCredentials implements AWSCredentials {
    private final String accessKey;
    private final String secretKey;

    public BasicAWSCredentials(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("Access key cannot be null.");
        }
        if (str2 == null) {
            throw new IllegalArgumentException("Secret key cannot be null.");
        }
        this.accessKey = str;
        this.secretKey = str2;
    }

    @Override // com.netease.nimlib.amazonaws.auth.AWSCredentials
    public String getAWSAccessKeyId() {
        return this.accessKey;
    }

    @Override // com.netease.nimlib.amazonaws.auth.AWSCredentials
    public String getAWSSecretKey() {
        return this.secretKey;
    }
}
