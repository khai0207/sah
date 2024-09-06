package com.netease.nim.highavailable;

import com.netease.nim.highavailable.enums.HAvailableFCSErrorCode;

/* loaded from: classes.dex */
public interface FCSDownloadCallback {
    void onDownloadProgress(long j, long j2);

    void onDownloadResult(HAvailableFCSErrorCode hAvailableFCSErrorCode, int i, String str);

    void onDownloadSpeed(long j);
}
