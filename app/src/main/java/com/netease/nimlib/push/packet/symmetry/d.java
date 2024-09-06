package com.netease.nimlib.push.packet.symmetry;

/* compiled from: SymmetryFactory.java */
/* loaded from: classes.dex */
public class d {

    /* compiled from: SymmetryFactory.java */
    /* renamed from: com.netease.nimlib.push.packet.symmetry.d$1, reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[SymmetryType.values().length];
            a = iArr;
            try {
                iArr[SymmetryType.SM4.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[SymmetryType.RC4.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static byte[] a(SymmetryType symmetryType) {
        if (AnonymousClass1.a[symmetryType.ordinal()] == 1) {
            return b.a();
        }
        return a.a();
    }

    public static c a(SymmetryType symmetryType, byte[] bArr) {
        if (AnonymousClass1.a[symmetryType.ordinal()] == 1) {
            return new b(bArr);
        }
        return new a(bArr);
    }

    public static c b(SymmetryType symmetryType, byte[] bArr) {
        if (AnonymousClass1.a[symmetryType.ordinal()] == 1) {
            return new b(bArr);
        }
        return new a(bArr);
    }
}
