package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.services.kms.model.UpdateCustomKeyStoreResult;
import com.netease.nimlib.amazonaws.transform.JsonUnmarshallerContext;
import com.netease.nimlib.amazonaws.transform.Unmarshaller;

/* loaded from: classes.dex */
public class UpdateCustomKeyStoreResultJsonUnmarshaller implements Unmarshaller<UpdateCustomKeyStoreResult, JsonUnmarshallerContext> {
    private static UpdateCustomKeyStoreResultJsonUnmarshaller instance;

    @Override // com.netease.nimlib.amazonaws.transform.Unmarshaller
    public UpdateCustomKeyStoreResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new UpdateCustomKeyStoreResult();
    }

    public static UpdateCustomKeyStoreResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new UpdateCustomKeyStoreResultJsonUnmarshaller();
        }
        return instance;
    }
}
