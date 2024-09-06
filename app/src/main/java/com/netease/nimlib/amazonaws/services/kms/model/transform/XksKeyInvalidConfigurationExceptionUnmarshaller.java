package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.http.JsonErrorResponseHandler;
import com.netease.nimlib.amazonaws.services.kms.model.XksKeyInvalidConfigurationException;
import com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller;

/* loaded from: classes.dex */
public class XksKeyInvalidConfigurationExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public XksKeyInvalidConfigurationExceptionUnmarshaller() {
        super(XksKeyInvalidConfigurationException.class);
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("XksKeyInvalidConfigurationException");
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller, com.netease.nimlib.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        XksKeyInvalidConfigurationException xksKeyInvalidConfigurationException = (XksKeyInvalidConfigurationException) super.unmarshall(jsonErrorResponse);
        xksKeyInvalidConfigurationException.setErrorCode("XksKeyInvalidConfigurationException");
        return xksKeyInvalidConfigurationException;
    }
}
