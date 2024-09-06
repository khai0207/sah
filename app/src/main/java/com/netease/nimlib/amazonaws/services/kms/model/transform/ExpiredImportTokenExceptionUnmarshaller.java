package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.http.JsonErrorResponseHandler;
import com.netease.nimlib.amazonaws.services.kms.model.ExpiredImportTokenException;
import com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller;

/* loaded from: classes.dex */
public class ExpiredImportTokenExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public ExpiredImportTokenExceptionUnmarshaller() {
        super(ExpiredImportTokenException.class);
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("ExpiredImportTokenException");
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller, com.netease.nimlib.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        ExpiredImportTokenException expiredImportTokenException = (ExpiredImportTokenException) super.unmarshall(jsonErrorResponse);
        expiredImportTokenException.setErrorCode("ExpiredImportTokenException");
        return expiredImportTokenException;
    }
}
