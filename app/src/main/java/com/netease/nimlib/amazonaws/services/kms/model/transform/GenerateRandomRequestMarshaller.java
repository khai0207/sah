package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.DefaultRequest;
import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.http.HttpMethodName;
import com.netease.nimlib.amazonaws.services.kms.model.GenerateRandomRequest;
import com.netease.nimlib.amazonaws.services.kms.model.RecipientInfo;
import com.netease.nimlib.amazonaws.transform.Marshaller;
import com.netease.nimlib.amazonaws.util.StringInputStream;
import com.netease.nimlib.amazonaws.util.StringUtils;
import com.netease.nimlib.amazonaws.util.json.AwsJsonWriter;
import com.netease.nimlib.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;

/* loaded from: classes.dex */
public class GenerateRandomRequestMarshaller implements Marshaller<Request<GenerateRandomRequest>, GenerateRandomRequest> {
    @Override // com.netease.nimlib.amazonaws.transform.Marshaller
    public Request<GenerateRandomRequest> marshall(GenerateRandomRequest generateRandomRequest) {
        if (generateRandomRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(GenerateRandomRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(generateRandomRequest, "AWSKMS");
        defaultRequest.addHeader("X-Amz-Target", "TrentService.GenerateRandom");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (generateRandomRequest.getNumberOfBytes() != null) {
                Integer numberOfBytes = generateRandomRequest.getNumberOfBytes();
                jsonWriter.name("NumberOfBytes");
                jsonWriter.value(numberOfBytes);
            }
            if (generateRandomRequest.getCustomKeyStoreId() != null) {
                String customKeyStoreId = generateRandomRequest.getCustomKeyStoreId();
                jsonWriter.name("CustomKeyStoreId");
                jsonWriter.value(customKeyStoreId);
            }
            if (generateRandomRequest.getRecipient() != null) {
                RecipientInfo recipient = generateRandomRequest.getRecipient();
                jsonWriter.name("Recipient");
                RecipientInfoJsonMarshaller.getInstance().marshall(recipient, jsonWriter);
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
