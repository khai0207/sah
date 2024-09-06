package com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity;

/* loaded from: classes.dex */
final class e implements Runnable {
    final /* synthetic */ a au;
    private final /* synthetic */ String ax;

    e(a aVar, String str) {
        this.au = aVar;
        this.ax = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        WeChatNotifyActivity weChatNotifyActivity;
        String str;
        WeChatNotifyActivity weChatNotifyActivity2;
        int i;
        WeChatNotifyActivity weChatNotifyActivity3;
        String str2;
        WeChatNotifyActivity weChatNotifyActivity4;
        String str3;
        WeChatNotifyActivity weChatNotifyActivity5;
        StringBuilder sb = new StringBuilder("开始检测是否跳转 url = ");
        sb.append(this.ax);
        sb.append(" \n currentUrl = ");
        weChatNotifyActivity = this.au.at;
        str = weChatNotifyActivity.ag;
        sb.append(str);
        sb.append(" \nloadFlag = ");
        weChatNotifyActivity2 = this.au.at;
        i = weChatNotifyActivity2.al;
        sb.append(i);
        sb.append("\n output = ");
        weChatNotifyActivity3 = this.au.at;
        str2 = weChatNotifyActivity3.ac;
        sb.append(str2);
        com.ipaynow.wechatpay.plugin.e.b.b(sb.toString());
        String str4 = this.ax;
        weChatNotifyActivity4 = this.au.at;
        str3 = weChatNotifyActivity4.ag;
        if (str4.equals(str3)) {
            com.ipaynow.wechatpay.plugin.e.b.b("未跳转到weixin://");
            weChatNotifyActivity5 = this.au.at;
            weChatNotifyActivity5.runOnUiThread(new f(this));
        }
    }
}
