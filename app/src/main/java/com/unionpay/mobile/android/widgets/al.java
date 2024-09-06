package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupWindow;

/* loaded from: classes.dex */
final class al implements AdapterView.OnItemClickListener {
    final /* synthetic */ ai a;

    al(ai aiVar) {
        this.a = aiVar;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        String a;
        PopupWindow popupWindow;
        PopupWindow popupWindow2;
        int intValue = ((Integer) view.getTag()).intValue();
        ai aiVar = this.a;
        Context context = aiVar.c;
        String str = this.a.v() + this.a.d();
        String[] strArr = com.unionpay.mobile.android.utils.o.h;
        a = this.a.a(intValue, i, "label");
        aiVar.a(context, str, strArr, new Object[]{ai.a(this.a, intValue, "type"), Integer.valueOf(i), a});
        this.a.a(intValue, i);
        popupWindow = this.a.p;
        if (popupWindow != null) {
            popupWindow2 = this.a.p;
            popupWindow2.dismiss();
        }
    }
}
