package com.unionpay.mobile.android.widgets;

import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupWindow;

/* loaded from: classes.dex */
final class i implements AdapterView.OnItemClickListener {
    final /* synthetic */ g a;

    i(g gVar) {
        this.a = gVar;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        PopupWindow popupWindow;
        PopupWindow popupWindow2;
        g gVar = this.a;
        gVar.a(gVar.c, this.a.v() + this.a.d());
        this.a.a(i);
        popupWindow = this.a.r;
        if (popupWindow != null) {
            popupWindow2 = this.a.r;
            popupWindow2.dismiss();
        }
    }
}
