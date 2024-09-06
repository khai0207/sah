package com.UCMobile.PayPlugin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* loaded from: classes.dex */
public class PayResultReceiver extends BroadcastReceiver {
    private static native void native_UCPayResultNotify(String str);

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        native_UCPayResultNotify(intent.getStringExtra("ResultURL"));
        String packageName = context.getPackageName();
        if (packageName.startsWith("com.UCMobile")) {
            Intent intent2 = new Intent("com.UCMobile.PluginApp.ActivityState");
            intent2.putExtra("ActivityState", "inactive");
            intent2.putExtra("PackageName", packageName);
            context.sendBroadcast(intent2);
        }
    }
}
