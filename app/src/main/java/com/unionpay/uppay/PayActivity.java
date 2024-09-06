package com.unionpay.uppay;

import android.content.Intent;
import android.nfc.NfcManager;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import com.unionpay.mobile.android.hce.f;
import com.unionpay.mobile.android.model.e;
import com.unionpay.mobile.android.nocard.utils.UPPayEngine;
import com.unionpay.mobile.android.pboctransaction.nfc.b;
import com.unionpay.mobile.android.plugin.BaseActivity;
import com.unionpay.mobile.android.pro.pboc.engine.b;
import com.unionpay.mobile.android.pro.views.a;
import com.unionpay.mobile.android.pro.views.j;
import com.unionpay.mobile.android.pro.views.k;
import com.unionpay.mobile.android.pro.views.u;
import com.unionpay.mobile.android.pro.views.w;
import com.unionpay.mobile.android.utils.n;

/* loaded from: classes.dex */
public final class PayActivity extends BaseActivity {
    public static String a;
    private n e;
    private b c = null;
    private f d = null;
    private k f = null;

    static {
        System.loadLibrary("entryexpro");
        a = "";
    }

    @Override // com.unionpay.mobile.android.plugin.a
    public final com.unionpay.mobile.android.nocard.views.b a(int i, e eVar) {
        com.unionpay.mobile.android.nocard.views.b bVar;
        if (i == 1) {
            j jVar = new j(this);
            jVar.a(a());
            return jVar;
        }
        if (i == 2) {
            return new u(this, eVar);
        }
        if (i == 6) {
            return new w(this, eVar);
        }
        if (i == 17) {
            k kVar = new k(this, eVar, (UPPayEngine) a(UPPayEngine.class.toString()));
            this.f = kVar;
            kVar.r = d();
            this.f.b(this.b.isEnabled());
            bVar = kVar;
        } else {
            if (i != 18) {
                return null;
            }
            bVar = new a(this, eVar, (UPPayEngine) a(UPPayEngine.class.toString()));
        }
        return bVar;
    }

    @Override // com.unionpay.mobile.android.plugin.BaseActivity
    public final Object a(String str) {
        if (b.class.toString().equalsIgnoreCase(str)) {
            if (this.c == null) {
                this.c = new b(this, d());
            }
            return this.c;
        }
        if (!f.class.toString().equalsIgnoreCase(str)) {
            return super.a(str);
        }
        if (this.d == null) {
            this.d = new f(this);
        }
        return this.d;
    }

    @Override // com.unionpay.mobile.android.plugin.BaseActivity
    public final boolean a() {
        return Build.VERSION.SDK_INT >= 10 && ((NfcManager) getSystemService("nfc")).getDefaultAdapter() != null;
    }

    @Override // com.unionpay.mobile.android.plugin.b
    public final UPPayEngine e() {
        n nVar = new n(this);
        this.e = nVar;
        return nVar;
    }

    @Override // com.unionpay.mobile.android.plugin.BaseActivity, android.app.Activity
    protected final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        k kVar = this.f;
        if (kVar == null || kVar.getParent() == null) {
            return;
        }
        this.f.b(this.b.isEnabled());
    }

    @Override // com.unionpay.mobile.android.plugin.BaseActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.unionpay.mobile.android.plugin.BaseActivity, android.app.Activity
    protected final void onDestroy() {
        super.onDestroy();
        b bVar = this.c;
        if (bVar != null) {
            bVar.a();
            this.c = null;
        }
        this.e.g();
        this.e = null;
        this.f = null;
        a = "";
    }

    @Override // android.app.Activity
    protected final void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Parcelable parcelableExtra = intent.getParcelableExtra("android.nfc.extra.TAG");
        if (parcelableExtra != null) {
            Log.d("NFCTAG", intent.getAction());
            b.C0069b c0069b = new b.C0069b(IsoDep.get((Tag) parcelableExtra));
            c0069b.a();
            com.unionpay.mobile.android.pboctransaction.nfc.a aVar = new com.unionpay.mobile.android.pboctransaction.nfc.a((com.unionpay.mobile.android.fully.a) a(UPPayEngine.class.toString()), c0069b);
            k kVar = this.f;
            if (kVar == null || kVar.getParent() == null) {
                return;
            }
            this.f.a(aVar);
        }
    }

    @Override // com.unionpay.mobile.android.plugin.BaseActivity, android.app.Activity
    protected final void onResume() {
        super.onResume();
        k kVar = this.f;
        if (kVar == null || kVar.getParent() == null) {
            return;
        }
        this.f.b(this.b.isEnabled());
    }
}
