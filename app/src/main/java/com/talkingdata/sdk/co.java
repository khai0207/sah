package com.talkingdata.sdk;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import com.talkingdata.sdk.cm;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.zip.CRC32;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

/* compiled from: td */
/* loaded from: classes.dex */
public class co {
    private static String a = "v1";
    private static String b = "utf-8";
    private static final int d = 5;
    private static final int e = 120000;
    private static final int f = 30000;
    private static final int g = 30000;
    private static final boolean j = true;
    private static HandlerThread l;
    private long h = 0;
    private boolean i = false;
    private Handler m;
    private static final CRC32 c = new CRC32();
    private static volatile co k = null;

    static {
        try {
            bk.a().register(b());
        } catch (Throwable unused) {
        }
    }

    public void a() {
        if (this.m.hasMessages(5)) {
            return;
        }
        Random random = new Random();
        int i = 30000;
        if (az.g(ab.f)) {
            if (!this.i) {
                i = 30000 + random.nextInt(30000);
            }
        } else {
            i = !this.i ? (random.nextInt(60000) - 30000) + 120000 : 120000;
        }
        Iterator it = a.i().iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            Handler handler = this.m;
            handler.sendMessageDelayed(Message.obtain(handler, 5, aVar), i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean d() {
        try {
            Calendar calendar = Calendar.getInstance();
            long j2 = (calendar.get(6) * 1000) + calendar.get(3);
            calendar.setTimeInMillis(ap.e());
            long j3 = (calendar.get(6) * 1000) + calendar.get(3);
            long b2 = bd.b(ab.f, ab.r, ab.f31u, 0L);
            if (System.currentTimeMillis() - ap.e() < 5000 || Math.abs((j3 / 1000) - (j2 / 1000)) == 1) {
                return true;
            }
            return Math.abs((j2 % 100) - (b2 % 100)) >= 1;
        } catch (Throwable th) {
            ce.postSDKError(th);
            return false;
        }
    }

    private void a(String str, a aVar, boolean z) {
        int c2 = aVar.c();
        if (c2 != 0 && c2 != 1 && c2 != 3) {
            if (c2 == 4) {
                if (z) {
                    an.iForDeveloper("Push " + str);
                    return;
                }
                return;
            }
            if (c2 != 7 && c2 != 8) {
                an.iForInternal(str);
                return;
            }
        }
        an.iForDeveloper(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(a aVar) {
        List a2;
        if (az.c(ab.f)) {
            try {
                ct.a().getFileLock(aVar);
                a2 = cr.b().a(aVar);
            } finally {
                try {
                    return;
                } finally {
                }
            }
            if (a2 != null && a2.size() > 0) {
                a("New data found, Submitting...", aVar, true);
                byte[] e2 = bh.e(a(a2));
                c.reset();
                c.update(e2);
                StringBuilder sb = new StringBuilder(aVar.f());
                if (aVar.b().equals("TRACKING")) {
                    sb.append("/" + Long.toHexString(c.getValue()));
                    sb.append("/1");
                } else {
                    sb.append("/" + a);
                    sb.append("/" + Long.toHexString(c.getValue()));
                }
                if (aq.a(ab.f, aVar.e(), "", sb.toString(), aVar.d(), e2).a() == 200) {
                    this.h = SystemClock.elapsedRealtime();
                    this.i = true;
                    cr.b().sendMessageSuccess(aVar);
                    a("Data submitted successfully!", aVar, true);
                } else {
                    this.i = false;
                    a("Failed to submit data!", aVar, true);
                }
                return;
            }
            a("No new data found!", aVar, false);
        }
    }

    private static String a(List list) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator it = list.iterator();
        while (it.hasNext()) {
            sb.append((String) it.next());
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

    public static byte[] a(byte[] bArr) {
        BufferedInputStream bufferedInputStream;
        byte[] bArr2 = new byte[2048];
        try {
            bufferedInputStream = new BufferedInputStream(new InflaterInputStream(new ByteArrayInputStream(bArr), new Inflater(false)));
        } catch (Exception unused) {
            bufferedInputStream = null;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int read = bufferedInputStream.read(bArr2);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr2, 0, read);
                } else {
                    byteArrayOutputStream.close();
                    bufferedInputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (Exception unused2) {
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            return null;
        }
    }

    private static void e() {
        if (az.c(ab.f)) {
            new Thread(new cp()).start();
        }
    }

    public final void onTDEBEventForwardRequest(cm cmVar) {
        if (cmVar == null || ab.f == null) {
            return;
        }
        if (cmVar.b.equals(cm.a.IMMEDIATELY)) {
            if (this.m.hasMessages(5, cmVar.a)) {
                this.m.removeMessages(5);
            }
            Message.obtain(this.m, 5, cmVar.a).sendToTarget();
        } else if (cmVar.b.equals(cm.a.HIGH)) {
            if (this.m.hasMessages(5)) {
                this.m.removeMessages(5);
            }
            long abs = Math.abs((SystemClock.elapsedRealtime() - this.h) - 30000);
            this.m.sendMessageDelayed(Message.obtain(this.m, 5, cmVar.a), abs <= 30000 ? abs : 30000L);
        }
    }

    public static co b() {
        if (k == null) {
            synchronized (co.class) {
                if (k == null) {
                    k = new co();
                }
            }
        }
        return k;
    }

    private co() {
        this.m = null;
        HandlerThread handlerThread = new HandlerThread("ModuleDataForward");
        l = handlerThread;
        handlerThread.start();
        this.m = new cq(this, l.getLooper());
        e();
        a();
    }
}
