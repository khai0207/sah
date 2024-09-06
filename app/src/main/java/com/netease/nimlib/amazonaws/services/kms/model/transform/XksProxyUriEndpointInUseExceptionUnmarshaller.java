package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.http.JsonErrorResponseHandler;
import com.netease.nimlib.amazonaws.services.kms.model.XksProxyUriEndpointInUseException;
import com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller;

/* loaded from: classes.dex */
public class XksProxyUriEndpointInUseExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public XksProxyUriEndpointInUseExceptionUnmarshaller() {
        super(XksProxyUriEndpointInUseException.class);
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("XksProxyUriEndpointInUseException");
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller, com.netease.nimlib.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        XksProxyUriEndpointInUseException xksProxyUriEndpointInUseException = (XksProxyUriEndpointInUseException) super.unmarshall(jsonErrorResponse);
        xksProxyUriEndpointInUseException.setErrorCode("XksProxyUriEndpointInUseException");
        return xksProxyUriEndpointInUseException;
    }
}
