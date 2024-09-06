package com.netease.nimlib.n.b;

import com.netease.nim.highavailable.enums.HAvailableFCSErrorCode;

/* compiled from: EMExceptionState.java */
/* loaded from: classes.dex */
public enum h {
    kUnknown(-1),
    kSucceed(0),
    kFailed(1),
    kAborted(2),
    kCanceled(3),
    kMissed(4),
    kPoorPerformance(5);

    private int h;

    h(int i2) {
        this.h = i2;
    }

    public int a() {
        return this.h;
    }

    public static h a(HAvailableFCSErrorCode hAvailableFCSErrorCode) {
        if (hAvailableFCSErrorCode == null) {
            return kUnknown;
        }
        int i2 = AnonymousClass1.a[hAvailableFCSErrorCode.ordinal()];
        if (i2 == 1) {
            return kSucceed;
        }
        if (i2 == 2) {
            return kCanceled;
        }
        return kFailed;
    }

    /* compiled from: EMExceptionState.java */
    /* renamed from: com.netease.nimlib.n.b.h$1, reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[HAvailableFCSErrorCode.values().length];
            a = iArr;
            try {
                iArr[HAvailableFCSErrorCode.kOK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[HAvailableFCSErrorCode.kCancel.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[HAvailableFCSErrorCode.kErrorMoveFile.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[HAvailableFCSErrorCode.kError.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }
}
