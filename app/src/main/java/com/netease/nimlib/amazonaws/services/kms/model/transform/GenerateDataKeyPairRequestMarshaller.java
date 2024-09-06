package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.DefaultRequest;
import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.http.HttpMethodName;
import com.netease.nimlib.amazonaws.services.kms.model.GenerateDataKeyPairRequest;
import com.netease.nimlib.amazonaws.services.kms.model.RecipientInfo;
import com.netease.nimlib.amazonaws.transform.Marshaller;
import com.netease.nimlib.amazonaws.util.StringInputStream;
import com.netease.nimlib.amazonaws.util.StringUtils;
import com.netease.nimlib.amazonaws.util.json.AwsJsonWriter;
import com.netease.nimlib.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class GenerateDataKeyPairRequestMarshaller implements Marshaller<Request<GenerateDataKeyPairRequest>, GenerateDataKeyPairRequest> {
    @Override // com.netease.nimlib.amazonaws.transform.Marshaller
    public Request<GenerateDataKeyPairRequest> marshall(GenerateDataKeyPairRequest generateDataKeyPairRequest) {
        if (generateDataKeyPairRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(GenerateDataKeyPairRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(generateDataKeyPairRequest, "AWSKMS");
        defaultRequest.addHeader("X-Amz-Target", "TrentService.GenerateDataKeyPair");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (generateDataKeyPairRequest.getEncryptionContext() != null) {
                Map<String, String> encryptionContext = generateDataKeyPairRequest.getEncryptionContext();
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
            if (generateDataKeyPairRequest.getKeyId() != null) {
                String keyId = generateDataKeyPairRequest.getKeyId();
                jsonWriter.name("KeyId");
                jsonWriter.value(keyId);
            }
            if (generateDataKeyPairRequest.getKeyPairSpec() != null) {
                String keyPairSpec = generateDataKeyPairRequest.getKeyPairSpec();
                jsonWriter.name("KeyPairSpec");
                jsonWriter.value(keyPairSpec);
            }
            if (generateDataKeyPairRequest.getGrantTokens() != null) {
                List<String> grantTokens = generateDataKeyPairRequest.getGrantTokens();
                jsonWriter.name("GrantTokens");
                jsonWriter.beginArray();
                for (String str : grantTokens) {
                    if (str != null) {
                        jsonWriter.value(str);
                    }
                }
                jsonWriter.endArray();
            }
            if (generateDataKeyPairRequest.getRecipient() != null) {
                RecipientInfo recipient = generateDataKeyPairRequest.getRecipient();
                jsonWriter.name("Recipient");
                RecipientInfoJsonMarshaller.getInstance().marshall(recipient, jsonWriter);
            }
            if (generateDataKeyPairRequest.getDryRun() != null) {
                Boolean dryRun = generateDataKeyPairRequest.getDryRun();
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
