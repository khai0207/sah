package com.unionpay.mobile.android.widgets;

import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupWindow;

/* loaded from: classes.dex */
final class c implements AdapterView.OnItemClickListener {
    final /* synthetic */ a a;

    c(a aVar) {
        this.a = aVar;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        PopupWindow popupWindow;
        PopupWindow popupWindow2;
        a aVar = this.a;
        aVar.a(aVar.c, this.a.v() + this.a.d());
        a.a(this.a, i);
        popupWindow = this.a.r;
        if (popupWindow != null) {
            popupWindow2 = this.a.r;
            popupWindow2.dismiss();
        }
    }
}
