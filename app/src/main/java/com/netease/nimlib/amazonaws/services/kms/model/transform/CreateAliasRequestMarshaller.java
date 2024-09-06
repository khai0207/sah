package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.DefaultRequest;
import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.http.HttpMethodName;
import com.netease.nimlib.amazonaws.services.kms.model.CreateAliasRequest;
import com.netease.nimlib.amazonaws.transform.Marshaller;
import com.netease.nimlib.amazonaws.util.StringInputStream;
import com.netease.nimlib.amazonaws.util.StringUtils;
import com.netease.nimlib.amazonaws.util.json.AwsJsonWriter;
import com.netease.nimlib.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* loaded from: classes.dex */
public class CreateAliasRequestMarshaller implements Marshaller<Request<CreateAliasRequest>, CreateAliasRequest> {
    @Override // com.netease.nimlib.amazonaws.transform.Marshaller
    public Request<CreateAliasRequest> marshall(CreateAliasRequest createAliasRequest) {
        if (createAliasRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(CreateAliasRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(createAliasRequest, "AWSKMS");
        defaultRequest.addHeader("X-Amz-Target", "TrentService.CreateAlias");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (createAliasRequest.getAliasName() != null) {
                String aliasName = createAliasRequest.getAliasName();
                jsonWriter.name("AliasName");
                jsonWriter.value(aliasName);
            }
            if (createAliasRequest.getTargetKeyId() != null) {
                String targetKeyId = createAliasRequest.getTargetKeyId();
                jsonWriter.name("TargetKeyId");
                jsonWriter.value(targetKeyId);
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
