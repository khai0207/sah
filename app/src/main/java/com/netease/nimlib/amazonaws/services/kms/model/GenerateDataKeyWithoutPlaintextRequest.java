package com.netease.nimlib.amazonaws.services.kms.model;

import com.alipay.sdk.m.q.h;
import com.netease.nimlib.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class GenerateDataKeyWithoutPlaintextRequest extends AmazonWebServiceRequest implements Serializable {
    private Boolean dryRun;
    private Map<String, String> encryptionContext = new HashMap();
    private List<String> grantTokens = new ArrayList();
    private String keyId;
    private String keySpec;
    private Integer numberOfBytes;

    public String getKeyId() {
        return this.keyId;
    }

    public void setKeyId(String str) {
        this.keyId = str;
    }

    public GenerateDataKeyWithoutPlaintextRequest withKeyId(String str) {
        this.keyId = str;
        return this;
    }

    public Map<String, String> getEncryptionContext() {
        return this.encryptionContext;
    }

    public void setEncryptionContext(Map<String, String> map) {
        this.encryptionContext = map;
    }

    public GenerateDataKeyWithoutPlaintextRequest withEncryptionContext(Map<String, String> map) {
        this.encryptionContext = map;
        return this;
    }

    public GenerateDataKeyWithoutPlaintextRequest addEncryptionContextEntry(String str, String str2) {
        if (this.encryptionContext == null) {
            this.encryptionContext = new HashMap();
        }
        if (this.encryptionContext.containsKey(str)) {
            throw new IllegalArgumentException("Duplicated keys (" + str.toString() + ") are provided.");
        }
        this.encryptionContext.put(str, str2);
        return this;
    }

    public GenerateDataKeyWithoutPlaintextRequest clearEncryptionContextEntries() {
        this.encryptionContext = null;
        return this;
    }

    public String getKeySpec() {
        return this.keySpec;
    }

    public void setKeySpec(String str) {
        this.keySpec = str;
    }

    public GenerateDataKeyWithoutPlaintextRequest withKeySpec(String str) {
        this.keySpec = str;
        return this;
    }

    public void setKeySpec(DataKeySpec dataKeySpec) {
        this.keySpec = dataKeySpec.toString();
    }

    public GenerateDataKeyWithoutPlaintextRequest withKeySpec(DataKeySpec dataKeySpec) {
        this.keySpec = dataKeySpec.toString();
        return this;
    }

    public Integer getNumberOfBytes() {
        return this.numberOfBytes;
    }

    public void setNumberOfBytes(Integer num) {
        this.numberOfBytes = num;
    }

    public GenerateDataKeyWithoutPlaintextRequest withNumberOfBytes(Integer num) {
        this.numberOfBytes = num;
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

    public GenerateDataKeyWithoutPlaintextRequest withGrantTokens(String... strArr) {
        if (getGrantTokens() == null) {
            this.grantTokens = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.grantTokens.add(str);
        }
        return this;
    }

    public GenerateDataKeyWithoutPlaintextRequest withGrantTokens(Collection<String> collection) {
        setGrantTokens(collection);
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

    public GenerateDataKeyWithoutPlaintextRequest withDryRun(Boolean bool) {
        this.dryRun = bool;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getKeyId() != null) {
            sb.append("KeyId: " + getKeyId() + ",");
        }
        if (getEncryptionContext() != null) {
            sb.append("EncryptionContext: " + getEncryptionContext() + ",");
        }
        if (getKeySpec() != null) {
            sb.append("KeySpec: " + getKeySpec() + ",");
        }
        if (getNumberOfBytes() != null) {
            sb.append("NumberOfBytes: " + getNumberOfBytes() + ",");
        }
        if (getGrantTokens() != null) {
            sb.append("GrantTokens: " + getGrantTokens() + ",");
        }
        if (getDryRun() != null) {
            sb.append("DryRun: " + getDryRun());
        }
        sb.append(h.d);
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((getKeyId() == null ? 0 : getKeyId().hashCode()) + 31) * 31) + (getEncryptionContext() == null ? 0 : getEncryptionContext().hashCode())) * 31) + (getKeySpec() == null ? 0 : getKeySpec().hashCode())) * 31) + (getNumberOfBytes() == null ? 0 : getNumberOfBytes().hashCode())) * 31) + (getGrantTokens() == null ? 0 : getGrantTokens().hashCode())) * 31) + (getDryRun() != null ? getDryRun().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GenerateDataKeyWithoutPlaintextRequest)) {
            return false;
        }
        GenerateDataKeyWithoutPlaintextRequest generateDataKeyWithoutPlaintextRequest = (GenerateDataKeyWithoutPlaintextRequest) obj;
        if ((generateDataKeyWithoutPlaintextRequest.getKeyId() == null) ^ (getKeyId() == null)) {
            return false;
        }
        if (generateDataKeyWithoutPlaintextRequest.getKeyId() != null && !generateDataKeyWithoutPlaintextRequest.getKeyId().equals(getKeyId())) {
            return false;
        }
        if ((generateDataKeyWithoutPlaintextRequest.getEncryptionContext() == null) ^ (getEncryptionContext() == null)) {
            return false;
        }
        if (generateDataKeyWithoutPlaintextRequest.getEncryptionContext() != null && !generateDataKeyWithoutPlaintextRequest.getEncryptionContext().equals(getEncryptionContext())) {
            return false;
        }
        if ((generateDataKeyWithoutPlaintextRequest.getKeySpec() == null) ^ (getKeySpec() == null)) {
            return false;
        }
        if (generateDataKeyWithoutPlaintextRequest.getKeySpec() != null && !generateDataKeyWithoutPlaintextRequest.getKeySpec().equals(getKeySpec())) {
            return false;
        }
        if ((generateDataKeyWithoutPlaintextRequest.getNumberOfBytes() == null) ^ (getNumberOfBytes() == null)) {
            return false;
        }
        if (generateDataKeyWithoutPlaintextRequest.getNumberOfBytes() != null && !generateDataKeyWithoutPlaintextRequest.getNumberOfBytes().equals(getNumberOfBytes())) {
            return false;
        }
        if ((generateDataKeyWithoutPlaintextRequest.getGrantTokens() == null) ^ (getGrantTokens() == null)) {
            return false;
        }
        if (generateDataKeyWithoutPlaintextRequest.getGrantTokens() != null && !generateDataKeyWithoutPlaintextRequest.getGrantTokens().equals(getGrantTokens())) {
            return false;
        }
        if ((generateDataKeyWithoutPlaintextRequest.getDryRun() == null) ^ (getDryRun() == null)) {
            return false;
        }
        return generateDataKeyWithoutPlaintextRequest.getDryRun() == null || generateDataKeyWithoutPlaintextRequest.getDryRun().equals(getDryRun());
    }
}
