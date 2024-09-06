package com.unionpay.mobile.android.hce;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.kqg.main.constant.KV;
import com.unionpay.tsmservice.data.Constant;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
final class g implements Handler.Callback {
    final /* synthetic */ f a;

    g(f fVar) {
        this.a = fVar;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        String str;
        ConcurrentHashMap concurrentHashMap;
        ConcurrentHashMap concurrentHashMap2;
        ConcurrentHashMap concurrentHashMap3;
        ConcurrentHashMap concurrentHashMap4;
        ConcurrentHashMap concurrentHashMap5;
        ConcurrentHashMap concurrentHashMap6;
        switch (message.what) {
            case KV.INIT_ERROR /* 2001 */:
                f.a(this.a);
                this.a.b();
                return false;
            case 2002:
                str = (String) message.obj;
                break;
            case 2003:
                Bundle bundle = (Bundle) message.obj;
                if (bundle == null) {
                    return false;
                }
                String string = bundle.getString("pkgName");
                boolean z = bundle.getBoolean(Constant.CASH_LOAD_SUCCESS);
                String string2 = bundle.getString("result");
                String string3 = bundle.getString("reserved");
                com.unionpay.mobile.android.utils.j.c("yitong", "result: " + string2);
                concurrentHashMap = this.a.f37u;
                d dVar = (d) concurrentHashMap.get(string);
                if (dVar == null) {
                    dVar = new d(string);
                }
                if (z) {
                    dVar.a(string2);
                    dVar.b(string3);
                }
                dVar.e();
                concurrentHashMap2 = this.a.f37u;
                concurrentHashMap2.put(string, dVar);
                f.a(this.a, string);
                return false;
            case 2004:
            default:
                return false;
            case 2005:
            case 2006:
                str = (String) message.obj;
                concurrentHashMap3 = this.a.f37u;
                d dVar2 = (d) concurrentHashMap3.get(str);
                concurrentHashMap4 = this.a.v;
                l lVar = (l) concurrentHashMap4.get(str);
                dVar2.f();
                concurrentHashMap5 = this.a.f37u;
                concurrentHashMap5.put(str, dVar2);
                lVar.f();
                concurrentHashMap6 = this.a.v;
                concurrentHashMap6.put(str, lVar);
                break;
        }
        f.a(this.a, str);
        return false;
    }
}
