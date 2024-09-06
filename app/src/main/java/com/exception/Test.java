package com.exception;

import android.app.Activity;
import android.os.Bundle;
import com.bluemobi.exception.ExceptionHandler;

/* loaded from: classes.dex */
public class Test extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.main2);
        System.out.println(getIntent().getStringExtra(ExceptionHandler.getKey()));
    }
}
