package com.iflytek.cloud.b;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.Setting;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.Version;
import com.iflytek.cloud.a.g.e;
import com.iflytek.cloud.a.g.h;
import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import com.talkingdata.sdk.aj;

/* loaded from: classes.dex */
public class c {
    private static String a = "xiaoyan";

    public static String a(Context context) {
        if (context == null) {
            return Constants.NULL_VERSION_ID;
        }
        a a2 = com.iflytek.cloud.a.g.a.a(context);
        String str = a2.e("os.imsi") + "|" + a2.e("os.imei");
        if (str.length() < 10) {
            str = a2.e("net.mac");
        }
        if (TextUtils.isEmpty(str) || str.length() <= 0) {
            return null;
        }
        return str;
    }

    public static String a(Context context, com.iflytek.cloud.a.d.a aVar) throws SpeechError {
        if (context == null) {
            throw new SpeechError(ErrorCode.ERROR_INVALID_PARAM);
        }
        a clone = aVar.t().clone();
        a(context, clone);
        clone.a("timeout", "20000", false);
        clone.a(com.alipay.sdk.m.g.b.n, "1", false);
        clone.a("msc.ver", Version.getVersion());
        clone.a("mac", com.iflytek.cloud.a.g.a.a(context).e("net.mac"), false);
        clone.a("dvc", a(context), false);
        clone.a("msc.lat", "" + com.iflytek.cloud.a.g.b.a(context).a("msc.lat"), false);
        clone.a("msc.lng", "" + com.iflytek.cloud.a.g.b.a(context).a("msc.lng"), false);
        clone.a(com.iflytek.cloud.a.g.a.b(context));
        a(clone);
        clone.a(b.c);
        return clone.toString();
    }

    public static String a(Context context, String str, com.iflytek.cloud.a.d.a aVar) {
        String str2;
        a clone = aVar.t().clone();
        clone.c(SpeechConstant.CLOUD_GRAMMAR);
        a(context, clone);
        clone.a(SpeechConstant.LANGUAGE, "zh_cn", false);
        clone.a(SpeechConstant.RESULT_TYPE, "json", false);
        clone.a("rse", aVar.p(), false);
        clone.a(SpeechConstant.TEXT_ENCODING, aVar.o(), false);
        clone.a("ssm", "1", false);
        clone.a(SpeechConstant.SUBJECT, TextUtils.isEmpty(str) ? "iat" : "asr", false);
        int q = aVar.q();
        clone.a("auf=audio/L16;rate", Integer.toString(q), false);
        clone.a("aue", q == 16000 ? "speex-wb" : "speex", false);
        if (aVar.i()) {
            clone.a(SpeechConstant.VAD_BOS, "5000", false);
            str2 = "1800";
        } else {
            clone.a(SpeechConstant.VAD_BOS, "4000", false);
            str2 = "700";
        }
        clone.a(SpeechConstant.VAD_EOS, str2, false);
        clone.a(b.c);
        return clone.toString();
    }

    public static void a(Context context, a aVar) {
        NetworkInfo activeNetworkInfo;
        if (context == null || (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) == null) {
            aVar.a(SpeechConstant.WAP_PROXY, "none", false);
        } else {
            aVar.a(SpeechConstant.WAP_PROXY, h.a(activeNetworkInfo), false);
            aVar.a("net_subtype", a.f(h.b(activeNetworkInfo)), false);
        }
    }

    private static void a(a aVar) {
        if (aVar == null || Setting.c == Setting.LOG_LEVEL.none) {
            return;
        }
        String str = Setting.d;
        if (TextUtils.isEmpty(str)) {
            str = "/sdcard/msc/msc.log";
        }
        int i = -1;
        if (Setting.c == Setting.LOG_LEVEL.detail) {
            i = 31;
        } else if (Setting.c == Setting.LOG_LEVEL.normal) {
            i = 15;
        } else if (Setting.c == Setting.LOG_LEVEL.low) {
            i = 7;
        }
        e.b(str);
        aVar.a("log", str);
        aVar.a("lvl", "" + i);
        aVar.a("output", "1", false);
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.contains("sms") || str.contains("iat");
    }

    public static String b(Context context, com.iflytek.cloud.a.d.a aVar) {
        a clone = aVar.t().clone();
        a(context, clone);
        clone.a(SpeechConstant.RESULT_TYPE, "json");
        clone.a("rse", aVar.p());
        clone.a(SpeechConstant.TEXT_ENCODING, aVar.o());
        clone.a("ssm", "1", false);
        clone.a(SpeechConstant.SUBJECT, SpeechConstant.ENG_IVP, false);
        int q = aVar.q();
        clone.a("auf=audio/L16;rate", Integer.toString(q), false);
        clone.a("aue", q == 16000 ? "speex-wb;10" : "speex", false);
        clone.a(SpeechConstant.VAD_BOS, "3000", false);
        clone.a(SpeechConstant.VAD_EOS, "700", false);
        clone.a(b.c);
        return clone.toString();
    }

    public static String b(Context context, String str, com.iflytek.cloud.a.d.a aVar) {
        if (str.equals("oneshot")) {
            return a(context, aVar.t().e(SpeechConstant.CLOUD_GRAMMAR), aVar);
        }
        a clone = aVar.t().clone();
        clone.a(b.c);
        return clone.toString();
    }

    private static void b(a aVar) {
        String str;
        if (aVar.g("speed_increase")) {
            return;
        }
        int a2 = aVar.a("speed", 50);
        if (a2 <= 100) {
            aVar.a("speed", "" + a2);
            str = "1";
        } else if (100 < a2 && a2 <= 150) {
            aVar.a("speed", "" + (a2 - 50));
            str = "2";
        } else {
            if (150 >= a2 || a2 > 200) {
                return;
            }
            aVar.a("speed", "" + (a2 - 100));
            str = aj.a;
        }
        aVar.a("speed_increase", str);
    }

    public static String c(Context context, com.iflytek.cloud.a.d.a aVar) {
        a clone = aVar.t().clone();
        a(context, clone);
        clone.a("ssm", "1", false);
        clone.a(SpeechConstant.RESULT_TYPE, "json", false);
        clone.a("rse", aVar.p(), false);
        clone.a(SpeechConstant.TEXT_ENCODING, aVar.o(), false);
        clone.a(b.c);
        return clone.toString();
    }

    public static String d(Context context, com.iflytek.cloud.a.d.a aVar) {
        a clone = aVar.t().clone();
        a(context, clone);
        clone.a("ssm", "1", false);
        int q = aVar.q();
        clone.a("auf=audio/L16;rate", Integer.toString(q));
        clone.a("aue", q == 16000 ? "speex-wb" : "speex", false);
        clone.a(SpeechConstant.VOICE_NAME, clone.b(SpeechConstant.VOICE_NAME, a), true);
        clone.a(SpeechConstant.TEXT_ENCODING, aVar.o(), false);
        b(clone);
        clone.a(b.c);
        return clone.toString();
    }
}
