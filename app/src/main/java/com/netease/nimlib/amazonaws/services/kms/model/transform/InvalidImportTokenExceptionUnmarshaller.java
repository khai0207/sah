package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.http.JsonErrorResponseHandler;
import com.netease.nimlib.amazonaws.services.kms.model.InvalidImportTokenException;
import com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller;

/* loaded from: classes.dex */
public class InvalidImportTokenExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public InvalidImportTokenExceptionUnmarshaller() {
        super(InvalidImportTokenException.class);
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("InvalidImportTokenException");
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller, com.netease.nimlib.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        InvalidImportTokenException invalidImportTokenException = (InvalidImportTokenException) super.unmarshall(jsonErrorResponse);
        invalidImportTokenException.setErrorCode("InvalidImportTokenException");
        return invalidImportTokenException;
    }
}
