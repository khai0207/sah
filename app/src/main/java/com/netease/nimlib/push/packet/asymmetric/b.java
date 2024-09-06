package com.netease.nimlib.push.packet.asymmetric;

import android.content.Context;
import com.netease.nimlib.push.packet.symmetry.SymmetryType;
import java.security.PublicKey;

/* compiled from: AsymmetricFactory.java */
/* loaded from: classes.dex */
public class b {
    private static b a;
    private static Object b = new Object();
    private Context c;
    private c d;
    private AsymmetricType e;
    private SymmetryType f;
    private a g;

    public static b a(Context context) {
        if (a == null) {
            synchronized (b) {
                if (a == null) {
                    a = new b(context);
                }
            }
        }
        return a;
    }

    private b(Context context) {
        this.c = context;
    }

    public AsymmetricType a() {
        return this.e;
    }

    public SymmetryType b() {
        return this.f;
    }

    public void c() {
        this.d = c.a(this.c);
    }

    public void d() {
        this.e = com.netease.nimlib.d.e.e();
        this.f = com.netease.nimlib.d.e.f();
        int i = AnonymousClass1.a[this.e.ordinal()];
        if (i == 1) {
            this.g = new f(this.c);
            return;
        }
        if (i == 2) {
            this.g = new e(this.c, AsymmetricType.RSA_OAEP_1);
        } else if (i == 3) {
            this.g = new e(this.c, AsymmetricType.RSA_OAEP_256);
        } else {
            this.g = new e(this.c, AsymmetricType.RSA);
        }
    }

    /* compiled from: AsymmetricFactory.java */
    /* renamed from: com.netease.nimlib.push.packet.asymmetric.b$1, reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[AsymmetricType.values().length];
            a = iArr;
            try {
                iArr[AsymmetricType.SM2.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[AsymmetricType.RSA_OAEP_1.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[AsymmetricType.RSA_OAEP_256.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[AsymmetricType.RSA.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public PublicKey e() {
        return this.g.c;
    }

    public int f() {
        return this.g.b;
    }

    public a g() {
        return this.g;
    }

    public PublicKey h() {
        if (this.d == null) {
            this.d = c.a(this.c);
        }
        return this.d.b;
    }

    public int i() {
        return this.d.a;
    }

    public void a(int i, byte[] bArr, long j) {
        this.d.a(i, bArr, j);
    }

    public void j() {
        this.d.a();
    }
}
