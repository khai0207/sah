package com.netease.nimlib.amazonaws.auth;

/* loaded from: classes.dex */
public interface AWSIdentityProvider {
    String getToken();

    String refresh();
}
