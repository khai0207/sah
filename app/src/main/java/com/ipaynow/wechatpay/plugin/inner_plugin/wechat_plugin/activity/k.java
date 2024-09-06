package com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity;

import com.ipaynow.wechatpay.plugin.view.IpaynowLoading;

/* loaded from: classes.dex */
class k extends com.ipaynow.wechatpay.plugin.d.c.b.a {
    final /* synthetic */ WeChatNotifyActivity at;

    k(WeChatNotifyActivity weChatNotifyActivity) {
        this.at = weChatNotifyActivity;
    }

    @Override // com.ipaynow.wechatpay.plugin.d.c.b.a
    public void c(com.ipaynow.wechatpay.plugin.d.c.a.a aVar) {
        super.c(aVar);
    }

    @Override // com.ipaynow.wechatpay.plugin.d.c.b.a
    public final void d(com.ipaynow.wechatpay.plugin.d.c.a.a aVar) {
        IpaynowLoading ipaynowLoading;
        WeChatNotifyActivity weChatNotifyActivity = this.at;
        if (weChatNotifyActivity != null) {
            ipaynowLoading = weChatNotifyActivity.loading;
            ipaynowLoading.dismiss();
        }
        this.at.ai = false;
        com.ipaynow.wechatpay.plugin.manager.route.a.I();
        com.ipaynow.wechatpay.plugin.manager.route.a.a(this.at, aVar.errorCode, aVar.respMsg);
        com.ipaynow.wechatpay.plugin.manager.a.a.r().clearAll();
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x005b, code lost:
    
        if (com.netease.nimlib.amazonaws.services.s3.internal.Constants.NULL_VERSION_ID.equals(r0) != false) goto L20;
     */
    @Override // com.ipaynow.wechatpay.plugin.d.c.b.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void e(com.ipaynow.wechatpay.plugin.d.c.a.a r3) {
        /*
            r2 = this;
            java.util.HashMap r3 = r3.Z     // Catch: java.lang.Exception -> L6a
            com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.WeChatNotifyActivity r0 = r2.at     // Catch: java.lang.Exception -> L6a
            java.lang.String r1 = "tn"
            java.lang.Object r1 = r3.get(r1)     // Catch: java.lang.Exception -> L6a
            java.lang.String r1 = (java.lang.String) r1     // Catch: java.lang.Exception -> L6a
            com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.WeChatNotifyActivity.b(r0, r1)     // Catch: java.lang.Exception -> L6a
            com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.WeChatNotifyActivity r0 = r2.at     // Catch: java.lang.Exception -> L6a
            java.lang.String r1 = "appId"
            java.lang.Object r1 = r3.get(r1)     // Catch: java.lang.Exception -> L6a
            java.lang.String r1 = (java.lang.String) r1     // Catch: java.lang.Exception -> L6a
            com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.WeChatNotifyActivity.c(r0, r1)     // Catch: java.lang.Exception -> L6a
            com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.WeChatNotifyActivity r0 = r2.at     // Catch: java.lang.Exception -> L6a
            java.lang.String r1 = "mhtOrderNo"
            java.lang.Object r1 = r3.get(r1)     // Catch: java.lang.Exception -> L6a
            java.lang.String r1 = (java.lang.String) r1     // Catch: java.lang.Exception -> L6a
            com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.WeChatNotifyActivity.d(r0, r1)     // Catch: java.lang.Exception -> L6a
            com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.WeChatNotifyActivity r0 = r2.at     // Catch: java.lang.Exception -> L6a
            java.lang.String r1 = "respOutputType"
            java.lang.Object r1 = r3.get(r1)     // Catch: java.lang.Exception -> L6a
            java.lang.String r1 = (java.lang.String) r1     // Catch: java.lang.Exception -> L6a
            com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.WeChatNotifyActivity.e(r0, r1)     // Catch: java.lang.Exception -> L6a
            com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.WeChatNotifyActivity r0 = r2.at     // Catch: java.lang.Exception -> L6a
            java.lang.String r1 = "referer"
            java.lang.Object r3 = r3.get(r1)     // Catch: java.lang.Exception -> L6a
            java.lang.String r3 = (java.lang.String) r3     // Catch: java.lang.Exception -> L6a
            com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.WeChatNotifyActivity.f(r0, r3)     // Catch: java.lang.Exception -> L6a
            com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.WeChatNotifyActivity r3 = r2.at     // Catch: java.lang.Exception -> L6a
            java.lang.String r3 = com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.WeChatNotifyActivity.h(r3)     // Catch: java.lang.Exception -> L6a
            boolean r3 = com.ipaynow.wechatpay.plugin.utils.g.isEmpty(r3)     // Catch: java.lang.Exception -> L6a
            if (r3 != 0) goto L5d
            java.lang.String r3 = "null"
            com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.WeChatNotifyActivity r0 = r2.at     // Catch: java.lang.Exception -> L6a
            java.lang.String r0 = com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.WeChatNotifyActivity.h(r0)     // Catch: java.lang.Exception -> L6a
            boolean r3 = r3.equals(r0)     // Catch: java.lang.Exception -> L6a
            if (r3 == 0) goto L64
        L5d:
            com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.WeChatNotifyActivity r3 = r2.at     // Catch: java.lang.Exception -> L6a
            java.lang.String r0 = "https://pay.ipaynow.cn"
            com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.WeChatNotifyActivity.f(r3, r0)     // Catch: java.lang.Exception -> L6a
        L64:
            com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.WeChatNotifyActivity r3 = r2.at     // Catch: java.lang.Exception -> L6a
            r3.l()     // Catch: java.lang.Exception -> L6a
            return
        L6a:
            r3 = move-exception
            r3.printStackTrace()
            java.lang.Thread.currentThread()
            com.ipaynow.wechatpay.plugin.d.b.a.a(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.k.e(com.ipaynow.wechatpay.plugin.d.c.a.a):void");
    }

    @Override // com.ipaynow.wechatpay.plugin.d.c.b.a
    public void j() {
        IpaynowLoading ipaynowLoading;
        WeChatNotifyActivity weChatNotifyActivity = this.at;
        if (weChatNotifyActivity != null) {
            ipaynowLoading = weChatNotifyActivity.loading;
            ipaynowLoading.dismiss();
        }
        this.at.ai = false;
        com.ipaynow.wechatpay.plugin.manager.route.a.I();
        com.ipaynow.wechatpay.plugin.manager.route.a.a(this.at, com.ipaynow.wechatpay.plugin.c.b.PE002.name(), com.ipaynow.wechatpay.plugin.c.b.PE002.d());
        com.ipaynow.wechatpay.plugin.manager.a.a.r().clearAll();
    }
}
