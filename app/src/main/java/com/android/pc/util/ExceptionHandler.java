package com.android.pc.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
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
    private Context context;
    private HashMap<String, String> exceptionBean;
    private String fileDirs;
    private String filename;
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
    private Map<String, String> infos = new HashMap();
    private boolean isSave = false;
    private Handler handler = null;

    public static ExceptionHandler getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ExceptionHandler(context);
        }
        return INSTANCE;
    }

    public void SetHandler(Handler handler) {
        this.handler = handler;
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

    public ExceptionHandler(Context context) {
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.context = context;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        final String collectDeviceInfo = collectDeviceInfo(this.context, th);
        if (this.handler != null) {
            Message message = new Message();
            message.obj = collectDeviceInfo;
            this.handler.sendMessage(message);
        }
        System.out.println("错误：" + collectDeviceInfo);
        if (!this.isSave || this.filename == null || this.fileDirs == null) {
            return;
        }
        new Thread(new Runnable() { // from class: com.android.pc.util.ExceptionHandler.1
            @Override // java.lang.Runnable
            public void run() {
                ExceptionHandler.this.saveCrashInfoToFile(collectDeviceInfo);
            }
        }).start();
    }

    private String collectDeviceInfo(Context context, Throwable th) {
        HashMap<String, String> hashMap = new HashMap<>();
        this.exceptionBean = hashMap;
        hashMap.put("projectName", context.getApplicationInfo().loadLabel(context.getPackageManager()).toString());
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 1);
            if (packageInfo != null) {
                String str = packageInfo.versionName == null ? Constants.NULL_VERSION_ID : packageInfo.versionName;
                String str2 = packageInfo.versionCode + "";
                this.exceptionBean.put("versionName", str);
                this.exceptionBean.put("versionCode", str2);
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        for (Field field : Build.class.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                this.infos.put(field.getName(), field.get(null).toString());
            } catch (Exception unused2) {
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, String> entry : this.infos.entrySet()) {
            stringBuffer.append(entry.getKey() + "=" + entry.getValue() + "\n");
        }
        this.exceptionBean.put("devInfo", stringBuffer.toString());
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
            cause.printStackTrace(printWriter);
        }
        printWriter.close();
        this.exceptionBean.put("exceptionMsg", stringWriter.toString());
        return stringBuffer.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String saveCrashInfoToFile(String str) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            String str2 = this.filename + "-" + this.formatter.format(new Date()) + "-" + currentTimeMillis + ".log";
            if (Environment.getExternalStorageState().equals("mounted")) {
                String str3 = this.fileDirs;
                if (str3.startsWith("/") && str3.endsWith("/")) {
                    File file = new File(str3);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    FileOutputStream fileOutputStream = new FileOutputStream(str3 + str2);
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
