package com.netease.nimlib.amazonaws.services.securitytoken.model.transform;

import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.services.securitytoken.model.Credentials;
import com.netease.nimlib.amazonaws.util.StringUtils;

/* loaded from: classes.dex */
class CredentialsStaxMarshaller {
    private static CredentialsStaxMarshaller instance;

    CredentialsStaxMarshaller() {
    }

    public void marshall(Credentials credentials, Request<?> request, String str) {
        if (credentials.getAccessKeyId() != null) {
            request.addParameter(str + "AccessKeyId", StringUtils.fromString(credentials.getAccessKeyId()));
        }
        if (credentials.getSecretAccessKey() != null) {
            request.addParameter(str + "SecretAccessKey", StringUtils.fromString(credentials.getSecretAccessKey()));
        }
        if (credentials.getSessionToken() != null) {
            request.addParameter(str + "SessionToken", StringUtils.fromString(credentials.getSessionToken()));
        }
        if (credentials.getExpiration() != null) {
            request.addParameter(str + "Expiration", StringUtils.fromDate(credentials.getExpiration()));
        }
    }

    public static CredentialsStaxMarshaller getInstance() {
        if (instance == null) {
            instance = new CredentialsStaxMarshaller();
        }
        return instance;
    }
}
