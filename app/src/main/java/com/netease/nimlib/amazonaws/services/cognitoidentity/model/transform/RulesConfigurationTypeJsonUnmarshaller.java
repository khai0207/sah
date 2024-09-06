package com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform;

import com.netease.nimlib.amazonaws.services.cognitoidentity.model.RulesConfigurationType;
import com.netease.nimlib.amazonaws.transform.JsonUnmarshallerContext;
import com.netease.nimlib.amazonaws.transform.ListUnmarshaller;
import com.netease.nimlib.amazonaws.transform.Unmarshaller;
import com.netease.nimlib.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
class RulesConfigurationTypeJsonUnmarshaller implements Unmarshaller<RulesConfigurationType, JsonUnmarshallerContext> {
    private static RulesConfigurationTypeJsonUnmarshaller instance;

    RulesConfigurationTypeJsonUnmarshaller() {
    }

    @Override // com.netease.nimlib.amazonaws.transform.Unmarshaller
    public RulesConfigurationType unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        RulesConfigurationType rulesConfigurationType = new RulesConfigurationType();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("Rules")) {
                rulesConfigurationType.setRules(new ListUnmarshaller(MappingRuleJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return rulesConfigurationType;
    }

    public static RulesConfigurationTypeJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new RulesConfigurationTypeJsonUnmarshaller();
        }
        return instance;
    }
}
