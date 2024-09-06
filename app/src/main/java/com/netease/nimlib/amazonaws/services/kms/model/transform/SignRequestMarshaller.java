package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.DefaultRequest;
import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.http.HttpMethodName;
import com.netease.nimlib.amazonaws.services.kms.model.SignRequest;
import com.netease.nimlib.amazonaws.transform.Marshaller;
import com.netease.nimlib.amazonaws.util.StringInputStream;
import com.netease.nimlib.amazonaws.util.StringUtils;
import com.netease.nimlib.amazonaws.util.json.AwsJsonWriter;
import com.netease.nimlib.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.util.List;

/* loaded from: classes.dex */
public class SignRequestMarshaller implements Marshaller<Request<SignRequest>, SignRequest> {
    @Override // com.netease.nimlib.amazonaws.transform.Marshaller
    public Request<SignRequest> marshall(SignRequest signRequest) {
        if (signRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(SignRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(signRequest, "AWSKMS");
        defaultRequest.addHeader("X-Amz-Target", "TrentService.Sign");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (signRequest.getKeyId() != null) {
                String keyId = signRequest.getKeyId();
                jsonWriter.name("KeyId");
                jsonWriter.value(keyId);
            }
            if (signRequest.getMessage() != null) {
                ByteBuffer message = signRequest.getMessage();
                jsonWriter.name("Message");
                jsonWriter.value(message);
            }
            if (signRequest.getMessageType() != null) {
                String messageType = signRequest.getMessageType();
                jsonWriter.name("MessageType");
                jsonWriter.value(messageType);
            }
            if (signRequest.getGrantTokens() != null) {
                List<String> grantTokens = signRequest.getGrantTokens();
                jsonWriter.name("GrantTokens");
                jsonWriter.beginArray();
                for (String str : grantTokens) {
                    if (str != null) {
                        jsonWriter.value(str);
                    }
                }
                jsonWriter.endArray();
            }
            if (signRequest.getSigningAlgorithm() != null) {
                String signingAlgorithm = signRequest.getSigningAlgorithm();
                jsonWriter.name("SigningAlgorithm");
                jsonWriter.value(signingAlgorithm);
            }
            if (signRequest.getDryRun() != null) {
                Boolean dryRun = signRequest.getDryRun();
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
