package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.services.kms.model.DeleteCustomKeyStoreResult;
import com.netease.nimlib.amazonaws.transform.JsonUnmarshallerContext;
import com.netease.nimlib.amazonaws.transform.Unmarshaller;

/* loaded from: classes.dex */
public class DeleteCustomKeyStoreResultJsonUnmarshaller implements Unmarshaller<DeleteCustomKeyStoreResult, JsonUnmarshallerContext> {
    private static DeleteCustomKeyStoreResultJsonUnmarshaller instance;

    @Override // com.netease.nimlib.amazonaws.transform.Unmarshaller
    public DeleteCustomKeyStoreResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new DeleteCustomKeyStoreResult();
    }

    public static DeleteCustomKeyStoreResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DeleteCustomKeyStoreResultJsonUnmarshaller();
        }
        return instance;
    }
}
