package com.unionpay.mobile.android.plugin;

import android.R;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.nfc.NfcAdapter;
import android.nfc.tech.IsoDep;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import com.unionpay.mobile.android.nocard.utils.UPPayEngine;
import com.unionpay.mobile.android.nocard.views.l;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.android.widgets.m;
import com.unionpay.sdk.UPAgent;
import java.util.ArrayList;

/* loaded from: classes.dex */
public abstract class BaseActivity extends Activity implements com.unionpay.mobile.android.plugin.a, b {
    public static IntentFilter[] FILTERS;
    public static String[][] TECHLISTS;
    private static int f;
    protected NfcAdapter b;
    private PendingIntent g;
    private ArrayList<com.unionpay.mobile.android.nocard.views.b> a = null;
    private l c = null;
    private a d = null;
    private m e = null;

    /* loaded from: classes.dex */
    private class a {
        public com.unionpay.mobile.android.model.b a;
        public UPPayEngine b;

        public a(UPPayEngine uPPayEngine) {
            this.a = null;
            this.b = null;
            com.unionpay.mobile.android.model.b bVar = new com.unionpay.mobile.android.model.b();
            this.a = bVar;
            this.b = uPPayEngine;
            uPPayEngine.a(bVar);
        }
    }

    static {
        try {
            TECHLISTS = new String[][]{new String[]{IsoDep.class.getName()}, new String[]{NfcV.class.getName()}, new String[]{NfcF.class.getName()}};
            FILTERS = new IntentFilter[]{new IntentFilter("android.nfc.action.TECH_DISCOVERED", "*/*")};
        } catch (Exception unused) {
        }
    }

    public Object a(String str) {
        if (str == null) {
            return this.d.a;
        }
        if (str.equalsIgnoreCase(UPPayEngine.class.toString())) {
            return this.d.b;
        }
        if (str.equalsIgnoreCase(m.class.toString())) {
            return this.e;
        }
        return null;
    }

    public final void a(int i) {
        int size = this.a.size() - 1;
        for (int i2 = size; i2 >= 0; i2--) {
            com.unionpay.mobile.android.nocard.views.b bVar = this.a.get(i2);
            if (bVar.h() == i) {
                bVar.r();
                setContentView(bVar);
                return;
            } else {
                if (i2 == size) {
                    bVar.s();
                }
                this.a.remove(i2);
            }
        }
    }

    public final void a(com.unionpay.mobile.android.nocard.views.b bVar) {
        int size = this.a.size();
        if (size > 0) {
            this.a.get(size - 1).s();
        }
        bVar.r();
        this.a.add(bVar);
        setContentView(bVar);
    }

    public boolean a() {
        return false;
    }

    public final void b() {
        int size = this.a.size();
        if (size > 0) {
            int i = size - 1;
            this.a.get(i).s();
            this.a.remove(i);
            if (this.a.size() != 0) {
                this.a.get(r0.size() - 1).r();
                setContentView(this.a.get(r0.size() - 1));
            }
        }
    }

    public final void c() {
        ArrayList<com.unionpay.mobile.android.nocard.views.b> arrayList = this.a;
        if (arrayList != null) {
            arrayList.clear();
        }
        l lVar = this.c;
        if (lVar != null) {
            lVar.A();
        }
        this.c = null;
        com.unionpay.mobile.android.languages.c.bD = null;
        com.unionpay.mobile.android.model.b.bf = false;
        com.unionpay.mobile.android.model.b.aW = null;
        com.unionpay.mobile.android.model.b.bg = false;
        int i = f - 1;
        f = i;
        if (i == 0) {
            com.unionpay.mobile.android.resource.c.a(this).a();
        }
        this.e.c();
        this.e = null;
        this.d.b = null;
        this.d.a = null;
        this.d = null;
        ((ViewGroup) getWindow().getDecorView().findViewById(R.id.content)).removeAllViews();
    }

    public final String d() {
        return this.d.a.a;
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        l lVar = this.c;
        if (lVar != null) {
            lVar.x();
        }
        super.onActivityResult(i, i2, intent);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        j.a("uppay", "PayActivityEx.onCreate() +++");
        com.unionpay.mobile.android.languages.c.a();
        com.unionpay.mobile.android.global.a.a(this);
        this.a = new ArrayList<>(1);
        this.d = new a(e());
        this.e = new m(this);
        UPAgent.LOG_ON = false;
        requestWindowFeature(1);
        super.onCreate(bundle);
        l lVar = (l) a(1, null);
        this.c = lVar;
        setContentView(lVar);
        getWindow().addFlags(8192);
        f++;
        j.a("uppay", "PayActivityEx.onCreate() ---");
        if (a()) {
            this.b = NfcAdapter.getDefaultAdapter(this);
            this.g = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(536870912), 0);
            onNewIntent(getIntent());
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        if (this.a.size() > 0) {
            ArrayList<com.unionpay.mobile.android.nocard.views.b> arrayList = this.a;
            arrayList.get(arrayList.size() - 1).l();
        }
        return true;
    }

    @Override // android.app.Activity
    protected void onPause() {
        NfcAdapter nfcAdapter;
        super.onPause();
        if (!a() || (nfcAdapter = this.b) == null) {
            return;
        }
        nfcAdapter.disableForegroundDispatch(this);
    }

    @Override // android.app.Activity
    protected void onResume() {
        NfcAdapter nfcAdapter;
        super.onResume();
        if (this.e.a()) {
            this.e.b();
        }
        if (!a() || (nfcAdapter = this.b) == null) {
            return;
        }
        nfcAdapter.enableForegroundDispatch(this, this.g, FILTERS, TECHLISTS);
    }
}
