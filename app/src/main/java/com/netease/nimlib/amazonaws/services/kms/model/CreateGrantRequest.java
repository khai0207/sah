package com.netease.nimlib.amazonaws.services.kms.model;

import com.alipay.sdk.m.q.h;
import com.netease.nimlib.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* loaded from: classes.dex */
public class CreateGrantRequest extends AmazonWebServiceRequest implements Serializable {
    private GrantConstraints constraints;
    private Boolean dryRun;
    private String granteePrincipal;
    private String keyId;
    private String name;
    private String retiringPrincipal;
    private List<String> operations = new ArrayList();
    private List<String> grantTokens = new ArrayList();

    public String getKeyId() {
        return this.keyId;
    }

    public void setKeyId(String str) {
        this.keyId = str;
    }

    public CreateGrantRequest withKeyId(String str) {
        this.keyId = str;
        return this;
    }

    public String getGranteePrincipal() {
        return this.granteePrincipal;
    }

    public void setGranteePrincipal(String str) {
        this.granteePrincipal = str;
    }

    public CreateGrantRequest withGranteePrincipal(String str) {
        this.granteePrincipal = str;
        return this;
    }

    public String getRetiringPrincipal() {
        return this.retiringPrincipal;
    }

    public void setRetiringPrincipal(String str) {
        this.retiringPrincipal = str;
    }

    public CreateGrantRequest withRetiringPrincipal(String str) {
        this.retiringPrincipal = str;
        return this;
    }

    public List<String> getOperations() {
        return this.operations;
    }

    public void setOperations(Collection<String> collection) {
        if (collection == null) {
            this.operations = null;
        } else {
            this.operations = new ArrayList(collection);
        }
    }

    public CreateGrantRequest withOperations(String... strArr) {
        if (getOperations() == null) {
            this.operations = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.operations.add(str);
        }
        return this;
    }

    public CreateGrantRequest withOperations(Collection<String> collection) {
        setOperations(collection);
        return this;
    }

    public GrantConstraints getConstraints() {
        return this.constraints;
    }

    public void setConstraints(GrantConstraints grantConstraints) {
        this.constraints = grantConstraints;
    }

    public CreateGrantRequest withConstraints(GrantConstraints grantConstraints) {
        this.constraints = grantConstraints;
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

    public CreateGrantRequest withGrantTokens(String... strArr) {
        if (getGrantTokens() == null) {
            this.grantTokens = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.grantTokens.add(str);
        }
        return this;
    }

    public CreateGrantRequest withGrantTokens(Collection<String> collection) {
        setGrantTokens(collection);
        return this;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public CreateGrantRequest withName(String str) {
        this.name = str;
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

    public CreateGrantRequest withDryRun(Boolean bool) {
        this.dryRun = bool;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getKeyId() != null) {
            sb.append("KeyId: " + getKeyId() + ",");
        }
        if (getGranteePrincipal() != null) {
            sb.append("GranteePrincipal: " + getGranteePrincipal() + ",");
        }
        if (getRetiringPrincipal() != null) {
            sb.append("RetiringPrincipal: " + getRetiringPrincipal() + ",");
        }
        if (getOperations() != null) {
            sb.append("Operations: " + getOperations() + ",");
        }
        if (getConstraints() != null) {
            sb.append("Constraints: " + getConstraints() + ",");
        }
        if (getGrantTokens() != null) {
            sb.append("GrantTokens: " + getGrantTokens() + ",");
        }
        if (getName() != null) {
            sb.append("Name: " + getName() + ",");
        }
        if (getDryRun() != null) {
            sb.append("DryRun: " + getDryRun());
        }
        sb.append(h.d);
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((((getKeyId() == null ? 0 : getKeyId().hashCode()) + 31) * 31) + (getGranteePrincipal() == null ? 0 : getGranteePrincipal().hashCode())) * 31) + (getRetiringPrincipal() == null ? 0 : getRetiringPrincipal().hashCode())) * 31) + (getOperations() == null ? 0 : getOperations().hashCode())) * 31) + (getConstraints() == null ? 0 : getConstraints().hashCode())) * 31) + (getGrantTokens() == null ? 0 : getGrantTokens().hashCode())) * 31) + (getName() == null ? 0 : getName().hashCode())) * 31) + (getDryRun() != null ? getDryRun().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateGrantRequest)) {
            return false;
        }
        CreateGrantRequest createGrantRequest = (CreateGrantRequest) obj;
        if ((createGrantRequest.getKeyId() == null) ^ (getKeyId() == null)) {
            return false;
        }
        if (createGrantRequest.getKeyId() != null && !createGrantRequest.getKeyId().equals(getKeyId())) {
            return false;
        }
        if ((createGrantRequest.getGranteePrincipal() == null) ^ (getGranteePrincipal() == null)) {
            return false;
        }
        if (createGrantRequest.getGranteePrincipal() != null && !createGrantRequest.getGranteePrincipal().equals(getGranteePrincipal())) {
            return false;
        }
        if ((createGrantRequest.getRetiringPrincipal() == null) ^ (getRetiringPrincipal() == null)) {
            return false;
        }
        if (createGrantRequest.getRetiringPrincipal() != null && !createGrantRequest.getRetiringPrincipal().equals(getRetiringPrincipal())) {
            return false;
        }
        if ((createGrantRequest.getOperations() == null) ^ (getOperations() == null)) {
            return false;
        }
        if (createGrantRequest.getOperations() != null && !createGrantRequest.getOperations().equals(getOperations())) {
            return false;
        }
        if ((createGrantRequest.getConstraints() == null) ^ (getConstraints() == null)) {
            return false;
        }
        if (createGrantRequest.getConstraints() != null && !createGrantRequest.getConstraints().equals(getConstraints())) {
            return false;
        }
        if ((createGrantRequest.getGrantTokens() == null) ^ (getGrantTokens() == null)) {
            return false;
        }
        if (createGrantRequest.getGrantTokens() != null && !createGrantRequest.getGrantTokens().equals(getGrantTokens())) {
            return false;
        }
        if ((createGrantRequest.getName() == null) ^ (getName() == null)) {
            return false;
        }
        if (createGrantRequest.getName() != null && !createGrantRequest.getName().equals(getName())) {
            return false;
        }
        if ((createGrantRequest.getDryRun() == null) ^ (getDryRun() == null)) {
            return false;
        }
        return createGrantRequest.getDryRun() == null || createGrantRequest.getDryRun().equals(getDryRun());
    }
}
