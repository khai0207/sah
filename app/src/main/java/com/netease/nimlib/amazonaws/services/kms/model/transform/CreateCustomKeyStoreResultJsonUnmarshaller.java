package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.services.kms.model.CreateCustomKeyStoreResult;
import com.netease.nimlib.amazonaws.transform.JsonUnmarshallerContext;
import com.netease.nimlib.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.netease.nimlib.amazonaws.transform.Unmarshaller;
import com.netease.nimlib.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
public class CreateCustomKeyStoreResultJsonUnmarshaller implements Unmarshaller<CreateCustomKeyStoreResult, JsonUnmarshallerContext> {
    private static CreateCustomKeyStoreResultJsonUnmarshaller instance;

    @Override // com.netease.nimlib.amazonaws.transform.Unmarshaller
    public CreateCustomKeyStoreResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        CreateCustomKeyStoreResult createCustomKeyStoreResult = new CreateCustomKeyStoreResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("CustomKeyStoreId")) {
                createCustomKeyStoreResult.setCustomKeyStoreId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return createCustomKeyStoreResult;
    }

    public static CreateCustomKeyStoreResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CreateCustomKeyStoreResultJsonUnmarshaller();
        }
        return instance;
    }
}
