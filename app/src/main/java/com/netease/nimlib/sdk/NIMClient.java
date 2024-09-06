package com.netease.nimlib.sdk;

import android.content.Context;
import android.util.Log;
import com.netease.nimlib.c;
import com.netease.nimlib.h;
import com.netease.nimlib.log.b.a;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.msg.model.CaptureDeviceInfoConfig;
import com.netease.nimlib.sdk.util.NIMUtil;
import com.netease.nimlib.sdk.util.api.RequestResult;

/* loaded from: classes.dex */
public class NIMClient {
    public static final String TAG = "NIMClient";

    public static String getSDKVersion() {
        return "9.17.0";
    }

    public static void init(Context context, LoginInfo loginInfo, SDKOptions sDKOptions) {
        long currentTimeMillis = System.currentTimeMillis();
        if (a.a()) {
            Log.i(TAG, "NIMClient init");
        }
        c.a(context, loginInfo, sDKOptions);
        if (NIMUtil.isMainProcess(context)) {
            c.a();
            com.netease.nimlib.n.a.a().a(currentTimeMillis, TAG, "init");
        }
    }

    public static void config(Context context, LoginInfo loginInfo, SDKOptions sDKOptions) {
        long currentTimeMillis = System.currentTimeMillis();
        if (a.a()) {
            Log.i(TAG, "NIMClient config");
        }
        c.a(context, loginInfo, sDKOptions);
        if (NIMUtil.isMainProcessPure(context) > 0) {
            h.a(2);
            com.netease.nimlib.n.a.a().a(currentTimeMillis, TAG, "config");
        }
    }

    public static void initSDK() {
        long currentTimeMillis = System.currentTimeMillis();
        if (a.a()) {
            Log.i(TAG, "NIMClient initSDK");
        }
        c.a();
        if (NIMUtil.isMainProcess(c.e())) {
            com.netease.nimlib.n.a.a().a(currentTimeMillis, TAG, "initSDK");
        }
    }

    public static void initDelay(Context context, LoginInfo loginInfo, SDKOptions sDKOptions) {
        long currentTimeMillis = System.currentTimeMillis();
        if (a.a()) {
            Log.i(TAG, "NIMClient initDelay");
        }
        if (NIMUtil.isMainProcess(context)) {
            c.a(context, loginInfo, sDKOptions, true);
            c.a();
            com.netease.nimlib.n.a.a().a(currentTimeMillis, TAG, "initDelay");
        }
    }

    public static <T> T getService(Class<T> cls) {
        return (T) c.a(cls);
    }

    public static <T> RequestResult<T> syncRequest(InvocationFuture<T> invocationFuture, long j) {
        return com.netease.nimlib.i.a.a.a.a(invocationFuture, j);
    }

    public static <T> RequestResult<T> syncRequest(InvocationFuture<T> invocationFuture) {
        return com.netease.nimlib.i.a.a.a.a(invocationFuture, 30000L);
    }

    public static String getCurrentAccount() {
        String n = c.n();
        return n == null ? "" : n;
    }

    public static String getAppKey() {
        try {
            String g = c.g();
            return g == null ? "" : g;
        } catch (Exception unused) {
            return "";
        }
    }

    public static StatusCode getStatus() {
        return h.e();
    }

    public static ModeCode getMode() {
        return h.f();
    }

    public static void toggleNotification(boolean z) {
        c.a(z);
    }

    public static void toggleRevokeMessageNotification(boolean z) {
        c.b(z);
    }

    public static void updateStatusBarNotificationConfig(StatusBarNotificationConfig statusBarNotificationConfig) {
        c.a(statusBarNotificationConfig);
    }

    public static void updateStrings(NimStrings nimStrings) {
        c.a(nimStrings);
    }

    public static String getSdkStorageDirPath() {
        return com.netease.nimlib.o.b.c.a();
    }

    public static void updateTokenSceneConfig(NosTokenSceneConfig nosTokenSceneConfig) {
        c.a(nosTokenSceneConfig);
    }

    public static void updateCaptureDeviceInfoOption(CaptureDeviceInfoConfig captureDeviceInfoConfig) {
        c.a(captureDeviceInfoConfig);
    }
}
