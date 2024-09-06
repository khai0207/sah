package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.DefaultRequest;
import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.http.HttpMethodName;
import com.netease.nimlib.amazonaws.services.kms.model.VerifyRequest;
import com.netease.nimlib.amazonaws.transform.Marshaller;
import com.netease.nimlib.amazonaws.util.StringInputStream;
import com.netease.nimlib.amazonaws.util.StringUtils;
import com.netease.nimlib.amazonaws.util.json.AwsJsonWriter;
import com.netease.nimlib.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.util.List;

/* loaded from: classes.dex */
public class VerifyRequestMarshaller implements Marshaller<Request<VerifyRequest>, VerifyRequest> {
    @Override // com.netease.nimlib.amazonaws.transform.Marshaller
    public Request<VerifyRequest> marshall(VerifyRequest verifyRequest) {
        if (verifyRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(VerifyRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(verifyRequest, "AWSKMS");
        defaultRequest.addHeader("X-Amz-Target", "TrentService.Verify");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (verifyRequest.getKeyId() != null) {
                String keyId = verifyRequest.getKeyId();
                jsonWriter.name("KeyId");
                jsonWriter.value(keyId);
            }
            if (verifyRequest.getMessage() != null) {
                ByteBuffer message = verifyRequest.getMessage();
                jsonWriter.name("Message");
                jsonWriter.value(message);
            }
            if (verifyRequest.getMessageType() != null) {
                String messageType = verifyRequest.getMessageType();
                jsonWriter.name("MessageType");
                jsonWriter.value(messageType);
            }
            if (verifyRequest.getSignature() != null) {
                ByteBuffer signature = verifyRequest.getSignature();
                jsonWriter.name("Signature");
                jsonWriter.value(signature);
            }
            if (verifyRequest.getSigningAlgorithm() != null) {
                String signingAlgorithm = verifyRequest.getSigningAlgorithm();
                jsonWriter.name("SigningAlgorithm");
                jsonWriter.value(signingAlgorithm);
            }
            if (verifyRequest.getGrantTokens() != null) {
                List<String> grantTokens = verifyRequest.getGrantTokens();
                jsonWriter.name("GrantTokens");
                jsonWriter.beginArray();
                for (String str : grantTokens) {
                    if (str != null) {
                        jsonWriter.value(str);
                    }
                }
                jsonWriter.endArray();
            }
            if (verifyRequest.getDryRun() != null) {
                Boolean dryRun = verifyRequest.getDryRun();
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
