package com.unionpay.mobile.android.widgets;

import android.view.View;
import android.widget.ImageView;
import com.unionpay.mobile.android.widgets.t;

/* loaded from: classes.dex */
final class w implements View.OnFocusChangeListener {
    final /* synthetic */ t a;

    w(t tVar) {
        this.a = tVar;
    }

    @Override // android.view.View.OnFocusChangeListener
    public final void onFocusChange(View view, boolean z) {
        ImageView imageView;
        ImageView imageView2;
        int i;
        t.b bVar;
        t.a aVar;
        t.a aVar2;
        t.b bVar2;
        ImageView imageView3;
        boolean b = t.b(this.a);
        if (z) {
            if (b) {
                imageView3 = this.a.c;
                if (imageView3 != null) {
                    imageView2 = this.a.c;
                    i = 0;
                    imageView2.setVisibility(i);
                }
            }
        } else if (b) {
            imageView = this.a.c;
            if (imageView != null) {
                imageView2 = this.a.c;
                i = 8;
                imageView2.setVisibility(i);
            }
        }
        bVar = this.a.e;
        if (bVar != null) {
            bVar2 = this.a.e;
            bVar2.a(z);
        }
        aVar = this.a.f;
        if (aVar != null) {
            aVar2 = this.a.f;
            aVar2.a(z);
        }
        t.g();
        this.a.invalidate();
    }
}
