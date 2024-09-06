package com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform;

import com.netease.nimlib.amazonaws.services.cognitoidentity.model.RoleMapping;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.RulesConfigurationType;
import com.netease.nimlib.amazonaws.util.json.AwsJsonWriter;
import com.talkingdata.sdk.cs;

/* loaded from: classes.dex */
class RoleMappingJsonMarshaller {
    private static RoleMappingJsonMarshaller instance;

    RoleMappingJsonMarshaller() {
    }

    public void marshall(RoleMapping roleMapping, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (roleMapping.getType() != null) {
            String type = roleMapping.getType();
            awsJsonWriter.name(cs.b.a);
            awsJsonWriter.value(type);
        }
        if (roleMapping.getAmbiguousRoleResolution() != null) {
            String ambiguousRoleResolution = roleMapping.getAmbiguousRoleResolution();
            awsJsonWriter.name("AmbiguousRoleResolution");
            awsJsonWriter.value(ambiguousRoleResolution);
        }
        if (roleMapping.getRulesConfiguration() != null) {
            RulesConfigurationType rulesConfiguration = roleMapping.getRulesConfiguration();
            awsJsonWriter.name("RulesConfiguration");
            RulesConfigurationTypeJsonMarshaller.getInstance().marshall(rulesConfiguration, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }

    public static RoleMappingJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new RoleMappingJsonMarshaller();
        }
        return instance;
    }
}
