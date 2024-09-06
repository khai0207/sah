package com.talkingdata.sdk;

import android.content.Context;
import android.content.pm.Signature;
import com.unionpay.tsmservice.data.Constant;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: td */
/* loaded from: classes.dex */
public class cy extends da {
    private static HashMap a = new HashMap();
    private static HashMap c = new HashMap();
    private static volatile cy d = null;

    private cy() {
        a("displayName", am.a().h(ab.f));
        a("globalId", am.a().a(ab.f));
        a("versionName", ap.k());
        a("versionCode", Integer.valueOf(ap.j()));
        a("installTime", Long.valueOf(am.a().d(ab.f)));
        a("updateTime", Long.valueOf(am.a().e(ab.f)));
    }

    public void a(Object obj, a aVar) {
        a.put(aVar.b(), obj);
    }

    public void b(Object obj, a aVar) {
        c.put(aVar.b(), obj);
    }

    private ArrayList b() {
        ArrayList arrayList = new ArrayList();
        try {
            Iterator it = a.entrySet().iterator();
            while (it.hasNext()) {
                arrayList.add(a.a(((Map.Entry) it.next()).getKey().toString()));
            }
        } catch (Throwable unused) {
        }
        return arrayList;
    }

    public void setSubmitAppId(a aVar) {
        if (aVar != null) {
            try {
                Object obj = a.get(aVar.b());
                if (obj == null) {
                    obj = a.get(((a) b().get(0)).b());
                }
                a("appKey", obj);
            } catch (Throwable unused) {
            }
        }
    }

    public void setSubmitChannelId(a aVar) {
        if (aVar != null) {
            try {
                Object obj = c.get(aVar.b());
                if (obj == null) {
                    obj = c.get(((a) b().get(0)).b());
                }
                a(Constant.KEY_CHANNEL, obj);
                return;
            } catch (Throwable unused) {
                return;
            }
        }
        setAppChannel("Default");
    }

    public void setAppChannel(String str) {
        a(Constant.KEY_CHANNEL, str);
    }

    public void setUniqueId(String str) {
        a("uniqueId", str);
    }

    public static cy a() {
        if (d == null) {
            synchronized (cr.class) {
                if (d == null) {
                    d = new cy();
                }
            }
        }
        return d;
    }

    static byte[] a(Context context, String str) {
        try {
            Signature[] signatureArr = context.getPackageManager().getPackageInfo(str, 64).signatures;
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            byte[] byteArray = signatureArr[0].toByteArray();
            certificateFactory.generateCertificate(new ByteArrayInputStream(byteArray));
            return byteArray;
        } catch (Throwable unused) {
            return null;
        }
    }
}
