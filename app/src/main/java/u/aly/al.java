package u.aly;

import android.content.Context;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import u.aly.g;

/* compiled from: ABTest.java */
/* loaded from: classes.dex */
public class al implements x {
    private static al h;
    private int b;
    private int c;
    private int d;
    private Context g;
    private boolean a = false;
    private float e = 0.0f;
    private float f = 0.0f;

    public static synchronized al a(Context context) {
        al alVar;
        synchronized (al.class) {
            if (h == null) {
                g.a b = g.a(context).b();
                h = new al(context, b.a((String) null), b.d(0));
            }
            alVar = h;
        }
        return alVar;
    }

    private al(Context context, String str, int i) {
        this.g = null;
        this.g = context;
        a(str, i);
    }

    private float b(String str, int i) {
        int i2 = i * 2;
        if (str == null) {
            return 0.0f;
        }
        return Integer.valueOf(str.substring(i2, i2 + 5), 16).intValue() / 1048576.0f;
    }

    public void a(String str, int i) {
        this.c = i;
        String a = c.a(this.g);
        if (TextUtils.isEmpty(a) || TextUtils.isEmpty(str)) {
            this.a = false;
            return;
        }
        try {
            this.e = b(a, 12);
            this.f = b(a, 6);
            if (str.startsWith("SIG7")) {
                b(str);
            } else if (str.startsWith("FIXED")) {
                c(str);
            }
        } catch (Exception e) {
            this.a = false;
            bv.e("v:" + str, e);
        }
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String[] split = str.split("\\|");
        if (split.length != 6) {
            return false;
        }
        if (split[0].startsWith("SIG7") && split[1].split(",").length == split[5].split(",").length) {
            return true;
        }
        if (split[0].startsWith("FIXED")) {
            int length = split[5].split(",").length;
            int parseInt = Integer.parseInt(split[1]);
            if (length >= parseInt && parseInt >= 1) {
                return true;
            }
        }
        return false;
    }

    private void b(String str) {
        float[] fArr;
        if (str == null) {
            return;
        }
        String[] split = str.split("\\|");
        float f = 0.0f;
        if (this.e > (split[2].equals("SIG13") ? Float.valueOf(split[3]).floatValue() : 0.0f)) {
            this.a = false;
            return;
        }
        int[] iArr = null;
        if (split[0].equals("SIG7")) {
            String[] split2 = split[1].split(",");
            fArr = new float[split2.length];
            for (int i = 0; i < split2.length; i++) {
                fArr[i] = Float.valueOf(split2[i]).floatValue();
            }
        } else {
            fArr = null;
        }
        if (split[4].equals("RPT")) {
            String[] split3 = split[5].split(",");
            iArr = new int[split3.length];
            for (int i2 = 0; i2 < split3.length; i2++) {
                iArr[i2] = Integer.valueOf(split3[i2]).intValue();
            }
        }
        int i3 = 0;
        while (true) {
            if (i3 >= fArr.length) {
                i3 = -1;
                break;
            }
            f += fArr[i3];
            if (this.f < f) {
                break;
            } else {
                i3++;
            }
        }
        if (i3 != -1) {
            this.a = true;
            this.d = i3 + 1;
            this.b = iArr[i3];
            return;
        }
        this.a = false;
    }

    private void c(String str) {
        if (str == null) {
            return;
        }
        String[] split = str.split("\\|");
        if (this.e > (split[2].equals("SIG13") ? Float.valueOf(split[3]).floatValue() : 0.0f)) {
            this.a = false;
            return;
        }
        int intValue = split[0].equals("FIXED") ? Integer.valueOf(split[1]).intValue() : -1;
        int[] iArr = null;
        if (split[4].equals("RPT")) {
            String[] split2 = split[5].split(",");
            iArr = new int[split2.length];
            for (int i = 0; i < split2.length; i++) {
                iArr[i] = Integer.valueOf(split2[i]).intValue();
            }
        }
        if (intValue != -1) {
            this.a = true;
            this.d = intValue;
            this.b = iArr[intValue - 1];
            return;
        }
        this.a = false;
    }

    public boolean a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public Map<String, Integer> e() {
        if (!this.a) {
            return null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("client_test", Integer.valueOf(this.d));
        return hashMap;
    }

    @Override // u.aly.x
    public void a(g.a aVar) {
        a(aVar.a((String) null), aVar.d(0));
    }

    public String toString() {
        return " p13:" + this.e + " p07:" + this.f + " policy:" + this.b + " interval:" + this.c;
    }
}
