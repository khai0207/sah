package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import com.alipay.sdk.m.g.b;
import com.alipay.sdk.m.o.a;
import com.alipay.sdk.m.q.k;
import com.iflytek.speech.TextUnderstanderAidl;
import com.unionpay.tsmservice.data.Constant;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class AlipayResultActivity extends Activity {
    public static final ConcurrentHashMap<String, a> a = new ConcurrentHashMap<>();

    /* loaded from: classes.dex */
    public interface a {
        void a(int i, String str, String str2);
    }

    private void a(String str, Bundle bundle) {
        a remove = a.remove(str);
        if (remove == null) {
            return;
        }
        try {
            remove.a(bundle.getInt("endCode"), bundle.getString(k.b), bundle.getString("result"));
        } finally {
            finish();
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        Throwable th;
        JSONObject jSONObject;
        Bundle bundle2;
        super.onCreate(bundle);
        try {
            Intent intent = getIntent();
            try {
                String stringExtra = intent.getStringExtra("session");
                Bundle bundleExtra = intent.getBundleExtra("result");
                String stringExtra2 = intent.getStringExtra(TextUnderstanderAidl.SCENE);
                com.alipay.sdk.m.o.a a2 = a.C0010a.a(stringExtra);
                if (a2 == null) {
                    finish();
                    return;
                }
                com.alipay.sdk.m.g.a.a(a2, b.l, "BSPSession", stringExtra + "|" + SystemClock.elapsedRealtime());
                if (TextUtils.equals("mqpSchemePay", stringExtra2)) {
                    a(stringExtra, bundleExtra);
                    return;
                }
                if ((TextUtils.isEmpty(stringExtra) || bundleExtra == null) && intent.getData() != null) {
                    try {
                        JSONObject jSONObject2 = new JSONObject(new String(Base64.decode(intent.getData().getQuery(), 2), "UTF-8"));
                        jSONObject = jSONObject2.getJSONObject("result");
                        stringExtra = jSONObject2.getString("session");
                        com.alipay.sdk.m.g.a.a(a2, b.l, "BSPUriSession", stringExtra);
                        bundle2 = new Bundle();
                    } catch (Throwable th2) {
                        th = th2;
                    }
                    try {
                        Iterator<String> keys = jSONObject.keys();
                        while (keys.hasNext()) {
                            String next = keys.next();
                            bundle2.putString(next, jSONObject.getString(next));
                        }
                        bundleExtra = bundle2;
                    } catch (Throwable th3) {
                        th = th3;
                        bundleExtra = bundle2;
                        com.alipay.sdk.m.g.a.a(a2, b.l, "BSPResEx", th);
                        com.alipay.sdk.m.g.a.a(a2, b.l, b.n0, th);
                        if (TextUtils.isEmpty(stringExtra)) {
                        }
                        com.alipay.sdk.m.g.a.b(this, a2, "", a2.d);
                        finish();
                    }
                }
                if (TextUtils.isEmpty(stringExtra) && bundleExtra != null) {
                    try {
                        com.alipay.sdk.m.g.a.a(a2, b.l, b.T, "" + SystemClock.elapsedRealtime());
                        com.alipay.sdk.m.g.a.a(a2, b.l, b.U, bundleExtra.getInt("endCode", -1) + "|" + bundleExtra.getString(k.b, "-"));
                        OpenAuthTask.a(stringExtra, OpenAuthTask.OK, Constant.STRING_CONFIRM_BUTTON, bundleExtra);
                        com.alipay.sdk.m.g.a.b(this, a2, "", a2.d);
                        finish();
                        return;
                    } catch (Throwable th4) {
                        com.alipay.sdk.m.g.a.b(this, a2, "", a2.d);
                        finish();
                        throw th4;
                    }
                }
                com.alipay.sdk.m.g.a.b(this, a2, "", a2.d);
                finish();
            } catch (Throwable th5) {
                com.alipay.sdk.m.g.a.a((com.alipay.sdk.m.o.a) null, b.l, "BSPSerError", th5);
                com.alipay.sdk.m.g.a.a((com.alipay.sdk.m.o.a) null, b.l, b.m0, th5);
                finish();
            }
        } catch (Throwable unused) {
            finish();
        }
    }
}
