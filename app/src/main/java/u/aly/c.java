package u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import com.unionpay.tsmservice.data.Constant;
import java.io.File;
import org.json.JSONObject;

/* compiled from: Envelope.java */
/* loaded from: classes.dex */
public class c {
    private String e;
    private int j;
    private int k;
    private byte[] l;
    private byte[] m;
    private final byte[] a = {0, 0, 0, 0, 0, 0, 0, 0};
    private final int b = 1;
    private final int c = 0;
    private String d = "1.0";
    private byte[] f = null;
    private byte[] g = null;
    private byte[] h = null;
    private int i = 0;
    private boolean n = false;

    private c(byte[] bArr, String str, byte[] bArr2) throws Exception {
        this.e = null;
        this.j = 0;
        this.k = 0;
        this.l = null;
        this.m = null;
        if (bArr == null || bArr.length == 0) {
            throw new Exception("entity is null or empty");
        }
        this.e = str;
        this.k = bArr.length;
        this.l = bs.a(bArr);
        this.j = (int) (System.currentTimeMillis() / 1000);
        this.m = bArr2;
    }

    public static String a(Context context) {
        SharedPreferences a = y.a(context);
        if (a == null) {
            return null;
        }
        return a.getString(Constant.KEY_SIGNATURE, null);
    }

    public void a(String str) {
        this.f = com.umeng.analytics.b.a(str);
    }

    public String a() {
        return com.umeng.analytics.b.a(this.f);
    }

    public void a(int i) {
        this.i = i;
    }

    public void a(boolean z) {
        this.n = z;
    }

    public static c a(Context context, String str, byte[] bArr) {
        try {
            String q = bt.q(context);
            String f = bt.f(context);
            SharedPreferences a = y.a(context);
            String string = a.getString(Constant.KEY_SIGNATURE, null);
            int i = a.getInt("serial", 1);
            c cVar = new c(bArr, str, (f + q).getBytes());
            cVar.a(string);
            cVar.a(i);
            cVar.b();
            a.edit().putInt("serial", i + 1).putString(Constant.KEY_SIGNATURE, cVar.a()).commit();
            cVar.b(context);
            return cVar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static c b(Context context, String str, byte[] bArr) {
        try {
            String q = bt.q(context);
            String f = bt.f(context);
            SharedPreferences a = y.a(context);
            String string = a.getString(Constant.KEY_SIGNATURE, null);
            int i = a.getInt("serial", 1);
            c cVar = new c(bArr, str, (f + q).getBytes());
            cVar.a(true);
            cVar.a(string);
            cVar.a(i);
            cVar.b();
            a.edit().putInt("serial", i + 1).putString(Constant.KEY_SIGNATURE, cVar.a()).commit();
            cVar.b(context);
            return cVar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void b() {
        if (this.f == null) {
            this.f = d();
        }
        if (this.n) {
            byte[] bArr = new byte[16];
            try {
                System.arraycopy(this.f, 1, bArr, 0, 16);
                this.l = com.umeng.analytics.b.a(this.l, bArr);
            } catch (Exception unused) {
            }
        }
        this.g = a(this.f, this.j);
        this.h = e();
    }

    private byte[] a(byte[] bArr, int i) {
        byte[] b = com.umeng.analytics.b.b(this.m);
        byte[] b2 = com.umeng.analytics.b.b(this.l);
        int length = b.length;
        int i2 = length * 2;
        byte[] bArr2 = new byte[i2];
        for (int i3 = 0; i3 < length; i3++) {
            int i4 = i3 * 2;
            bArr2[i4] = b2[i3];
            bArr2[i4 + 1] = b[i3];
        }
        for (int i5 = 0; i5 < 2; i5++) {
            bArr2[i5] = bArr[i5];
            bArr2[(i2 - i5) - 1] = bArr[(bArr.length - i5) - 1];
        }
        byte[] bArr3 = {(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) (i >>> 24)};
        for (int i6 = 0; i6 < i2; i6++) {
            bArr2[i6] = (byte) (bArr2[i6] ^ bArr3[i6 % 4]);
        }
        return bArr2;
    }

    private byte[] d() {
        return a(this.a, (int) (System.currentTimeMillis() / 1000));
    }

    private byte[] e() {
        return com.umeng.analytics.b.b((com.umeng.analytics.b.a(this.f) + this.i + this.j + this.k + com.umeng.analytics.b.a(this.g)).getBytes());
    }

    public byte[] c() {
        bq bqVar = new bq();
        bqVar.a(this.d);
        bqVar.b(this.e);
        bqVar.c(com.umeng.analytics.b.a(this.f));
        bqVar.a(this.i);
        bqVar.c(this.j);
        bqVar.d(this.k);
        bqVar.a(this.l);
        bqVar.e(this.n ? 1 : 0);
        bqVar.d(com.umeng.analytics.b.a(this.g));
        bqVar.e(com.umeng.analytics.b.a(this.h));
        try {
            return new ci().a(bqVar);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void b(Context context) {
        String str = this.e;
        String b = g.a(context).b().b((String) null);
        String a = com.umeng.analytics.b.a(this.f);
        byte[] bArr = new byte[16];
        System.arraycopy(this.f, 2, bArr, 0, 16);
        String a2 = com.umeng.analytics.b.a(com.umeng.analytics.b.b(bArr));
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(com.alipay.sdk.m.o.a.p, str);
            if (b != null) {
                jSONObject.put("umid", b);
            }
            jSONObject.put(Constant.KEY_SIGNATURE, a);
            jSONObject.put("checksum", a2);
            String jSONObject2 = jSONObject.toString();
            File file = new File(context.getFilesDir(), ".umeng");
            if (!file.exists()) {
                file.mkdir();
            }
            bu.a(new File(file, "exchangeIdentity.json"), jSONObject2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return String.format("version : %s\n", this.d) + String.format("address : %s\n", this.e) + String.format("signature : %s\n", com.umeng.analytics.b.a(this.f)) + String.format("serial : %s\n", Integer.valueOf(this.i)) + String.format("timestamp : %d\n", Integer.valueOf(this.j)) + String.format("length : %d\n", Integer.valueOf(this.k)) + String.format("guid : %s\n", com.umeng.analytics.b.a(this.g)) + String.format("checksum : %s ", com.umeng.analytics.b.a(this.h)) + String.format("codex : %d", Integer.valueOf(this.n ? 1 : 0));
    }
}
