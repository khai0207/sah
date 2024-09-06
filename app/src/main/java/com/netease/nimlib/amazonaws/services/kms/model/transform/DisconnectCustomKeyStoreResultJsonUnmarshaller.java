package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.services.kms.model.DisconnectCustomKeyStoreResult;
import com.netease.nimlib.amazonaws.transform.JsonUnmarshallerContext;
import com.netease.nimlib.amazonaws.transform.Unmarshaller;

/* loaded from: classes.dex */
public class DisconnectCustomKeyStoreResultJsonUnmarshaller implements Unmarshaller<DisconnectCustomKeyStoreResult, JsonUnmarshallerContext> {
    private static DisconnectCustomKeyStoreResultJsonUnmarshaller instance;

    @Override // com.netease.nimlib.amazonaws.transform.Unmarshaller
    public DisconnectCustomKeyStoreResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new DisconnectCustomKeyStoreResult();
    }

    public static DisconnectCustomKeyStoreResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DisconnectCustomKeyStoreResultJsonUnmarshaller();
        }
        return instance;
    }
}
