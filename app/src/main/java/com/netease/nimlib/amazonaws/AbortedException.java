package com.netease.nimlib.amazonaws;

/* loaded from: classes.dex */
public class AbortedException extends AmazonClientException {
    private static final long serialVersionUID = 1;

    @Override // com.netease.nimlib.amazonaws.AmazonClientException
    public boolean isRetryable() {
        return false;
    }

    public AbortedException(String str, Throwable th) {
        super(str, th);
    }

    public AbortedException(Throwable th) {
        super("", th);
    }

    public AbortedException(String str) {
        super(str);
    }

    public AbortedException() {
        super("");
    }
}
