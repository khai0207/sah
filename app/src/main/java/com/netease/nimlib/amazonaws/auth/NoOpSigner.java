package com.netease.nimlib.amazonaws.auth;

import com.netease.nimlib.amazonaws.Request;

/* loaded from: classes.dex */
public class NoOpSigner implements Signer {
    @Override // com.netease.nimlib.amazonaws.auth.Signer
    public void sign(Request<?> request, AWSCredentials aWSCredentials) {
    }
}
