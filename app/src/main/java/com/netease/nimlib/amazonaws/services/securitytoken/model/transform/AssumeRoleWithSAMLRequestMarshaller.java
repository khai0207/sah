package com.netease.nimlib.amazonaws.services.securitytoken.model.transform;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.DefaultRequest;
import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.auth.policy.internal.JsonDocumentFields;
import com.netease.nimlib.amazonaws.services.securitytoken.model.AssumeRoleWithSAMLRequest;
import com.netease.nimlib.amazonaws.services.securitytoken.model.PolicyDescriptorType;
import com.netease.nimlib.amazonaws.transform.Marshaller;
import com.netease.nimlib.amazonaws.util.StringUtils;

/* loaded from: classes.dex */
public class AssumeRoleWithSAMLRequestMarshaller implements Marshaller<Request<AssumeRoleWithSAMLRequest>, AssumeRoleWithSAMLRequest> {
    @Override // com.netease.nimlib.amazonaws.transform.Marshaller
    public Request<AssumeRoleWithSAMLRequest> marshall(AssumeRoleWithSAMLRequest assumeRoleWithSAMLRequest) {
        if (assumeRoleWithSAMLRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(AssumeRoleWithSAMLRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(assumeRoleWithSAMLRequest, "AWSSecurityTokenService");
        defaultRequest.addParameter(JsonDocumentFields.ACTION, "AssumeRoleWithSAML");
        defaultRequest.addParameter("Version", "2011-06-15");
        if (assumeRoleWithSAMLRequest.getRoleArn() != null) {
            defaultRequest.addParameter("RoleArn", StringUtils.fromString(assumeRoleWithSAMLRequest.getRoleArn()));
        }
        if (assumeRoleWithSAMLRequest.getPrincipalArn() != null) {
            defaultRequest.addParameter("PrincipalArn", StringUtils.fromString(assumeRoleWithSAMLRequest.getPrincipalArn()));
        }
        if (assumeRoleWithSAMLRequest.getSAMLAssertion() != null) {
            defaultRequest.addParameter("SAMLAssertion", StringUtils.fromString(assumeRoleWithSAMLRequest.getSAMLAssertion()));
        }
        if (assumeRoleWithSAMLRequest.getPolicyArns() != null) {
            int i = 1;
            for (PolicyDescriptorType policyDescriptorType : assumeRoleWithSAMLRequest.getPolicyArns()) {
                String str = "PolicyArns.member." + i;
                if (policyDescriptorType != null) {
                    PolicyDescriptorTypeStaxMarshaller.getInstance().marshall(policyDescriptorType, defaultRequest, str + ".");
                }
                i++;
            }
        }
        if (assumeRoleWithSAMLRequest.getPolicy() != null) {
            defaultRequest.addParameter("Policy", StringUtils.fromString(assumeRoleWithSAMLRequest.getPolicy()));
        }
        if (assumeRoleWithSAMLRequest.getDurationSeconds() != null) {
            defaultRequest.addParameter("DurationSeconds", StringUtils.fromInteger(assumeRoleWithSAMLRequest.getDurationSeconds()));
        }
        return defaultRequest;
    }
}
