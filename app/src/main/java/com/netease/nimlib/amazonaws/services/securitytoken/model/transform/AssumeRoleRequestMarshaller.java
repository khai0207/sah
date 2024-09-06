package com.netease.nimlib.amazonaws.services.securitytoken.model.transform;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.DefaultRequest;
import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.auth.policy.internal.JsonDocumentFields;
import com.netease.nimlib.amazonaws.services.securitytoken.model.AssumeRoleRequest;
import com.netease.nimlib.amazonaws.services.securitytoken.model.PolicyDescriptorType;
import com.netease.nimlib.amazonaws.services.securitytoken.model.ProvidedContext;
import com.netease.nimlib.amazonaws.services.securitytoken.model.Tag;
import com.netease.nimlib.amazonaws.transform.Marshaller;
import com.netease.nimlib.amazonaws.util.StringUtils;

/* loaded from: classes.dex */
public class AssumeRoleRequestMarshaller implements Marshaller<Request<AssumeRoleRequest>, AssumeRoleRequest> {
    @Override // com.netease.nimlib.amazonaws.transform.Marshaller
    public Request<AssumeRoleRequest> marshall(AssumeRoleRequest assumeRoleRequest) {
        if (assumeRoleRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(AssumeRoleRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(assumeRoleRequest, "AWSSecurityTokenService");
        defaultRequest.addParameter(JsonDocumentFields.ACTION, "AssumeRole");
        defaultRequest.addParameter("Version", "2011-06-15");
        if (assumeRoleRequest.getRoleArn() != null) {
            defaultRequest.addParameter("RoleArn", StringUtils.fromString(assumeRoleRequest.getRoleArn()));
        }
        if (assumeRoleRequest.getRoleSessionName() != null) {
            defaultRequest.addParameter("RoleSessionName", StringUtils.fromString(assumeRoleRequest.getRoleSessionName()));
        }
        int i = 1;
        if (assumeRoleRequest.getPolicyArns() != null) {
            int i2 = 1;
            for (PolicyDescriptorType policyDescriptorType : assumeRoleRequest.getPolicyArns()) {
                String str = "PolicyArns.member." + i2;
                if (policyDescriptorType != null) {
                    PolicyDescriptorTypeStaxMarshaller.getInstance().marshall(policyDescriptorType, defaultRequest, str + ".");
                }
                i2++;
            }
        }
        if (assumeRoleRequest.getPolicy() != null) {
            defaultRequest.addParameter("Policy", StringUtils.fromString(assumeRoleRequest.getPolicy()));
        }
        if (assumeRoleRequest.getDurationSeconds() != null) {
            defaultRequest.addParameter("DurationSeconds", StringUtils.fromInteger(assumeRoleRequest.getDurationSeconds()));
        }
        if (assumeRoleRequest.getTags() != null) {
            int i3 = 1;
            for (Tag tag : assumeRoleRequest.getTags()) {
                String str2 = "Tags.member." + i3;
                if (tag != null) {
                    TagStaxMarshaller.getInstance().marshall(tag, defaultRequest, str2 + ".");
                }
                i3++;
            }
        }
        if (assumeRoleRequest.getTransitiveTagKeys() != null) {
            int i4 = 1;
            for (String str3 : assumeRoleRequest.getTransitiveTagKeys()) {
                String str4 = "TransitiveTagKeys.member." + i4;
                if (str3 != null) {
                    defaultRequest.addParameter(str4, StringUtils.fromString(str3));
                }
                i4++;
            }
        }
        if (assumeRoleRequest.getExternalId() != null) {
            defaultRequest.addParameter("ExternalId", StringUtils.fromString(assumeRoleRequest.getExternalId()));
        }
        if (assumeRoleRequest.getSerialNumber() != null) {
            defaultRequest.addParameter("SerialNumber", StringUtils.fromString(assumeRoleRequest.getSerialNumber()));
        }
        if (assumeRoleRequest.getTokenCode() != null) {
            defaultRequest.addParameter("TokenCode", StringUtils.fromString(assumeRoleRequest.getTokenCode()));
        }
        if (assumeRoleRequest.getSourceIdentity() != null) {
            defaultRequest.addParameter("SourceIdentity", StringUtils.fromString(assumeRoleRequest.getSourceIdentity()));
        }
        if (assumeRoleRequest.getProvidedContexts() != null) {
            for (ProvidedContext providedContext : assumeRoleRequest.getProvidedContexts()) {
                String str5 = "ProvidedContexts.member." + i;
                if (providedContext != null) {
                    ProvidedContextStaxMarshaller.getInstance().marshall(providedContext, defaultRequest, str5 + ".");
                }
                i++;
            }
        }
        return defaultRequest;
    }
}
