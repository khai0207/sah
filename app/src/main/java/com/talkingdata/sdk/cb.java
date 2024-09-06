package com.talkingdata.sdk;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.iflytek.cloud.SpeechConstant;
import com.talkingdata.sdk.cm;
import com.talkingdata.sdk.zz;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* compiled from: td */
/* loaded from: classes.dex */
public class cb {
    private static volatile cb a;
    private static boolean b;
    private static boolean c;
    private static boolean d;

    public final void onTDEBEventInitEvent(zz.a aVar) {
        try {
            if (Integer.parseInt(String.valueOf(aVar.a.get("apiType"))) != 1) {
                return;
            }
            String valueOf = String.valueOf(aVar.a.get("action"));
            a aVar2 = (a) aVar.a.get(NotificationCompat.CATEGORY_SERVICE);
            if (valueOf.equals("install") && aVar2.b().equals("TRACKING")) {
                cn cnVar = new cn();
                Object obj = aVar.a.get("data");
                cnVar.b = String.valueOf(aVar.a.get(SpeechConstant.DOMAIN));
                cnVar.c = valueOf;
                if (obj != null && (obj instanceof Map)) {
                    cnVar.d = (Map) obj;
                }
                cnVar.a = aVar2;
                bk.a().post(cnVar);
                return;
            }
            if (valueOf.equals("init")) {
                Context context = ab.f;
                String valueOf2 = String.valueOf(aVar.a.get("appId"));
                String valueOf3 = String.valueOf(aVar.a.get("channelId"));
                String a2 = bh.a(context, "ChannelConfig.json");
                if (!bh.b(a2)) {
                    valueOf3 = a2;
                }
                if (bh.b(valueOf3)) {
                    valueOf3 = "Default";
                }
                if (bh.b(valueOf2)) {
                    an.eForDeveloper("[SDKInit] TD AppId is null");
                    return;
                }
                ab.a(valueOf2, valueOf3, aVar2);
                cf.a();
                if (a(context)) {
                    by.a();
                    ca.a();
                    bz.a();
                }
                a(aVar2);
                b();
                ab.b = true;
                if (aVar2.b().equals("TRACKING")) {
                    a(context, aVar2);
                }
                sendInitEventWithTDFeatures(aVar2);
                return;
            }
            if (valueOf.equals("sendInit")) {
                sendInitEventWithTDFeatures(aVar2);
            }
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    private void a(Context context, a aVar) {
        if (d) {
            return;
        }
        try {
            String str = "TalkingData AppCpa SDK init...\n\tSDK_VERSION is: Android+TD+V4.0.8 gp Type:" + ab.b() + "\n\tApp ID is: " + ab.a(context, aVar) + "\n\tApp Channel is: " + ab.b(context, aVar) + "\n\tSDK_OVC is: " + aj.e;
            if (ab.b || an.a) {
                Log.i(ab.p, str);
            }
            String n = ap.n();
            if (!TextUtils.isEmpty(n)) {
                cv.a().setDeepLink(n);
            }
            d = true;
        } catch (Throwable unused) {
        }
    }

    private static String a(Bundle bundle, String str) {
        if (bundle == null) {
            return null;
        }
        try {
            Iterator<String> it = bundle.keySet().iterator();
            while (it.hasNext()) {
                if (it.next().equalsIgnoreCase(str)) {
                    return String.valueOf(bundle.get(str));
                }
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static void b() {
        try {
            if (ap.e() == 0) {
                ap.setInitTime(System.currentTimeMillis());
            }
        } catch (Throwable unused) {
        }
    }

    private static void a(a aVar) {
        try {
            if (aVar == null) {
                an.eForInternal("TDFeatures is null...");
            } else if (ap.c(aVar) == 0) {
                ap.b(System.currentTimeMillis(), aVar);
            } else if (System.currentTimeMillis() - ap.c(aVar) > com.umeng.analytics.a.h) {
                bh.e = true;
            }
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    public static void sendInitEventWithTDFeatures(a aVar) {
        try {
            if (aVar == null) {
                an.eForInternal("TDFeatures is null...");
                return;
            }
            TreeMap treeMap = new TreeMap();
            boolean z = System.currentTimeMillis() - ap.c(aVar) <= 5000;
            treeMap.put("first", Boolean.valueOf(z));
            cn cnVar = new cn();
            cnVar.b = "app";
            cnVar.c = "init";
            cnVar.d = treeMap;
            cnVar.a = aVar;
            bk.a().post(cnVar);
            cm cmVar = new cm();
            cmVar.a = aVar;
            cmVar.b = cm.a.IMMEDIATELY;
            bk.a().post(cmVar);
            if (z) {
                c();
            }
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    private static boolean a(Context context) {
        try {
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
        if (bh.a(22)) {
            return be.b();
        }
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        String str = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).processName;
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (Process.myPid() == runningAppProcessInfo.pid && runningAppProcessInfo.processName.equals(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void c() {
        try {
            cn cnVar = new cn();
            cnVar.b = "env";
            cnVar.c = "getProp";
            TreeMap treeMap = new TreeMap();
            treeMap.put("sysproperty", bh.a());
            cnVar.d = treeMap;
            cnVar.a = a.e;
            bk.a().post(cnVar);
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    public static cb a() {
        if (a == null) {
            synchronized (cb.class) {
                if (a == null) {
                    a = new cb();
                }
            }
        }
        return a;
    }

    private cb() {
    }

    static {
        try {
            bk.a().register(a());
        } catch (Throwable unused) {
        }
        a = null;
        b = false;
        c = false;
        d = false;
    }
}
