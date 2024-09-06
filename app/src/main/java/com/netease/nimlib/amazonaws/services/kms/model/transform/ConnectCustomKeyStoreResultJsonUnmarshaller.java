package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.services.kms.model.ConnectCustomKeyStoreResult;
import com.netease.nimlib.amazonaws.transform.JsonUnmarshallerContext;
import com.netease.nimlib.amazonaws.transform.Unmarshaller;

/* loaded from: classes.dex */
public class ConnectCustomKeyStoreResultJsonUnmarshaller implements Unmarshaller<ConnectCustomKeyStoreResult, JsonUnmarshallerContext> {
    private static ConnectCustomKeyStoreResultJsonUnmarshaller instance;

    @Override // com.netease.nimlib.amazonaws.transform.Unmarshaller
    public ConnectCustomKeyStoreResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new ConnectCustomKeyStoreResult();
    }

    public static ConnectCustomKeyStoreResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ConnectCustomKeyStoreResultJsonUnmarshaller();
        }
        return instance;
    }
}
