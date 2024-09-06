package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.http.JsonErrorResponseHandler;
import com.netease.nimlib.amazonaws.services.kms.model.CloudHsmClusterNotRelatedException;
import com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller;

/* loaded from: classes.dex */
public class CloudHsmClusterNotRelatedExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public CloudHsmClusterNotRelatedExceptionUnmarshaller() {
        super(CloudHsmClusterNotRelatedException.class);
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("CloudHsmClusterNotRelatedException");
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller, com.netease.nimlib.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        CloudHsmClusterNotRelatedException cloudHsmClusterNotRelatedException = (CloudHsmClusterNotRelatedException) super.unmarshall(jsonErrorResponse);
        cloudHsmClusterNotRelatedException.setErrorCode("CloudHsmClusterNotRelatedException");
        return cloudHsmClusterNotRelatedException;
    }
}
