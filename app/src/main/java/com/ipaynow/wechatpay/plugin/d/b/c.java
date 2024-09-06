package com.ipaynow.wechatpay.plugin.d.b;

/* loaded from: classes.dex */
final class c implements Runnable {
    private final /* synthetic */ String J;

    c(String str) {
        this.J = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        a.a(com.ipaynow.wechatpay.plugin.d.a.b.a(com.ipaynow.wechatpay.plugin.c.e.e(), this.J.getBytes()));
    }
}
