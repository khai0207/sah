package com.unionpay.mobile.android.pboctransaction.remoteapdu;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.tsm.connect.IInitCallback;
import com.unionpay.mobile.tsm.connect.IRemoteApdu;

/* loaded from: classes.dex */
final class b implements ServiceConnection {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        IRemoteApdu iRemoteApdu;
        IInitCallback.Stub stub;
        IRemoteApdu iRemoteApdu2;
        j.a("plugin-tsm", "mConnection.onServiceConnected()");
        try {
            this.a.b = IRemoteApdu.Stub.asInterface(iBinder);
            iRemoteApdu = this.a.b;
            stub = this.a.f;
            iRemoteApdu.registerCallback(stub);
            iRemoteApdu2 = this.a.b;
            iRemoteApdu2.init();
        } catch (Exception unused) {
            if (this.a.a != null) {
                this.a.a.b();
            }
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        j.a("plugin-tsm", "mConnection.onServiceDisconnected()");
        this.a.b = null;
        if (this.a.a != null) {
            this.a.a.b();
        }
    }
}
