package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.services.kms.model.XksProxyAuthenticationCredentialType;
import com.netease.nimlib.amazonaws.transform.JsonUnmarshallerContext;
import com.netease.nimlib.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.netease.nimlib.amazonaws.transform.Unmarshaller;
import com.netease.nimlib.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
class XksProxyAuthenticationCredentialTypeJsonUnmarshaller implements Unmarshaller<XksProxyAuthenticationCredentialType, JsonUnmarshallerContext> {
    private static XksProxyAuthenticationCredentialTypeJsonUnmarshaller instance;

    XksProxyAuthenticationCredentialTypeJsonUnmarshaller() {
    }

    @Override // com.netease.nimlib.amazonaws.transform.Unmarshaller
    public XksProxyAuthenticationCredentialType unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        XksProxyAuthenticationCredentialType xksProxyAuthenticationCredentialType = new XksProxyAuthenticationCredentialType();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("AccessKeyId")) {
                xksProxyAuthenticationCredentialType.setAccessKeyId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("RawSecretAccessKey")) {
                xksProxyAuthenticationCredentialType.setRawSecretAccessKey(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return xksProxyAuthenticationCredentialType;
    }

    public static XksProxyAuthenticationCredentialTypeJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new XksProxyAuthenticationCredentialTypeJsonUnmarshaller();
        }
        return instance;
    }
}
