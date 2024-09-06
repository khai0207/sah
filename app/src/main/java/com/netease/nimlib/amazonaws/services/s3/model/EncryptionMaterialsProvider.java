package com.netease.nimlib.amazonaws.services.s3.model;

@Deprecated
/* loaded from: classes.dex */
public interface EncryptionMaterialsProvider extends EncryptionMaterialsAccessor {
    EncryptionMaterials getEncryptionMaterials();

    void refresh();
}
