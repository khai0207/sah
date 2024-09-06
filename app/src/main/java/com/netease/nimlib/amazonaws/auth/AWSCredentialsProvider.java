package com.netease.nimlib.amazonaws.auth;

/* loaded from: classes.dex */
public interface AWSCredentialsProvider {
    AWSCredentials getCredentials();

    void refresh();
}
