package com.netease.nimlib.amazonaws.services.securitytoken.model.transform;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.DefaultRequest;
import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.auth.policy.internal.JsonDocumentFields;
import com.netease.nimlib.amazonaws.services.securitytoken.model.GetCallerIdentityRequest;
import com.netease.nimlib.amazonaws.transform.Marshaller;

/* loaded from: classes.dex */
public class GetCallerIdentityRequestMarshaller implements Marshaller<Request<GetCallerIdentityRequest>, GetCallerIdentityRequest> {
    @Override // com.netease.nimlib.amazonaws.transform.Marshaller
    public Request<GetCallerIdentityRequest> marshall(GetCallerIdentityRequest getCallerIdentityRequest) {
        if (getCallerIdentityRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(GetCallerIdentityRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(getCallerIdentityRequest, "AWSSecurityTokenService");
        defaultRequest.addParameter(JsonDocumentFields.ACTION, "GetCallerIdentity");
        defaultRequest.addParameter("Version", "2011-06-15");
        return defaultRequest;
    }
}
