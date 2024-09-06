package com.netease.nimlib.amazonaws.internal;

import com.netease.nimlib.amazonaws.auth.AWSCredentials;
import com.netease.nimlib.amazonaws.auth.AWSCredentialsProvider;

/* loaded from: classes.dex */
public class StaticCredentialsProvider implements AWSCredentialsProvider {
    private final AWSCredentials credentials;

    @Override // com.netease.nimlib.amazonaws.auth.AWSCredentialsProvider
    public void refresh() {
    }

    public StaticCredentialsProvider(AWSCredentials aWSCredentials) {
        this.credentials = aWSCredentials;
    }

    @Override // com.netease.nimlib.amazonaws.auth.AWSCredentialsProvider
    public AWSCredentials getCredentials() {
        return this.credentials;
    }
}
