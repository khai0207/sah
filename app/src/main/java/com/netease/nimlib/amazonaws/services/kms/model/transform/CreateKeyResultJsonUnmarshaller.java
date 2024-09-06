package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.services.kms.model.CreateKeyResult;
import com.netease.nimlib.amazonaws.transform.JsonUnmarshallerContext;
import com.netease.nimlib.amazonaws.transform.Unmarshaller;
import com.netease.nimlib.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
public class CreateKeyResultJsonUnmarshaller implements Unmarshaller<CreateKeyResult, JsonUnmarshallerContext> {
    private static CreateKeyResultJsonUnmarshaller instance;

    @Override // com.netease.nimlib.amazonaws.transform.Unmarshaller
    public CreateKeyResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        CreateKeyResult createKeyResult = new CreateKeyResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("KeyMetadata")) {
                createKeyResult.setKeyMetadata(KeyMetadataJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return createKeyResult;
    }

    public static CreateKeyResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CreateKeyResultJsonUnmarshaller();
        }
        return instance;
    }
}
