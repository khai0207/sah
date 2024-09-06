package com.alipay.sdk.m.f;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.alipay.sdk.m.i.a;
import com.alipay.sdk.m.q.m;
import com.unionpay.tsmservice.data.Constant;
import java.util.Collections;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class d {
    public static final int a = 1010;
    public static a b;

    /* loaded from: classes.dex */
    public interface a {
        void a(boolean z, JSONObject jSONObject, String str);
    }

    public static boolean a(com.alipay.sdk.m.o.a aVar, Context context) {
        return m.a(aVar, context, Collections.singletonList(new a.b("com.taobao.taobao", 0, "")), false);
    }

    public static boolean a(com.alipay.sdk.m.o.a aVar, Activity activity, int i, String str, String str2, a aVar2) {
        try {
            com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.p0);
            activity.startActivityForResult(new Intent(str2, Uri.parse(str)), i);
            b = aVar2;
            return true;
        } catch (Throwable th) {
            aVar2.a(false, null, "UNKNOWN_ERROR");
            com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.t0, th);
            return false;
        }
    }

    public static boolean a(com.alipay.sdk.m.o.a aVar, int i, int i2, Intent intent) {
        if (i != 1010 || intent == null) {
            return false;
        }
        a aVar2 = b;
        if (aVar2 == null) {
            return true;
        }
        b = null;
        if (i2 != -1) {
            if (i2 != 0) {
                com.alipay.sdk.m.g.a.b(aVar, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.r0, "" + i2);
            } else {
                com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.q0, intent != null ? intent.toUri(1) : "");
                aVar2.a(false, null, "CANCELED");
            }
        } else {
            com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.s0, intent.toUri(1));
            aVar2.a(true, m.a(intent), Constant.STRING_CONFIRM_BUTTON);
        }
        return true;
    }
}
