package com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.DefaultRequest;
import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.http.HttpMethodName;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.GetCredentialsForIdentityRequest;
import com.netease.nimlib.amazonaws.transform.Marshaller;
import com.netease.nimlib.amazonaws.util.StringInputStream;
import com.netease.nimlib.amazonaws.util.StringUtils;
import com.netease.nimlib.amazonaws.util.json.AwsJsonWriter;
import com.netease.nimlib.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;
import java.util.Map;

/* loaded from: classes.dex */
public class GetCredentialsForIdentityRequestMarshaller implements Marshaller<Request<GetCredentialsForIdentityRequest>, GetCredentialsForIdentityRequest> {
    @Override // com.netease.nimlib.amazonaws.transform.Marshaller
    public Request<GetCredentialsForIdentityRequest> marshall(GetCredentialsForIdentityRequest getCredentialsForIdentityRequest) {
        if (getCredentialsForIdentityRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(GetCredentialsForIdentityRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(getCredentialsForIdentityRequest, "AmazonCognitoIdentity");
        defaultRequest.addHeader("X-Amz-Target", "AWSCognitoIdentityService.GetCredentialsForIdentity");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (getCredentialsForIdentityRequest.getIdentityId() != null) {
                String identityId = getCredentialsForIdentityRequest.getIdentityId();
                jsonWriter.name("IdentityId");
                jsonWriter.value(identityId);
            }
            if (getCredentialsForIdentityRequest.getLogins() != null) {
                Map<String, String> logins = getCredentialsForIdentityRequest.getLogins();
                jsonWriter.name("Logins");
                jsonWriter.beginObject();
                for (Map.Entry<String, String> entry : logins.entrySet()) {
                    String value = entry.getValue();
                    if (value != null) {
                        jsonWriter.name(entry.getKey());
                        jsonWriter.value(value);
                    }
                }
                jsonWriter.endObject();
            }
            if (getCredentialsForIdentityRequest.getCustomRoleArn() != null) {
                String customRoleArn = getCredentialsForIdentityRequest.getCustomRoleArn();
                jsonWriter.name("CustomRoleArn");
                jsonWriter.value(customRoleArn);
            }
            jsonWriter.endObject();
            jsonWriter.close();
            String stringWriter2 = stringWriter.toString();
            byte[] bytes = stringWriter2.getBytes(StringUtils.UTF8);
            defaultRequest.setContent(new StringInputStream(stringWriter2));
            defaultRequest.addHeader("Content-Length", Integer.toString(bytes.length));
            if (!defaultRequest.getHeaders().containsKey("Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.1");
            }
            return defaultRequest;
        } catch (Throwable th) {
            throw new AmazonClientException("Unable to marshall request to JSON: " + th.getMessage(), th);
        }
    }
}
