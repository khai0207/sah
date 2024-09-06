package com.unionpay;

/* loaded from: classes.dex */
final class w implements Runnable {
    final /* synthetic */ ab a;
    final /* synthetic */ String b;
    final /* synthetic */ ac c;
    final /* synthetic */ WebViewJavascriptBridge d;

    w(WebViewJavascriptBridge webViewJavascriptBridge, ab abVar, String str, ac acVar) {
        this.d = webViewJavascriptBridge;
        this.a = abVar;
        this.b = str;
        this.c = acVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ab abVar = this.a;
        if (abVar != null) {
            abVar.a(this.b, this.c);
        }
    }
}
