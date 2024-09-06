package com.ipaynow.wechatpay.plugin.f;

import android.app.ProgressDialog;
import com.ipaynow.wechatpay.plugin.view.IpaynowLoading;

/* loaded from: classes.dex */
public abstract class a implements com.ipaynow.wechatpay.plugin.f.a.a {
    public ProgressDialog S;
    public IpaynowLoading T = null;
    public com.ipaynow.wechatpay.plugin.a.a.a bA;

    public a(com.ipaynow.wechatpay.plugin.a.a.a aVar) {
        this.S = null;
        this.bA = null;
        this.bA = aVar;
        this.S = null;
    }

    private void a(com.ipaynow.wechatpay.plugin.d.c.b bVar) {
        ProgressDialog progressDialog = this.S;
        if (progressDialog != null) {
            bVar.a(progressDialog);
            return;
        }
        IpaynowLoading ipaynowLoading = this.T;
        if (ipaynowLoading != null) {
            bVar.a(ipaynowLoading);
        }
    }

    public final void f(String str, String str2) {
        String d = com.ipaynow.wechatpay.plugin.manager.b.a.d(str, str2);
        com.ipaynow.wechatpay.plugin.e.b.b("发送的原文:" + d);
        com.ipaynow.wechatpay.plugin.d.c.b bVar = new com.ipaynow.wechatpay.plugin.d.c.b(this, 3);
        a(bVar);
        bVar.execute(d);
    }

    public final void h(String str) {
        com.ipaynow.wechatpay.plugin.d.c.b bVar = new com.ipaynow.wechatpay.plugin.d.c.b(this, 1);
        a(bVar);
        bVar.execute(str);
    }
}
