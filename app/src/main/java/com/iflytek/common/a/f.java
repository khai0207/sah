package com.iflytek.common.a;

import android.content.Context;

/* loaded from: classes.dex */
final class f implements Runnable {
    final /* synthetic */ Context a;

    f(Context context) {
        this.a = context;
    }

    @Override // java.lang.Runnable
    public final void run() {
        c.c(this.a);
    }
}
