package com.unionpay.mobile.android.upviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.netease.nimlib.amazonaws.services.s3.util.Mimetypes;
import java.util.ArrayList;
import java.util.Timer;

/* loaded from: classes.dex */
public final class b extends WebView implements Handler.Callback {
    private WebSettings a;
    private Handler b;
    private a c;
    private Timer d;
    private boolean e;
    private ArrayList<String> f;

    /* loaded from: classes.dex */
    public interface a {
        void t();

        void u();
    }

    /* renamed from: com.unionpay.mobile.android.upviews.b$b, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0072b extends a {
        void c(String str);
    }

    /* loaded from: classes.dex */
    private class c extends WebChromeClient {
        private c() {
        }

        /* synthetic */ c(b bVar, byte b) {
            this();
        }

        @Override // android.webkit.WebChromeClient
        public final void onProgressChanged(WebView webView, int i) {
            if (i == 100) {
                b.this.b.sendEmptyMessage(1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    class d extends WebViewClient {
        private d() {
        }

        /* synthetic */ d(b bVar, byte b) {
            this();
        }

        @Override // android.webkit.WebViewClient
        public final void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            b.this.d.cancel();
            b.this.d.purge();
        }

        @Override // android.webkit.WebViewClient
        public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            b.this.d = new Timer();
            b.this.d.schedule(new com.unionpay.mobile.android.upviews.c(this), 30000L);
        }

        @Override // android.webkit.WebViewClient
        public final void onReceivedError(WebView webView, int i, String str, String str2) {
            b.this.a();
        }

        @Override // android.webkit.WebViewClient
        public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
            String str2 = null;
            if (b.this.f != null && b.this.f.size() != 0 && str != null && str.length() > 0) {
                int i = 0;
                while (true) {
                    if (b.this.f == null || i >= b.this.f.size()) {
                        break;
                    }
                    if (str.startsWith((String) b.this.f.get(i))) {
                        str2 = (String) b.this.f.get(i);
                        break;
                    }
                    i++;
                }
            }
            if (str2 == null) {
                webView.loadUrl(str);
                return true;
            }
            Message obtainMessage = b.this.b.obtainMessage(4);
            obtainMessage.obj = str;
            b.this.b.sendMessage(obtainMessage);
            return true;
        }
    }

    public b(Context context, a aVar) {
        super(context);
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = new Timer();
        byte b = 0;
        this.e = false;
        this.f = null;
        this.b = new Handler(this);
        this.c = aVar;
        setScrollBarStyle(33554432);
        WebSettings settings = getSettings();
        this.a = settings;
        settings.setJavaScriptEnabled(true);
        setWebChromeClient(new c(this, b));
        setWebViewClient(new d(this, b));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a() {
        loadData(String.format("<div align=\"center\">%s</div>", "&#x7F51;&#x9875;&#x52A0;&#x8F7D;&#x5931;&#x8D25;&#xFF0C;&#x8BF7;&#x91CD;&#x8BD5;"), Mimetypes.MIMETYPE_HTML, "utf-8");
    }

    public final void a(String str) {
        if (this.f == null) {
            this.f = new ArrayList<>();
        }
        if (str == null || str.length() <= 0) {
            return;
        }
        this.f.add(str);
    }

    public final void b(String str) {
        Message obtainMessage = this.b.obtainMessage(0);
        obtainMessage.obj = str;
        this.b.sendMessage(obtainMessage);
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        a aVar;
        int i = message.what;
        if (i != 0) {
            if (i != 1 && i != 2) {
                if (i == 3) {
                    a();
                } else if (i == 4 && (aVar = this.c) != null && (aVar instanceof InterfaceC0072b)) {
                    ((InterfaceC0072b) aVar).c((String) message.obj);
                }
            }
            if (message.what == 1) {
                this.e = true;
            }
            a aVar2 = this.c;
            if (aVar2 != null) {
                aVar2.u();
            }
        } else {
            a aVar3 = this.c;
            if (aVar3 != null) {
                aVar3.t();
            }
            String str = message.obj != null ? (String) message.obj : "";
            Log.e("uppay", "url = " + str);
            loadUrl(str);
        }
        return true;
    }
}
