package com.netease.nimlib.amazonaws.services.cognitoidentity.model;

import com.alipay.sdk.m.q.h;
import com.netease.nimlib.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* loaded from: classes.dex */
public class DeleteIdentityPoolRequest extends AmazonWebServiceRequest implements Serializable {
    private String identityPoolId;

    public String getIdentityPoolId() {
        return this.identityPoolId;
    }

    public void setIdentityPoolId(String str) {
        this.identityPoolId = str;
    }

    public DeleteIdentityPoolRequest withIdentityPoolId(String str) {
        this.identityPoolId = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getIdentityPoolId() != null) {
            sb.append("IdentityPoolId: " + getIdentityPoolId());
        }
        sb.append(h.d);
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getIdentityPoolId() == null ? 0 : getIdentityPoolId().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteIdentityPoolRequest)) {
            return false;
        }
        DeleteIdentityPoolRequest deleteIdentityPoolRequest = (DeleteIdentityPoolRequest) obj;
        if ((deleteIdentityPoolRequest.getIdentityPoolId() == null) ^ (getIdentityPoolId() == null)) {
            return false;
        }
        return deleteIdentityPoolRequest.getIdentityPoolId() == null || deleteIdentityPoolRequest.getIdentityPoolId().equals(getIdentityPoolId());
    }
}
