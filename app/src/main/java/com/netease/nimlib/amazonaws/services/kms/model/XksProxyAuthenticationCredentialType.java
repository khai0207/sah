package com.netease.nimlib.amazonaws.services.kms.model;

import com.alipay.sdk.m.q.h;
import java.io.Serializable;

/* loaded from: classes.dex */
public class XksProxyAuthenticationCredentialType implements Serializable {
    private String accessKeyId;
    private String rawSecretAccessKey;

    public String getAccessKeyId() {
        return this.accessKeyId;
    }

    public void setAccessKeyId(String str) {
        this.accessKeyId = str;
    }

    public XksProxyAuthenticationCredentialType withAccessKeyId(String str) {
        this.accessKeyId = str;
        return this;
    }

    public String getRawSecretAccessKey() {
        return this.rawSecretAccessKey;
    }

    public void setRawSecretAccessKey(String str) {
        this.rawSecretAccessKey = str;
    }

    public XksProxyAuthenticationCredentialType withRawSecretAccessKey(String str) {
        this.rawSecretAccessKey = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getAccessKeyId() != null) {
            sb.append("AccessKeyId: " + getAccessKeyId() + ",");
        }
        if (getRawSecretAccessKey() != null) {
            sb.append("RawSecretAccessKey: " + getRawSecretAccessKey());
        }
        sb.append(h.d);
        return sb.toString();
    }

    public int hashCode() {
        return (((getAccessKeyId() == null ? 0 : getAccessKeyId().hashCode()) + 31) * 31) + (getRawSecretAccessKey() != null ? getRawSecretAccessKey().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof XksProxyAuthenticationCredentialType)) {
            return false;
        }
        XksProxyAuthenticationCredentialType xksProxyAuthenticationCredentialType = (XksProxyAuthenticationCredentialType) obj;
        if ((xksProxyAuthenticationCredentialType.getAccessKeyId() == null) ^ (getAccessKeyId() == null)) {
            return false;
        }
        if (xksProxyAuthenticationCredentialType.getAccessKeyId() != null && !xksProxyAuthenticationCredentialType.getAccessKeyId().equals(getAccessKeyId())) {
            return false;
        }
        if ((xksProxyAuthenticationCredentialType.getRawSecretAccessKey() == null) ^ (getRawSecretAccessKey() == null)) {
            return false;
        }
        return xksProxyAuthenticationCredentialType.getRawSecretAccessKey() == null || xksProxyAuthenticationCredentialType.getRawSecretAccessKey().equals(getRawSecretAccessKey());
    }
}
