package com.tendcloud.appcpa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.iflytek.cloud.SpeechConstant;
import com.talkingdata.sdk.a;
import com.talkingdata.sdk.ab;
import com.talkingdata.sdk.zz;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.TreeMap;

/* compiled from: td */
/* loaded from: classes.dex */
public class ReferralReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String stringExtra;
        try {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                extras.containsKey(null);
            }
            new HashMap();
            if (!intent.getAction().equals("com.android.vending.INSTALL_REFERRER") || (stringExtra = intent.getStringExtra("referrer")) == null || stringExtra.length() == 0) {
                return;
            }
            Log.d("install_referer", stringExtra);
            String decode = URLDecoder.decode(stringExtra, "UTF-8");
            ab.f = context.getApplicationContext();
            zz.a aVar = new zz.a();
            aVar.a.put(SpeechConstant.DOMAIN, "app");
            aVar.a.put("apiType", 1);
            aVar.a.put("action", "install");
            aVar.a.put(NotificationCompat.CATEGORY_SERVICE, a.d);
            TreeMap treeMap = new TreeMap();
            treeMap.put("referer", decode);
            aVar.a.put("data", treeMap);
            zz.c().obtainMessage(102, aVar).sendToTarget();
        } catch (Throwable unused) {
        }
    }
}
