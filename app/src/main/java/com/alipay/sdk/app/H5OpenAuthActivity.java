package com.alipay.sdk.app;

import android.content.Intent;
import android.net.Uri;
import com.alipay.sdk.m.g.b;
import com.alipay.sdk.m.o.a;
import com.netease.nimlib.amazonaws.services.s3.internal.Constants;

/* loaded from: classes.dex */
public class H5OpenAuthActivity extends H5PayActivity {
    public boolean i = false;

    @Override // com.alipay.sdk.app.H5PayActivity
    public void a() {
    }

    @Override // com.alipay.sdk.app.H5PayActivity, android.app.Activity
    public void onDestroy() {
        if (this.i) {
            try {
                a a = a.C0010a.a(getIntent());
                if (a != null) {
                    com.alipay.sdk.m.g.a.b(this, a, "", a.d);
                }
            } catch (Throwable unused) {
            }
        }
        super.onDestroy();
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent) {
        try {
            a a = a.C0010a.a(intent);
            try {
                super.startActivity(intent);
                Uri data = intent != null ? intent.getData() : null;
                if (data == null || !data.toString().startsWith("alipays://platformapi/startapp")) {
                    return;
                }
                finish();
            } catch (Throwable th) {
                String uri = (intent == null || intent.getData() == null) ? Constants.NULL_VERSION_ID : intent.getData().toString();
                if (a != null) {
                    com.alipay.sdk.m.g.a.a(a, b.l, b.k0, th, uri);
                }
                this.i = true;
                throw th;
            }
        } catch (Throwable unused) {
            finish();
        }
    }
}
