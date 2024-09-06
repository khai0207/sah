package com.netease.nimlib.amazonaws.services.securitytoken.model;

import com.alipay.sdk.m.q.h;
import com.netease.nimlib.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* loaded from: classes.dex */
public class GetAccessKeyInfoRequest extends AmazonWebServiceRequest implements Serializable {
    private String accessKeyId;

    public String getAccessKeyId() {
        return this.accessKeyId;
    }

    public void setAccessKeyId(String str) {
        this.accessKeyId = str;
    }

    public GetAccessKeyInfoRequest withAccessKeyId(String str) {
        this.accessKeyId = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getAccessKeyId() != null) {
            sb.append("AccessKeyId: " + getAccessKeyId());
        }
        sb.append(h.d);
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getAccessKeyId() == null ? 0 : getAccessKeyId().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetAccessKeyInfoRequest)) {
            return false;
        }
        GetAccessKeyInfoRequest getAccessKeyInfoRequest = (GetAccessKeyInfoRequest) obj;
        if ((getAccessKeyInfoRequest.getAccessKeyId() == null) ^ (getAccessKeyId() == null)) {
            return false;
        }
        return getAccessKeyInfoRequest.getAccessKeyId() == null || getAccessKeyInfoRequest.getAccessKeyId().equals(getAccessKeyId());
    }
}
