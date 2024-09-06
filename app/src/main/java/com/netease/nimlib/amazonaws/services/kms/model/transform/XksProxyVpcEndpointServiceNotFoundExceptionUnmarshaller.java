package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.http.JsonErrorResponseHandler;
import com.netease.nimlib.amazonaws.services.kms.model.XksProxyVpcEndpointServiceNotFoundException;
import com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller;

/* loaded from: classes.dex */
public class XksProxyVpcEndpointServiceNotFoundExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public XksProxyVpcEndpointServiceNotFoundExceptionUnmarshaller() {
        super(XksProxyVpcEndpointServiceNotFoundException.class);
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("XksProxyVpcEndpointServiceNotFoundException");
    }

    @Override // com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller, com.netease.nimlib.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        XksProxyVpcEndpointServiceNotFoundException xksProxyVpcEndpointServiceNotFoundException = (XksProxyVpcEndpointServiceNotFoundException) super.unmarshall(jsonErrorResponse);
        xksProxyVpcEndpointServiceNotFoundException.setErrorCode("XksProxyVpcEndpointServiceNotFoundException");
        return xksProxyVpcEndpointServiceNotFoundException;
    }
}
