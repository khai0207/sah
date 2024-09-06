package com.netease.nim.highavailable;

/* loaded from: classes.dex */
interface HighAvailableLBSNativeCallback {
    String getAccid();

    int getAuthState();

    void onInitCallback(boolean z);

    void onRequestError(int i, String str, String str2, String str3, long j, long j2);

    void onSingleRequestTrackerReport(String str);

    void onUpdate(int i, String str);
}
