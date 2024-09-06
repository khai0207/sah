package com.netease.nimlib.sdk.msg.model;

import java.io.Serializable;

/* loaded from: classes.dex */
public class AttachmentProgress implements Serializable {
    private final long total;
    private final long transferred;
    private final String uuid;

    public AttachmentProgress(String str, long j, long j2) {
        this.uuid = str;
        this.transferred = j;
        this.total = j2;
    }

    public String getUuid() {
        return this.uuid;
    }

    public long getTransferred() {
        return this.transferred;
    }

    public long getTotal() {
        return this.total;
    }
}
