package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.services.kms.model.ReplicateKeyResult;
import com.netease.nimlib.amazonaws.transform.JsonUnmarshallerContext;
import com.netease.nimlib.amazonaws.transform.ListUnmarshaller;
import com.netease.nimlib.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.netease.nimlib.amazonaws.transform.Unmarshaller;
import com.netease.nimlib.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
public class ReplicateKeyResultJsonUnmarshaller implements Unmarshaller<ReplicateKeyResult, JsonUnmarshallerContext> {
    private static ReplicateKeyResultJsonUnmarshaller instance;

    @Override // com.netease.nimlib.amazonaws.transform.Unmarshaller
    public ReplicateKeyResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ReplicateKeyResult replicateKeyResult = new ReplicateKeyResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("ReplicaKeyMetadata")) {
                replicateKeyResult.setReplicaKeyMetadata(KeyMetadataJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("ReplicaPolicy")) {
                replicateKeyResult.setReplicaPolicy(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("ReplicaTags")) {
                replicateKeyResult.setReplicaTags(new ListUnmarshaller(TagJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return replicateKeyResult;
    }

    public static ReplicateKeyResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ReplicateKeyResultJsonUnmarshaller();
        }
        return instance;
    }
}
