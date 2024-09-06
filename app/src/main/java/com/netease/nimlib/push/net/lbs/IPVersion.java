package com.netease.nimlib.push.net.lbs;

import com.netease.nim.highavailable.HighAvailableLBSService;

/* loaded from: classes.dex */
public enum IPVersion {
    IPV4(0),
    IPV6(1),
    ANY(2),
    IPV6_FIRST(3);

    private int mValue;

    IPVersion(int i) {
        this.mValue = i;
    }

    public int getValue() {
        return this.mValue;
    }

    public static IPVersion value(int i) {
        for (IPVersion iPVersion : values()) {
            if (iPVersion.mValue == i) {
                return iPVersion;
            }
        }
        return IPV4;
    }

    /* renamed from: com.netease.nimlib.push.net.lbs.IPVersion$1, reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[IPVersion.values().length];
            a = iArr;
            try {
                iArr[IPVersion.IPV6.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[IPVersion.IPV6_FIRST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[IPVersion.ANY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[IPVersion.IPV4.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public HighAvailableLBSService.AddressFamily toAddressFamily() {
        int i = AnonymousClass1.a[ordinal()];
        if (i == 1 || i == 2) {
            return HighAvailableLBSService.AddressFamily.kIPV6;
        }
        if (i == 3) {
            return HighAvailableLBSService.AddressFamily.kUnknown;
        }
        return HighAvailableLBSService.AddressFamily.kIPV4;
    }
}
