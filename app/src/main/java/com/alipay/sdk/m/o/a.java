package com.alipay.sdk.m.o;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.alipay.sdk.m.q.d;
import com.alipay.sdk.m.q.m;
import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Locale;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class a {
    public static final String k = "\"&";
    public static final String l = "&";
    public static final String m = "bizcontext=\"";
    public static final String n = "bizcontext=";
    public static final String o = "\"";
    public static final String p = "appkey";
    public static final String q = "ty";
    public static final String r = "sv";
    public static final String s = "an";
    public static final String t = "setting";

    /* renamed from: u */
    public static final String f16u = "av";
    public static final String v = "sdk_start_time";
    public static final String w = "extInfo";
    public static final String x = "ap_link_token";
    public static final String y = "act_info";
    public static final String z = "UTF-8";
    public String a;
    public String b;
    public Context c;
    public final String d;
    public final long e;
    public final int f;
    public final String g;
    public boolean h = false;
    public final ActivityInfo i;
    public final com.alipay.sdk.m.g.b j;

    public a(Context context, String str, String str2) {
        String str3;
        this.a = "";
        this.b = "";
        this.c = null;
        boolean isEmpty = TextUtils.isEmpty(str2);
        this.j = new com.alipay.sdk.m.g.b(context, isEmpty);
        this.d = c(str, this.b);
        this.e = SystemClock.elapsedRealtime();
        this.f = m.g();
        this.i = m.a(context);
        this.g = str2;
        if (!isEmpty) {
            com.alipay.sdk.m.g.a.a(this, com.alipay.sdk.m.g.b.l, "eptyp", str2 + "|" + this.d);
            if (this.i != null) {
                str3 = this.i.name + "|" + this.i.launchMode;
            } else {
                str3 = Constants.NULL_VERSION_ID;
            }
            com.alipay.sdk.m.g.a.a(this, com.alipay.sdk.m.g.b.l, "actInfo", str3);
            com.alipay.sdk.m.g.a.a(this, com.alipay.sdk.m.g.b.l, NotificationCompat.CATEGORY_SYSTEM, m.a(this));
            com.alipay.sdk.m.g.a.a(this, com.alipay.sdk.m.g.b.l, "sdkv", "bfb4c89-clean");
        }
        try {
            this.c = context.getApplicationContext();
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            this.a = packageInfo.versionName;
            this.b = packageInfo.packageName;
        } catch (Exception e) {
            d.a(e);
        }
        if (!isEmpty) {
            com.alipay.sdk.m.g.a.a(this, com.alipay.sdk.m.g.b.l, "u" + m.g());
            com.alipay.sdk.m.g.a.a(this, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.O, "" + SystemClock.elapsedRealtime());
            com.alipay.sdk.m.g.a.a(context, this, str, this.d);
        }
        if (isEmpty || !com.alipay.sdk.m.i.a.x().r()) {
            return;
        }
        com.alipay.sdk.m.i.a.x().a(this, this.c, true, 2);
    }

    private boolean d(String str) {
        return !str.contains(k);
    }

    private JSONObject e() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(x, this.d);
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    public static a f() {
        return null;
    }

    public Context a() {
        return this.c;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.a;
    }

    private String b(String str) {
        try {
            String a = a(str, l, n);
            if (TextUtils.isEmpty(a)) {
                str = str + l + b(n, "");
            } else {
                int indexOf = str.indexOf(a);
                str = str.substring(0, indexOf) + a(a, n, "", true) + str.substring(indexOf + a.length());
            }
        } catch (Throwable unused) {
        }
        return str;
    }

    private String c(String str) {
        try {
            String a = a(str, k, m);
            if (TextUtils.isEmpty(a)) {
                return str + l + b(m, "\"");
            }
            if (!a.endsWith("\"")) {
                a = a + "\"";
            }
            int indexOf = str.indexOf(a);
            return str.substring(0, indexOf) + a(a, m, "\"", false) + str.substring(indexOf + a.length());
        } catch (Throwable unused) {
            return str;
        }
    }

    public String a(String str) {
        if (TextUtils.isEmpty(str) || str.startsWith("new_external_info==")) {
            return str;
        }
        if (d(str)) {
            return b(str);
        }
        return c(str);
    }

    public boolean d() {
        return this.h;
    }

    /* renamed from: com.alipay.sdk.m.o.a$a */
    /* loaded from: classes.dex */
    public static final class C0010a {
        public static final HashMap<UUID, a> a = new HashMap<>();
        public static final HashMap<String, a> b = new HashMap<>();
        public static final String c = "i_uuid_b_c";

        public static void a(a aVar, Intent intent) {
            if (aVar == null || intent == null) {
                return;
            }
            UUID randomUUID = UUID.randomUUID();
            a.put(randomUUID, aVar);
            intent.putExtra(c, randomUUID);
        }

        public static a a(Intent intent) {
            if (intent == null) {
                return null;
            }
            Serializable serializableExtra = intent.getSerializableExtra(c);
            if (serializableExtra instanceof UUID) {
                return a.remove((UUID) serializableExtra);
            }
            return null;
        }

        public static void a(a aVar, String str) {
            if (aVar == null || TextUtils.isEmpty(str)) {
                return;
            }
            b.put(str, aVar);
        }

        public static a a(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return b.remove(str);
        }
    }

    private String b(String str, String str2) throws JSONException, UnsupportedEncodingException {
        return str + a("", "") + str2;
    }

    private String a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(str2);
        for (int i = 0; i < split.length; i++) {
            if (!TextUtils.isEmpty(split[i]) && split[i].startsWith(str3)) {
                return split[i];
            }
        }
        return null;
    }

    public static String c(String str, String str2) {
        try {
            Locale locale = Locale.getDefault();
            Object[] objArr = new Object[4];
            if (str == null) {
                str = "";
            }
            objArr[0] = str;
            if (str2 == null) {
                str2 = "";
            }
            objArr[1] = str2;
            objArr[2] = Long.valueOf(System.currentTimeMillis());
            objArr[3] = UUID.randomUUID().toString();
            return String.format("EP%s%s_%s", "1", m.g(String.format(locale, "%s%s%d%s", objArr)), Long.valueOf(System.currentTimeMillis()));
        } catch (Throwable unused) {
            return "-";
        }
    }

    public String a(String str, String str2) {
        String str3;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(p, com.alipay.sdk.m.h.a.f);
            jSONObject.put(q, "and_lite");
            jSONObject.put(r, "h.a.3.8.06");
            if (!this.b.contains(t) || !m.h(this.c)) {
                jSONObject.put(s, this.b);
            }
            jSONObject.put(f16u, this.a);
            jSONObject.put(v, System.currentTimeMillis());
            jSONObject.put(w, e());
            if (this.i != null) {
                str3 = this.i.name + "|" + this.i.launchMode;
            } else {
                str3 = Constants.NULL_VERSION_ID;
            }
            jSONObject.put(y, str3);
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put(str, str2);
            }
            return jSONObject.toString();
        } catch (Throwable th) {
            d.a(th);
            return "";
        }
    }

    private String a(String str, String str2, String str3, boolean z2) throws JSONException, UnsupportedEncodingException {
        JSONObject jSONObject;
        String substring = str.substring(str2.length());
        boolean z3 = false;
        String substring2 = substring.substring(0, substring.length() - str3.length());
        if (substring2.length() >= 2 && substring2.startsWith("\"") && substring2.endsWith("\"")) {
            jSONObject = new JSONObject(substring2.substring(1, substring2.length() - 1));
            z3 = true;
        } else {
            jSONObject = new JSONObject(substring2);
        }
        if (!jSONObject.has(p)) {
            jSONObject.put(p, com.alipay.sdk.m.h.a.f);
        }
        if (!jSONObject.has(q)) {
            jSONObject.put(q, "and_lite");
        }
        if (!jSONObject.has(r)) {
            jSONObject.put(r, "h.a.3.8.06");
        }
        if (!jSONObject.has(s) && (!this.b.contains(t) || !m.h(this.c))) {
            jSONObject.put(s, this.b);
        }
        if (!jSONObject.has(f16u)) {
            jSONObject.put(f16u, this.a);
        }
        if (!jSONObject.has(v)) {
            jSONObject.put(v, System.currentTimeMillis());
        }
        if (!jSONObject.has(w)) {
            jSONObject.put(w, e());
        }
        String jSONObject2 = jSONObject.toString();
        if (z3) {
            jSONObject2 = "\"" + jSONObject2 + "\"";
        }
        return str2 + jSONObject2 + str3;
    }

    public static HashMap<String, String> a(a aVar) {
        HashMap<String, String> hashMap = new HashMap<>();
        if (aVar != null) {
            hashMap.put("sdk_ver", "15.8.06");
            hashMap.put("app_name", aVar.b);
            hashMap.put("token", aVar.d);
            hashMap.put("call_type", aVar.g);
            hashMap.put("ts_api_invoke", String.valueOf(aVar.e));
            com.alipay.sdk.m.q.a.a(aVar, hashMap);
        }
        return hashMap;
    }

    public void a(boolean z2) {
        this.h = z2;
    }
}
