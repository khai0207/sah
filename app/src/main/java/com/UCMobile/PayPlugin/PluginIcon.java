package com.UCMobile.PayPlugin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

/* loaded from: classes.dex */
public class PluginIcon {
    Bitmap a;
    int[] b = null;
    Context c;
    private int d;

    static {
        System.loadLibrary("ucpayplugin");
    }

    public PluginIcon(Context context, int i) {
        this.a = null;
        this.c = null;
        this.c = context;
        this.d = i;
        try {
            this.a = new BitmapDrawable(com.unionpay.mobile.android.resource.a.class.getClassLoader().getResourceAsStream("res/drawable/mobilepayplugin.bin")).getBitmap().copy(Bitmap.Config.ARGB_8888, false);
        } catch (Exception unused) {
        }
    }

    public int getIconHeight() {
        Bitmap bitmap = this.a;
        if (bitmap != null) {
            return bitmap.getHeight();
        }
        return 0;
    }

    public int[] getIconPixels() {
        int[] iArr = this.b;
        if (iArr != null) {
            return iArr;
        }
        int width = this.a.getWidth();
        int height = this.a.getHeight();
        int rowBytes = this.a.getRowBytes() * height;
        Bitmap bitmap = this.a;
        if (bitmap != null) {
            int[] iArr2 = new int[rowBytes];
            this.b = iArr2;
            bitmap.getPixels(iArr2, 0, width, 0, 0, width, height);
        }
        for (int i = 0; i < rowBytes; i++) {
            int[] iArr3 = this.b;
            iArr3[i] = ((iArr3[i] >> 16) & 255) | ((iArr3[i] << 16) & 16711680) | (iArr3[i] & (-16711936));
        }
        return this.b;
    }

    public int getIconRowBytes() {
        Bitmap bitmap = this.a;
        if (bitmap != null) {
            return bitmap.getRowBytes();
        }
        return 0;
    }

    public int getIconWidth() {
        Bitmap bitmap = this.a;
        if (bitmap != null) {
            return bitmap.getWidth();
        }
        return 0;
    }
}
