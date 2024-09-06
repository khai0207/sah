package com.talkingdata.sdk;

import android.app.ActivityManager;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* compiled from: td */
/* loaded from: classes.dex */
public final class bz {
    private static volatile bz a;
    private static Map b = new TreeMap();

    public static bz a() {
        if (a == null) {
            synchronized (bz.class) {
                if (a == null) {
                    a = new bz();
                }
            }
        }
        return a;
    }

    private bz() {
        try {
            if (b()) {
                c();
                d();
            }
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    private void c() {
        try {
            ArrayList arrayList = new ArrayList();
            if (bh.a(21)) {
                for (ak akVar : be.a()) {
                    if (!akVar.c.startsWith("android.") && !akVar.c.equals("system")) {
                        arrayList.add(akVar.c);
                    }
                }
            } else {
                ActivityManager activityManager = (ActivityManager) ab.f.getSystemService("activity");
                PackageManager packageManager = ab.f.getPackageManager();
                List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
                if (runningAppProcesses != null) {
                    Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
                    while (it.hasNext()) {
                        String str = it.next().processName;
                        try {
                            if (packageManager.getLaunchIntentForPackage(str) != null) {
                                arrayList.add(str);
                            }
                        } catch (Throwable unused) {
                        }
                    }
                }
            }
            b.put("ras", arrayList.toString());
            ap.setCollectRunningTime(System.currentTimeMillis());
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    boolean b() {
        try {
            return System.currentTimeMillis() - ap.g() > 10800000;
        } catch (Throwable unused) {
            return false;
        }
    }

    private void d() {
        cn cnVar = new cn();
        cnVar.b = "env";
        cnVar.c = "apps";
        cnVar.d = b;
        cnVar.a = a.e;
        bk.a().post(cnVar);
    }
}
