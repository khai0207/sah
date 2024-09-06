package com.netease.nimlib.amazonaws.services.kms.model;

import com.alipay.sdk.m.q.h;
import java.io.Serializable;

/* loaded from: classes.dex */
public class XksKeyConfigurationType implements Serializable {
    private String id;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public XksKeyConfigurationType withId(String str) {
        this.id = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getId() != null) {
            sb.append("Id: " + getId());
        }
        sb.append(h.d);
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getId() == null ? 0 : getId().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof XksKeyConfigurationType)) {
            return false;
        }
        XksKeyConfigurationType xksKeyConfigurationType = (XksKeyConfigurationType) obj;
        if ((xksKeyConfigurationType.getId() == null) ^ (getId() == null)) {
            return false;
        }
        return xksKeyConfigurationType.getId() == null || xksKeyConfigurationType.getId().equals(getId());
    }
}
