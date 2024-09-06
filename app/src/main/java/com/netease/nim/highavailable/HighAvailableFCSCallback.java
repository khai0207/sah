package com.netease.nim.highavailable;

import com.netease.nim.highavailable.enums.HAvailableFCSChannelFunID;

/* loaded from: classes.dex */
public interface HighAvailableFCSCallback {
    void fcsChannelRequest(HAvailableFCSChannelFunID hAvailableFCSChannelFunID, int i, long j, byte[] bArr, FCSChannelResponseCallback fCSChannelResponseCallback);

    void getCustomAuthToken(String str, FCSCustomAuthTokenCallback fCSCustomAuthTokenCallback);

    void onInitCallback(boolean z);
}
