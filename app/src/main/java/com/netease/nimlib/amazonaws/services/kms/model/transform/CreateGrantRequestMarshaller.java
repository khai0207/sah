package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.DefaultRequest;
import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.http.HttpMethodName;
import com.netease.nimlib.amazonaws.services.kms.model.CreateGrantRequest;
import com.netease.nimlib.amazonaws.services.kms.model.GrantConstraints;
import com.netease.nimlib.amazonaws.transform.Marshaller;
import com.netease.nimlib.amazonaws.util.StringInputStream;
import com.netease.nimlib.amazonaws.util.StringUtils;
import com.netease.nimlib.amazonaws.util.json.AwsJsonWriter;
import com.netease.nimlib.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;
import java.util.List;

/* loaded from: classes.dex */
public class CreateGrantRequestMarshaller implements Marshaller<Request<CreateGrantRequest>, CreateGrantRequest> {
    @Override // com.netease.nimlib.amazonaws.transform.Marshaller
    public Request<CreateGrantRequest> marshall(CreateGrantRequest createGrantRequest) {
        if (createGrantRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(CreateGrantRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(createGrantRequest, "AWSKMS");
        defaultRequest.addHeader("X-Amz-Target", "TrentService.CreateGrant");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (createGrantRequest.getKeyId() != null) {
                String keyId = createGrantRequest.getKeyId();
                jsonWriter.name("KeyId");
                jsonWriter.value(keyId);
            }
            if (createGrantRequest.getGranteePrincipal() != null) {
                String granteePrincipal = createGrantRequest.getGranteePrincipal();
                jsonWriter.name("GranteePrincipal");
                jsonWriter.value(granteePrincipal);
            }
            if (createGrantRequest.getRetiringPrincipal() != null) {
                String retiringPrincipal = createGrantRequest.getRetiringPrincipal();
                jsonWriter.name("RetiringPrincipal");
                jsonWriter.value(retiringPrincipal);
            }
            if (createGrantRequest.getOperations() != null) {
                List<String> operations = createGrantRequest.getOperations();
                jsonWriter.name("Operations");
                jsonWriter.beginArray();
                for (String str : operations) {
                    if (str != null) {
                        jsonWriter.value(str);
                    }
                }
                jsonWriter.endArray();
            }
            if (createGrantRequest.getConstraints() != null) {
                GrantConstraints constraints = createGrantRequest.getConstraints();
                jsonWriter.name("Constraints");
                GrantConstraintsJsonMarshaller.getInstance().marshall(constraints, jsonWriter);
            }
            if (createGrantRequest.getGrantTokens() != null) {
                List<String> grantTokens = createGrantRequest.getGrantTokens();
                jsonWriter.name("GrantTokens");
                jsonWriter.beginArray();
                for (String str2 : grantTokens) {
                    if (str2 != null) {
                        jsonWriter.value(str2);
                    }
                }
                jsonWriter.endArray();
            }
            if (createGrantRequest.getName() != null) {
                String name = createGrantRequest.getName();
                jsonWriter.name("Name");
                jsonWriter.value(name);
            }
            if (createGrantRequest.getDryRun() != null) {
                Boolean dryRun = createGrantRequest.getDryRun();
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
