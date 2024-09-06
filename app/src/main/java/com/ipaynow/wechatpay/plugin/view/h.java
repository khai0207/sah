package com.ipaynow.wechatpay.plugin.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import java.io.IOException;

/* loaded from: classes.dex */
public final class h {
    public static Drawable a(Context context, String str) {
        try {
            return Drawable.createFromStream(context.getAssets().open(str), null);
        } catch (IOException unused) {
            return null;
        }
    }
}
