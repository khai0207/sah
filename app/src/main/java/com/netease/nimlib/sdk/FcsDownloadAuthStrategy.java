package com.netease.nimlib.sdk;

import android.util.Pair;
import com.netease.nim.highavailable.enums.HAvailableDownloadAuthType;

/* loaded from: classes.dex */
public interface FcsDownloadAuthStrategy {
    Pair<String, String> getAuthRefer();

    String getCustomAuthToken(String str);

    HAvailableDownloadAuthType getDownloadAuthType();
}
