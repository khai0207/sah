package com.unionpay;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/* loaded from: classes.dex */
final class aa extends WebViewClient {
    final /* synthetic */ WebViewJavascriptBridge a;

    private aa(WebViewJavascriptBridge webViewJavascriptBridge) {
        this.a = webViewJavascriptBridge;
    }

    /* synthetic */ aa(WebViewJavascriptBridge webViewJavascriptBridge, byte b) {
        this(webViewJavascriptBridge);
    }

    @Override // android.webkit.WebViewClient
    public final void onPageFinished(WebView webView, String str) {
        com.unionpay.utils.g.a("test", "onPageFinished");
    }
}
