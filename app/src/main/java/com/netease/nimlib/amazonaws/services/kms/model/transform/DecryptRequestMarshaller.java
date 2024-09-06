package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.DefaultRequest;
import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.http.HttpMethodName;
import com.netease.nimlib.amazonaws.services.kms.model.DecryptRequest;
import com.netease.nimlib.amazonaws.services.kms.model.RecipientInfo;
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
public class DecryptRequestMarshaller implements Marshaller<Request<DecryptRequest>, DecryptRequest> {
    @Override // com.netease.nimlib.amazonaws.transform.Marshaller
    public Request<DecryptRequest> marshall(DecryptRequest decryptRequest) {
        if (decryptRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DecryptRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(decryptRequest, "AWSKMS");
        defaultRequest.addHeader("X-Amz-Target", "TrentService.Decrypt");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (decryptRequest.getCiphertextBlob() != null) {
                ByteBuffer ciphertextBlob = decryptRequest.getCiphertextBlob();
                jsonWriter.name("CiphertextBlob");
                jsonWriter.value(ciphertextBlob);
            }
            if (decryptRequest.getEncryptionContext() != null) {
                Map<String, String> encryptionContext = decryptRequest.getEncryptionContext();
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
            if (decryptRequest.getGrantTokens() != null) {
                List<String> grantTokens = decryptRequest.getGrantTokens();
                jsonWriter.name("GrantTokens");
                jsonWriter.beginArray();
                for (String str : grantTokens) {
                    if (str != null) {
                        jsonWriter.value(str);
                    }
                }
                jsonWriter.endArray();
            }
            if (decryptRequest.getKeyId() != null) {
                String keyId = decryptRequest.getKeyId();
                jsonWriter.name("KeyId");
                jsonWriter.value(keyId);
            }
            if (decryptRequest.getEncryptionAlgorithm() != null) {
                String encryptionAlgorithm = decryptRequest.getEncryptionAlgorithm();
                jsonWriter.name("EncryptionAlgorithm");
                jsonWriter.value(encryptionAlgorithm);
            }
            if (decryptRequest.getRecipient() != null) {
                RecipientInfo recipient = decryptRequest.getRecipient();
                jsonWriter.name("Recipient");
                RecipientInfoJsonMarshaller.getInstance().marshall(recipient, jsonWriter);
            }
            if (decryptRequest.getDryRun() != null) {
                Boolean dryRun = decryptRequest.getDryRun();
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
