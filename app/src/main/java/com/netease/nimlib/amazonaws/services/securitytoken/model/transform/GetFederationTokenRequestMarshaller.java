package com.netease.nimlib.amazonaws.services.securitytoken.model.transform;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.DefaultRequest;
import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.auth.policy.internal.JsonDocumentFields;
import com.netease.nimlib.amazonaws.services.securitytoken.model.GetFederationTokenRequest;
import com.netease.nimlib.amazonaws.services.securitytoken.model.PolicyDescriptorType;
import com.netease.nimlib.amazonaws.services.securitytoken.model.Tag;
import com.netease.nimlib.amazonaws.transform.Marshaller;
import com.netease.nimlib.amazonaws.util.StringUtils;

/* loaded from: classes.dex */
public class GetFederationTokenRequestMarshaller implements Marshaller<Request<GetFederationTokenRequest>, GetFederationTokenRequest> {
    @Override // com.netease.nimlib.amazonaws.transform.Marshaller
    public Request<GetFederationTokenRequest> marshall(GetFederationTokenRequest getFederationTokenRequest) {
        if (getFederationTokenRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(GetFederationTokenRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(getFederationTokenRequest, "AWSSecurityTokenService");
        defaultRequest.addParameter(JsonDocumentFields.ACTION, "GetFederationToken");
        defaultRequest.addParameter("Version", "2011-06-15");
        if (getFederationTokenRequest.getName() != null) {
            defaultRequest.addParameter("Name", StringUtils.fromString(getFederationTokenRequest.getName()));
        }
        if (getFederationTokenRequest.getPolicy() != null) {
            defaultRequest.addParameter("Policy", StringUtils.fromString(getFederationTokenRequest.getPolicy()));
        }
        int i = 1;
        if (getFederationTokenRequest.getPolicyArns() != null) {
            int i2 = 1;
            for (PolicyDescriptorType policyDescriptorType : getFederationTokenRequest.getPolicyArns()) {
                String str = "PolicyArns.member." + i2;
                if (policyDescriptorType != null) {
                    PolicyDescriptorTypeStaxMarshaller.getInstance().marshall(policyDescriptorType, defaultRequest, str + ".");
                }
                i2++;
            }
        }
        if (getFederationTokenRequest.getDurationSeconds() != null) {
            defaultRequest.addParameter("DurationSeconds", StringUtils.fromInteger(getFederationTokenRequest.getDurationSeconds()));
        }
        if (getFederationTokenRequest.getTags() != null) {
            for (Tag tag : getFederationTokenRequest.getTags()) {
                String str2 = "Tags.member." + i;
                if (tag != null) {
                    TagStaxMarshaller.getInstance().marshall(tag, defaultRequest, str2 + ".");
                }
                i++;
            }
        }
        return defaultRequest;
    }
}
