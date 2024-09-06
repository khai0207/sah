package com.ipaynow.wechatpay.plugin.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

/* loaded from: classes.dex */
final class a extends View implements g {
    private Paint cd;
    private Paint ce;
    private RectF cf;
    private int cg;
    private int ch;

    public a(Context context) {
        super(context);
        this.cg = 100;
        this.ch = 0;
        Paint paint = new Paint(1);
        this.cd = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.cd.setStrokeWidth(i.a(3.0f, getContext()));
        this.cd.setColor(-1);
        Paint paint2 = new Paint(1);
        this.ce = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        this.ce.setStrokeWidth(i.a(3.0f, getContext()));
        this.ce.setColor(Color.parseColor("#449E9E9E"));
        this.cf = new RectF();
    }

    @Override // android.view.View
    protected final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f = (this.ch * 360.0f) / this.cg;
        canvas.drawArc(this.cf, 270.0f, f, false, this.cd);
        canvas.drawArc(this.cf, f + 270.0f, 360.0f - f, false, this.ce);
    }

    @Override // android.view.View
    protected final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int a = i.a(40.0f, getContext());
        setMeasuredDimension(a, a);
    }

    @Override // android.view.View
    protected final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        float a = i.a(4.0f, getContext());
        this.cf.set(a, a, i - r4, i2 - r4);
    }

    @Override // com.ipaynow.wechatpay.plugin.view.g
    public final void setMax(int i) {
        this.cg = i;
    }
}
