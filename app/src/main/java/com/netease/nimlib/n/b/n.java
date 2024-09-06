package com.netease.nimlib.n.b;

import com.netease.nim.highavailable.enums.HAvailableFCSUploadPluginTag;

/* compiled from: EMResourceUploadWay.java */
/* loaded from: classes.dex */
public enum n {
    kResourceUploadWayUnknown(-1),
    kResourceUploadWayNOS(0),
    kResourceUploadWayAWSPlugin(1),
    kResourceUploadWayNOSPlugin(2);

    private int e;

    n(int i) {
        this.e = i;
    }

    public int a() {
        return this.e;
    }

    public static n a(HAvailableFCSUploadPluginTag hAvailableFCSUploadPluginTag) {
        if (hAvailableFCSUploadPluginTag == null) {
            return kResourceUploadWayUnknown;
        }
        int i = AnonymousClass1.a[hAvailableFCSUploadPluginTag.ordinal()];
        if (i == 1) {
            return kResourceUploadWayAWSPlugin;
        }
        if (i == 2) {
            return kResourceUploadWayNOSPlugin;
        }
        return kResourceUploadWayUnknown;
    }

    /* compiled from: EMResourceUploadWay.java */
    /* renamed from: com.netease.nimlib.n.b.n$1, reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[HAvailableFCSUploadPluginTag.values().length];
            a = iArr;
            try {
                iArr[HAvailableFCSUploadPluginTag.kUploadPluginTagAWSS3.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[HAvailableFCSUploadPluginTag.kUploadPluginTagNOS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[HAvailableFCSUploadPluginTag.kUploadPluginTagUnknown.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
