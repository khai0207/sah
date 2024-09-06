package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.http.JsonErrorResponseHandler;
import com.netease.nimlib.amazonaws.services.kms.model.CloudHsmClusterNotActiveException;
import com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller;

/* loaded from: classes.dex */
public class CloudHsmClusterNotActiveExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public CloudHsmClusterNotActiveExceptionUnmarshaller() {
        super(CloudHsmClusterNotActiveException.class);
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("CloudHsmClusterNotActiveException");
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller, com.netease.nimlib.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        CloudHsmClusterNotActiveException cloudHsmClusterNotActiveException = (CloudHsmClusterNotActiveException) super.unmarshall(jsonErrorResponse);
        cloudHsmClusterNotActiveException.setErrorCode("CloudHsmClusterNotActiveException");
        return cloudHsmClusterNotActiveException;
    }
}
