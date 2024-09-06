package com.netease.nimlib.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.netease.nimlib.biz.i;

/* loaded from: classes.dex */
public class ResponseReceiver extends BroadcastReceiver {
    public static void a(Context context) {
        if (context == null) {
            com.netease.nimlib.log.b.O("Push awake UI by Broadcast failed, as context is null");
            return;
        }
        Intent intent = new Intent(context, (Class<?>) ResponseReceiver.class);
        intent.addFlags(268435456);
        context.sendBroadcast(intent);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            com.netease.nimlib.log.b.N("Push awake UI by Broadcast success, UI process run!");
            i.a().j();
        } catch (Throwable unused) {
        }
    }
}
