package com.unionpay.sdk;

/* loaded from: classes.dex */
final class am {
    static String a() {
        if (ad.c == null) {
            return null;
        }
        return an.b(ad.c, "unionpaypref_longtime", "unionpayaes_key");
    }

    static void a(String str) {
        if (ad.c == null) {
            return;
        }
        an.a(ad.c, "unionpaypref_longtime", "unionpayisAppQuiting", str);
    }

    static long b() {
        if (ad.c == null) {
            return 0L;
        }
        return an.a(ad.c, "unionpaypref_longtime", "unionpaypref.init.key");
    }

    static String c() {
        if (ad.c == null) {
            return null;
        }
        return an.b(ad.c, "unionpaypref_longtime", "unionpayisAppQuiting");
    }
}
