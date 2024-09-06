package com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform;

import com.netease.nimlib.amazonaws.services.cognitoidentity.model.CognitoIdentityProvider;
import com.netease.nimlib.amazonaws.transform.JsonUnmarshallerContext;
import com.netease.nimlib.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.netease.nimlib.amazonaws.transform.Unmarshaller;
import com.netease.nimlib.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
class CognitoIdentityProviderJsonUnmarshaller implements Unmarshaller<CognitoIdentityProvider, JsonUnmarshallerContext> {
    private static CognitoIdentityProviderJsonUnmarshaller instance;

    CognitoIdentityProviderJsonUnmarshaller() {
    }

    @Override // com.netease.nimlib.amazonaws.transform.Unmarshaller
    public CognitoIdentityProvider unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        CognitoIdentityProvider cognitoIdentityProvider = new CognitoIdentityProvider();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("ProviderName")) {
                cognitoIdentityProvider.setProviderName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("ClientId")) {
                cognitoIdentityProvider.setClientId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("ServerSideTokenCheck")) {
                cognitoIdentityProvider.setServerSideTokenCheck(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return cognitoIdentityProvider;
    }

    public static CognitoIdentityProviderJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CognitoIdentityProviderJsonUnmarshaller();
        }
        return instance;
    }
}
