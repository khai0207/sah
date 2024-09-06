package com.netease.nimlib.amazonaws.services.kms.model;

import com.alipay.sdk.m.q.h;
import com.netease.nimlib.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* loaded from: classes.dex */
public class SignRequest extends AmazonWebServiceRequest implements Serializable {
    private Boolean dryRun;
    private List<String> grantTokens = new ArrayList();
    private String keyId;
    private ByteBuffer message;
    private String messageType;
    private String signingAlgorithm;

    public String getKeyId() {
        return this.keyId;
    }

    public void setKeyId(String str) {
        this.keyId = str;
    }

    public SignRequest withKeyId(String str) {
        this.keyId = str;
        return this;
    }

    public ByteBuffer getMessage() {
        return this.message;
    }

    public void setMessage(ByteBuffer byteBuffer) {
        this.message = byteBuffer;
    }

    public SignRequest withMessage(ByteBuffer byteBuffer) {
        this.message = byteBuffer;
        return this;
    }

    public String getMessageType() {
        return this.messageType;
    }

    public void setMessageType(String str) {
        this.messageType = str;
    }

    public SignRequest withMessageType(String str) {
        this.messageType = str;
        return this;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType.toString();
    }

    public SignRequest withMessageType(MessageType messageType) {
        this.messageType = messageType.toString();
        return this;
    }

    public List<String> getGrantTokens() {
        return this.grantTokens;
    }

    public void setGrantTokens(Collection<String> collection) {
        if (collection == null) {
            this.grantTokens = null;
        } else {
            this.grantTokens = new ArrayList(collection);
        }
    }

    public SignRequest withGrantTokens(String... strArr) {
        if (getGrantTokens() == null) {
            this.grantTokens = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.grantTokens.add(str);
        }
        return this;
    }

    public SignRequest withGrantTokens(Collection<String> collection) {
        setGrantTokens(collection);
        return this;
    }

    public String getSigningAlgorithm() {
        return this.signingAlgorithm;
    }

    public void setSigningAlgorithm(String str) {
        this.signingAlgorithm = str;
    }

    public SignRequest withSigningAlgorithm(String str) {
        this.signingAlgorithm = str;
        return this;
    }

    public void setSigningAlgorithm(SigningAlgorithmSpec signingAlgorithmSpec) {
        this.signingAlgorithm = signingAlgorithmSpec.toString();
    }

    public SignRequest withSigningAlgorithm(SigningAlgorithmSpec signingAlgorithmSpec) {
        this.signingAlgorithm = signingAlgorithmSpec.toString();
        return this;
    }

    public Boolean isDryRun() {
        return this.dryRun;
    }

    public Boolean getDryRun() {
        return this.dryRun;
    }

    public void setDryRun(Boolean bool) {
        this.dryRun = bool;
    }

    public SignRequest withDryRun(Boolean bool) {
        this.dryRun = bool;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getKeyId() != null) {
            sb.append("KeyId: " + getKeyId() + ",");
        }
        if (getMessage() != null) {
            sb.append("Message: " + getMessage() + ",");
        }
        if (getMessageType() != null) {
            sb.append("MessageType: " + getMessageType() + ",");
        }
        if (getGrantTokens() != null) {
            sb.append("GrantTokens: " + getGrantTokens() + ",");
        }
        if (getSigningAlgorithm() != null) {
            sb.append("SigningAlgorithm: " + getSigningAlgorithm() + ",");
        }
        if (getDryRun() != null) {
            sb.append("DryRun: " + getDryRun());
        }
        sb.append(h.d);
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((getKeyId() == null ? 0 : getKeyId().hashCode()) + 31) * 31) + (getMessage() == null ? 0 : getMessage().hashCode())) * 31) + (getMessageType() == null ? 0 : getMessageType().hashCode())) * 31) + (getGrantTokens() == null ? 0 : getGrantTokens().hashCode())) * 31) + (getSigningAlgorithm() == null ? 0 : getSigningAlgorithm().hashCode())) * 31) + (getDryRun() != null ? getDryRun().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof SignRequest)) {
            return false;
        }
        SignRequest signRequest = (SignRequest) obj;
        if ((signRequest.getKeyId() == null) ^ (getKeyId() == null)) {
            return false;
        }
        if (signRequest.getKeyId() != null && !signRequest.getKeyId().equals(getKeyId())) {
            return false;
        }
        if ((signRequest.getMessage() == null) ^ (getMessage() == null)) {
            return false;
        }
        if (signRequest.getMessage() != null && !signRequest.getMessage().equals(getMessage())) {
            return false;
        }
        if ((signRequest.getMessageType() == null) ^ (getMessageType() == null)) {
            return false;
        }
        if (signRequest.getMessageType() != null && !signRequest.getMessageType().equals(getMessageType())) {
            return false;
        }
        if ((signRequest.getGrantTokens() == null) ^ (getGrantTokens() == null)) {
            return false;
        }
        if (signRequest.getGrantTokens() != null && !signRequest.getGrantTokens().equals(getGrantTokens())) {
            return false;
        }
        if ((signRequest.getSigningAlgorithm() == null) ^ (getSigningAlgorithm() == null)) {
            return false;
        }
        if (signRequest.getSigningAlgorithm() != null && !signRequest.getSigningAlgorithm().equals(getSigningAlgorithm())) {
            return false;
        }
        if ((signRequest.getDryRun() == null) ^ (getDryRun() == null)) {
            return false;
        }
        return signRequest.getDryRun() == null || signRequest.getDryRun().equals(getDryRun());
    }
}
