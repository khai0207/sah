package com.netease.nimlib.amazonaws.services.securitytoken.model.transform;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.DefaultRequest;
import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.auth.policy.internal.JsonDocumentFields;
import com.netease.nimlib.amazonaws.services.securitytoken.model.AssumeRoleWithWebIdentityRequest;
import com.netease.nimlib.amazonaws.services.securitytoken.model.PolicyDescriptorType;
import com.netease.nimlib.amazonaws.transform.Marshaller;
import com.netease.nimlib.amazonaws.util.StringUtils;

/* loaded from: classes.dex */
public class AssumeRoleWithWebIdentityRequestMarshaller implements Marshaller<Request<AssumeRoleWithWebIdentityRequest>, AssumeRoleWithWebIdentityRequest> {
    @Override // com.netease.nimlib.amazonaws.transform.Marshaller
    public Request<AssumeRoleWithWebIdentityRequest> marshall(AssumeRoleWithWebIdentityRequest assumeRoleWithWebIdentityRequest) {
        if (assumeRoleWithWebIdentityRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(AssumeRoleWithWebIdentityRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(assumeRoleWithWebIdentityRequest, "AWSSecurityTokenService");
        defaultRequest.addParameter(JsonDocumentFields.ACTION, "AssumeRoleWithWebIdentity");
        defaultRequest.addParameter("Version", "2011-06-15");
        if (assumeRoleWithWebIdentityRequest.getRoleArn() != null) {
            defaultRequest.addParameter("RoleArn", StringUtils.fromString(assumeRoleWithWebIdentityRequest.getRoleArn()));
        }
        if (assumeRoleWithWebIdentityRequest.getRoleSessionName() != null) {
            defaultRequest.addParameter("RoleSessionName", StringUtils.fromString(assumeRoleWithWebIdentityRequest.getRoleSessionName()));
        }
        if (assumeRoleWithWebIdentityRequest.getWebIdentityToken() != null) {
            defaultRequest.addParameter("WebIdentityToken", StringUtils.fromString(assumeRoleWithWebIdentityRequest.getWebIdentityToken()));
        }
        if (assumeRoleWithWebIdentityRequest.getProviderId() != null) {
            defaultRequest.addParameter("ProviderId", StringUtils.fromString(assumeRoleWithWebIdentityRequest.getProviderId()));
        }
        if (assumeRoleWithWebIdentityRequest.getPolicyArns() != null) {
            int i = 1;
            for (PolicyDescriptorType policyDescriptorType : assumeRoleWithWebIdentityRequest.getPolicyArns()) {
                String str = "PolicyArns.member." + i;
                if (policyDescriptorType != null) {
                    PolicyDescriptorTypeStaxMarshaller.getInstance().marshall(policyDescriptorType, defaultRequest, str + ".");
                }
                i++;
            }
        }
        if (assumeRoleWithWebIdentityRequest.getPolicy() != null) {
            defaultRequest.addParameter("Policy", StringUtils.fromString(assumeRoleWithWebIdentityRequest.getPolicy()));
        }
        if (assumeRoleWithWebIdentityRequest.getDurationSeconds() != null) {
            defaultRequest.addParameter("DurationSeconds", StringUtils.fromInteger(assumeRoleWithWebIdentityRequest.getDurationSeconds()));
        }
        return defaultRequest;
    }
}
