package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.DefaultRequest;
import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.http.HttpMethodName;
import com.netease.nimlib.amazonaws.services.kms.model.GenerateMacRequest;
import com.netease.nimlib.amazonaws.transform.Marshaller;
import com.netease.nimlib.amazonaws.util.StringInputStream;
import com.netease.nimlib.amazonaws.util.StringUtils;
import com.netease.nimlib.amazonaws.util.json.AwsJsonWriter;
import com.netease.nimlib.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.util.List;

/* loaded from: classes.dex */
public class GenerateMacRequestMarshaller implements Marshaller<Request<GenerateMacRequest>, GenerateMacRequest> {
    @Override // com.netease.nimlib.amazonaws.transform.Marshaller
    public Request<GenerateMacRequest> marshall(GenerateMacRequest generateMacRequest) {
        if (generateMacRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(GenerateMacRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(generateMacRequest, "AWSKMS");
        defaultRequest.addHeader("X-Amz-Target", "TrentService.GenerateMac");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (generateMacRequest.getMessage() != null) {
                ByteBuffer message = generateMacRequest.getMessage();
                jsonWriter.name("Message");
                jsonWriter.value(message);
            }
            if (generateMacRequest.getKeyId() != null) {
                String keyId = generateMacRequest.getKeyId();
                jsonWriter.name("KeyId");
                jsonWriter.value(keyId);
            }
            if (generateMacRequest.getMacAlgorithm() != null) {
                String macAlgorithm = generateMacRequest.getMacAlgorithm();
                jsonWriter.name("MacAlgorithm");
                jsonWriter.value(macAlgorithm);
            }
            if (generateMacRequest.getGrantTokens() != null) {
                List<String> grantTokens = generateMacRequest.getGrantTokens();
                jsonWriter.name("GrantTokens");
                jsonWriter.beginArray();
                for (String str : grantTokens) {
                    if (str != null) {
                        jsonWriter.value(str);
                    }
                }
                jsonWriter.endArray();
            }
            if (generateMacRequest.getDryRun() != null) {
                Boolean dryRun = generateMacRequest.getDryRun();
                jsonWriter.name("DryRun");
                jsonWriter.value(dryRun.booleanValue());
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
