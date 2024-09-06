package com.netease.nimlib.amazonaws.services.kms.model;

import com.alipay.sdk.m.q.h;
import com.netease.nimlib.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* loaded from: classes.dex */
public class DeleteCustomKeyStoreRequest extends AmazonWebServiceRequest implements Serializable {
    private String customKeyStoreId;

    public String getCustomKeyStoreId() {
        return this.customKeyStoreId;
    }

    public void setCustomKeyStoreId(String str) {
        this.customKeyStoreId = str;
    }

    public DeleteCustomKeyStoreRequest withCustomKeyStoreId(String str) {
        this.customKeyStoreId = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCustomKeyStoreId() != null) {
            sb.append("CustomKeyStoreId: " + getCustomKeyStoreId());
        }
        sb.append(h.d);
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getCustomKeyStoreId() == null ? 0 : getCustomKeyStoreId().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteCustomKeyStoreRequest)) {
            return false;
        }
        DeleteCustomKeyStoreRequest deleteCustomKeyStoreRequest = (DeleteCustomKeyStoreRequest) obj;
        if ((deleteCustomKeyStoreRequest.getCustomKeyStoreId() == null) ^ (getCustomKeyStoreId() == null)) {
            return false;
        }
        return deleteCustomKeyStoreRequest.getCustomKeyStoreId() == null || deleteCustomKeyStoreRequest.getCustomKeyStoreId().equals(getCustomKeyStoreId());
    }
}
