package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.services.kms.model.CreateGrantResult;
import com.netease.nimlib.amazonaws.transform.JsonUnmarshallerContext;
import com.netease.nimlib.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.netease.nimlib.amazonaws.transform.Unmarshaller;
import com.netease.nimlib.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
public class CreateGrantResultJsonUnmarshaller implements Unmarshaller<CreateGrantResult, JsonUnmarshallerContext> {
    private static CreateGrantResultJsonUnmarshaller instance;

    @Override // com.netease.nimlib.amazonaws.transform.Unmarshaller
    public CreateGrantResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        CreateGrantResult createGrantResult = new CreateGrantResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("GrantToken")) {
                createGrantResult.setGrantToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("GrantId")) {
                createGrantResult.setGrantId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return createGrantResult;
    }

    public static CreateGrantResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CreateGrantResultJsonUnmarshaller();
        }
        return instance;
    }
}
