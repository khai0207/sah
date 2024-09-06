package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.http.JsonErrorResponseHandler;
import com.netease.nimlib.amazonaws.services.kms.model.DisabledException;
import com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller;

/* loaded from: classes.dex */
public class DisabledExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public DisabledExceptionUnmarshaller() {
        super(DisabledException.class);
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("DisabledException");
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller, com.netease.nimlib.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        DisabledException disabledException = (DisabledException) super.unmarshall(jsonErrorResponse);
        disabledException.setErrorCode("DisabledException");
        return disabledException;
    }
}
