package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.DefaultRequest;
import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.http.HttpMethodName;
import com.netease.nimlib.amazonaws.services.kms.model.DeleteAliasRequest;
import com.netease.nimlib.amazonaws.transform.Marshaller;
import com.netease.nimlib.amazonaws.util.StringInputStream;
import com.netease.nimlib.amazonaws.util.StringUtils;
import com.netease.nimlib.amazonaws.util.json.AwsJsonWriter;
import com.netease.nimlib.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* loaded from: classes.dex */
public class DeleteAliasRequestMarshaller implements Marshaller<Request<DeleteAliasRequest>, DeleteAliasRequest> {
    @Override // com.netease.nimlib.amazonaws.transform.Marshaller
    public Request<DeleteAliasRequest> marshall(DeleteAliasRequest deleteAliasRequest) {
        if (deleteAliasRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DeleteAliasRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(deleteAliasRequest, "AWSKMS");
        defaultRequest.addHeader("X-Amz-Target", "TrentService.DeleteAlias");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (deleteAliasRequest.getAliasName() != null) {
                String aliasName = deleteAliasRequest.getAliasName();
                jsonWriter.name("AliasName");
                jsonWriter.value(aliasName);
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
