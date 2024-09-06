package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.DefaultRequest;
import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.http.HttpMethodName;
import com.netease.nimlib.amazonaws.services.kms.model.ListRetirableGrantsRequest;
import com.netease.nimlib.amazonaws.transform.Marshaller;
import com.netease.nimlib.amazonaws.util.StringInputStream;
import com.netease.nimlib.amazonaws.util.StringUtils;
import com.netease.nimlib.amazonaws.util.json.AwsJsonWriter;
import com.netease.nimlib.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* loaded from: classes.dex */
public class ListRetirableGrantsRequestMarshaller implements Marshaller<Request<ListRetirableGrantsRequest>, ListRetirableGrantsRequest> {
    @Override // com.netease.nimlib.amazonaws.transform.Marshaller
    public Request<ListRetirableGrantsRequest> marshall(ListRetirableGrantsRequest listRetirableGrantsRequest) {
        if (listRetirableGrantsRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListRetirableGrantsRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listRetirableGrantsRequest, "AWSKMS");
        defaultRequest.addHeader("X-Amz-Target", "TrentService.ListRetirableGrants");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (listRetirableGrantsRequest.getLimit() != null) {
                Integer limit = listRetirableGrantsRequest.getLimit();
                jsonWriter.name("Limit");
                jsonWriter.value(limit);
            }
            if (listRetirableGrantsRequest.getMarker() != null) {
                String marker = listRetirableGrantsRequest.getMarker();
                jsonWriter.name("Marker");
                jsonWriter.value(marker);
            }
            if (listRetirableGrantsRequest.getRetiringPrincipal() != null) {
                String retiringPrincipal = listRetirableGrantsRequest.getRetiringPrincipal();
                jsonWriter.name("RetiringPrincipal");
                jsonWriter.value(retiringPrincipal);
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
