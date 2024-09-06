package com.netease.nimlib.sdk.util;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.netease.nimlib.log.b;
import com.netease.nimlib.log.b.a;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Collection;

/* loaded from: classes.dex */
public final class NIMUtil {
    private static final String TAG = "NIMUtil";
    private static String currentProcessName;

    private NIMUtil() {
    }

    public static int isMainProcessPure(Context context) {
        if (context == null) {
            if (a.a()) {
                Log.d(TAG, " isMainProcessPure context == null");
            }
            return -1;
        }
        String packageName = context.getApplicationContext().getPackageName();
        String processNamePure = getProcessNamePure(context);
        if (TextUtils.isEmpty(processNamePure)) {
            if (a.a()) {
                Log.d(TAG, " isMainProcessPure can not get processName");
            }
            return -1;
        }
        if (a.a()) {
            Log.d(TAG, " isMainProcessPure packageName = " + packageName + ",processName = " + processNamePure);
        }
        return packageName.equals(processNamePure) ? 1 : 0;
    }

    public static String getProcessNamePure(Context context) {
        if (!TextUtils.isEmpty(currentProcessName)) {
            if (a.a()) {
                Log.d(TAG, "get processName from Cache = " + currentProcessName);
            }
            return currentProcessName;
        }
        String currentProcessNameByApplication = getCurrentProcessNameByApplication();
        currentProcessName = currentProcessNameByApplication;
        if (!TextUtils.isEmpty(currentProcessNameByApplication)) {
            if (a.a()) {
                Log.d(TAG, "get processName from Application = " + currentProcessName);
            }
            return currentProcessName;
        }
        String currentProcessNameByActivityThread = getCurrentProcessNameByActivityThread();
        currentProcessName = currentProcessNameByActivityThread;
        if (!TextUtils.isEmpty(currentProcessNameByActivityThread)) {
            if (a.a()) {
                Log.d(TAG, "get processName from ActivityThread = " + currentProcessName);
            }
            return currentProcessName;
        }
        return currentProcessName;
    }

    public static boolean isMainProcess(Context context) {
        if (context == null) {
            return false;
        }
        return context.getApplicationContext().getPackageName().equals(getProcessName(context));
    }

    public static String getProcessName(Context context) {
        if (!TextUtils.isEmpty(currentProcessName)) {
            if (a.a()) {
                Log.d(TAG, "get processName from Cache = " + currentProcessName);
            }
            return currentProcessName;
        }
        String currentProcessNameByApplication = getCurrentProcessNameByApplication();
        currentProcessName = currentProcessNameByApplication;
        if (!TextUtils.isEmpty(currentProcessNameByApplication)) {
            if (a.a()) {
                Log.d(TAG, "get processName from Application = " + currentProcessName);
            }
            return currentProcessName;
        }
        String currentProcessNameByActivityThread = getCurrentProcessNameByActivityThread();
        currentProcessName = currentProcessNameByActivityThread;
        if (!TextUtils.isEmpty(currentProcessNameByActivityThread)) {
            if (a.a()) {
                Log.d(TAG, "get processName from ActivityThread = " + currentProcessName);
            }
            return currentProcessName;
        }
        String processFromFile = getProcessFromFile();
        currentProcessName = processFromFile;
        if (!TextUtils.isEmpty(processFromFile)) {
            if (a.a()) {
                Log.d(TAG, "get processName from File = " + currentProcessName);
            }
            return currentProcessName;
        }
        if (a.a()) {
            Log.d(TAG, "get processName from ActivityManager = " + currentProcessName);
        }
        return currentProcessName;
    }

    private static String getProcessFromFile() {
        BufferedReader bufferedReader;
        Throwable th;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/" + Process.myPid() + "/cmdline"), "iso-8859-1"));
        } catch (Exception unused) {
            bufferedReader = null;
        } catch (Throwable th2) {
            bufferedReader = null;
            th = th2;
        }
        try {
            StringBuilder sb = new StringBuilder();
            while (true) {
                int read = bufferedReader.read();
                if (read <= 0) {
                    break;
                }
                sb.append((char) read);
            }
            String sb2 = sb.toString();
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb2;
        } catch (Exception unused2) {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static String getNimDefaultCacheDir(Context context) {
        String str;
        if (context.getCacheDir() != null) {
            str = context.getCacheDir().getAbsolutePath();
        } else {
            b.f(TAG, "loadStorageState context.getCacheDir() == null");
            str = "";
        }
        if (TextUtils.isEmpty(str)) {
            if (Environment.getExternalStorageDirectory() != null) {
                str = Environment.getExternalStorageDirectory().getPath() + "/" + context.getPackageName();
            } else {
                b.f(TAG, "loadStorageState Environment.getExternalStorageDirectory() == null");
            }
        }
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        return str + "/nim/";
    }

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    private static String getCurrentProcessNameByApplication() {
        if (Build.VERSION.SDK_INT >= 28) {
            return Application.getProcessName();
        }
        return null;
    }

    private static String getCurrentProcessNameByActivityThread() {
        try {
            Method declaredMethod = Class.forName("android.app.ActivityThread", false, Application.class.getClassLoader()).getDeclaredMethod("currentProcessName", new Class[0]);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(null, new Object[0]);
            if (invoke instanceof String) {
                return (String) invoke;
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }
}
