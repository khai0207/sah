package com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity;

import android.webkit.WebView;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes.dex */
final class b extends TimerTask {
    final /* synthetic */ a au;
    private final /* synthetic */ ArrayList av;

    b(a aVar, ArrayList arrayList) {
        this.au = aVar;
        this.av = arrayList;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        WeChatNotifyActivity weChatNotifyActivity;
        WebView webView;
        WeChatNotifyActivity weChatNotifyActivity2;
        Timer timer;
        WeChatNotifyActivity weChatNotifyActivity3;
        Timer timer2;
        WeChatNotifyActivity weChatNotifyActivity4;
        Timer timer3;
        WeChatNotifyActivity weChatNotifyActivity5;
        WeChatNotifyActivity weChatNotifyActivity6;
        Timer timer4;
        WeChatNotifyActivity weChatNotifyActivity7;
        Timer timer5;
        WeChatNotifyActivity weChatNotifyActivity8;
        Timer timer6;
        weChatNotifyActivity = this.au.at;
        webView = weChatNotifyActivity.aj;
        webView.post(new c(this, this.av));
        int intValue = ((Integer) this.av.get(0)).intValue();
        if (intValue < 100) {
            weChatNotifyActivity5 = this.au.at;
            weChatNotifyActivity5.runOnUiThread(new d(this));
            weChatNotifyActivity6 = this.au.at;
            timer4 = weChatNotifyActivity6.am;
            if (timer4 != null) {
                weChatNotifyActivity7 = this.au.at;
                timer5 = weChatNotifyActivity7.am;
                timer5.cancel();
                weChatNotifyActivity8 = this.au.at;
                timer6 = weChatNotifyActivity8.am;
                timer6.purge();
            }
        }
        if (intValue == 100) {
            weChatNotifyActivity2 = this.au.at;
            timer = weChatNotifyActivity2.am;
            if (timer != null) {
                weChatNotifyActivity3 = this.au.at;
                timer2 = weChatNotifyActivity3.am;
                timer2.cancel();
                weChatNotifyActivity4 = this.au.at;
                timer3 = weChatNotifyActivity4.am;
                timer3.purge();
            }
        }
    }
}
