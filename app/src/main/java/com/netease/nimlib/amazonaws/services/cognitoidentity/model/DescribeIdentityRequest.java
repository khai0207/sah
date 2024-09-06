package com.netease.nimlib.amazonaws.services.cognitoidentity.model;

import com.alipay.sdk.m.q.h;
import com.netease.nimlib.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* loaded from: classes.dex */
public class DescribeIdentityRequest extends AmazonWebServiceRequest implements Serializable {
    private String identityId;

    public String getIdentityId() {
        return this.identityId;
    }

    public void setIdentityId(String str) {
        this.identityId = str;
    }

    public DescribeIdentityRequest withIdentityId(String str) {
        this.identityId = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getIdentityId() != null) {
            sb.append("IdentityId: " + getIdentityId());
        }
        sb.append(h.d);
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getIdentityId() == null ? 0 : getIdentityId().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeIdentityRequest)) {
            return false;
        }
        DescribeIdentityRequest describeIdentityRequest = (DescribeIdentityRequest) obj;
        if ((describeIdentityRequest.getIdentityId() == null) ^ (getIdentityId() == null)) {
            return false;
        }
        return describeIdentityRequest.getIdentityId() == null || describeIdentityRequest.getIdentityId().equals(getIdentityId());
    }
}
