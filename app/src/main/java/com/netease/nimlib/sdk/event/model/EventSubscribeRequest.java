package com.netease.nimlib.sdk.event.model;

import java.util.List;

/* loaded from: classes.dex */
public class EventSubscribeRequest {
    private int eventType;
    private long expiry;
    private List<String> publishers;
    private boolean syncCurrentValue;

    public void setEventType(int i) {
        this.eventType = i;
    }

    public void setExpiry(long j) {
        this.expiry = j;
    }

    public void setSyncCurrentValue(boolean z) {
        this.syncCurrentValue = z;
    }

    public void setPublishers(List<String> list) {
        this.publishers = list;
    }

    public int getEventType() {
        return this.eventType;
    }

    public boolean isSyncCurrentValue() {
        return this.syncCurrentValue;
    }

    public List<String> getPublishers() {
        return this.publishers;
    }

    public long getExpiry() {
        return this.expiry;
    }
}
