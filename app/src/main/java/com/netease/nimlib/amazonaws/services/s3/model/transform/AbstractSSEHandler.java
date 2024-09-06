package com.netease.nimlib.amazonaws.services.s3.model.transform;

import com.netease.nimlib.amazonaws.services.s3.internal.ServerSideEncryptionResult;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class AbstractSSEHandler extends AbstractHandler implements ServerSideEncryptionResult {
    protected abstract ServerSideEncryptionResult sseResult();

    AbstractSSEHandler() {
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.ServerSideEncryptionResult
    public final String getSSEAlgorithm() {
        ServerSideEncryptionResult sseResult = sseResult();
        if (sseResult == null) {
            return null;
        }
        return sseResult.getSSEAlgorithm();
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.ServerSideEncryptionResult
    public final void setSSEAlgorithm(String str) {
        ServerSideEncryptionResult sseResult = sseResult();
        if (sseResult != null) {
            sseResult.setSSEAlgorithm(str);
        }
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.ServerSideEncryptionResult
    public final String getSSECustomerAlgorithm() {
        ServerSideEncryptionResult sseResult = sseResult();
        if (sseResult == null) {
            return null;
        }
        return sseResult.getSSECustomerAlgorithm();
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.ServerSideEncryptionResult
    public final void setSSECustomerAlgorithm(String str) {
        ServerSideEncryptionResult sseResult = sseResult();
        if (sseResult != null) {
            sseResult.setSSECustomerAlgorithm(str);
        }
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.ServerSideEncryptionResult
    public final String getSSECustomerKeyMd5() {
        ServerSideEncryptionResult sseResult = sseResult();
        if (sseResult == null) {
            return null;
        }
        return sseResult.getSSECustomerKeyMd5();
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.ServerSideEncryptionResult
    public final void setSSECustomerKeyMd5(String str) {
        ServerSideEncryptionResult sseResult = sseResult();
        if (sseResult != null) {
            sseResult.setSSECustomerKeyMd5(str);
        }
    }
}
