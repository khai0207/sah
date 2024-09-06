package com.unionpay.mobile.android.widgets;

import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupWindow;

/* loaded from: classes.dex */
final class r implements AdapterView.OnItemClickListener {
    final /* synthetic */ p a;

    r(p pVar) {
        this.a = pVar;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        PopupWindow popupWindow;
        PopupWindow popupWindow2;
        p pVar = this.a;
        pVar.a(pVar.c, this.a.v() + this.a.d(), com.unionpay.mobile.android.utils.o.f, new Object[]{Integer.valueOf(i)});
        this.a.a(i);
        popupWindow = this.a.p;
        if (popupWindow != null) {
            popupWindow2 = this.a.p;
            popupWindow2.dismiss();
        }
    }
}
