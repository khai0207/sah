package com.netease.nimlib.sdk.nos.model;

import java.io.Serializable;

/* loaded from: classes.dex */
public class NosTransferProgress implements Serializable {
    private final String key;
    private final long total;
    private final long transferred;

    public NosTransferProgress(String str, long j, long j2) {
        this.key = str;
        this.transferred = j;
        this.total = j2;
    }

    public String getKey() {
        return this.key;
    }

    public long getTransferred() {
        return this.transferred;
    }

    public long getTotal() {
        return this.total;
    }
}
