package com.ipaynow.wechatpay.plugin.view;

import android.content.Context;

/* loaded from: classes.dex */
final class i {
    private static float cs;

    public static int a(float f, Context context) {
        if (cs == 0.0f) {
            cs = context.getResources().getDisplayMetrics().density;
        }
        return (int) (f * cs);
    }
}
