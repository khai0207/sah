package com.netease.nimlib.amazonaws.services.kms.model;

import com.alipay.sdk.m.q.h;
import java.io.Serializable;

/* loaded from: classes.dex */
public class GetKeyRotationStatusResult implements Serializable {
    private Boolean keyRotationEnabled;

    public Boolean isKeyRotationEnabled() {
        return this.keyRotationEnabled;
    }

    public Boolean getKeyRotationEnabled() {
        return this.keyRotationEnabled;
    }

    public void setKeyRotationEnabled(Boolean bool) {
        this.keyRotationEnabled = bool;
    }

    public GetKeyRotationStatusResult withKeyRotationEnabled(Boolean bool) {
        this.keyRotationEnabled = bool;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getKeyRotationEnabled() != null) {
            sb.append("KeyRotationEnabled: " + getKeyRotationEnabled());
        }
        sb.append(h.d);
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getKeyRotationEnabled() == null ? 0 : getKeyRotationEnabled().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetKeyRotationStatusResult)) {
            return false;
        }
        GetKeyRotationStatusResult getKeyRotationStatusResult = (GetKeyRotationStatusResult) obj;
        if ((getKeyRotationStatusResult.getKeyRotationEnabled() == null) ^ (getKeyRotationEnabled() == null)) {
            return false;
        }
        return getKeyRotationStatusResult.getKeyRotationEnabled() == null || getKeyRotationStatusResult.getKeyRotationEnabled().equals(getKeyRotationEnabled());
    }
}
