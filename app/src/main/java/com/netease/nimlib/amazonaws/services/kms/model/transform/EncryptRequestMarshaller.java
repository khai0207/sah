package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.DefaultRequest;
import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.http.HttpMethodName;
import com.netease.nimlib.amazonaws.services.kms.model.EncryptRequest;
import com.netease.nimlib.amazonaws.transform.Marshaller;
import com.netease.nimlib.amazonaws.util.StringInputStream;
import com.netease.nimlib.amazonaws.util.StringUtils;
import com.netease.nimlib.amazonaws.util.json.AwsJsonWriter;
import com.netease.nimlib.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class EncryptRequestMarshaller implements Marshaller<Request<EncryptRequest>, EncryptRequest> {
    @Override // com.netease.nimlib.amazonaws.transform.Marshaller
    public Request<EncryptRequest> marshall(EncryptRequest encryptRequest) {
        if (encryptRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(EncryptRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(encryptRequest, "AWSKMS");
        defaultRequest.addHeader("X-Amz-Target", "TrentService.Encrypt");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (encryptRequest.getKeyId() != null) {
                String keyId = encryptRequest.getKeyId();
                jsonWriter.name("KeyId");
                jsonWriter.value(keyId);
            }
            if (encryptRequest.getPlaintext() != null) {
                ByteBuffer plaintext = encryptRequest.getPlaintext();
                jsonWriter.name("Plaintext");
                jsonWriter.value(plaintext);
            }
            if (encryptRequest.getEncryptionContext() != null) {
                Map<String, String> encryptionContext = encryptRequest.getEncryptionContext();
                jsonWriter.name("EncryptionContext");
                jsonWriter.beginObject();
                for (Map.Entry<String, String> entry : encryptionContext.entrySet()) {
                    String value = entry.getValue();
                    if (value != null) {
                        jsonWriter.name(entry.getKey());
                        jsonWriter.value(value);
                    }
                }
                jsonWriter.endObject();
            }
            if (encryptRequest.getGrantTokens() != null) {
                List<String> grantTokens = encryptRequest.getGrantTokens();
                jsonWriter.name("GrantTokens");
                jsonWriter.beginArray();
                for (String str : grantTokens) {
                    if (str != null) {
                        jsonWriter.value(str);
                    }
                }
                jsonWriter.endArray();
            }
            if (encryptRequest.getEncryptionAlgorithm() != null) {
                String encryptionAlgorithm = encryptRequest.getEncryptionAlgorithm();
                jsonWriter.name("EncryptionAlgorithm");
                jsonWriter.value(encryptionAlgorithm);
            }
            if (encryptRequest.getDryRun() != null) {
                Boolean dryRun = encryptRequest.getDryRun();
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
