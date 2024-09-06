package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.auth.policy.internal.JsonDocumentFields;
import com.netease.nimlib.amazonaws.services.kms.model.XksKeyConfigurationType;
import com.netease.nimlib.amazonaws.transform.JsonUnmarshallerContext;
import com.netease.nimlib.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.netease.nimlib.amazonaws.transform.Unmarshaller;
import com.netease.nimlib.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
class XksKeyConfigurationTypeJsonUnmarshaller implements Unmarshaller<XksKeyConfigurationType, JsonUnmarshallerContext> {
    private static XksKeyConfigurationTypeJsonUnmarshaller instance;

    XksKeyConfigurationTypeJsonUnmarshaller() {
    }

    @Override // com.netease.nimlib.amazonaws.transform.Unmarshaller
    public XksKeyConfigurationType unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        XksKeyConfigurationType xksKeyConfigurationType = new XksKeyConfigurationType();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals(JsonDocumentFields.POLICY_ID)) {
                xksKeyConfigurationType.setId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return xksKeyConfigurationType;
    }

    public static XksKeyConfigurationTypeJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new XksKeyConfigurationTypeJsonUnmarshaller();
        }
        return instance;
    }
}
