package com.nearme.atlas.offlinepay.application.ui.activities;

import android.app.Activity;
import android.os.Handler;

/* loaded from: classes.dex */
public abstract class BaseActivity extends Activity {
    public void finishDelay(int i) {
        new Handler().postDelayed(new a(this), i);
    }
}
