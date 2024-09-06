package com.netease.nimlib.push.b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.netease.nimlib.o.p;
import com.netease.nimlib.push.b.b;

/* compiled from: ConnectivityWatcher.java */
/* loaded from: classes.dex */
public class a {
    private InterfaceC0053a a;
    private Context b;
    private boolean c;
    private String d;
    private BroadcastReceiver e = new BroadcastReceiver() { // from class: com.netease.nimlib.push.b.a.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                NetworkInfo d = p.d(context);
                boolean z = d != null && d.isAvailable();
                String typeName = z ? d.getTypeName() : null;
                com.netease.nimlib.log.b.d("ConnectivityWatcher", "receive CONNECTIVITY_ACTION,isConnected = " + (d != null && d.isConnected()) + ",available = " + z + ",typeName = " + typeName);
                if (a.this.c != z) {
                    a.this.c = z;
                    a.this.d = typeName;
                    a.this.a(z);
                } else {
                    if (!a.this.c || typeName.equals(a.this.d)) {
                        return;
                    }
                    a.this.d = typeName;
                    a.this.a(b.a.NETWORK_CHANGE);
                }
            }
        }
    };

    /* compiled from: ConnectivityWatcher.java */
    /* renamed from: com.netease.nimlib.push.b.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0053a {
        void onNetworkEvent(b.a aVar);
    }

    public a(Context context, InterfaceC0053a interfaceC0053a) {
        this.b = context;
        this.a = interfaceC0053a;
    }

    public boolean a() {
        return this.c || p.c(this.b);
    }

    public boolean b() {
        return p.c(this.b);
    }

    public void c() {
        if (((ConnectivityManager) this.b.getSystemService("connectivity")) != null) {
            NetworkInfo d = p.d(this.b);
            boolean z = d != null && d.isAvailable();
            this.c = z;
            this.d = z ? d.getTypeName() : null;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        try {
            this.b.registerReceiver(this.e, intentFilter);
            com.netease.nimlib.log.b.d("ConnectivityWatcher", "registerReceiver");
        } catch (Throwable th) {
            com.netease.nimlib.log.b.c("ConnectivityWatcher", "registerReceiver error", th);
        }
    }

    public void d() {
        try {
            this.b.unregisterReceiver(this.e);
            com.netease.nimlib.log.b.d("ConnectivityWatcher", "unregisterReceiver");
        } catch (Throwable th) {
            com.netease.nimlib.log.b.c("ConnectivityWatcher", "unregisterReceiver error", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        if (z) {
            a(b.a.NETWORK_AVAILABLE);
        } else {
            a(b.a.NETWORK_UNAVAILABLE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(b.a aVar) {
        InterfaceC0053a interfaceC0053a = this.a;
        if (interfaceC0053a != null) {
            interfaceC0053a.onNetworkEvent(aVar);
        }
        if (this.c) {
            com.netease.nimlib.log.b.O("network type changed to: " + this.d);
        }
    }
}
