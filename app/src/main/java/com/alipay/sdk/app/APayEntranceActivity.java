package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import com.alipay.sdk.m.g.b;
import com.alipay.sdk.m.o.a;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class APayEntranceActivity extends Activity {
    public static final String d = "ap_order_info";
    public static final String e = "ap_target_packagename";
    public static final String f = "ap_session";
    public static final String g = "ap_local_info";
    public static final ConcurrentHashMap<String, a> h = new ConcurrentHashMap<>();
    public String a;
    public String b;
    public com.alipay.sdk.m.o.a c;

    /* loaded from: classes.dex */
    public interface a {
        void a(String str);
    }

    @Override // android.app.Activity
    public void finish() {
        a remove;
        String str = this.b;
        com.alipay.sdk.m.g.a.a(this.c, b.l, "BSAFinish", this.b + "|" + TextUtils.isEmpty(this.a));
        if (TextUtils.isEmpty(this.a)) {
            this.a = com.alipay.sdk.m.f.b.a();
        }
        if (str != null && (remove = h.remove(str)) != null) {
            remove.a(this.a);
        }
        try {
            super.finish();
        } catch (Throwable unused) {
        }
    }

    @Override // android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1000) {
            try {
                if (intent != null) {
                    this.a = intent.getStringExtra("result");
                } else {
                    this.a = com.alipay.sdk.m.f.b.a();
                }
            } catch (Throwable unused) {
                this.a = com.alipay.sdk.m.f.b.a();
            }
            finish();
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                finish();
                return;
            }
            String string = extras.getString(d);
            String string2 = extras.getString(e);
            this.b = extras.getString(f);
            String string3 = extras.getString(g, "{}");
            if (!TextUtils.isEmpty(this.b)) {
                com.alipay.sdk.m.o.a a2 = a.C0010a.a(this.b);
                this.c = a2;
                com.alipay.sdk.m.g.a.a(a2, b.l, "BSAEntryCreate", this.b + "|" + SystemClock.elapsedRealtime());
            }
            Intent intent = new Intent();
            intent.putExtra("order_info", string);
            intent.putExtra("localInfo", string3);
            intent.setClassName(string2, "com.alipay.android.app.flybird.ui.window.FlyBirdWindowActivity");
            try {
                startActivityForResult(intent, 1000);
            } catch (Throwable unused) {
                finish();
            }
        } catch (Throwable unused2) {
            finish();
        }
    }
}
