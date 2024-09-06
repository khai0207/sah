package com.talkingdata.sdk;

import android.app.Activity;
import android.os.Message;
import androidx.core.app.NotificationCompat;
import com.talkingdata.sdk.zz;

/* compiled from: td */
/* loaded from: classes.dex */
final class ai implements Runnable {
    final /* synthetic */ a a;
    final /* synthetic */ Activity b;

    ai(a aVar, Activity activity) {
        this.a = aVar;
        this.b = activity;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            zz.a aVar = new zz.a();
            aVar.a.put("apiType", 11);
            aVar.a.put(NotificationCompat.CATEGORY_SERVICE, this.a);
            aVar.a.put("pageName", this.b != null ? this.b.getLocalClassName() : "");
            aVar.a.put("occurTime", String.valueOf(System.currentTimeMillis()));
            Message.obtain(zz.c(), 102, aVar).sendToTarget();
        } catch (Throwable unused) {
        }
    }
}
