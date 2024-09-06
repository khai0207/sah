package com.talkingdata.sdk;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* compiled from: td */
/* loaded from: classes.dex */
public class be {
    private be() {
        throw new AssertionError("no instances");
    }

    public static List a() {
        ArrayList arrayList = new ArrayList();
        try {
            for (File file : new File("/proc").listFiles()) {
                if (file.isDirectory()) {
                    try {
                        int parseInt = Integer.parseInt(file.getName());
                        ak akVar = new ak(-1);
                        if ((akVar.b < 1000 || akVar.b > 9999) && !akVar.c.contains(":") && !akVar.c.contains("/")) {
                            arrayList.add(new ak(parseInt));
                        }
                    } catch (Throwable unused) {
                    }
                }
            }
        } catch (Throwable unused2) {
        }
        return arrayList;
    }

    public static List a(Context context) {
        ArrayList arrayList = new ArrayList();
        try {
            File[] listFiles = new File("/proc").listFiles();
            context.getPackageManager();
            for (File file : listFiles) {
                if (file.isDirectory()) {
                    try {
                        ak akVar = new ak(Integer.parseInt(file.getName()));
                        if (akVar.a && ((akVar.b < 1000 || akVar.b > 9999) && !akVar.c.contains(":") && !akVar.c.contains("/"))) {
                            arrayList.add(akVar);
                        }
                    } catch (Throwable unused) {
                    }
                }
            }
        } catch (Throwable unused2) {
        }
        return arrayList;
    }

    public static boolean b() {
        try {
            List<ak> a = a();
            int myPid = Process.myPid();
            for (ak akVar : a) {
                if (akVar.d == myPid && akVar.a) {
                    return true;
                }
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static List b(Context context) {
        try {
            if (Build.VERSION.SDK_INT >= 22) {
                List<ak> a = a();
                ArrayList arrayList = new ArrayList();
                for (ak akVar : a) {
                    ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo(akVar.c, akVar.d, null);
                    runningAppProcessInfo.uid = akVar.b;
                    arrayList.add(runningAppProcessInfo);
                }
                return arrayList;
            }
            return ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        } catch (Throwable unused) {
            return null;
        }
    }
}
