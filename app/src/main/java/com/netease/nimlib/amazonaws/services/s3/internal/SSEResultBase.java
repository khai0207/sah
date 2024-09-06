package com.netease.nimlib.amazonaws.services.s3.internal;

/* loaded from: classes.dex */
public abstract class SSEResultBase implements ServerSideEncryptionResult {
    private String sseAlgorithm;
    private String sseCustomerAlgorithm;
    private String sseCustomerKeyMD5;

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.ServerSideEncryptionResult
    public final String getSSEAlgorithm() {
        return this.sseAlgorithm;
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.ServerSideEncryptionResult
    public final void setSSEAlgorithm(String str) {
        this.sseAlgorithm = str;
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.ServerSideEncryptionResult
    public final String getSSECustomerAlgorithm() {
        return this.sseCustomerAlgorithm;
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.ServerSideEncryptionResult
    public final void setSSECustomerAlgorithm(String str) {
        this.sseCustomerAlgorithm = str;
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.ServerSideEncryptionResult
    public final String getSSECustomerKeyMd5() {
        return this.sseCustomerKeyMD5;
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.ServerSideEncryptionResult
    public final void setSSECustomerKeyMd5(String str) {
        this.sseCustomerKeyMD5 = str;
    }

    @Deprecated
    public final String getServerSideEncryption() {
        return this.sseAlgorithm;
    }
}
