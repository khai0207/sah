package com.netease.nimlib.amazonaws.auth;

/* loaded from: classes.dex */
public interface AWSRefreshableSessionCredentials extends AWSSessionCredentials {
    void refreshCredentials();
}
