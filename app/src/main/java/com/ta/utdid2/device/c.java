package com.ta.utdid2.device;

import android.content.Context;
import android.text.TextUtils;
import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import com.ta.utdid2.a.a.f;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Random;
import java.util.regex.Pattern;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public class c {
    public static c a;
    public static final Object b = new Object();
    public static final String i = ".UTSystemConfig" + File.separator + "Global";

    /* renamed from: a, reason: collision with other field name */
    public com.ta.utdid2.b.a.a f9a;

    /* renamed from: a, reason: collision with other field name */
    public d f10a;

    /* renamed from: b, reason: collision with other field name */
    public com.ta.utdid2.b.a.a f11b;
    public String g;
    public String h;
    public Context mContext;
    public String f = null;

    /* renamed from: b, reason: collision with other field name */
    public Pattern f12b = Pattern.compile("[^0-9a-zA-Z=/+]+");

    public c(Context context) {
        this.mContext = null;
        this.f10a = null;
        this.g = "xx_utdid_key";
        this.h = "xx_utdid_domain";
        this.f9a = null;
        this.f11b = null;
        this.mContext = context;
        this.f11b = new com.ta.utdid2.b.a.a(context, i, "Alvin2", false, true);
        this.f9a = new com.ta.utdid2.b.a.a(context, ".DataStorage", "ContextData", false, true);
        this.f10a = new d();
        this.g = String.format("K_%d", Integer.valueOf(f.a(this.g)));
        this.h = String.format("D_%d", Integer.valueOf(f.a(this.h)));
    }

    public static c a(Context context) {
        if (context != null && a == null) {
            synchronized (b) {
                if (a == null) {
                    c cVar = new c(context);
                    a = cVar;
                    cVar.b();
                }
            }
        }
        return a;
    }

    private void b() {
        com.ta.utdid2.b.a.a aVar = this.f11b;
        if (aVar != null) {
            if (f.m59a(aVar.getString("UTDID2"))) {
                String string = this.f11b.getString("UTDID");
                if (!f.m59a(string)) {
                    e(string);
                }
            }
            boolean z = false;
            boolean z2 = true;
            if (!f.m59a(this.f11b.getString("DID"))) {
                this.f11b.remove("DID");
                z = true;
            }
            if (!f.m59a(this.f11b.getString("EI"))) {
                this.f11b.remove("EI");
                z = true;
            }
            if (f.m59a(this.f11b.getString("SI"))) {
                z2 = z;
            } else {
                this.f11b.remove("SI");
            }
            if (z2) {
                this.f11b.commit();
            }
        }
    }

    private byte[] c() throws Exception {
        String str;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        int nextInt = new Random().nextInt();
        byte[] bytes = com.ta.utdid2.a.a.c.getBytes(currentTimeMillis);
        byte[] bytes2 = com.ta.utdid2.a.a.c.getBytes(nextInt);
        byteArrayOutputStream.write(bytes, 0, 4);
        byteArrayOutputStream.write(bytes2, 0, 4);
        byteArrayOutputStream.write(3);
        byteArrayOutputStream.write(0);
        try {
            str = com.ta.utdid2.a.a.d.a(this.mContext);
        } catch (Exception unused) {
            str = "" + new Random().nextInt();
        }
        byteArrayOutputStream.write(com.ta.utdid2.a.a.c.getBytes(f.a(str)), 0, 4);
        byteArrayOutputStream.write(com.ta.utdid2.a.a.c.getBytes(f.a(b(byteArrayOutputStream.toByteArray()))));
        return byteArrayOutputStream.toByteArray();
    }

    private void e(String str) {
        com.ta.utdid2.b.a.a aVar;
        if (b(str)) {
            if (str.endsWith("\n")) {
                str = str.substring(0, str.length() - 1);
            }
            if (str.length() != 24 || (aVar = this.f11b) == null) {
                return;
            }
            aVar.putString("UTDID2", str);
            this.f11b.commit();
        }
    }

    private void f(String str) {
        com.ta.utdid2.b.a.a aVar;
        if (str == null || (aVar = this.f9a) == null || str.equals(aVar.getString(this.g))) {
            return;
        }
        this.f9a.putString(this.g, str);
        this.f9a.commit();
    }

    public synchronized String g() {
        String h = h();
        this.f = h;
        if (!TextUtils.isEmpty(h)) {
            return this.f;
        }
        try {
            byte[] c = c();
            if (c != null) {
                String encodeToString = com.ta.utdid2.a.a.b.encodeToString(c, 2);
                this.f = encodeToString;
                e(encodeToString);
                String c2 = this.f10a.c(c);
                if (c2 != null) {
                    f(c2);
                }
                return this.f;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public synchronized String getValue() {
        String str = this.f;
        if (str != null) {
            return str;
        }
        return g();
    }

    public synchronized String h() {
        String f = f();
        if (b(f)) {
            f(this.f10a.c(f));
            this.f = f;
            return f;
        }
        String string = this.f9a.getString(this.g);
        if (!f.m59a(string)) {
            String d = new e().d(string);
            if (!b(d)) {
                d = this.f10a.d(string);
            }
            if (b(d) && !f.m59a(d)) {
                this.f = d;
                e(d);
                return this.f;
            }
        }
        return null;
    }

    private String f() {
        com.ta.utdid2.b.a.a aVar = this.f11b;
        if (aVar == null) {
            return null;
        }
        String string = aVar.getString("UTDID2");
        if (f.m59a(string) || this.f10a.c(string) == null) {
            return null;
        }
        return string;
    }

    private boolean b(String str) {
        if (str != null) {
            if (str.endsWith("\n")) {
                str = str.substring(0, str.length() - 1);
            }
            if (24 == str.length() && !this.f12b.matcher(str).find()) {
                return true;
            }
        }
        return false;
    }

    public static String b(byte[] bArr) throws Exception {
        Mac mac = Mac.getInstance(Constants.HMAC_SHA1_ALGORITHM);
        mac.init(new SecretKeySpec(com.ta.utdid2.a.a.e.a(new byte[]{69, 114, 116, -33, 125, -54, -31, 86, -11, 11, -78, -96, -17, -99, 64, 23, -95, -126, -82, -64, 113, 116, -16, -103, 49, -30, 9, -39, 33, -80, -68, -78, -117, 53, 30, -122, 64, -104, 74, -49, 106, 85, -38, -93}), mac.getAlgorithm()));
        return com.ta.utdid2.a.a.b.encodeToString(mac.doFinal(bArr), 2);
    }
}
