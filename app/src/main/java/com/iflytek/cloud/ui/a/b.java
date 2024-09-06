package com.iflytek.cloud.ui.a;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.TypedValue;
import java.io.InputStream;

/* loaded from: classes.dex */
public class b {
    private static int a;

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0019, code lost:
    
        if (r2 != 65535) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Bitmap a(android.content.res.Resources r1, android.util.TypedValue r2, java.io.InputStream r3, android.graphics.Rect r4, android.graphics.BitmapFactory.Options r5) {
        /*
            if (r5 != 0) goto L7
            android.graphics.BitmapFactory$Options r5 = new android.graphics.BitmapFactory$Options
            r5.<init>()
        L7:
            int r0 = r5.inDensity
            if (r0 != 0) goto L1c
            if (r2 == 0) goto L1c
            int r2 = r2.density
            if (r2 != 0) goto L16
            r2 = 160(0xa0, float:2.24E-43)
        L13:
            r5.inDensity = r2
            goto L1c
        L16:
            r0 = 65535(0xffff, float:9.1834E-41)
            if (r2 == r0) goto L1c
            goto L13
        L1c:
            int r2 = r5.inTargetDensity
            if (r2 != 0) goto L2a
            if (r1 == 0) goto L2a
            android.util.DisplayMetrics r1 = r1.getDisplayMetrics()
            int r1 = r1.densityDpi
            r5.inTargetDensity = r1
        L2a:
            android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeStream(r3, r4, r5)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iflytek.cloud.ui.a.b.a(android.content.res.Resources, android.util.TypedValue, java.io.InputStream, android.graphics.Rect, android.graphics.BitmapFactory$Options):android.graphics.Bitmap");
    }

    private static Drawable a(Resources resources, Bitmap bitmap, byte[] bArr, Rect rect, String str) {
        return bArr != null ? new NinePatchDrawable(resources, bitmap, bArr, rect, str) : new BitmapDrawable(resources, bitmap);
    }

    public static Drawable a(Resources resources, TypedValue typedValue, InputStream inputStream, String str, BitmapFactory.Options options) {
        byte[] bArr = null;
        if (inputStream == null) {
            return null;
        }
        Rect rect = new Rect();
        if (options == null) {
            options = new BitmapFactory.Options();
        }
        Bitmap a2 = a(resources, typedValue, inputStream, rect, options);
        if (a2 == null) {
            return null;
        }
        byte[] ninePatchChunk = a2.getNinePatchChunk();
        if (ninePatchChunk == null || !NinePatch.isNinePatchChunk(ninePatchChunk)) {
            rect = null;
        } else {
            bArr = ninePatchChunk;
        }
        return a(resources, a2, bArr, rect, str);
    }
}
