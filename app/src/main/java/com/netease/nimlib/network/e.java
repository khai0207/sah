package com.netease.nimlib.network;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;
import com.netease.nimlib.network.NetworkBroadcastReceiver;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: NetworkListenerHelper.java */
/* loaded from: classes.dex */
public class e {
    private Context b;
    private ConnectivityManager.NetworkCallback d;
    private NetworkBroadcastReceiver e;
    private final String a = "NetworkListenerHelper";
    private CopyOnWriteArrayList<com.netease.nimlib.network.a> c = null;

    public e(Context context) {
        this.b = context.getApplicationContext();
    }

    public void a() {
        try {
            if (Build.VERSION.SDK_INT >= 24) {
                ConnectivityManager connectivityManager = (ConnectivityManager) this.b.getSystemService("connectivity");
                if (connectivityManager == null) {
                    com.netease.nimlib.log.b.f("NetworkListenerHelper", "registerNetworkListener#return#connectivityManager=" + connectivityManager);
                    return;
                }
                if (this.d == null) {
                    this.d = new a();
                }
                connectivityManager.registerDefaultNetworkCallback(this.d);
                return;
            }
            if (this.e == null) {
                this.e = new NetworkBroadcastReceiver();
            }
            this.b.registerReceiver(this.e, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            this.e.a(new NetworkBroadcastReceiver.a() { // from class: com.netease.nimlib.network.-$$Lambda$e$60wGmq5xOg4bKX0Uni6Y52-QbsM
                @Override // com.netease.nimlib.network.NetworkBroadcastReceiver.a
                public final void onNetworkBroadcastCallback(boolean z, com.netease.nimlib.network.a.a aVar) {
                    e.this.a(z, aVar);
                }
            });
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("NetworkListenerHelper", "registerNetworkListener exception", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z, com.netease.nimlib.network.a.a aVar) {
        com.netease.nimlib.log.b.d("NetworkListenerHelper", "notifyAllListeners isConnected=" + z + ",networkStatus=" + aVar);
        if (com.netease.nimlib.o.f.d(this.c)) {
            try {
                Iterator<com.netease.nimlib.network.a> it = this.c.iterator();
                while (it.hasNext()) {
                    com.netease.nimlib.network.a next = it.next();
                    if (next != null) {
                        next.onNetworkChanged(z, aVar);
                    }
                }
            } catch (Throwable th) {
                com.netease.nimlib.log.b.e("NetworkListenerHelper", "notifyAllListeners exception", th);
            }
        }
    }

    public synchronized void a(com.netease.nimlib.network.a aVar) {
        if (aVar == null) {
            return;
        }
        if (this.c == null) {
            this.c = new CopyOnWriteArrayList<>();
        }
        if (!this.c.contains(aVar)) {
            this.c.add(aVar);
        }
    }

    /* compiled from: NetworkListenerHelper.java */
    /* loaded from: classes.dex */
    private class a extends ConnectivityManager.NetworkCallback {
        private com.netease.nimlib.network.a.a b;
        private com.netease.nimlib.network.a.a c;
        private Network d;
        private boolean e;

        private a() {
            this.b = com.netease.nimlib.network.a.a.NONE;
            this.c = com.netease.nimlib.network.a.a.NONE;
            this.d = null;
            this.e = false;
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network network) {
            super.onAvailable(network);
            com.netease.nimlib.log.b.d("NetworkListenerHelper", "onAvailable#network=" + network + ", netWorkState=" + this.b);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network network) {
            super.onLost(network);
            com.netease.nimlib.log.b.d("NetworkListenerHelper", "onLost#network=" + network + ", netWorkState=" + this.b);
            if (!this.e) {
                com.netease.nimlib.log.b.d("NetworkListenerHelper", "onLost，isCurrentConnected=false，return");
            } else {
                this.e = false;
                e.this.a(false, this.b);
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            this.d = network;
            super.onCapabilitiesChanged(network, networkCapabilities);
            this.c = this.b;
            if (networkCapabilities.hasTransport(1)) {
                this.b = com.netease.nimlib.network.a.a.WIFI;
            } else if (networkCapabilities.hasTransport(0)) {
                this.b = com.netease.nimlib.network.a.a.MOBILE;
            } else {
                this.b = com.netease.nimlib.network.a.a.UNKNOWN;
            }
            com.netease.nimlib.log.b.d("NetworkListenerHelper", "onCapabilitiesChanged#network=" + network + ",lastNetworkStatus=" + this.c + ",netWorkState=" + this.b + ", capabilities=" + networkCapabilities);
            if (networkCapabilities.hasCapability(16)) {
                com.netease.nimlib.log.b.d("NetworkListenerHelper", "onCapabilitiesChanged#network=" + network + ",netWorkState=" + this.b + ", capabilities=" + networkCapabilities);
                if (this.e && this.c == this.b) {
                    com.netease.nimlib.log.b.d("NetworkListenerHelper", "onCapabilitiesChanged，isCurrentConnected=true and lastNetworkStatus == currentNetworkStatus，return");
                } else {
                    this.e = true;
                    e.this.a(true, this.b);
                }
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
            super.onLinkPropertiesChanged(network, linkProperties);
            com.netease.nimlib.log.b.d("NetworkListenerHelper", "onLinkPropertiesChanged#network=" + network + ", linkProperties=" + linkProperties);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onUnavailable() {
            super.onUnavailable();
            com.netease.nimlib.log.b.d("NetworkListenerHelper", "onUnavailable#network=" + this.d + ", netWorkState=" + this.b);
            this.d = null;
            this.c = this.b;
            this.b = com.netease.nimlib.network.a.a.NONE;
        }
    }
}
