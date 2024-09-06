package com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform;

import com.netease.nimlib.amazonaws.services.cognitoidentity.model.MappingRule;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.RulesConfigurationType;
import com.netease.nimlib.amazonaws.util.json.AwsJsonWriter;
import java.util.List;

/* loaded from: classes.dex */
class RulesConfigurationTypeJsonMarshaller {
    private static RulesConfigurationTypeJsonMarshaller instance;

    RulesConfigurationTypeJsonMarshaller() {
    }

    public void marshall(RulesConfigurationType rulesConfigurationType, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (rulesConfigurationType.getRules() != null) {
            List<MappingRule> rules = rulesConfigurationType.getRules();
            awsJsonWriter.name("Rules");
            awsJsonWriter.beginArray();
            for (MappingRule mappingRule : rules) {
                if (mappingRule != null) {
                    MappingRuleJsonMarshaller.getInstance().marshall(mappingRule, awsJsonWriter);
                }
            }
            awsJsonWriter.endArray();
        }
        awsJsonWriter.endObject();
    }

    public static RulesConfigurationTypeJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new RulesConfigurationTypeJsonMarshaller();
        }
        return instance;
    }
}
