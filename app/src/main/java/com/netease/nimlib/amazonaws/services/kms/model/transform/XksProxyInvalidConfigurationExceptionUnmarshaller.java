package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.http.JsonErrorResponseHandler;
import com.netease.nimlib.amazonaws.services.kms.model.XksProxyInvalidConfigurationException;
import com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller;

/* loaded from: classes.dex */
public class XksProxyInvalidConfigurationExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public XksProxyInvalidConfigurationExceptionUnmarshaller() {
        super(XksProxyInvalidConfigurationException.class);
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("XksProxyInvalidConfigurationException");
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller, com.netease.nimlib.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        XksProxyInvalidConfigurationException xksProxyInvalidConfigurationException = (XksProxyInvalidConfigurationException) super.unmarshall(jsonErrorResponse);
        xksProxyInvalidConfigurationException.setErrorCode("XksProxyInvalidConfigurationException");
        return xksProxyInvalidConfigurationException;
    }
}
