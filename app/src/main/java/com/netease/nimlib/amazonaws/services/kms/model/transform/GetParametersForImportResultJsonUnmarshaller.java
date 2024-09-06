package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.services.kms.model.GetParametersForImportResult;
import com.netease.nimlib.amazonaws.transform.JsonUnmarshallerContext;
import com.netease.nimlib.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.netease.nimlib.amazonaws.transform.Unmarshaller;
import com.netease.nimlib.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
public class GetParametersForImportResultJsonUnmarshaller implements Unmarshaller<GetParametersForImportResult, JsonUnmarshallerContext> {
    private static GetParametersForImportResultJsonUnmarshaller instance;

    @Override // com.netease.nimlib.amazonaws.transform.Unmarshaller
    public GetParametersForImportResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        GetParametersForImportResult getParametersForImportResult = new GetParametersForImportResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("KeyId")) {
                getParametersForImportResult.setKeyId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("ImportToken")) {
                getParametersForImportResult.setImportToken(SimpleTypeJsonUnmarshallers.ByteBufferJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("PublicKey")) {
                getParametersForImportResult.setPublicKey(SimpleTypeJsonUnmarshallers.ByteBufferJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("ParametersValidTo")) {
                getParametersForImportResult.setParametersValidTo(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return getParametersForImportResult;
    }

    public static GetParametersForImportResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new GetParametersForImportResultJsonUnmarshaller();
        }
        return instance;
    }
}
