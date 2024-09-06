package org.cocos2dx.lib;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.style.DynamicDrawableSpan;

/* loaded from: classes.dex */
public class PlaceholderImageSpan extends DynamicDrawableSpan {
    private int mBitmapHeight;
    private int mBitmapWidth;
    private int mOffsetY;

    @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
    }

    @Override // android.text.style.DynamicDrawableSpan
    public Drawable getDrawable() {
        return null;
    }

    public PlaceholderImageSpan(int i, int i2, float f) {
        this.mOffsetY = (int) f;
        this.mBitmapWidth = i;
        this.mBitmapHeight = i2;
    }

    @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
        if (fontMetricsInt != null) {
            fontMetricsInt.ascent = Math.min(0, (-this.mBitmapHeight) - this.mOffsetY);
            fontMetricsInt.descent = Math.max(0, -this.mOffsetY);
            fontMetricsInt.top = fontMetricsInt.ascent;
            fontMetricsInt.bottom = fontMetricsInt.descent;
        }
        return this.mBitmapWidth;
    }
}
