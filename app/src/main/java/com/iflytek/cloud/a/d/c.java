package com.iflytek.cloud.a.d;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.iflytek.speech.UtilityConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class c {
    JSONObject a = new JSONObject();
    long b = 0;
    long c = 0;

    public synchronized String a() {
        return this.a.toString();
    }

    public void a(com.iflytek.cloud.b.a aVar) {
        this.c = System.currentTimeMillis();
        this.b = SystemClock.elapsedRealtime();
        a("app_start", com.iflytek.cloud.a.g.c.a(this.c), false);
        String e = aVar.e(UtilityConfig.KEY_CALLER_APPID);
        if (!TextUtils.isEmpty(e)) {
            a("app_caller_appid", e, false);
        }
        String e2 = com.iflytek.cloud.a.g.a.a((Context) null).e("app.ver.code");
        if (TextUtils.isEmpty(e2)) {
            return;
        }
        a("app_cver", e2, false);
    }

    public synchronized void a(String str) {
        a(str, SystemClock.elapsedRealtime() - this.b, false);
    }

    public synchronized void a(String str, long j, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            if (z) {
                JSONArray optJSONArray = this.a.optJSONArray(str);
                if (optJSONArray == null) {
                    optJSONArray = new JSONArray();
                    this.a.put(str, optJSONArray);
                }
                if (optJSONArray != null) {
                    optJSONArray.put(j);
                }
            } else {
                this.a.put(str, j);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public synchronized void a(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        try {
            if (z) {
                JSONArray optJSONArray = this.a.optJSONArray(str);
                if (optJSONArray == null) {
                    optJSONArray = new JSONArray();
                    this.a.put(str, optJSONArray);
                }
                if (optJSONArray != null) {
                    optJSONArray.put(str2);
                }
            } else {
                this.a.put(str, str2);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
