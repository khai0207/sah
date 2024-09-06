package com.netease.nimlib.sdk.settings;

import com.netease.nimlib.i.d;
import com.netease.nimlib.sdk.Observer;

@d
/* loaded from: classes.dex */
public interface SettingsServiceObserver {
    void observeMultiportPushConfigNotify(Observer<Boolean> observer, boolean z);
}
