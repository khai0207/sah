package com.unionpay;

/* loaded from: classes.dex */
final class x implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ WebViewJavascriptBridge b;

    x(WebViewJavascriptBridge webViewJavascriptBridge, String str) {
        this.b = webViewJavascriptBridge;
        this.a = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.b.mWebView.loadUrl(this.a);
    }
}
