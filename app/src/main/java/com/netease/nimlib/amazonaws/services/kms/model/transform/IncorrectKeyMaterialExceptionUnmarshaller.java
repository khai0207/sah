package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.http.JsonErrorResponseHandler;
import com.netease.nimlib.amazonaws.services.kms.model.IncorrectKeyMaterialException;
import com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller;

/* loaded from: classes.dex */
public class IncorrectKeyMaterialExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public IncorrectKeyMaterialExceptionUnmarshaller() {
        super(IncorrectKeyMaterialException.class);
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("IncorrectKeyMaterialException");
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller, com.netease.nimlib.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        IncorrectKeyMaterialException incorrectKeyMaterialException = (IncorrectKeyMaterialException) super.unmarshall(jsonErrorResponse);
        incorrectKeyMaterialException.setErrorCode("IncorrectKeyMaterialException");
        return incorrectKeyMaterialException;
    }
}
