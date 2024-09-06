package com.talkingdata.sdk;

import android.os.Message;
import androidx.core.app.NotificationCompat;
import com.talkingdata.sdk.zz;

/* compiled from: td */
/* loaded from: classes.dex */
final class ah implements Runnable {
    final /* synthetic */ a a;

    ah(a aVar) {
        this.a = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            zz.a aVar = new zz.a();
            aVar.a.put("apiType", 10);
            aVar.a.put("occurTime", Long.valueOf(System.currentTimeMillis()));
            aVar.a.put(NotificationCompat.CATEGORY_SERVICE, this.a);
            Message.obtain(zz.c(), 102, aVar).sendToTarget();
        } catch (Throwable unused) {
        }
    }
}
