package com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity;

import android.content.DialogInterface;

/* loaded from: classes.dex */
final class n implements DialogInterface.OnClickListener {
    final /* synthetic */ l aB;

    n(l lVar) {
        this.aB = lVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        this.aB.at.m();
        com.ipaynow.wechatpay.plugin.manager.route.a.I();
        com.ipaynow.wechatpay.plugin.manager.route.a.c(this.aB.at);
        com.ipaynow.wechatpay.plugin.manager.a.a.r().clearAll();
        this.aB.at.ai = false;
    }
}
