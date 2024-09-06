package com.netease.nimlib.fusionstorage.crossplatform.defines;

/* loaded from: classes.dex */
public enum StorageProvider {
    STORAGE_PROVIDER_UNKNOWN(0),
    STORAGE_PROVIDER_NOS(1),
    STORAGE_PROVIDER_AWS_S3(2),
    STORAGE_PROVIDER_ALIYUN_OSS(3);

    private final int value;

    StorageProvider(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
