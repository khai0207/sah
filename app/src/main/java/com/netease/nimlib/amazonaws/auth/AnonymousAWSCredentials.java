package com.netease.nimlib.amazonaws.auth;

/* loaded from: classes.dex */
public class AnonymousAWSCredentials implements AWSCredentials {
    @Override // com.netease.nimlib.amazonaws.auth.AWSCredentials
    public String getAWSAccessKeyId() {
        return null;
    }

    @Override // com.netease.nimlib.amazonaws.auth.AWSCredentials
    public String getAWSSecretKey() {
        return null;
    }
}
