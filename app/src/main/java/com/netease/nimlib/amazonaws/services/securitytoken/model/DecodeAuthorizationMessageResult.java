package com.netease.nimlib.amazonaws.services.securitytoken.model;

import com.alipay.sdk.m.q.h;
import java.io.Serializable;

/* loaded from: classes.dex */
public class DecodeAuthorizationMessageResult implements Serializable {
    private String decodedMessage;

    public String getDecodedMessage() {
        return this.decodedMessage;
    }

    public void setDecodedMessage(String str) {
        this.decodedMessage = str;
    }

    public DecodeAuthorizationMessageResult withDecodedMessage(String str) {
        this.decodedMessage = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getDecodedMessage() != null) {
            sb.append("DecodedMessage: " + getDecodedMessage());
        }
        sb.append(h.d);
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getDecodedMessage() == null ? 0 : getDecodedMessage().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DecodeAuthorizationMessageResult)) {
            return false;
        }
        DecodeAuthorizationMessageResult decodeAuthorizationMessageResult = (DecodeAuthorizationMessageResult) obj;
        if ((decodeAuthorizationMessageResult.getDecodedMessage() == null) ^ (getDecodedMessage() == null)) {
            return false;
        }
        return decodeAuthorizationMessageResult.getDecodedMessage() == null || decodeAuthorizationMessageResult.getDecodedMessage().equals(getDecodedMessage());
    }
}
