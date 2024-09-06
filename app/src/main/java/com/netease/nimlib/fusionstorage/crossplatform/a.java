package com.netease.nimlib.fusionstorage.crossplatform;

import com.netease.nim.highavailable.enums.HAvailableFCSDownloadType;
import com.netease.nimlib.fusionstorage.crossplatform.defines.StorageProvider;

/* compiled from: Constants.java */
/* loaded from: classes.dex */
public class a {
    public static int b(int i) {
        return i == 2 ? 1 : 0;
    }

    public static int a(int i) {
        if (i == 1) {
            return StorageProvider.STORAGE_PROVIDER_NOS.getValue();
        }
        if (i == 2) {
            return StorageProvider.STORAGE_PROVIDER_AWS_S3.getValue();
        }
        if (i == 3) {
            return StorageProvider.STORAGE_PROVIDER_ALIYUN_OSS.getValue();
        }
        return StorageProvider.STORAGE_PROVIDER_UNKNOWN.getValue();
    }

    /* compiled from: Constants.java */
    /* renamed from: com.netease.nimlib.fusionstorage.crossplatform.a$1, reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[HAvailableFCSDownloadType.values().length];
            a = iArr;
            try {
                iArr[HAvailableFCSDownloadType.kThumbnail.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[HAvailableFCSDownloadType.kVideoCover.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[HAvailableFCSDownloadType.kSource.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static int a(HAvailableFCSDownloadType hAvailableFCSDownloadType) {
        int i = AnonymousClass1.a[hAvailableFCSDownloadType.ordinal()];
        int i2 = 1;
        if (i != 1) {
            i2 = 2;
            if (i != 2) {
                return 0;
            }
        }
        return i2;
    }
}
