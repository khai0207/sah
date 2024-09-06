package com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform;

import com.netease.nimlib.amazonaws.services.cognitoidentity.model.DescribeIdentityResult;
import com.netease.nimlib.amazonaws.transform.JsonUnmarshallerContext;
import com.netease.nimlib.amazonaws.transform.ListUnmarshaller;
import com.netease.nimlib.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.netease.nimlib.amazonaws.transform.Unmarshaller;
import com.netease.nimlib.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
public class DescribeIdentityResultJsonUnmarshaller implements Unmarshaller<DescribeIdentityResult, JsonUnmarshallerContext> {
    private static DescribeIdentityResultJsonUnmarshaller instance;

    @Override // com.netease.nimlib.amazonaws.transform.Unmarshaller
    public DescribeIdentityResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DescribeIdentityResult describeIdentityResult = new DescribeIdentityResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("IdentityId")) {
                describeIdentityResult.setIdentityId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("Logins")) {
                describeIdentityResult.setLogins(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("CreationDate")) {
                describeIdentityResult.setCreationDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("LastModifiedDate")) {
                describeIdentityResult.setLastModifiedDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return describeIdentityResult;
    }

    public static DescribeIdentityResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeIdentityResultJsonUnmarshaller();
        }
        return instance;
    }
}
