package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.DefaultRequest;
import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.http.HttpMethodName;
import com.netease.nimlib.amazonaws.services.kms.model.CreateKeyRequest;
import com.netease.nimlib.amazonaws.services.kms.model.Tag;
import com.netease.nimlib.amazonaws.transform.Marshaller;
import com.netease.nimlib.amazonaws.util.StringInputStream;
import com.netease.nimlib.amazonaws.util.StringUtils;
import com.netease.nimlib.amazonaws.util.json.AwsJsonWriter;
import com.netease.nimlib.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;
import java.util.List;

/* loaded from: classes.dex */
public class CreateKeyRequestMarshaller implements Marshaller<Request<CreateKeyRequest>, CreateKeyRequest> {
    @Override // com.netease.nimlib.amazonaws.transform.Marshaller
    public Request<CreateKeyRequest> marshall(CreateKeyRequest createKeyRequest) {
        if (createKeyRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(CreateKeyRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(createKeyRequest, "AWSKMS");
        defaultRequest.addHeader("X-Amz-Target", "TrentService.CreateKey");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (createKeyRequest.getPolicy() != null) {
                String policy = createKeyRequest.getPolicy();
                jsonWriter.name("Policy");
                jsonWriter.value(policy);
            }
            if (createKeyRequest.getDescription() != null) {
                String description = createKeyRequest.getDescription();
                jsonWriter.name("Description");
                jsonWriter.value(description);
            }
            if (createKeyRequest.getKeyUsage() != null) {
                String keyUsage = createKeyRequest.getKeyUsage();
                jsonWriter.name("KeyUsage");
                jsonWriter.value(keyUsage);
            }
            if (createKeyRequest.getCustomerMasterKeySpec() != null) {
                String customerMasterKeySpec = createKeyRequest.getCustomerMasterKeySpec();
                jsonWriter.name("CustomerMasterKeySpec");
                jsonWriter.value(customerMasterKeySpec);
            }
            if (createKeyRequest.getKeySpec() != null) {
                String keySpec = createKeyRequest.getKeySpec();
                jsonWriter.name("KeySpec");
                jsonWriter.value(keySpec);
            }
            if (createKeyRequest.getOrigin() != null) {
                String origin = createKeyRequest.getOrigin();
                jsonWriter.name("Origin");
                jsonWriter.value(origin);
            }
            if (createKeyRequest.getCustomKeyStoreId() != null) {
                String customKeyStoreId = createKeyRequest.getCustomKeyStoreId();
                jsonWriter.name("CustomKeyStoreId");
                jsonWriter.value(customKeyStoreId);
            }
            if (createKeyRequest.getBypassPolicyLockoutSafetyCheck() != null) {
                Boolean bypassPolicyLockoutSafetyCheck = createKeyRequest.getBypassPolicyLockoutSafetyCheck();
                jsonWriter.name("BypassPolicyLockoutSafetyCheck");
                jsonWriter.value(bypassPolicyLockoutSafetyCheck.booleanValue());
            }
            if (createKeyRequest.getTags() != null) {
                List<Tag> tags = createKeyRequest.getTags();
                jsonWriter.name("Tags");
                jsonWriter.beginArray();
                for (Tag tag : tags) {
                    if (tag != null) {
                        TagJsonMarshaller.getInstance().marshall(tag, jsonWriter);
                    }
                }
                jsonWriter.endArray();
            }
            if (createKeyRequest.getMultiRegion() != null) {
                Boolean multiRegion = createKeyRequest.getMultiRegion();
                jsonWriter.name("MultiRegion");
                jsonWriter.value(multiRegion.booleanValue());
            }
            if (createKeyRequest.getXksKeyId() != null) {
                String xksKeyId = createKeyRequest.getXksKeyId();
                jsonWriter.name("XksKeyId");
                jsonWriter.value(xksKeyId);
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
