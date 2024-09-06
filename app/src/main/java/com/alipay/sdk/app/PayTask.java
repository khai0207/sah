package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import com.alipay.sdk.app.PayResultActivity;
import com.alipay.sdk.m.i.a;
import com.alipay.sdk.m.m.f;
import com.alipay.sdk.m.o.a;
import com.alipay.sdk.m.q.d;
import com.alipay.sdk.m.q.g;
import com.alipay.sdk.m.q.h;
import com.alipay.sdk.m.q.k;
import com.alipay.sdk.m.q.m;
import com.alipay.sdk.util.H5PayResultModel;
import com.iflytek.cloud.SpeechConstant;
import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class PayTask {
    public static final Object h = g.class;
    public static long i = 0;
    public static final long j = 3000;
    public static long k = -1;
    public Activity a;
    public com.alipay.sdk.m.s.a b;
    public final String c = "wappaygw.alipay.com/service/rest.htm";
    public final String d = "mclient.alipay.com/service/rest.htm";
    public final String e = "mclient.alipay.com/home/exterfaceAssign.htm";
    public final String f = "mclient.alipay.com/cashier/mobilepay.htm";
    public Map<String, c> g = new HashMap();

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public final /* synthetic */ String a;
        public final /* synthetic */ boolean b;
        public final /* synthetic */ H5PayCallback c;

        public a(String str, boolean z, H5PayCallback h5PayCallback) {
            this.a = str;
            this.b = z;
            this.c = h5PayCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            H5PayResultModel h5Pay = PayTask.this.h5Pay(new com.alipay.sdk.m.o.a(PayTask.this.a, this.a, "payInterceptorWithUrl"), this.a, this.b);
            d.d(com.alipay.sdk.m.h.a.z, "inc finished: " + h5Pay.getResultCode());
            this.c.onPayResult(h5Pay);
        }
    }

    /* loaded from: classes.dex */
    public class b implements g.e {
        public b() {
        }

        @Override // com.alipay.sdk.m.q.g.e
        public void a() {
            PayTask.this.dismissLoading();
        }

        @Override // com.alipay.sdk.m.q.g.e
        public void b() {
        }
    }

    /* loaded from: classes.dex */
    public class c {
        public String a;
        public String b;
        public String c;
        public String d;

        public c() {
            this.a = "";
            this.b = "";
            this.c = "";
            this.d = "";
        }

        public String a() {
            return this.c;
        }

        public String b() {
            return this.a;
        }

        public String c() {
            return this.b;
        }

        public String d() {
            return this.d;
        }

        public void a(String str) {
            this.c = str;
        }

        public void b(String str) {
            this.a = str;
        }

        public void c(String str) {
            this.b = str;
        }

        public void d(String str) {
            this.d = str;
        }

        public /* synthetic */ c(PayTask payTask, a aVar) {
            this();
        }
    }

    public PayTask(Activity activity) {
        this.a = activity;
        com.alipay.sdk.m.o.b.d().a(this.a);
        this.b = new com.alipay.sdk.m.s.a(activity, com.alipay.sdk.m.s.a.j);
    }

    public static boolean b() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - k < 3000) {
            return true;
        }
        k = elapsedRealtime;
        return false;
    }

    public static synchronized boolean fetchSdkConfig(Context context) {
        synchronized (PayTask.class) {
            try {
                com.alipay.sdk.m.o.b.d().a(context);
                long elapsedRealtime = SystemClock.elapsedRealtime() / 1000;
                if (elapsedRealtime - i < com.alipay.sdk.m.i.a.x().d()) {
                    return false;
                }
                i = elapsedRealtime;
                com.alipay.sdk.m.i.a.x().a(com.alipay.sdk.m.o.a.f(), context.getApplicationContext(), false, 4);
                return true;
            } catch (Exception e) {
                d.a(e);
                return false;
            }
        }
    }

    public void dismissLoading() {
        com.alipay.sdk.m.s.a aVar = this.b;
        if (aVar != null) {
            aVar.a();
            this.b = null;
        }
    }

    public synchronized String fetchOrderInfoFromH5PayUrl(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                String trim = str.trim();
                if (trim.startsWith("https://wappaygw.alipay.com/service/rest.htm") || trim.startsWith("http://wappaygw.alipay.com/service/rest.htm")) {
                    String trim2 = trim.replaceFirst("(http|https)://wappaygw.alipay.com/service/rest.htm\\?", "").trim();
                    if (!TextUtils.isEmpty(trim2)) {
                        return "_input_charset=\"utf-8\"&ordertoken=\"" + m.a("<request_token>", "</request_token>", m.b(trim2).get("req_data")) + "\"&pay_channel_id=\"alipay_sdk\"&bizcontext=\"" + new com.alipay.sdk.m.o.a(this.a, "", "").a("sc", "h5tonative") + "\"";
                    }
                }
                if (trim.startsWith("https://mclient.alipay.com/service/rest.htm") || trim.startsWith("http://mclient.alipay.com/service/rest.htm")) {
                    String trim3 = trim.replaceFirst("(http|https)://mclient.alipay.com/service/rest.htm\\?", "").trim();
                    if (!TextUtils.isEmpty(trim3)) {
                        return "_input_charset=\"utf-8\"&ordertoken=\"" + m.a("<request_token>", "</request_token>", m.b(trim3).get("req_data")) + "\"&pay_channel_id=\"alipay_sdk\"&bizcontext=\"" + new com.alipay.sdk.m.o.a(this.a, "", "").a("sc", "h5tonative") + "\"";
                    }
                }
                if ((trim.startsWith("https://mclient.alipay.com/home/exterfaceAssign.htm") || trim.startsWith("http://mclient.alipay.com/home/exterfaceAssign.htm")) && ((trim.contains("alipay.wap.create.direct.pay.by.user") || trim.contains("create_forex_trade_wap")) && !TextUtils.isEmpty(trim.replaceFirst("(http|https)://mclient.alipay.com/home/exterfaceAssign.htm\\?", "").trim()))) {
                    com.alipay.sdk.m.o.a aVar = new com.alipay.sdk.m.o.a(this.a, "", "");
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(Constants.URL_ENCODING, str);
                    jSONObject.put("bizcontext", aVar.a("sc", "h5tonative"));
                    return "new_external_info==" + jSONObject.toString();
                }
                if (Pattern.compile("^(http|https)://(maliprod\\.alipay\\.com/w/trade_pay\\.do.?|mali\\.alipay\\.com/w/trade_pay\\.do.?|mclient\\.alipay\\.com/w/trade_pay\\.do.?)").matcher(str).find()) {
                    String a2 = m.a("?", "", str);
                    if (!TextUtils.isEmpty(a2)) {
                        Map<String, String> b2 = m.b(a2);
                        StringBuilder sb = new StringBuilder();
                        if (a(false, true, com.alipay.sdk.m.g.b.w0, sb, b2, com.alipay.sdk.m.g.b.w0, "alipay_trade_no")) {
                            a(true, false, "pay_phase_id", sb, b2, "payPhaseId", "pay_phase_id", "out_relation_id");
                            sb.append("&biz_sub_type=\"TRADE\"");
                            sb.append("&biz_type=\"trade\"");
                            String str2 = b2.get("app_name");
                            if (TextUtils.isEmpty(str2) && !TextUtils.isEmpty(b2.get("cid"))) {
                                str2 = "ali1688";
                            } else if (TextUtils.isEmpty(str2) && (!TextUtils.isEmpty(b2.get(SpeechConstant.IST_SESSION_ID)) || !TextUtils.isEmpty(b2.get("s_id")))) {
                                str2 = "tb";
                            }
                            sb.append("&app_name=\"" + str2 + "\"");
                            if (!a(true, true, "extern_token", sb, b2, "extern_token", "cid", SpeechConstant.IST_SESSION_ID, "s_id")) {
                                return "";
                            }
                            a(true, false, "appenv", sb, b2, "appenv");
                            sb.append("&pay_channel_id=\"alipay_sdk\"");
                            c cVar = new c(this, null);
                            cVar.b(b2.get("return_url"));
                            cVar.c(b2.get("show_url"));
                            cVar.a(b2.get("pay_order_id"));
                            String str3 = sb.toString() + "&bizcontext=\"" + new com.alipay.sdk.m.o.a(this.a, "", "").a("sc", "h5tonative") + "\"";
                            this.g.put(str3, cVar);
                            return str3;
                        }
                    }
                }
                if (!trim.startsWith("https://mclient.alipay.com/cashier/mobilepay.htm") && !trim.startsWith("http://mclient.alipay.com/cashier/mobilepay.htm") && (!EnvUtils.isSandBox() || !trim.contains("mobileclientgw.alipaydev.com/cashier/mobilepay.htm"))) {
                    if (com.alipay.sdk.m.i.a.x().h() && Pattern.compile("^https?://(maliprod\\.alipay\\.com|mali\\.alipay\\.com)/batch_payment\\.do\\?").matcher(trim).find()) {
                        Uri parse = Uri.parse(trim);
                        String queryParameter = parse.getQueryParameter("return_url");
                        String queryParameter2 = parse.getQueryParameter("show_url");
                        String queryParameter3 = parse.getQueryParameter("pay_order_id");
                        String a3 = a(parse.getQueryParameter("trade_nos"), parse.getQueryParameter("alipay_trade_no"));
                        String a4 = a(parse.getQueryParameter("payPhaseId"), parse.getQueryParameter("pay_phase_id"), parse.getQueryParameter("out_relation_id"));
                        String[] strArr = new String[4];
                        strArr[0] = parse.getQueryParameter("app_name");
                        strArr[1] = !TextUtils.isEmpty(parse.getQueryParameter("cid")) ? "ali1688" : "";
                        strArr[2] = !TextUtils.isEmpty(parse.getQueryParameter(SpeechConstant.IST_SESSION_ID)) ? "tb" : "";
                        strArr[3] = !TextUtils.isEmpty(parse.getQueryParameter("s_id")) ? "tb" : "";
                        String a5 = a(strArr);
                        String a6 = a(parse.getQueryParameter("extern_token"), parse.getQueryParameter("cid"), parse.getQueryParameter(SpeechConstant.IST_SESSION_ID), parse.getQueryParameter("s_id"));
                        String a7 = a(parse.getQueryParameter("appenv"));
                        if (!TextUtils.isEmpty(a3) && !TextUtils.isEmpty(a5) && !TextUtils.isEmpty(a6)) {
                            String format = String.format("trade_no=\"%s\"&pay_phase_id=\"%s\"&biz_type=\"trade\"&biz_sub_type=\"TRADE\"&app_name=\"%s\"&extern_token=\"%s\"&appenv=\"%s\"&pay_channel_id=\"alipay_sdk\"&bizcontext=\"%s\"", a3, a4, a5, a6, a7, new com.alipay.sdk.m.o.a(this.a, "", "").a("sc", "h5tonative"));
                            c cVar2 = new c(this, null);
                            cVar2.b(queryParameter);
                            cVar2.c(queryParameter2);
                            cVar2.a(queryParameter3);
                            cVar2.d(a3);
                            this.g.put(format, cVar2);
                            return format;
                        }
                    }
                }
                String a8 = new com.alipay.sdk.m.o.a(this.a, "", "").a("sc", "h5tonative");
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(Constants.URL_ENCODING, trim);
                jSONObject2.put("bizcontext", a8);
                return String.format("new_external_info==%s", jSONObject2.toString());
            }
        } catch (Throwable th) {
            d.a(th);
        }
        return "";
    }

    public synchronized String fetchTradeToken() {
        return h.a(new com.alipay.sdk.m.o.a(this.a, "", "fetchTradeToken"), this.a.getApplicationContext());
    }

    public String getVersion() {
        return "15.8.06";
    }

    public synchronized H5PayResultModel h5Pay(com.alipay.sdk.m.o.a aVar, String str, boolean z) {
        H5PayResultModel h5PayResultModel;
        h5PayResultModel = new H5PayResultModel();
        try {
            String[] split = a(aVar, str, z).split(";");
            HashMap hashMap = new HashMap();
            for (String str2 : split) {
                int indexOf = str2.indexOf("={");
                if (indexOf >= 0) {
                    String substring = str2.substring(0, indexOf);
                    hashMap.put(substring, a(str2, substring));
                }
            }
            if (hashMap.containsKey(k.a)) {
                h5PayResultModel.setResultCode(hashMap.get(k.a));
            }
            h5PayResultModel.setReturnUrl(a(str, hashMap));
            if (TextUtils.isEmpty(h5PayResultModel.getReturnUrl())) {
                com.alipay.sdk.m.g.a.b(aVar, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.i0, "");
            }
        } catch (Throwable th) {
            com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.j0, th);
            d.a(th);
        }
        return h5PayResultModel;
    }

    public synchronized String pay(String str, boolean z) {
        return a(new com.alipay.sdk.m.o.a(this.a, str, "pay"), str, z);
    }

    public synchronized boolean payInterceptorWithUrl(String str, boolean z, H5PayCallback h5PayCallback) {
        String fetchOrderInfoFromH5PayUrl;
        fetchOrderInfoFromH5PayUrl = fetchOrderInfoFromH5PayUrl(str);
        if (!TextUtils.isEmpty(fetchOrderInfoFromH5PayUrl)) {
            d.d(com.alipay.sdk.m.h.a.z, "intercepted: " + fetchOrderInfoFromH5PayUrl);
            new Thread(new a(fetchOrderInfoFromH5PayUrl, z, h5PayCallback)).start();
        }
        return !TextUtils.isEmpty(fetchOrderInfoFromH5PayUrl);
    }

    public synchronized Map<String, String> payV2(String str, boolean z) {
        com.alipay.sdk.m.o.a aVar;
        aVar = new com.alipay.sdk.m.o.a(this.a, str, "payV2");
        return k.a(aVar, a(aVar, str, z));
    }

    public void showLoading() {
        com.alipay.sdk.m.s.a aVar = this.b;
        if (aVar != null) {
            aVar.d();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x011c, code lost:
    
        if (com.alipay.sdk.m.i.a.x().r() == false) goto L98;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0181, code lost:
    
        dismissLoading();
        com.alipay.sdk.m.g.a.b(r6.a.getApplicationContext(), r7, r8, r7.d);
        com.alipay.sdk.m.q.d.d(com.alipay.sdk.m.h.a.z, "pay returning: " + r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x01a6, code lost:
    
        return r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0174, code lost:
    
        com.alipay.sdk.m.i.a.x().a(r7, r6.a.getApplicationContext(), false, 3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0172, code lost:
    
        if (com.alipay.sdk.m.i.a.x().r() != false) goto L99;
     */
    /* JADX WARN: Finally extract failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized java.lang.String a(com.alipay.sdk.m.o.a r7, java.lang.String r8, boolean r9) {
        /*
            Method dump skipped, instructions count: 532
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.app.PayTask.a(com.alipay.sdk.m.o.a, java.lang.String, boolean):java.lang.String");
    }

    public static final String a(String... strArr) {
        if (strArr == null) {
            return "";
        }
        for (String str : strArr) {
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
        }
        return "";
    }

    private boolean a(boolean z, boolean z2, String str, StringBuilder sb, Map<String, String> map, String... strArr) {
        String str2;
        int length = strArr.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                str2 = "";
                break;
            }
            String str3 = strArr[i2];
            if (!TextUtils.isEmpty(map.get(str3))) {
                str2 = map.get(str3);
                break;
            }
            i2++;
        }
        if (TextUtils.isEmpty(str2)) {
            return !z2;
        }
        if (z) {
            sb.append(com.alipay.sdk.m.o.a.l);
            sb.append(str);
            sb.append("=\"");
            sb.append(str2);
            sb.append("\"");
            return true;
        }
        sb.append(str);
        sb.append("=\"");
        sb.append(str2);
        sb.append("\"");
        return true;
    }

    private String a(String str, Map<String, String> map) throws UnsupportedEncodingException {
        boolean equals = "9000".equals(map.get(k.a));
        String str2 = map.get("result");
        c remove = this.g.remove(str);
        String[] strArr = new String[2];
        strArr[0] = remove != null ? remove.a() : "";
        strArr[1] = remove != null ? remove.d() : "";
        a(strArr);
        if (map.containsKey("callBackUrl")) {
            return map.get("callBackUrl");
        }
        if (str2.length() > 15) {
            String a2 = a(m.a("&callBackUrl=\"", "\"", str2), m.a("&call_back_url=\"", "\"", str2), m.a(com.alipay.sdk.m.h.a.t, "\"", str2), URLDecoder.decode(m.a(com.alipay.sdk.m.h.a.f14u, com.alipay.sdk.m.o.a.l, str2), "utf-8"), URLDecoder.decode(m.a("&callBackUrl=", com.alipay.sdk.m.o.a.l, str2), "utf-8"), m.a("call_back_url=\"", "\"", str2));
            if (!TextUtils.isEmpty(a2)) {
                return a2;
            }
        }
        if (remove != null) {
            String b2 = equals ? remove.b() : remove.c();
            if (!TextUtils.isEmpty(b2)) {
                return b2;
            }
        }
        return remove != null ? com.alipay.sdk.m.i.a.x().q() : "";
    }

    private String a(String str, String str2) {
        String str3 = str2 + "={";
        return str.substring(str.indexOf(str3) + str3.length(), str.lastIndexOf(h.d));
    }

    private g.e a() {
        return new b();
    }

    private String a(String str, com.alipay.sdk.m.o.a aVar) {
        String a2 = aVar.a(str);
        if (a2.contains("paymethod=\"expressGateway\"")) {
            return a(aVar, a2);
        }
        List<a.b> l = com.alipay.sdk.m.i.a.x().l();
        if (!com.alipay.sdk.m.i.a.x().g || l == null) {
            l = com.alipay.sdk.m.f.a.d;
        }
        if (m.a(aVar, this.a, l, true)) {
            g gVar = new g(this.a, aVar, a());
            d.d(com.alipay.sdk.m.h.a.z, "pay inner started: " + a2);
            String a3 = gVar.a(a2, false);
            if (!TextUtils.isEmpty(a3)) {
                if (a3.contains("resultStatus={" + com.alipay.sdk.m.f.c.ACTIVITY_NOT_START_EXIT.b() + h.d)) {
                    if (com.alipay.sdk.m.i.a.x().u()) {
                        a3 = gVar.a(a2, true);
                    } else {
                        a3 = a3.replace("resultStatus={" + com.alipay.sdk.m.f.c.ACTIVITY_NOT_START_EXIT.b() + h.d, "resultStatus={" + com.alipay.sdk.m.f.c.CANCELED.b() + h.d);
                    }
                }
            }
            d.d(com.alipay.sdk.m.h.a.z, "pay inner raw result: " + a3);
            gVar.a();
            if (!TextUtils.equals(a3, g.j) && !TextUtils.equals(a3, g.k)) {
                if (TextUtils.isEmpty(a3)) {
                    return com.alipay.sdk.m.f.b.a();
                }
                if (!a3.contains(PayResultActivity.b)) {
                    return a3;
                }
                com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.f0);
                return a(aVar, a2, l, a3, this.a);
            }
            com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.d0);
            return a(aVar, a2);
        }
        com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.e0);
        return a(aVar, a2);
    }

    public static String a(com.alipay.sdk.m.o.a aVar, String str, List<a.b> list, String str2, Activity activity) {
        m.c a2 = m.a(aVar, activity, list);
        if (a2 == null || a2.a(aVar) || a2.a() || !TextUtils.equals(a2.a.packageName, PayResultActivity.d)) {
            return str2;
        }
        d.b(com.alipay.sdk.m.h.a.z, "PayTask not_login");
        String valueOf = String.valueOf(str.hashCode());
        PayResultActivity.c.put(valueOf, new Object());
        Intent intent = new Intent(activity, (Class<?>) PayResultActivity.class);
        intent.putExtra(PayResultActivity.f, str);
        intent.putExtra(PayResultActivity.g, activity.getPackageName());
        intent.putExtra(PayResultActivity.e, valueOf);
        a.C0010a.a(aVar, intent);
        activity.startActivity(intent);
        synchronized (PayResultActivity.c.get(valueOf)) {
            try {
                d.b(com.alipay.sdk.m.h.a.z, "PayTask wait");
                PayResultActivity.c.get(valueOf).wait();
            } catch (InterruptedException unused) {
                d.b(com.alipay.sdk.m.h.a.z, "PayTask interrupted");
                return com.alipay.sdk.m.f.b.a();
            }
        }
        String str3 = PayResultActivity.b.b;
        d.b(com.alipay.sdk.m.h.a.z, "PayTask ret: " + str3);
        return str3;
    }

    private String a(com.alipay.sdk.m.o.a aVar, String str) {
        showLoading();
        com.alipay.sdk.m.f.c cVar = null;
        try {
            try {
                try {
                    JSONObject c2 = new f().a(aVar, this.a.getApplicationContext(), str).c();
                    String optString = c2.optString("end_code", null);
                    List<com.alipay.sdk.m.n.b> a2 = com.alipay.sdk.m.n.b.a(c2.optJSONObject(com.alipay.sdk.m.h.c.c).optJSONObject(com.alipay.sdk.m.h.c.d));
                    for (int i2 = 0; i2 < a2.size(); i2++) {
                        if (a2.get(i2).a() == com.alipay.sdk.m.n.a.Update) {
                            com.alipay.sdk.m.n.b.a(a2.get(i2));
                        }
                    }
                    a(aVar, c2);
                    dismissLoading();
                    com.alipay.sdk.m.g.a.a(this.a, aVar, str, aVar.d);
                    for (int i3 = 0; i3 < a2.size(); i3++) {
                        com.alipay.sdk.m.n.b bVar = a2.get(i3);
                        if (bVar.a() == com.alipay.sdk.m.n.a.WapPay) {
                            String a3 = a(aVar, bVar);
                            dismissLoading();
                            com.alipay.sdk.m.g.a.a(this.a, aVar, str, aVar.d);
                            return a3;
                        }
                        if (bVar.a() == com.alipay.sdk.m.n.a.OpenWeb) {
                            String a4 = a(aVar, bVar, optString);
                            dismissLoading();
                            com.alipay.sdk.m.g.a.a(this.a, aVar, str, aVar.d);
                            return a4;
                        }
                    }
                    dismissLoading();
                    com.alipay.sdk.m.g.a.a(this.a, aVar, str, aVar.d);
                } catch (Throwable th) {
                    d.a(th);
                    com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.z, th);
                    dismissLoading();
                    com.alipay.sdk.m.g.a.a(this.a, aVar, str, aVar.d);
                }
            } catch (IOException e) {
                com.alipay.sdk.m.f.c b2 = com.alipay.sdk.m.f.c.b(com.alipay.sdk.m.f.c.NETWORK_ERROR.b());
                com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.k, e);
                dismissLoading();
                com.alipay.sdk.m.g.a.a(this.a, aVar, str, aVar.d);
                cVar = b2;
            }
            if (cVar == null) {
                cVar = com.alipay.sdk.m.f.c.b(com.alipay.sdk.m.f.c.FAILED.b());
            }
            return com.alipay.sdk.m.f.b.a(cVar.b(), cVar.a(), "");
        } catch (Throwable th2) {
            dismissLoading();
            com.alipay.sdk.m.g.a.a(this.a, aVar, str, aVar.d);
            throw th2;
        }
    }

    private void a(com.alipay.sdk.m.o.a aVar, JSONObject jSONObject) {
        try {
            String optString = jSONObject.optString("tid");
            String optString2 = jSONObject.optString(com.alipay.sdk.m.p.a.j);
            if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2)) {
                return;
            }
            com.alipay.sdk.m.p.a.a(com.alipay.sdk.m.o.b.d().b()).a(optString, optString2);
        } catch (Throwable th) {
            com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.N, th);
        }
    }

    private String a(com.alipay.sdk.m.o.a aVar, com.alipay.sdk.m.n.b bVar, String str) {
        boolean c2;
        String d;
        String a2;
        String[] c3 = bVar.c();
        Intent intent = new Intent(this.a, (Class<?>) H5PayActivity.class);
        try {
            JSONObject h2 = m.h(new String(com.alipay.sdk.m.j.a.a(c3[2])));
            intent.putExtra(Constants.URL_ENCODING, c3[0]);
            intent.putExtra(com.alipay.sdk.m.s.d.v, c3[1]);
            intent.putExtra("version", com.alipay.sdk.m.s.c.d);
            intent.putExtra("method", h2.optString("method", "POST"));
            com.alipay.sdk.m.f.b.a(false);
            com.alipay.sdk.m.f.b.a((String) null);
            a.C0010a.a(aVar, intent);
            this.a.startActivity(intent);
            synchronized (h) {
                try {
                    h.wait();
                    c2 = com.alipay.sdk.m.f.b.c();
                    d = com.alipay.sdk.m.f.b.d();
                    com.alipay.sdk.m.f.b.a(false);
                    com.alipay.sdk.m.f.b.a((String) null);
                } catch (InterruptedException e) {
                    d.a(e);
                    return com.alipay.sdk.m.f.b.a();
                }
            }
            if (c2) {
                try {
                    List<com.alipay.sdk.m.n.b> a3 = com.alipay.sdk.m.n.b.a(m.h(new String(com.alipay.sdk.m.j.a.a(d))));
                    for (int i2 = 0; i2 < a3.size(); i2++) {
                        com.alipay.sdk.m.n.b bVar2 = a3.get(i2);
                        if (bVar2.a() == com.alipay.sdk.m.n.a.SetResult) {
                            String[] c4 = bVar2.c();
                            a2 = com.alipay.sdk.m.f.b.a(Integer.valueOf(c4[1]).intValue(), c4[0], m.e(aVar, c4[2]));
                            break;
                        }
                    }
                } catch (Throwable th) {
                    d.a(th);
                    com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.z, th, d);
                }
            }
            a2 = "";
            if (!TextUtils.isEmpty(a2)) {
                return a2;
            }
            try {
                return com.alipay.sdk.m.f.b.a(Integer.valueOf(str).intValue(), "", "");
            } catch (Throwable th2) {
                com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.z, th2, "endCode: " + str);
                return com.alipay.sdk.m.f.b.a(8000, "", "");
            }
        } catch (Throwable th3) {
            d.a(th3);
            com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.z, th3, Arrays.toString(c3));
            return com.alipay.sdk.m.f.b.a();
        }
    }

    private String a(com.alipay.sdk.m.o.a aVar, com.alipay.sdk.m.n.b bVar) {
        String[] c2 = bVar.c();
        Intent intent = new Intent(this.a, (Class<?>) H5PayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.URL_ENCODING, c2[0]);
        if (c2.length == 2) {
            bundle.putString("cookie", c2[1]);
        }
        intent.putExtras(bundle);
        a.C0010a.a(aVar, intent);
        this.a.startActivity(intent);
        synchronized (h) {
            try {
                h.wait();
            } catch (InterruptedException e) {
                d.a(e);
                return com.alipay.sdk.m.f.b.a();
            }
        }
        String d = com.alipay.sdk.m.f.b.d();
        return TextUtils.isEmpty(d) ? com.alipay.sdk.m.f.b.a() : d;
    }
}
