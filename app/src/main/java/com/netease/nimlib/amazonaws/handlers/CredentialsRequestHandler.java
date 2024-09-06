package com.netease.nimlib.amazonaws.handlers;

import com.netease.nimlib.amazonaws.auth.AWSCredentials;

/* loaded from: classes.dex */
public abstract class CredentialsRequestHandler extends RequestHandler2 {
    protected AWSCredentials awsCredentials;

    public void setCredentials(AWSCredentials aWSCredentials) {
        this.awsCredentials = aWSCredentials;
    }
}
