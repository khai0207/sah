package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.auth.policy.internal.JsonDocumentFields;
import com.netease.nimlib.amazonaws.services.kms.model.XksKeyConfigurationType;
import com.netease.nimlib.amazonaws.util.json.AwsJsonWriter;

/* loaded from: classes.dex */
class XksKeyConfigurationTypeJsonMarshaller {
    private static XksKeyConfigurationTypeJsonMarshaller instance;

    XksKeyConfigurationTypeJsonMarshaller() {
    }

    public void marshall(XksKeyConfigurationType xksKeyConfigurationType, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (xksKeyConfigurationType.getId() != null) {
            String id = xksKeyConfigurationType.getId();
            awsJsonWriter.name(JsonDocumentFields.POLICY_ID);
            awsJsonWriter.value(id);
        }
        awsJsonWriter.endObject();
    }

    public static XksKeyConfigurationTypeJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new XksKeyConfigurationTypeJsonMarshaller();
        }
        return instance;
    }
}
