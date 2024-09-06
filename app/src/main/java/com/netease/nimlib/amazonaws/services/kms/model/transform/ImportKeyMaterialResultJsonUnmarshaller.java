package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.services.kms.model.ImportKeyMaterialResult;
import com.netease.nimlib.amazonaws.transform.JsonUnmarshallerContext;
import com.netease.nimlib.amazonaws.transform.Unmarshaller;

/* loaded from: classes.dex */
public class ImportKeyMaterialResultJsonUnmarshaller implements Unmarshaller<ImportKeyMaterialResult, JsonUnmarshallerContext> {
    private static ImportKeyMaterialResultJsonUnmarshaller instance;

    @Override // com.netease.nimlib.amazonaws.transform.Unmarshaller
    public ImportKeyMaterialResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new ImportKeyMaterialResult();
    }

    public static ImportKeyMaterialResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ImportKeyMaterialResultJsonUnmarshaller();
        }
        return instance;
    }
}
