package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.http.JsonErrorResponseHandler;
import com.netease.nimlib.amazonaws.services.kms.model.InvalidGrantTokenException;
import com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller;

/* loaded from: classes.dex */
public class InvalidGrantTokenExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public InvalidGrantTokenExceptionUnmarshaller() {
        super(InvalidGrantTokenException.class);
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("InvalidGrantTokenException");
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller, com.netease.nimlib.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        InvalidGrantTokenException invalidGrantTokenException = (InvalidGrantTokenException) super.unmarshall(jsonErrorResponse);
        invalidGrantTokenException.setErrorCode("InvalidGrantTokenException");
        return invalidGrantTokenException;
    }
}
