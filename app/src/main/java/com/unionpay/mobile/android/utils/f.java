package com.unionpay.mobile.android.utils;

import android.content.Context;

/* loaded from: classes.dex */
public final class f {
    public static int a(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
