package com.unionpay.mobile.android.global;

import android.content.Context;
import com.unionpay.mobile.android.utils.f;

/* loaded from: classes.dex */
public final class a {
    public static int A = 45;
    public static int B = 32;
    public static int C = 6;
    public static int D = 25;
    public static int E = 95;
    public static int F = 25;
    public static int G = 25;
    public static int H = 1;
    public static int I = 0;
    public static int J = 5;
    public static int K = 1;
    public static boolean L = true;
    public static int M = 0;
    public static int N = 0;
    private static boolean O = false;
    public static int a = 48;
    public static int b = 8;
    public static int c = 4;
    public static int d = 12;
    public static int e = 8;
    public static int f = 8;
    public static int g = 4;
    public static int h = 8;
    public static int i = 2;
    public static int j = 16;
    public static int k = 52;
    public static int l = 320;
    public static int m = 32;
    public static int n = 54;
    public static int o = 45;
    public static int p = 35;
    public static int q = 40;
    public static int r = 54;
    public static int s = 10;
    public static int t = 0;

    /* renamed from: u, reason: collision with root package name */
    public static int f36u = 28;
    public static int v = 30;
    public static int w = 22;
    public static int x = 40;
    public static int y = 54;
    public static int z = 46;

    public static void a(Context context) {
        b.a(context);
        if (O) {
            return;
        }
        a = f.a(context, a);
        b = f.a(context, b);
        c = f.a(context, c);
        d = f.a(context, d);
        e = f.a(context, e);
        f = f.a(context, f);
        g = f.a(context, g);
        h = f.a(context, h);
        i = f.a(context, i);
        j = f.a(context, j);
        k = f.a(context, k);
        m = f.a(context, m);
        n = f.a(context, n);
        o = f.a(context, o);
        p = f.a(context, p);
        q = f.a(context, q);
        r = f.a(context, r);
        s = f.a(context, s);
        f36u = f.a(context, f36u);
        v = f.a(context, v);
        w = f.a(context, w);
        z = f.a(context, z);
        x = f.a(context, x);
        A = f.a(context, A);
        B = f.a(context, B);
        C = f.a(context, C);
        y = f.a(context, y);
        D = f.a(context, D);
        E = f.a(context, E);
        F = f.a(context, F);
        G = f.a(context, G);
        H = f.a(context, H);
        double d2 = context.getResources().getDisplayMetrics().density;
        Double.isNaN(d2);
        K = (int) (d2 + 0.5d);
        I = context.getResources().getDisplayMetrics().widthPixels;
        int i2 = context.getResources().getDisplayMetrics().heightPixels;
        t = i2;
        int i3 = I;
        if (i3 > i2) {
            int i4 = i3 + i2;
            I = i4;
            int i5 = i4 - i2;
            t = i5;
            I = i4 - i5;
        }
        J = f.a(context, J);
        O = true;
    }
}
