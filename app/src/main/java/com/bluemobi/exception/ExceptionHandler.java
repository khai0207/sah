package com.bluemobi.exception;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.util.Log;
import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static ExceptionHandler INSTANCE;
    private static final String TAG = ExceptionHandler.class.getSimpleName();
    private static String key = "msg";
    private Class<?> clazz;
    private Context context;
    private String fileDirs;
    private String filename;
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
    private Map<String, String> infos = new HashMap();
    private boolean isSave = false;
    private SkipStatus skipStatus;

    /* loaded from: classes.dex */
    public interface SkipStatus {
        void skep(Context context);
    }

    public static ExceptionHandler getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ExceptionHandler(context);
        }
        return INSTANCE;
    }

    public static String getKey() {
        return key;
    }

    public static void setKey(String str) {
        key = str;
    }

    public void setSave(boolean z) {
        this.isSave = z;
    }

    public void setFilename(String str) {
        this.filename = str;
    }

    public void setFileDirs(String str) {
        this.fileDirs = str;
    }

    public void setClazz(Class cls) {
        this.clazz = cls;
    }

    public ExceptionHandler(Context context) {
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.context = context;
    }

    public void setSkipStatus(SkipStatus skipStatus) {
        this.skipStatus = skipStatus;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        String collectDeviceInfo = collectDeviceInfo(this.context, th);
        if (this.clazz != null) {
            this.context.startActivity(new Intent(this.context, this.clazz).addFlags(268435456).putExtra(key, collectDeviceInfo));
        }
        SkipStatus skipStatus = this.skipStatus;
        if (skipStatus != null) {
            skipStatus.skep(this.context);
        }
        if (this.isSave && this.filename != null && this.fileDirs != null) {
            saveCrashInfoToFile(collectDeviceInfo);
        }
        th.printStackTrace();
        Process.killProcess(Process.myPid());
    }

    public String collectDeviceInfo(Context context, Throwable th) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 1);
            if (packageInfo != null) {
                String str = packageInfo.versionName == null ? Constants.NULL_VERSION_ID : packageInfo.versionName;
                String sb = new StringBuilder(String.valueOf(packageInfo.versionCode)).toString();
                this.infos.put("versionName", str);
                this.infos.put("versionCode", sb);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "an error occured when collect package info", e);
        }
        for (Field field : Build.class.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                this.infos.put(field.getName(), field.get(null).toString());
                Log.d(TAG, String.valueOf(field.getName()) + " : " + field.get(null));
            } catch (Exception e2) {
                Log.e(TAG, "an error occured when collect crash info", e2);
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, String> entry : this.infos.entrySet()) {
            stringBuffer.append(String.valueOf(entry.getKey()) + "=" + entry.getValue() + "\n");
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
            cause.printStackTrace(printWriter);
        }
        printWriter.close();
        stringBuffer.append(stringWriter.toString());
        return stringBuffer.toString();
    }

    private String saveCrashInfoToFile(String str) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            String str2 = String.valueOf(this.filename) + "-" + this.formatter.format(new Date()) + "-" + currentTimeMillis + ".log";
            if (Environment.getExternalStorageState().equals("mounted")) {
                String str3 = this.fileDirs;
                if (str3.startsWith("/") && str3.endsWith("/")) {
                    File file = new File(str3);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    FileOutputStream fileOutputStream = new FileOutputStream(String.valueOf(str3) + str2);
                    fileOutputStream.write(str.getBytes());
                    fileOutputStream.close();
                }
                Log.e(TAG, "the file path is err");
                return null;
            }
            return str2;
        } catch (Exception e) {
            Log.e(TAG, "an error occured while writing file...", e);
            return null;
        }
    }
}
