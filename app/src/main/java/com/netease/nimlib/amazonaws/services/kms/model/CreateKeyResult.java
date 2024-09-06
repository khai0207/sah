package com.netease.nimlib.amazonaws.services.kms.model;

import com.alipay.sdk.m.q.h;
import java.io.Serializable;

/* loaded from: classes.dex */
public class CreateKeyResult implements Serializable {
    private KeyMetadata keyMetadata;

    public KeyMetadata getKeyMetadata() {
        return this.keyMetadata;
    }

    public void setKeyMetadata(KeyMetadata keyMetadata) {
        this.keyMetadata = keyMetadata;
    }

    public CreateKeyResult withKeyMetadata(KeyMetadata keyMetadata) {
        this.keyMetadata = keyMetadata;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getKeyMetadata() != null) {
            sb.append("KeyMetadata: " + getKeyMetadata());
        }
        sb.append(h.d);
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getKeyMetadata() == null ? 0 : getKeyMetadata().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateKeyResult)) {
            return false;
        }
        CreateKeyResult createKeyResult = (CreateKeyResult) obj;
        if ((createKeyResult.getKeyMetadata() == null) ^ (getKeyMetadata() == null)) {
            return false;
        }
        return createKeyResult.getKeyMetadata() == null || createKeyResult.getKeyMetadata().equals(getKeyMetadata());
    }
}
