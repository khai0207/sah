package com.netease.nimlib.amazonaws.services.cognitoidentity.model;

import com.alipay.sdk.m.q.h;
import java.io.Serializable;

/* loaded from: classes.dex */
public class MergeDeveloperIdentitiesResult implements Serializable {
    private String identityId;

    public String getIdentityId() {
        return this.identityId;
    }

    public void setIdentityId(String str) {
        this.identityId = str;
    }

    public MergeDeveloperIdentitiesResult withIdentityId(String str) {
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
        if (obj == null || !(obj instanceof MergeDeveloperIdentitiesResult)) {
            return false;
        }
        MergeDeveloperIdentitiesResult mergeDeveloperIdentitiesResult = (MergeDeveloperIdentitiesResult) obj;
        if ((mergeDeveloperIdentitiesResult.getIdentityId() == null) ^ (getIdentityId() == null)) {
            return false;
        }
        return mergeDeveloperIdentitiesResult.getIdentityId() == null || mergeDeveloperIdentitiesResult.getIdentityId().equals(getIdentityId());
    }
}
