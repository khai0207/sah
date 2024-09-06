package com.netease.nimlib.amazonaws.services.kms.model;

import com.alipay.sdk.m.q.h;
import com.netease.nimlib.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* loaded from: classes.dex */
public class CreateKeyRequest extends AmazonWebServiceRequest implements Serializable {
    private Boolean bypassPolicyLockoutSafetyCheck;
    private String customKeyStoreId;
    private String customerMasterKeySpec;
    private String description;
    private String keySpec;
    private String keyUsage;
    private Boolean multiRegion;
    private String origin;
    private String policy;
    private List<Tag> tags = new ArrayList();
    private String xksKeyId;

    public String getPolicy() {
        return this.policy;
    }

    public void setPolicy(String str) {
        this.policy = str;
    }

    public CreateKeyRequest withPolicy(String str) {
        this.policy = str;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public CreateKeyRequest withDescription(String str) {
        this.description = str;
        return this;
    }

    public String getKeyUsage() {
        return this.keyUsage;
    }

    public void setKeyUsage(String str) {
        this.keyUsage = str;
    }

    public CreateKeyRequest withKeyUsage(String str) {
        this.keyUsage = str;
        return this;
    }

    public void setKeyUsage(KeyUsageType keyUsageType) {
        this.keyUsage = keyUsageType.toString();
    }

    public CreateKeyRequest withKeyUsage(KeyUsageType keyUsageType) {
        this.keyUsage = keyUsageType.toString();
        return this;
    }

    public String getCustomerMasterKeySpec() {
        return this.customerMasterKeySpec;
    }

    public void setCustomerMasterKeySpec(String str) {
        this.customerMasterKeySpec = str;
    }

    public CreateKeyRequest withCustomerMasterKeySpec(String str) {
        this.customerMasterKeySpec = str;
        return this;
    }

    public void setCustomerMasterKeySpec(CustomerMasterKeySpec customerMasterKeySpec) {
        this.customerMasterKeySpec = customerMasterKeySpec.toString();
    }

    public CreateKeyRequest withCustomerMasterKeySpec(CustomerMasterKeySpec customerMasterKeySpec) {
        this.customerMasterKeySpec = customerMasterKeySpec.toString();
        return this;
    }

    public String getKeySpec() {
        return this.keySpec;
    }

    public void setKeySpec(String str) {
        this.keySpec = str;
    }

    public CreateKeyRequest withKeySpec(String str) {
        this.keySpec = str;
        return this;
    }

    public void setKeySpec(KeySpec keySpec) {
        this.keySpec = keySpec.toString();
    }

    public CreateKeyRequest withKeySpec(KeySpec keySpec) {
        this.keySpec = keySpec.toString();
        return this;
    }

    public String getOrigin() {
        return this.origin;
    }

    public void setOrigin(String str) {
        this.origin = str;
    }

    public CreateKeyRequest withOrigin(String str) {
        this.origin = str;
        return this;
    }

    public void setOrigin(OriginType originType) {
        this.origin = originType.toString();
    }

    public CreateKeyRequest withOrigin(OriginType originType) {
        this.origin = originType.toString();
        return this;
    }

    public String getCustomKeyStoreId() {
        return this.customKeyStoreId;
    }

    public void setCustomKeyStoreId(String str) {
        this.customKeyStoreId = str;
    }

    public CreateKeyRequest withCustomKeyStoreId(String str) {
        this.customKeyStoreId = str;
        return this;
    }

    public Boolean isBypassPolicyLockoutSafetyCheck() {
        return this.bypassPolicyLockoutSafetyCheck;
    }

    public Boolean getBypassPolicyLockoutSafetyCheck() {
        return this.bypassPolicyLockoutSafetyCheck;
    }

    public void setBypassPolicyLockoutSafetyCheck(Boolean bool) {
        this.bypassPolicyLockoutSafetyCheck = bool;
    }

    public CreateKeyRequest withBypassPolicyLockoutSafetyCheck(Boolean bool) {
        this.bypassPolicyLockoutSafetyCheck = bool;
        return this;
    }

    public List<Tag> getTags() {
        return this.tags;
    }

    public void setTags(Collection<Tag> collection) {
        if (collection == null) {
            this.tags = null;
        } else {
            this.tags = new ArrayList(collection);
        }
    }

    public CreateKeyRequest withTags(Tag... tagArr) {
        if (getTags() == null) {
            this.tags = new ArrayList(tagArr.length);
        }
        for (Tag tag : tagArr) {
            this.tags.add(tag);
        }
        return this;
    }

    public CreateKeyRequest withTags(Collection<Tag> collection) {
        setTags(collection);
        return this;
    }

    public Boolean isMultiRegion() {
        return this.multiRegion;
    }

    public Boolean getMultiRegion() {
        return this.multiRegion;
    }

    public void setMultiRegion(Boolean bool) {
        this.multiRegion = bool;
    }

    public CreateKeyRequest withMultiRegion(Boolean bool) {
        this.multiRegion = bool;
        return this;
    }

    public String getXksKeyId() {
        return this.xksKeyId;
    }

    public void setXksKeyId(String str) {
        this.xksKeyId = str;
    }

    public CreateKeyRequest withXksKeyId(String str) {
        this.xksKeyId = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getPolicy() != null) {
            sb.append("Policy: " + getPolicy() + ",");
        }
        if (getDescription() != null) {
            sb.append("Description: " + getDescription() + ",");
        }
        if (getKeyUsage() != null) {
            sb.append("KeyUsage: " + getKeyUsage() + ",");
        }
        if (getCustomerMasterKeySpec() != null) {
            sb.append("CustomerMasterKeySpec: " + getCustomerMasterKeySpec() + ",");
        }
        if (getKeySpec() != null) {
            sb.append("KeySpec: " + getKeySpec() + ",");
        }
        if (getOrigin() != null) {
            sb.append("Origin: " + getOrigin() + ",");
        }
        if (getCustomKeyStoreId() != null) {
            sb.append("CustomKeyStoreId: " + getCustomKeyStoreId() + ",");
        }
        if (getBypassPolicyLockoutSafetyCheck() != null) {
            sb.append("BypassPolicyLockoutSafetyCheck: " + getBypassPolicyLockoutSafetyCheck() + ",");
        }
        if (getTags() != null) {
            sb.append("Tags: " + getTags() + ",");
        }
        if (getMultiRegion() != null) {
            sb.append("MultiRegion: " + getMultiRegion() + ",");
        }
        if (getXksKeyId() != null) {
            sb.append("XksKeyId: " + getXksKeyId());
        }
        sb.append(h.d);
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((((((((((getPolicy() == null ? 0 : getPolicy().hashCode()) + 31) * 31) + (getDescription() == null ? 0 : getDescription().hashCode())) * 31) + (getKeyUsage() == null ? 0 : getKeyUsage().hashCode())) * 31) + (getCustomerMasterKeySpec() == null ? 0 : getCustomerMasterKeySpec().hashCode())) * 31) + (getKeySpec() == null ? 0 : getKeySpec().hashCode())) * 31) + (getOrigin() == null ? 0 : getOrigin().hashCode())) * 31) + (getCustomKeyStoreId() == null ? 0 : getCustomKeyStoreId().hashCode())) * 31) + (getBypassPolicyLockoutSafetyCheck() == null ? 0 : getBypassPolicyLockoutSafetyCheck().hashCode())) * 31) + (getTags() == null ? 0 : getTags().hashCode())) * 31) + (getMultiRegion() == null ? 0 : getMultiRegion().hashCode())) * 31) + (getXksKeyId() != null ? getXksKeyId().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateKeyRequest)) {
            return false;
        }
        CreateKeyRequest createKeyRequest = (CreateKeyRequest) obj;
        if ((createKeyRequest.getPolicy() == null) ^ (getPolicy() == null)) {
            return false;
        }
        if (createKeyRequest.getPolicy() != null && !createKeyRequest.getPolicy().equals(getPolicy())) {
            return false;
        }
        if ((createKeyRequest.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        if (createKeyRequest.getDescription() != null && !createKeyRequest.getDescription().equals(getDescription())) {
            return false;
        }
        if ((createKeyRequest.getKeyUsage() == null) ^ (getKeyUsage() == null)) {
            return false;
        }
        if (createKeyRequest.getKeyUsage() != null && !createKeyRequest.getKeyUsage().equals(getKeyUsage())) {
            return false;
        }
        if ((createKeyRequest.getCustomerMasterKeySpec() == null) ^ (getCustomerMasterKeySpec() == null)) {
            return false;
        }
        if (createKeyRequest.getCustomerMasterKeySpec() != null && !createKeyRequest.getCustomerMasterKeySpec().equals(getCustomerMasterKeySpec())) {
            return false;
        }
        if ((createKeyRequest.getKeySpec() == null) ^ (getKeySpec() == null)) {
            return false;
        }
        if (createKeyRequest.getKeySpec() != null && !createKeyRequest.getKeySpec().equals(getKeySpec())) {
            return false;
        }
        if ((createKeyRequest.getOrigin() == null) ^ (getOrigin() == null)) {
            return false;
        }
        if (createKeyRequest.getOrigin() != null && !createKeyRequest.getOrigin().equals(getOrigin())) {
            return false;
        }
        if ((createKeyRequest.getCustomKeyStoreId() == null) ^ (getCustomKeyStoreId() == null)) {
            return false;
        }
        if (createKeyRequest.getCustomKeyStoreId() != null && !createKeyRequest.getCustomKeyStoreId().equals(getCustomKeyStoreId())) {
            return false;
        }
        if ((createKeyRequest.getBypassPolicyLockoutSafetyCheck() == null) ^ (getBypassPolicyLockoutSafetyCheck() == null)) {
            return false;
        }
        if (createKeyRequest.getBypassPolicyLockoutSafetyCheck() != null && !createKeyRequest.getBypassPolicyLockoutSafetyCheck().equals(getBypassPolicyLockoutSafetyCheck())) {
            return false;
        }
        if ((createKeyRequest.getTags() == null) ^ (getTags() == null)) {
            return false;
        }
        if (createKeyRequest.getTags() != null && !createKeyRequest.getTags().equals(getTags())) {
            return false;
        }
        if ((createKeyRequest.getMultiRegion() == null) ^ (getMultiRegion() == null)) {
            return false;
        }
        if (createKeyRequest.getMultiRegion() != null && !createKeyRequest.getMultiRegion().equals(getMultiRegion())) {
            return false;
        }
        if ((createKeyRequest.getXksKeyId() == null) ^ (getXksKeyId() == null)) {
            return false;
        }
        return createKeyRequest.getXksKeyId() == null || createKeyRequest.getXksKeyId().equals(getXksKeyId());
    }
}
