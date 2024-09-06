package com.unionpay.mobile.android.nocard.utils;

import android.content.Context;
import android.text.TextUtils;
import com.unionpay.mobile.android.utils.PreferenceUtils;
import com.unionpay.mobile.android.views.order.l;

/* loaded from: classes.dex */
public final class c {
    public static int a(Context context, com.unionpay.mobile.android.model.b bVar) {
        Integer num;
        String b = PreferenceUtils.b(context);
        int intValue = l.b.intValue();
        if (!TextUtils.isEmpty(b) && TextUtils.isDigitsOnly(b)) {
            intValue = Integer.valueOf(b).intValue();
        }
        boolean equalsIgnoreCase = "0".equalsIgnoreCase(bVar.ai);
        if (bVar.ar && (bVar.aj & 69889) == 0) {
            equalsIgnoreCase = true;
        }
        if (equalsIgnoreCase) {
            num = l.a;
        } else {
            if (!TextUtils.isEmpty(bVar.p)) {
                intValue = l.b.intValue();
            }
            if (!f.a(bVar.o)) {
                return intValue;
            }
            num = l.b;
        }
        return num.intValue();
    }
}
