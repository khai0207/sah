package com.UCMobile.PayPlugin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/* loaded from: classes.dex */
public class PluginSurfaceView extends SurfaceView {
    SurfaceHolder a;
    Paint b;
    Bitmap c;
    int[] d;
    private final int e;
    private boolean f;
    private Object g;

    static {
        System.loadLibrary("ucpayplugin");
    }

    public PluginSurfaceView(Context context, int i, int i2, int i3) {
        super(context);
        this.f = true;
        this.g = new Object();
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        SurfaceHolder holder = getHolder();
        this.a = holder;
        holder.setFormat(1);
        this.b = new Paint();
        this.e = i;
        this.c = new BitmapDrawable(com.unionpay.mobile.android.resource.a.class.getClassLoader().getResourceAsStream("res/drawable/mobilepayplugin.bin")).getBitmap().copy(Bitmap.Config.ARGB_8888, false);
        getHolder().setFormat(-3);
        getHolder().setFormat(1);
        getHolder().addCallback(new a(this));
        getHolder().setSizeFromLayout();
        setWillNotDraw(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeSurfaceChanged(int i, int i2, int i3, int i4);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeSurfaceCreated(int i);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeSurfaceDestroyed(int i);

    public int getIconHeight() {
        Bitmap bitmap = this.c;
        if (bitmap != null) {
            return bitmap.getHeight();
        }
        return 0;
    }

    public int[] getIconPixels() {
        int[] iArr = this.d;
        if (iArr != null) {
            return iArr;
        }
        int width = this.c.getWidth();
        int height = this.c.getHeight();
        int rowBytes = this.c.getRowBytes() * height;
        Bitmap bitmap = this.c;
        if (bitmap != null) {
            int[] iArr2 = new int[rowBytes];
            this.d = iArr2;
            bitmap.getPixels(iArr2, 0, width, 0, 0, width, height);
        }
        for (int i = 0; i < rowBytes; i++) {
            int[] iArr3 = this.d;
            iArr3[i] = ((iArr3[i] >> 16) & 255) | ((iArr3[i] << 16) & 16711680) | (iArr3[i] & (-16711936));
        }
        return this.d;
    }

    public int getIconRowBytes() {
        Bitmap bitmap = this.c;
        if (bitmap != null) {
            return bitmap.getRowBytes();
        }
        return 0;
    }

    public int getIconWidth() {
        Bitmap bitmap = this.c;
        if (bitmap != null) {
            return bitmap.getWidth();
        }
        return 0;
    }

    public void invalidateNPP() {
        synchronized (this.g) {
            this.f = false;
        }
    }
}
