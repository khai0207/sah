package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.http.JsonErrorResponseHandler;
import com.netease.nimlib.amazonaws.services.kms.model.CloudHsmClusterInvalidConfigurationException;
import com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller;

/* loaded from: classes.dex */
public class CloudHsmClusterInvalidConfigurationExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public CloudHsmClusterInvalidConfigurationExceptionUnmarshaller() {
        super(CloudHsmClusterInvalidConfigurationException.class);
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("CloudHsmClusterInvalidConfigurationException");
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller, com.netease.nimlib.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        CloudHsmClusterInvalidConfigurationException cloudHsmClusterInvalidConfigurationException = (CloudHsmClusterInvalidConfigurationException) super.unmarshall(jsonErrorResponse);
        cloudHsmClusterInvalidConfigurationException.setErrorCode("CloudHsmClusterInvalidConfigurationException");
        return cloudHsmClusterInvalidConfigurationException;
    }
}
