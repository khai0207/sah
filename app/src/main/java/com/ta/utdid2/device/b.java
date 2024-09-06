package com.ta.utdid2.device;

import android.content.Context;
import com.ta.utdid2.a.a.f;
import java.util.zip.Adler32;

/* loaded from: classes.dex */
public class b {
    public static a a;

    /* renamed from: a, reason: collision with other field name */
    public static final Object f8a = new Object();

    public static synchronized a b(Context context) {
        synchronized (b.class) {
            a aVar = a;
            if (aVar != null) {
                return aVar;
            }
            if (context == null) {
                return null;
            }
            a a2 = a(context);
            a = a2;
            return a2;
        }
    }

    public static long a(a aVar) {
        if (aVar == null) {
            return 0L;
        }
        String format = String.format("%s%s%s%s%s", aVar.e(), aVar.getDeviceId(), Long.valueOf(aVar.a()), aVar.getImsi(), aVar.d());
        if (f.m59a(format)) {
            return 0L;
        }
        Adler32 adler32 = new Adler32();
        adler32.reset();
        adler32.update(format.getBytes());
        return adler32.getValue();
    }

    public static a a(Context context) {
        if (context == null) {
            return null;
        }
        synchronized (f8a) {
            String value = c.a(context).getValue();
            if (f.m59a(value)) {
                return null;
            }
            if (value.endsWith("\n")) {
                value = value.substring(0, value.length() - 1);
            }
            a aVar = new a();
            long currentTimeMillis = System.currentTimeMillis();
            String a2 = com.ta.utdid2.a.a.d.a(context);
            String b = com.ta.utdid2.a.a.d.b(context);
            aVar.c(a2);
            aVar.a(a2);
            aVar.b(currentTimeMillis);
            aVar.b(b);
            aVar.d(value);
            aVar.a(a(aVar));
            return aVar;
        }
    }
}
