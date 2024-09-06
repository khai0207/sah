package com.netease.nim.highavailable;

import com.netease.nim.highavailable.enums.HAvailableAuthState;

/* loaded from: classes.dex */
public interface HighAvailableLBSCallback {
    String getAccid();

    HAvailableAuthState getAuthState();

    void onInitCallback(boolean z);

    void onRequestError(int i, String str, String str2, String str3, long j, long j2);

    void onSingleRequestTrackerReport(String str);

    void onUpdate(int i, String str);
}
