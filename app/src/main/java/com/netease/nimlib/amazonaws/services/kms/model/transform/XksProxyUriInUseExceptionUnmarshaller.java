package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.http.JsonErrorResponseHandler;
import com.netease.nimlib.amazonaws.services.kms.model.XksProxyUriInUseException;
import com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller;

/* loaded from: classes.dex */
public class XksProxyUriInUseExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public XksProxyUriInUseExceptionUnmarshaller() {
        super(XksProxyUriInUseException.class);
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("XksProxyUriInUseException");
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller, com.netease.nimlib.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        XksProxyUriInUseException xksProxyUriInUseException = (XksProxyUriInUseException) super.unmarshall(jsonErrorResponse);
        xksProxyUriInUseException.setErrorCode("XksProxyUriInUseException");
        return xksProxyUriInUseException;
    }
}
