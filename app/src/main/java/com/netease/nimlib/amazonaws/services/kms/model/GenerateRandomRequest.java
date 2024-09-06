package com.netease.nimlib.amazonaws.services.kms.model;

import com.alipay.sdk.m.q.h;
import com.netease.nimlib.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* loaded from: classes.dex */
public class GenerateRandomRequest extends AmazonWebServiceRequest implements Serializable {
    private String customKeyStoreId;
    private Integer numberOfBytes;
    private RecipientInfo recipient;

    public Integer getNumberOfBytes() {
        return this.numberOfBytes;
    }

    public void setNumberOfBytes(Integer num) {
        this.numberOfBytes = num;
    }

    public GenerateRandomRequest withNumberOfBytes(Integer num) {
        this.numberOfBytes = num;
        return this;
    }

    public String getCustomKeyStoreId() {
        return this.customKeyStoreId;
    }

    public void setCustomKeyStoreId(String str) {
        this.customKeyStoreId = str;
    }

    public GenerateRandomRequest withCustomKeyStoreId(String str) {
        this.customKeyStoreId = str;
        return this;
    }

    public RecipientInfo getRecipient() {
        return this.recipient;
    }

    public void setRecipient(RecipientInfo recipientInfo) {
        this.recipient = recipientInfo;
    }

    public GenerateRandomRequest withRecipient(RecipientInfo recipientInfo) {
        this.recipient = recipientInfo;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getNumberOfBytes() != null) {
            sb.append("NumberOfBytes: " + getNumberOfBytes() + ",");
        }
        if (getCustomKeyStoreId() != null) {
            sb.append("CustomKeyStoreId: " + getCustomKeyStoreId() + ",");
        }
        if (getRecipient() != null) {
            sb.append("Recipient: " + getRecipient());
        }
        sb.append(h.d);
        return sb.toString();
    }

    public int hashCode() {
        return (((((getNumberOfBytes() == null ? 0 : getNumberOfBytes().hashCode()) + 31) * 31) + (getCustomKeyStoreId() == null ? 0 : getCustomKeyStoreId().hashCode())) * 31) + (getRecipient() != null ? getRecipient().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GenerateRandomRequest)) {
            return false;
        }
        GenerateRandomRequest generateRandomRequest = (GenerateRandomRequest) obj;
        if ((generateRandomRequest.getNumberOfBytes() == null) ^ (getNumberOfBytes() == null)) {
            return false;
        }
        if (generateRandomRequest.getNumberOfBytes() != null && !generateRandomRequest.getNumberOfBytes().equals(getNumberOfBytes())) {
            return false;
        }
        if ((generateRandomRequest.getCustomKeyStoreId() == null) ^ (getCustomKeyStoreId() == null)) {
            return false;
        }
        if (generateRandomRequest.getCustomKeyStoreId() != null && !generateRandomRequest.getCustomKeyStoreId().equals(getCustomKeyStoreId())) {
            return false;
        }
        if ((generateRandomRequest.getRecipient() == null) ^ (getRecipient() == null)) {
            return false;
        }
        return generateRandomRequest.getRecipient() == null || generateRandomRequest.getRecipient().equals(getRecipient());
    }
}
