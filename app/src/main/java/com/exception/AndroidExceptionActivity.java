package com.exception;

import android.app.Activity;
import android.os.Bundle;

/* loaded from: classes.dex */
public class AndroidExceptionActivity extends Activity {
    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.main);
        int i = 100 / 0;
    }
}
