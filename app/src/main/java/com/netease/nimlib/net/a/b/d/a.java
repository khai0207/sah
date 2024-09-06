package com.netease.nimlib.net.a.b.d;

import com.netease.nimlib.c;
import com.netease.nimlib.o.p;

/* compiled from: NosUploadConf.java */
/* loaded from: classes.dex */
public class a {
    public static boolean a = true;
    private int b = 131072;
    private int c = 30000;
    private int d = 30000;
    private int e = 2;
    private int f = 2;
    private long g = 7200000;

    public int a() {
        return this.c;
    }

    public int b() {
        return this.d;
    }

    public int c() {
        try {
            int j = p.j(c.e());
            if (j == 0) {
                return 131072;
            }
            if (j == 1) {
                return 16384;
            }
            if (j != 2) {
                return j != 3 ? 131072 : 131072;
            }
            return 65536;
        } catch (Exception e) {
            e.printStackTrace();
            return 131072;
        }
    }

    public int d() {
        return this.e;
    }

    public int e() {
        return this.f;
    }
}
