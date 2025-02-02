package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import com.alipay.sdk.m.f.b;
import com.alipay.sdk.m.o.a;
import com.alipay.sdk.m.q.d;
import com.alipay.sdk.m.q.m;
import com.alipay.sdk.m.s.c;
import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class H5PayActivity extends Activity {
    public c a;
    public String b;
    public String c;
    public String d;
    public String e;
    public boolean f;
    public String g;
    public WeakReference<a> h;

    private void b() {
        try {
            super.requestWindowFeature(1);
            getWindow().addFlags(8192);
        } catch (Throwable th) {
            d.a(th);
        }
    }

    public void a() {
        Object obj = PayTask.h;
        synchronized (obj) {
            try {
                obj.notify();
            } catch (Exception unused) {
            }
        }
    }

    @Override // android.app.Activity
    public void finish() {
        a();
        super.finish();
    }

    @Override // android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1010) {
            com.alipay.sdk.m.f.d.a((a) m.a(this.h), i, i2, intent);
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        c cVar = this.a;
        if (cVar == null) {
            finish();
            return;
        }
        if (cVar.a()) {
            cVar.b();
            return;
        }
        if (!cVar.b()) {
            super.onBackPressed();
        }
        b.a(b.a());
        finish();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        b();
        super.onCreate(bundle);
        try {
            a a = a.C0010a.a(getIntent());
            if (a == null) {
                finish();
                return;
            }
            this.h = new WeakReference<>(a);
            if (!com.alipay.sdk.m.i.a.x().t()) {
                setRequestedOrientation(1);
            } else {
                setRequestedOrientation(3);
            }
            try {
                Bundle extras = getIntent().getExtras();
                String string = extras.getString(Constants.URL_ENCODING, null);
                this.b = string;
                if (!m.f(string)) {
                    finish();
                    return;
                }
                this.d = extras.getString("cookie", null);
                this.c = extras.getString("method", null);
                this.e = extras.getString(com.alipay.sdk.m.s.d.v, null);
                this.g = extras.getString("version", c.c);
                this.f = extras.getBoolean("backisexit", false);
                try {
                    com.alipay.sdk.m.s.d dVar = new com.alipay.sdk.m.s.d(this, a, this.g);
                    setContentView(dVar);
                    dVar.a(this.e, this.c, this.f);
                    dVar.a(this.b, this.d);
                    dVar.a(this.b);
                    this.a = dVar;
                } catch (Throwable th) {
                    com.alipay.sdk.m.g.a.a(a, com.alipay.sdk.m.g.b.l, "GetInstalledAppEx", th);
                    finish();
                }
            } catch (Exception unused) {
                finish();
            }
        } catch (Exception unused2) {
            finish();
        }
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        c cVar = this.a;
        if (cVar != null) {
            cVar.c();
        }
    }

    @Override // android.app.Activity
    public void setRequestedOrientation(int i) {
        try {
            super.setRequestedOrientation(i);
        } catch (Throwable th) {
            try {
                com.alipay.sdk.m.g.a.a((a) m.a(this.h), com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.z, th);
            } catch (Throwable unused) {
            }
        }
    }
}
