package com.unionpay.mobile.android.hce;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import com.unionpay.mobile.android.hce.service.b;
import com.unionpay.tsmservice.data.Constant;

/* loaded from: classes.dex */
public final class b extends b.a {
    private int a;
    private String b;
    private Handler c;

    public b(int i, String str, Handler handler) {
        this.a = i;
        this.b = str;
        this.c = handler;
    }

    @Override // com.unionpay.mobile.android.hce.service.b
    public final void a(String str) throws RemoteException {
        int i = this.a;
        if (i == 2003) {
            Bundle bundle = new Bundle();
            bundle.putString("pkgName", this.b);
            bundle.putBoolean(Constant.CASH_LOAD_SUCCESS, false);
            bundle.putString("errCode", str);
            return;
        }
        if (i != 2004) {
            return;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putBoolean(Constant.CASH_LOAD_SUCCESS, false);
        bundle2.putString("errCode", str);
        Handler handler = this.c;
        handler.sendMessage(Message.obtain(handler, 2004, bundle2));
    }

    @Override // com.unionpay.mobile.android.hce.service.b
    public final void a(String str, String str2) throws RemoteException {
        int i = this.a;
        if (i == 2003) {
            Bundle bundle = new Bundle();
            bundle.putString("pkgName", this.b);
            bundle.putBoolean(Constant.CASH_LOAD_SUCCESS, true);
            bundle.putString("result", str);
            bundle.putString("reserved", str2);
            Handler handler = this.c;
            handler.sendMessage(Message.obtain(handler, 2003, bundle));
            return;
        }
        if (i != 2004) {
            return;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putBoolean(Constant.CASH_LOAD_SUCCESS, true);
        bundle2.putString("result", str);
        bundle2.putString("reserved", str2);
        Handler handler2 = this.c;
        handler2.sendMessage(Message.obtain(handler2, 2004, bundle2));
    }
}
