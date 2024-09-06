package com.unionpay.mobile.android.hce;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import com.unionpay.mobile.android.hce.service.a;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
final class i implements ServiceConnection {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ f c;

    i(f fVar, String str, String str2) {
        this.c = fVar;
        this.a = str;
        this.b = str2;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        com.unionpay.mobile.android.hce.service.a aVar;
        ConcurrentHashMap concurrentHashMap;
        ConcurrentHashMap concurrentHashMap2;
        Handler handler;
        Handler handler2;
        String str;
        String str2;
        Handler handler3;
        Handler handler4;
        Handler handler5;
        int i;
        String str3 = null;
        try {
            aVar = a.AbstractBinderC0066a.a(iBinder);
        } catch (Exception e) {
            e.printStackTrace();
            aVar = null;
        }
        if (aVar != null) {
            try {
                str = this.c.d;
                str2 = this.c.e;
                String str4 = this.a;
                handler3 = this.c.y;
                str3 = aVar.a(str, str2, new b(2003, str4, handler3));
                handler4 = this.c.y;
                Message obtainMessage = handler4.obtainMessage(2006, this.a);
                handler5 = this.c.y;
                i = this.c.i;
                handler5.sendMessageDelayed(obtainMessage, i);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            } catch (Exception unused) {
            }
            if (str3 != null) {
                com.unionpay.mobile.android.utils.j.a("uppay-hce", "session Key: " + str3);
                com.unionpay.mobile.android.utils.j.a("uppay-hce", "3des key: " + this.b);
                String a = a.a(str3, this.b);
                com.unionpay.mobile.android.utils.j.a("uppay-hce", this.a + " sessionkey after: " + a);
                concurrentHashMap = this.c.v;
                l lVar = (l) concurrentHashMap.get(this.a);
                if (lVar == null) {
                    lVar = new l(this.a);
                }
                lVar.a(a);
                lVar.a(aVar);
                lVar.e();
                concurrentHashMap2 = this.c.v;
                concurrentHashMap2.put(this.a, lVar);
                handler = this.c.y;
                Message obtainMessage2 = handler.obtainMessage(2002, this.a);
                handler2 = this.c.y;
                handler2.sendMessage(obtainMessage2);
            }
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        Handler handler;
        Handler handler2;
        handler = this.c.y;
        Message obtainMessage = handler.obtainMessage(2005, this.a);
        handler2 = this.c.y;
        handler2.sendMessage(obtainMessage);
    }
}
