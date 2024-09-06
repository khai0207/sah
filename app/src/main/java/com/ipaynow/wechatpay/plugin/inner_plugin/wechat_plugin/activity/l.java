package com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity;

import android.app.AlertDialog;

/* loaded from: classes.dex */
final class l extends k {
    final /* synthetic */ WeChatNotifyActivity at;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    l(WeChatNotifyActivity weChatNotifyActivity) {
        super(weChatNotifyActivity);
        this.at = weChatNotifyActivity;
    }

    @Override // com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.k, com.ipaynow.wechatpay.plugin.d.c.b.a
    public final void c(com.ipaynow.wechatpay.plugin.d.c.a.a aVar) {
        super.c(aVar);
        this.at.m();
    }

    @Override // com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.k, com.ipaynow.wechatpay.plugin.d.c.b.a
    public final void e(com.ipaynow.wechatpay.plugin.d.c.a.a aVar) {
        AlertDialog alertDialog;
        com.ipaynow.wechatpay.plugin.e.b.b("handleSuccess");
        String str = (String) aVar.Z.get("tradeStatus");
        if ("A001".equals(str)) {
            this.at.m();
            com.ipaynow.wechatpay.plugin.manager.route.a.I();
            com.ipaynow.wechatpay.plugin.manager.route.a.d(this.at);
            com.ipaynow.wechatpay.plugin.manager.a.a.r().clearAll();
            this.at.ai = false;
            return;
        }
        if (!"A003".equals(str) && !"A004".equals(str)) {
            this.at.m();
            com.ipaynow.wechatpay.plugin.manager.route.a.I();
            com.ipaynow.wechatpay.plugin.manager.route.a.b(this.at, str, "查询失败");
            com.ipaynow.wechatpay.plugin.manager.a.a.r().clearAll();
            this.at.ai = false;
            return;
        }
        if (!com.ipaynow.wechatpay.plugin.c.f.y) {
            this.at.m();
            com.ipaynow.wechatpay.plugin.manager.route.a.I();
            com.ipaynow.wechatpay.plugin.manager.route.a.c(this.at);
            com.ipaynow.wechatpay.plugin.manager.a.a.r().clearAll();
            this.at.ai = false;
            return;
        }
        if (this.at.isFinishing()) {
            return;
        }
        this.at.ap = new com.ipaynow.wechatpay.plugin.view.d(this.at, "提示", "是否继续完成微信支付?", new m(this), new n(this)).create();
        this.at.o();
        alertDialog = this.at.ap;
        alertDialog.show();
    }

    @Override // com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.k, com.ipaynow.wechatpay.plugin.d.c.b.a
    public final void j() {
        this.at.m();
        com.ipaynow.wechatpay.plugin.manager.route.a.I();
        com.ipaynow.wechatpay.plugin.manager.route.a.a(this.at, com.ipaynow.wechatpay.plugin.c.b.PE002.name(), "微信交易查询超时");
        com.ipaynow.wechatpay.plugin.manager.a.a.r().clearAll();
        this.at.ai = false;
    }
}
