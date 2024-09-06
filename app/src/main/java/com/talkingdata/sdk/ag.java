package com.talkingdata.sdk;

import android.os.Message;
import androidx.core.app.NotificationCompat;
import com.talkingdata.sdk.zz;

/* compiled from: td */
/* loaded from: classes.dex */
final class ag implements Runnable {
    final /* synthetic */ a a;

    ag(a aVar) {
        this.a = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        zz.a aVar = new zz.a();
        aVar.a.put("apiType", 1);
        aVar.a.put("action", "sendInit");
        aVar.a.put(NotificationCompat.CATEGORY_SERVICE, this.a);
        Message.obtain(zz.c(), 102, aVar).sendToTarget();
    }
}
