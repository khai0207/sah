package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.http.JsonErrorResponseHandler;
import com.netease.nimlib.amazonaws.services.kms.model.XksProxyIncorrectAuthenticationCredentialException;
import com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller;

/* loaded from: classes.dex */
public class XksProxyIncorrectAuthenticationCredentialExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public XksProxyIncorrectAuthenticationCredentialExceptionUnmarshaller() {
        super(XksProxyIncorrectAuthenticationCredentialException.class);
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("XksProxyIncorrectAuthenticationCredentialException");
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller, com.netease.nimlib.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        XksProxyIncorrectAuthenticationCredentialException xksProxyIncorrectAuthenticationCredentialException = (XksProxyIncorrectAuthenticationCredentialException) super.unmarshall(jsonErrorResponse);
        xksProxyIncorrectAuthenticationCredentialException.setErrorCode("XksProxyIncorrectAuthenticationCredentialException");
        return xksProxyIncorrectAuthenticationCredentialException;
    }
}
