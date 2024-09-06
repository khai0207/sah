package com.talkingdata.sdk;

import androidx.core.app.NotificationCompat;
import com.iflytek.cloud.SpeechConstant;
import com.talkingdata.sdk.cm;
import com.talkingdata.sdk.zz;
import com.unionpay.sdk.OttoBus;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: td */
/* loaded from: classes.dex */
public class by {
    private static volatile by a;
    private static String b;
    private static String c;
    private static String d;
    private static String e;
    private static String f;
    private static String g;
    private static String h;
    private static String j;
    private static JSONObject p;
    private String i;
    private a k = a.UNKNOWN;
    private String l;
    private int m;
    private String n;
    private JSONObject o;

    /* compiled from: td */
    /* loaded from: classes.dex */
    public enum a {
        MALE,
        FEMALE,
        UNKNOWN
    }

    public final void onTDEBEventAccount(zz.a aVar) {
        if (aVar != null) {
            try {
                if (aVar.a != null && Integer.parseInt(String.valueOf(aVar.a.get("apiType"))) == 9) {
                    aVar.a.get("account");
                    com.talkingdata.sdk.a aVar2 = (com.talkingdata.sdk.a) aVar.a.get(NotificationCompat.CATEGORY_SERVICE);
                    Object obj = aVar.a.get("data");
                    Object obj2 = aVar.a.get(SpeechConstant.DOMAIN);
                    Object obj3 = aVar.a.get("action");
                    Object obj4 = aVar.a.get("immediate");
                    if (obj3 != null) {
                        if (!obj3.equals("login") && !obj3.equals("register") && !obj3.equals("logout")) {
                            if (obj3.equals("roleCreate")) {
                                a(String.valueOf(aVar.a.get("parameter")), aVar2);
                            } else {
                                a(obj2, obj3, obj, aVar2);
                                if (a(obj4)) {
                                    a(aVar2);
                                }
                            }
                        }
                        a(String.valueOf(aVar.a.get("accountId")), obj3, obj2, aVar2);
                    }
                }
            } catch (Throwable th) {
                ce.postSDKError(th);
            }
        }
    }

    private boolean a(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            return ((Boolean) obj).booleanValue();
        } catch (Throwable unused) {
            return false;
        }
    }

    private void a(com.talkingdata.sdk.a aVar) {
        try {
            cm cmVar = new cm();
            cmVar.b = cm.a.IMMEDIATELY;
            cmVar.a = aVar;
            bk.a().post(cmVar);
        } catch (Throwable unused) {
        }
    }

    protected static void a(Object obj, Object obj2, Object obj3, com.talkingdata.sdk.a aVar) {
        if (aVar == null || obj == null || obj2 == null || !(obj instanceof String) || !(obj2 instanceof String)) {
            return;
        }
        cn cnVar = new cn();
        cnVar.b = String.valueOf(obj);
        cnVar.c = String.valueOf(obj2);
        if (obj3 != null && (obj3 instanceof Map)) {
            cnVar.d = (Map) obj3;
        }
        cnVar.a = aVar;
        bk.a().post(cnVar);
    }

    public void a(String str, Object obj, Object obj2, com.talkingdata.sdk.a aVar) {
        try {
            if (a == null || a.i == null) {
                a = new by();
            }
            a.a(str);
            Map d2 = a.d();
            cv.a().setAccount(new JSONObject(d2));
            a(obj2, obj, d2, aVar);
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    private void b() {
        try {
            Map d2 = d();
            zz.a aVar = new zz.a();
            aVar.a.put("apiType", 9);
            aVar.a.put(SpeechConstant.DOMAIN, b);
            aVar.a.put("action", "update");
            aVar.a.put("data", d2);
            zz.c().obtainMessage(102, aVar).sendToTarget();
            cv.a().setAccount(new JSONObject(d2));
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    private void a(String str) {
        try {
            this.i = str;
            String a2 = ap.a(str);
            if (a2 != null) {
                try {
                    JSONObject jSONObject = new JSONObject(a2);
                    if (jSONObject.has(d)) {
                        this.l = jSONObject.getString(d);
                    }
                    if (jSONObject.has(e)) {
                        this.k = a.valueOf(jSONObject.getString(e));
                    }
                    if (jSONObject.has(f)) {
                        this.m = jSONObject.getInt(f);
                    }
                    if (jSONObject.has(g)) {
                        this.n = jSONObject.getString(g);
                    }
                    if (jSONObject.has(h)) {
                        this.o = jSONObject.getJSONObject(h);
                    }
                } catch (Throwable unused) {
                }
            }
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    public void setName(String str) {
        try {
            if (this.l == null || !this.l.equalsIgnoreCase(str)) {
                this.l = str;
                b();
            }
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    public void setGender(a aVar) {
        try {
            if (this.k != aVar) {
                this.k = aVar;
                b();
            }
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    public void setAge(int i) {
        try {
            if (this.m != i) {
                this.m = i;
                b();
            }
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    public void setAccountType(String str) {
        try {
            if (this.n == null || !this.n.equalsIgnoreCase(str)) {
                this.n = str;
                b();
            }
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    public static synchronized void a(String str, com.talkingdata.sdk.a aVar) {
        synchronized (by.class) {
            try {
                ap.setLastRoleName(str);
                p = null;
                j = str;
                String b2 = ap.b(str);
                if (b2 != null) {
                    try {
                        p = new JSONObject(b2);
                        b(aVar);
                    } catch (JSONException e2) {
                        an.dForInternal(e2.getMessage());
                    }
                } else {
                    p = new JSONObject();
                    c();
                    Map e3 = e();
                    a((Object) b, (Object) "roleCreate", (Object) e3, aVar);
                    cv.a().setSubaccount(new JSONObject(e3));
                }
            } catch (Throwable unused) {
            }
        }
    }

    public synchronized void a(String str, String str2) {
        if (p == null) {
            p = new JSONObject();
        }
        try {
            p.put(str, str2);
            c();
            f();
        } catch (Throwable unused) {
        }
    }

    public synchronized void a(String str, int i) {
        if (p == null) {
            p = new JSONObject();
        }
        try {
            p.put(str, i);
            c();
            f();
        } catch (Throwable unused) {
        }
    }

    public synchronized void b(String str, String str2) {
        if (this.o == null) {
            this.o = new JSONObject();
        }
        try {
            this.o.put(str, str2);
            b();
        } catch (Throwable unused) {
        }
    }

    public synchronized void b(String str, int i) {
        if (this.o == null) {
            this.o = new JSONObject();
        }
        try {
            this.o.put(str, i);
            b();
        } catch (Throwable unused) {
        }
    }

    private static void c() {
        ap.setLastRoleName(j);
        ap.b(j, p.toString());
    }

    private Map d() {
        TreeMap treeMap = new TreeMap();
        try {
            treeMap.put(c, this.i);
            treeMap.put(f, Integer.valueOf(this.m));
            treeMap.put(e, this.k.name());
            if (this.l != null) {
                treeMap.put(d, this.l);
            }
            if (this.n != null) {
                treeMap.put(g, this.n);
            }
            if (this.o != null && this.o.length() > 0) {
                treeMap.put("custom", this.o);
            }
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
        return treeMap;
    }

    private static Map e() {
        TreeMap treeMap = new TreeMap();
        try {
            treeMap.put(com.alipay.sdk.m.h.c.e, j);
            if (p != null && p.length() > 0) {
                treeMap.put("custom", p);
            }
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
        return treeMap;
    }

    private static void f() {
        try {
            cv.a().setSubaccount(new JSONObject(e()));
            b(null);
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    private static void b(com.talkingdata.sdk.a aVar) {
        try {
            Map e2 = e();
            cv.a().setSubaccount(new JSONObject(e2));
            a((Object) b, (Object) "roleUpdate", (Object) e2, aVar);
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    public static by a() {
        if (a == null) {
            synchronized (by.class) {
                if (a == null) {
                    a = new by();
                }
            }
        }
        return a;
    }

    private by() {
    }

    static {
        try {
            bk.a().register(a());
        } catch (Throwable unused) {
        }
        b = "account";
        c = "accountId";
        d = com.alipay.sdk.m.h.c.e;
        e = "gender";
        f = "age";
        g = "type";
        h = "accountCus";
        j = OttoBus.DEFAULT_IDENTIFIER;
    }
}
