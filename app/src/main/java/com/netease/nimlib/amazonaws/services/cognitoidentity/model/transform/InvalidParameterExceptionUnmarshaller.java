package com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform;

import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.http.JsonErrorResponseHandler;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.InvalidParameterException;
import com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller;

/* loaded from: classes.dex */
public class InvalidParameterExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public InvalidParameterExceptionUnmarshaller() {
        super(InvalidParameterException.class);
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("InvalidParameterException");
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller, com.netease.nimlib.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        InvalidParameterException invalidParameterException = (InvalidParameterException) super.unmarshall(jsonErrorResponse);
        invalidParameterException.setErrorCode("InvalidParameterException");
        return invalidParameterException;
    }
}
