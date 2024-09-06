package com.netease.nimlib.amazonaws.services.securitytoken.model;

import com.alipay.sdk.m.q.h;
import com.netease.nimlib.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* loaded from: classes.dex */
public class DecodeAuthorizationMessageRequest extends AmazonWebServiceRequest implements Serializable {
    private String encodedMessage;

    public String getEncodedMessage() {
        return this.encodedMessage;
    }

    public void setEncodedMessage(String str) {
        this.encodedMessage = str;
    }

    public DecodeAuthorizationMessageRequest withEncodedMessage(String str) {
        this.encodedMessage = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getEncodedMessage() != null) {
            sb.append("EncodedMessage: " + getEncodedMessage());
        }
        sb.append(h.d);
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getEncodedMessage() == null ? 0 : getEncodedMessage().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DecodeAuthorizationMessageRequest)) {
            return false;
        }
        DecodeAuthorizationMessageRequest decodeAuthorizationMessageRequest = (DecodeAuthorizationMessageRequest) obj;
        if ((decodeAuthorizationMessageRequest.getEncodedMessage() == null) ^ (getEncodedMessage() == null)) {
            return false;
        }
        return decodeAuthorizationMessageRequest.getEncodedMessage() == null || decodeAuthorizationMessageRequest.getEncodedMessage().equals(getEncodedMessage());
    }
}
