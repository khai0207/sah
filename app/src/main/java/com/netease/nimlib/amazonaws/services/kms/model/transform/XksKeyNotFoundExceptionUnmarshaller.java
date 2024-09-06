package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.http.JsonErrorResponseHandler;
import com.netease.nimlib.amazonaws.services.kms.model.XksKeyNotFoundException;
import com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller;

/* loaded from: classes.dex */
public class XksKeyNotFoundExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public XksKeyNotFoundExceptionUnmarshaller() {
        super(XksKeyNotFoundException.class);
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("XksKeyNotFoundException");
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller, com.netease.nimlib.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        XksKeyNotFoundException xksKeyNotFoundException = (XksKeyNotFoundException) super.unmarshall(jsonErrorResponse);
        xksKeyNotFoundException.setErrorCode("XksKeyNotFoundException");
        return xksKeyNotFoundException;
    }
}
