package com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity;

import android.R;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import com.ipaynow.wechatpay.plugin.view.IpaynowLoading;
import com.nearme.atlas.offlinepay.application.ui.activities.BaseActivity;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes.dex */
public class WeChatNotifyActivity extends BaseActivity implements com.ipaynow.wechatpay.plugin.a.a.a {
    private Timer am;
    private TimerTask an;
    private boolean aa = false;
    private boolean ab = false;
    private String appId = null;
    private String mhtOrderNo = null;
    private String ac = null;
    private String ad = null;
    private String ae = null;
    private String af = null;
    private String ag = null;
    private int ah = 0;
    private boolean ai = false;
    private WebView aj = null;
    private Map ak = null;
    private int al = 0;
    private final int ao = 10000;
    private IpaynowLoading loading = null;
    private AlertDialog ap = null;
    private Bundle aq = null;
    private Thread ar = null;
    private com.ipaynow.wechatpay.plugin.f.b as = null;

    /* JADX INFO: Access modifiers changed from: private */
    public void a(WebView webView, String str, Map map) {
        if (map == null) {
            webView.loadUrl(str);
        } else {
            webView.loadUrl(str, map);
        }
        this.al++;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(String str) {
        Uri parse = Uri.parse(str);
        if (com.ipaynow.wechatpay.plugin.utils.g.y(str) || !"weixin".equals(parse.getScheme())) {
            return false;
        }
        try {
            startActivity(new Intent("android.intent.action.VIEW", parse));
            this.ai = true;
            return true;
        } catch (ActivityNotFoundException unused) {
            this.ai = false;
            if (!isFinishing()) {
                m();
                com.ipaynow.wechatpay.plugin.manager.route.a.I();
                com.ipaynow.wechatpay.plugin.manager.route.a.a(this, com.ipaynow.wechatpay.plugin.c.b.PE007.name(), "微信 未安装");
                com.ipaynow.wechatpay.plugin.manager.a.a.r().clearAll();
                this.ai = false;
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        n();
        o();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        if (this.ap == null || isFinishing()) {
            return;
        }
        this.ap.dismiss();
    }

    @Override // com.ipaynow.wechatpay.plugin.a.a.a
    public final void a(com.ipaynow.wechatpay.plugin.d.c.a.a aVar) {
        com.ipaynow.wechatpay.plugin.d.c.b.a.a kVar;
        com.ipaynow.wechatpay.plugin.e.b.b(aVar);
        int i = aVar.W;
        if (i == 1) {
            kVar = new k(this);
        } else {
            if (i != 3) {
                com.ipaynow.wechatpay.plugin.manager.route.a.I();
                com.ipaynow.wechatpay.plugin.manager.route.a.a(this, com.ipaynow.wechatpay.plugin.c.b.PE002.name(), com.ipaynow.wechatpay.plugin.c.b.PE002.d());
                return;
            }
            kVar = new l(this);
        }
        kVar.b(aVar);
    }

    public final void l() {
        this.ag = this.ad;
        this.loading.setLoadingMsg("正在加载微信");
        this.loading.show();
        WebView webView = new WebView(this);
        this.aj = webView;
        webView.getSettings().setJavaScriptEnabled(true);
        this.aj.setLayerType(1, null);
        this.aj.setVisibility(8);
        setContentView(this.aj);
        if (c(this.ad)) {
            return;
        }
        HashMap hashMap = new HashMap();
        this.ak = hashMap;
        hashMap.put("Referer", this.af);
        a(this.aj, this.ad, this.ak);
        this.aj.setWebViewClient(new a(this));
    }

    public final void o() {
        com.ipaynow.wechatpay.plugin.e.b.b("closeLoading");
        if (this.loading == null || isFinishing()) {
            return;
        }
        this.loading.dismiss();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        com.ipaynow.wechatpay.plugin.e.b.b("onBackPressed");
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        if (bundle != null) {
            this.aq = bundle;
        } else {
            this.aq = getIntent().getExtras();
        }
        com.ipaynow.wechatpay.plugin.e.b.b("进入微信通知页面");
        super.onCreate(bundle);
        try {
            com.ipaynow.wechatpay.plugin.manager.a.a.r().a(this);
            this.loading = com.ipaynow.wechatpay.plugin.manager.a.a.r().w() == null ? new com.ipaynow.wechatpay.plugin.view.f(this) : com.ipaynow.wechatpay.plugin.manager.a.a.r().w();
            if (this.loading instanceof com.ipaynow.wechatpay.plugin.view.f) {
                com.ipaynow.wechatpay.plugin.manager.a.a.r().a((com.ipaynow.wechatpay.plugin.view.f) this.loading);
            }
            this.loading.setLoadingMsg("安全环境扫描");
            this.loading.show();
            this.aa = false;
            this.ai = false;
            this.ae = this.aq.getString("requestParams");
            requestWindowFeature(1);
            setTheme(R.style.Theme.Holo.InputMethod);
            com.ipaynow.wechatpay.plugin.f.b bVar = new com.ipaynow.wechatpay.plugin.f.b(this);
            this.as = bVar;
            bVar.i(this.ae);
            if (Build.VERSION.SDK_INT > 21) {
                Thread thread = new Thread(new g(this));
                this.ar = thread;
                thread.start();
            } else {
                Thread thread2 = new Thread(new i(this));
                this.ar = thread2;
                thread2.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Thread.currentThread();
            com.ipaynow.wechatpay.plugin.d.b.a.a(e);
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        com.ipaynow.wechatpay.plugin.f.b bVar = this.as;
        if (bVar != null) {
            bVar.bA = null;
        }
        WebView webView = this.aj;
        if (webView != null) {
            webView.destroy();
        }
        AlertDialog alertDialog = this.ap;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        IpaynowLoading ipaynowLoading = this.loading;
        if (ipaynowLoading != null) {
            ipaynowLoading.dismiss();
        }
        this.aj = null;
        this.loading = null;
        this.ap = null;
        this.as = null;
        com.ipaynow.wechatpay.plugin.manager.a.a.r().a(false);
        super.onDestroy();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 3) {
            return super.onKeyDown(i, keyEvent);
        }
        com.ipaynow.wechatpay.plugin.e.b.b("点击了HOME");
        return true;
    }

    @Override // android.app.Activity
    protected void onPause() {
        com.ipaynow.wechatpay.plugin.e.b.b("onPause");
        super.onPause();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        com.ipaynow.wechatpay.plugin.e.b.b("onResume");
        if (!this.aa || this.ai) {
            int i = this.ah + 1;
            this.ah = i;
            if (i % 2 == 0) {
                this.loading.setLoadingMsg("交易查询中");
                this.loading.show();
                this.as.g(this.appId, this.mhtOrderNo);
                this.ai = false;
            }
        }
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        bundle.putString("requestParams", this.ae);
        super.onSaveInstanceState(bundle);
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        com.ipaynow.wechatpay.plugin.e.b.b("微信通知Activity结束");
        this.aa = true;
    }
}
