package com.talkingdata.sdk;

import java.util.Calendar;

/* compiled from: td */
/* loaded from: classes.dex */
final class cp implements Runnable {
    cp() {
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean d;
        try {
            if (az.c(ab.f)) {
                d = co.d();
                if (d) {
                    aq.a("https://i.tddmp.com/a/" + as.a(ab.f), "", false);
                    Calendar calendar = Calendar.getInstance();
                    bd.a(ab.f, ab.r, ab.f31u, (long) ((calendar.get(6) * 1000) + calendar.get(3)));
                }
            }
        } catch (Throwable unused) {
        }
    }
}
