package com.netease.nimlib.amazonaws.services.securitytoken.model;

import com.alipay.sdk.m.q.h;
import java.io.Serializable;

/* loaded from: classes.dex */
public class GetAccessKeyInfoResult implements Serializable {
    private String account;

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String str) {
        this.account = str;
    }

    public GetAccessKeyInfoResult withAccount(String str) {
        this.account = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getAccount() != null) {
            sb.append("Account: " + getAccount());
        }
        sb.append(h.d);
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getAccount() == null ? 0 : getAccount().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetAccessKeyInfoResult)) {
            return false;
        }
        GetAccessKeyInfoResult getAccessKeyInfoResult = (GetAccessKeyInfoResult) obj;
        if ((getAccessKeyInfoResult.getAccount() == null) ^ (getAccount() == null)) {
            return false;
        }
        return getAccessKeyInfoResult.getAccount() == null || getAccessKeyInfoResult.getAccount().equals(getAccount());
    }
}
