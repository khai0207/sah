package com.unionpay.sdk;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import com.unionpay.sdk.au;
import java.io.ByteArrayOutputStream;
import java.util.zip.CRC32;
import java.util.zip.Deflater;

/* loaded from: classes.dex */
final class as {
    private static volatile as a;
    private static ap e;
    private final CRC32 b = new CRC32();
    private Handler c = null;
    private final HandlerThread d = new HandlerThread("NetWorkThread");

    static {
        a().d.start();
        a().c = new at(a().d.getLooper());
    }

    as() {
    }

    public static as a() {
        if (a == null) {
            synchronized (as.class) {
                if (a == null) {
                    a = new as();
                }
            }
        }
        return a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void a(String str, String str2, String str3, String str4, byte[] bArr, Object obj, int i, boolean z) {
        StringBuilder sb;
        String hexString;
        if (!z) {
            try {
                bArr = a(bArr);
            } catch (Throwable unused) {
                return;
            }
        }
        al.a(String.format("Gzipped post size is: %d", Integer.valueOf(bArr.length)));
        this.b.reset();
        this.b.update(bArr);
        if (i == 0) {
            sb = new StringBuilder();
            sb.append(str);
            sb.append("?crc=");
            hexString = Long.toHexString(this.b.getValue());
        } else {
            sb = new StringBuilder();
            sb.append(str);
            sb.append("?crc=");
            hexString = Long.toHexString(this.b.getValue());
        }
        sb.append(hexString);
        String sb2 = sb.toString();
        au.d a2 = au.a(str3, str2, sb2, str4).a(sb2, bArr);
        ad.f = SystemClock.elapsedRealtime();
        aq aqVar = new aq();
        aqVar.a.putInt("statusCode", a2.a);
        aqVar.a.putString("responseMsg", a2.b);
        aqVar.a.putString("action", e.d);
        aqVar.b = obj;
        aqVar.c = i;
        ai.a().post(aqVar);
    }

    private static byte[] a(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream;
        Deflater deflater = new Deflater(9);
        deflater.setInput(bArr);
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream(bArr.length);
        } catch (Throwable th) {
            th = th;
        }
        try {
            deflater.finish();
            byte[] bArr2 = new byte[1024];
            while (!deflater.finished()) {
                byteArrayOutputStream.write(bArr2, 0, deflater.deflate(bArr2));
            }
            try {
                byteArrayOutputStream.close();
            } catch (Exception unused) {
            }
            byteArrayOutputStream.close();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            al.a("Original: " + bArr.length);
            al.a("Compressed: " + byteArray.length);
            return byteArray;
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream2 = byteArrayOutputStream;
            if (byteArrayOutputStream2 != null) {
                try {
                    byteArrayOutputStream2.close();
                } catch (Exception unused2) {
                }
            }
            throw th;
        }
    }

    static /* synthetic */ void c() {
        aq aqVar = new aq();
        aqVar.a = null;
        aqVar.b = null;
        ai.a().post(aqVar);
    }
}
