package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.services.kms.model.ReEncryptResult;
import com.netease.nimlib.amazonaws.transform.JsonUnmarshallerContext;
import com.netease.nimlib.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.netease.nimlib.amazonaws.transform.Unmarshaller;
import com.netease.nimlib.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
public class ReEncryptResultJsonUnmarshaller implements Unmarshaller<ReEncryptResult, JsonUnmarshallerContext> {
    private static ReEncryptResultJsonUnmarshaller instance;

    @Override // com.netease.nimlib.amazonaws.transform.Unmarshaller
    public ReEncryptResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ReEncryptResult reEncryptResult = new ReEncryptResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("CiphertextBlob")) {
                reEncryptResult.setCiphertextBlob(SimpleTypeJsonUnmarshallers.ByteBufferJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("SourceKeyId")) {
                reEncryptResult.setSourceKeyId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("KeyId")) {
                reEncryptResult.setKeyId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("SourceEncryptionAlgorithm")) {
                reEncryptResult.setSourceEncryptionAlgorithm(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("DestinationEncryptionAlgorithm")) {
                reEncryptResult.setDestinationEncryptionAlgorithm(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return reEncryptResult;
    }

    public static ReEncryptResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ReEncryptResultJsonUnmarshaller();
        }
        return instance;
    }
}
