package com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes.dex */
final class a extends WebViewClient {
    final /* synthetic */ WeChatNotifyActivity at;

    a(WeChatNotifyActivity weChatNotifyActivity) {
        this.at = weChatNotifyActivity;
    }

    @Override // android.webkit.WebViewClient
    public final void onPageFinished(WebView webView, String str) {
        Timer timer;
        Timer timer2;
        Timer timer3;
        super.onPageFinished(webView, str);
        com.ipaynow.wechatpay.plugin.e.b.b("pageFinished = " + str);
        timer = this.at.am;
        if (timer != null) {
            timer2 = this.at.am;
            timer2.cancel();
            timer3 = this.at.am;
            timer3.purge();
        }
        new Handler(Looper.getMainLooper()).postDelayed(new e(this, str), 400L);
    }

    @Override // android.webkit.WebViewClient
    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        Timer timer;
        TimerTask timerTask;
        super.onPageStarted(webView, str, bitmap);
        com.ipaynow.wechatpay.plugin.e.b.b("pageStarted = " + str);
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(0);
        this.at.am = new Timer();
        this.at.an = new b(this, arrayList);
        timer = this.at.am;
        timerTask = this.at.an;
        timer.schedule(timerTask, 10000L, 1L);
    }

    @Override // android.webkit.WebViewClient
    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        com.ipaynow.wechatpay.plugin.e.b.b("onReceivedError = errorCode = " + i + "  \n description = " + str + "  \n failingUrl = " + str2);
        super.onReceivedError(webView, i, str, str2);
    }

    @Override // android.webkit.WebViewClient
    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        boolean c;
        Map map;
        String str2;
        Map map2;
        com.ipaynow.wechatpay.plugin.e.b.b("shouldOverrideUrlLoading = " + str);
        this.at.ag = str;
        c = this.at.c(str);
        if (c) {
            return true;
        }
        this.at.ak = new HashMap();
        map = this.at.ak;
        str2 = this.at.af;
        map.put("Referer", str2);
        WeChatNotifyActivity weChatNotifyActivity = this.at;
        map2 = weChatNotifyActivity.ak;
        weChatNotifyActivity.a(webView, str, map2);
        return true;
    }
}
