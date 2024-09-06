package com.ipaynow.wechatpay.plugin.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

/* loaded from: classes.dex */
final class c extends View implements g {
    private RectF cf;
    private int cg;
    private int ch;
    private Paint cl;
    private Paint cm;

    /* renamed from: cn, reason: collision with root package name */
    private RectF f20cn;
    private float co;
    private float cp;

    public c(Context context) {
        super(context);
        this.cg = 100;
        this.ch = 0;
        Paint paint = new Paint(1);
        this.cl = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.cl.setStrokeWidth(i.a(2.0f, getContext()));
        this.cl.setColor(-1);
        Paint paint2 = new Paint(1);
        this.cm = paint2;
        paint2.setStyle(Paint.Style.FILL);
        this.cm.setColor(-1);
        this.cp = i.a(5.0f, getContext());
        float f = this.cp;
        this.f20cn = new RectF(f, f, ((getWidth() - this.cp) * this.ch) / this.cg, getHeight() - this.cp);
        this.co = i.a(10.0f, getContext());
        this.cf = new RectF();
    }

    @Override // android.view.View
    protected final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = this.cf;
        float f = this.co;
        canvas.drawRoundRect(rectF, f, f, this.cl);
        RectF rectF2 = this.f20cn;
        float f2 = this.co;
        canvas.drawRoundRect(rectF2, f2, f2, this.cm);
    }

    @Override // android.view.View
    protected final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(i.a(100.0f, getContext()), i.a(20.0f, getContext()));
    }

    @Override // android.view.View
    protected final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        float a = i.a(2.0f, getContext());
        this.cf.set(a, a, i - r4, i2 - r4);
    }

    @Override // com.ipaynow.wechatpay.plugin.view.g
    public final void setMax(int i) {
        this.cg = i;
    }
}
