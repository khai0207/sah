package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.services.kms.model.XksProxyConfigurationType;
import com.netease.nimlib.amazonaws.transform.JsonUnmarshallerContext;
import com.netease.nimlib.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.netease.nimlib.amazonaws.transform.Unmarshaller;
import com.netease.nimlib.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
class XksProxyConfigurationTypeJsonUnmarshaller implements Unmarshaller<XksProxyConfigurationType, JsonUnmarshallerContext> {
    private static XksProxyConfigurationTypeJsonUnmarshaller instance;

    XksProxyConfigurationTypeJsonUnmarshaller() {
    }

    @Override // com.netease.nimlib.amazonaws.transform.Unmarshaller
    public XksProxyConfigurationType unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        XksProxyConfigurationType xksProxyConfigurationType = new XksProxyConfigurationType();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("Connectivity")) {
                xksProxyConfigurationType.setConnectivity(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("AccessKeyId")) {
                xksProxyConfigurationType.setAccessKeyId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("UriEndpoint")) {
                xksProxyConfigurationType.setUriEndpoint(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("UriPath")) {
                xksProxyConfigurationType.setUriPath(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("VpcEndpointServiceName")) {
                xksProxyConfigurationType.setVpcEndpointServiceName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return xksProxyConfigurationType;
    }

    public static XksProxyConfigurationTypeJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new XksProxyConfigurationTypeJsonUnmarshaller();
        }
        return instance;
    }
}
