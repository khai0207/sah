package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;

/* loaded from: classes.dex */
final class ak implements View.OnClickListener {
    final /* synthetic */ ai a;

    ak(ai aiVar) {
        this.a = aiVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        String a;
        PopupWindow popupWindow;
        PopupWindow popupWindow2;
        int intValue = ((Integer) view.getTag()).intValue();
        ai aiVar = this.a;
        Context context = aiVar.c;
        String str = this.a.v() + this.a.d();
        String[] strArr = com.unionpay.mobile.android.utils.o.h;
        a = this.a.a(intValue, 0, "label");
        aiVar.a(context, str, strArr, new Object[]{ai.a(this.a, intValue, "type"), 0, a});
        this.a.a(intValue, 0);
        popupWindow = this.a.p;
        if (popupWindow != null) {
            popupWindow2 = this.a.p;
            popupWindow2.dismiss();
        }
    }
}
