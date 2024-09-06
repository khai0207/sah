package com.talkingdata.sdk;

import android.os.Build;
import com.iflytek.cloud.SpeechConstant;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONObject;

/* compiled from: td */
/* loaded from: classes.dex */
public class dg extends da {
    public dg() {
        a("os", "android");
        a("osVersionName", au.a());
        a("osVersionCode", String.valueOf(au.g()));
        a("timezone", TimeZone.getDefault().getID());
        a("locale", Locale.getDefault().toString());
        a("timezoneV", String.valueOf(TimeZone.getDefault().getRawOffset() / 3600000.0f));
        a(SpeechConstant.LANGUAGE, au.i());
        a("romVersion", Build.FINGERPRINT);
        if (bh.a(14)) {
            a("basebandVersion", Build.getRadioVersion());
        }
    }

    public String b() {
        return ((JSONObject) a_()).optString("timezoneV");
    }

    public String c() {
        return ((JSONObject) a_()).optString("locale");
    }
}
