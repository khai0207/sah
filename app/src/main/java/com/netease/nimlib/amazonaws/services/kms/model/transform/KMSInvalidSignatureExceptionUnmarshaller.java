package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.http.JsonErrorResponseHandler;
import com.netease.nimlib.amazonaws.services.kms.model.KMSInvalidSignatureException;
import com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller;

/* loaded from: classes.dex */
public class KMSInvalidSignatureExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public KMSInvalidSignatureExceptionUnmarshaller() {
        super(KMSInvalidSignatureException.class);
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("KMSInvalidSignatureException");
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller, com.netease.nimlib.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        KMSInvalidSignatureException kMSInvalidSignatureException = (KMSInvalidSignatureException) super.unmarshall(jsonErrorResponse);
        kMSInvalidSignatureException.setErrorCode("KMSInvalidSignatureException");
        return kMSInvalidSignatureException;
    }
}
