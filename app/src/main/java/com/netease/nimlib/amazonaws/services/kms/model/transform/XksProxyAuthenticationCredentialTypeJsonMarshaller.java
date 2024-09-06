package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.services.kms.model.XksProxyAuthenticationCredentialType;
import com.netease.nimlib.amazonaws.util.json.AwsJsonWriter;

/* loaded from: classes.dex */
class XksProxyAuthenticationCredentialTypeJsonMarshaller {
    private static XksProxyAuthenticationCredentialTypeJsonMarshaller instance;

    XksProxyAuthenticationCredentialTypeJsonMarshaller() {
    }

    public void marshall(XksProxyAuthenticationCredentialType xksProxyAuthenticationCredentialType, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (xksProxyAuthenticationCredentialType.getAccessKeyId() != null) {
            String accessKeyId = xksProxyAuthenticationCredentialType.getAccessKeyId();
            awsJsonWriter.name("AccessKeyId");
            awsJsonWriter.value(accessKeyId);
        }
        if (xksProxyAuthenticationCredentialType.getRawSecretAccessKey() != null) {
            String rawSecretAccessKey = xksProxyAuthenticationCredentialType.getRawSecretAccessKey();
            awsJsonWriter.name("RawSecretAccessKey");
            awsJsonWriter.value(rawSecretAccessKey);
        }
        awsJsonWriter.endObject();
    }

    public static XksProxyAuthenticationCredentialTypeJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new XksProxyAuthenticationCredentialTypeJsonMarshaller();
        }
        return instance;
    }
}
