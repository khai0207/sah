package com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity;

/* loaded from: classes.dex */
final class i implements Runnable {
    final /* synthetic */ WeChatNotifyActivity at;

    i(WeChatNotifyActivity weChatNotifyActivity) {
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
            if (r0 != 0) goto L7d
            com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.WeChatNotifyActivity r0 = r6.at
            boolean r0 = com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.WeChatNotifyActivity.p(r0)
            if (r0 == 0) goto L11
            goto L7d
        L11:
            com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.WeChatNotifyActivity r0 = r6.at
            android.content.Context r1 = r0.getApplicationContext()
            java.lang.String r2 = "activity"
            java.lang.Object r1 = r1.getSystemService(r2)
            android.app.ActivityManager r1 = (android.app.ActivityManager) r1
            java.util.List r1 = r1.getRunningAppProcesses()
            r2 = 0
            if (r1 != 0) goto L27
            goto L49
        L27:
            java.util.Iterator r1 = r1.iterator()
        L2b:
            boolean r3 = r1.hasNext()
            if (r3 != 0) goto L32
            goto L49
        L32:
            java.lang.Object r3 = r1.next()
            android.app.ActivityManager$RunningAppProcessInfo r3 = (android.app.ActivityManager.RunningAppProcessInfo) r3
            int r4 = r3.importance
            r5 = 100
            if (r4 != r5) goto L2b
            java.lang.String r3 = r3.processName
            java.lang.String r4 = "com.tencent.mm"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L2b
            r2 = 1
        L49:
            com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.WeChatNotifyActivity.a(r0, r2)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "getRunningAppProcess = "
            r0.<init>(r1)
            com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.WeChatNotifyActivity r1 = r6.at
            boolean r1 = com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.WeChatNotifyActivity.o(r1)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.ipaynow.wechatpay.plugin.e.b.b(r0)
            com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.WeChatNotifyActivity r0 = r6.at
            boolean r0 = com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.WeChatNotifyActivity.o(r0)
            if (r0 != 0) goto L72
            r0 = 20
            java.lang.Thread.sleep(r0)     // Catch: java.lang.InterruptedException -> L71
            goto L0
        L71:
            return
        L72:
            com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.WeChatNotifyActivity r0 = r6.at
            com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.j r1 = new com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.j
            r1.<init>(r6)
            r0.runOnUiThread(r1)
            goto L0
        L7d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.i.run():void");
    }
}
