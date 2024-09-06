package com.netease.nimlib.amazonaws.services.kms.model;

import com.alipay.sdk.m.q.h;
import java.io.Serializable;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public class GenerateRandomResult implements Serializable {
    private ByteBuffer ciphertextForRecipient;
    private ByteBuffer plaintext;

    public ByteBuffer getPlaintext() {
        return this.plaintext;
    }

    public void setPlaintext(ByteBuffer byteBuffer) {
        this.plaintext = byteBuffer;
    }

    public GenerateRandomResult withPlaintext(ByteBuffer byteBuffer) {
        this.plaintext = byteBuffer;
        return this;
    }

    public ByteBuffer getCiphertextForRecipient() {
        return this.ciphertextForRecipient;
    }

    public void setCiphertextForRecipient(ByteBuffer byteBuffer) {
        this.ciphertextForRecipient = byteBuffer;
    }

    public GenerateRandomResult withCiphertextForRecipient(ByteBuffer byteBuffer) {
        this.ciphertextForRecipient = byteBuffer;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getPlaintext() != null) {
            sb.append("Plaintext: " + getPlaintext() + ",");
        }
        if (getCiphertextForRecipient() != null) {
            sb.append("CiphertextForRecipient: " + getCiphertextForRecipient());
        }
        sb.append(h.d);
        return sb.toString();
    }

    public int hashCode() {
        return (((getPlaintext() == null ? 0 : getPlaintext().hashCode()) + 31) * 31) + (getCiphertextForRecipient() != null ? getCiphertextForRecipient().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GenerateRandomResult)) {
            return false;
        }
        GenerateRandomResult generateRandomResult = (GenerateRandomResult) obj;
        if ((generateRandomResult.getPlaintext() == null) ^ (getPlaintext() == null)) {
            return false;
        }
        if (generateRandomResult.getPlaintext() != null && !generateRandomResult.getPlaintext().equals(getPlaintext())) {
            return false;
        }
        if ((generateRandomResult.getCiphertextForRecipient() == null) ^ (getCiphertextForRecipient() == null)) {
            return false;
        }
        return generateRandomResult.getCiphertextForRecipient() == null || generateRandomResult.getCiphertextForRecipient().equals(getCiphertextForRecipient());
    }
}
