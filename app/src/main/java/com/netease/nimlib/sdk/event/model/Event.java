package com.netease.nimlib.sdk.event.model;

import java.io.Serializable;
import java.util.Map;

/* loaded from: classes.dex */
public class Event implements Serializable {
    protected boolean broadcastOnlineOnly;
    protected String config;
    protected String eventId;
    protected int eventType;
    protected int eventValue;
    protected long expiry;
    protected String multiClientConfig;
    protected Map<Integer, String> multiClientConfigMap;
    protected String nimConfig;
    protected long publishTime;
    protected String publisherAccount;
    protected int publisherClientType;
    protected boolean syncSelfEnable;

    protected Event() {
    }

    public Event(int i, int i2, long j) {
        this.eventType = i;
        this.eventValue = i2;
        this.expiry = j;
    }

    public void setEventType(int i) {
        this.eventType = i;
    }

    public void setEventValue(int i) {
        this.eventValue = i;
    }

    public void setConfig(String str) {
        this.config = str;
    }

    public void setBroadcastOnlineOnly(boolean z) {
        this.broadcastOnlineOnly = z;
    }

    public void setExpiry(long j) {
        this.expiry = j;
    }

    public void setSyncSelfEnable(boolean z) {
        this.syncSelfEnable = z;
    }

    public String getEventId() {
        return this.eventId;
    }

    public int getEventType() {
        return this.eventType;
    }

    public int getEventValue() {
        return this.eventValue;
    }

    public String getConfig() {
        return this.config;
    }

    public long getExpiry() {
        return this.expiry;
    }

    public boolean isBroadcastOnlineOnly() {
        return this.broadcastOnlineOnly;
    }

    public boolean isSyncSelfEnable() {
        return this.syncSelfEnable;
    }

    public String getPublisherAccount() {
        return this.publisherAccount;
    }

    public long getPublishTime() {
        return this.publishTime;
    }

    public int getPublisherClientType() {
        return this.publisherClientType;
    }

    public String getMultiClientConfig() {
        return this.multiClientConfig;
    }

    public String getConfigByClient(int i) {
        Map<Integer, String> map = this.multiClientConfigMap;
        if (map == null) {
            return null;
        }
        return map.get(Integer.valueOf(i));
    }

    public String getNimConfig() {
        return this.nimConfig;
    }
}
