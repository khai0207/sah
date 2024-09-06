package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.http.JsonErrorResponseHandler;
import com.netease.nimlib.amazonaws.services.kms.model.XksProxyVpcEndpointServiceInUseException;
import com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller;

/* loaded from: classes.dex */
public class XksProxyVpcEndpointServiceInUseExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public XksProxyVpcEndpointServiceInUseExceptionUnmarshaller() {
        super(XksProxyVpcEndpointServiceInUseException.class);
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("XksProxyVpcEndpointServiceInUseException");
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller, com.netease.nimlib.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        XksProxyVpcEndpointServiceInUseException xksProxyVpcEndpointServiceInUseException = (XksProxyVpcEndpointServiceInUseException) super.unmarshall(jsonErrorResponse);
        xksProxyVpcEndpointServiceInUseException.setErrorCode("XksProxyVpcEndpointServiceInUseException");
        return xksProxyVpcEndpointServiceInUseException;
    }
}
