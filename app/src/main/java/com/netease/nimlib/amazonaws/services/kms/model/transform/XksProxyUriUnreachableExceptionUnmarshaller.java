package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.http.JsonErrorResponseHandler;
import com.netease.nimlib.amazonaws.services.kms.model.XksProxyUriUnreachableException;
import com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller;

/* loaded from: classes.dex */
public class XksProxyUriUnreachableExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public XksProxyUriUnreachableExceptionUnmarshaller() {
        super(XksProxyUriUnreachableException.class);
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("XksProxyUriUnreachableException");
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller, com.netease.nimlib.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        XksProxyUriUnreachableException xksProxyUriUnreachableException = (XksProxyUriUnreachableException) super.unmarshall(jsonErrorResponse);
        xksProxyUriUnreachableException.setErrorCode("XksProxyUriUnreachableException");
        return xksProxyUriUnreachableException;
    }
}
