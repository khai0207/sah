package com.netease.nimlib.fusionstorage.crossplatform.defines;

/* loaded from: classes.dex */
public class AuthPolicy {
    private boolean authEnabled;
    private int policyType;

    public AuthPolicy(boolean z, int i) {
        this.authEnabled = z;
        this.policyType = i;
    }

    public boolean isAuthEnabled() {
        return this.authEnabled;
    }

    public void setAuthEnabled(boolean z) {
        this.authEnabled = z;
    }

    public int getPolicyType() {
        return this.policyType;
    }

    public void setPolicyType(int i) {
        this.policyType = i;
    }

    public String toString() {
        return "AuthPolicy{authEnabled=" + this.authEnabled + ", policyType=" + this.policyType + '}';
    }
}
