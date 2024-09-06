package com.talkingdata.sdk;

import android.os.Handler;
import android.os.HandlerThread;
import androidx.core.app.NotificationCompat;
import com.talkingdata.sdk.cm;
import com.talkingdata.sdk.zz;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.UUID;

/* compiled from: td */
/* loaded from: classes.dex */
public class cf {
    private static volatile cf a;
    private static final HandlerThread b;
    private static Handler c;

    public final void onTDEBEventSession(zz.a aVar) {
        if (aVar == null || aVar.a == null) {
            return;
        }
        try {
            int parseInt = Integer.parseInt(String.valueOf(aVar.a.get("apiType")));
            if (parseInt == 10) {
                b(aVar.a);
            } else if (parseInt == 11) {
                c(aVar.a);
            }
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    private void a(HashMap hashMap) {
        try {
            ap.c(Long.valueOf(String.valueOf(hashMap.get("occurTime"))).longValue(), (a) hashMap.get(NotificationCompat.CATEGORY_SERVICE));
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    private final void b(HashMap hashMap) {
        try {
            a aVar = (a) hashMap.get(NotificationCompat.CATEGORY_SERVICE);
            long longValue = Long.valueOf(String.valueOf(hashMap.get("occurTime"))).longValue();
            long b2 = ap.b(aVar);
            long d = ap.d(aVar);
            if (d <= b2) {
                d = b2;
            }
            if (longValue - d > ab.E) {
                a(aVar);
                a(longValue, aVar);
                ap.setLastActivity("");
            } else {
                an.iForDeveloper("[Session] - Same session as before!");
                cv.a().setSessionId(ap.a(aVar));
                cv.a().setSessionStartTime(b2);
            }
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    private void a(a aVar) {
        try {
            String a2 = ap.a(aVar);
            if (a2 == null || a2.trim().isEmpty()) {
                return;
            }
            long b2 = ap.b(aVar);
            long d = ap.d(aVar) - b2;
            if ((aVar.b().equals("APP") || aVar.b().equals("APP_SQL") || aVar.b().equals("TRACKING")) && d < 500) {
                d = -1000;
            }
            cn cnVar = new cn();
            cnVar.b = "session";
            cnVar.c = "end";
            TreeMap treeMap = new TreeMap();
            treeMap.put("sessionId", a2);
            treeMap.put("start", Long.valueOf(b2));
            treeMap.put("duration", Long.valueOf(d / 1000));
            cnVar.d = treeMap;
            cnVar.a = aVar;
            bk.a().post(cnVar);
            b(aVar);
            ap.a((String) null, aVar);
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    private void a(long j, a aVar) {
        try {
            an.iForDeveloper("[Session] - New session!");
            String uuid = UUID.randomUUID().toString();
            an.iForDeveloper("[Session] - Id: " + uuid);
            long d = ap.d(aVar);
            long j2 = j - d;
            if (0 == d) {
                j2 = 0;
            }
            ap.a(uuid, aVar);
            ap.a(j, aVar);
            cv.a().setSessionId(uuid);
            cv.a().setSessionStartTime(j);
            cn cnVar = new cn();
            cnVar.b = "session";
            cnVar.c = "begin";
            TreeMap treeMap = new TreeMap();
            treeMap.put("sessionId", uuid);
            treeMap.put("interval", Long.valueOf(j2 / 1000));
            cnVar.d = treeMap;
            cnVar.a = aVar;
            bk.a().post(cnVar);
            b(aVar);
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    private final void c(HashMap hashMap) {
        try {
            a aVar = (a) hashMap.get(NotificationCompat.CATEGORY_SERVICE);
            long longValue = Long.valueOf(String.valueOf(hashMap.get("occurTime"))).longValue();
            if (hashMap.containsKey("sessionEnd")) {
                a(aVar);
                return;
            }
            if (hashMap.containsKey("pageName")) {
                ap.setLastActivity(String.valueOf(hashMap.get("pageName")));
            }
            if (aVar.b().equals("GAME")) {
                b(aVar);
            }
            ap.c(longValue, aVar);
            ab.y = null;
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    private void b(a aVar) {
        cm cmVar = new cm();
        cmVar.a = aVar;
        cmVar.b = cm.a.IMMEDIATELY;
        bk.a().post(cmVar);
    }

    public static cf a() {
        if (a == null) {
            synchronized (cf.class) {
                if (a == null) {
                    a = new cf();
                }
            }
        }
        return a;
    }

    private cf() {
    }

    public static Handler b() {
        return c;
    }

    static {
        HandlerThread handlerThread = new HandlerThread("PauseEventThread");
        b = handlerThread;
        c = null;
        handlerThread.start();
        c = new cg(b.getLooper());
        try {
            bk.a().register(a());
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }
}
