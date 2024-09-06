package com.netease.nimlib.amazonaws.services.securitytoken.model;

import com.alipay.sdk.m.q.h;
import java.io.Serializable;

/* loaded from: classes.dex */
public class ProvidedContext implements Serializable {
    private String contextAssertion;
    private String providerArn;

    public String getProviderArn() {
        return this.providerArn;
    }

    public void setProviderArn(String str) {
        this.providerArn = str;
    }

    public ProvidedContext withProviderArn(String str) {
        this.providerArn = str;
        return this;
    }

    public String getContextAssertion() {
        return this.contextAssertion;
    }

    public void setContextAssertion(String str) {
        this.contextAssertion = str;
    }

    public ProvidedContext withContextAssertion(String str) {
        this.contextAssertion = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getProviderArn() != null) {
            sb.append("ProviderArn: " + getProviderArn() + ",");
        }
        if (getContextAssertion() != null) {
            sb.append("ContextAssertion: " + getContextAssertion());
        }
        sb.append(h.d);
        return sb.toString();
    }

    public int hashCode() {
        return (((getProviderArn() == null ? 0 : getProviderArn().hashCode()) + 31) * 31) + (getContextAssertion() != null ? getContextAssertion().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ProvidedContext)) {
            return false;
        }
        ProvidedContext providedContext = (ProvidedContext) obj;
        if ((providedContext.getProviderArn() == null) ^ (getProviderArn() == null)) {
            return false;
        }
        if (providedContext.getProviderArn() != null && !providedContext.getProviderArn().equals(getProviderArn())) {
            return false;
        }
        if ((providedContext.getContextAssertion() == null) ^ (getContextAssertion() == null)) {
            return false;
        }
        return providedContext.getContextAssertion() == null || providedContext.getContextAssertion().equals(getContextAssertion());
    }
}
