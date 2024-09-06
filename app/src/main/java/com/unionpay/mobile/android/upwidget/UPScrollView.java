package com.unionpay.mobile.android.upwidget;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class UPScrollView extends ScrollView {
    private WeakReference<a> a;
    private int b;
    private ViewTreeObserver.OnGlobalLayoutListener c;
    private Handler d;

    /* loaded from: classes.dex */
    public interface a {
        void e(int i);
    }

    public UPScrollView(Context context) {
        this(context, null);
    }

    public UPScrollView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public UPScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = new t(this);
        this.c = new s(this);
    }

    public final void a(a aVar) {
        this.a = new WeakReference<>(aVar);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(this.c);
    }

    @Override // android.widget.ScrollView, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeGlobalOnLayoutListener(this.c);
    }

    @Override // android.widget.ScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        WeakReference<a> weakReference = this.a;
        if (weakReference != null && weakReference.get() != null) {
            a aVar = this.a.get();
            int scrollY = getScrollY();
            this.b = scrollY;
            aVar.e(scrollY);
        }
        if (motionEvent.getAction() == 1) {
            Handler handler = this.d;
            handler.sendMessageDelayed(handler.obtainMessage(), 5L);
        }
        return super.onTouchEvent(motionEvent);
    }
}
