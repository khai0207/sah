package com.unionpay.mobile.android.pro.views;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.pc.util.ThreeMap;
import com.iflytek.cloud.SpeechConstant;
import com.unionpay.mobile.android.nocard.utils.UPPayEngine;
import com.unionpay.mobile.android.upviews.a;
import com.unionpay.mobile.android.widgets.ax;
import com.unionpay.tsmservice.data.Constant;
import com.unionpay.tsmservice.data.ResultCode;
import com.unionpay.uppay.PayActivity;
import java.security.MessageDigest;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class a extends com.unionpay.mobile.android.nocard.views.b implements Handler.Callback, a.b {
    private static Date M = new Date(System.currentTimeMillis());
    private static String N = new SimpleDateFormat("yyyyMMddhhmmss").format((java.util.Date) M);
    private static HashMap<String, String> P = new HashMap<>();
    private static HashMap<String, String> Q = new HashMap<>();
    private ax A;
    private String B;
    private com.unionpay.mobile.android.hce.c C;
    private int D;
    private String E;
    private boolean F;
    private Handler.Callback G;
    private Handler H;
    private View.OnClickListener I;
    private View.OnClickListener J;
    private View.OnClickListener K;
    private View.OnClickListener L;
    private String O;
    public String r;
    UPPayEngine s;
    private int t;

    /* renamed from: u, reason: collision with root package name */
    private int f51u;
    private TextView v;
    private boolean w;
    private com.unionpay.mobile.android.upviews.a x;
    private com.unionpay.mobile.android.upviews.a y;
    private Handler z;

    public a(Context context, com.unionpay.mobile.android.model.e eVar, UPPayEngine uPPayEngine) {
        super(context, eVar);
        this.t = 20;
        this.f51u = 100;
        this.r = "1.9";
        this.v = null;
        this.w = false;
        this.x = null;
        this.y = null;
        this.z = null;
        this.D = 5;
        this.F = false;
        this.G = new b(this);
        this.H = new Handler(this.G);
        this.I = new c(this);
        this.J = new d(this);
        this.K = new e(this);
        this.L = new f(this);
        this.O = null;
        this.f = 6;
        this.q = "hcepay";
        this.s = uPPayEngine;
        this.z = new Handler(this);
        this.C = (com.unionpay.mobile.android.hce.c) com.unionpay.mobile.android.model.b.aW.get(this.a.aX);
        this.w = this.a.F;
        setBackgroundColor(-1052684);
        e();
    }

    private void a(LinearLayout linearLayout) {
        com.unionpay.mobile.android.upviews.a aVar = this.y;
        com.unionpay.mobile.android.widgets.y c = aVar != null ? aVar.c("instalment") : null;
        int length = this.a.f39u.length();
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < length; i++) {
            Object b = com.unionpay.mobile.android.utils.i.b(this.a.f39u, i);
            if (b != null) {
                try {
                    JSONObject jSONObject = (JSONObject) b;
                    if (Constant.KEY_PAN.equals(com.unionpay.mobile.android.utils.i.a(jSONObject, "type"))) {
                        jSONObject.put("label", this.C.b() + this.C.c());
                    }
                    jSONArray.put(jSONObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        this.x = new com.unionpay.mobile.android.upviews.a(this.d, jSONArray, this.e.c(), this, this.C.a(), true, false, c, this.a.Y, this.q);
        linearLayout.addView(this.x, new LinearLayout.LayoutParams(-1, -2));
    }

    static /* synthetic */ void a(a aVar, String str, String str2) {
        aVar.f51u = 8;
        aVar.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        aVar.e.c(str, str2);
    }

    static /* synthetic */ void a(a aVar, String str, HashMap hashMap) {
        Object a = ((PayActivity) aVar.d).a(com.unionpay.mobile.android.pro.pboc.engine.b.class.toString());
        if ((a != null ? (com.unionpay.mobile.android.pro.pboc.engine.b) a : null) == null) {
            aVar.b(5);
        } else {
            new Thread(new g(aVar, str, hashMap)).start();
        }
    }

    private static void a(StringBuffer stringBuffer) {
        for (String str : P.keySet()) {
            String str2 = P.get(str);
            if (!TextUtils.isEmpty(str2)) {
                String a = com.unionpay.mobile.android.pboctransaction.e.a(new byte[]{(byte) (str2.length() / 2)}, 1);
                stringBuffer.append(str);
                stringBuffer.append(a);
                stringBuffer.append(str2);
            }
        }
    }

    private synchronized Bundle b(String str, HashMap<String, String> hashMap) {
        Bundle v;
        v = v();
        Q.put("PIN", str);
        Q.put("AN1", this.C.a());
        StringBuffer stringBuffer = new StringBuffer();
        a(stringBuffer);
        Q.put("DCD", stringBuffer.toString());
        this.B = c(this.a.aZ, this.s.b());
        if (Q.get("5F34") != null) {
            StringBuffer stringBuffer2 = new StringBuffer(Q.get("5F34"));
            stringBuffer2.insert(0, "0");
            Q.put("5F34", stringBuffer2.toString());
        } else {
            Q.put("5F34", "");
        }
        if (Q.get("57") != null) {
            String upperCase = Q.get("57").toUpperCase();
            while (true) {
                if (!upperCase.substring(upperCase.length() - 1, upperCase.length()).equalsIgnoreCase(ThreeMap.type_float) && !upperCase.substring(upperCase.length() - 1, upperCase.length()).equalsIgnoreCase("F")) {
                    break;
                }
                upperCase = upperCase.substring(0, upperCase.length() - 1);
            }
            Q.put("TD2", upperCase.toString());
            int indexOf = upperCase.indexOf("D");
            String substring = upperCase.substring(0, indexOf);
            if (substring.endsWith("F") || substring.endsWith(ThreeMap.type_float)) {
                substring = substring.substring(0, substring.length() - 1);
            }
            Q.put("AN1", substring);
            Q.put("ED", upperCase.substring(indexOf + 1, indexOf + 5));
        }
        Q.put("AMT", P.get("9F02"));
        String str2 = (((("pan=" + Q.get("AN1")) + "&pin=" + Q.get("PIN")) + "&icc_data=" + Q.get("DCD")) + "&card_seq_id=" + Q.get("5F34")) + "&auth_id=" + this.a.ba;
        com.unionpay.mobile.android.utils.j.c("mac", str2);
        String d = d(str2);
        com.unionpay.mobile.android.utils.j.c("md5", d);
        String h = this.e.h(d);
        com.unionpay.mobile.android.utils.j.c("sig", h);
        String a = this.e.a(str2 + com.alipay.sdk.m.o.a.l + h, this.B);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(ThreeMap.value, this.r);
            jSONObject.put(SpeechConstant.ISV_CMD, "pay");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject.put("params", jSONObject2);
            jSONObject2.put("encrypt_key_field", a);
            jSONObject2.put("pay_type", "2");
            jSONObject2.put("pay_mode", "1");
            jSONObject2.put("bind", "no");
            jSONObject2.put("carrier_tp", "9");
            jSONObject2.put("carrier_app_tp", "0");
            jSONObject2.put("sign", h);
            jSONObject2.put(Constant.KEY_PAN, Q.get("AN1"));
            if (Q.get("ED") != null) {
                jSONObject2.put("expire", Q.get("ED"));
            }
            if (Q.get("TD2") != null) {
                jSONObject2.put("track2_data", Q.get("TD2"));
            }
            if (hashMap != null && hashMap.keySet() != null && hashMap.keySet().size() > 0) {
                hashMap.remove(Constant.KEY_PAN);
                hashMap.remove(Constant.KEY_PIN);
                for (String str3 : hashMap.keySet()) {
                    jSONObject2.put(str3, hashMap.get(str3));
                }
            }
            v.putString("action_resp_message", this.s.a(jSONObject.toString()));
        } catch (JSONException unused) {
            v.putString("action_resp_code", ResultCode.ERROR_INTERFACE_GET_SMS_AUTH_CODE);
            return v;
        }
        return v;
    }

    private boolean b(HashMap<String, String> hashMap) {
        String substring = N.substring(2, 8);
        long time = new Date(System.currentTimeMillis()).getTime();
        String valueOf = String.valueOf(time);
        String format = valueOf.length() < 8 ? String.format("%08d", Long.valueOf(time)) : valueOf.substring(valueOf.length() - 8, valueOf.length());
        P.put("9F26", "");
        P.put("9F27", "80");
        P.put("9F10", "");
        P.put("9F37", format);
        P.put("9F36", "");
        P.put("95", "0000000800");
        P.put("9A", substring);
        P.put("9C", "45");
        P.put("9F02", Constant.DEFAULT_BALANCE);
        P.put("5F2A", "0156");
        P.put("82", "");
        P.put("9F1A", "0156");
        P.put("9F03", Constant.DEFAULT_BALANCE);
        P.put("9F33", "A04000");
        P.put("9F34", "420300");
        P.put("9F35", "34");
        P.put("9F1E", "3030303030303030");
        P.put("84", "");
        P.put("9F09", ResultCode.ERROR_DETAIL_NETWORK);
        P.put("9F41", "");
        P.put("91", "");
        P.put("71", "");
        P.put("72", "");
        P.put("DF31", "");
        P.put("9F74", "");
        P.put("9F63", "");
        P.put("8A", "");
        Q.put("9F66", "26C00000");
        v();
        P.put("9F02", hashMap.get("trans_amt"));
        P.put("9F1A", "0156");
        P.put("5F2A", hashMap.get("trans currcy code"));
        P.put("9C", hashMap.get("trans_type"));
        Q.put("CUR", P.get("5F2A"));
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("9F66", Q.get("9F66"));
            jSONObject.put("9F02", P.get("9F02"));
            jSONObject.put("9F03", P.get("9F03"));
            jSONObject.put("9F1A", P.get("9F1A"));
            jSONObject.put("95", P.get("95"));
            jSONObject.put("5F2A", P.get("5F2A"));
            jSONObject.put("9A", P.get("9A"));
            jSONObject.put("9C", P.get("9C"));
            jSONObject.put("9F37", P.get("9F37"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String f = this.C.f();
        try {
            this.C.g().a(com.unionpay.mobile.android.hce.a.b(this.C.a(), f), com.unionpay.mobile.android.hce.a.b(jSONObject.toString(), f), "", new com.unionpay.mobile.android.hce.b(2004, "", this.H));
            this.H.sendMessageDelayed(this.H.obtainMessage(2006), this.a.aY);
            return true;
        } catch (RemoteException e2) {
            e2.printStackTrace();
            a(this.a.ak, false);
            return false;
        }
    }

    private static String c(String str, String str2) {
        byte[] a = com.unionpay.mobile.android.pboctransaction.e.a(str);
        byte[] a2 = com.unionpay.mobile.android.pboctransaction.e.a(str2);
        for (int i = 0; i < a.length; i++) {
            a[i] = (byte) (a[i] ^ a2[i]);
        }
        return com.unionpay.mobile.android.pboctransaction.e.a(a);
    }

    private static final String d(String str) {
        try {
            byte[] bytes = str.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bytes);
            return com.unionpay.mobile.android.pboctransaction.e.a(messageDigest.digest());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, String str2) {
        this.f51u = 9;
        if (TextUtils.isEmpty(str2)) {
            this.e.c(str, "");
        } else {
            this.e.a(str, "\"uuid\":\"" + str2 + "\"", 10);
        }
        this.D--;
    }

    static /* synthetic */ boolean d(JSONObject jSONObject) {
        if (jSONObject == null) {
            return false;
        }
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            String a = com.unionpay.mobile.android.utils.i.a(jSONObject, next);
            if (!TextUtils.isEmpty(a)) {
                (("5F34".equals(next) || "57".equals(next) || "9F6C".equals(next) || "9F5D".equals(next) || "5F20".equals(next)) ? Q : P).put(next, a);
            }
        }
        String str = P.get("9F10");
        return TextUtils.isEmpty(str) || ((byte) (com.unionpay.mobile.android.pboctransaction.e.a(str)[4] & 48)) == 32;
    }

    static /* synthetic */ HashMap f(a aVar) {
        HashMap<String, String> hashMap = new HashMap<>();
        com.unionpay.mobile.android.upviews.a aVar2 = aVar.x;
        if (aVar2 != null) {
            hashMap = aVar2.c();
        }
        com.unionpay.mobile.android.upviews.a aVar3 = aVar.y;
        if (aVar3 != null) {
            HashMap<String, String> c = aVar3.c();
            if (c != null && hashMap != null) {
                hashMap.putAll(c);
            } else if (c != null && hashMap == null) {
                return c;
            }
        }
        return hashMap;
    }

    static /* synthetic */ int i(a aVar) {
        aVar.D = 5;
        return 5;
    }

    static /* synthetic */ void p(a aVar) {
        aVar.f51u = 104;
        aVar.j = false;
        aVar.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        com.unionpay.mobile.android.utils.j.c("uppay", "");
        aVar.e.c("cardsecret", "");
    }

    static /* synthetic */ int u(a aVar) {
        aVar.f51u = 101;
        return 101;
    }

    private void u() {
        this.f51u = 103;
        this.e.a("query", this.a.ae, 3);
        this.t--;
    }

    private static Bundle v() {
        Bundle bundle = new Bundle();
        bundle.putString("action_resp_code", "0000");
        return bundle;
    }

    public final Bundle a(String str, HashMap<String, String> hashMap) {
        return b(str, hashMap);
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void a(a.C0071a c0071a) {
    }

    @Override // com.unionpay.mobile.android.nocard.views.a
    public final void a(JSONObject jSONObject) {
        int i = this.f51u;
        if (i == 8) {
            j();
            JSONArray d = com.unionpay.mobile.android.utils.i.d(jSONObject, "options");
            com.unionpay.mobile.android.upviews.a aVar = this.y;
            if (aVar != null) {
                aVar.a(d);
                return;
            }
            return;
        }
        if (i == 9) {
            String a = com.unionpay.mobile.android.utils.i.a(jSONObject, "status");
            if (a == null || !"01".equals(a)) {
                JSONArray d2 = com.unionpay.mobile.android.utils.i.d(jSONObject, "options");
                String a2 = com.unionpay.mobile.android.utils.i.a(jSONObject, "empty_info");
                com.unionpay.mobile.android.upviews.a aVar2 = this.y;
                if (aVar2 != null) {
                    aVar2.a(d2, a2);
                    return;
                }
                return;
            }
            String a3 = com.unionpay.mobile.android.utils.i.a(jSONObject, "uuid");
            if (this.D >= 0) {
                d(this.E, a3);
                return;
            }
            String str = com.unionpay.mobile.android.languages.c.bD.D;
            com.unionpay.mobile.android.upviews.a aVar3 = this.y;
            if (aVar3 != null) {
                aVar3.a((JSONArray) null, str);
                return;
            }
            return;
        }
        if (i == 101) {
            this.a.ae = com.unionpay.mobile.android.utils.h.a(jSONObject.toString());
            if (this.a.ae == null || this.a.ae.length() <= 0) {
                b(2);
                return;
            } else {
                this.t = 20;
                u();
                return;
            }
        }
        if (i != 103) {
            if (i != 104) {
                return;
            }
            try {
                this.a.aZ = (String) jSONObject.get("encrypt_key");
                this.a.ba = (String) jSONObject.get("auth_id");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (this.a.k != null) {
                a.C0071a a4 = this.x.a();
                if (a4.a()) {
                    this.f51u = 101;
                    a(this.a.k);
                    return;
                } else {
                    this.F = true;
                    a(a4.b);
                    return;
                }
            }
            return;
        }
        String a5 = com.unionpay.mobile.android.utils.i.a(jSONObject, "status");
        String a6 = com.unionpay.mobile.android.utils.i.a(jSONObject, "fail_msg");
        if (this.t > 0 && a5.equalsIgnoreCase("01")) {
            u();
            return;
        }
        this.f51u = 100;
        boolean equalsIgnoreCase = a5.equalsIgnoreCase("00");
        j();
        if (!equalsIgnoreCase) {
            this.F = true;
            if (!a5.equalsIgnoreCase("03")) {
                if (this.t <= 0) {
                    b(19);
                    return;
                }
                return;
            }
            a(this.d, this.q + "_fail", com.unionpay.mobile.android.utils.o.j, new String[]{a5, a6});
            StringBuilder sb = new StringBuilder();
            sb.append(a6);
            a(sb.toString());
            return;
        }
        this.f51u = 100;
        this.a.C = com.unionpay.mobile.android.utils.i.d(jSONObject, "result");
        this.a.K = com.unionpay.mobile.android.utils.i.a(jSONObject, "openupgrade_flag");
        this.a.L = com.unionpay.mobile.android.utils.i.a(jSONObject, "temporary_pay_flag");
        this.a.M = com.unionpay.mobile.android.utils.i.a(jSONObject, "temporary_pay_info");
        this.a.Q = com.unionpay.mobile.android.utils.i.a(jSONObject, "front_url");
        this.a.R = com.unionpay.mobile.android.utils.i.a(jSONObject, "front_request");
        this.a.v = com.unionpay.mobile.android.utils.i.a(jSONObject, com.alipay.sdk.m.s.d.v);
        this.a.w = com.unionpay.mobile.android.utils.i.a(jSONObject, "succ_info");
        com.unionpay.mobile.android.nocard.utils.b.b(jSONObject, this.a);
        com.unionpay.mobile.android.nocard.utils.b.a(jSONObject, this.a);
        com.unionpay.mobile.android.upviews.a aVar4 = this.x;
        if (aVar4 != null) {
            aVar4.f();
        }
        a(this.d, this.q + "_succeed");
        Iterator<com.unionpay.mobile.android.model.d> it = com.unionpay.mobile.android.model.b.aW.iterator();
        while (it.hasNext()) {
            try {
                this.d.unbindService(((com.unionpay.mobile.android.hce.c) it.next()).h());
            } catch (IllegalArgumentException unused) {
            }
        }
        d(8);
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void a(boolean z) {
        TextView textView = this.v;
        if (textView != null) {
            textView.setEnabled(!z);
        }
    }

    public final boolean a(HashMap<String, String> hashMap) {
        M = new Date(System.currentTimeMillis());
        N = new SimpleDateFormat("yyyyMMddHHmmss").format((java.util.Date) M);
        this.O = new String(N);
        return b(hashMap);
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    protected final void b() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        this.A = new ax(getContext(), com.unionpay.mobile.android.languages.c.bD.bq, this);
        layoutParams.addRule(13, -1);
        this.k.addView(this.A, layoutParams);
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    public final void b(int i) {
        int i2 = this.f51u;
        if (i2 == 101 || i2 == 103 || i2 == 104) {
            this.F = true;
        }
        super.b(i);
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void b(String str, String str2) {
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    protected final void c() {
        this.m.removeAllViews();
        this.o.a(this);
        LinearLayout linearLayout = new LinearLayout(this.d);
        boolean z = true;
        linearLayout.setOrientation(1);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = com.unionpay.mobile.android.global.a.f;
        layoutParams.addRule(10, -1);
        this.m.addView(linearLayout, layoutParams);
        JSONArray jSONArray = new JSONArray();
        if (this.p != null) {
            com.unionpay.mobile.android.model.f fVar = (com.unionpay.mobile.android.model.f) this.p;
            jSONArray.put(fVar.a("promotion"));
            jSONArray.put(fVar.a("instalment"));
            this.a.aP = fVar.a("promotion_instalment_msgbox");
        }
        if (jSONArray.length() > 0) {
            com.unionpay.mobile.android.upviews.a aVar = new com.unionpay.mobile.android.upviews.a(this.d, jSONArray, this, this.q);
            this.y = aVar;
            aVar.a(this.I);
            this.y.b(this.J);
            this.y.c(this.K);
            this.y.a(this.b, this.a.aP);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
            layoutParams2.bottomMargin = com.unionpay.mobile.android.global.a.f;
            linearLayout.addView(this.y, layoutParams2);
        }
        a(linearLayout);
        new LinearLayout.LayoutParams(-1, -2);
        TextView textView = new TextView(this.d);
        this.v = textView;
        textView.setText(com.unionpay.mobile.android.utils.i.a(this.a.x, "label"));
        this.v.setTextSize(com.unionpay.mobile.android.global.b.i);
        this.v.setTextColor(p());
        this.v.setGravity(17);
        TextView textView2 = this.v;
        com.unionpay.mobile.android.upviews.a aVar2 = this.x;
        if (aVar2 != null && !aVar2.e()) {
            z = false;
        }
        textView2.setEnabled(z);
        int i = com.unionpay.mobile.android.global.a.n;
        this.v.setBackgroundDrawable(this.c.a(2008));
        this.v.setOnClickListener(this.L);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, i);
        layoutParams3.topMargin = com.unionpay.mobile.android.global.a.f;
        int a = com.unionpay.mobile.android.utils.f.a(this.d, 10.0f);
        layoutParams3.rightMargin = a;
        layoutParams3.leftMargin = a;
        linearLayout.addView(this.v, layoutParams3);
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void c(String str) {
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        if (message.obj == null) {
            return true;
        }
        Bundle bundle = (Bundle) message.obj;
        String string = bundle.getString("action_resp_code");
        String string2 = bundle.getString("action_resp_message");
        if (!"0000".equalsIgnoreCase(string)) {
            a(this.a.ak, false);
            return true;
        }
        if (string2 == null) {
            return true;
        }
        a(0, string2);
        return true;
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    public final void l() {
        com.unionpay.mobile.android.upviews.a aVar = this.x;
        if (aVar == null || !aVar.d()) {
            if (this.a.F && this.w) {
                this.a.F = false;
                n();
            } else {
                this.a.F = false;
                a(2);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        com.unionpay.mobile.android.upviews.a aVar = this.x;
        if (aVar != null) {
            aVar.d();
        }
        this.b = null;
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void t() {
    }
}
