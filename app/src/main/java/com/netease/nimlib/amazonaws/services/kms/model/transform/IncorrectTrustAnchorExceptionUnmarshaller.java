package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.http.JsonErrorResponseHandler;
import com.netease.nimlib.amazonaws.services.kms.model.IncorrectTrustAnchorException;
import com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller;

/* loaded from: classes.dex */
public class IncorrectTrustAnchorExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public IncorrectTrustAnchorExceptionUnmarshaller() {
        super(IncorrectTrustAnchorException.class);
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("IncorrectTrustAnchorException");
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller, com.netease.nimlib.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        IncorrectTrustAnchorException incorrectTrustAnchorException = (IncorrectTrustAnchorException) super.unmarshall(jsonErrorResponse);
        incorrectTrustAnchorException.setErrorCode("IncorrectTrustAnchorException");
        return incorrectTrustAnchorException;
    }
}
