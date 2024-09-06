package com.unionpay;

/* loaded from: classes.dex */
final class y implements ac {
    final /* synthetic */ WebViewJavascriptBridge a;
    private final String b;

    public y(WebViewJavascriptBridge webViewJavascriptBridge, String str) {
        this.a = webViewJavascriptBridge;
        this.b = str;
    }

    @Override // com.unionpay.ac
    public final void a(String str) {
        this.a._callbackJs(this.b, str);
    }
}
