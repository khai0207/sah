package com.netease.nimlib.amazonaws.auth;

import com.netease.nimlib.amazonaws.Request;

/* loaded from: classes.dex */
public interface Signer {
    void sign(Request<?> request, AWSCredentials aWSCredentials);
}
