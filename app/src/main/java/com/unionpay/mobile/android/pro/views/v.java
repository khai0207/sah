package com.unionpay.mobile.android.pro.views;

import android.os.Handler;
import android.os.Message;
import com.unionpay.tsmservice.data.Constant;

/* loaded from: classes.dex */
final class v implements Handler.Callback {
    final /* synthetic */ u a;

    v(u uVar) {
        this.a = uVar;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        com.unionpay.mobile.android.model.b bVar;
        com.unionpay.mobile.android.model.b bVar2;
        com.unionpay.mobile.android.model.b bVar3;
        int i = message.what;
        if (i == 2000) {
            if (message.obj == null) {
                u uVar = this.a;
                bVar = uVar.a;
                uVar.a(bVar.ak, false);
                return true;
            }
            com.unionpay.mobile.android.model.a aVar = (com.unionpay.mobile.android.model.a) message.obj;
            if (aVar == null) {
                return true;
            }
            this.a.a(aVar);
            return true;
        }
        if (i != 2001) {
            return true;
        }
        if ("1003100020".equalsIgnoreCase((String) message.obj)) {
            if (!com.unionpay.mobile.android.model.b.bg) {
                return true;
            }
            this.a.u();
            return true;
        }
        if (com.unionpay.mobile.android.model.b.bg) {
            u uVar2 = this.a;
            bVar3 = uVar2.a;
            uVar2.d(bVar3.ak, Constant.CASH_LOAD_FAIL);
            return true;
        }
        u uVar3 = this.a;
        bVar2 = uVar3.a;
        uVar3.a(bVar2.ak, false);
        return true;
    }
}
