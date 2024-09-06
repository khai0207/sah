package com.UCMobile.PayPlugin;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import com.unionpay.mobile.android.utils.j;

/* loaded from: classes.dex */
public class PayInterface {
    private static boolean a = false;

    public static int show(Context context, String str) {
        j.a("uppay", "UC.PayInterface.show() +++ ");
        boolean z = true;
        if (!a) {
            context.registerReceiver(new PayResultReceiver(), new IntentFilter("com.unionpay.uppay.resultURL"));
            a = true;
        }
        if (str == null) {
            j.a("uppay", "data == null!!!!");
            return -1;
        }
        String[] split = str.split(",");
        if (split != null && split.length != 1) {
            z = false;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("reqOriginalId", 4);
        if (z) {
            bundle.putString("paydata", str);
        } else {
            bundle.putString("oldVersionPlugin", "true");
        }
        String packageName = context.getPackageName();
        if (packageName != null && packageName.startsWith("com.UCMobile")) {
            Intent intent = new Intent();
            intent.setFlags(33554432);
            intent.putExtras(bundle);
            intent.putExtra("PackageName", packageName);
            intent.setClassName("com.unionpay.uppay", "com.unionpay.uppay.PayActivity");
            context.startActivity(intent);
            Intent intent2 = new Intent("com.UCMobile.PluginApp.ActivityState");
            intent2.putExtra("ActivityState", "active");
            intent2.putExtra("PackageName", packageName);
            intent2.addCategory("android.intent.category.DEFAULT");
            context.sendBroadcast(intent2);
        }
        j.a("uppay", "UC.PayInterface.show() +++ ");
        return 0;
    }
}
