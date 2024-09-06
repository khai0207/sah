package com.netease.nim.highavailable;

/* loaded from: classes.dex */
interface HighAvailableFCSNativeCallback {
    void fcsChannelRequest(int i, int i2, long j, byte[] bArr);

    void getCustomAuthToken(String str);

    void onDownloadProgress(long j, long j2, long j3);

    void onDownloadResult(long j, int i, int i2, String str);

    void onDownloadSpeed(long j, long j2);

    void onInitCallback(boolean z);

    void onUploadProgress(long j, long j2, long j3);

    void onUploadResult(long j, int i, int i2, String str);

    void onUploadResume(long j, long j2, long j3, int i);

    void onUploadSpeed(long j, long j2);
}
