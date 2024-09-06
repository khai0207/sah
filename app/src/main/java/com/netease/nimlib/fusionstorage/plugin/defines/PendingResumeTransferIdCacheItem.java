package com.netease.nimlib.fusionstorage.plugin.defines;

import com.netease.nimlib.fusionstorage.crossplatform.defines.UploadParameter;

/* loaded from: classes.dex */
public class PendingResumeTransferIdCacheItem {
    private final int transferId;
    private final UploadParameter uploadParameter;

    public PendingResumeTransferIdCacheItem(int i, UploadParameter uploadParameter) {
        this.transferId = i;
        this.uploadParameter = uploadParameter;
    }

    public int getTransferId() {
        return this.transferId;
    }

    public UploadParameter getUploadParameter() {
        return this.uploadParameter;
    }
}
