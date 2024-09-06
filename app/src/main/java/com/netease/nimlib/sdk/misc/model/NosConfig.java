package com.netease.nimlib.sdk.misc.model;

import android.text.TextUtils;

/* loaded from: classes.dex */
public class NosConfig {
    private final String bucket;
    private final String cdnDomain;
    private final long deadline;
    private final String objectNamePrefix;

    public NosConfig(String str, String str2, long j, String str3) {
        this.bucket = str;
        this.cdnDomain = str2;
        this.deadline = j;
        this.objectNamePrefix = str3;
    }

    public String getBucket() {
        return this.bucket;
    }

    public String getCdnDomain() {
        return this.cdnDomain;
    }

    public long getDeadline() {
        return this.deadline;
    }

    public String getObjectNamePrefix() {
        return this.objectNamePrefix;
    }

    public boolean isValid() {
        return (this.deadline == 0 || TextUtils.isEmpty(this.cdnDomain)) ? false : true;
    }

    public boolean isValidForever() {
        return this.deadline == -1;
    }

    public String toString() {
        return "NosConfig{bucket='" + this.bucket + "', cdnDomain='" + this.cdnDomain + "', deadline=" + this.deadline + ", objectNamePrefix='" + this.objectNamePrefix + "'}";
    }
}
