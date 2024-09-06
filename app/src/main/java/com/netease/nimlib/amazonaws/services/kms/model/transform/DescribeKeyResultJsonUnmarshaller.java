package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.services.kms.model.DescribeKeyResult;
import com.netease.nimlib.amazonaws.transform.JsonUnmarshallerContext;
import com.netease.nimlib.amazonaws.transform.Unmarshaller;
import com.netease.nimlib.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
public class DescribeKeyResultJsonUnmarshaller implements Unmarshaller<DescribeKeyResult, JsonUnmarshallerContext> {
    private static DescribeKeyResultJsonUnmarshaller instance;

    @Override // com.netease.nimlib.amazonaws.transform.Unmarshaller
    public DescribeKeyResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DescribeKeyResult describeKeyResult = new DescribeKeyResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("KeyMetadata")) {
                describeKeyResult.setKeyMetadata(KeyMetadataJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return describeKeyResult;
    }

    public static DescribeKeyResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeKeyResultJsonUnmarshaller();
        }
        return instance;
    }
}
