package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/* loaded from: classes.dex */
public final class o extends View {
    private Paint a;
    private int b;

    public o(Context context) {
        this(context, -7829368, 0);
    }

    public o(Context context, int i, int i2) {
        super(context);
        Paint paint = new Paint();
        this.a = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.a.setStrokeWidth(4.0f);
        this.a.setColor(i);
        this.a.setPathEffect(null);
        this.b = i2;
    }

    @Override // android.view.View
    protected final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int i = this.b;
        if (i == 0) {
            float height = getHeight() >> 1;
            canvas.drawLine(0.0f, height, getWidth(), height, this.a);
        } else if (1 == i) {
            float width = getWidth() >> 1;
            canvas.drawLine(width, 0.0f, width, getHeight(), this.a);
        }
    }
}
