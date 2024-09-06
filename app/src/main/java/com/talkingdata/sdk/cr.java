package com.talkingdata.sdk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* compiled from: td */
/* loaded from: classes.dex */
public class cr {
    static byte[] d;
    static HashMap a = new HashMap();
    static HashMap b = new HashMap();
    static String c = "utf-8";
    private static volatile cr e = null;

    public static void a() {
    }

    static {
        try {
            bk.a().register(b());
        } catch (Throwable unused) {
        }
    }

    public final synchronized void onTDEBEventDataStore(cn cnVar) {
        if (cnVar == null) {
            return;
        }
        try {
            if (cnVar.a == null || !cnVar.a.b().equals("APP_SQL")) {
                if (cnVar.a != null && cnVar.a.b().equals("ENV")) {
                    cx cxVar = new cx(cnVar.b, cnVar.c);
                    cxVar.setData(cnVar.d);
                    ct.a().a(new cs(bh.a(dm.a().a(cxVar, true, cnVar.a).toString().getBytes(), d)), cnVar);
                } else if (cnVar.a == null || !cnVar.a.b().equals("BG")) {
                    cx cxVar2 = new cx(cnVar.b, cnVar.c);
                    cxVar2.setData(cnVar.d);
                    ct.a().a(new cs(bh.a(dm.a().a(cxVar2, true, cnVar.a).toString().getBytes(), d)), cnVar);
                }
            }
        } catch (Throwable th) {
            try {
                ce.postSDKError(th);
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    public synchronized List a(a aVar) {
        ArrayList arrayList;
        arrayList = null;
        try {
            List a2 = ct.a().a(aVar, 100);
            if (a2.size() > 0) {
                ArrayList arrayList2 = new ArrayList();
                try {
                    Iterator it = a2.iterator();
                    while (it.hasNext()) {
                        try {
                            arrayList2.add(new String(bh.b((byte[]) it.next(), d)));
                        } catch (Throwable unused) {
                        }
                    }
                    arrayList = arrayList2;
                } catch (Throwable th) {
                    th = th;
                    arrayList = arrayList2;
                    ce.postSDKError(th);
                    return arrayList;
                }
            }
        } catch (Throwable th2) {
            th = th2;
        }
        return arrayList;
    }

    public void sendMessageSuccess(a aVar) {
        try {
            ct.a().confirmRead(aVar);
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    public static cr b() {
        if (e == null) {
            synchronized (cr.class) {
                if (e == null) {
                    e = new cr();
                }
            }
        }
        return e;
    }

    private cr() {
        String c2 = bh.c(ab.f.getPackageName());
        if (ab.f != null && c2 != null) {
            d = c2.getBytes();
        } else {
            d = ab.class.getSimpleName().getBytes();
        }
    }
}
