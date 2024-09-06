package com.netease.nimlib.amazonaws.transform;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.http.JsonErrorResponseHandler;

/* loaded from: classes.dex */
public class JsonErrorUnmarshaller extends AbstractErrorUnmarshaller<JsonErrorResponseHandler.JsonErrorResponse> {
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return true;
    }

    public JsonErrorUnmarshaller() {
    }

    protected JsonErrorUnmarshaller(Class<? extends AmazonServiceException> cls) {
        super(cls);
    }

    @Override // com.netease.nimlib.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        String message = jsonErrorResponse.getMessage();
        String errorCode = jsonErrorResponse.getErrorCode();
        if ((message == null || message.isEmpty()) && (errorCode == null || errorCode.isEmpty())) {
            throw new AmazonClientException("Neither error message nor error code is found in the error response payload.");
        }
        AmazonServiceException newException = newException(message);
        newException.setErrorCode(errorCode);
        return newException;
    }
}
