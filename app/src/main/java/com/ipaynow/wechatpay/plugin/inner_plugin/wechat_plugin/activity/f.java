package com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity;

/* loaded from: classes.dex */
final class f implements Runnable {
    final /* synthetic */ e ay;

    f(e eVar) {
        this.ay = eVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        WeChatNotifyActivity weChatNotifyActivity;
        WeChatNotifyActivity weChatNotifyActivity2;
        com.ipaynow.wechatpay.plugin.manager.route.a.I();
        weChatNotifyActivity = this.ay.au.at;
        com.ipaynow.wechatpay.plugin.manager.route.a.a(weChatNotifyActivity, com.ipaynow.wechatpay.plugin.c.b.PE002.name(), "微信支付链接请求失败");
        com.ipaynow.wechatpay.plugin.manager.a.a.r().clearAll();
        weChatNotifyActivity2 = this.ay.au.at;
        weChatNotifyActivity2.ai = false;
    }
}
