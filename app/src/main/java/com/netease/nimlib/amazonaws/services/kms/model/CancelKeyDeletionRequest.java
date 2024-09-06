package com.netease.nimlib.amazonaws.services.kms.model;

import com.alipay.sdk.m.q.h;
import com.netease.nimlib.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* loaded from: classes.dex */
public class CancelKeyDeletionRequest extends AmazonWebServiceRequest implements Serializable {
    private String keyId;

    public String getKeyId() {
        return this.keyId;
    }

    public void setKeyId(String str) {
        this.keyId = str;
    }

    public CancelKeyDeletionRequest withKeyId(String str) {
        this.keyId = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getKeyId() != null) {
            sb.append("KeyId: " + getKeyId());
        }
        sb.append(h.d);
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getKeyId() == null ? 0 : getKeyId().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CancelKeyDeletionRequest)) {
            return false;
        }
        CancelKeyDeletionRequest cancelKeyDeletionRequest = (CancelKeyDeletionRequest) obj;
        if ((cancelKeyDeletionRequest.getKeyId() == null) ^ (getKeyId() == null)) {
            return false;
        }
        return cancelKeyDeletionRequest.getKeyId() == null || cancelKeyDeletionRequest.getKeyId().equals(getKeyId());
    }
}
