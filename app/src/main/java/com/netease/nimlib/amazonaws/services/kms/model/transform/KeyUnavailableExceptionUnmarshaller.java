package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.http.JsonErrorResponseHandler;
import com.netease.nimlib.amazonaws.services.kms.model.KeyUnavailableException;
import com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller;

/* loaded from: classes.dex */
public class KeyUnavailableExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public KeyUnavailableExceptionUnmarshaller() {
        super(KeyUnavailableException.class);
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("KeyUnavailableException");
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller, com.netease.nimlib.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        KeyUnavailableException keyUnavailableException = (KeyUnavailableException) super.unmarshall(jsonErrorResponse);
        keyUnavailableException.setErrorCode("KeyUnavailableException");
        return keyUnavailableException;
    }
}
