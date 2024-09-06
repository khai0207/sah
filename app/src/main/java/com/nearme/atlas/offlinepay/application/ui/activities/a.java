package com.nearme.atlas.offlinepay.application.ui.activities;

/* loaded from: classes.dex */
final class a implements Runnable {
    final /* synthetic */ BaseActivity da;

    a(BaseActivity baseActivity) {
        this.da = baseActivity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.da.finish();
    }
}
