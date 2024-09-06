package com.netease.nimlib.amazonaws.services.kms.model;

import com.alipay.sdk.m.q.h;
import com.netease.nimlib.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* loaded from: classes.dex */
public class DeleteImportedKeyMaterialRequest extends AmazonWebServiceRequest implements Serializable {
    private String keyId;

    public String getKeyId() {
        return this.keyId;
    }

    public void setKeyId(String str) {
        this.keyId = str;
    }

    public DeleteImportedKeyMaterialRequest withKeyId(String str) {
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
        if (obj == null || !(obj instanceof DeleteImportedKeyMaterialRequest)) {
            return false;
        }
        DeleteImportedKeyMaterialRequest deleteImportedKeyMaterialRequest = (DeleteImportedKeyMaterialRequest) obj;
        if ((deleteImportedKeyMaterialRequest.getKeyId() == null) ^ (getKeyId() == null)) {
            return false;
        }
        return deleteImportedKeyMaterialRequest.getKeyId() == null || deleteImportedKeyMaterialRequest.getKeyId().equals(getKeyId());
    }
}
