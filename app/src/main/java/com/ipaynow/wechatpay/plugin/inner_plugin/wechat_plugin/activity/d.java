package com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity;

/* loaded from: classes.dex */
final class d implements Runnable {
    final /* synthetic */ b aw;

    d(b bVar) {
        this.aw = bVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        a aVar;
        WeChatNotifyActivity weChatNotifyActivity;
        a aVar2;
        WeChatNotifyActivity weChatNotifyActivity2;
        com.ipaynow.wechatpay.plugin.manager.route.a.I();
        aVar = this.aw.au;
        weChatNotifyActivity = aVar.at;
        com.ipaynow.wechatpay.plugin.manager.route.a.a(weChatNotifyActivity, com.ipaynow.wechatpay.plugin.c.b.PE002.name(), com.ipaynow.wechatpay.plugin.c.b.PE002.d());
        com.ipaynow.wechatpay.plugin.manager.a.a.r().clearAll();
        aVar2 = this.aw.au;
        weChatNotifyActivity2 = aVar2.at;
        weChatNotifyActivity2.ai = false;
    }
}
