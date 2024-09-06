package com.talkingdata.sdk;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.core.app.NotificationCompat;
import com.talkingdata.sdk.zz;

/* compiled from: td */
/* loaded from: classes.dex */
final class cg extends Handler {
    cg(Looper looper) {
        super(looper);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        try {
            a aVar = (a) message.obj;
            if (ap.e(aVar).equals("1")) {
                zz.c().removeMessages(103);
                zz.a aVar2 = new zz.a();
                aVar2.a.put("apiType", 11);
                aVar2.a.put("occurTime", String.valueOf(System.currentTimeMillis()));
                aVar2.a.put("sessionEnd", 1);
                aVar2.a.put(NotificationCompat.CATEGORY_SERVICE, aVar);
                Message.obtain(zz.c(), 102, aVar2).sendToTarget();
                ap.b("2", aVar);
            }
        } catch (Exception e) {
            ce.postSDKError(e);
        }
    }
}
