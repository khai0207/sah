package com.unionpay.mobile.android.nocard.views;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.alipay.sdk.app.OpenAuthTask;
import com.iflytek.cloud.SpeechConstant;
import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import com.unionpay.mobile.android.plugin.BaseActivity;
import com.unionpay.mobile.android.utils.PreferenceUtils;
import com.unionpay.mobile.android.utils.q;
import com.unionpay.sdk.UPAgent;
import com.unionpay.tsmservice.data.Constant;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class l extends b implements q.a {
    private String A;
    private int B;
    private volatile int C;
    private boolean D;
    private boolean E;
    private JSONArray F;
    private com.unionpay.mobile.android.model.e G;
    private long H;
    private Activity I;
    private boolean J;
    private boolean K;
    public List<com.unionpay.mobile.android.model.c> r;
    public List<com.unionpay.mobile.android.model.c> s;
    int t;

    /* renamed from: u, reason: collision with root package name */
    String f47u;
    String v;
    String w;
    int x;
    int y;
    private ProgressBar z;

    public l(Context context) {
        super(context);
        this.z = null;
        this.A = null;
        this.B = 0;
        this.C = 0;
        this.r = null;
        this.s = null;
        this.D = false;
        this.E = false;
        this.K = false;
        this.t = 0;
        this.f47u = "";
        this.v = "";
        this.w = "";
        this.x = 500;
        this.y = 5;
        this.f = 1;
        this.q = "init";
        d();
        Activity activity = (Activity) context;
        this.I = activity;
        this.J = com.unionpay.mobile.android.nocard.utils.a.a(activity.getIntent(), this.a);
        if (this.a.aH) {
            setVisibility(8);
            this.b.a(com.unionpay.mobile.android.languages.c.bD.c);
        }
        t();
    }

    private final boolean C() {
        try {
            String string = this.F != null ? this.F.getString(3) : null;
            if (string == null || string.length() <= 0) {
                return false;
            }
            return !Constants.NULL_VERSION_ID.equalsIgnoreCase(string);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:175:0x012a A[Catch: all -> 0x028d, TryCatch #1 {, blocks: (B:3:0x0001, B:5:0x001d, B:8:0x0024, B:10:0x0028, B:12:0x0034, B:14:0x003e, B:18:0x0049, B:29:0x0058, B:33:0x005e, B:35:0x0066, B:36:0x006b, B:38:0x007b, B:40:0x0081, B:41:0x0141, B:43:0x014d, B:45:0x0153, B:47:0x015d, B:49:0x0163, B:52:0x016e, B:97:0x017a, B:99:0x017e, B:100:0x0189, B:102:0x018d, B:103:0x0198, B:105:0x019c, B:106:0x01a7, B:108:0x01ad, B:109:0x01b9, B:111:0x01bf, B:112:0x01c7, B:114:0x01cd, B:117:0x01d9, B:126:0x01f4, B:136:0x01b5, B:137:0x01a3, B:138:0x0194, B:139:0x0185, B:54:0x01fc, B:56:0x0200, B:58:0x0204, B:60:0x0217, B:62:0x021d, B:64:0x0223, B:66:0x0229, B:68:0x022f, B:69:0x023f, B:71:0x0245, B:73:0x024b, B:75:0x0251, B:76:0x0284, B:79:0x0257, B:81:0x025d, B:83:0x0263, B:85:0x0267, B:87:0x026b, B:89:0x0273, B:90:0x027f, B:91:0x0208, B:93:0x020c, B:141:0x01f9, B:142:0x0088, B:143:0x0090, B:145:0x0094, B:147:0x0098, B:149:0x009c, B:151:0x00a0, B:153:0x00a8, B:154:0x00ae, B:156:0x00b4, B:159:0x00c0, B:164:0x00c4, B:166:0x00d0, B:167:0x00d4, B:168:0x010c, B:170:0x0112, B:172:0x011c, B:173:0x0124, B:175:0x012a, B:178:0x0136, B:181:0x013d, B:187:0x00d7, B:189:0x00dd, B:190:0x00e2, B:192:0x00e6, B:194:0x00ee, B:195:0x00f7, B:197:0x00fb, B:199:0x0103), top: B:2:0x0001, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final synchronized void D() {
        /*
            Method dump skipped, instructions count: 658
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.nocard.views.l.D():void");
    }

    private void a(String str, String str2, String str3) {
        com.unionpay.mobile.android.widgets.m mVar;
        String str4;
        m mVar2 = new m(this, str3);
        n nVar = new n(this);
        if (!str.equalsIgnoreCase("01")) {
            this.b.a(mVar2, nVar);
            mVar = this.b;
            str4 = com.unionpay.mobile.android.languages.c.bD.ae;
        } else {
            this.b.a(mVar2, nVar);
            mVar = this.b;
            str4 = com.unionpay.mobile.android.languages.c.bD.Y;
        }
        mVar.a(str4, str2, com.unionpay.mobile.android.languages.c.bD.af, com.unionpay.mobile.android.languages.c.bD.ag);
    }

    private void b(int i, String str) {
        if (str != null && str.length() > 0) {
            this.a.D.f = str;
        }
        this.z.setVisibility(4);
        a(c(i), true);
    }

    private void b(String str, String str2) {
        if (str2 != null && str2.length() > 0) {
            this.a.D.f = str2;
        }
        this.z.setVisibility(4);
        a(str, true);
    }

    private boolean f(int i) {
        if ((i & 2) == 0) {
            return false;
        }
        this.a.ar = true;
        return true;
    }

    public final void A() {
        removeAllViews();
        this.z = null;
    }

    public void B() {
        d(Constant.DEFAULT_CVN2);
    }

    public void a(int i, int i2, String str, String str2, int i3, String str3) {
    }

    @Override // com.unionpay.mobile.android.utils.q.a
    public final void a(int i, byte[] bArr) {
        j();
        if (i != 0) {
            b(i, (String) null);
        }
        com.unionpay.mobile.android.utils.j.a("uppay", "status = " + i);
        if (bArr != null) {
            if (!"mounted".equals(Environment.getExternalStorageState())) {
                b(9, (String) null);
                return;
            }
            if (!com.unionpay.mobile.android.utils.p.a(bArr)) {
                b(21, (String) null);
                return;
            }
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            String str = Environment.getExternalStorageDirectory() + File.separator + "UPPay" + File.separator + "UPPayPluginEx.apk";
            com.unionpay.mobile.android.utils.j.a("uppay", "apk path:" + str);
            intent.setDataAndType(Uri.fromFile(new File(str)), "application/vnd.android.package-archive");
            ((BaseActivity) this.d).startActivityForResult(intent, 100);
        }
    }

    @Override // com.unionpay.mobile.android.nocard.views.a
    public final void a(JSONObject jSONObject) {
        String a;
        com.unionpay.mobile.android.utils.j.a("uppay", "init.parserParamJsonObj() +++");
        if (jSONObject == null) {
            b(2);
            return;
        }
        int i = this.B;
        int i2 = 1;
        if (i != 1) {
            if (i == 2) {
                com.unionpay.mobile.android.nocard.utils.f.c(this.a, jSONObject);
                int b = com.unionpay.mobile.android.nocard.utils.f.b(this.a, jSONObject);
                if (b != 0) {
                    b(b);
                } else {
                    String a2 = com.unionpay.mobile.android.utils.i.a(jSONObject, "userId");
                    if (!TextUtils.isEmpty(a2)) {
                        a(this.d, "_login", com.unionpay.mobile.android.utils.o.e, new Object[]{a2});
                    }
                    this.G = com.unionpay.mobile.android.nocard.utils.f.a(jSONObject);
                    x();
                }
            }
            com.unionpay.mobile.android.utils.j.a("uppay", "init.parserParamJsonObj() ---");
        }
        String a3 = com.unionpay.mobile.android.utils.i.a(jSONObject, "secret");
        com.unionpay.mobile.android.utils.i.a(jSONObject, "sec_sign");
        this.e.e(a3);
        String a4 = com.unionpay.mobile.android.utils.i.a(jSONObject, "talking_data_flag");
        if (!TextUtils.isEmpty(a4)) {
            com.unionpay.mobile.android.global.a.L = "0".equals(a4);
        }
        String a5 = com.unionpay.mobile.android.utils.i.a(jSONObject, "mer_id");
        String str = !TextUtils.isEmpty(a5) ? a5 : "002";
        if (!this.a.D.d) {
            str = "001";
        }
        if (com.unionpay.mobile.android.global.a.L) {
            UPAgent.init(this.d, this.e.b(this.a.D.c), str);
            UPAgent.setReportUncaughtExceptions(true);
            UPAgent.setAdditionalVersionNameAndCode(com.unionpay.mobile.android.languages.c.bD.a, 57L);
        }
        a(this.d, "tn", com.unionpay.mobile.android.utils.o.a, new String[]{this.a.b});
        a(this.d, "merch_id", com.unionpay.mobile.android.utils.o.b, new String[]{a5});
        a(this.d, "package_version", com.unionpay.mobile.android.utils.o.c, new String[]{com.unionpay.mobile.android.languages.c.bD.a});
        JSONObject c = com.unionpay.mobile.android.utils.i.c(jSONObject, "upgrade_info");
        this.A = com.unionpay.mobile.android.utils.i.a(c, "type");
        String a6 = com.unionpay.mobile.android.utils.i.a(c, "desc");
        String a7 = com.unionpay.mobile.android.utils.i.a(c, Constants.URL_ENCODING);
        if (!this.A.equalsIgnoreCase("02")) {
            this.a.j = com.unionpay.mobile.android.utils.i.a(jSONObject, com.alipay.sdk.m.s.d.v);
            this.a.g = com.unionpay.mobile.android.utils.i.c(jSONObject, "init_button");
            this.a.e = com.unionpay.mobile.android.utils.i.d(jSONObject, "order");
            HashMap hashMap = new HashMap();
            for (int i3 = 0; i3 < this.a.e.length(); i3++) {
                Object b2 = com.unionpay.mobile.android.utils.i.b(this.a.e, i3);
                if (b2 != null) {
                    JSONObject jSONObject2 = (JSONObject) b2;
                    hashMap.put(com.unionpay.mobile.android.utils.i.a(jSONObject2, "label"), com.unionpay.mobile.android.utils.i.a(jSONObject2, "value"));
                }
            }
            a(this.d, "basic_info", com.unionpay.mobile.android.utils.o.d, new String[]{hashMap.toString()});
            this.a.f = com.unionpay.mobile.android.utils.i.c(jSONObject, "risk_info");
            List<JSONArray> e = com.unionpay.mobile.android.utils.i.e(jSONObject, "cards");
            if (e != null && e.size() > 0) {
                this.r = new ArrayList(e.size());
            }
            int i4 = 0;
            while (e != null && i4 < e.size()) {
                this.r.add(new com.unionpay.mobile.android.model.a(com.unionpay.mobile.android.utils.i.a(e.get(i4), 0), com.unionpay.mobile.android.utils.i.a(e.get(i4), i2), com.unionpay.mobile.android.utils.i.a(e.get(i4), 2), (byte) 0));
                i4++;
                i2 = 1;
            }
            this.a.n = com.unionpay.mobile.android.utils.i.a(jSONObject, "bank_url");
            this.a.o = com.unionpay.mobile.android.utils.i.d(jSONObject, "input_info");
            this.a.q = com.unionpay.mobile.android.utils.i.c(jSONObject, "account_info");
            this.a.r = com.unionpay.mobile.android.utils.i.c(jSONObject, "other_card_info");
            this.a.p = com.unionpay.mobile.android.utils.i.a(jSONObject, "user_id");
            this.e.c(com.unionpay.mobile.android.utils.i.a(jSONObject, SpeechConstant.IST_SESSION_ID));
            this.e.d(com.unionpay.mobile.android.utils.i.a(jSONObject, "secret"));
            String a8 = com.unionpay.mobile.android.utils.i.a(jSONObject, "secret");
            if (!TextUtils.isEmpty(a8)) {
                this.a.i = a8;
            }
            String a9 = com.unionpay.mobile.android.utils.i.a(jSONObject, "uid");
            if (a9 != null && !TextUtils.isEmpty(a9)) {
                PreferenceUtils.b(this.d, a9);
            }
            if (w()) {
                if (v()) {
                    this.a.s = com.unionpay.mobile.android.utils.i.c(jSONObject, "kalefu_info");
                    this.a.t = com.unionpay.mobile.android.utils.i.a(jSONObject, "kalefu_button_label");
                    if ((this.a.t == null || this.a.t.length() <= 0) && com.unionpay.mobile.android.languages.c.bD != null) {
                        this.a.t = com.unionpay.mobile.android.languages.c.bD.am;
                    }
                }
                this.F = com.unionpay.mobile.android.utils.i.d(jSONObject, "cards_desc");
                this.a.ai = com.unionpay.mobile.android.utils.i.a(jSONObject, "trade_privilege");
                this.a.ak = com.unionpay.mobile.android.utils.i.a(jSONObject, "upcard_msg");
                this.a.aj = 0;
                String a10 = com.unionpay.mobile.android.utils.i.a(jSONObject, "upcard_support_type");
                if (!"1".equalsIgnoreCase(this.a.ai) && a10 != null && a10.length() > 0) {
                    this.a.aj = Integer.parseInt(a10, 2);
                }
                f(this.a.aj);
            }
            this.a.am = com.unionpay.mobile.android.utils.i.a(jSONObject, "ad");
            this.a.ao = com.unionpay.mobile.android.utils.i.a(jSONObject, "pay_tip");
            String a11 = com.unionpay.mobile.android.utils.i.a(jSONObject, "sup_pay_method");
            if (!TextUtils.isEmpty(a11)) {
                this.a.ax = "01".equals(a11);
                this.a.ay = "001".equals(a11);
            }
            String a12 = com.unionpay.mobile.android.utils.i.a(jSONObject, "default_pay_type");
            if (!TextUtils.isEmpty(a12)) {
                this.a.az = "0501".equals(a12);
            }
            this.a.ap = com.unionpay.mobile.android.utils.i.c(jSONObject, "find_pwd_url");
            this.a.T = com.unionpay.mobile.android.utils.i.c(jSONObject, "reg_url");
            this.a.at = "1".equals(com.unionpay.mobile.android.utils.i.a(jSONObject, "sup_nfc"));
            this.a.au = "1".equals(com.unionpay.mobile.android.utils.i.a(jSONObject, "sup_hce"));
            com.unionpay.mobile.android.model.b.av = "1".equals(com.unionpay.mobile.android.utils.i.a(jSONObject, "sup_samsung_pay"));
            this.a.bb = com.unionpay.mobile.android.utils.i.a(jSONObject, "hce_introduction_url");
            if (v() && this.a.at && !this.a.ax) {
                String a13 = com.unionpay.mobile.android.utils.i.a(jSONObject, "nfc_title");
                if (!TextUtils.isEmpty(a13)) {
                    com.unionpay.mobile.android.languages.c.bD.bo = a13;
                }
                JSONObject c2 = com.unionpay.mobile.android.utils.i.c(jSONObject, "nfc_button");
                if (c2 != null && (a = com.unionpay.mobile.android.utils.i.a(c2, "label")) != null && !TextUtils.isEmpty(a)) {
                    com.unionpay.mobile.android.languages.c.bD.bp = a;
                }
            }
            if (v() && this.a.au && !this.a.ax && !this.a.ay) {
                String a14 = com.unionpay.mobile.android.utils.i.a(jSONObject, "hce_title");
                this.t = com.unionpay.mobile.android.utils.i.b(jSONObject, "hce_page_size");
                JSONObject c3 = com.unionpay.mobile.android.utils.i.c(jSONObject, "hce_button");
                String a15 = com.unionpay.mobile.android.utils.i.a(c3, "label");
                String a16 = com.unionpay.mobile.android.utils.i.a(c3, "htmlLabel");
                if (TextUtils.isEmpty(a14)) {
                    com.unionpay.mobile.android.languages.c.bD.bq = a15;
                } else {
                    com.unionpay.mobile.android.languages.c.bD.bq = a14;
                }
                if (TextUtils.isEmpty(a16)) {
                    com.unionpay.mobile.android.languages.c.bD.br = a15;
                } else {
                    com.unionpay.mobile.android.languages.c.bD.br = a16;
                }
                this.f47u = com.unionpay.mobile.android.utils.i.a(c3, "action");
                this.v = com.unionpay.mobile.android.utils.i.a(c3, "reserved");
                this.w = com.unionpay.mobile.android.utils.i.a(jSONObject, "iss_ins_code");
                this.x = com.unionpay.mobile.android.utils.i.b(jSONObject, "hce_bank_timeout");
                this.y = com.unionpay.mobile.android.utils.i.b(jSONObject, "hce_concurrent_count");
                int b3 = com.unionpay.mobile.android.utils.i.b(jSONObject, "hce_pay_timeout");
                if (b3 != 0) {
                    this.a.aY = b3;
                } else {
                    this.a.aY = OpenAuthTask.Duplex;
                }
                this.a.bc = com.unionpay.mobile.android.utils.i.a(jSONObject, "no_hce_card_msg");
            }
            com.unionpay.mobile.android.model.b bVar = this.a;
            bVar.k = new HashMap<>();
            JSONObject c4 = com.unionpay.mobile.android.utils.i.c(jSONObject, "f55");
            String a17 = com.unionpay.mobile.android.utils.i.a(c4, "order_amount");
            bVar.k.put("trans_amt", (a17 == null || a17.length() <= 0) ? Constant.DEFAULT_BALANCE : a17);
            bVar.bj = com.unionpay.mobile.android.utils.c.c(a17);
            String a18 = com.unionpay.mobile.android.utils.i.a(c4, "order_currency");
            bVar.k.put("trans currcy code", (a18 == null || a18.length() <= 0) ? "0156" : a18);
            bVar.bk = a18;
            String a19 = com.unionpay.mobile.android.utils.i.a(c4, "trans_type");
            HashMap<String, String> hashMap2 = bVar.k;
            if (a19 == null || a19.length() <= 0) {
                a19 = "00";
            }
            hashMap2.put("trans_type", a19);
            String a20 = com.unionpay.mobile.android.utils.i.a(c4, "mer_name");
            HashMap<String, String> hashMap3 = bVar.k;
            if (a20 == null || a20.length() <= 0) {
                a20 = "";
            }
            hashMap3.put("mer_name", a20);
            if (this.a.ay) {
                this.a.p = "";
            }
            if (this.A.equalsIgnoreCase("00")) {
                if (b(this.a.p)) {
                    this.B = 2;
                    this.e.m(String.format("\"user_id\":\"%s\"", this.a.p));
                    com.unionpay.mobile.android.utils.j.a("uppay", "init.parserParamJsonObj() ---");
                }
                x();
                com.unionpay.mobile.android.utils.j.a("uppay", "init.parserParamJsonObj() ---");
            }
        }
        a(this.A, a6, a7);
        com.unionpay.mobile.android.utils.j.a("uppay", "init.parserParamJsonObj() ---");
    }

    public final void a(boolean z) {
        this.a.as = z;
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    public final void b(int i) {
        com.unionpay.mobile.android.utils.j.a("uppay", toString() + "doErrHappended() +++");
        b(i, Constant.CASH_LOAD_FAIL);
        com.unionpay.mobile.android.utils.j.a("uppay", toString() + "doErrHappended() ---");
    }

    public final void c(String str) {
        this.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        new com.unionpay.mobile.android.utils.q(this.d, str, this).a();
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    protected final void d() {
        super.d();
        this.m.setBackgroundColor(-1);
        setBackgroundDrawable(this.c.a(3008));
        int i = com.unionpay.mobile.android.global.a.I / 2;
        ImageView imageView = new ImageView(getContext());
        imageView.setImageDrawable(this.c.a(1027, i, -1));
        imageView.setId(imageView.hashCode());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i, -2);
        layoutParams.addRule(14);
        layoutParams.leftMargin = com.unionpay.mobile.android.global.a.j;
        layoutParams.topMargin = (int) (com.unionpay.mobile.android.global.a.t * 0.3f);
        addView(imageView, layoutParams);
        this.z = new ProgressBar(getContext(), null, R.attr.progressBarStyleInverse);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(14, -1);
        layoutParams2.addRule(3, imageView.getId());
        layoutParams2.topMargin = com.unionpay.mobile.android.global.a.d * 3;
        addView(this.z, layoutParams2);
        LinearLayout linearLayout = new LinearLayout(this.d);
        linearLayout.setOrientation(1);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.addRule(14, -1);
        layoutParams3.addRule(12, -1);
        layoutParams3.bottomMargin = com.unionpay.mobile.android.global.a.b;
        addView(linearLayout, layoutParams3);
        TextView textView = new TextView(this.d);
        textView.setText(com.unionpay.mobile.android.languages.c.bD.a);
        textView.setTextColor(-1);
        textView.setTextSize(14.0f);
        textView.setGravity(1);
        new LinearLayout.LayoutParams(-2, -2).gravity = 14;
        linearLayout.addView(textView, layoutParams3);
        TextView textView2 = new TextView(getContext());
        textView2.setText(com.unionpay.mobile.android.languages.c.bD.b);
        textView2.setTextColor(-1);
        textView2.setTextSize(16.0f);
        textView2.setGravity(1);
        new LinearLayout.LayoutParams(-2, -2).gravity = 14;
        linearLayout.addView(textView2, layoutParams3);
    }

    public final void d(String str) {
        this.B = 1;
        this.e.a(this.H);
        this.e.b(this.a.b, str);
        this.e.a(this);
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    public final void l() {
    }

    protected void t() {
        u();
    }

    public final void u() {
        int i;
        if (this.K) {
            return;
        }
        this.K = true;
        y();
        this.E = false;
        Activity activity = this.I;
        boolean z = this.J;
        this.e.a();
        try {
            i = Integer.parseInt(this.a.D.c);
        } catch (NumberFormatException unused) {
            i = 0;
        }
        boolean z2 = !"com.unionpay.uppay".equals(com.unionpay.mobile.android.utils.e.b(this.d));
        long initJNIEnv = this.e.initJNIEnv(activity, this.a.c ? 1 : 0, i, z2, this.a.a, this.a.aJ, com.unionpay.mobile.android.utils.c.b(this.a.b));
        this.H = initJNIEnv;
        if (z && initJNIEnv != 0 && initJNIEnv != -1) {
            Context context = this.d;
            B();
        } else if (this.H == -1) {
            b(7, (String) null);
        } else {
            if (z) {
                return;
            }
            b(5, (String) null);
        }
    }

    public boolean v() {
        return false;
    }

    public boolean w() {
        return false;
    }

    public final void x() {
        if (this.A.equalsIgnoreCase("02")) {
            k();
            return;
        }
        this.C++;
        this.E = true;
        D();
    }

    public void y() {
        z();
    }

    protected final void z() {
        this.C++;
        D();
    }
}
