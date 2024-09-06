package com.unionpay.mobile.android.pro.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.pc.util.ThreeMap;
import com.iflytek.cloud.SpeechConstant;
import com.kqg.main.constant.KV;
import com.unionpay.mobile.android.nocard.utils.UPPayEngine;
import com.unionpay.mobile.android.upviews.a;
import com.unionpay.mobile.android.upwidget.UPRadiationView;
import com.unionpay.mobile.android.widgets.ax;
import com.unionpay.tsmservice.data.Constant;
import com.unionpay.tsmservice.data.ResultCode;
import com.unionpay.uppay.PayActivity;
import java.security.MessageDigest;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class k extends com.unionpay.mobile.android.nocard.views.b implements Handler.Callback, a.b {
    private static Date Z = new Date(System.currentTimeMillis());
    private static String aa = new SimpleDateFormat("yyyyMMddhhmmss").format((java.util.Date) Z);
    private LinearLayout A;
    private LinearLayout B;
    private RelativeLayout C;
    private LinearLayout D;
    private LinearLayout E;
    private ax F;
    private UPRadiationView G;
    private ImageView H;
    private String I;
    private String J;
    private com.unionpay.mobile.android.upwidget.a K;
    private com.unionpay.mobile.android.upwidget.a L;
    private boolean M;
    private boolean N;
    private com.unionpay.mobile.android.upviews.a O;
    private String P;
    private int Q;
    private View.OnClickListener R;
    private View.OnClickListener S;
    private View.OnClickListener T;
    private View.OnClickListener U;
    private View.OnClickListener V;
    private View.OnClickListener W;
    private String ab;
    private HashMap<String, String> ac;
    public String r;
    UPPayEngine s;
    com.unionpay.mobile.android.pboctransaction.nfc.a t;

    /* renamed from: u, reason: collision with root package name */
    private int f52u;
    private int v;
    private TextView w;
    private boolean x;
    private com.unionpay.mobile.android.upviews.a y;
    private Handler z;

    public k(Context context, com.unionpay.mobile.android.model.e eVar, UPPayEngine uPPayEngine) {
        super(context, eVar);
        this.f52u = 20;
        this.v = 100;
        this.r = "1.8";
        this.w = null;
        this.x = false;
        this.y = null;
        this.z = null;
        this.A = null;
        this.B = null;
        this.C = null;
        this.D = null;
        this.E = null;
        this.K = null;
        this.L = null;
        this.M = true;
        this.N = false;
        this.O = null;
        this.Q = 5;
        this.R = new l(this);
        this.S = new m(this);
        this.T = new n(this);
        this.U = new o(this);
        this.V = new p(this);
        this.W = new q(this);
        this.ab = null;
        this.ac = new HashMap<>();
        this.f = 6;
        this.q = "nfcpay";
        this.s = uPPayEngine;
        this.z = new Handler(this);
        this.x = this.a.F;
        setBackgroundColor(-11495169);
        e();
    }

    static /* synthetic */ void a(k kVar, String str, String str2) {
        kVar.v = 7;
        kVar.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        kVar.e.c(str, str2);
    }

    private void a(String str, StringBuffer stringBuffer) {
        String str2 = this.ac.get(str);
        String a = com.unionpay.mobile.android.pboctransaction.e.a(new byte[]{(byte) (str2.length() / 2)}, 1);
        stringBuffer.append(str);
        stringBuffer.append(a);
        stringBuffer.append(str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, HashMap<String, String> hashMap) {
        Object a = ((PayActivity) this.d).a(com.unionpay.mobile.android.pro.pboc.engine.b.class.toString());
        if ((a != null ? (com.unionpay.mobile.android.pro.pboc.engine.b) a : null) == null) {
            b(5);
        } else {
            new Thread(new r(this, str, hashMap)).start();
        }
    }

    private synchronized Bundle c(String str, HashMap<String, String> hashMap) {
        Bundle w;
        w = w();
        this.ac.put("PIN", str);
        StringBuffer stringBuffer = new StringBuffer();
        a("9F26", stringBuffer);
        a("9F27", stringBuffer);
        a("9F10", stringBuffer);
        a("9F37", stringBuffer);
        a("9F36", stringBuffer);
        a("95", stringBuffer);
        a("9A", stringBuffer);
        a("9C", stringBuffer);
        a("9F02", stringBuffer);
        a("5F2A", stringBuffer);
        a("82", stringBuffer);
        a("9F1A", stringBuffer);
        a("9F03", stringBuffer);
        a("9F33", stringBuffer);
        a("9F34", stringBuffer);
        a("9F35", stringBuffer);
        a("9F1E", stringBuffer);
        if (this.ac.get("9F63") != null && !TextUtils.isEmpty(this.ac.get("9F63"))) {
            a("9F63", stringBuffer);
        }
        this.ac.put("DCD", stringBuffer.toString());
        this.I = d(this.I, this.s.b());
        String str2 = (((("pan=" + this.ac.get("AN1")) + "&pin=" + this.ac.get("PIN")) + "&icc_data=" + this.ac.get("DCD")) + "&card_seq_id=" + this.ac.get("5F34")) + "&auth_id=" + this.J;
        com.unionpay.mobile.android.utils.j.c("mac", str2);
        String d = d(str2);
        com.unionpay.mobile.android.utils.j.c("md5", d);
        String h = this.e.h(d);
        com.unionpay.mobile.android.utils.j.c("sig", h);
        String a = this.e.a(str2 + com.alipay.sdk.m.o.a.l + h, this.I);
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
            jSONObject2.put("carrier_tp", "7");
            jSONObject2.put("carrier_app_tp", "0");
            jSONObject2.put("sign", h);
            jSONObject2.put(Constant.KEY_PAN, this.ac.get("AN1"));
            if (this.ac.get("ED") != null) {
                jSONObject2.put("expire", this.ac.get("ED"));
            }
            if (this.ac.get("TD2") != null) {
                jSONObject2.put("track2_data", this.ac.get("TD2"));
            }
            if (hashMap != null && hashMap.keySet() != null && hashMap.keySet().size() > 0) {
                hashMap.remove(Constant.KEY_PAN);
                hashMap.remove(Constant.KEY_PIN);
                for (String str3 : hashMap.keySet()) {
                    jSONObject2.put(str3, hashMap.get(str3));
                }
            }
            w.putString("action_resp_message", this.s.a(jSONObject.toString()));
        } catch (JSONException unused) {
            w.putString("action_resp_code", ResultCode.ERROR_INTERFACE_GET_SMS_AUTH_CODE);
            return w;
        }
        return w;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, String str2) {
        this.v = 8;
        if (TextUtils.isEmpty(str2)) {
            this.e.c(str, "");
        } else {
            this.e.a(str, "\"uuid\":\"" + str2 + "\"", 10);
        }
        this.Q--;
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

    private static String d(String str, String str2) {
        byte[] a = com.unionpay.mobile.android.pboctransaction.e.a(str);
        byte[] a2 = com.unionpay.mobile.android.pboctransaction.e.a(str2);
        for (int i = 0; i < a.length; i++) {
            a[i] = (byte) (a[i] ^ a2[i]);
        }
        return com.unionpay.mobile.android.pboctransaction.e.a(a);
    }

    static /* synthetic */ void f(k kVar) {
        kVar.v = 104;
        kVar.j = false;
        kVar.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        kVar.e.c("cardrules", "\"pan\":\"" + kVar.ac.get("AN1") + "\",\"carrier_tp\":\"7\"");
    }

    static /* synthetic */ int k(k kVar) {
        kVar.v = 101;
        return 101;
    }

    static /* synthetic */ int m(k kVar) {
        kVar.Q = 5;
        return 5;
    }

    private void u() {
        this.v = 103;
        this.e.a("query", this.a.ae, 3);
        this.f52u--;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HashMap<String, String> v() {
        HashMap<String, String> hashMap = new HashMap<>();
        com.unionpay.mobile.android.upviews.a aVar = this.y;
        if (aVar != null) {
            hashMap = aVar.c();
        }
        com.unionpay.mobile.android.upviews.a aVar2 = this.O;
        if (aVar2 != null) {
            HashMap<String, String> c = aVar2.c();
            if (c != null && hashMap != null) {
                hashMap.putAll(c);
            } else if (c != null && hashMap == null) {
                return c;
            }
        }
        return hashMap;
    }

    private static Bundle w() {
        Bundle bundle = new Bundle();
        bundle.putString("action_resp_code", "0000");
        return bundle;
    }

    private int x() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) this.d).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public final Bundle a(String str, HashMap<String, String> hashMap) {
        return c(str, hashMap);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x01c0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(com.unionpay.mobile.android.pboctransaction.nfc.a r13) {
        /*
            Method dump skipped, instructions count: 520
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.pro.views.k.a(com.unionpay.mobile.android.pboctransaction.nfc.a):void");
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void a(a.C0071a c0071a) {
    }

    @Override // com.unionpay.mobile.android.nocard.views.a
    public final void a(JSONObject jSONObject) {
        int i = this.v;
        if (i == 7) {
            j();
            JSONArray d = com.unionpay.mobile.android.utils.i.d(jSONObject, "options");
            com.unionpay.mobile.android.upviews.a aVar = this.O;
            if (aVar != null) {
                aVar.a(d);
                return;
            }
            return;
        }
        if (i == 8) {
            String a = com.unionpay.mobile.android.utils.i.a(jSONObject, "status");
            if (a == null || !"01".equals(a)) {
                JSONArray d2 = com.unionpay.mobile.android.utils.i.d(jSONObject, "options");
                String a2 = com.unionpay.mobile.android.utils.i.a(jSONObject, "empty_info");
                com.unionpay.mobile.android.upviews.a aVar2 = this.O;
                if (aVar2 != null) {
                    aVar2.a(d2, a2);
                    return;
                }
                return;
            }
            String a3 = com.unionpay.mobile.android.utils.i.a(jSONObject, "uuid");
            if (this.Q >= 0) {
                c(this.P, a3);
                return;
            }
            String str = com.unionpay.mobile.android.languages.c.bD.D;
            com.unionpay.mobile.android.upviews.a aVar3 = this.O;
            if (aVar3 != null) {
                aVar3.a((JSONArray) null, str);
                return;
            }
            return;
        }
        switch (i) {
            case 101:
                this.a.ae = com.unionpay.mobile.android.utils.h.a(jSONObject.toString());
                if (this.a.ae == null || this.a.ae.length() <= 0) {
                    b(2);
                    return;
                } else {
                    this.f52u = 20;
                    u();
                    return;
                }
            case 102:
                this.b.c();
                try {
                    this.I = (String) jSONObject.get("encrypt_key");
                    this.J = (String) jSONObject.get("auth_id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                int a4 = com.unionpay.mobile.android.nocard.utils.f.a(this.a, jSONObject, false);
                this.p = com.unionpay.mobile.android.nocard.utils.f.a(jSONObject);
                if (a4 != 0) {
                    b(a4);
                    return;
                }
                this.M = false;
                setBackgroundColor(-1052684);
                this.F.setBackgroundColor(com.unionpay.mobile.android.global.a.M);
                this.F.a(8);
                this.A.removeAllViews();
                this.B.setVisibility(8);
                this.m.setBackgroundColor(-1052684);
                this.l.setVisibility(0);
                this.H.setVisibility(8);
                this.D.setVisibility(8);
                new LinearLayout.LayoutParams(-1, -2);
                JSONArray jSONArray = new JSONArray();
                if (this.p != null) {
                    com.unionpay.mobile.android.model.f fVar = (com.unionpay.mobile.android.model.f) this.p;
                    jSONArray.put(fVar.a("promotion"));
                    jSONArray.put(fVar.a("instalment"));
                    this.a.aP = fVar.a("promotion_instalment_msgbox");
                }
                if (jSONArray.length() > 0) {
                    com.unionpay.mobile.android.upviews.a aVar4 = new com.unionpay.mobile.android.upviews.a(this.d, jSONArray, this, this.q);
                    this.O = aVar4;
                    aVar4.a(this.b, this.a.aP);
                    this.O.a(this.S);
                    this.O.b(this.T);
                    this.O.c(this.U);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
                    layoutParams.topMargin = com.unionpay.mobile.android.global.a.f;
                    this.A.addView(this.O, layoutParams);
                }
                com.unionpay.mobile.android.upviews.a aVar5 = this.O;
                if (aVar5 != null) {
                    aVar5.c("instalment");
                }
                this.y = new com.unionpay.mobile.android.upviews.a(this.d, this.a.f39u, this.e.c(), this, this.ac.get("AN1"), true, this.q);
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
                layoutParams2.topMargin = com.unionpay.mobile.android.global.a.f;
                this.A.addView(this.y, layoutParams2);
                LinearLayout linearLayout = new LinearLayout(this.d);
                linearLayout.setOrientation(1);
                linearLayout.setId(linearLayout.hashCode());
                new LinearLayout.LayoutParams(-1, -2);
                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
                layoutParams3.topMargin = com.unionpay.mobile.android.global.a.f;
                layoutParams3.leftMargin = com.unionpay.mobile.android.utils.f.a(this.d, 10.0f);
                this.A.addView(linearLayout, layoutParams3);
                if (i() || this.a.ag != null || this.a.ah != null) {
                    if (this.a.ag != null) {
                        com.unionpay.mobile.android.upwidget.a aVar6 = new com.unionpay.mobile.android.upwidget.a(this.d, this.a.ag, this.W, this.q + "_agree_user_protocol");
                        this.L = aVar6;
                        linearLayout.addView(aVar6);
                    }
                    if (this.a.ah != null) {
                        this.K = new com.unionpay.mobile.android.upwidget.a(this.d, this.a.ah, null, this.q + "_remember_bankNO");
                        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-2, -2);
                        layoutParams4.topMargin = com.unionpay.mobile.android.global.a.f;
                        linearLayout.addView(this.K, layoutParams4);
                    }
                }
                new LinearLayout.LayoutParams(-1, -2);
                TextView textView = new TextView(this.d);
                this.w = textView;
                textView.setText(com.unionpay.mobile.android.utils.i.a(this.a.x, "label"));
                this.w.setTextSize(com.unionpay.mobile.android.global.b.i);
                this.w.setTextColor(p());
                this.w.setGravity(17);
                TextView textView2 = this.w;
                com.unionpay.mobile.android.upviews.a aVar7 = this.y;
                textView2.setEnabled(aVar7 == null || aVar7.e());
                int i2 = com.unionpay.mobile.android.global.a.n;
                this.w.setBackgroundDrawable(this.c.a(2008));
                this.w.setOnClickListener(this.R);
                LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-1, i2);
                layoutParams5.topMargin = com.unionpay.mobile.android.global.a.f;
                int a5 = com.unionpay.mobile.android.utils.f.a(this.d, 10.0f);
                layoutParams5.rightMargin = a5;
                layoutParams5.leftMargin = a5;
                this.A.addView(this.w, layoutParams5);
                return;
            case 103:
                String a6 = com.unionpay.mobile.android.utils.i.a(jSONObject, "status");
                String a7 = com.unionpay.mobile.android.utils.i.a(jSONObject, "fail_msg");
                if (this.f52u > 0 && a6.equalsIgnoreCase("01")) {
                    u();
                    return;
                }
                this.v = 100;
                boolean equalsIgnoreCase = a6.equalsIgnoreCase("00");
                j();
                if (!equalsIgnoreCase) {
                    this.N = true;
                    if (!a6.equalsIgnoreCase("03")) {
                        if (this.f52u <= 0) {
                            a(this.a.af);
                            return;
                        }
                        return;
                    }
                    a(this.d, this.q + "_fail", com.unionpay.mobile.android.utils.o.j, new String[]{a6, a7});
                    StringBuilder sb = new StringBuilder();
                    sb.append(a7);
                    a(sb.toString());
                    return;
                }
                this.v = 100;
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
                com.unionpay.mobile.android.upviews.a aVar8 = this.y;
                if (aVar8 != null) {
                    aVar8.f();
                }
                a(this.d, this.q + "_succeed");
                d(8);
                return;
            case 104:
                try {
                    this.I = (String) jSONObject.get("encrypt_key");
                    this.J = (String) jSONObject.get("auth_id");
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                if (this.a.k != null) {
                    a.C0071a a8 = this.y.a();
                    if (a8.a()) {
                        this.v = 101;
                        b(this.y.a().b, v());
                        return;
                    } else {
                        this.N = true;
                        a(a8.b);
                        return;
                    }
                }
                return;
            default:
                return;
        }
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void a(boolean z) {
        TextView textView = this.w;
        if (textView != null) {
            textView.setEnabled(!z);
        }
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    protected final void b() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        this.F = this.a.ay ? new ax(this.d, com.unionpay.mobile.android.languages.c.bD.bo, this.c.a(1030), com.unionpay.mobile.android.utils.f.a(this.d, 20.0f), this) : new ax(getContext(), com.unionpay.mobile.android.languages.c.bD.bo, this);
        this.F.setBackgroundColor(-16686660);
        this.F.a(0);
        layoutParams.addRule(13, -1);
        this.k.addView(this.F, layoutParams);
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    public final void b(int i) {
        int i2 = this.v;
        if (i2 == 101 || i2 == 103 || i2 == 104) {
            this.N = true;
        }
        super.b(i);
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void b(String str, String str2) {
    }

    public final void b(boolean z) {
        if (!z) {
            this.B.setVisibility(0);
            this.D.setVisibility(8);
            this.E.setVisibility(0);
            UPRadiationView uPRadiationView = this.G;
            if (uPRadiationView != null) {
                uPRadiationView.setVisibility(4);
                return;
            }
            return;
        }
        this.B.setVisibility(8);
        if (this.M) {
            this.D.setVisibility(0);
        }
        this.E.setVisibility(8);
        UPRadiationView uPRadiationView2 = this.G;
        if (uPRadiationView2 != null) {
            uPRadiationView2.setVisibility(0);
        }
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    protected final void c() {
        this.l.setVisibility(8);
        FrameLayout frameLayout = new FrameLayout(this.d);
        this.m.addView(frameLayout, new RelativeLayout.LayoutParams(-1, -1));
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, (x() - com.unionpay.mobile.android.global.b.n) - (com.unionpay.mobile.android.global.b.n / 2));
        LinearLayout linearLayout = new LinearLayout(this.d);
        this.A = linearLayout;
        linearLayout.setId(linearLayout.hashCode());
        this.A.setOrientation(1);
        frameLayout.addView(this.A, layoutParams);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(com.unionpay.mobile.android.global.b.n * 2, com.unionpay.mobile.android.global.b.n * 2);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) this.d).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        layoutParams2.leftMargin = (displayMetrics.widthPixels / 2) - com.unionpay.mobile.android.global.b.n;
        layoutParams2.topMargin = (x() / 2) - (com.unionpay.mobile.android.global.b.n * 2);
        ImageView imageView = new ImageView(this.d);
        this.H = imageView;
        imageView.setBackgroundDrawable(this.c.a(KV.EVENT_BIND_EMAIL_GET_CODE2));
        frameLayout.addView(this.H, layoutParams2);
        LinearLayout linearLayout2 = new LinearLayout(this.d);
        this.E = linearLayout2;
        linearLayout2.setBackgroundColor(-1342177280);
        frameLayout.addView(this.E, new FrameLayout.LayoutParams(-1, -1));
        this.C = new RelativeLayout(this.d);
        frameLayout.addView(this.C, new FrameLayout.LayoutParams(-1, -1));
        this.m.setBackgroundColor(-11495169);
        LinearLayout linearLayout3 = this.A;
        linearLayout3.removeAllViews();
        this.G = new UPRadiationView(this.d);
        linearLayout3.addView(this.G, new LinearLayout.LayoutParams(-1, -1));
        RelativeLayout relativeLayout = this.C;
        relativeLayout.setGravity(1);
        int a = com.unionpay.mobile.android.utils.f.a(this.d, 10.0f);
        LinearLayout linearLayout4 = new LinearLayout(this.d);
        this.B = linearLayout4;
        linearLayout4.setId(linearLayout4.hashCode());
        this.B.setOrientation(0);
        int i = a * 2;
        this.B.setPadding(i, a, i, a);
        Drawable a2 = this.c.a(KV.EVENT_HANDLE_MESSAGE_TO_BIND_EMAIL_GET_CODE);
        this.B.setBackgroundDrawable(a2);
        String str = com.unionpay.mobile.android.languages.c.bD.bl;
        TextView textView = new TextView(this.d);
        textView.setTextColor(-1);
        textView.setText(str);
        textView.setTextSize(com.unionpay.mobile.android.global.b.k);
        this.B.addView(textView);
        String str2 = com.unionpay.mobile.android.languages.c.bD.bm;
        TextView textView2 = new TextView(this.d);
        textView2.setTextColor(-16729682);
        textView2.setText(str2);
        textView2.setTextSize(com.unionpay.mobile.android.global.b.k);
        textView2.setOnClickListener(this.V);
        this.B.addView(textView2);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(10, -1);
        layoutParams3.topMargin = com.unionpay.mobile.android.global.b.n;
        relativeLayout.addView(this.B, layoutParams3);
        LinearLayout linearLayout5 = new LinearLayout(this.d);
        this.D = linearLayout5;
        linearLayout5.setId(linearLayout5.hashCode());
        this.D.setOrientation(0);
        this.D.setGravity(17);
        this.D.setPadding(i, a, i, a);
        this.D.setBackgroundDrawable(a2);
        String str3 = com.unionpay.mobile.android.languages.c.bD.bn;
        TextView textView3 = new TextView(this.d);
        textView3.setTextColor(-1);
        textView3.setText(str3);
        textView3.setTextSize(com.unionpay.mobile.android.global.b.k);
        this.D.addView(textView3);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams4.addRule(12, -1);
        layoutParams4.bottomMargin = com.unionpay.mobile.android.global.b.n;
        relativeLayout.addView(this.D, layoutParams4);
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
            ResultCode.ERROR_INTERFACE_GET_CARD_INFO.equalsIgnoreCase(string);
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
        if (this.a.ay) {
            this.b.a(new s(this), new t(this));
            this.b.a(com.unionpay.mobile.android.languages.c.bD.Y, com.unionpay.mobile.android.languages.c.bD.av, com.unionpay.mobile.android.languages.c.bD.W, com.unionpay.mobile.android.languages.c.bD.X);
            return;
        }
        com.unionpay.mobile.android.upviews.a aVar = this.y;
        if (aVar == null || !aVar.d()) {
            if (this.a.F && this.x) {
                this.a.F = false;
                n();
            } else {
                this.a.F = false;
                this.M = false;
                a(2);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        com.unionpay.mobile.android.upviews.a aVar = this.y;
        if (aVar != null) {
            aVar.d();
        }
        UPRadiationView uPRadiationView = this.G;
        if (uPRadiationView != null) {
            uPRadiationView.a();
        }
        this.G = null;
        this.b = null;
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void t() {
    }
}
