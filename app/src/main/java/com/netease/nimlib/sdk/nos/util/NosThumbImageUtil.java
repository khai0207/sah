package com.netease.nimlib.sdk.nos.util;

import android.util.DisplayMetrics;
import com.netease.nimlib.c;
import com.netease.nimlib.net.a.c.d;
import com.netease.nimlib.sdk.nos.model.NosThumbParam;

/* loaded from: classes.dex */
public class NosThumbImageUtil {
    public static final String makeImageThumbUrl(String str, NosThumbParam.ThumbType thumbType, int i, int i2) {
        return d.b(str, toImageThumbParams(thumbType, i, i2));
    }

    public static final String makeImageThumbUrl(String str, int i, int i2) {
        NosThumbParam.ThumbType thumbType = NosThumbParam.ThumbType.Internal;
        if (i2 > 0 && i > 0) {
            thumbType = (i > i2 ? i / i2 : i2 / i) > 4 ? NosThumbParam.ThumbType.External : NosThumbParam.ThumbType.Internal;
        }
        int i3 = c.i().thumbnailSize;
        if (i3 <= 0) {
            DisplayMetrics displayMetrics = c.e().getApplicationContext().getResources().getDisplayMetrics();
            i3 = Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels) / 2;
        }
        return d.b(str, toImageThumbParams(thumbType, i3, i3));
    }

    private static final String toImageThumbParams(NosThumbParam.ThumbType thumbType, int i, int i2) {
        if (!checkImageThumb(thumbType, i, i2)) {
            throw new IllegalArgumentException("width=" + i + ", height=" + i2);
        }
        return "thumbnail=" + i + toImageThumbMethod(thumbType) + i2 + "&imageView";
    }

    /* renamed from: com.netease.nimlib.sdk.nos.util.NosThumbImageUtil$1, reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$netease$nimlib$sdk$nos$model$NosThumbParam$ThumbType;

        static {
            int[] iArr = new int[NosThumbParam.ThumbType.values().length];
            $SwitchMap$com$netease$nimlib$sdk$nos$model$NosThumbParam$ThumbType = iArr;
            try {
                iArr[NosThumbParam.ThumbType.Internal.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$netease$nimlib$sdk$nos$model$NosThumbParam$ThumbType[NosThumbParam.ThumbType.Crop.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$netease$nimlib$sdk$nos$model$NosThumbParam$ThumbType[NosThumbParam.ThumbType.External.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private static final boolean checkImageThumb(NosThumbParam.ThumbType thumbType, int i, int i2) {
        if (i < 0 || i2 < 0) {
            return false;
        }
        int i3 = AnonymousClass1.$SwitchMap$com$netease$nimlib$sdk$nos$model$NosThumbParam$ThumbType[thumbType.ordinal()];
        return i3 != 1 ? (i3 == 2 || i3 == 3) && i > 0 && i2 > 0 : i > 0 || i2 > 0;
    }

    private static final String toImageThumbMethod(NosThumbParam.ThumbType thumbType) {
        int i = AnonymousClass1.$SwitchMap$com$netease$nimlib$sdk$nos$model$NosThumbParam$ThumbType[thumbType.ordinal()];
        if (i == 1) {
            return "x";
        }
        if (i == 2) {
            return "y";
        }
        if (i == 3) {
            return "z";
        }
        throw new IllegalArgumentException("thumb: " + thumbType);
    }
}
