package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.services.kms.model.MultiRegionConfiguration;
import com.netease.nimlib.amazonaws.transform.JsonUnmarshallerContext;
import com.netease.nimlib.amazonaws.transform.ListUnmarshaller;
import com.netease.nimlib.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.netease.nimlib.amazonaws.transform.Unmarshaller;
import com.netease.nimlib.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
class MultiRegionConfigurationJsonUnmarshaller implements Unmarshaller<MultiRegionConfiguration, JsonUnmarshallerContext> {
    private static MultiRegionConfigurationJsonUnmarshaller instance;

    MultiRegionConfigurationJsonUnmarshaller() {
    }

    @Override // com.netease.nimlib.amazonaws.transform.Unmarshaller
    public MultiRegionConfiguration unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        MultiRegionConfiguration multiRegionConfiguration = new MultiRegionConfiguration();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("MultiRegionKeyType")) {
                multiRegionConfiguration.setMultiRegionKeyType(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("PrimaryKey")) {
                multiRegionConfiguration.setPrimaryKey(MultiRegionKeyJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("ReplicaKeys")) {
                multiRegionConfiguration.setReplicaKeys(new ListUnmarshaller(MultiRegionKeyJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return multiRegionConfiguration;
    }

    public static MultiRegionConfigurationJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new MultiRegionConfigurationJsonUnmarshaller();
        }
        return instance;
    }
}
