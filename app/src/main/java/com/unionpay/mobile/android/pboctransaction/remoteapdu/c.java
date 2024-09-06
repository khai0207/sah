package com.unionpay.mobile.android.pboctransaction.remoteapdu;

import android.os.RemoteException;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.tsm.connect.IInitCallback;

/* loaded from: classes.dex */
final class c extends IInitCallback.Stub {
    final /* synthetic */ a a;

    c(a aVar) {
        this.a = aVar;
    }

    @Override // com.unionpay.mobile.tsm.connect.IInitCallback
    public final void initFailed() throws RemoteException {
        j.a("plugin-tsm", "mInitCallback.initFailed()");
        if (this.a.a != null) {
            this.a.a.b();
        }
    }

    @Override // com.unionpay.mobile.tsm.connect.IInitCallback
    public final void initSucceed() throws RemoteException {
        j.a("plugin-tsm", "mInitCallback.initSucceed()");
        if (this.a.a != null) {
            this.a.a.a();
        }
    }
}
