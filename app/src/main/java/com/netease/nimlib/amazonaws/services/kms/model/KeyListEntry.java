package com.netease.nimlib.amazonaws.services.kms.model;

import com.alipay.sdk.m.q.h;
import java.io.Serializable;

/* loaded from: classes.dex */
public class KeyListEntry implements Serializable {
    private String keyArn;
    private String keyId;

    public String getKeyId() {
        return this.keyId;
    }

    public void setKeyId(String str) {
        this.keyId = str;
    }

    public KeyListEntry withKeyId(String str) {
        this.keyId = str;
        return this;
    }

    public String getKeyArn() {
        return this.keyArn;
    }

    public void setKeyArn(String str) {
        this.keyArn = str;
    }

    public KeyListEntry withKeyArn(String str) {
        this.keyArn = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getKeyId() != null) {
            sb.append("KeyId: " + getKeyId() + ",");
        }
        if (getKeyArn() != null) {
            sb.append("KeyArn: " + getKeyArn());
        }
        sb.append(h.d);
        return sb.toString();
    }

    public int hashCode() {
        return (((getKeyId() == null ? 0 : getKeyId().hashCode()) + 31) * 31) + (getKeyArn() != null ? getKeyArn().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof KeyListEntry)) {
            return false;
        }
        KeyListEntry keyListEntry = (KeyListEntry) obj;
        if ((keyListEntry.getKeyId() == null) ^ (getKeyId() == null)) {
            return false;
        }
        if (keyListEntry.getKeyId() != null && !keyListEntry.getKeyId().equals(getKeyId())) {
            return false;
        }
        if ((keyListEntry.getKeyArn() == null) ^ (getKeyArn() == null)) {
            return false;
        }
        return keyListEntry.getKeyArn() == null || keyListEntry.getKeyArn().equals(getKeyArn());
    }
}
