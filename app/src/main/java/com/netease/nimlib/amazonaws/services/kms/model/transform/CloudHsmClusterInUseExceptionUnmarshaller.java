package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.http.JsonErrorResponseHandler;
import com.netease.nimlib.amazonaws.services.kms.model.CloudHsmClusterInUseException;
import com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller;

/* loaded from: classes.dex */
public class CloudHsmClusterInUseExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public CloudHsmClusterInUseExceptionUnmarshaller() {
        super(CloudHsmClusterInUseException.class);
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("CloudHsmClusterInUseException");
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller, com.netease.nimlib.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        CloudHsmClusterInUseException cloudHsmClusterInUseException = (CloudHsmClusterInUseException) super.unmarshall(jsonErrorResponse);
        cloudHsmClusterInUseException.setErrorCode("CloudHsmClusterInUseException");
        return cloudHsmClusterInUseException;
    }
}
