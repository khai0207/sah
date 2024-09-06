package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.http.JsonErrorResponseHandler;
import com.netease.nimlib.amazonaws.services.kms.model.CustomKeyStoreNotFoundException;
import com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller;

/* loaded from: classes.dex */
public class CustomKeyStoreNotFoundExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public CustomKeyStoreNotFoundExceptionUnmarshaller() {
        super(CustomKeyStoreNotFoundException.class);
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("CustomKeyStoreNotFoundException");
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller, com.netease.nimlib.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        CustomKeyStoreNotFoundException customKeyStoreNotFoundException = (CustomKeyStoreNotFoundException) super.unmarshall(jsonErrorResponse);
        customKeyStoreNotFoundException.setErrorCode("CustomKeyStoreNotFoundException");
        return customKeyStoreNotFoundException;
    }
}
