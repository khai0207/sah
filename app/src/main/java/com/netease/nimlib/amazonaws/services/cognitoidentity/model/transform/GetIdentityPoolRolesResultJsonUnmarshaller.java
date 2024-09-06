package com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform;

import com.netease.nimlib.amazonaws.services.cognitoidentity.model.GetIdentityPoolRolesResult;
import com.netease.nimlib.amazonaws.transform.JsonUnmarshallerContext;
import com.netease.nimlib.amazonaws.transform.MapUnmarshaller;
import com.netease.nimlib.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.netease.nimlib.amazonaws.transform.Unmarshaller;
import com.netease.nimlib.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
public class GetIdentityPoolRolesResultJsonUnmarshaller implements Unmarshaller<GetIdentityPoolRolesResult, JsonUnmarshallerContext> {
    private static GetIdentityPoolRolesResultJsonUnmarshaller instance;

    @Override // com.netease.nimlib.amazonaws.transform.Unmarshaller
    public GetIdentityPoolRolesResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        GetIdentityPoolRolesResult getIdentityPoolRolesResult = new GetIdentityPoolRolesResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("IdentityPoolId")) {
                getIdentityPoolRolesResult.setIdentityPoolId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("Roles")) {
                getIdentityPoolRolesResult.setRoles(new MapUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("RoleMappings")) {
                getIdentityPoolRolesResult.setRoleMappings(new MapUnmarshaller(RoleMappingJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return getIdentityPoolRolesResult;
    }

    public static GetIdentityPoolRolesResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new GetIdentityPoolRolesResultJsonUnmarshaller();
        }
        return instance;
    }
}
