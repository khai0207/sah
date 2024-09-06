package com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform;

import com.netease.nimlib.amazonaws.services.cognitoidentity.model.SetPrincipalTagAttributeMapResult;
import com.netease.nimlib.amazonaws.transform.JsonUnmarshallerContext;
import com.netease.nimlib.amazonaws.transform.MapUnmarshaller;
import com.netease.nimlib.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.netease.nimlib.amazonaws.transform.Unmarshaller;
import com.netease.nimlib.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
public class SetPrincipalTagAttributeMapResultJsonUnmarshaller implements Unmarshaller<SetPrincipalTagAttributeMapResult, JsonUnmarshallerContext> {
    private static SetPrincipalTagAttributeMapResultJsonUnmarshaller instance;

    @Override // com.netease.nimlib.amazonaws.transform.Unmarshaller
    public SetPrincipalTagAttributeMapResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        SetPrincipalTagAttributeMapResult setPrincipalTagAttributeMapResult = new SetPrincipalTagAttributeMapResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("IdentityPoolId")) {
                setPrincipalTagAttributeMapResult.setIdentityPoolId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("IdentityProviderName")) {
                setPrincipalTagAttributeMapResult.setIdentityProviderName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("UseDefaults")) {
                setPrincipalTagAttributeMapResult.setUseDefaults(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("PrincipalTags")) {
                setPrincipalTagAttributeMapResult.setPrincipalTags(new MapUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return setPrincipalTagAttributeMapResult;
    }

    public static SetPrincipalTagAttributeMapResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new SetPrincipalTagAttributeMapResultJsonUnmarshaller();
        }
        return instance;
    }
}
