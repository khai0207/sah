package com.umeng.analytics;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.unionpay.tsmservice.data.Constant;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Locale;
import u.aly.bt;
import u.aly.bu;
import u.aly.bv;
import u.aly.y;

/* compiled from: StoreHelper.java */
/* loaded from: classes.dex */
public final class h {
    private static h a = null;
    private static Context b = null;
    private static String c = null;
    private static long e = 1209600000;
    private static long f = 2097152;
    private static final String g = "mobclick_agent_user_";
    private static final String h = "mobclick_agent_online_setting_";
    private static final String i = "mobclick_agent_header_";
    private static final String j = "mobclick_agent_update_";
    private static final String k = "mobclick_agent_state_";
    private static final String l = "mobclick_agent_cached_";
    private a d;

    /* compiled from: StoreHelper.java */
    /* loaded from: classes.dex */
    public interface b {
        void a(File file);

        boolean b(File file);

        void c(File file);
    }

    public h(Context context) {
        this.d = new a(context);
        b = context.getApplicationContext();
        c = context.getPackageName();
    }

    public static synchronized h a(Context context) {
        h hVar;
        synchronized (h.class) {
            if (a == null) {
                a = new h(context);
            }
            hVar = a;
        }
        return hVar;
    }

    private static boolean a(File file) {
        return file.exists() && file.length() > f;
    }

    public void a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        SharedPreferences.Editor edit = l().edit();
        edit.putString("au_p", str);
        edit.putString("au_u", str2);
        edit.commit();
    }

    public String[] a() {
        SharedPreferences l2 = l();
        String string = l2.getString("au_p", null);
        String string2 = l2.getString("au_u", null);
        if (string == null || string2 == null) {
            return null;
        }
        return new String[]{string, string2};
    }

    public void b() {
        l().edit().remove("au_p").remove("au_u").commit();
    }

    public String c() {
        SharedPreferences a2 = y.a(b);
        if (a2 != null) {
            return a2.getString(com.alipay.sdk.m.o.a.p, null);
        }
        return null;
    }

    public void a(String str) {
        SharedPreferences a2 = y.a(b);
        if (a2 != null) {
            a2.edit().putString(com.alipay.sdk.m.o.a.p, str).commit();
        }
    }

    public String d() {
        SharedPreferences a2 = y.a(b);
        if (a2 != null) {
            return a2.getString(Constant.KEY_CHANNEL, null);
        }
        return null;
    }

    public void b(String str) {
        SharedPreferences a2 = y.a(b);
        if (a2 != null) {
            a2.edit().putString(Constant.KEY_CHANNEL, str).commit();
        }
    }

    public byte[] e() {
        FileInputStream fileInputStream;
        String n = n();
        File file = new File(b.getFilesDir(), n);
        FileInputStream fileInputStream2 = null;
        if (a(file)) {
            file.delete();
            return null;
        }
        if (!file.exists()) {
            return null;
        }
        try {
            fileInputStream = b.openFileInput(n);
        } catch (Exception e2) {
            e = e2;
            fileInputStream = null;
        } catch (Throwable th) {
            th = th;
            bu.c(fileInputStream2);
            throw th;
        }
        try {
            try {
                byte[] b2 = bu.b(fileInputStream);
                bu.c(fileInputStream);
                return b2;
            } catch (Throwable th2) {
                th = th2;
                fileInputStream2 = fileInputStream;
                bu.c(fileInputStream2);
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            e.printStackTrace();
            bu.c(fileInputStream);
            return null;
        }
    }

    public void a(byte[] bArr) {
        try {
            bu.a(new File(b.getFilesDir(), n()), bArr);
        } catch (Exception e2) {
            bv.e(e2);
        }
    }

    public void f() {
        b.deleteFile(m());
        b.deleteFile(n());
    }

    public void b(byte[] bArr) {
        this.d.a(bArr);
    }

    public boolean g() {
        return this.d.a();
    }

    public a h() {
        return this.d;
    }

    private SharedPreferences l() {
        return b.getSharedPreferences(g + c, 0);
    }

    public SharedPreferences i() {
        return b.getSharedPreferences(i + c, 0);
    }

    public SharedPreferences j() {
        return b.getSharedPreferences(j + c, 0);
    }

    public SharedPreferences k() {
        return b.getSharedPreferences(k + c, 0);
    }

    private String m() {
        return i + c;
    }

    private String n() {
        return l + c + bt.c(b);
    }

    /* compiled from: StoreHelper.java */
    /* loaded from: classes.dex */
    public static class a {
        private final int a;
        private File b;
        private FilenameFilter c;

        public a(Context context) {
            this(context, ".um");
        }

        public a(Context context, String str) {
            this.a = 10;
            this.c = new FilenameFilter() { // from class: com.umeng.analytics.h.a.1
                @Override // java.io.FilenameFilter
                public boolean accept(File file, String str2) {
                    return str2.startsWith("um");
                }
            };
            File file = new File(context.getFilesDir(), str);
            this.b = file;
            if (file.exists() && this.b.isDirectory()) {
                return;
            }
            this.b.mkdir();
        }

        public boolean a() {
            File[] listFiles = this.b.listFiles();
            return listFiles != null && listFiles.length > 0;
        }

        public void a(b bVar) {
            File file;
            File[] listFiles = this.b.listFiles(this.c);
            if (listFiles == null || listFiles.length <= 0) {
                return;
            }
            bVar.a(this.b);
            int length = listFiles.length;
            for (int i = 0; i < length; i++) {
                try {
                } catch (Throwable unused) {
                    file = listFiles[i];
                }
                if (bVar.b(listFiles[i])) {
                    file = listFiles[i];
                    file.delete();
                }
            }
            bVar.c(this.b);
        }

        public void a(byte[] bArr) {
            if (bArr == null || bArr.length == 0) {
                return;
            }
            try {
                bu.a(new File(this.b, String.format(Locale.US, "um_cache_%d.env", Long.valueOf(System.currentTimeMillis()))), bArr);
            } catch (Exception unused) {
            }
            File[] listFiles = this.b.listFiles(this.c);
            if (listFiles == null || listFiles.length < 10) {
                return;
            }
            Arrays.sort(listFiles);
            int length = listFiles.length - 10;
            for (int i = 0; i < length; i++) {
                listFiles[i].delete();
            }
        }

        public void b() {
            File[] listFiles = this.b.listFiles(this.c);
            if (listFiles == null || listFiles.length <= 0) {
                return;
            }
            for (File file : listFiles) {
                file.delete();
            }
        }

        public int c() {
            File[] listFiles = this.b.listFiles(this.c);
            if (listFiles == null || listFiles.length <= 0) {
                return 0;
            }
            return listFiles.length;
        }
    }
}
