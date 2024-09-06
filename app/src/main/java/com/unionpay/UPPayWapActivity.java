package com.unionpay;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class UPPayWapActivity extends Activity {
    LinearLayout a;
    private WebView b;
    private WebViewJavascriptBridge c;
    private AlertDialog d;

    static /* synthetic */ void a(UPPayWapActivity uPPayWapActivity, String str, String str2) {
        Intent intent = new Intent();
        intent.putExtra("pay_result", str);
        intent.putExtra("result_data", str2);
        uPPayWapActivity.setResult(-1, intent);
        uPPayWapActivity.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(String str, String str2, String str3) {
        try {
            JSONObject jSONObject = new JSONObject("{\"code\":\"0\",\"msg\":\"success\"}");
            if (str != null) {
                jSONObject.put("code", str);
            }
            if (str2 != null) {
                jSONObject.put("msg", str2);
            }
            if (str3 != null) {
                jSONObject.put("value", str3);
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        String str;
        View.OnClickListener mVar;
        String str2;
        super.onCreate(bundle);
        String stringExtra = getIntent().getStringExtra("waptype");
        str = "";
        if (stringExtra == null || !stringExtra.equals("new_page")) {
            String stringExtra2 = getIntent().getStringExtra("wapurl");
            String stringExtra3 = getIntent().getStringExtra("paydata");
            if (stringExtra3 != null) {
                str = stringExtra2 + "?s=" + stringExtra3;
            }
            String str3 = com.unionpay.utils.h.a().e;
            mVar = new m(this);
            String str4 = str;
            str = str3;
            str2 = str4;
        } else {
            str2 = getIntent().getStringExtra("wapurl");
            String stringExtra4 = getIntent().getStringExtra("waptitle");
            if (str2 == null) {
                str2 = "";
            }
            str = stringExtra4 != null ? stringExtra4 : "";
            mVar = new i(this);
        }
        getWindow().requestFeature(1);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(1);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(layoutParams);
        int a = com.unionpay.utils.e.a(this, 10.0f);
        int a2 = com.unionpay.utils.e.a(this, 52.0f);
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, a2));
        relativeLayout.setBackgroundColor(-10705958);
        LinearLayout linearLayout2 = new LinearLayout(this);
        this.a = linearLayout2;
        linearLayout2.setPadding(a, a, a, a);
        this.a.setGravity(16);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(9, -1);
        layoutParams2.addRule(15, -1);
        layoutParams2.leftMargin = a;
        this.a.setOnClickListener(mVar);
        relativeLayout.addView(this.a, layoutParams2);
        int a3 = com.unionpay.utils.e.a(this, 20.0f);
        int a4 = com.unionpay.utils.e.a(this, 11.0f);
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundDrawable(new BitmapDrawable(BitmapFactory.decodeStream(getClass().getResourceAsStream("res/nav_back.png"))));
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(a4, a3);
        layoutParams3.addRule(15, -1);
        this.a.addView(imageView, layoutParams3);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(com.unionpay.utils.e.a(this, 320.0f), a2);
        layoutParams4.addRule(13, -1);
        TextView textView = new TextView(this);
        textView.setTextSize(20.0f);
        textView.setTextColor(-1);
        textView.setText(str);
        textView.setGravity(17);
        textView.setSingleLine(true);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        relativeLayout.addView(textView, layoutParams4);
        linearLayout.addView(relativeLayout);
        WebView webView = new WebView(this);
        this.b = webView;
        webView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        linearLayout.addView(this.b);
        setContentView(linearLayout);
        this.c = new WebViewJavascriptBridge(this, this.b, null);
        this.b.loadUrl(str2);
        this.c.registerHandler("getDeviceInfo", new p(this));
        this.c.registerHandler("saveData", new q(this));
        this.c.registerHandler("getData", new r(this));
        this.c.registerHandler("removeData", new s(this));
        this.c.registerHandler("setPageBackEnable", new t(this));
        this.c.registerHandler("payBySDK", new u(this));
        this.c.registerHandler("downloadApp", new v(this));
        this.c.registerHandler("payResult", new j(this));
        this.c.registerHandler("closePage", new k(this));
        this.c.registerHandler("openNewPage", new l(this));
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        onPause();
        return true;
    }
}
