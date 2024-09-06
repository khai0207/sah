package com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform;

import com.netease.nimlib.amazonaws.services.cognitoidentity.model.CreateIdentityPoolResult;
import com.netease.nimlib.amazonaws.transform.JsonUnmarshallerContext;
import com.netease.nimlib.amazonaws.transform.ListUnmarshaller;
import com.netease.nimlib.amazonaws.transform.MapUnmarshaller;
import com.netease.nimlib.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.netease.nimlib.amazonaws.transform.Unmarshaller;
import com.netease.nimlib.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
public class CreateIdentityPoolResultJsonUnmarshaller implements Unmarshaller<CreateIdentityPoolResult, JsonUnmarshallerContext> {
    private static CreateIdentityPoolResultJsonUnmarshaller instance;

    @Override // com.netease.nimlib.amazonaws.transform.Unmarshaller
    public CreateIdentityPoolResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        CreateIdentityPoolResult createIdentityPoolResult = new CreateIdentityPoolResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("IdentityPoolId")) {
                createIdentityPoolResult.setIdentityPoolId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("IdentityPoolName")) {
                createIdentityPoolResult.setIdentityPoolName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("AllowUnauthenticatedIdentities")) {
                createIdentityPoolResult.setAllowUnauthenticatedIdentities(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("AllowClassicFlow")) {
                createIdentityPoolResult.setAllowClassicFlow(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("SupportedLoginProviders")) {
                createIdentityPoolResult.setSupportedLoginProviders(new MapUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("DeveloperProviderName")) {
                createIdentityPoolResult.setDeveloperProviderName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("OpenIdConnectProviderARNs")) {
                createIdentityPoolResult.setOpenIdConnectProviderARNs(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("CognitoIdentityProviders")) {
                createIdentityPoolResult.setCognitoIdentityProviders(new ListUnmarshaller(CognitoIdentityProviderJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("SamlProviderARNs")) {
                createIdentityPoolResult.setSamlProviderARNs(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("IdentityPoolTags")) {
                createIdentityPoolResult.setIdentityPoolTags(new MapUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return createIdentityPoolResult;
    }

    public static CreateIdentityPoolResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CreateIdentityPoolResultJsonUnmarshaller();
        }
        return instance;
    }
}
