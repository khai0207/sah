package com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.DefaultRequest;
import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.http.HttpMethodName;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.MergeDeveloperIdentitiesRequest;
import com.netease.nimlib.amazonaws.transform.Marshaller;
import com.netease.nimlib.amazonaws.util.StringInputStream;
import com.netease.nimlib.amazonaws.util.StringUtils;
import com.netease.nimlib.amazonaws.util.json.AwsJsonWriter;
import com.netease.nimlib.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* loaded from: classes.dex */
public class MergeDeveloperIdentitiesRequestMarshaller implements Marshaller<Request<MergeDeveloperIdentitiesRequest>, MergeDeveloperIdentitiesRequest> {
    @Override // com.netease.nimlib.amazonaws.transform.Marshaller
    public Request<MergeDeveloperIdentitiesRequest> marshall(MergeDeveloperIdentitiesRequest mergeDeveloperIdentitiesRequest) {
        if (mergeDeveloperIdentitiesRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(MergeDeveloperIdentitiesRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(mergeDeveloperIdentitiesRequest, "AmazonCognitoIdentity");
        defaultRequest.addHeader("X-Amz-Target", "AWSCognitoIdentityService.MergeDeveloperIdentities");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (mergeDeveloperIdentitiesRequest.getSourceUserIdentifier() != null) {
                String sourceUserIdentifier = mergeDeveloperIdentitiesRequest.getSourceUserIdentifier();
                jsonWriter.name("SourceUserIdentifier");
                jsonWriter.value(sourceUserIdentifier);
            }
            if (mergeDeveloperIdentitiesRequest.getDestinationUserIdentifier() != null) {
                String destinationUserIdentifier = mergeDeveloperIdentitiesRequest.getDestinationUserIdentifier();
                jsonWriter.name("DestinationUserIdentifier");
                jsonWriter.value(destinationUserIdentifier);
            }
            if (mergeDeveloperIdentitiesRequest.getDeveloperProviderName() != null) {
                String developerProviderName = mergeDeveloperIdentitiesRequest.getDeveloperProviderName();
                jsonWriter.name("DeveloperProviderName");
                jsonWriter.value(developerProviderName);
            }
            if (mergeDeveloperIdentitiesRequest.getIdentityPoolId() != null) {
                String identityPoolId = mergeDeveloperIdentitiesRequest.getIdentityPoolId();
                jsonWriter.name("IdentityPoolId");
                jsonWriter.value(identityPoolId);
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
