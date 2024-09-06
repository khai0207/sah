package com.unionpay.mobile.android.nocard.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechConstant;
import com.unionpay.mobile.android.nocard.views.bh;
import com.unionpay.mobile.android.utils.h;
import com.unionpay.mobile.android.utils.j;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class UPPayEngine implements Handler.Callback, com.unionpay.mobile.android.fully.a, Runnable {
    private Context d;
    private Handler e;
    private com.unionpay.mobile.android.net.d a = null;
    private String b = null;
    private String c = null;
    private WeakReference<a> f = null;
    private com.unionpay.mobile.android.model.b g = null;
    private long h = 0;

    /* loaded from: classes.dex */
    public interface a {
        void a(int i, String str);
    }

    /* loaded from: classes.dex */
    class b {
        public int a;
        public String b;

        public b(int i, String str) {
            this.a = i;
            this.b = str;
        }
    }

    public UPPayEngine(Context context) {
        this.d = null;
        this.e = null;
        this.d = context;
        this.e = new Handler(this);
    }

    private native String commonMessage(long j, String str, String str2, String str3);

    private native String decryptResponse(long j, String str);

    private native String desEncryptMessage(long j, String str, String str2);

    private native String encryptMessage(long j, String str);

    private native String followRulesMessage(long j, String str, String str2);

    private native String getServerUrl(int i, int i2, int i3);

    private native String getTalkingDataId(int i);

    private native String getUserInfo(long j, String str, String str2);

    protected static String h() {
        return new SimpleDateFormat("yyyyMMddhhmmss").format((Date) new java.sql.Date(System.currentTimeMillis()));
    }

    private native String initMessage(long j, String str, String str2);

    private void n(String str) {
        new Thread(this, str).start();
    }

    private native String openupgradeMessage(long j, String str, String str2);

    private native String payingMessage(long j, String str, String str2, String str3, String str4, String str5);

    private native String retrieveInitializeKey(long j);

    private native String rsaPrivateEncryptMessage(long j, String str);

    private native String ruleMessage(long j, String str, String str2);

    private native void setSessionKey(long j, String str);

    private native String unBoundMessage(long j, String str, String str2);

    @Override // com.unionpay.mobile.android.fully.a
    public String a(String str) {
        String str2;
        try {
            JSONObject jSONObject = new JSONObject(str);
            jSONObject.put("reqtm", h());
            str2 = jSONObject.toString();
        } catch (JSONException unused) {
            str2 = str;
        }
        j.c("uppay", "post message = " + str);
        this.a.a(encryptMessage(this.h, str2));
        HashMap<String, String> hashMap = new HashMap<>(1);
        hashMap.put(SpeechConstant.IST_SESSION_ID, this.b);
        this.a.a(hashMap);
        com.unionpay.mobile.android.net.c cVar = new com.unionpay.mobile.android.net.c(this.a, this.d);
        int a2 = cVar.a();
        String c = cVar.c();
        if (a2 != 0) {
            Message obtainMessage = this.e.obtainMessage(2);
            obtainMessage.arg1 = a2;
            this.e.sendMessage(obtainMessage);
            return null;
        }
        String decryptResponse = decryptResponse(this.h, c);
        j.a("uppay", "[ response msg ] " + decryptResponse);
        return decryptResponse;
    }

    public final String a(String str, String str2) {
        return desEncryptMessage(this.h, str, str2);
    }

    public final void a() {
        String serverUrl;
        StringBuilder sb;
        String str;
        if (TextUtils.isEmpty(this.g.be)) {
            int i = 0;
            if (this.g.D.c.equalsIgnoreCase("01")) {
                i = 1;
            } else if (this.g.D.c.equalsIgnoreCase("02")) {
                i = 2;
            } else if (this.g.D.c.equalsIgnoreCase("98")) {
                i = 98;
            } else if (this.g.D.c.equalsIgnoreCase("99")) {
                i = 99;
            } else if ("95".equalsIgnoreCase(this.g.D.c)) {
                i = 95;
            }
            j.a("uppay", "idx  is : " + i + ", isNewTypeTn :" + this.g.c);
            serverUrl = getServerUrl(this.g.c ? 1 : 0, i, this.g.aJ);
        } else {
            if (this.g.c) {
                sb = new StringBuilder();
                sb.append(this.g.be);
                str = "/app/mobile/json";
            } else {
                sb = new StringBuilder();
                sb.append(this.g.be);
                str = "/gateway/mobile/json";
            }
            sb.append(str);
            serverUrl = sb.toString();
        }
        j.a("uppay", "url  is : " + serverUrl);
        this.a = new com.unionpay.mobile.android.net.d(serverUrl);
    }

    public final void a(long j) {
        this.h = j;
    }

    public final void a(com.unionpay.mobile.android.model.b bVar) {
        com.unionpay.mobile.android.model.b bVar2 = this.g;
        if (bVar2 == null || bVar2 != bVar) {
            this.g = bVar;
        }
    }

    public final void a(a aVar) {
        this.f = new WeakReference<>(aVar);
    }

    public final void a(String str, String str2, int i) {
        this.a.a(commonMessage(this.h, str, str2, h()));
        HashMap<String, String> hashMap = new HashMap<>(1);
        hashMap.put(SpeechConstant.IST_SESSION_ID, this.b);
        this.a.a(hashMap);
        if (i <= 0) {
            n(str);
        } else {
            this.e.sendMessageDelayed(this.e.obtainMessage(1, str), i * 1000);
        }
    }

    public final void a(String str, String str2, String str3, String str4) {
        this.a.a(payingMessage(this.h, str, str2, str3, str4, h()));
        HashMap<String, String> hashMap = new HashMap<>(1);
        hashMap.put(SpeechConstant.IST_SESSION_ID, this.b);
        this.a.a(hashMap);
        n("pay");
    }

    public final String b() {
        return this.c;
    }

    public final String b(String str) {
        if (!h.c(str)) {
            str = "00";
        }
        return getTalkingDataId(Integer.decode(str).intValue());
    }

    public final void b(String str, String str2) {
        this.a.a(initMessage(this.h, bh.a(this.d, str, "android", this.g.a(), this.g.d, str2), h()));
        HashMap<String, String> hashMap = new HashMap<>(1);
        hashMap.put("secret", retrieveInitializeKey(this.h));
        this.a.a(hashMap);
        n("init");
    }

    public final long c() {
        return this.h;
    }

    public final void c(String str) {
        this.b = str;
    }

    public final void c(String str, String str2) {
        a(str, str2, 0);
    }

    public final com.unionpay.mobile.android.net.d d() {
        return this.a;
    }

    public final void d(String str) {
        this.c = str;
    }

    public final Handler e() {
        return this.e;
    }

    public final boolean e(String str) {
        setSessionKey(this.h, str);
        return true;
    }

    public final String f() {
        return this.b;
    }

    public final String f(String str) {
        return encryptMessage(this.h, str);
    }

    public final String g(String str) {
        return decryptResponse(this.h, str);
    }

    public final void g() {
        this.d = null;
        this.e.removeCallbacksAndMessages(null);
        this.e = null;
        this.a = null;
        this.g = null;
    }

    public final String h(String str) {
        return rsaPrivateEncryptMessage(this.h, str);
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        WeakReference<a> weakReference;
        String str = null;
        if (message.what == 0) {
            b bVar = (b) message.obj;
            if (bVar.a == 0) {
                str = decryptResponse(this.h, bVar.b);
                j.a("uppay", "resp is:" + str);
            }
            WeakReference<a> weakReference2 = this.f;
            if (weakReference2 != null && weakReference2.get() != null) {
                this.f.get().a(bVar.a, str);
                j.b("uppayEx", "UPPayEngine:" + this.f.toString());
            }
        } else if (message.what == 1) {
            n((String) message.obj);
        } else if (message.what == 2 && (weakReference = this.f) != null && weakReference.get() != null) {
            this.f.get().a(message.arg1, null);
        }
        return true;
    }

    public final void i(String str) {
        this.a.a(ruleMessage(this.h, str, h()));
        HashMap<String, String> hashMap = new HashMap<>(1);
        hashMap.put(SpeechConstant.IST_SESSION_ID, this.b);
        this.a.a(hashMap);
        n("rule");
    }

    public native long initJNIEnv(Activity activity, int i, int i2, boolean z, String str, int i3, String str2);

    public final void j(String str) {
        this.a.a(followRulesMessage(this.h, str, h()));
        HashMap<String, String> hashMap = new HashMap<>(1);
        hashMap.put(SpeechConstant.IST_SESSION_ID, this.b);
        this.a.a(hashMap);
        n("followRule");
    }

    public final void k(String str) {
        this.a.a(openupgradeMessage(this.h, str, h()));
        HashMap<String, String> hashMap = new HashMap<>(1);
        hashMap.put(SpeechConstant.IST_SESSION_ID, this.b);
        this.a.a(hashMap);
        n("openupgrade");
    }

    public final void l(String str) {
        this.a.a(unBoundMessage(this.h, str, h()));
        HashMap<String, String> hashMap = new HashMap<>(1);
        hashMap.put(SpeechConstant.IST_SESSION_ID, this.b);
        this.a.a(hashMap);
        n("unbindcard");
    }

    public final void m(String str) {
        String userInfo = getUserInfo(this.h, str, h());
        j.a("uppay", "actEntrust msg:" + userInfo);
        this.a.a(userInfo);
        HashMap<String, String> hashMap = new HashMap<>(1);
        hashMap.put(SpeechConstant.IST_SESSION_ID, this.b);
        this.a.a(hashMap);
        n("getuserinfo");
    }

    @Override // java.lang.Runnable
    public void run() {
        HashMap<String, String> c;
        String str;
        if (this.g.aJ <= 0 || this.g.aJ > 5) {
            c = this.a.c();
            str = "20131120";
        } else {
            c = this.a.c();
            str = "20150423";
        }
        c.put("magic_number", str);
        com.unionpay.mobile.android.net.c cVar = new com.unionpay.mobile.android.net.c(this.a, this.d);
        b bVar = new b(cVar.a(), cVar.c());
        Handler handler = this.e;
        if (handler != null) {
            Message obtainMessage = handler.obtainMessage();
            obtainMessage.what = 0;
            obtainMessage.obj = bVar;
            this.e.sendMessage(obtainMessage);
        }
    }
}
