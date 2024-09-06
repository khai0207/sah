package com.iflytek.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* loaded from: classes.dex */
final class b extends BroadcastReceiver {
    final /* synthetic */ LaunchService a;

    b(LaunchService launchService) {
        this.a = launchService;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        com.iflytek.common.c.c.a(context, "alarm onReceive");
        com.iflytek.common.a.c.a(context);
        this.a.b();
    }
}
