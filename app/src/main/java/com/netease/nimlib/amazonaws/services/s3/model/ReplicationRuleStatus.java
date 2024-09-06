package com.netease.nimlib.amazonaws.services.s3.model;

/* loaded from: classes.dex */
public enum ReplicationRuleStatus {
    Enabled("Enabled"),
    Disabled(BucketLifecycleConfiguration.DISABLED);

    private final String status;

    ReplicationRuleStatus(String str) {
        this.status = str;
    }

    public String getStatus() {
        return this.status;
    }
}
