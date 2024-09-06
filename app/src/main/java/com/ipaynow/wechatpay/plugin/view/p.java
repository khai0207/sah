package com.ipaynow.wechatpay.plugin.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

/* loaded from: classes.dex */
final class p extends ImageView implements j {
    private float cR;
    private int cS;
    private boolean cT;
    private Runnable cU;

    public p(Context context) {
        super(context);
        Context context2 = getContext();
        setImageBitmap(Bitmap.createScaledBitmap(((BitmapDrawable) h.a(context2, "cn_ipaynow_wechatpay_plugin.png")).getBitmap(), o.a(context2, 30.0f), o.a(context2, 30.0f), true));
        this.cS = 83;
        this.cU = new q(this);
    }

    @Override // com.ipaynow.wechatpay.plugin.view.j
    public final void a(float f) {
        this.cS = (int) (83.0f / f);
    }

    @Override // android.widget.ImageView, android.view.View
    protected final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.cT = true;
        post(this.cU);
    }

    @Override // android.widget.ImageView, android.view.View
    protected final void onDetachedFromWindow() {
        this.cT = false;
        super.onDetachedFromWindow();
    }

    @Override // android.widget.ImageView, android.view.View
    protected final void onDraw(Canvas canvas) {
        canvas.rotate(this.cR, getWidth() / 2, getHeight() / 2);
        super.onDraw(canvas);
    }
}
