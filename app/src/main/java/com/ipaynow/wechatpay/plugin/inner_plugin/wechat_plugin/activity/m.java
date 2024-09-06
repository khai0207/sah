package com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity;

import android.content.DialogInterface;

/* loaded from: classes.dex */
final class m implements DialogInterface.OnClickListener {
    final /* synthetic */ l aB;

    m(l lVar) {
        this.aB = lVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        int i2;
        String str;
        WeChatNotifyActivity weChatNotifyActivity = this.aB.at;
        i2 = weChatNotifyActivity.ah;
        weChatNotifyActivity.ah = i2 - 1;
        WeChatNotifyActivity weChatNotifyActivity2 = this.aB.at;
        str = this.aB.at.ag;
        weChatNotifyActivity2.c(str);
        this.aB.at.n();
    }
}
