package com.umeng.analytics;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.social.UMPlatformData;
import com.umeng.analytics.social.UMSocialService;
import com.unionpay.tsmservice.data.Constant;
import java.util.HashMap;
import java.util.Map;
import javax.microedition.khronos.opengles.GL10;
import u.aly.bt;
import u.aly.bv;

/* loaded from: classes.dex */
public class MobclickAgent {
    private static final String a = "input map is null";
    private static final d b = new d();

    public static void flush(Context context) {
    }

    public static String getConfigParams(Context context, String str) {
        return "";
    }

    public static void setAutoLocation(boolean z) {
    }

    public static void setOnlineConfigureListener(Object obj) {
    }

    public static void setUserID(Context context, String str, String str2, Gender gender, int i) {
    }

    public static void updateOnlineConfig(Context context) {
    }

    public static void updateOnlineConfig(Context context, String str, String str2) {
    }

    public static void setCatchUncaughtExceptions(boolean z) {
        AnalyticsConfig.CATCH_EXCEPTION = z;
    }

    public static void setWrapper(String str, String str2) {
        b.a(str, str2);
    }

    public static void setSessionContinueMillis(long j) {
        AnalyticsConfig.kContinueSessionMillis = j;
    }

    public static void setEnableEventBuffer(boolean z) {
        AnalyticsConfig.ENABLE_MEMORY_BUFFER = z;
    }

    public static void setCheckDevice(boolean z) {
        a.f = z;
    }

    public static d getAgent() {
        return b;
    }

    public static void setOpenGLContext(GL10 gl10) {
        if (gl10 != null) {
            String[] a2 = bt.a(gl10);
            if (a2.length == 2) {
                AnalyticsConfig.GPU_VENDER = a2[0];
                AnalyticsConfig.GPU_RENDERER = a2[1];
            }
        }
    }

    public static void openActivityDurationTrack(boolean z) {
        AnalyticsConfig.ACTIVITY_DURATION_OPEN = z;
    }

    public static void onPageStart(String str) {
        if (!TextUtils.isEmpty(str)) {
            b.a(str);
        } else {
            bv.e("pageName is null or empty");
        }
    }

    public static void onPageEnd(String str) {
        if (!TextUtils.isEmpty(str)) {
            b.b(str);
        } else {
            bv.e("pageName is null or empty");
        }
    }

    public static void setDebugMode(boolean z) {
        bv.a = z;
        com.umeng.analytics.social.e.v = z;
    }

    public static void onPause(Context context) {
        b.b(context);
    }

    public static void onResume(Context context) {
        if (context == null) {
            bv.e("unexpected null context in onResume");
        } else {
            b.a(context);
        }
    }

    public static void onResume(Context context, String str, String str2) {
        if (context == null) {
            bv.e("unexpected null context in onResume");
            return;
        }
        if (str == null || str.length() == 0) {
            bv.e("unexpected empty appkey in onResume");
            return;
        }
        AnalyticsConfig.setAppkey(context, str);
        AnalyticsConfig.setChannel(str2);
        b.a(context);
    }

    public static void reportError(Context context, String str) {
        b.a(context, str);
    }

    public static void reportError(Context context, Throwable th) {
        b.a(context, th);
    }

    public static void onEvent(Context context, String str) {
        b.a(context, str, null, -1L, 1);
    }

    public static void onEvent(Context context, String str, int i) {
        b.a(context, str, null, -1L, i);
    }

    public static void onEvent(Context context, String str, String str2, int i) {
        if (TextUtils.isEmpty(str2)) {
            bv.c("label is null or empty");
        } else {
            b.a(context, str, str2, -1L, i);
        }
    }

    public static void onEvent(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            bv.c("label is null or empty");
        } else {
            b.a(context, str, str2, -1L, 1);
        }
    }

    public static void onEvent(Context context, String str, Map<String, String> map) {
        if (map == null) {
            bv.e(a);
        } else {
            b.a(context, str, new HashMap(map), -1L);
        }
    }

    public static void onEventDuration(Context context, String str, long j) {
        if (j <= 0) {
            bv.c("duration is not valid in onEventDuration");
        } else {
            b.a(context, str, null, j, 1);
        }
    }

    public static void onEventDuration(Context context, String str, String str2, long j) {
        if (TextUtils.isEmpty(str2)) {
            bv.c("label is null or empty");
        } else if (j <= 0) {
            bv.c("duration is not valid in onEventDuration");
        } else {
            b.a(context, str, str2, j, 1);
        }
    }

    public static void onEventDuration(Context context, String str, Map<String, String> map, long j) {
        if (j <= 0) {
            bv.c("duration is not valid in onEventDuration");
        } else if (map == null) {
            bv.e(a);
        } else {
            b.a(context, str, new HashMap(map), j);
        }
    }

    public static void onEventValue(Context context, String str, Map<String, String> map, int i) {
        HashMap hashMap;
        if (map == null) {
            hashMap = new HashMap();
        } else {
            hashMap = new HashMap(map);
        }
        hashMap.put("__ct__", Integer.valueOf(i));
        b.a(context, str, hashMap, -1L);
    }

    public static void onEventBegin(Context context, String str) {
        b.a(context, str, (String) null);
    }

    public static void onEventEnd(Context context, String str) {
        b.b(context, str, null);
    }

    public static void onEventBegin(Context context, String str, String str2) {
        b.a(context, str, str2);
    }

    public static void onEventEnd(Context context, String str, String str2) {
        b.b(context, str, str2);
    }

    public static void onKVEventBegin(Context context, String str, Map<String, String> map, String str2) {
        if (map == null) {
            bv.e(a);
        } else {
            b.a(context, str, new HashMap<>(map), str2);
        }
    }

    public static void onKVEventEnd(Context context, String str, String str2) {
        b.c(context, str, str2);
    }

    public static void onSocialEvent(Context context, String str, UMPlatformData... uMPlatformDataArr) {
        if (context == null) {
            bv.e("context is null in onShareEvent");
        } else {
            com.umeng.analytics.social.e.e = Constant.APPLY_MODE_DECIDED_BY_BANK;
            UMSocialService.share(context, str, uMPlatformDataArr);
        }
    }

    public static void onSocialEvent(Context context, UMPlatformData... uMPlatformDataArr) {
        if (context == null) {
            bv.e("context is null in onShareEvent");
        } else {
            com.umeng.analytics.social.e.e = Constant.APPLY_MODE_DECIDED_BY_BANK;
            UMSocialService.share(context, uMPlatformDataArr);
        }
    }

    public static void onKillProcess(Context context) {
        b.d(context);
    }

    public static void onProfileSignIn(String str) {
        onProfileSignIn("_adhoc", str);
    }

    public static void onProfileSignIn(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            bv.d("uid is null");
            return;
        }
        if (str2.length() > 64) {
            bv.d("uid is Illegal(length bigger then  legitimate length).");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            b.b("_adhoc", str2);
        } else if (str.length() > 32) {
            bv.d("provider is Illegal(length bigger then  legitimate length).");
        } else {
            b.b(str, str2);
        }
    }

    public static void onProfileSignOff() {
        b.b();
    }
}
