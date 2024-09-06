package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.http.JsonErrorResponseHandler;
import com.netease.nimlib.amazonaws.services.kms.model.CustomKeyStoreHasCMKsException;
import com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller;

/* loaded from: classes.dex */
public class CustomKeyStoreHasCMKsExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public CustomKeyStoreHasCMKsExceptionUnmarshaller() {
        super(CustomKeyStoreHasCMKsException.class);
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("CustomKeyStoreHasCMKsException");
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller, com.netease.nimlib.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        CustomKeyStoreHasCMKsException customKeyStoreHasCMKsException = (CustomKeyStoreHasCMKsException) super.unmarshall(jsonErrorResponse);
        customKeyStoreHasCMKsException.setErrorCode("CustomKeyStoreHasCMKsException");
        return customKeyStoreHasCMKsException;
    }
}
