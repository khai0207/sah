package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.http.JsonErrorResponseHandler;
import com.netease.nimlib.amazonaws.services.kms.model.AlreadyExistsException;
import com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller;

/* loaded from: classes.dex */
public class AlreadyExistsExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public AlreadyExistsExceptionUnmarshaller() {
        super(AlreadyExistsException.class);
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("AlreadyExistsException");
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller, com.netease.nimlib.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        AlreadyExistsException alreadyExistsException = (AlreadyExistsException) super.unmarshall(jsonErrorResponse);
        alreadyExistsException.setErrorCode("AlreadyExistsException");
        return alreadyExistsException;
    }
}
