package com.netease.nimlib.amazonaws.services.kms.model;

import com.alipay.sdk.m.q.h;
import java.io.Serializable;
import java.util.Date;

/* loaded from: classes.dex */
public class CustomKeyStoresListEntry implements Serializable {
    private String cloudHsmClusterId;
    private String connectionErrorCode;
    private String connectionState;
    private Date creationDate;
    private String customKeyStoreId;
    private String customKeyStoreName;
    private String customKeyStoreType;
    private String trustAnchorCertificate;
    private XksProxyConfigurationType xksProxyConfiguration;

    public String getCustomKeyStoreId() {
        return this.customKeyStoreId;
    }

    public void setCustomKeyStoreId(String str) {
        this.customKeyStoreId = str;
    }

    public CustomKeyStoresListEntry withCustomKeyStoreId(String str) {
        this.customKeyStoreId = str;
        return this;
    }

    public String getCustomKeyStoreName() {
        return this.customKeyStoreName;
    }

    public void setCustomKeyStoreName(String str) {
        this.customKeyStoreName = str;
    }

    public CustomKeyStoresListEntry withCustomKeyStoreName(String str) {
        this.customKeyStoreName = str;
        return this;
    }

    public String getCloudHsmClusterId() {
        return this.cloudHsmClusterId;
    }

    public void setCloudHsmClusterId(String str) {
        this.cloudHsmClusterId = str;
    }

    public CustomKeyStoresListEntry withCloudHsmClusterId(String str) {
        this.cloudHsmClusterId = str;
        return this;
    }

    public String getTrustAnchorCertificate() {
        return this.trustAnchorCertificate;
    }

    public void setTrustAnchorCertificate(String str) {
        this.trustAnchorCertificate = str;
    }

    public CustomKeyStoresListEntry withTrustAnchorCertificate(String str) {
        this.trustAnchorCertificate = str;
        return this;
    }

    public String getConnectionState() {
        return this.connectionState;
    }

    public void setConnectionState(String str) {
        this.connectionState = str;
    }

    public CustomKeyStoresListEntry withConnectionState(String str) {
        this.connectionState = str;
        return this;
    }

    public void setConnectionState(ConnectionStateType connectionStateType) {
        this.connectionState = connectionStateType.toString();
    }

    public CustomKeyStoresListEntry withConnectionState(ConnectionStateType connectionStateType) {
        this.connectionState = connectionStateType.toString();
        return this;
    }

    public String getConnectionErrorCode() {
        return this.connectionErrorCode;
    }

    public void setConnectionErrorCode(String str) {
        this.connectionErrorCode = str;
    }

    public CustomKeyStoresListEntry withConnectionErrorCode(String str) {
        this.connectionErrorCode = str;
        return this;
    }

    public void setConnectionErrorCode(ConnectionErrorCodeType connectionErrorCodeType) {
        this.connectionErrorCode = connectionErrorCodeType.toString();
    }

    public CustomKeyStoresListEntry withConnectionErrorCode(ConnectionErrorCodeType connectionErrorCodeType) {
        this.connectionErrorCode = connectionErrorCodeType.toString();
        return this;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public CustomKeyStoresListEntry withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public String getCustomKeyStoreType() {
        return this.customKeyStoreType;
    }

    public void setCustomKeyStoreType(String str) {
        this.customKeyStoreType = str;
    }

    public CustomKeyStoresListEntry withCustomKeyStoreType(String str) {
        this.customKeyStoreType = str;
        return this;
    }

    public void setCustomKeyStoreType(CustomKeyStoreType customKeyStoreType) {
        this.customKeyStoreType = customKeyStoreType.toString();
    }

    public CustomKeyStoresListEntry withCustomKeyStoreType(CustomKeyStoreType customKeyStoreType) {
        this.customKeyStoreType = customKeyStoreType.toString();
        return this;
    }

    public XksProxyConfigurationType getXksProxyConfiguration() {
        return this.xksProxyConfiguration;
    }

    public void setXksProxyConfiguration(XksProxyConfigurationType xksProxyConfigurationType) {
        this.xksProxyConfiguration = xksProxyConfigurationType;
    }

    public CustomKeyStoresListEntry withXksProxyConfiguration(XksProxyConfigurationType xksProxyConfigurationType) {
        this.xksProxyConfiguration = xksProxyConfigurationType;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCustomKeyStoreId() != null) {
            sb.append("CustomKeyStoreId: " + getCustomKeyStoreId() + ",");
        }
        if (getCustomKeyStoreName() != null) {
            sb.append("CustomKeyStoreName: " + getCustomKeyStoreName() + ",");
        }
        if (getCloudHsmClusterId() != null) {
            sb.append("CloudHsmClusterId: " + getCloudHsmClusterId() + ",");
        }
        if (getTrustAnchorCertificate() != null) {
            sb.append("TrustAnchorCertificate: " + getTrustAnchorCertificate() + ",");
        }
        if (getConnectionState() != null) {
            sb.append("ConnectionState: " + getConnectionState() + ",");
        }
        if (getConnectionErrorCode() != null) {
            sb.append("ConnectionErrorCode: " + getConnectionErrorCode() + ",");
        }
        if (getCreationDate() != null) {
            sb.append("CreationDate: " + getCreationDate() + ",");
        }
        if (getCustomKeyStoreType() != null) {
            sb.append("CustomKeyStoreType: " + getCustomKeyStoreType() + ",");
        }
        if (getXksProxyConfiguration() != null) {
            sb.append("XksProxyConfiguration: " + getXksProxyConfiguration());
        }
        sb.append(h.d);
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((((((getCustomKeyStoreId() == null ? 0 : getCustomKeyStoreId().hashCode()) + 31) * 31) + (getCustomKeyStoreName() == null ? 0 : getCustomKeyStoreName().hashCode())) * 31) + (getCloudHsmClusterId() == null ? 0 : getCloudHsmClusterId().hashCode())) * 31) + (getTrustAnchorCertificate() == null ? 0 : getTrustAnchorCertificate().hashCode())) * 31) + (getConnectionState() == null ? 0 : getConnectionState().hashCode())) * 31) + (getConnectionErrorCode() == null ? 0 : getConnectionErrorCode().hashCode())) * 31) + (getCreationDate() == null ? 0 : getCreationDate().hashCode())) * 31) + (getCustomKeyStoreType() == null ? 0 : getCustomKeyStoreType().hashCode())) * 31) + (getXksProxyConfiguration() != null ? getXksProxyConfiguration().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CustomKeyStoresListEntry)) {
            return false;
        }
        CustomKeyStoresListEntry customKeyStoresListEntry = (CustomKeyStoresListEntry) obj;
        if ((customKeyStoresListEntry.getCustomKeyStoreId() == null) ^ (getCustomKeyStoreId() == null)) {
            return false;
        }
        if (customKeyStoresListEntry.getCustomKeyStoreId() != null && !customKeyStoresListEntry.getCustomKeyStoreId().equals(getCustomKeyStoreId())) {
            return false;
        }
        if ((customKeyStoresListEntry.getCustomKeyStoreName() == null) ^ (getCustomKeyStoreName() == null)) {
            return false;
        }
        if (customKeyStoresListEntry.getCustomKeyStoreName() != null && !customKeyStoresListEntry.getCustomKeyStoreName().equals(getCustomKeyStoreName())) {
            return false;
        }
        if ((customKeyStoresListEntry.getCloudHsmClusterId() == null) ^ (getCloudHsmClusterId() == null)) {
            return false;
        }
        if (customKeyStoresListEntry.getCloudHsmClusterId() != null && !customKeyStoresListEntry.getCloudHsmClusterId().equals(getCloudHsmClusterId())) {
            return false;
        }
        if ((customKeyStoresListEntry.getTrustAnchorCertificate() == null) ^ (getTrustAnchorCertificate() == null)) {
            return false;
        }
        if (customKeyStoresListEntry.getTrustAnchorCertificate() != null && !customKeyStoresListEntry.getTrustAnchorCertificate().equals(getTrustAnchorCertificate())) {
            return false;
        }
        if ((customKeyStoresListEntry.getConnectionState() == null) ^ (getConnectionState() == null)) {
            return false;
        }
        if (customKeyStoresListEntry.getConnectionState() != null && !customKeyStoresListEntry.getConnectionState().equals(getConnectionState())) {
            return false;
        }
        if ((customKeyStoresListEntry.getConnectionErrorCode() == null) ^ (getConnectionErrorCode() == null)) {
            return false;
        }
        if (customKeyStoresListEntry.getConnectionErrorCode() != null && !customKeyStoresListEntry.getConnectionErrorCode().equals(getConnectionErrorCode())) {
            return false;
        }
        if ((customKeyStoresListEntry.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        if (customKeyStoresListEntry.getCreationDate() != null && !customKeyStoresListEntry.getCreationDate().equals(getCreationDate())) {
            return false;
        }
        if ((customKeyStoresListEntry.getCustomKeyStoreType() == null) ^ (getCustomKeyStoreType() == null)) {
            return false;
        }
        if (customKeyStoresListEntry.getCustomKeyStoreType() != null && !customKeyStoresListEntry.getCustomKeyStoreType().equals(getCustomKeyStoreType())) {
            return false;
        }
        if ((customKeyStoresListEntry.getXksProxyConfiguration() == null) ^ (getXksProxyConfiguration() == null)) {
            return false;
        }
        return customKeyStoresListEntry.getXksProxyConfiguration() == null || customKeyStoresListEntry.getXksProxyConfiguration().equals(getXksProxyConfiguration());
    }
}
