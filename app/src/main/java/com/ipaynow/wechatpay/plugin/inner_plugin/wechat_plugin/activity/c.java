package com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity;

import android.webkit.WebView;
import java.util.ArrayList;

/* loaded from: classes.dex */
final class c implements Runnable {
    private final /* synthetic */ ArrayList av;
    final /* synthetic */ b aw;

    c(b bVar, ArrayList arrayList) {
        this.aw = bVar;
        this.av = arrayList;
    }

    @Override // java.lang.Runnable
    public final void run() {
        a aVar;
        WeChatNotifyActivity weChatNotifyActivity;
        WebView webView;
        ArrayList arrayList = this.av;
        aVar = this.aw.au;
        weChatNotifyActivity = aVar.at;
        webView = weChatNotifyActivity.aj;
        arrayList.add(Integer.valueOf(webView.getProgress()));
    }
}
