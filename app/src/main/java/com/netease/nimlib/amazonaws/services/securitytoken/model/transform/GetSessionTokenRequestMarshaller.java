package com.netease.nimlib.amazonaws.services.securitytoken.model.transform;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.DefaultRequest;
import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.auth.policy.internal.JsonDocumentFields;
import com.netease.nimlib.amazonaws.services.securitytoken.model.GetSessionTokenRequest;
import com.netease.nimlib.amazonaws.transform.Marshaller;
import com.netease.nimlib.amazonaws.util.StringUtils;

/* loaded from: classes.dex */
public class GetSessionTokenRequestMarshaller implements Marshaller<Request<GetSessionTokenRequest>, GetSessionTokenRequest> {
    @Override // com.netease.nimlib.amazonaws.transform.Marshaller
    public Request<GetSessionTokenRequest> marshall(GetSessionTokenRequest getSessionTokenRequest) {
        if (getSessionTokenRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(GetSessionTokenRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(getSessionTokenRequest, "AWSSecurityTokenService");
        defaultRequest.addParameter(JsonDocumentFields.ACTION, "GetSessionToken");
        defaultRequest.addParameter("Version", "2011-06-15");
        if (getSessionTokenRequest.getDurationSeconds() != null) {
            defaultRequest.addParameter("DurationSeconds", StringUtils.fromInteger(getSessionTokenRequest.getDurationSeconds()));
        }
        if (getSessionTokenRequest.getSerialNumber() != null) {
            defaultRequest.addParameter("SerialNumber", StringUtils.fromString(getSessionTokenRequest.getSerialNumber()));
        }
        if (getSessionTokenRequest.getTokenCode() != null) {
            defaultRequest.addParameter("TokenCode", StringUtils.fromString(getSessionTokenRequest.getTokenCode()));
        }
        return defaultRequest;
    }
}
