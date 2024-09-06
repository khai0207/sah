package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.DefaultRequest;
import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.http.HttpMethodName;
import com.netease.nimlib.amazonaws.services.kms.model.ListGrantsRequest;
import com.netease.nimlib.amazonaws.transform.Marshaller;
import com.netease.nimlib.amazonaws.util.StringInputStream;
import com.netease.nimlib.amazonaws.util.StringUtils;
import com.netease.nimlib.amazonaws.util.json.AwsJsonWriter;
import com.netease.nimlib.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* loaded from: classes.dex */
public class ListGrantsRequestMarshaller implements Marshaller<Request<ListGrantsRequest>, ListGrantsRequest> {
    @Override // com.netease.nimlib.amazonaws.transform.Marshaller
    public Request<ListGrantsRequest> marshall(ListGrantsRequest listGrantsRequest) {
        if (listGrantsRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListGrantsRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listGrantsRequest, "AWSKMS");
        defaultRequest.addHeader("X-Amz-Target", "TrentService.ListGrants");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (listGrantsRequest.getLimit() != null) {
                Integer limit = listGrantsRequest.getLimit();
                jsonWriter.name("Limit");
                jsonWriter.value(limit);
            }
            if (listGrantsRequest.getMarker() != null) {
                String marker = listGrantsRequest.getMarker();
                jsonWriter.name("Marker");
                jsonWriter.value(marker);
            }
            if (listGrantsRequest.getKeyId() != null) {
                String keyId = listGrantsRequest.getKeyId();
                jsonWriter.name("KeyId");
                jsonWriter.value(keyId);
            }
            if (listGrantsRequest.getGrantId() != null) {
                String grantId = listGrantsRequest.getGrantId();
                jsonWriter.name("GrantId");
                jsonWriter.value(grantId);
            }
            if (listGrantsRequest.getGranteePrincipal() != null) {
                String granteePrincipal = listGrantsRequest.getGranteePrincipal();
                jsonWriter.name("GranteePrincipal");
                jsonWriter.value(granteePrincipal);
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
