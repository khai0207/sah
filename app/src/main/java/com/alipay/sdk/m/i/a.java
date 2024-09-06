package com.alipay.sdk.m.i;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.m.q.d;
import com.alipay.sdk.m.q.i;
import com.alipay.sdk.m.q.m;
import com.android.pc.util.ThreeMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class a {
    public static final String A = "https://h5.m.taobao.com/mlapp/olist.html";
    public static final int B = 10;
    public static final boolean C = true;
    public static final boolean D = true;
    public static final boolean E = false;
    public static final boolean F = true;
    public static final boolean G = true;
    public static final String H = "";
    public static final boolean I = false;
    public static final boolean J = false;
    public static final boolean K = false;
    public static final boolean L = false;
    public static final boolean M = true;
    public static final String N = "";
    public static final boolean O = false;
    public static final boolean P = false;
    public static final int Q = 1000;
    public static final String R = "";
    public static final int S = 1000;
    public static final int T = 20000;
    public static final String U = "alipay_cashier_dynamic_config";
    public static final String V = "timeout";
    public static final String W = "h5_port_degrade";
    public static final String X = "st_sdk_config";
    public static final String Y = "tbreturl";
    public static final String Z = "launchAppSwitch";
    public static final String a0 = "configQueryInterval";
    public static final String b0 = "deg_log_mcgw";
    public static final String c0 = "deg_start_srv_first";
    public static final String d0 = "prev_jump_dual";
    public static final String e0 = "use_sc_only";
    public static final String f0 = "retry_aidl_activity_not_start";
    public static final String g0 = "bind_use_imp";
    public static final String h0 = "retry_bnd_once";
    public static final String i0 = "skip_trans";
    public static final String j0 = "start_trans";
    public static final String k0 = "up_before_pay";
    public static final String l0 = "lck_k";
    public static final String m0 = "use_sc_lck_a";
    public static final String n0 = "utdid_factor";
    public static final String o0 = "cfg_max_time";
    public static final String p0 = "scheme_pay_2";
    public static final String q0 = "intercept_batch";
    public static final String r0 = "bind_with_startActivity";
    public static a s0 = null;
    public static final String y = "DynCon";
    public static final int z = 10000;
    public JSONObject v;
    public int a = 10000;
    public boolean b = false;
    public String c = A;
    public int d = 10;
    public boolean e = true;
    public boolean f = true;
    public boolean g = false;
    public boolean h = false;
    public boolean i = true;
    public boolean j = true;
    public String k = "";
    public boolean l = false;
    public boolean m = false;
    public boolean n = false;
    public boolean o = false;
    public boolean p = true;
    public String q = "";
    public String r = "";
    public boolean s = false;
    public boolean t = false;

    /* renamed from: u, reason: collision with root package name */
    public int f15u = 1000;
    public List<b> w = null;
    public int x = -1;

    /* renamed from: com.alipay.sdk.m.i.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class RunnableC0007a implements Runnable {
        public final /* synthetic */ com.alipay.sdk.m.o.a a;
        public final /* synthetic */ Context b;
        public final /* synthetic */ boolean c;
        public final /* synthetic */ int d;

        public RunnableC0007a(com.alipay.sdk.m.o.a aVar, Context context, boolean z, int i) {
            this.a = aVar;
            this.b = context;
            this.c = z;
            this.d = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                com.alipay.sdk.m.l.b a = new com.alipay.sdk.m.m.b().a(this.a, this.b);
                if (a != null) {
                    a.this.a(this.a, a.a());
                    a.this.a(com.alipay.sdk.m.o.a.f());
                    com.alipay.sdk.m.g.a.a(this.a, com.alipay.sdk.m.g.b.l, "offcfg|" + this.c + "|" + this.d);
                }
            } catch (Throwable th) {
                d.a(th);
            }
        }
    }

    private int w() {
        return this.f15u;
    }

    public static a x() {
        if (s0 == null) {
            a aVar = new a();
            s0 = aVar;
            aVar.s();
        }
        return s0;
    }

    private JSONObject y() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("timeout", k());
        jSONObject.put(W, t());
        jSONObject.put(Y, q());
        jSONObject.put(a0, d());
        jSONObject.put(Z, b.a(l()));
        jSONObject.put(p0, i());
        jSONObject.put(q0, h());
        jSONObject.put(b0, e());
        jSONObject.put(c0, f());
        jSONObject.put(d0, m());
        jSONObject.put(e0, g());
        jSONObject.put(g0, b());
        jSONObject.put(h0, n());
        jSONObject.put(i0, p());
        jSONObject.put(j0, v());
        jSONObject.put(k0, r());
        jSONObject.put(m0, o());
        jSONObject.put(l0, j());
        jSONObject.put(r0, c());
        jSONObject.put(f0, u());
        jSONObject.put(o0, w());
        jSONObject.put(com.alipay.sdk.m.q.a.b, a());
        return jSONObject;
    }

    public boolean b() {
        return this.l;
    }

    public String c() {
        return this.r;
    }

    public int d() {
        return this.d;
    }

    public boolean e() {
        return this.h;
    }

    public boolean f() {
        return this.i;
    }

    public String g() {
        return this.k;
    }

    public boolean h() {
        return this.f;
    }

    public boolean i() {
        return this.e;
    }

    public String j() {
        return this.q;
    }

    public int k() {
        int i = this.a;
        if (i >= 1000 && i <= 20000) {
            d.b(y, "time = " + this.a);
            return this.a;
        }
        d.b(y, "time(def) = 10000");
        return 10000;
    }

    public List<b> l() {
        return this.w;
    }

    public boolean m() {
        return this.j;
    }

    public boolean n() {
        return this.m;
    }

    public boolean o() {
        return this.s;
    }

    public boolean p() {
        return this.n;
    }

    public String q() {
        return this.c;
    }

    public boolean r() {
        return this.p;
    }

    public void s() {
        Context b2 = com.alipay.sdk.m.o.b.d().b();
        String a = i.a(com.alipay.sdk.m.o.a.f(), b2, U, null);
        try {
            this.x = Integer.parseInt(i.a(com.alipay.sdk.m.o.a.f(), b2, n0, "-1"));
        } catch (Exception unused) {
        }
        a(a);
    }

    public boolean t() {
        return this.b;
    }

    public boolean u() {
        return this.t;
    }

    public boolean v() {
        return this.o;
    }

    /* loaded from: classes.dex */
    public static final class b {
        public final String a;
        public final int b;
        public final String c;

        public b(String str, int i, String str2) {
            this.a = str;
            this.b = i;
            this.c = str2;
        }

        public static b a(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            return new b(jSONObject.optString("pn"), jSONObject.optInt(ThreeMap.value, 0), jSONObject.optString("pk"));
        }

        public String toString() {
            return String.valueOf(a(this));
        }

        public static List<b> a(JSONArray jSONArray) {
            if (jSONArray == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                b a = a(jSONArray.optJSONObject(i));
                if (a != null) {
                    arrayList.add(a);
                }
            }
            return arrayList;
        }

        public static JSONObject a(b bVar) {
            if (bVar == null) {
                return null;
            }
            try {
                return new JSONObject().put("pn", bVar.a).put(ThreeMap.value, bVar.b).put("pk", bVar.c);
            } catch (JSONException e) {
                d.a(e);
                return null;
            }
        }

        public static JSONArray a(List<b> list) {
            if (list == null) {
                return null;
            }
            JSONArray jSONArray = new JSONArray();
            Iterator<b> it = list.iterator();
            while (it.hasNext()) {
                jSONArray.put(a(it.next()));
            }
            return jSONArray;
        }
    }

    public JSONObject a() {
        return this.v;
    }

    public void a(boolean z2) {
        this.g = z2;
    }

    private void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            a(new JSONObject(str));
        } catch (Throwable th) {
            d.a(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.alipay.sdk.m.o.a aVar) {
        try {
            JSONObject y2 = y();
            i.b(aVar, com.alipay.sdk.m.o.b.d().b(), U, y2.toString());
        } catch (Exception e) {
            d.a(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.alipay.sdk.m.o.a aVar, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject optJSONObject = jSONObject.optJSONObject(X);
            com.alipay.sdk.m.q.a.a(aVar, optJSONObject, com.alipay.sdk.m.q.a.a(aVar, jSONObject));
            if (optJSONObject != null) {
                a(optJSONObject);
            } else {
                d.e(y, "empty config");
            }
        } catch (Throwable th) {
            d.a(th);
        }
    }

    private void a(JSONObject jSONObject) {
        this.a = jSONObject.optInt("timeout", 10000);
        this.b = jSONObject.optBoolean(W, false);
        this.c = jSONObject.optString(Y, A).trim();
        this.d = jSONObject.optInt(a0, 10);
        this.w = b.a(jSONObject.optJSONArray(Z));
        this.e = jSONObject.optBoolean(p0, true);
        this.f = jSONObject.optBoolean(q0, true);
        this.h = jSONObject.optBoolean(b0, false);
        this.i = jSONObject.optBoolean(c0, true);
        this.j = jSONObject.optBoolean(d0, true);
        this.k = jSONObject.optString(e0, "");
        this.l = jSONObject.optBoolean(g0, false);
        this.m = jSONObject.optBoolean(h0, false);
        this.n = jSONObject.optBoolean(i0, false);
        this.o = jSONObject.optBoolean(j0, false);
        this.p = jSONObject.optBoolean(k0, true);
        this.q = jSONObject.optString(l0, "");
        this.s = jSONObject.optBoolean(m0, false);
        this.t = jSONObject.optBoolean(f0, false);
        this.r = jSONObject.optString(r0, "");
        this.f15u = jSONObject.optInt(o0, 1000);
        this.v = jSONObject.optJSONObject(com.alipay.sdk.m.q.a.b);
    }

    public void a(com.alipay.sdk.m.o.a aVar, Context context, boolean z2, int i) {
        com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, "oncfg|" + z2 + "|" + i);
        RunnableC0007a runnableC0007a = new RunnableC0007a(aVar, context, z2, i);
        if (z2) {
            int w = w();
            if (m.a(w, runnableC0007a, "AlipayDCPBlok")) {
                return;
            }
            com.alipay.sdk.m.g.a.b(aVar, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.h0, "" + w);
            return;
        }
        Thread thread = new Thread(runnableC0007a);
        thread.setName("AlipayDCP");
        thread.start();
    }

    public boolean a(Context context, int i) {
        if (this.x == -1) {
            this.x = m.a();
            i.b(com.alipay.sdk.m.o.a.f(), context, n0, String.valueOf(this.x));
        }
        return this.x < i;
    }
}
