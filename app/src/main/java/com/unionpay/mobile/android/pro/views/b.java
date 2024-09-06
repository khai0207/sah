package com.unionpay.mobile.android.pro.views;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.unionpay.tsmservice.data.Constant;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
final class b implements Handler.Callback {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        Handler handler;
        com.unionpay.mobile.android.model.b bVar;
        com.unionpay.mobile.android.hce.c cVar;
        com.unionpay.mobile.android.model.b bVar2;
        com.unionpay.mobile.android.upviews.a aVar;
        com.unionpay.mobile.android.model.b bVar3;
        com.unionpay.mobile.android.model.b bVar4;
        int i = message.what;
        if (i != 2004) {
            if (i != 2006) {
                return true;
            }
            a aVar2 = this.a;
            bVar4 = aVar2.a;
            aVar2.a(bVar4.ak, false);
            return true;
        }
        handler = this.a.H;
        handler.removeMessages(2006);
        Bundle bundle = (Bundle) message.obj;
        if (bundle == null) {
            return true;
        }
        if (!bundle.getBoolean(Constant.CASH_LOAD_SUCCESS)) {
            a aVar3 = this.a;
            bVar = aVar3.a;
            aVar3.a(bVar.ak, false);
            return true;
        }
        String string = bundle.getString("result");
        cVar = this.a.C;
        try {
            if (a.d(new JSONObject(com.unionpay.mobile.android.hce.a.a(string, cVar.f())))) {
                a aVar4 = this.a;
                aVar = aVar4.x;
                a.a(aVar4, aVar.a().b, a.f(this.a));
                return true;
            }
            a aVar5 = this.a;
            bVar3 = aVar5.a;
            aVar5.a(bVar3.ak, false);
            return false;
        } catch (JSONException e) {
            a aVar6 = this.a;
            bVar2 = aVar6.a;
            aVar6.a(bVar2.ak, false);
            e.printStackTrace();
            return false;
        }
    }
}
