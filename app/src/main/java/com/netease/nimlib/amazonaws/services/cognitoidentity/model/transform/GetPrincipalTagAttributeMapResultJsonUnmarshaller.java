package com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform;

import com.netease.nimlib.amazonaws.services.cognitoidentity.model.GetPrincipalTagAttributeMapResult;
import com.netease.nimlib.amazonaws.transform.JsonUnmarshallerContext;
import com.netease.nimlib.amazonaws.transform.MapUnmarshaller;
import com.netease.nimlib.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.netease.nimlib.amazonaws.transform.Unmarshaller;
import com.netease.nimlib.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
public class GetPrincipalTagAttributeMapResultJsonUnmarshaller implements Unmarshaller<GetPrincipalTagAttributeMapResult, JsonUnmarshallerContext> {
    private static GetPrincipalTagAttributeMapResultJsonUnmarshaller instance;

    @Override // com.netease.nimlib.amazonaws.transform.Unmarshaller
    public GetPrincipalTagAttributeMapResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        GetPrincipalTagAttributeMapResult getPrincipalTagAttributeMapResult = new GetPrincipalTagAttributeMapResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("IdentityPoolId")) {
                getPrincipalTagAttributeMapResult.setIdentityPoolId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("IdentityProviderName")) {
                getPrincipalTagAttributeMapResult.setIdentityProviderName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("UseDefaults")) {
                getPrincipalTagAttributeMapResult.setUseDefaults(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("PrincipalTags")) {
                getPrincipalTagAttributeMapResult.setPrincipalTags(new MapUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return getPrincipalTagAttributeMapResult;
    }

    public static GetPrincipalTagAttributeMapResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new GetPrincipalTagAttributeMapResultJsonUnmarshaller();
        }
        return instance;
    }
}
