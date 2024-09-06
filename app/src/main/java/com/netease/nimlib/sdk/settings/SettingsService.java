package com.netease.nimlib.sdk.settings;

import com.netease.nimlib.sdk.InvocationFuture;

/* loaded from: classes.dex */
public interface SettingsService {
    boolean isMultiportPushOpen();

    InvocationFuture<Void> updateMultiportPushConfig(boolean z);
}
