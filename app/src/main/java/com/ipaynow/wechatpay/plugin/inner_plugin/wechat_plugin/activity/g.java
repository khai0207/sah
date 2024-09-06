package com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity;

/* loaded from: classes.dex */
final class g implements Runnable {
    final /* synthetic */ WeChatNotifyActivity at;

    g(WeChatNotifyActivity weChatNotifyActivity) {
        this.at = weChatNotifyActivity;
    }

    /* JADX WARN: Incorrect condition in loop: B:2:0x0006 */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void run() {
        /*
            r6 = this;
        L0:
            com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.WeChatNotifyActivity r0 = r6.at
            boolean r0 = com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.WeChatNotifyActivity.o(r0)
            if (r0 != 0) goto L75
            com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.WeChatNotifyActivity r0 = r6.at
            boolean r0 = com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.WeChatNotifyActivity.p(r0)
            if (r0 == 0) goto L11
            goto L75
        L11:
            com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.WeChatNotifyActivity r0 = r6.at
            java.lang.String r1 = "com.tencent.mm"
            android.content.Context r2 = r0.getApplicationContext()
            java.lang.String r3 = "activity"
            java.lang.Object r2 = r2.getSystemService(r3)
            android.app.ActivityManager r2 = (android.app.ActivityManager) r2
            r3 = 1
            java.util.List r2 = r2.getRunningTasks(r3)
            r4 = 0
            java.lang.Object r2 = r2.get(r4)
            android.app.ActivityManager$RunningTaskInfo r2 = (android.app.ActivityManager.RunningTaskInfo) r2
            android.content.ComponentName r2 = r2.topActivity
            boolean r5 = android.text.TextUtils.isEmpty(r1)
            if (r5 != 0) goto L40
            java.lang.String r2 = r2.getPackageName()
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L40
            goto L41
        L40:
            r3 = 0
        L41:
            com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.WeChatNotifyActivity.a(r0, r3)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "getRunningTask = "
            r0.<init>(r1)
            com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.WeChatNotifyActivity r1 = r6.at
            boolean r1 = com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.WeChatNotifyActivity.o(r1)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.ipaynow.wechatpay.plugin.e.b.b(r0)
            com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.WeChatNotifyActivity r0 = r6.at
            boolean r0 = com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.WeChatNotifyActivity.o(r0)
            if (r0 != 0) goto L6a
            r0 = 20
            java.lang.Thread.sleep(r0)     // Catch: java.lang.InterruptedException -> L69
            goto L0
        L69:
            return
        L6a:
            com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.WeChatNotifyActivity r0 = r6.at
            com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.h r1 = new com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.h
            r1.<init>(r6)
            r0.runOnUiThread(r1)
            goto L0
        L75:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.g.run():void");
    }
}
