package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.http.JsonErrorResponseHandler;
import com.netease.nimlib.amazonaws.services.kms.model.CustomKeyStoreInvalidStateException;
import com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller;

/* loaded from: classes.dex */
public class CustomKeyStoreInvalidStateExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public CustomKeyStoreInvalidStateExceptionUnmarshaller() {
        super(CustomKeyStoreInvalidStateException.class);
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("CustomKeyStoreInvalidStateException");
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller, com.netease.nimlib.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        CustomKeyStoreInvalidStateException customKeyStoreInvalidStateException = (CustomKeyStoreInvalidStateException) super.unmarshall(jsonErrorResponse);
        customKeyStoreInvalidStateException.setErrorCode("CustomKeyStoreInvalidStateException");
        return customKeyStoreInvalidStateException;
    }
}
