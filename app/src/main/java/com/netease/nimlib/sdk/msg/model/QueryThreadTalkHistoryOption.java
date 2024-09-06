package com.netease.nimlib.sdk.msg.model;

import java.io.Serializable;

/* loaded from: classes.dex */
public class QueryThreadTalkHistoryOption implements Serializable {
    private QueryDirectionEnum direction;
    private long excludeMessageServerId;
    private long fromTime;
    private int limit;
    private boolean persist;
    private long toTime;

    public long getFromTime() {
        return this.fromTime;
    }

    public void setFromTime(long j) {
        this.fromTime = j;
    }

    public long getToTime() {
        return this.toTime;
    }

    public void setToTime(long j) {
        this.toTime = j;
    }

    public long getExcludeMessageServerId() {
        return this.excludeMessageServerId;
    }

    public void setExcludeMessageServerId(long j) {
        this.excludeMessageServerId = j;
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int i) {
        this.limit = i;
    }

    public QueryDirectionEnum getDirection() {
        return this.direction;
    }

    public void setDirection(QueryDirectionEnum queryDirectionEnum) {
        this.direction = queryDirectionEnum;
    }

    public boolean isPersist() {
        return this.persist;
    }

    public void setPersist(boolean z) {
        this.persist = z;
    }

    public boolean isValid() {
        long j = this.fromTime;
        return j >= 0 && this.toTime >= j && this.excludeMessageServerId >= 0 && this.limit >= 0;
    }

    public String toString() {
        return "QueryThreadTalkHistoryOption{fromTime=" + this.fromTime + ", toTime=" + this.toTime + ", excludeMessageServerId=" + this.excludeMessageServerId + ", limit=" + this.limit + ", direction=" + this.direction + ", persist=" + this.persist + '}';
    }
}
