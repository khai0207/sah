package com.android.pc.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.core.view.ViewCompat;
import java.io.ByteArrayOutputStream;

/* loaded from: classes.dex */
public class Handler_Bitmap {
    public static final String textChangLine = "@";

    public static Bitmap scaleImg(Bitmap bitmap, int i, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(i / width, i2 / height);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    public static Drawable zoomDrawable(Drawable drawable, int i, int i2) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap drawableToBitmap = drawableToBitmap(drawable);
        Matrix matrix = new Matrix();
        matrix.postScale(i / intrinsicWidth, i2 / intrinsicHeight);
        return new BitmapDrawable(Bitmap.createBitmap(drawableToBitmap, 0, 0, intrinsicWidth, intrinsicHeight, matrix, true));
    }

    public static Drawable bitmap2Drawable(Bitmap bitmap) {
        return new BitmapDrawable(bitmap);
    }

    public static Bitmap createReflectedImage(Bitmap bitmap, int i) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.preScale(1.0f, -1.0f);
        double d = i;
        Double.isNaN(d);
        double d2 = height;
        Double.isNaN(d2);
        int i2 = (int) (d2 * (d / 100.0d));
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, i2, width, i2, matrix, false);
        Bitmap createBitmap2 = Bitmap.createBitmap(width, i2 + height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap2);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        canvas.drawBitmap(createBitmap, 0.0f, height + 0, (Paint) null);
        Paint paint = new Paint();
        paint.setShader(new LinearGradient(0.0f, bitmap.getHeight(), 0.0f, createBitmap2.getHeight() + 0, 1895825407, ViewCompat.MEASURED_SIZE_MASK, Shader.TileMode.MIRROR));
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRect(0.0f, height, width, createBitmap2.getHeight() + 0, paint);
        return createBitmap2;
    }

    public static Bitmap addFrame(Bitmap bitmap, int i) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Rect clipBounds = canvas.getClipBounds();
        clipBounds.bottom--;
        clipBounds.right--;
        Paint paint = new Paint();
        paint.setColor(i);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(clipBounds, paint);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        return createBitmap;
    }

    public static Bitmap getBitmap(byte[] bArr) {
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}
