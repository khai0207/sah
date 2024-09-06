package com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform;

import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.http.JsonErrorResponseHandler;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.DeveloperUserAlreadyRegisteredException;
import com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller;

/* loaded from: classes.dex */
public class DeveloperUserAlreadyRegisteredExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public DeveloperUserAlreadyRegisteredExceptionUnmarshaller() {
        super(DeveloperUserAlreadyRegisteredException.class);
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("DeveloperUserAlreadyRegisteredException");
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller, com.netease.nimlib.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        DeveloperUserAlreadyRegisteredException developerUserAlreadyRegisteredException = (DeveloperUserAlreadyRegisteredException) super.unmarshall(jsonErrorResponse);
        developerUserAlreadyRegisteredException.setErrorCode("DeveloperUserAlreadyRegisteredException");
        return developerUserAlreadyRegisteredException;
    }
}
