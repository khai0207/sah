package com.netease.nimlib.fusionstorage.crossplatform.defines;

import com.alipay.sdk.app.OpenAuthTask;

/* loaded from: classes.dex */
public class DownloadParameter {
    private AuthPolicy authPolicy;
    private String downloadUrl;
    private int provider;
    private int type;

    public int getDownloadRetryCount() {
        return 3;
    }

    public int getDownloadRetryInterval() {
        return OpenAuthTask.Duplex;
    }

    public DownloadParameter(String str, int i, AuthPolicy authPolicy, int i2) {
        this.downloadUrl = str;
        this.type = i;
        this.authPolicy = authPolicy;
        this.provider = i2;
    }

    public String getDownloadUrl() {
        return this.downloadUrl;
    }

    public void setDownloadUrl(String str) {
        this.downloadUrl = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public AuthPolicy getAuthPolicy() {
        return this.authPolicy;
    }

    public void setAuthPolicy(AuthPolicy authPolicy) {
        this.authPolicy = authPolicy;
    }

    public int getProvider() {
        return this.provider;
    }

    public void setProvider(int i) {
        this.provider = i;
    }

    public boolean needAuth() {
        return (getAuthPolicy() == null || !getAuthPolicy().isAuthEnabled() || getAuthPolicy().getPolicyType() == 0) ? false : true;
    }

    public String toString() {
        return "DownloadParameter{downloadUrl='" + this.downloadUrl + "', type=" + this.type + ", authPolicy=" + this.authPolicy + ", provider=" + this.provider + '}';
    }
}
