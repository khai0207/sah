package com.ipaynow.wechatpay.plugin.view;

import android.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.widget.FrameLayout;

/* loaded from: classes.dex */
final class b extends FrameLayout {
    private float ci;
    private Paint cj;
    private RectF ck;

    public b(Context context) {
        super(context);
        b(Color.parseColor("#b1000000"));
        setBackgroundColor(getContext().getResources().getColor(R.color.transparent));
    }

    public final void b(int i) {
        Paint paint = new Paint();
        this.cj = paint;
        paint.setColor(i);
        this.cj.setStyle(Paint.Style.FILL);
    }

    @Override // android.view.View
    protected final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = this.ck;
        float f = this.ci;
        canvas.drawRoundRect(rectF, f, f, this.cj);
    }

    @Override // android.view.View
    protected final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.ck = new RectF(0.0f, 0.0f, i, i2);
    }

    public final void setCornerRadius(float f) {
        this.ci = i.a(f, getContext());
    }
}
