package com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform;

import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.http.JsonErrorResponseHandler;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.ConcurrentModificationException;
import com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller;

/* loaded from: classes.dex */
public class ConcurrentModificationExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public ConcurrentModificationExceptionUnmarshaller() {
        super(ConcurrentModificationException.class);
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("ConcurrentModificationException");
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller, com.netease.nimlib.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        ConcurrentModificationException concurrentModificationException = (ConcurrentModificationException) super.unmarshall(jsonErrorResponse);
        concurrentModificationException.setErrorCode("ConcurrentModificationException");
        return concurrentModificationException;
    }
}
