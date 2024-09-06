package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.services.kms.model.GrantConstraints;
import com.netease.nimlib.amazonaws.transform.JsonUnmarshallerContext;
import com.netease.nimlib.amazonaws.transform.MapUnmarshaller;
import com.netease.nimlib.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.netease.nimlib.amazonaws.transform.Unmarshaller;
import com.netease.nimlib.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
class GrantConstraintsJsonUnmarshaller implements Unmarshaller<GrantConstraints, JsonUnmarshallerContext> {
    private static GrantConstraintsJsonUnmarshaller instance;

    GrantConstraintsJsonUnmarshaller() {
    }

    @Override // com.netease.nimlib.amazonaws.transform.Unmarshaller
    public GrantConstraints unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        GrantConstraints grantConstraints = new GrantConstraints();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("EncryptionContextSubset")) {
                grantConstraints.setEncryptionContextSubset(new MapUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("EncryptionContextEquals")) {
                grantConstraints.setEncryptionContextEquals(new MapUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return grantConstraints;
    }

    public static GrantConstraintsJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new GrantConstraintsJsonUnmarshaller();
        }
        return instance;
    }
}
