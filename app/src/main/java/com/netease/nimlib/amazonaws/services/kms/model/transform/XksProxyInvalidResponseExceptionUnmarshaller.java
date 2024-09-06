package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.http.JsonErrorResponseHandler;
import com.netease.nimlib.amazonaws.services.kms.model.XksProxyInvalidResponseException;
import com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller;

/* loaded from: classes.dex */
public class XksProxyInvalidResponseExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public XksProxyInvalidResponseExceptionUnmarshaller() {
        super(XksProxyInvalidResponseException.class);
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("XksProxyInvalidResponseException");
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller, com.netease.nimlib.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        XksProxyInvalidResponseException xksProxyInvalidResponseException = (XksProxyInvalidResponseException) super.unmarshall(jsonErrorResponse);
        xksProxyInvalidResponseException.setErrorCode("XksProxyInvalidResponseException");
        return xksProxyInvalidResponseException;
    }
}
