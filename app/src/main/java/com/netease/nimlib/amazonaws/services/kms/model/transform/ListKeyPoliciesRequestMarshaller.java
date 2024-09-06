package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.DefaultRequest;
import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.http.HttpMethodName;
import com.netease.nimlib.amazonaws.services.kms.model.ListKeyPoliciesRequest;
import com.netease.nimlib.amazonaws.transform.Marshaller;
import com.netease.nimlib.amazonaws.util.StringInputStream;
import com.netease.nimlib.amazonaws.util.StringUtils;
import com.netease.nimlib.amazonaws.util.json.AwsJsonWriter;
import com.netease.nimlib.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* loaded from: classes.dex */
public class ListKeyPoliciesRequestMarshaller implements Marshaller<Request<ListKeyPoliciesRequest>, ListKeyPoliciesRequest> {
    @Override // com.netease.nimlib.amazonaws.transform.Marshaller
    public Request<ListKeyPoliciesRequest> marshall(ListKeyPoliciesRequest listKeyPoliciesRequest) {
        if (listKeyPoliciesRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListKeyPoliciesRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listKeyPoliciesRequest, "AWSKMS");
        defaultRequest.addHeader("X-Amz-Target", "TrentService.ListKeyPolicies");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (listKeyPoliciesRequest.getKeyId() != null) {
                String keyId = listKeyPoliciesRequest.getKeyId();
                jsonWriter.name("KeyId");
                jsonWriter.value(keyId);
            }
            if (listKeyPoliciesRequest.getLimit() != null) {
                Integer limit = listKeyPoliciesRequest.getLimit();
                jsonWriter.name("Limit");
                jsonWriter.value(limit);
            }
            if (listKeyPoliciesRequest.getMarker() != null) {
                String marker = listKeyPoliciesRequest.getMarker();
                jsonWriter.name("Marker");
                jsonWriter.value(marker);
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
