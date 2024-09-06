package com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform;

import com.netease.nimlib.amazonaws.services.cognitoidentity.model.IdentityPoolShortDescription;
import com.netease.nimlib.amazonaws.util.json.AwsJsonWriter;

/* loaded from: classes.dex */
class IdentityPoolShortDescriptionJsonMarshaller {
    private static IdentityPoolShortDescriptionJsonMarshaller instance;

    IdentityPoolShortDescriptionJsonMarshaller() {
    }

    public void marshall(IdentityPoolShortDescription identityPoolShortDescription, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (identityPoolShortDescription.getIdentityPoolId() != null) {
            String identityPoolId = identityPoolShortDescription.getIdentityPoolId();
            awsJsonWriter.name("IdentityPoolId");
            awsJsonWriter.value(identityPoolId);
        }
        if (identityPoolShortDescription.getIdentityPoolName() != null) {
            String identityPoolName = identityPoolShortDescription.getIdentityPoolName();
            awsJsonWriter.name("IdentityPoolName");
            awsJsonWriter.value(identityPoolName);
        }
        awsJsonWriter.endObject();
    }

    public static IdentityPoolShortDescriptionJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new IdentityPoolShortDescriptionJsonMarshaller();
        }
        return instance;
    }
}
