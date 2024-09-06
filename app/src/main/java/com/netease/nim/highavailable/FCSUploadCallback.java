package com.netease.nim.highavailable;

import com.netease.nim.highavailable.enums.HAvailableFCSErrorCode;
import com.netease.nim.highavailable.enums.HAvailableFCSUploadPluginTag;

/* loaded from: classes.dex */
public interface FCSUploadCallback {
    void onUploadProgress(long j, long j2);

    void onUploadResult(HAvailableFCSErrorCode hAvailableFCSErrorCode, int i, String str);

    void onUploadResume(long j, long j2, HAvailableFCSUploadPluginTag hAvailableFCSUploadPluginTag);

    void onUploadSpeed(long j);
}
