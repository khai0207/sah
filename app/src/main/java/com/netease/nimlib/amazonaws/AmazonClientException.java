package com.netease.nimlib.amazonaws;

/* loaded from: classes.dex */
public class AmazonClientException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public boolean isRetryable() {
        return true;
    }

    public AmazonClientException(String str, Throwable th) {
        super(str, th);
    }

    public AmazonClientException(String str) {
        super(str);
    }

    public AmazonClientException(Throwable th) {
        super(th);
    }
}
