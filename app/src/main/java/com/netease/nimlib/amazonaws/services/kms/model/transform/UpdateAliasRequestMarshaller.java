package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.DefaultRequest;
import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.http.HttpMethodName;
import com.netease.nimlib.amazonaws.services.kms.model.UpdateAliasRequest;
import com.netease.nimlib.amazonaws.transform.Marshaller;
import com.netease.nimlib.amazonaws.util.StringInputStream;
import com.netease.nimlib.amazonaws.util.StringUtils;
import com.netease.nimlib.amazonaws.util.json.AwsJsonWriter;
import com.netease.nimlib.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* loaded from: classes.dex */
public class UpdateAliasRequestMarshaller implements Marshaller<Request<UpdateAliasRequest>, UpdateAliasRequest> {
    @Override // com.netease.nimlib.amazonaws.transform.Marshaller
    public Request<UpdateAliasRequest> marshall(UpdateAliasRequest updateAliasRequest) {
        if (updateAliasRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(UpdateAliasRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(updateAliasRequest, "AWSKMS");
        defaultRequest.addHeader("X-Amz-Target", "TrentService.UpdateAlias");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (updateAliasRequest.getAliasName() != null) {
                String aliasName = updateAliasRequest.getAliasName();
                jsonWriter.name("AliasName");
                jsonWriter.value(aliasName);
            }
            if (updateAliasRequest.getTargetKeyId() != null) {
                String targetKeyId = updateAliasRequest.getTargetKeyId();
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
