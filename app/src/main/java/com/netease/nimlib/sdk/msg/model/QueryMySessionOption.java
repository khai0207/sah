package com.netease.nimlib.sdk.msg.model;

import java.io.Serializable;

/* loaded from: classes.dex */
public class QueryMySessionOption implements Serializable {
    private int limit;
    private long maxTimestamp;
    private long minTimestamp;
    private boolean needLastMsg;

    public QueryMySessionOption() {
        this.minTimestamp = 0L;
        this.maxTimestamp = 0L;
        this.needLastMsg = true;
        this.limit = 100;
    }

    public QueryMySessionOption(long j, long j2, boolean z, int i) {
        this.minTimestamp = j;
        this.maxTimestamp = j2;
        this.needLastMsg = z;
        this.limit = i;
    }

    public long getMinTimestamp() {
        return this.minTimestamp;
    }

    public void setMinTimestamp(long j) {
        this.minTimestamp = j;
    }

    public long getMaxTimestamp() {
        return this.maxTimestamp;
    }

    public void setMaxTimestamp(long j) {
        this.maxTimestamp = j;
    }

    public boolean isNeedLastMsg() {
        return this.needLastMsg;
    }

    public void setNeedLastMsg(boolean z) {
        this.needLastMsg = z;
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int i) {
        this.limit = i;
    }

    public String toString() {
        return "QueryMySessionOption{minTimestamp=" + this.minTimestamp + ", maxTimestamp=" + this.maxTimestamp + ", needLastMsg=" + this.needLastMsg + ", limit=" + this.limit + '}';
    }
}
