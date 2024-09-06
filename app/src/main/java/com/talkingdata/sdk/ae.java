package com.talkingdata.sdk;

import android.app.Activity;
import android.os.Message;

/* compiled from: td */
/* loaded from: classes.dex */
final class ae {
    private ae() {
    }

    static void a(Activity activity, a aVar) {
        try {
            if (aVar.b().equals("APP")) {
                if (ap.e(aVar) != null && ap.e(aVar).equals("2")) {
                    bh.execute(new ag(aVar));
                }
                ap.b("0", aVar);
                cf.b().removeMessages(0);
                if (activity != null && (activity.getChangingConfigurations() & 128) == 128) {
                    an.iForDeveloper("Ignore page changing during screen switch");
                    zz.v = true;
                    return;
                }
            }
            bh.execute(new ah(aVar));
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    static void b(Activity activity, a aVar) {
        try {
            if (aVar.b().equals("APP") || aVar.b().equals("APP_SQL")) {
                ap.b("1", aVar);
                cf.b().removeMessages(0);
                Message obtain = Message.obtain();
                obtain.obj = aVar;
                obtain.what = 0;
                cf.b().sendMessageDelayed(obtain, ab.E);
            }
            bh.execute(new ai(aVar, activity));
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }
}
