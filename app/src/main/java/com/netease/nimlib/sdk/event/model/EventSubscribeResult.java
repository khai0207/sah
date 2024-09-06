package com.netease.nimlib.sdk.event.model;

/* loaded from: classes.dex */
public class EventSubscribeResult {
    protected int eventType;
    protected long expiry;
    protected String publisherAccount;
    protected long time;

    public int getEventType() {
        return this.eventType;
    }

    public long getExpiry() {
        return this.expiry;
    }

    public long getTime() {
        return this.time;
    }

    public String getPublisherAccount() {
        return this.publisherAccount;
    }
}
