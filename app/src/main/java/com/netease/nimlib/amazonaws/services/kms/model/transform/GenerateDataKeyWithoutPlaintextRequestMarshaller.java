package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.DefaultRequest;
import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.http.HttpMethodName;
import com.netease.nimlib.amazonaws.services.kms.model.GenerateDataKeyWithoutPlaintextRequest;
import com.netease.nimlib.amazonaws.transform.Marshaller;
import com.netease.nimlib.amazonaws.util.StringInputStream;
import com.netease.nimlib.amazonaws.util.StringUtils;
import com.netease.nimlib.amazonaws.util.json.AwsJsonWriter;
import com.netease.nimlib.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class GenerateDataKeyWithoutPlaintextRequestMarshaller implements Marshaller<Request<GenerateDataKeyWithoutPlaintextRequest>, GenerateDataKeyWithoutPlaintextRequest> {
    @Override // com.netease.nimlib.amazonaws.transform.Marshaller
    public Request<GenerateDataKeyWithoutPlaintextRequest> marshall(GenerateDataKeyWithoutPlaintextRequest generateDataKeyWithoutPlaintextRequest) {
        if (generateDataKeyWithoutPlaintextRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(GenerateDataKeyWithoutPlaintextRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(generateDataKeyWithoutPlaintextRequest, "AWSKMS");
        defaultRequest.addHeader("X-Amz-Target", "TrentService.GenerateDataKeyWithoutPlaintext");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (generateDataKeyWithoutPlaintextRequest.getKeyId() != null) {
                String keyId = generateDataKeyWithoutPlaintextRequest.getKeyId();
                jsonWriter.name("KeyId");
                jsonWriter.value(keyId);
            }
            if (generateDataKeyWithoutPlaintextRequest.getEncryptionContext() != null) {
                Map<String, String> encryptionContext = generateDataKeyWithoutPlaintextRequest.getEncryptionContext();
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
            if (generateDataKeyWithoutPlaintextRequest.getKeySpec() != null) {
                String keySpec = generateDataKeyWithoutPlaintextRequest.getKeySpec();
                jsonWriter.name("KeySpec");
                jsonWriter.value(keySpec);
            }
            if (generateDataKeyWithoutPlaintextRequest.getNumberOfBytes() != null) {
                Integer numberOfBytes = generateDataKeyWithoutPlaintextRequest.getNumberOfBytes();
                jsonWriter.name("NumberOfBytes");
                jsonWriter.value(numberOfBytes);
            }
            if (generateDataKeyWithoutPlaintextRequest.getGrantTokens() != null) {
                List<String> grantTokens = generateDataKeyWithoutPlaintextRequest.getGrantTokens();
                jsonWriter.name("GrantTokens");
                jsonWriter.beginArray();
                for (String str : grantTokens) {
                    if (str != null) {
                        jsonWriter.value(str);
                    }
                }
                jsonWriter.endArray();
            }
            if (generateDataKeyWithoutPlaintextRequest.getDryRun() != null) {
                Boolean dryRun = generateDataKeyWithoutPlaintextRequest.getDryRun();
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
