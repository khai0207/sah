package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.services.kms.model.GenerateDataKeyWithoutPlaintextResult;
import com.netease.nimlib.amazonaws.transform.JsonUnmarshallerContext;
import com.netease.nimlib.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.netease.nimlib.amazonaws.transform.Unmarshaller;
import com.netease.nimlib.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
public class GenerateDataKeyWithoutPlaintextResultJsonUnmarshaller implements Unmarshaller<GenerateDataKeyWithoutPlaintextResult, JsonUnmarshallerContext> {
    private static GenerateDataKeyWithoutPlaintextResultJsonUnmarshaller instance;

    @Override // com.netease.nimlib.amazonaws.transform.Unmarshaller
    public GenerateDataKeyWithoutPlaintextResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        GenerateDataKeyWithoutPlaintextResult generateDataKeyWithoutPlaintextResult = new GenerateDataKeyWithoutPlaintextResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("CiphertextBlob")) {
                generateDataKeyWithoutPlaintextResult.setCiphertextBlob(SimpleTypeJsonUnmarshallers.ByteBufferJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("KeyId")) {
                generateDataKeyWithoutPlaintextResult.setKeyId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return generateDataKeyWithoutPlaintextResult;
    }

    public static GenerateDataKeyWithoutPlaintextResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new GenerateDataKeyWithoutPlaintextResultJsonUnmarshaller();
        }
        return instance;
    }
}
