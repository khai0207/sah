package com.unionpay.sdk;

import android.util.Log;

/* loaded from: classes.dex */
final class al {
    static void a(String str) {
        if (UPAgent.LOG_ON) {
            Log.i("unionpayLog", str);
        }
    }

    static void a(String str, Throwable th) {
        if (UPAgent.LOG_ON) {
            Log.e("unionpayLog", str, th);
        }
    }

    static void a(Throwable th) {
        if (r.a) {
            th.printStackTrace();
        }
    }

    static void a(String... strArr) {
        if (r.a) {
            StringBuilder sb = new StringBuilder();
            for (String str : strArr) {
                sb.append("\t");
                sb.append(str);
            }
            Log.i("unionpayLog", sb.toString().trim());
        }
    }

    static void b(String str) {
        if (UPAgent.LOG_ON) {
            Log.e("unionpayLog", str);
        }
    }

    static void b(String... strArr) {
        if (r.a) {
            StringBuilder sb = new StringBuilder();
            for (String str : strArr) {
                sb.append("\t");
                sb.append(str);
            }
            Log.d("unionpayLog", sb.toString().trim());
        }
    }
}
