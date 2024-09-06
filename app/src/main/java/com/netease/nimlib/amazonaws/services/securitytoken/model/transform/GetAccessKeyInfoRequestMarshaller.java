package com.netease.nimlib.amazonaws.services.securitytoken.model.transform;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.DefaultRequest;
import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.auth.policy.internal.JsonDocumentFields;
import com.netease.nimlib.amazonaws.services.securitytoken.model.GetAccessKeyInfoRequest;
import com.netease.nimlib.amazonaws.transform.Marshaller;
import com.netease.nimlib.amazonaws.util.StringUtils;

/* loaded from: classes.dex */
public class GetAccessKeyInfoRequestMarshaller implements Marshaller<Request<GetAccessKeyInfoRequest>, GetAccessKeyInfoRequest> {
    @Override // com.netease.nimlib.amazonaws.transform.Marshaller
    public Request<GetAccessKeyInfoRequest> marshall(GetAccessKeyInfoRequest getAccessKeyInfoRequest) {
        if (getAccessKeyInfoRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(GetAccessKeyInfoRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(getAccessKeyInfoRequest, "AWSSecurityTokenService");
        defaultRequest.addParameter(JsonDocumentFields.ACTION, "GetAccessKeyInfo");
        defaultRequest.addParameter("Version", "2011-06-15");
        if (getAccessKeyInfoRequest.getAccessKeyId() != null) {
            defaultRequest.addParameter("AccessKeyId", StringUtils.fromString(getAccessKeyInfoRequest.getAccessKeyId()));
        }
        return defaultRequest;
    }
}
