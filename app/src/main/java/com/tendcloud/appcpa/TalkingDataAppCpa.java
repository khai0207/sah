package com.tendcloud.appcpa;

import android.content.Context;
import com.talkingdata.sdk.a;
import com.talkingdata.sdk.ac;
import com.unionpay.sdk.OttoBus;

/* compiled from: td */
/* loaded from: classes.dex */
public class TalkingDataAppCpa {
    public static boolean LOG_NO = true;
    public static String TAG = "TDLOG";
    public static String sdkversion = "2.3.8";

    public static final void init(Context context, String str, String str2) {
        ac.a(context, str, str2, a.d);
    }

    public static final void setVerboseLogDisable() {
        ac.setVerboseLogDisable(a.d);
    }

    public static final String getDeviceId(Context context) {
        return ac.d(context, a.d);
    }

    public static final void onRegister(String str) {
        ac.b(str, a.d);
    }

    public static final void onLogin(String str) {
        ac.a(str, a.d);
    }

    public static final void onCreateRole(String str) {
        ac.c(str, a.d);
    }

    public static final void onPay(String str, String str2, int i, String str3) {
        onPay(str, str2, i, str3, OttoBus.DEFAULT_IDENTIFIER);
    }

    public static final void onPay(String str, String str2, int i, String str3, String str4) {
        ac.a(str, str2, i, str3, str4, a.d);
    }

    public static final void onPay(String str, String str2, int i, String str3, String str4, Order order) {
        ac.a(str, str2, i, str3, str4, order, a.d);
    }

    public static final void onPay(String str, String str2, int i, String str3, String str4, String str5, int i2) {
        ac.a(str, str2, i, str3, str4, str5, i2, a.d);
    }

    public static final void onPlaceOrder(String str, Order order) {
        ac.a(str, order, a.d);
    }

    public static final void onOrderPaySucc(String str, String str2, int i, String str3, String str4) {
        ac.b(str, str2, i, str3, str4, a.d);
    }

    public static final void onAddItemToShoppingCart(String str, String str2, String str3, int i, int i2) {
        ac.a(str, str2, str3, i, i2, a.d);
    }

    public static final void onViewItem(String str, String str2, String str3, int i) {
        ac.a(str, str2, str3, i, a.d);
    }

    public static final void onViewShoppingCart(ShoppingCart shoppingCart) {
        ac.a(shoppingCart, a.d);
    }

    public static final void onReceiveDeepLink(String str) {
        ac.e(str, a.d);
    }
}
