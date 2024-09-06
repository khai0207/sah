package com.iflytek.common.a;

import android.content.Context;
import com.iflytek.cloud.SpeechConstant;
import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* loaded from: classes.dex */
public class d {
    private static d a;
    private com.iflytek.common.c.d b;
    private Context d;
    private e e;
    private com.iflytek.common.c.a f;
    private a c = null;
    private b g = new g(this);

    private d(Context context) {
        this.b = new com.iflytek.common.c.d(context);
        this.d = context;
        this.f = new com.iflytek.common.c.a(context);
        this.e = new e(this.b);
        com.iflytek.common.c.c.a(this.d, "load settings: pkgRun=" + this.e.g() + " periodRun=" + this.e.a() + " periodUpdate=" + this.e.b());
    }

    public static synchronized d a(Context context) {
        d dVar;
        synchronized (d.class) {
            if (a == null) {
                a = new d(context);
            }
            dVar = a;
        }
        return dVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, int i) {
        if (i != 0 || str == null) {
            com.iflytek.common.c.c.a(this.d, "get config error:" + i);
        } else {
            this.e.c(System.currentTimeMillis());
            com.iflytek.common.c.c.a(this.d, "get config success");
            com.iflytek.common.c.c.a(this.d, str);
            b(str);
            c.a(this.d);
        }
        this.c = null;
    }

    private void b(String str) {
        if (str == null || str.length() <= 0) {
            com.iflytek.common.c.c.a("LaunchSettings", "loadJson empty.");
            return;
        }
        JSONTokener jSONTokener = new JSONTokener(str);
        try {
            String str2 = "";
            JSONObject jSONObject = new JSONObject(jSONTokener);
            long j = jSONObject.getLong("updateperiod");
            JSONObject jSONObject2 = jSONObject.getJSONObject("launch");
            JSONArray jSONArray = jSONObject2.getJSONArray("runpackage");
            String[] strArr = new String[jSONArray.length()];
            for (int i = 0; i < jSONArray.length(); i++) {
                strArr[i] = jSONArray.getString(i);
                str2 = str2 + strArr[i] + ";";
            }
            long j2 = jSONObject2.getLong("runperiod");
            this.e.a(strArr);
            this.e.a(j);
            this.e.b(j2);
            com.iflytek.common.c.c.a(this.d, "parse json updateperiod:" + j + " runperiod:" + j2 + " runpackage:" + str2);
        } catch (JSONException e) {
            String message = e.getMessage();
            com.iflytek.common.c.c.a(this.d, "parse json error:" + message);
        }
    }

    private String d() {
        return "?t=" + f() + "&p=" + e();
    }

    private String e() {
        String a2 = this.f.a();
        String b = this.f.b();
        String f = this.e.f();
        StringBuilder sb = new StringBuilder();
        sb.append("imei=");
        if (a2 == null || a2.length() == 0) {
            a2 = Constants.NULL_VERSION_ID;
        }
        sb.append(a2);
        sb.append(",ua=");
        sb.append(b);
        sb.append(",appid=");
        if (f == null || f.length() == 0) {
            f = Constants.NULL_VERSION_ID;
        }
        sb.append(f);
        sb.append(",sdkver=2.0");
        sb.append(",pkg=" + this.d.getPackageName());
        String sb2 = sb.toString();
        char[] charArray = com.iflytek.common.c.b.a(sb2.getBytes()).toCharArray();
        for (int i = 0; i < 8; i++) {
            char c = charArray[i];
            charArray[i] = charArray[(charArray.length - 8) + i];
            charArray[(charArray.length - 8) + i] = c;
        }
        StringBuilder sb3 = new StringBuilder();
        for (char c2 : charArray) {
            sb3.append(c2);
        }
        com.iflytek.common.c.c.a(this.d, sb2);
        return sb3.toString();
    }

    private String f() {
        return new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
    }

    public void a() {
        if (this.e.d() > System.currentTimeMillis()) {
            this.e.c(System.currentTimeMillis() - this.e.b());
        }
        long currentTimeMillis = System.currentTimeMillis() - this.e.d();
        long b = this.e.b();
        if (currentTimeMillis <= b) {
            com.iflytek.common.c.c.a(this.d, "check update interval=" + currentTimeMillis + " period=" + b + " ret=false");
            return;
        }
        if (this.c != null) {
            com.iflytek.common.c.c.a("LaunchSettings", "mGetThread running.");
            return;
        }
        String str = "http://hxqd.openspeech.cn/launchconfig" + d();
        a aVar = new a(str, this.g);
        this.c = aVar;
        aVar.start();
        com.iflytek.common.c.c.a(this.d, "check update start get config ");
        com.iflytek.common.c.c.a(this.d, str);
    }

    public void a(long j) {
        this.e.d(j);
    }

    public void a(String str, String str2) {
        if (SpeechConstant.APPID.equals(str)) {
            this.e.a(str2);
            return;
        }
        com.iflytek.common.c.c.a("LaunchSettings", "unkown key =" + str);
    }

    public boolean a(String str) {
        if (this.e.c() != null && str != null) {
            for (String str2 : this.e.c()) {
                if (str.equals(str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean b() {
        if (this.e.e() > System.currentTimeMillis()) {
            this.e.d(System.currentTimeMillis() - this.e.a());
        }
        boolean z = false;
        long currentTimeMillis = System.currentTimeMillis() - this.e.e();
        if (this.e.a() > 0 && currentTimeMillis > this.e.a()) {
            z = true;
        }
        com.iflytek.common.c.c.a(this.d, "check launch interval=" + currentTimeMillis + " period=" + this.e.a() + " ret=" + z);
        return z;
    }

    public long c() {
        return this.e.a();
    }
}
