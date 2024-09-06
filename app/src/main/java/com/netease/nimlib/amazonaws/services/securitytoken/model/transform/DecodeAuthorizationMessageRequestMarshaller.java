package com.netease.nimlib.amazonaws.services.securitytoken.model.transform;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.DefaultRequest;
import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.auth.policy.internal.JsonDocumentFields;
import com.netease.nimlib.amazonaws.services.securitytoken.model.DecodeAuthorizationMessageRequest;
import com.netease.nimlib.amazonaws.transform.Marshaller;
import com.netease.nimlib.amazonaws.util.StringUtils;

/* loaded from: classes.dex */
public class DecodeAuthorizationMessageRequestMarshaller implements Marshaller<Request<DecodeAuthorizationMessageRequest>, DecodeAuthorizationMessageRequest> {
    @Override // com.netease.nimlib.amazonaws.transform.Marshaller
    public Request<DecodeAuthorizationMessageRequest> marshall(DecodeAuthorizationMessageRequest decodeAuthorizationMessageRequest) {
        if (decodeAuthorizationMessageRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DecodeAuthorizationMessageRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(decodeAuthorizationMessageRequest, "AWSSecurityTokenService");
        defaultRequest.addParameter(JsonDocumentFields.ACTION, "DecodeAuthorizationMessage");
        defaultRequest.addParameter("Version", "2011-06-15");
        if (decodeAuthorizationMessageRequest.getEncodedMessage() != null) {
            defaultRequest.addParameter("EncodedMessage", StringUtils.fromString(decodeAuthorizationMessageRequest.getEncodedMessage()));
        }
        return defaultRequest;
    }
}
